package shlottery.gov.cn.lotterycenter.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.NewsListBean;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.NewsListProtocol;
import shlottery.gov.cn.lotterycenter.ui.activity.NewsDetailActivity;
import shlottery.gov.cn.lotterycenter.ui.view.DividerItemDecoration;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/12/13 10:48
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class RmdAnalysisFragment extends RmdBaseFragment {
    private MyAdapter mAdapter;

    private ArrayList<NewsListBean.DataBean.ListBean> mAdapterData = new ArrayList<>();

    @Override
    protected void prepareInit() {
        mAdapter = new MyAdapter();
    }

    @Override
    protected void prepareCreateView() {
        mRecycleview.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.divider));
        mRecycleview.setAdapter(mAdapter);
    }

    @Override
    protected boolean beginLoad() {
        LogUtils.i("RmdFragment beginLoad:" + getTags());
        NewsListProtocol protocol = new NewsListProtocol(getActivity());
        protocol.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                params.put("type", "4");
                params.put("tags", getTags() + "");
                params.put("pageNo", mPageNo + "");
                params.put("pageSize", mPageSize + "");
                return params;
            }
        }, this);
        return false;
    }


    @Override
    protected void handleSucess(Object loadData) {
        LogUtils.i("RmdFragment  success :" + loadData);
        mController.setLoadDataStatus(false);
        mRefreshLayout.setRefreshing(false);
        if (loadData != null) {
            NewsListBean listbean = (NewsListBean) loadData;
            if (listbean.getRet() == 100) {
                mPageCount = listbean.getData().getPageCount();
                if (!isLoadMore) {
                    mAdapterData.clear();
                }

                mAdapterData.addAll(listbean.getData().getList());
                mAdapter.notifyDataSetChanged();
                LogUtils.i("RmdFragment  success data:" + mAdapterData.size());
            }
            isLoadMore = false;
        }
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
        LogUtils.i("RmdFragment loadMore" + mPageNo);
    }

    class MyAdapter extends RecyclerView.Adapter<RmdAnalysisFragment.MyAdapter.MyHolder> {
        @Override
        public RmdAnalysisFragment.MyAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LogUtils.i("RmdFragment adapter onCreateViewHolder:");
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.adapter_newslist, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(RmdAnalysisFragment.MyAdapter.MyHolder holder, final int position) {
            LogUtils.i("RmdFragment adapter onBindViewHolder:" + position + ":::" + mAdapterData.size());
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
                    bundle.putString("title", "体彩分析");
                    inten.putExtras(bundle);
                    startActivity(inten);
                }
            });
        }

        @Override
        public int getItemCount() {
            LogUtils.i("RmdFragment adapter getItemCount:" + mAdapterData.size());
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
                LogUtils.i("RmdFragment adapter MyHolder:");
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
        return "体彩分析列表";
    }
}
