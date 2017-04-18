package shlottery.gov.cn.lotterycenter.ui.calculatebouns.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.NumLotOrderBean;
import shlottery.gov.cn.lotterycenter.bean.NumLotteryBean;
import shlottery.gov.cn.lotterycenter.callback.CalculateEventBus;
import shlottery.gov.cn.lotterycenter.ui.calculatebouns.adapter.CalcuNumGridViewAdapter;
import shlottery.gov.cn.lotterycenter.ui.view.GridViewWithoutScroll;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-01-0001 16:45
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class QxcCalculateFragment extends CalculateBaseFragment {


    @BindView(R.id.calculate_issue_tv)
    TextView calculateIssueTv;
    @BindView(R.id.stoptime_tv)
    TextView stoptimeTv;
    @BindView(R.id.calculate_top_ll)
    LinearLayout calculateTopLl;
    @BindView(R.id.qxc_yilou1_tv)
    TextView qxcYilou1Tv;
    @BindView(R.id.qxc_l1gridview)
    GridViewWithoutScroll qxcL1gridview;
    @BindView(R.id.qxc_yilou2_tv)
    TextView qxcYilou2Tv;
    @BindView(R.id.qxc_l2gridview)
    GridViewWithoutScroll qxcL2gridview;
    @BindView(R.id.qxc_yilou3_tv)
    TextView qxcYilou3Tv;
    @BindView(R.id.qxc_l3gridview)
    GridViewWithoutScroll qxcL3gridview;
    @BindView(R.id.qxc_yilou4_tv)
    TextView qxcYilou4Tv;
    @BindView(R.id.qxc_l4gridview)
    GridViewWithoutScroll qxcL4gridview;
    @BindView(R.id.qxc_yilou5_tv)
    TextView qxcYilou5Tv;
    @BindView(R.id.qxc_l5gridview)
    GridViewWithoutScroll qxcL5gridview;
    @BindView(R.id.qxc_yilou6_tv)
    TextView qxcYilou6Tv;
    @BindView(R.id.qxc_l6gridview)
    GridViewWithoutScroll qxcL6gridview;
    @BindView(R.id.qxc_yilou7_tv)
    TextView qxcYilou7Tv;
    @BindView(R.id.qxc_l7gridview)
    GridViewWithoutScroll qxcL7gridview;
    @BindView(R.id.qxc_ll)
    LinearLayout qxcLl;
    @BindView(R.id.qxc_scorll)
    ScrollView qxcScorll;
    @BindView(R.id.textbei_tv)
    TextView textbeiTv;
    @BindView(R.id.calculate_plus_im)
    ImageView calculatePlusIm;
    @BindView(R.id.calculate_multiple_edit)
    EditText calculateMultipleEdit;
    @BindView(R.id.calculate_minus_im)
    ImageView calculateMinusIm;
    @BindView(R.id.beishu_tv)
    TextView beishuTv;
    @BindView(R.id.calculate_count_tv)
    TextView calculateCountTv;
    @BindView(R.id.calculate_money_tv)
    TextView calculateMoneyTv;
    @BindView(R.id.calculate_deleteall_tv)
    TextView calculateDeleteallTv;
    @BindView(R.id.calculate_sum_bt)
    TextView calculateSumBt;
    @BindView(R.id.calculatebottom_ll)
    LinearLayout calculatebottomLl;
    @BindView(R.id.qxc_ball1_tv)
    TextView qxcBall1Tv;
    @BindView(R.id.qxc_ball2_tv)
    TextView qxcBall2Tv;
    @BindView(R.id.qxc_ball3_tv)
    TextView qxcBall3Tv;
    @BindView(R.id.qxc_ball4_tv)
    TextView qxcBall4Tv;
    @BindView(R.id.qxc_ball5_tv)
    TextView qxcBall5Tv;
    @BindView(R.id.qxc_ball6_tv)
    TextView qxcBall6Tv;
    @BindView(R.id.qxc_ball7_tv)
    TextView qxcBall7Tv;
    @BindView(R.id.qxc_fragment_cal)
    RelativeLayout qxcFragmentCal;
    private List<String> mMissList = new ArrayList<>();//遗漏的集合
    private CalcuNumGridViewAdapter mQXCL1GridViewAdapter;
    private CalcuNumGridViewAdapter mQXCL2GridViewAdapter;
    private CalcuNumGridViewAdapter mQXCL3GridViewAdapter;
    private CalcuNumGridViewAdapter mQXCL4GridViewAdapter;
    private CalcuNumGridViewAdapter mQXCL5GridViewAdapter;
    private CalcuNumGridViewAdapter mQXCL6GridViewAdapter;
    private CalcuNumGridViewAdapter mQXCL7GridViewAdapter;

    private int count1;
    private int count2;
    private int count3;
    private int count4;
    private int count5;
    private int count6;
    private int count7;

    private List<Map<Integer, Boolean>> lotteryData = new ArrayList<>();//提交数据
    private NumLotOrderBean numLotOrderBean;
    int total;//总注数
    private NumLotteryBean mNumLotteryBean;

//    private IssueBonusBean issueBonusBean;
//    private IssueBonusNumberDetailBean issueBonusNumberDetailBean;//开奖数据详情
//    private String issueNo;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            total = count1 * count2 * count3 * count4 * count5 * count6 * count7;
            switch (msg.what) {
                case 1:
                    calculateMoneyTv.setVisibility(View.VISIBLE);
                    calculateSumBt.setEnabled(true);
                    int value=Integer.parseInt(calculateMultipleEdit.getText().toString());
                    calculateCountTv.setText(total + "注");
                    calculateMoneyTv.setText(total * 2 *value+ "元");
                    TextUtils.setStrColor(calculateCountTv, total + "注", total + "", getActivity().getResources().getColor(R.color.select_red));
                    TextUtils.setStrColor(calculateMoneyTv, total * 2*value + "元", total * 2 *value+ "", getActivity().getResources().getColor(R.color.select_red));
                    break;
                case 2:
                    calculateMoneyTv.setVisibility(View.INVISIBLE);
                    calculateSumBt.setEnabled(false);
                    calculateCountTv.setText("请选择投注号码");
                    break;
            }

        }
    };

    @Override
    public View initView() {
        View mView = UIUtils.inflate(R.layout.fragment_qxc_cal);
        ButterKnife.bind(this, mView);
        EventBus.getDefault().register(this);
        for (int i = 0; i < 10; i++) {
            mMissList.add("");
        }
        calculateSumBt.setEnabled(false);//查询不可点击
        mQXCL1GridViewAdapter = new CalcuNumGridViewAdapter(mMissList, getActivity());
        mQXCL2GridViewAdapter = new CalcuNumGridViewAdapter(mMissList, getActivity());
        mQXCL3GridViewAdapter = new CalcuNumGridViewAdapter(mMissList, getActivity());
        mQXCL4GridViewAdapter = new CalcuNumGridViewAdapter(mMissList, getActivity());
        mQXCL5GridViewAdapter = new CalcuNumGridViewAdapter(mMissList, getActivity());
        mQXCL6GridViewAdapter = new CalcuNumGridViewAdapter(mMissList, getActivity());
        mQXCL7GridViewAdapter = new CalcuNumGridViewAdapter(mMissList, getActivity());
        setAdapter(qxcL1gridview, mQXCL1GridViewAdapter);
        setAdapter(qxcL2gridview, mQXCL2GridViewAdapter);
        setAdapter(qxcL3gridview, mQXCL3GridViewAdapter);
        setAdapter(qxcL4gridview, mQXCL4GridViewAdapter);
        setAdapter(qxcL5gridview, mQXCL5GridViewAdapter);
        setAdapter(qxcL6gridview, mQXCL6GridViewAdapter);
        setAdapter(qxcL7gridview, mQXCL7GridViewAdapter);

        baseOnclickListener = new baseOnclickListener();
        calculateSumBt.setOnClickListener(baseOnclickListener);
        calculateDeleteallTv.setOnClickListener(baseOnclickListener);
        return mView;
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void updatestickyUi(CalculateEventBus messageEvent) {
        issueBonusBean = messageEvent.getIssueBonusBean();

        if (issueBonusBean != null) {
            for (int i = 0; i < issueBonusBean.getData().getList().size(); i++) {
                if (issueBonusBean.getData().getList().get(i).getLotId().equals("qxc")) {
                    issueNo = issueBonusBean.getData().getList().get(i).getIssueNo();
                    calculateIssueTv.setText("第"+issueBonusBean.getData().getList().get(i).getIssueNo() + "期");
                    long time = 1000 * (long) issueBonusBean.getData().getList().get(i).getBonusTime();
                    String s = DateUtils.formatDate(time);
                    stoptimeTv.setText(s);
                    String arr[] = issueBonusBean.getData().getList().get(i).getBonusCode().split(",");
                    qxcBall1Tv.setText(arr[0]);
                    qxcBall2Tv.setText(arr[1]);
                    qxcBall3Tv.setText(arr[2]);
                    qxcBall4Tv.setText(arr[3]);
                    qxcBall5Tv.setText(arr[4]);
                    qxcBall6Tv.setText(arr[5]);
                    qxcBall7Tv.setText(arr[6]);
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateUi(CalculateEventBus messageEvent) {
        issueBonusNumberDetailBean = messageEvent.getIssueBonusNumberDetailBean();

        if (issueBonusNumberDetailBean != null) {
            if (issueBonusNumberDetailBean.getData().getLotId().equals("qxc")) {
                issueNo = issueBonusNumberDetailBean.getData().getIssueNo();
                calculateIssueTv.setText("第"+issueBonusNumberDetailBean.getData().getIssueNo() + "期");
//                long time = 1000 * (long) issueBonusNumberDetailBean.getData().getBonusDate();
//                String s = DateUtils.getPl5DateAndTime(time);
                stoptimeTv.setText(issueBonusNumberDetailBean.getData().getBonusDate());
                String arr[] = issueBonusNumberDetailBean.getData().getBonusCode().split(",");
                qxcBall1Tv.setText(arr[0]);
                qxcBall2Tv.setText(arr[1]);
                qxcBall3Tv.setText(arr[2]);
                qxcBall4Tv.setText(arr[3]);
                qxcBall5Tv.setText(arr[4]);
                qxcBall5Tv.setText(arr[5]);
                qxcBall5Tv.setText(arr[6]);
                Logger.e("event");
            }
        }
    }


    private void setAdapter(GridViewWithoutScroll g, final CalcuNumGridViewAdapter p) {
        g.setAdapter(p);
        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                p.setSelectMap(position);
                p.notifyDataSetChanged();
                meath(p);
            }
        });
    }

    @Override
    public void initLotteryData() {
        if (mNumLotOrderBeans == null) {
            mNumLotOrderBeans = new ArrayList<>();
        }
        lotteryData.clear();
        lotteryData.add(mQXCL1GridViewAdapter.getSelectionMap());
        lotteryData.add(mQXCL2GridViewAdapter.getSelectionMap());
        lotteryData.add(mQXCL3GridViewAdapter.getSelectionMap());
        lotteryData.add(mQXCL4GridViewAdapter.getSelectionMap());
        lotteryData.add(mQXCL5GridViewAdapter.getSelectionMap());
        lotteryData.add(mQXCL6GridViewAdapter.getSelectionMap());
        lotteryData.add(mQXCL7GridViewAdapter.getSelectionMap());
        numLotOrderBean = new NumLotOrderBean();
        numLotOrderBean.setLotteryData(lotteryData);
        numLotOrderBean.setCount(total);
        numLotOrderBean.setLotId("qxc");
        numLotOrderBean.setIssueNo(issueNo);
        numLotOrderBean.setMultiple(calculateMultipleEdit.getText().toString());
        if (total > 1) {
            numLotOrderBean.setGuoguanType("f");
        } else {
            numLotOrderBean.setGuoguanType("d");
        }
        mNumLotOrderBeans.clear();
        mNumLotOrderBeans.add(numLotOrderBean);
    }


    private void meath(CalcuNumGridViewAdapter p) {
        if (p.equals(mQXCL1GridViewAdapter)) {
            count1 = 0;
            for (int i = 0; i < 10; i++) {
                if (p.getSelectionMap().get(i)) {
                    count1++;
                }
            }
        } else if (p.equals(mQXCL2GridViewAdapter)) {
            count2 = 0;
            for (int i = 0; i < 10; i++) {
                if (p.getSelectionMap().get(i)) {
                    count2++;
                }
            }
        } else if (p.equals(mQXCL3GridViewAdapter)) {
            count3 = 0;
            for (int i = 0; i < 10; i++) {
                if (p.getSelectionMap().get(i)) {
                    count3++;
                }

            }
        } else if (p.equals(mQXCL4GridViewAdapter)) {
            count4 = 0;
            for (int i = 0; i < 10; i++) {
                if (p.getSelectionMap().get(i)) {
                    count4++;
                }

            }
        } else if (p.equals(mQXCL5GridViewAdapter)) {
            count5 = 0;
            for (int i = 0; i < 10; i++) {
                if (p.getSelectionMap().get(i)) {
                    count5++;
                }
            }
        } else if (p.equals(mQXCL6GridViewAdapter)) {
            count6 = 0;
            for (int i = 0; i < 10; i++) {
                if (p.getSelectionMap().get(i)) {
                    count6++;
                }
            }
        } else if (p.equals(mQXCL7GridViewAdapter)) {
            count7 = 0;
            for (int i = 0; i < 10; i++) {
                if (p.getSelectionMap().get(i)) {
                    count7++;
                }
            }
        }
        if (count1 != 0 && count2 != 0 && count3 != 0 && count4 != 0 && count5 != 0 && count6 != 0 && count7 != 0) {
            Message message = new Message();
            message.what = 1;
            mHandler.sendMessage(message);
        } else {
            Message message = new Message();
            message.what = 2;
            mHandler.sendMessage(message);
        }
    }

    @Override
    public void clearData() {
        for (int i = 0; i < 10; i++) {
            mQXCL1GridViewAdapter.getSelectionMap().put(i, false);
            mQXCL2GridViewAdapter.getSelectionMap().put(i, false);
            mQXCL3GridViewAdapter.getSelectionMap().put(i, false);
            mQXCL4GridViewAdapter.getSelectionMap().put(i, false);
            mQXCL5GridViewAdapter.getSelectionMap().put(i, false);
            mQXCL6GridViewAdapter.getSelectionMap().put(i, false);
            mQXCL7GridViewAdapter.getSelectionMap().put(i, false);
            mQXCL1GridViewAdapter.notifyDataSetChanged();
            mQXCL2GridViewAdapter.notifyDataSetChanged();
            mQXCL3GridViewAdapter.notifyDataSetChanged();
            mQXCL4GridViewAdapter.notifyDataSetChanged();
            mQXCL5GridViewAdapter.notifyDataSetChanged();
            mQXCL6GridViewAdapter.notifyDataSetChanged();
            mQXCL7GridViewAdapter.notifyDataSetChanged();
            meath(mQXCL1GridViewAdapter);
            meath(mQXCL2GridViewAdapter);
            meath(mQXCL3GridViewAdapter);
            meath(mQXCL4GridViewAdapter);
            meath(mQXCL5GridViewAdapter);
            meath(mQXCL6GridViewAdapter);
            meath(mQXCL7GridViewAdapter);
        }
    }
    @Override
    protected String getTitle() {
        return "奖金计算七星彩";
    }

}
