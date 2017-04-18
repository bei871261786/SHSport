package shlottery.gov.cn.lotterycenter.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.NewsListBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.NewsListProtocol;
import shlottery.gov.cn.lotterycenter.ui.view.DividerItemDecoration;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

import static shlottery.gov.cn.lotterycenter.R.id.titlebar_indicator;

/**
 * 体彩活动列表页面
 */
public class HuodongActivity extends BaseActivity implements LoadCallback {
    private RecyclerView mRecycleView;
    private MyAdapter mAdapter;
    private ArrayList<NewsListBean.DataBean.ListBean> mAdapterData = new ArrayList<>();
    private String mTags = "";
    private int mPageNo = 1;
    private int mPageSize = 20;
    private MyListener mListener;
    private LinearLayout mBack;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_huodong);
        mRecycleView = (RecyclerView) findViewById(R.id.huodong_recycleview);
        mRecycleView.setLayoutManager(new LinearLayoutManager(HuodongActivity.this));
        mRecycleView.addItemDecoration(new DividerItemDecoration(HuodongActivity.this, R.drawable.divider2));
        mRecycleView.setAdapter(mAdapter);
        mBack = (LinearLayout) findViewById(R.id.titlebar_back_ll);
        mBack.setOnClickListener(mListener);
        initTitleBar();
        beginLoad();
    }

    private void initTitleBar() {
        LinearLayout mBack = (LinearLayout) findViewById(R.id.titlebar_back_ll);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titlebarTv = (TextView) findViewById(R.id.titlebar_tv);
        titlebarTv.setText("体彩活动");
        ImageView mIndicatorImg = (ImageView) findViewById(titlebar_indicator);
        mIndicatorImg.setVisibility(View.GONE);
    }

    @Override
    protected void init() {
        mAdapter = new MyAdapter();
        mListener = new MyListener();

    }

    @Override
    protected String getBaidutitle() {
        return "活动首页";
    }

    private void beginLoad() {
        LogUtils.i("newsFragment beginLoad:" + mTags);
        NewsListProtocol protocol = new NewsListProtocol(HuodongActivity.this);
        protocol.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                params.put("type", "5");
                params.put("tags", mTags + "");
                params.put("pageNo", mPageNo + "");
                params.put("pageSize", mPageSize + "");
                return params;
            }
        }, this);
    }

    @Override
    public void onSucess(Object o) {
        if (o != null) {
            NewsListBean listBean = (NewsListBean) o;
            if (listBean.getRet() == 100) {
                mAdapterData.clear();
                mAdapterData.addAll(listBean.getData().getList());
                LogUtils.i("newsFragment onSucess:" + mAdapterData.size());
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onError() {

    }


    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(HuodongActivity.this, IssueBounsDetailActivity.class);
            Bundle bundle = new Bundle();
            switch (view.getId()) {
                case R.id.titlebar_back_ll:
                    finish();
                    break;
            }
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<HuodongActivity.MyAdapter.MyHolder> {

        @Override
        public HuodongActivity.MyAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(HuodongActivity.this).inflate(R.layout.adapter_huodonglist, parent, false);
            return new HuodongActivity.MyAdapter.MyHolder(view);
        }

        @Override
        public void onBindViewHolder(HuodongActivity.MyAdapter.MyHolder holder, final int position) {
            LogUtils.i("newsFragment adapter onBindViewHolder:");
            String url = mAdapterData.get(position).getPicUrl();
            if (url != null && !url.isEmpty()) {
                Picasso.with(HuodongActivity.this).load(url).into(holder.imageView);
            }
            holder.date.setText(mAdapterData.get(position).getSummary());

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent inten = new Intent(HuodongActivity.this, NewsDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", mAdapterData.get(position).getId());
                    bundle.putString("title", "活动");
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
            ImageView imageView;
            TextView date;
            View view;

            public MyHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.adapter_huodong_Img);
                date = (TextView) itemView.findViewById(R.id.adapter_huodong_Datw);
                date.setAlpha((float) 0.75);
                view = itemView;
            }
        }
    }

}
