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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.BaseBean;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusNumberDetailBean;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.IssueBonusNumDetailProtocol;
import shlottery.gov.cn.lotterycenter.ui.activity.NewsDetailActivity;
import shlottery.gov.cn.lotterycenter.ui.adapter.IBDetaiNumlInfoAdapter;
import shlottery.gov.cn.lotterycenter.ui.adapter.IssueRecyCircleAdapter;
import shlottery.gov.cn.lotterycenter.ui.view.ListViewWithoutScroll;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;

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

public class IBDetailNumberFragment extends IBDetailBaseFragment {
    private RecyclerView mRecycleView;
    private ListViewWithoutScroll mInfoListview;
    private IssueRecyCircleAdapter mNumAdapter;
    private ArrayList<BaseBean> mNumberData=new ArrayList<>();
    private IBDetaiNumlInfoAdapter mInfoAdapter;
    private ArrayList<IssueBonusNumberDetailBean.DataBean.BonusListBean> mInfoData = new ArrayList<>();
    private Intent mIntent;
    private TextView mIssueNoTv;
    private TextView mIssueDate;
    private TextView mTotalSaleMoney;
    private TextView mTotalPoolMoney;
    private TextView mCloseDate;
    private ImageView mJiangjiangImg;
    private int mNewdId;

    @Override
    protected void init() {
        mIntent = getActivity().getIntent();
        Bundle bundle = mIntent.getExtras();
        mLotid = (String) bundle.getSerializable("lotid");
        if (mNumberData != null) {
            mNumAdapter = new IssueRecyCircleAdapter(getActivity(), mNumberData, RECY_NORMAL_CIRCLR);
        }
        mInfoAdapter = new IBDetaiNumlInfoAdapter(getActivity(), mInfoData);
    }

    @Override
    protected void handleMessge(Message msg) {

    }

    @Override
    protected View beginCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_ib_numdetail, null);
        mRecycleView = (RecyclerView) view.findViewById(R.id.issueDetail_NumberRecyclerView);
        mInfoListview = (ListViewWithoutScroll) view.findViewById(R.id.issueDetail_winInformationListview);
        mIssueNoTv = (TextView) view.findViewById(R.id.issueDetail_issueNo);
        mJiangjiangImg= (ImageView) view.findViewById(R.id.issueDetail_jiajiangImg);
        mIssueDate = (TextView) view.findViewById(R.id.issueDetail_date);
        mTotalSaleMoney = (TextView) view.findViewById(R.id.issueDetail_totalMoney);
        mTotalPoolMoney = (TextView) view.findViewById(R.id.issueDetail_poolMoney);
        mCloseDate = (TextView) view.findViewById(R.id.issueDetail_closeDate);
        mRecycleView.setAdapter(mNumAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecycleView.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mInfoListview.setAdapter(mInfoAdapter);
        mJiangjiangImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
               Bundle bundle=new Bundle();
                bundle. putInt("id", mNewdId);
                bundle.putString("title", "资讯");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    protected boolean beginLoad() {
        IssueBonusNumDetailProtocol protocol = new IssueBonusNumDetailProtocol(getActivity());
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
            IssueBonusNumberDetailBean issueBonusNumberDetailBean = (IssueBonusNumberDetailBean) loadData;
            if (issueBonusNumberDetailBean.getRet() == 100) {
                mInfoData.clear();
                mInfoData.addAll(issueBonusNumberDetailBean.getData().getBonusList());
                String code=issueBonusNumberDetailBean.getData().getBonusCode();
                initNumberData(code,mNumberData);
                initTitle(issueBonusNumberDetailBean.getData());
                mInfoAdapter.notifyDataSetChanged();
                mNumAdapter.notifyDataSetChanged();
            } else {

            }
        }
    }

    @Override
    protected void handleError() {
    }

    private void initTitle(IssueBonusNumberDetailBean.DataBean data) {
        mNewdId=data.getPlusAward();
        if(data.getPlusAward()>0)
        {
            mJiangjiangImg.setVisibility(View.VISIBLE);
        }
        else
        {
            mJiangjiangImg.setVisibility(View.GONE);
        }
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
        return  "数字彩开奖详情";
    }
}
