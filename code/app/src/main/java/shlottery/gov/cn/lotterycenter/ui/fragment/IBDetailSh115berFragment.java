package shlottery.gov.cn.lotterycenter.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.BaseBean;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusSh115DetailBean;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.IssueBonusSh115DetailProtocol;
import shlottery.gov.cn.lotterycenter.ui.adapter.IBDetaiSh115lInfoAdapter;
import shlottery.gov.cn.lotterycenter.ui.adapter.IssueRecyCircleAdapter;
import shlottery.gov.cn.lotterycenter.ui.view.DividerItemDecoration;
import shlottery.gov.cn.lotterycenter.ui.view.ListViewWithoutScroll;

import static shlottery.gov.cn.lotterycenter.Base.Configure.RECY_NORMAL_CIRCLR;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/28 13:51
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class IBDetailSh115berFragment extends IBDetailBaseFragment {
    private RecyclerView mRecycleView;
    private ListViewWithoutScroll mInfoListview;
    private IssueRecyCircleAdapter mNumAdapter;
    private ArrayList<BaseBean> mNumberData = new ArrayList<>();
    private IBDetaiSh115lInfoAdapter mInfoAdapter;
    private ArrayList<IssueBonusSh115DetailBean.DataBean.IssueListBean> mInfoData = new ArrayList<>();
    private Intent mIntent;
    private TextView mDateTv;
    private TextView mCloseDate;
    private TextView mIssueNoTv;

    @Override
    protected void init() {
        mIntent = getActivity().getIntent();
        Bundle bundle = mIntent.getExtras();
        mLotid = (String) bundle.getSerializable("lotid");
        if (mNumberData != null) {
            mNumAdapter = new IssueRecyCircleAdapter(getActivity(), mNumberData, RECY_NORMAL_CIRCLR);
        }
        mInfoAdapter = new IBDetaiSh115lInfoAdapter(getActivity(), mInfoData);
    }

    @Override
    protected void handleMessge(Message msg) {
    }

    @Override
    protected View beginCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_ibsh115detail, null);
        mRecycleView = (RecyclerView) view.findViewById(R.id.issueDetail_NumberRecyclerView);
        mInfoListview = (ListViewWithoutScroll) view.findViewById(R.id.issueDetail_winInformationListview);
        mCloseDate = (TextView) view.findViewById(R.id.issueDetail_closeDate);
        mIssueNoTv = (TextView) view.findViewById(R.id.issueDetail_issueNo);
        mDateTv = (TextView) view.findViewById(R.id.issueDetail_date);
        mRecycleView.setAdapter(mNumAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.divider));
        mRecycleView.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mInfoListview.setAdapter(mInfoAdapter);
        return view;
    }

    @Override
    protected boolean beginLoad() {
        IssueBonusSh115DetailProtocol protocol = new IssueBonusSh115DetailProtocol(getActivity());
        protocol.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                LinkedHashMap<String, String> parmas = new LinkedHashMap<String, String>();
                parmas.put("lotId", "sh115");
                parmas.put("issueNo", mIssueNo);
                return parmas;
            }
        }, this);
        return false;
    }

    @Override
    protected void handleSucess(Object loadData) {
        if (loadData != null) {
            IssueBonusSh115DetailBean issueBonusNumberDetailBean = (IssueBonusSh115DetailBean) loadData;
            if (issueBonusNumberDetailBean.getRet() == 100) {
                mInfoData.clear();
                mInfoData.addAll(issueBonusNumberDetailBean.getData().getIssueList());
                initTitle(issueBonusNumberDetailBean.getData());

                String code = mInfoData.get(mInfoData.size() - 1).getBonusCode();
                initNumberData(code, mNumberData);
                Collections.reverse(mInfoData);
                mInfoAdapter.notifyDataSetChanged();
                mNumAdapter.notifyDataSetChanged();
            } else {

            }
        }
    }

    @Override
    protected void handleError() {

    }

    private void initTitle(IssueBonusSh115DetailBean.DataBean data) {
        if (isAdded()) {
            mCloseDate.setText(getActivity().getResources().getString(R.string.awarding_instructions, data.getBonusCloseDate()));
        }
        mDateTv.setText(data.getBonusDate());
        String issue = "";
        if (data.getIssueList() != null && data.getIssueList().size() > 0) {
            issue = data.getIssueList().get(data.getIssueList().size() - 1).getIssueNo();
            //issue = issue.substring(0, 8);
        }

        mIssueNoTv.setText("第" + issue + "期");
    }


    @Override
    protected String getTitle() {
        return  "11选5开奖详情";
    }
}
