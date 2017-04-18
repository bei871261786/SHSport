package shlottery.gov.cn.lotterycenter.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusSoccerDetailBean;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.IssueBonusSoccerDetailProtocol;
import shlottery.gov.cn.lotterycenter.ui.adapter.IBDetaiBqcMatchAdapter;
import shlottery.gov.cn.lotterycenter.ui.adapter.IBDetaiJqcMatchAdapter;
import shlottery.gov.cn.lotterycenter.ui.adapter.IBDetaiSFMatchAdapter;
import shlottery.gov.cn.lotterycenter.ui.adapter.IBDetaiSoccerwinInfoAdapter;
import shlottery.gov.cn.lotterycenter.ui.view.ListViewWithoutScroll;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/31 13:34
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class IBDetailSoccerFragment extends IBDetailBaseFragment {
    private ArrayList<IssueBonusSoccerDetailBean.DataBean.MatchListBean> mResultData = new ArrayList<>();
    private ArrayList<IssueBonusSoccerDetailBean.DataBean.BonusListBean> mBonusData = new ArrayList<>();
    private BaseAdapter mResultAdapter;
    private IBDetaiSoccerwinInfoAdapter mBounstAdapter;
    private ListViewWithoutScroll mResultListview;
    private ListViewWithoutScroll mBounsListview;
    private String mIssueName;
    private ScrollView mScrollview;
    private Intent mIntent;
    private TextView mIssueNoTv;
    private TextView mIssueDate;
    private TextView mTotalSaleMoney;
    private TextView mTotalPoolMoney;
    private TextView mCloseDate;

    public IBDetailSoccerFragment() {
        Bundle bundle = getArguments();
        if (bundle != null && bundle.getString("titlename") != null) {
            this.mIssueName = bundle.getString("titlename");
        }
    }


    @Override
    protected void init() {
        mIntent = getActivity().getIntent();
        Bundle bundle = mIntent.getExtras();
        mLotid = (String) bundle.getSerializable("lotid");
        mResultAdapter = getAdapter(mLotid);
        mBounstAdapter = new IBDetaiSoccerwinInfoAdapter(getActivity(), mBonusData);
    }

    @Override
    protected void handleMessge(Message msg) {

    }

    @Override
    protected View beginCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_ib_soccerdetail, null);
        mResultListview = (ListViewWithoutScroll) view.findViewById(R.id.issueDetail_resultListview);
        mBounsListview = (ListViewWithoutScroll) view.findViewById(R.id.issueDetail_winInforListview);
        mIssueNoTv = (TextView) view.findViewById(R.id.issueDetail_issueNo);
        mIssueDate = (TextView) view.findViewById(R.id.issueDetail_date);
        mTotalSaleMoney = (TextView) view.findViewById(R.id.issueDetail_totalMoney);
        mTotalPoolMoney = (TextView) view.findViewById(R.id.issueDetail_poolMoney);
        mScrollview = (ScrollView) view.findViewById(R.id.issueDetail_scrollview);
        mCloseDate = (TextView) view.findViewById(R.id.issueDetail_closeDate);
        mResultListview.setAdapter(mResultAdapter);
        mBounsListview.setAdapter(mBounstAdapter);
        return view;
    }

    @Override
    protected boolean beginLoad() {
        IssueBonusSoccerDetailProtocol protocol = new IssueBonusSoccerDetailProtocol(getActivity());
        protocol.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                LinkedHashMap<String, String> parmas = new LinkedHashMap<String, String>();
                parmas.put("lotId", mLotid);
                parmas.put("issueNo", mIssueNo);
                return parmas;
            }
        }, this);
        return false;
    }

    @Override
    protected void handleSucess(Object loadData) {
        if (loadData != null) {
            IssueBonusSoccerDetailBean dataBean = (IssueBonusSoccerDetailBean) loadData;
            if (dataBean.getRet() == 100) {
                mResultData.clear();
                mBonusData.clear();
                mResultData.addAll(dataBean.getData().getMatchList());
                mBonusData.addAll(dataBean.getData().getBonusList());
                initTitle(dataBean.getData());
                mResultAdapter.notifyDataSetChanged();
                mBounstAdapter.notifyDataSetChanged();
                mScrollview.smoothScrollTo(0, 0);
            }
        }
    }

    @Override
    protected void handleError() {

    }

    private BaseAdapter getAdapter(String lotid) {
        if (lotid.equals("sfc")) {
            return new IBDetaiSFMatchAdapter(getActivity(), mResultData);
        }
        if (lotid.equals("bqc")) {
            return new IBDetaiBqcMatchAdapter(getActivity(), mResultData);
        }
        if (lotid.equals("jqc")) {
            return new IBDetaiJqcMatchAdapter(getActivity(), mResultData);
        }
        return null;

    }

    private void initTitle(IssueBonusSoccerDetailBean.DataBean data) {
        mIssueNoTv.setText("第" + data.getIssueNo() + "期");
        mIssueDate.setText(data.getBonusDate());
        mTotalSaleMoney.setText(TextUtils.checkNumber(data.getSalesMoney()));
            mTotalPoolMoney.setText(TextUtils.checkNumber(data.getPoolMoney()) );
        if (isAdded()) {
            mCloseDate.setText(getActivity().getResources().getString(R.string.awarding_instructions, data.getBonusCloseDate()));
        }
    }

    @Override
    protected String getTitle() {
        return "足彩开奖详情";
    }
}
