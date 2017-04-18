package shlottery.gov.cn.lotterycenter.ui.calculatebouns.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import shlottery.gov.cn.lotterycenter.callback.CalculateEventBus;
import shlottery.gov.cn.lotterycenter.ui.calculatebouns.adapter.CalcuNumGridViewAdapter;
import shlottery.gov.cn.lotterycenter.ui.view.GridViewWithoutScroll;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingDialog;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-01-0001 13:53
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class Pl5CalculateFragment extends CalculateBaseFragment {

    @BindView(R.id.calculate_issue_tv)
    TextView calculateIssueTv;
    @BindView(R.id.stoptime_tv)
    TextView stoptimeTv;
    @BindView(R.id.pl5_yl1_tv)
    TextView pl5Yl1Tv;
    @BindView(R.id.pl5_l1gridview)
    GridViewWithoutScroll pl5L1gridview;
    @BindView(R.id.pl5_yl2_tv)
    TextView pl5Yl2Tv;
    @BindView(R.id.pl5_l2gridview)
    GridViewWithoutScroll pl5L2gridview;
    @BindView(R.id.pl5_yl3_tv)
    TextView pl5Yl3Tv;
    @BindView(R.id.pl5_l3gridview)
    GridViewWithoutScroll pl5L3gridview;
    @BindView(R.id.pl5_yl4_tv)
    TextView pl5Yl4Tv;
    @BindView(R.id.pl5_l4gridview)
    GridViewWithoutScroll pl5L4gridview;
    @BindView(R.id.pl5_yl5_tv)
    TextView pl5Yl5Tv;
    @BindView(R.id.pl5_l5gridview)
    GridViewWithoutScroll pl5L5gridview;
    @BindView(R.id.scorll)
    ScrollView scorll;
    @BindView(R.id.calculate_top_ll)
    LinearLayout calculateTopLl;
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
    @BindView(R.id.calculate_money_tv)
    TextView calculateMoneyTv;
    @BindView(R.id.calculate_count_tv)
    TextView calculateCountTv;
    @BindView(R.id.calculate_deleteall_tv)
    TextView calculateDeleteallTv;
    @BindView(R.id.calculate_sum_bt)
    TextView calculateSumBt;
    @BindView(R.id.calculatebottom_ll)
    LinearLayout calculateBottomLl;
    @BindView(R.id.pl5_ball1_tv)
    TextView pl5Ball1Tv;
    @BindView(R.id.pl5_ball2_tv)
    TextView pl5Ball2Tv;
    @BindView(R.id.pl5_ball3_tv)
    TextView pl5Ball3Tv;
    @BindView(R.id.pl5_ball4_tv)
    TextView pl5Ball4Tv;
    @BindView(R.id.pl5_ball5_tv)
    TextView pl5Ball5Tv;


    private CalcuNumGridViewAdapter mPL5L1GridViewAdapter;
    private CalcuNumGridViewAdapter mPL5L2GridViewAdapter;
    private CalcuNumGridViewAdapter mPL5L3GridViewAdapter;
    private CalcuNumGridViewAdapter mPL5L4GridViewAdapter;
    private CalcuNumGridViewAdapter mPL5L5GridViewAdapter;

    private int count1;
    private int count2;
    private int count3;
    private int count4;
    private int count5;

    private List<String> mMissList = new ArrayList<>();//遗漏的集合
    private List<Map<Integer, Boolean>> lotteryData = new ArrayList<>();//提交数据
    private NumLotOrderBean numLotOrderBean;
    private int total;
    //    private IssueBonusBean issueBonusBean;
//    private IssueBonusNumberDetailBean issueBonusNumberDetailBean;//开奖数据详情
//    private String issueNo;
    private LoadingDialog loadingDialog;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            total = count1 * count2 * count3 * count4 * count5;
            switch (msg.what) {
                case 1:
                    calculateMoneyTv.setVisibility(View.VISIBLE);
                    calculateSumBt.setEnabled(true);
                    int value = Integer.parseInt(calculateMultipleEdit.getText().toString());
                    calculateCountTv.setText(total + "注");
                    calculateMoneyTv.setText(total * 2 * value + "元");
                    TextUtils.setStrColor(calculateCountTv, total + "注", total + "", getActivity().getResources().getColor(R.color.select_red));
                    TextUtils.setStrColor(calculateMoneyTv, total * 2 * value + "元", total * 2 * value + "", getActivity().getResources().getColor(R.color.select_red));
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
        View view = UIUtils.inflate(R.layout.fragment_pl5_cal);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        for (int i = 0; i < 10; i++) {
            mMissList.add("");
        }
        calculateSumBt.setEnabled(false);
        mPL5L1GridViewAdapter = new CalcuNumGridViewAdapter(mMissList, this.getActivity());
        mPL5L2GridViewAdapter = new CalcuNumGridViewAdapter(mMissList, this.getActivity());
        mPL5L3GridViewAdapter = new CalcuNumGridViewAdapter(mMissList, this.getActivity());
        mPL5L4GridViewAdapter = new CalcuNumGridViewAdapter(mMissList, this.getActivity());
        mPL5L5GridViewAdapter = new CalcuNumGridViewAdapter(mMissList, this.getActivity());
        setAdapter(pl5L1gridview, mPL5L1GridViewAdapter);
        setAdapter(pl5L2gridview, mPL5L2GridViewAdapter);
        setAdapter(pl5L3gridview, mPL5L3GridViewAdapter);
        setAdapter(pl5L4gridview, mPL5L4GridViewAdapter);
        setAdapter(pl5L5gridview, mPL5L5GridViewAdapter);
        pl5Yl1Tv.setVisibility(View.INVISIBLE);
        pl5Yl2Tv.setVisibility(View.INVISIBLE);
        pl5Yl3Tv.setVisibility(View.INVISIBLE);
        pl5Yl4Tv.setVisibility(View.INVISIBLE);
        pl5Yl5Tv.setVisibility(View.INVISIBLE);
        baseOnclickListener = new baseOnclickListener();
        calculateSumBt.setOnClickListener(baseOnclickListener);
        calculateDeleteallTv.setOnClickListener(baseOnclickListener);
        return view;
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


    private void meath(CalcuNumGridViewAdapter p) {
        if (p.equals(mPL5L1GridViewAdapter)) {
            count1 = 0;
            for (int i = 0; i < 10; i++) {
                if (p.getSelectionMap().get(i)) {
                    count1++;
                }
            }
        } else if (p.equals(mPL5L2GridViewAdapter)) {
            count2 = 0;
            for (int i = 0; i < 10; i++) {
                if (p.getSelectionMap().get(i)) {
                    count2++;
                }

            }
        } else if (p.equals(mPL5L3GridViewAdapter)) {
            count3 = 0;
            for (int i = 0; i < 10; i++) {
                if (p.getSelectionMap().get(i)) {
                    count3++;
                }

            }
        } else if (p.equals(mPL5L4GridViewAdapter)) {
            count4 = 0;
            for (int i = 0; i < 10; i++) {
                if (p.getSelectionMap().get(i)) {
                    count4++;
                }

            }
        } else if (p.equals(mPL5L5GridViewAdapter)) {
            count5 = 0;
            for (int i = 0; i < 10; i++) {
                if (p.getSelectionMap().get(i)) {
                    count5++;
                }
            }
        }
        if (count1 != 0 && count2 != 0 && count3 != 0 && count4 != 0 && count5 != 0) {
            Message message = new Message();
            message.what = 1;
            mHandler.sendMessage(message);
            calculateSumBt.setEnabled(true);
        } else {
            Message message = new Message();
            message.what = 2;
            mHandler.sendMessage(message);
            calculateSumBt.setEnabled(false);
        }
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void updatestickyUi(CalculateEventBus messageEvent) {
        issueBonusBean = messageEvent.getIssueBonusBean();
        for (int i = 0; i < issueBonusBean.getData().getList().size(); i++) {
            if (issueBonusBean.getData().getList().get(i).getLotId().equals("pl5")) {
                issueNo = issueBonusBean.getData().getList().get(i).getIssueNo();
                calculateIssueTv.setText("第" + issueBonusBean.getData().getList().get(i).getIssueNo() + "期");
                long time = 1000 * (long) issueBonusBean.getData().getList().get(i).getBonusTime();
                String s = DateUtils.formatDate(time);
                stoptimeTv.setText(s);
                String arr[] = issueBonusBean.getData().getList().get(i).getBonusCode().split(",");
                pl5Ball1Tv.setText(arr[0]);
                pl5Ball2Tv.setText(arr[1]);
                pl5Ball3Tv.setText(arr[2]);
                pl5Ball4Tv.setText(arr[3]);
                pl5Ball5Tv.setText(arr[4]);
            }
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateUi(CalculateEventBus messageEvent) {
        issueBonusNumberDetailBean = messageEvent.getIssueBonusNumberDetailBean();
        if (issueBonusNumberDetailBean != null) {
            if (issueBonusNumberDetailBean.getData().getLotId().equals("pl5")) {
                issueNo = issueBonusNumberDetailBean.getData().getIssueNo();
                calculateIssueTv.setText("第" + issueBonusNumberDetailBean.getData().getIssueNo() + "期");
//                long time = 1000 * (long) issueBonusNumberDetailBean.getData().getBonusDate();
//                String s = DateUtils.getPl5DateAndTime(time);
                stoptimeTv.setText(issueBonusNumberDetailBean.getData().getBonusDate());
                String arr[] = issueBonusNumberDetailBean.getData().getBonusCode().split(",");
                pl5Ball1Tv.setText(arr[0]);
                pl5Ball2Tv.setText(arr[1]);
                pl5Ball3Tv.setText(arr[2]);
                pl5Ball4Tv.setText(arr[3]);
                pl5Ball5Tv.setText(arr[4]);
                Logger.e("event");
            }
        }
    }


    @Override
    public void initLotteryData() {
        if (mNumLotOrderBeans == null) {
            mNumLotOrderBeans = new ArrayList<>();
        }
        lotteryData.clear();
        lotteryData.add(mPL5L1GridViewAdapter.getSelectionMap());
        lotteryData.add(mPL5L2GridViewAdapter.getSelectionMap());
        lotteryData.add(mPL5L3GridViewAdapter.getSelectionMap());
        lotteryData.add(mPL5L4GridViewAdapter.getSelectionMap());
        lotteryData.add(mPL5L5GridViewAdapter.getSelectionMap());
        numLotOrderBean = new NumLotOrderBean();
        numLotOrderBean.setLotteryData(lotteryData);
        numLotOrderBean.setCount(total);
        numLotOrderBean.setLotId("pl5");
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

    @Override
    public void clearData() {
        calculateMoneyTv.setVisibility(View.INVISIBLE);
        for (int i = 0; i < 10; i++) {
            mPL5L1GridViewAdapter.getSelectionMap().put(i, false);
            mPL5L2GridViewAdapter.getSelectionMap().put(i, false);
            mPL5L3GridViewAdapter.getSelectionMap().put(i, false);
            mPL5L4GridViewAdapter.getSelectionMap().put(i, false);
            mPL5L5GridViewAdapter.getSelectionMap().put(i, false);

        }
        mPL5L1GridViewAdapter.notifyDataSetChanged();
        mPL5L2GridViewAdapter.notifyDataSetChanged();
        mPL5L3GridViewAdapter.notifyDataSetChanged();
        mPL5L4GridViewAdapter.notifyDataSetChanged();
        mPL5L5GridViewAdapter.notifyDataSetChanged();
        meath(mPL5L1GridViewAdapter);
        meath(mPL5L2GridViewAdapter);
        meath(mPL5L3GridViewAdapter);
        meath(mPL5L4GridViewAdapter);
        meath(mPL5L5GridViewAdapter);
    }

    @Override
    protected String getTitle() {
        return "奖金计算排列5";
    }
}
