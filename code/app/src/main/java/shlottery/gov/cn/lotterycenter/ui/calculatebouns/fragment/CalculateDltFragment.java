package shlottery.gov.cn.lotterycenter.ui.calculatebouns.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.BaseBean;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusBean;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusNumberDetailBean;
import shlottery.gov.cn.lotterycenter.bean.LottoSelectData;
import shlottery.gov.cn.lotterycenter.bean.NumLotOrderBean;
import shlottery.gov.cn.lotterycenter.bean.OrderBean;
import shlottery.gov.cn.lotterycenter.callback.CaculateAnsyTask;
import shlottery.gov.cn.lotterycenter.callback.CaculateResulCallback;
import shlottery.gov.cn.lotterycenter.callback.CalculateEventBus;
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.LottoGridViewAdapter;
import shlottery.gov.cn.lotterycenter.ui.view.GridViewWithoutScroll;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

import static shlottery.gov.cn.lotterycenter.Base.Configure.LOCATION_DEFAULE;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-02-0002 14:33
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class CalculateDltFragment extends CalculateBaseFragment implements CaculateResulCallback {
    @BindView(R.id.calculate_issue_tv)
    TextView calculateIssueTv;
    @BindView(R.id.stoptime_tv)
    TextView stoptimeTv;
    @BindView(R.id.dlt_ball1_tv)
    TextView dltBall1Tv;
    @BindView(R.id.dlt_ball2_tv)
    TextView dltBall2Tv;
    @BindView(R.id.dlt_ball3_tv)
    TextView dltBall3Tv;
    @BindView(R.id.dlt_ball4_tv)
    TextView dltBall4Tv;
    @BindView(R.id.dlt_ball5_tv)
    TextView dltBall5Tv;
    @BindView(R.id.dlt_ball6_tv)
    TextView dltBall6Tv;
    @BindView(R.id.dlt_ball7_tv)
    TextView dltBall7Tv;
    @BindView(R.id.calculate_top_ll)
    LinearLayout calculateTopLl;
    @BindView(R.id.lotto_normal_qianqu)
    GridViewWithoutScroll lottoNormalQianqu;
    @BindView(R.id.lotto_normal_houqu)
    GridViewWithoutScroll lottoNormalHouqu;
    @BindView(R.id.dlt_ll)
    LinearLayout dltLl;
    @BindView(R.id.dlt_scorll)
    ScrollView dltScorll;
    @BindView(R.id.textbei_tv)
    TextView textbeiTv;
    /* @BindView(R.id.calculate_plus_im)
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
     LinearLayout calculatebottomLl;*/
    @BindView(R.id.dlt_fragment_cal)
    RelativeLayout dltFragmentCal;
    @BindView(R.id.zhuijia)
    TextView zhuijia;
    @BindView(R.id.zhuijia_cxb)
    CheckBox zhuijiaCxb;

    private IssueBonusBean issueBonusBean;
    private IssueBonusNumberDetailBean issueBonusNumberDetailBean;//开奖数据详情
    private String issueNo;

    private OrderBean bean = new OrderBean();

    private LottoGridViewAdapter mQianquAdapter;
    private LottoGridViewAdapter mHouquAdapter;

    protected ArrayList<LottoSelectData> mQianquData = new ArrayList<>();
    protected ArrayList<LottoSelectData> mHouquData = new ArrayList<>();

    protected boolean isCanPay = false;//没完成计算情况下，禁止跳转订单
    protected long mTotalCount;

    private CaculateAnsyTask mAnsyTask;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Object red = mQianquData;
            Object blue = mHouquData;
            List<BaseBean> dataRed = (List<BaseBean>) red;
            List<BaseBean> dataBlue = (List<BaseBean>) blue;
            mHouquAdapter.notifyDataSetChanged();
            mQianquAdapter.notifyDataSetChanged();
            updateBounds(dataRed, dataBlue);
            if (checkDingdan()) {
                calculateSumBt.setEnabled(true);
            } else {
                calculateSumBt.setEnabled(false);
            }

        }
    };
    private String stakeAdd="";

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.fragment_dlt_cal);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initData();
        mQianquAdapter = new LottoGridViewAdapter(getActivity(), LOCATION_DEFAULE, mQianquData, true, true, handler);
        mHouquAdapter = new LottoGridViewAdapter(getActivity(), LOCATION_DEFAULE, mHouquData, false, true, handler);
        setAdapter(lottoNormalQianqu, mQianquAdapter);
        setAdapter(lottoNormalHouqu, mHouquAdapter);

        baseOnclickListener = new baseOnclickListener();
        zhuijiaCxb.setChecked(false);
        zhuijiaCxb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    stakeAdd = "1";
                } else {
                    stakeAdd = "";
                }
                sendMsg();
            }
        });
        return view;
    }


    protected void updateBounds(List<BaseBean> dataRed, List<BaseBean> dataBlue) {
        isCanPay = false;
        if (mAnsyTask != null) {
            mAnsyTask.cancel(true);
        }
        mAnsyTask = new CaculateAnsyTask(this);
        mAnsyTask.execute(dataRed, dataBlue);
    }

    private void setAdapter(GridViewWithoutScroll g, final LottoGridViewAdapter p) {
        g.setAdapter(p);
    }

    private void initData() {
        mQianquData.clear();
        mHouquData.clear();
        String j = "";
        for (int i = 1; i <= 35; i++) {
            if (i < 10) {
                j = "0";
            } else {
                j = "";
            }
            LottoSelectData bean = new LottoSelectData();
            bean.setSelected(false);
            bean.setMsg(j + i);
            bean.setDan(false);
            bean.setIsRedArea(true);
            bean.setMinCount(5);
            bean.setLocation(LOCATION_DEFAULE);
            mQianquData.add(bean);
        }

        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                j = "0";
            } else {
                j = "";
            }
            LottoSelectData bean = new LottoSelectData();
            bean.setSelected(false);
            bean.setMsg(j + i);
            bean.setDan(false);
            bean.setMinCount(2);
            bean.setIsRedArea(false);
            bean.setLocation(LOCATION_DEFAULE);
            mHouquData.add(bean);
        }
    }

    protected boolean checkDingdan() {
        boolean isCan = true;
        int qianCount = 0;
        int houCount = 0;
        for (int i = 0; i < mQianquData.size(); i++) {
            if (mQianquData.get(i).isSelected()) {
                qianCount++;
            }
        }

        for (int i = 0; i < mHouquData.size(); i++) {
            if (mHouquData.get(i).isSelected()) {
                houCount++;
            }
        }

        if (qianCount < 5 || houCount < 2) {
            isCan = false;
        }
        return isCan;
    }


    public void sendMsg() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("dataRed", mQianquData);
        bundle.putSerializable("dataBlue", mHouquData);
        Message msg = new Message();
        msg.obj = bundle;
        handler.sendMessage(msg);
    }

    @Override
    public void initLotteryData() {
        addToOrders(mQianquData, mHouquData, mTotalCount);
        if (mNumLotOrderBeans == null) {
            mNumLotOrderBeans = new ArrayList<>();
        }
        NumLotOrderBean numLotOrderBean = new NumLotOrderBean();
        numLotOrderBean.setCount((int) mTotalCount);
        numLotOrderBean.setLotId("dlt");
        if (mTotalCount > 1) {
            numLotOrderBean.setGuoguanType("f");
        } else {
            numLotOrderBean.setGuoguanType("d");
        }
        numLotOrderBean.setMultiple(calculateMultipleEdit.getText().toString());
        numLotOrderBean.setIssueNo(issueNo);
        numLotOrderBean.setOrderBean(bean);
        numLotOrderBean.setStakeAdd(stakeAdd);
        mNumLotOrderBeans.clear();
        mNumLotOrderBeans.add(numLotOrderBean);
    }

    private void addToOrders(ArrayList<LottoSelectData> mQianquData, ArrayList<LottoSelectData> mHouquData, long count) {

        bean.setCount(count);
        bean.setSelectdata("redData", mQianquData);
        bean.setSelectdata("blueData", mHouquData);
        if (count <= 1) {
            bean.setOrderType("单式投注");
        } else {
            bean.setOrderType("复式投注");
        }
    }

    @Override
    public void clearData() {
        initData();
        sendMsg();
    }

    //延迟消息更新开奖数据  fragment 还未创建
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void updatestickyUi(CalculateEventBus messageEvent) {
        issueBonusBean = messageEvent.getIssueBonusBean();
        if (issueBonusBean != null) {
            for (int i = 0; i < issueBonusBean.getData().getList().size(); i++) {
                if (issueBonusBean.getData().getList().get(i).getLotId().equals("dlt")) {
                    issueNo = issueBonusBean.getData().getList().get(i).getIssueNo();
                    calculateIssueTv.setText("第"+issueBonusBean.getData().getList().get(i).getIssueNo() + "期");
                    long time = 1000 * (long) issueBonusBean.getData().getList().get(i).getBonusTime();
                    String s = DateUtils.formatDate(time);
                    stoptimeTv.setText(s);
                    String arr[] = issueBonusBean.getData().getList().get(i).getBonusCode().split("#");
                    String arr2[] = arr[0].split(",");
                    String arr3[] = arr[1].split(",");
                    dltBall1Tv.setText(arr2[0]);
                    dltBall2Tv.setText(arr2[1]);
                    dltBall3Tv.setText(arr2[2]);
                    dltBall4Tv.setText(arr2[3]);
                    dltBall5Tv.setText(arr2[4]);
                    dltBall6Tv.setText(arr3[0]);
                    dltBall7Tv.setText(arr3[1]);
                }
            }
        }
    }

    //选择期号后,重新获取数据,更新到ui  fragment已经创建
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateUi(CalculateEventBus messageEvent) {
        issueBonusNumberDetailBean = messageEvent.getIssueBonusNumberDetailBean();
        if (issueBonusNumberDetailBean != null) {
            if (issueBonusNumberDetailBean.getData().getLotId().equals("dlt")) {
                issueNo = issueBonusNumberDetailBean.getData().getIssueNo();
                calculateIssueTv.setText("第"+issueBonusNumberDetailBean.getData().getIssueNo() + "期");
                stoptimeTv.setText(issueBonusNumberDetailBean.getData().getBonusDate());
                String arr[] = issueBonusNumberDetailBean.getData().getBonusCode().split("#");
                String arr2[] = arr[0].split(",");
                String arr3[] = arr[1].split(",");
                dltBall1Tv.setText(arr2[0]);
                dltBall2Tv.setText(arr2[1]);
                dltBall3Tv.setText(arr2[2]);
                dltBall4Tv.setText(arr2[3]);
                dltBall5Tv.setText(arr2[4]);
                dltBall6Tv.setText(arr3[0]);
                dltBall7Tv.setText(arr3[1]);
            }
        }
    }

    @Override
    public void updateBounds(BigInteger bounts) {
        String value=calculateMultipleEdit.getText().toString();
        if (stakeAdd.equals("1")) {
            mTotalCount = Long.valueOf(bounts.toString());
            calculateCountTv.setText(bounts.toString() + "注");
            calculateMoneyTv.setText(bounts.multiply(new BigInteger("3")).multiply(new BigInteger(value)) + "元");
            String totalMoneyStr = bounts.multiply(new BigInteger("3")).multiply(new BigInteger(value)) + "元";
            String totalMoney = bounts.multiply(new BigInteger("3").multiply(new BigInteger(value))).toString();
            String totalCountStr = bounts.toString() + "注";
            String totalCount = bounts.toString();
            TextUtils.setStrColor(calculateMoneyTv, totalMoneyStr, totalMoney, getResources().getColor(R.color.select_red));
            TextUtils.setStrColor(calculateCountTv, totalCountStr, totalCount, getResources().getColor(R.color.select_red));

        } else {
            mTotalCount = Long.valueOf(bounts.toString());
            calculateCountTv.setText(bounts.toString() + "注");
            calculateMoneyTv.setText(bounts.multiply(new BigInteger("2")).multiply(new BigInteger(value))+ "元");
            String totalMoneyStr = bounts.multiply(new BigInteger("2")).multiply(new BigInteger(value)) + "元";
            String totalMoney = bounts.multiply(new BigInteger("2")).multiply(new BigInteger(value)).toString();
            String totalCountStr = bounts.toString() + "注";
            String totalCount = bounts.toString();
            TextUtils.setStrColor(calculateMoneyTv, totalMoneyStr, totalMoney, getResources().getColor(R.color.select_red));
            TextUtils.setStrColor(calculateCountTv, totalCountStr, totalCount, getResources().getColor(R.color.select_red));

        }
       isCanPay = true;
    }
    @Override
    protected String getTitle() {
        return "奖金计算大乐透";
    }
}
