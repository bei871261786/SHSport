package shlottery.gov.cn.lotterycenter.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.BaseFragment2;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.JifenBean;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.JifenProtacol;
import shlottery.gov.cn.lotterycenter.ui.view.DividerItemDecoration;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadDataScrollController;
import shlottery.gov.cn.lotterycenter.utils.DateTimePickDialogUtil;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/18 15:59
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class AccountJifenFragment extends BaseFragment2 implements LoadDataScrollController.OnRecycleRefreshListener {
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private JifenBean.DataBean mData;
    private List<JifenBean.DataBean.ListBean> mDataList;
    private TextView mStartTimeEdit;
    private TextView mEndTimeEdit;
    private TextView mTotalInteral;
    private String mStartDateTime = ""; // 初始化开始时间
    private String mEndDateTime = ""; // 初始化结束时间
    private Button mQuery;
    private MyListener mListener;
    private int mPageCount;
    private int mPageNo = 1;
    private int mPageSize = 20;
    private boolean isLoadMore = false;
    private SwipeRefreshLayout mRefreshLayout;
    private LoadDataScrollController mController;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mStartDateTime = mStartTimeEdit.getText().toString();
            mEndDateTime = mEndTimeEdit.getText().toString();
            boolean isExchange = compareDate(mStartDateTime, mEndDateTime);
            if (isExchange) {
                String temp = mStartDateTime;
                mStartDateTime = mEndDateTime;
                mEndDateTime = temp;
                mStartTimeEdit.setText(mStartDateTime);
                mEndTimeEdit.setText(mEndDateTime);
            }
            LogUtils.i("当前时间 ：" + mStartDateTime + "::::" + mEndDateTime);
        }
    };

    @Override
    protected void init() {
        mData = new JifenBean.DataBean();
        mDataList = new ArrayList<>();
        //        mAdapter = new MyAdapter(mDataList);
        mAdapter = new MyAdapter();
        mListener = new MyListener();
        mController = new LoadDataScrollController(this);

    }

    @Override
    protected void handleMessge(Message msg) {

    }

    @Override
    protected View beginCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_jifen, null);
        mStartTimeEdit = (TextView) view.findViewById(R.id.account_starttime);
        mEndTimeEdit = (TextView) view.findViewById(R.id.account_endtime);
        mTotalInteral = (TextView) view.findViewById(R.id.account_total);
        mQuery = (Button) view.findViewById(R.id.account_query);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.account_jifen_Recycleview);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.account_jifen_refreshLayout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.divider));
        mRecyclerView.addOnScrollListener(mController);
        mRefreshLayout.setOnRefreshListener(mController);

        mStartTimeEdit.setOnClickListener(mListener);
        mEndTimeEdit.setOnClickListener(mListener);
        mQuery.setOnClickListener(mListener);
        mStartTimeEdit.setInputType(InputType.TYPE_NULL);
        mEndTimeEdit.setInputType(InputType.TYPE_NULL);
        mStartTimeEdit.setText(mStartDateTime);
        mEndTimeEdit.setText(mEndDateTime);
        return view;
    }

    @Override
    protected boolean beginLoad() {
        JifenProtacol protacol = new JifenProtacol(getActivity());
        protacol.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                params.put("dateFrom", mStartDateTime);
                params.put("dateTo", mEndDateTime);
                params.put("pageNo", mPageNo + "");
                params.put("pageSize", mPageSize + "");
                return params;
            }
        }, this);
        return false;
    }

    @Override
    protected void handleSucess(Object loadData) {
        mController.setLoadDataStatus(false);
        mRefreshLayout.setRefreshing(false);
        if (null != loadData) {
            JifenBean bean = (JifenBean) loadData;
            if (bean.getRet() == 100) {
                mData = bean.getData();
                mPageCount = mData.getPageCount();
                mTotalInteral.setText("总积分：" + mData.getIntegral() + "分");
                if (isAdded()) {
                    TextUtils.setStrColor(mTotalInteral, "总积分：" + mData.getIntegral() + "分", mData.getIntegral() +
                            "", getResources().getColor(R.color.select_red));
                }
                if (!isLoadMore) {
                    mDataList.clear();
                }
                mDataList.addAll(mData.getList());
                LogUtils.i("jifen success:" + mData.getIntegral() + ":::" + mDataList.size());
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void handleError() {
        mRefreshLayout.setRefreshing(false);
        mController.setLoadDataStatus(false);
        isLoadMore = false;
    }

    //前面小返回false，否则返回true
    private boolean compareDate(String startTime, String endTime) {
        if (startTime.equals("") || endTime.equals("")) {
            return false;
        }
        String start = startTime.replace("-", "");
        String end = endTime.replace("-", "");
        int startValue = Integer.valueOf(start);
        int endValue = Integer.valueOf(end);
        if (startValue <= endValue) {
            return false;
        } else {
            return true;
        }
    }

    public void refresh() {
        isLoadMore = false;
        mPageNo = 1;
        LogUtils.i("accountVoucherfragmnt refresh" + mPageNo);
        refreshLoad();
    }

    @Override
    public void loadMore() {
        isLoadMore = true;
        mRefreshLayout.setRefreshing(true);
        mPageNo++;
        LogUtils.i("loadMore:" + mPageNo + ":::" + mPageCount);
        if (mPageNo > mPageCount) {
            mPageNo--;
            UIUtils.showToastSafe("没有更多数据了");
            mRefreshLayout.setRefreshing(false);
        } else {
            refreshLoad();
        }
        LogUtils.i("accountVoucherfragmnt loadMore" + mPageNo);
    }


    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            mStartTimeEdit.setText(mStartDateTime);
            switch (view.getId()) {
                case R.id.account_starttime:
                    if ("".equals(mStartDateTime)) {
                        Calendar calendar = Calendar.getInstance();
                        long currentTime = calendar.getTimeInMillis();
                        mStartDateTime = DateUtils.formatDate(currentTime);
                    }
                    DateTimePickDialogUtil dateTimePicKDialog1 = new DateTimePickDialogUtil(
                            getActivity(), mStartDateTime);
                    dateTimePicKDialog1.createTimePicKDialog(mStartTimeEdit, handler);
                    break;
                case R.id.account_endtime:
                    if ("".equals(mEndDateTime)) {
                        Calendar calendar = Calendar.getInstance();
                        long currentTime = calendar.getTimeInMillis();
                        mEndDateTime = DateUtils.formatDate(currentTime);
                    }
                    DateTimePickDialogUtil dateTimePicKDialog2 = new DateTimePickDialogUtil(
                            getActivity(), mEndDateTime);
                    dateTimePicKDialog2.createTimePicKDialog(mEndTimeEdit, handler);
                    break;
                case R.id.account_query:
                    isLoadMore = false;
                    refreshLoad();
                    break;
            }
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
        //        private List<JifenBean.DataBean.ListBean> data;

        //        public MyAdapter(List<JifenBean.DataBean.ListBean> data) {
        //            this.data = data;
        //        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LogUtils.i("MyAdapter onCreateViewHolder :");
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_jifen, parent, false);
            MyHolder holder = new MyHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            LogUtils.i("MyAdapter onBindViewHolder :" + mDataList.get(position).getIntegral() + ":::" + mDataList.get
                    (position).getDate() + ":::" + mDataList.get(position).getType());
            String type = mDataList.get(position).getType();
            if (type.equals("兑换")) {
                holder.integral.setText(mDataList.get(position).getIntegral() + "");
            } else {
                holder.integral.setText("+" + mDataList.get(position).getIntegral() + "");
            }
            String time = mDataList.get(position).getDate();
            time = time.substring(0, 10);
            holder.type.setText(type);
            holder.time.setText(time);
        }

        @Override
        public int getItemCount() {
            LogUtils.i("MyAdapter getItemCount :" + mDataList.size());
            return mDataList.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView time;
            TextView type;
            TextView integral;

            public MyHolder(View itemView) {
                super(itemView);
                time = (TextView) itemView.findViewById(R.id.item_jifen_time);
                type = (TextView) itemView.findViewById(R.id.item_jifen_type);
                integral = (TextView) itemView.findViewById(R.id.item_jifen_integral);

            }
        }
    }


    @Override
    protected String getTitle() {
        return "积分";
    }
}
