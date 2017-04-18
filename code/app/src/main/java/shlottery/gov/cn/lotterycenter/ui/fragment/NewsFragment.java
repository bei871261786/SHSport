package shlottery.gov.cn.lotterycenter.ui.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.Base.BaseFragment;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.NewsListBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.callback.NewsFlitrateEventBus;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.NewsListProtocol;
import shlottery.gov.cn.lotterycenter.ui.activity.NewsDetailActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.NewsFlitrateActivity;
import shlottery.gov.cn.lotterycenter.ui.view.DividerItemDecoration;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadDataScrollController;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingPager;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/12/7 14:41
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class NewsFragment extends BaseFragment implements LoadCallback, LoadDataScrollController.OnRecycleRefreshListener {
    private RecyclerView mShdtRecycleview;
    private MyAdapter mAdapter;
    private int mType = 1;
    private String mTags = "";
    private int mPageNo = 1;
    private int mPageSize = 20;
    private int mPageCount;
    private ArrayList<NewsListBean.DataBean.ListBean> mShdtData = new ArrayList<>();
    private ArrayList<NewsListBean.DataBean.ListBean> mQgtcData = new ArrayList<>();
    private ArrayList<NewsListBean.DataBean.ListBean> mGgtzData = new ArrayList<>();
    private ArrayList<NewsListBean.DataBean.ListBean> mAdapterData = new ArrayList<>();
    private RadioGroup mHeadGrouup;
    private MyListenr mListener;
    private ImageView mFlitrate;
    private NewsFlitrateEventBus mEventbusData = new NewsFlitrateEventBus();
    private boolean isLoadMore = false;
    private SwipeRefreshLayout mRefreshLayout;
    private LoadDataScrollController mController;
    private Boolean isClearFlitrateTag = false;
    private ArrayList<Integer> mTypeList = new ArrayList<>();

    @Override
    protected View createLoadedView() {
        init();
        View view = UIUtils.inflate(R.layout.fragment_news);
        mShdtRecycleview = (RecyclerView) view.findViewById(R.id.news_recycleview_shdt);
        mFlitrate = (ImageView) view.findViewById(R.id.news_flitrare);
        mHeadGrouup = (RadioGroup) view.findViewById(R.id.radioGroup);
        mFlitrate.setOnClickListener(mListener);
        mShdtRecycleview.setLayoutManager(new LinearLayoutManager(BaseApplication.getApplication()));
        mShdtRecycleview.addItemDecoration(new DividerItemDecoration(BaseApplication.getApplication(), R.drawable.divider));
        mShdtRecycleview.setAdapter(mAdapter);
        mHeadGrouup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioGroup.getChildAt(0).getId() == i) {
                    toggleHead(0);
                } else if (radioGroup.getChildAt(1).getId() == i) {
                    toggleHead(1);
                } else if (radioGroup.getChildAt(2).getId() == i) {
                    toggleHead(2);
                }

            }
        });
        initRefreshLayout(view);
        beginLoad();
        return view;
    }

    //在recycleview被初始化之后调用
    private void initRefreshLayout(View view) {
        mController = new LoadDataScrollController(this);
        mShdtRecycleview.addOnScrollListener(mController);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.account_jifen_refreshLayout);
        mRefreshLayout.setOnRefreshListener(mController);
        mRefreshLayout.setColorSchemeResources(R.color.homeblue);
    }

    @Override
    protected LoadingPager.LoadResult Load() {
        return check(0);
    }

    private void init() {
        EventBus.getDefault().register(this);
        mListener = new MyListenr();
        mAdapter = new MyAdapter();
        mTypeList.add(3);
    }

    @Override
    public void refreshData() {
        toggleHead(0);
    }

    public void beginLoad() {
        LogUtils.i("newsFragment beginLoad:" + mTags);
        NewsListProtocol protocol = new NewsListProtocol(getActivity());
        protocol.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                params.put("type", mType + "");
                params.put("tags", mTags + "");
                params.put("pageNo", mPageNo + "");
                params.put("pageSize", mPageSize + "");
                return params;
            }
        }, this);
    }

    @Override
    public void onSucess(Object o) {
        mController.setLoadDataStatus(false);
        mRefreshLayout.setRefreshing(false);
        if (o != null) {
            NewsListBean listbean = (NewsListBean) o;
            if (listbean.getRet() == 100) {
                mPageCount = listbean.getData().getPageCount();
                mAdapterData.clear();

                if (mType == 1) {
                    if (!isLoadMore) {
                        mShdtData.clear();
                    }
                    mShdtData.addAll(listbean.getData().getList());
                    mAdapterData.addAll(mShdtData);
                } else if (mType == 2) {
                    if (!isLoadMore) {
                        mQgtcData.clear();
                    }
                    mQgtcData.addAll(listbean.getData().getList());
                    mAdapterData.addAll(mQgtcData);
                } else if (mType == 3) {
                    if (!isLoadMore) {
                        mGgtzData.clear();
                    }
                    mGgtzData.addAll(listbean.getData().getList());
                    mAdapterData.addAll(mGgtzData);
                }
                LogUtils.i("newsFragment onsuccess:" + mAdapterData.size());
                mAdapter.notifyDataSetChanged();
            }
            isLoadMore = false;
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(NewsFlitrateEventBus event) {
        LogUtils.i("onEvent NewsFragment:");
        mEventbusData = event;
        String tag = "";
        if (event != null) {
            ArrayList<String> tagList = null;
            if (event.getLottery() != null) {
                tagList = event.getLottery();
                for (int i = 0; i < tagList.size(); i++) {

                    if (i == tagList.size() - 1) {
                        tag += tagList.get(i) + ",";
                        continue;
                    }
                    tag += tagList.get(i) + "|";
                    LogUtils.i("newsFragment eventbus getLottery:" + tag);
                }
            }
            if (event.getCategory() != null) {
                tagList = event.getCategory();
                for (int i = 0; i < tagList.size(); i++) {

                    if (i == tagList.size() - 1) {
                        tag += tagList.get(i) + ",";
                        continue;
                    }
                    tag += tagList.get(i) + "|";
                    LogUtils.i("newsFragment eventbus getCategory:" + tag);
                }
            }
            if (event.getDistrict() != null) {
                tagList = event.getDistrict();
                for (int i = 0; i < tagList.size(); i++) {

                    if (i == tagList.size() - 1) {
                        tag += tagList.get(i) + ",";
                        continue;
                    }
                    tag += tagList.get(i) + "|";
                }
            }
            if (event.getFamous() != null) {
                tagList = event.getFamous();
                for (int i = 0; i < tagList.size(); i++) {
                    if (i == tagList.size() - 1) {
                        tag += tagList.get(i) + ",";
                        continue;
                    }
                    tag += tagList.get(i) + "|";
                }
            }
            if (tag.contains(",")) {
                tag = tag.substring(0, tag.length() - 1);
            } else if (tag.contains("|")) {
                tag = tag.substring(0, tag.length() - 1);
            }
        }
        mTags = tag;
        beginLoad();

    }

    @Override
    public void onError() {
        mRefreshLayout.setRefreshing(false);
        mController.setLoadDataStatus(false);
        isLoadMore = false;
    }


    //更换头部标签时更换recycleview中的数据

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void toggleHead(int headType) {
        LogUtils.i("toggleHead :" + headType);
        Resources resources = BaseApplication.getApplication().getResources();
        mTypeList.clear();
        isClearFlitrateTag = true;
        mTags = "";
        mPageNo = 1;
        switch (headType) {
            case 0:
                mType = 1;
                mTypeList.add(3);
                break;
            case 1:
                mType = 2;
                mTypeList.add(3);
                mTypeList.add(4);
                break;
            case 2:
                mType = 3;
                mTypeList.add(3);
                mTypeList.add(4);
                break;
        }
        beginLoad();
    }

    @Override
    public void refresh() {
        isLoadMore = false;
        mPageNo = 1;
        beginLoad();
    }

    @Override
    public void loadMore() {
        isLoadMore = true;
        mRefreshLayout.setRefreshing(true);
        mPageNo++;
        LogUtils.i("loadMore:" + mPageNo + ":::" + mPageCount);
        if (mPageNo > mPageCount) {
            isLoadMore = false;
            mPageNo--;
            UIUtils.showToastSafe("没有更多数据了");
            mRefreshLayout.setRefreshing(false);
        } else {
            beginLoad();
        }

        LogUtils.i("accountVoucherfragmnt loadMore" + mPageNo);
    }

    private class MyListenr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.news_flitrare:
                    Intent intent = new Intent(getActivity(), NewsFlitrateActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("tagEventbus", mEventbusData);
                    bundle.putBoolean("isClearFlitrateTag", isClearFlitrateTag);
                    bundle.putIntegerArrayList("hideType", mTypeList);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    isClearFlitrateTag = false;
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.adapter_newslist, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, final int position) {
            LogUtils.i("newsFragment adapter onBindViewHolder:" + position + ":::" + mAdapterData.size());
            holder.title.setText(mAdapterData.get(position).getTitle());
            holder.date.setText(mAdapterData.get(position).getNewsTime());
            String tags = mAdapterData.get(position).getTags();
            String[] tagArr;
            if (tags != null && !tags.isEmpty()) {
                if (tags.contains(",")) {
                    tagArr = tags.split(",");
                } else {
                    tagArr = new String[1];
                    tagArr[0] = tags;
                    holder.tab1.setVisibility(View.VISIBLE);
                    holder.tab2.setVisibility(View.GONE);
                    holder.tab3.setVisibility(View.GONE);
                }
                if (tagArr.length == 1) {
                    holder.tab1.setText(tagArr[0]);
                    holder.tab1.setVisibility(View.VISIBLE);
                    holder.tab2.setVisibility(View.GONE);
                    holder.tab3.setVisibility(View.GONE);
                } else if (tagArr.length == 2) {
                    holder.tab1.setText(tagArr[0]);
                    holder.tab2.setText(tagArr[1]);
                    holder.tab1.setVisibility(View.VISIBLE);
                    holder.tab2.setVisibility(View.VISIBLE);
                    holder.tab3.setVisibility(View.GONE);
                } else {
                    holder.tab1.setText(tagArr[0]);
                    holder.tab2.setText(tagArr[1]);
                    holder.tab3.setText(tagArr[2]);
                    holder.tab1.setVisibility(View.VISIBLE);
                    holder.tab2.setVisibility(View.VISIBLE);
                    holder.tab3.setVisibility(View.VISIBLE);
                }
            } else {
                holder.tab1.setVisibility(View.GONE);
                holder.tab2.setVisibility(View.GONE);
                holder.tab3.setVisibility(View.GONE);
            }
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent inten = new Intent(getActivity(), NewsDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", mAdapterData.get(position).getId());
                    bundle.putString("title", "资讯");
                    inten.putExtras(bundle);
                    startActivity(inten);

                }
            });
        }

        @Override
        public int getItemCount() {
            LogUtils.i("newsFragment adapter getItemCount:" + mAdapterData.size());
            return mAdapterData.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView tab1;
            TextView tab2;
            TextView tab3;
            TextView date;
            View view;

            public MyHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.adapter_newlist_title);
                tab1 = (TextView) itemView.findViewById(R.id.adapter_newlist_tab1);
                tab2 = (TextView) itemView.findViewById(R.id.adapter_newlist_tab2);
                tab3 = (TextView) itemView.findViewById(R.id.adapter_newlist_tab3);
                date = (TextView) itemView.findViewById(R.id.adapter_newlist_date);
                view = itemView;
            }
        }
    }

    @Override
    protected String getTitle() {
        return "新闻首页";
    }
}
