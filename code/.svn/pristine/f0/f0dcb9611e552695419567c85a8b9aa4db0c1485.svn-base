package shlottery.gov.cn.lotterycenter.ui.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import shlottery.gov.cn.lotterycenter.Base.BaseFragment2;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.ui.activity.RecommendActivity;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadDataScrollController;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/12/13 10:48
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public abstract class RmdBaseFragment extends BaseFragment2 implements LoadDataScrollController.OnRecycleRefreshListener {
    protected int mPageNo = 1;
    protected int mPageSize = 20;
    protected int mPageCount;
    protected boolean isLoadMore = false;
    protected RecyclerView mRecycleview;
    protected SwipeRefreshLayout mRefreshLayout;
    protected LoadDataScrollController mController;

    public void refreshData() {
        beginLoad();
    }

    @Override
    protected void init() {
        prepareInit();

    }

    protected abstract void prepareInit();

    protected abstract void prepareCreateView();

    //在recycleview被初始化之后调用
    private void initRefreshLayout(View view) {
        mController = new LoadDataScrollController(this);
        mRecycleview.addOnScrollListener(mController);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.account_jifen_refreshLayout);
        mRefreshLayout.setOnRefreshListener(mController);
    }

    @Override
    protected void handleMessge(Message msg) {

    }

    protected String getTags() {
        RecommendActivity activity = (RecommendActivity) getActivity();
        return activity.getTag();
    }

    @Override
    protected View beginCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_rmd, null);
        mRecycleview = (RecyclerView) view.findViewById(R.id.recommend_recycleview);
        mRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));

        prepareCreateView();
        initRefreshLayout(view);
        return view;
    }


    @Override
    protected void handleError() {
        isLoadMore = false;
    }





}
