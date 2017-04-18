package shlottery.gov.cn.lotterycenter.ui.calculatebouns.fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-21-0021 15:35
 * 描    述：
 * 修订历史：
 * ================================================
 */

@SuppressLint("ValidFragment")
public class CalPL3NomalFragment extends CalculateBaseFragment {


    @BindView(R.id.pl3_l1gridview)
    GridViewWithoutScroll pl3L1gridview;
    @BindView(R.id.pl3_l2gridview)
    GridViewWithoutScroll pl3L2gridview;
    @BindView(R.id.pl3_l3gridview)
    GridViewWithoutScroll pl3L3gridview;
    @BindView(R.id.scorll)
    ScrollView scorll;
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
    private List<String> mMissList = new ArrayList<>();//遗漏的集合
    private List<String> mMissList1 = new ArrayList<>();//遗漏的集合
    private List<String> mMissList2 = new ArrayList<>();//遗漏的集合
    private CalcuNumGridViewAdapter mPL3L1GridViewAdapter;
    private CalcuNumGridViewAdapter mPL3L2GridViewAdapter;
    private CalcuNumGridViewAdapter mPL3L3GridViewAdapter;

    private int count1;
    private int count2;
    private int count3;

    private List<Map<Integer, Boolean>> lotteryData = new ArrayList<>();//提交数据
    int total;


//    private IssueBonusBean issueBonusBean;
//    private IssueBonusNumberDetailBean issueBonusNumberDetailBean;//开奖数据详情
//    private String issueNo;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            total = count1 * count2 * count3;
            switch (msg.what) {
                case 1:
                    int value=Integer.parseInt(calculateMultipleEdit.getText().toString());
                    calculateMoneyTv.setVisibility(View.VISIBLE);
                    calculateSumBt.setEnabled(true);
                    calculateCountTv.setText(total + "注");
                    calculateMoneyTv.setText(total * 2 *value+ "元");
                    TextUtils.setStrColor(calculateCountTv, total + "注", total + "", getActivity().getResources().getColor(R.color.select_red));
                    TextUtils.setStrColor(calculateMoneyTv, total * 2 *value+ "元", total * 2*value + "", getActivity().getResources().getColor(R.color.select_red));
                    break;
                case 2:
                    calculateMoneyTv.setVisibility(View.INVISIBLE);
                    calculateSumBt.setEnabled(false);
                    calculateCountTv.setText("请选择投注号码");
                    break;
            }

        }
    };


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
        if (p.equals(mPL3L1GridViewAdapter)) {
            count1 = 0;
            for (int i = 0; i < 10; i++) {
                if (p.getSelectionMap().get(i)) {
                    count1++;
                }
            }
        } else if (p.equals(mPL3L2GridViewAdapter)) {
            count2 = 0;
            for (int i = 0; i < 10; i++) {
                if (p.getSelectionMap().get(i)) {
                    count2++;
                }

            }
        } else if (p.equals(mPL3L3GridViewAdapter)) {
            count3 = 0;
            for (int i = 0; i < 10; i++) {
                if (p.getSelectionMap().get(i)) {
                    count3++;
                }

            }
        }
        if (count1 != 0 && count2 != 0 && count3 != 0) {
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
            mPL3L1GridViewAdapter.getSelectionMap().put(i, false);
            mPL3L2GridViewAdapter.getSelectionMap().put(i, false);
            mPL3L3GridViewAdapter.getSelectionMap().put(i, false);
        }
        mPL3L1GridViewAdapter.notifyDataSetChanged();
        mPL3L2GridViewAdapter.notifyDataSetChanged();
        mPL3L3GridViewAdapter.notifyDataSetChanged();
        meath(mPL3L1GridViewAdapter);
        meath(mPL3L2GridViewAdapter);
        meath(mPL3L3GridViewAdapter);

    }

    @Override
    public void initLotteryData() {
        lotteryData.clear();
        if (mNumLotOrderBeans == null) {
            mNumLotOrderBeans = new ArrayList<>();
        }
        lotteryData.add(mPL3L1GridViewAdapter.getSelectionMap());
        lotteryData.add(mPL3L2GridViewAdapter.getSelectionMap());
        lotteryData.add(mPL3L3GridViewAdapter.getSelectionMap());
        NumLotOrderBean numLotOrderBean = new NumLotOrderBean();
        numLotOrderBean.setLotteryData(lotteryData);
        numLotOrderBean.setCount(total);
        numLotOrderBean.setLotId("pl3");
        if (total > 1) {
            numLotOrderBean.setGuoguanType("f");
        } else {
            numLotOrderBean.setGuoguanType("d");
        }
        numLotOrderBean.setMultiple(calculateMultipleEdit.getText().toString());
        numLotOrderBean.setIssueNo(issueNo);
        mNumLotOrderBeans.clear();
        mNumLotOrderBeans.add(numLotOrderBean);
    }


    @Override
    public View initView() {
        View mView = UIUtils.inflate(R.layout.fragment_pl3cal_nomal);
        ButterKnife.bind(this, mView);
        EventBus.getDefault().register(this);
        for (int i = 0; i < 10; i++) {
            mMissList.add("-");
        }
        calculateMoneyTv.setVisibility(View.INVISIBLE);
        calculateSumBt.setEnabled(false);
        calculateCountTv.setText("请选择投注号码");
        mPL3L1GridViewAdapter = new CalcuNumGridViewAdapter(mMissList, getActivity());
        mPL3L2GridViewAdapter = new CalcuNumGridViewAdapter(mMissList, getActivity());
        mPL3L3GridViewAdapter = new CalcuNumGridViewAdapter(mMissList, getActivity());
        setAdapter(pl3L1gridview, mPL3L1GridViewAdapter);
        setAdapter(pl3L2gridview, mPL3L2GridViewAdapter);
        setAdapter(pl3L3gridview, mPL3L3GridViewAdapter);
        baseOnclickListener = new baseOnclickListener();
        calculateSumBt.setOnClickListener(baseOnclickListener);
        calculateDeleteallTv.setOnClickListener(baseOnclickListener);
        return mView;
    }

    //延迟消息更新开奖数据  fragment 还未创建
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void updatestickyUi(CalculateEventBus messageEvent) {
        issueBonusBean = messageEvent.getIssueBonusBean();
        if (issueBonusBean != null) {
            for (int i = 0; i < issueBonusBean.getData().getList().size(); i++) {
                if (issueBonusBean.getData().getList().get(i).getLotId().equals("pl3")) {
                    issueNo = issueBonusBean.getData().getList().get(i).getIssueNo();
                }
            }
        }
    }

    //选择期号后,重新获取数据,更新到ui  fragment已经创建
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateUi(CalculateEventBus messageEvent) {
        issueBonusNumberDetailBean = messageEvent.getIssueBonusNumberDetailBean();
        if (issueBonusNumberDetailBean != null) {
            if (issueBonusNumberDetailBean.getData().getLotId().equals("pl3")) {
                issueNo = issueBonusNumberDetailBean.getData().getIssueNo();
            }
        }
    }
    @Override
    protected String getTitle() {
        return "奖金计算排列三直选";
    }
}
