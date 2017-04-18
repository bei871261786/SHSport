package shlottery.gov.cn.lotterycenter.ui.nublottery.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.Base.BaseFragment;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.NumLotOrderBean;
import shlottery.gov.cn.lotterycenter.bean.NumLotteryBean;
import shlottery.gov.cn.lotterycenter.callback.Pl3Evenbus;
import shlottery.gov.cn.lotterycenter.callback.Syx5EventBus;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.ui.nublottery.activity.NumLotteryDingdanActivity;
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.NumGridViewAdapter;
import shlottery.gov.cn.lotterycenter.ui.view.GridViewWithoutScroll;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingPager;
import shlottery.gov.cn.lotterycenter.utils.CalculateStage;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.RandomUtils;
import shlottery.gov.cn.lotterycenter.utils.ShakeUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;
import shlottery.gov.cn.lotterycenter.utils.VibratorUtil;

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
public class Syx5RenxFragment extends BaseFragment {

    @BindView(R.id.syx5_rx_leastnum_tv)
    TextView syx5RxLeastnumTv;
    @BindView(R.id.syx5_issuedetaile_tv)
    TextView syx5IssuedetaileTv;
    @BindView(R.id.pl3_shake_tv)
    TextView pl3ShakeTv;
    @BindView(R.id.shake_layout)
    RelativeLayout mShake;
    @BindView(R.id.z3_l1gridview)
    GridViewWithoutScroll z3L1gridview;
    @BindView(R.id.zu3_scorll)
    ScrollView zu3Scorll;
    @BindView(R.id.baselottery_deleteall_tv)
    TextView baselotteryDeleteallTv;
    @BindView(R.id.fragment_renxun_tip)
    TextView fragmentrenxuntip;
    @BindView(R.id.baselottery_total_tv)
    TextView baselotteryTotalTv;
    @BindView(R.id.baselottery_money_tv)
    TextView baselotteryMoneyTv;
    @BindView(R.id.baselottery_sum_bt)
    TextView baselotterySumBt;
    @BindView(R.id.baselottery_bottom_ll)
    LinearLayout baselotteryBottomLl;
    private List<String> mMissList = new ArrayList<>();//遗漏的集合
    private NumGridViewAdapter mPL3L1GridViewAdapter;
    private int count1;
    private ShakeUtils mShakeUtils = null;//摇一摇
    public int mType = 0;
    private List<Map<Integer, Boolean>> lotteryData = new ArrayList<>();//提交数据
    private List<NumLotOrderBean> mNumLotOrderBeans;
    private NumLotOrderBean numLotOrderBean;
    int total;
    private MyListener mListener;
    private NumLotteryBean numLotteryBean;
    private int selectPostion;//当前期所在位置

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

                case 1:
                    total = 0;
                    if (mType == Configs.SYX5_QY) {//前一直选
                        total = count1;
                    } else if (mType == Configs.SYX5_Q2ZUX) {//前二组选
                        if (count1 == 2) {//当为2时  下面公式分母为零,须判断
                            total = 1;
                        } else {
                            total = CalculateStage.getFactorial(count1).divide(CalculateStage.getFactorial(2)).divide(CalculateStage.getFactorial(count1 - 2)).intValue();
                        }
                    } else if (mType == Configs.SYX5_Q3ZUX) {//前三组选
                        if (count1 == 3) {
                            total = 1;
                        } else {
                            total = CalculateStage.getFactorial(count1).divide(CalculateStage.getFactorial(3)).divide(CalculateStage.getFactorial(count1 - 3)).intValue();
                        }
                    } else {
                        if (count1 > (mType + 2)) {
                            total = CalculateStage.getFactorial(count1).divide(CalculateStage.getFactorial(2 + mType)).divide(CalculateStage.getFactorial(count1 - 2 - mType)).intValue();
                        } else {
                            total = 1;
                        }
                    }
//                    int total = count1 * (count1 - 1);
//                    total = CalculateStage.getFactorial(count1).divide(CalculateStage.getFactorial(2+mType)).divide(CalculateStage.getFactorial(count1 - 2-mType)).intValue();
                    baselotteryTotalTv.setText("共" + total + "注" + "  " + total * 2 + "元");
                    baselotterySumBt.setEnabled(true);
                    String s1 = "共" + total + "注" + "  " + total * 2 + "元";
                    String s2 = total * 2 + "";
                    TextUtils.setStrColor(baselotteryTotalTv, s1, s2, getResources().getColor(R.color.select_red));
                    break;
                case 2:
                    baselotterySumBt.setEnabled(false);
                    baselotteryTotalTv.setText("请选择投注号码");
                    break;
            }

        }
    };

    @SuppressLint("ValidFragment")
    public Syx5RenxFragment(ShakeUtils mShakeUtils, int mType, List<NumLotOrderBean> mNumLotOrderBeans) {
        this.mShakeUtils = mShakeUtils;
        this.mType = mType;
        this.mNumLotOrderBeans = mNumLotOrderBeans;
    }

    @Override
    protected View createLoadedView() {
        View mView = UIUtils.inflate(R.layout.fragment_syx5_renxuan);
        ButterKnife.bind(this, mView);
        for (int i = 0; i < 11; i++) {
            mMissList.add("-");
        }
        mPL3L1GridViewAdapter = new NumGridViewAdapter(mMissList, getActivity(), 1);
        setAdapter(z3L1gridview, mPL3L1GridViewAdapter);
        baselotteryTotalTv.setText("请选择投注号码");
        baselotteryMoneyTv.setVisibility(View.GONE);
        initShakeUtils();
        mListener = new MyListener();
        baselotterySumBt.setEnabled(false);
        String renDetail[] = getResources().getStringArray(R.array.syx5ren_detail);
        String renColorText[] = getResources().getStringArray(R.array.syx5ren_detail_colorText);
        String renLeastNUm[] = getResources().getStringArray(R.array.syx5ren_leastnum);
        LogUtils.i("SyxwRenxFragment colortext:" + renColorText[mType]);
        TextUtils.setStrColor(syx5IssuedetaileTv, renDetail[mType], renColorText[mType], BaseApplication.getApplication().getResources().getColor(R.color.select_red));
        syx5RxLeastnumTv.setText(renLeastNUm[mType]);
        if (mType == 7) {
            fragmentrenxuntip.setText("万位");
        }
        baselotteryDeleteallTv.setOnClickListener(mListener);
        baselotterySumBt.setOnClickListener(mListener);
        mShake.setOnClickListener(mListener);
        EventBus.getDefault().register(this);
        return mView;
    }

    private void setAdapter(GridViewWithoutScroll g, final NumGridViewAdapter p) {
        g.setAdapter(p);
        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mType == Configs.SYX5_RX8) {
                    int checked = 0;
                    for (int i = 0; i < 11; i++) {
                        if (p.getSelectionMap().get(i)) {
                            checked++;
                        }
                    }
                    if (checked == 8) {
                        if (p.getSelectionMap().get(position)) {
                            p.setSelectMap(position);
                            p.notifyDataSetChanged();
                            meath(p);
                            return;
                        } else {
                            UIUtils.showToastSafe("任选八最多只能选8个号码");
                            return;
                        }
                    }
                }
                p.setSelectMap(position);
                p.notifyDataSetChanged();
                meath(p);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(Pl3Evenbus messageEvent) {
        mNumLotOrderBeans = messageEvent.getmNumLotOrderBeans();
        Logger.e(mNumLotOrderBeans.size() + "十一选五任选mNumLotOrderBeans的长度");
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onShowMessageEvent(Syx5EventBus messageEvent) {

        numLotteryBean = messageEvent.getNumLotteryBean();
        selectPostion = messageEvent.getSelectPosition();
        String[] omit = numLotteryBean.getData().getOmit().split("#");
        String[] omit1 = omit[0].split(",");//任选
        String[] omit2 = omit[1].split(",");//直一
        String[] omit3 = omit[2].split(",");//直二
        String[] omit4 = omit[3].split(",");//直三
        mMissList.clear();
        if (mType == Configs.SYX5_QY) {
            for (int i = 0; i < omit2.length; i++) {

                mMissList.add(omit2[i]);
            }
        } else if (mType == Configs.SYX5_Q2ZUX) {
            for (int i = 0; i < omit2.length; i++) {
//                int m = Integer.parseInt((omit1[i]));
                int o = Integer.parseInt((omit2[i]));
                int p = Integer.parseInt((omit3[i]));
//                int j = (m > o ? o : m);
                int j = p > o ? o : p;
                mMissList.add(j + "");
            }
        } else if (mType == Configs.SYX5_Q3ZUX) {
            for (int i = 0; i < omit2.length; i++) {
                int m = Integer.parseInt((omit2[i]));
                int o = Integer.parseInt((omit3[i]));
                int p = Integer.parseInt((omit4[i]));
                int j = (m > o ? o : m);
                j = p > j ? j : p;
                mMissList.add(j + "");
            }
        } else {
            for (int i = 0; i < omit1.length; i++) {
                mMissList.add(omit1[i]);
            }
        }
        mPL3L1GridViewAdapter.notifyDataSetChanged();
        Logger.e(mMissList.size() + "任选mMissList的长度");
    }

    private void meath(NumGridViewAdapter p) {
        if (p.equals(mPL3L1GridViewAdapter)) {
            count1 = 0;
            for (int i = 0; i < mMissList.size(); i++) {
                if (p.getSelectionMap().get(i)) {
                    count1++;
                }
            }
        }

        if (mType == Configs.SYX5_QY) {//前一直选
            if (count1 >= 1) {
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessage(message);
            } else {
                Message message = new Message();
                message.what = 2;
                mHandler.sendMessage(message);
            }
        } else if (mType == Configs.SYX5_Q2ZUX) {//前二组选
            if (count1 >= 2) {
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessage(message);
            } else {
                Message message = new Message();
                message.what = 2;
                mHandler.sendMessage(message);
            }
        } else if (mType == Configs.SYX5_Q3ZUX) {//前三组选
            if (count1 >= 3) {
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessage(message);
            } else {
                Message message = new Message();
                message.what = 2;
                mHandler.sendMessage(message);
            }
        } else {
            if (count1 >= mType + 2) {
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessage(message);
            } else {
                Message message = new Message();
                message.what = 2;
                mHandler.sendMessage(message);
            }
        }

    }

    //监听摇一摇,并机选数据

    private void initShakeUtils() {
        mShakeUtils.setOnShakeListener(new ShakeUtils.OnShakeListener() {
            @Override
            public void onShake() {
                VibratorUtil.Vibrate(getActivity(), 300);
                shake();
            }
        });
    }

    protected void clearData() {
        for (int i = 0; i < mMissList.size(); i++) {
            mPL3L1GridViewAdapter.getSelectionMap().put(i, false);
        }
        mPL3L1GridViewAdapter.notifyDataSetChanged();
        meath(mPL3L1GridViewAdapter);
    }

    //机选一注并刷新页面
    protected void shake() {
        VibratorUtil.Vibrate(getActivity(), 300);
        clearData();
        int[] mNum;
        switch (mType) {
            case Configs.SYX5_RX2:
                mNum = RandomUtils.randomCommon(1, 11, 2);
                for (int m = 0; m < mNum.length; m++) {
                    mPL3L1GridViewAdapter.getSelectionMap().put(mNum[m] - 1, true);
                    Logger.e(mNum[m] + "_____=任选二随机 11选5");
                }
                mPL3L1GridViewAdapter.notifyDataSetChanged();
                meath(mPL3L1GridViewAdapter);
                break;
            case Configs.SYX5_RX3:
                mNum = RandomUtils.randomCommon(1, 11, 3);
                for (int m = 0; m < mNum.length; m++) {
                    mPL3L1GridViewAdapter.getSelectionMap().put(mNum[m] - 1, true);
                }
                mPL3L1GridViewAdapter.notifyDataSetChanged();
                break;
            case Configs.SYX5_RX4:
                mNum = RandomUtils.randomCommon(1, 11, 4);
                for (int m = 0; m < mNum.length; m++) {
                    mPL3L1GridViewAdapter.getSelectionMap().put(mNum[m] - 1, true);
                }
                mPL3L1GridViewAdapter.notifyDataSetChanged();
                break;
            case Configs.SYX5_RX5:
                mNum = RandomUtils.randomCommon(1, 11, 5);
                for (int m = 0; m < mNum.length; m++) {
                    mPL3L1GridViewAdapter.getSelectionMap().put(mNum[m] - 1, true);
                }
                mPL3L1GridViewAdapter.notifyDataSetChanged();
                break;
            case Configs.SYX5_RX6:
                mNum = RandomUtils.randomCommon(1, 11, 6);
                for (int m = 0; m < mNum.length; m++) {
                    mPL3L1GridViewAdapter.getSelectionMap().put(mNum[m] - 1, true);
                }
                mPL3L1GridViewAdapter.notifyDataSetChanged();
                break;
            case Configs.SYX5_RX7:
                mNum = RandomUtils.randomCommon(1, 11, 7);
                for (int m = 0; m < mNum.length; m++) {
                    mPL3L1GridViewAdapter.getSelectionMap().put(mNum[m] - 1, true);
                }
                mPL3L1GridViewAdapter.notifyDataSetChanged();
                break;
            case Configs.SYX5_RX8:
                mNum = RandomUtils.randomCommon(1, 11, 8);
                for (int m = 0; m < mNum.length; m++) {
                    mPL3L1GridViewAdapter.getSelectionMap().put(mNum[m] - 1, true);
                }
                mPL3L1GridViewAdapter.notifyDataSetChanged();
                break;
            case Configs.SYX5_QY:
                mNum = RandomUtils.randomCommon(1, 11, 1);
                for (int m = 0; m < mNum.length; m++) {
                    mPL3L1GridViewAdapter.getSelectionMap().put(mNum[m] - 1, true);
                }
                mPL3L1GridViewAdapter.notifyDataSetChanged();
                break;
            case Configs.SYX5_Q2ZUX:
                mNum = RandomUtils.randomCommon(1, 11, 2);
                for (int m = 0; m < mNum.length; m++) {
                    mPL3L1GridViewAdapter.getSelectionMap().put(mNum[m] - 1, true);
                }
                mPL3L1GridViewAdapter.notifyDataSetChanged();
                break;
            case Configs.SYX5_Q3ZUX:
                mNum = RandomUtils.randomCommon(1, 11, 3);
                for (int m = 0; m < mNum.length; m++) {
                    mPL3L1GridViewAdapter.getSelectionMap().put(mNum[m] - 1, true);
                }
                mPL3L1GridViewAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
        meath(mPL3L1GridViewAdapter);
    }


    @Override
    protected LoadingPager.LoadResult Load() {
        return check(0);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mShakeUtils) {
            mShakeUtils.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != mShakeUtils) {
            mShakeUtils.onPause();
        }
    }

    private void initLotteryData() {
        lotteryData.clear();
        lotteryData.add(mPL3L1GridViewAdapter.getSelectionMap());
        numLotOrderBean = new NumLotOrderBean();
        numLotOrderBean.setLotteryData(lotteryData);
        numLotOrderBean.setIsdan(false);
        numLotOrderBean.setCount(total);
        numLotOrderBean.setLotId("sh115");
        numLotOrderBean.setmType(mType);

        switch (mType) {
            case Configs.SYX5_RX2:
                if (total < 2) {
                    numLotOrderBean.setGuoguanType("d2");
                } else {
                    numLotOrderBean.setGuoguanType("f2");
                }
                break;
            case Configs.SYX5_RX3:
                if (total < 2) {
                    numLotOrderBean.setGuoguanType("d3");
                } else {
                    numLotOrderBean.setGuoguanType("f3");
                }
                break;
            case Configs.SYX5_RX4:
                if (total < 2) {
                    numLotOrderBean.setGuoguanType("d4");
                } else {
                    numLotOrderBean.setGuoguanType("f4");
                }
                break;
            case Configs.SYX5_RX5:
                if (total < 2) {
                    numLotOrderBean.setGuoguanType("d5");
                } else {
                    numLotOrderBean.setGuoguanType("f5");
                }
                break;
            case Configs.SYX5_RX6:
                if (total < 2) {
                    numLotOrderBean.setGuoguanType("d6");
                } else {
                    numLotOrderBean.setGuoguanType("f6");
                }
                break;
            case Configs.SYX5_RX7:
                if (total < 2) {
                    numLotOrderBean.setGuoguanType("d7");
                } else {
                    numLotOrderBean.setGuoguanType("f7");
                }
                break;
            case Configs.SYX5_RX8:
                if (total < 2) {
                    numLotOrderBean.setGuoguanType("d8");
                } else {
                    numLotOrderBean.setGuoguanType("f8");
                }
                break;
            case Configs.SYX5_QY:
                if (total < 2) {
                    numLotOrderBean.setGuoguanType("d1");
                } else {
                    numLotOrderBean.setGuoguanType("f1");
                }
                break;
            case Configs.SYX5_Q2ZUX:
                if (total < 2) {
                    numLotOrderBean.setGuoguanType("d2z");
                } else {
                    numLotOrderBean.setGuoguanType("f2z");
                }
                break;
            case Configs.SYX5_Q3ZUX:
                if (total < 2) {
                    numLotOrderBean.setGuoguanType("d3z");
                } else {
                    numLotOrderBean.setGuoguanType("f3z");
                }
                break;
            default:
                break;
        }

        if (selectPostion >= 0) {
            if (numLotteryBean != null) {
                numLotOrderBean.setIssueNo(numLotteryBean.getData().getIssueList().get(selectPostion).getIssueNo() == null ? "" : numLotteryBean.getData().getIssueList().get(selectPostion).getIssueNo());
                numLotOrderBean.setStoptime(numLotteryBean.getData().getIssueList().get(selectPostion).getStopTime());
            }
        }
//        mNumLotOrderBeans.clear();
        mNumLotOrderBeans.add(0, numLotOrderBean);
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.baselottery_deleteall_tv:
                    clearData();
                    break;
                case R.id.baselottery_sum_bt:
                    if (numLotteryBean == null) {
                        UIUtils.showToastSafe("未能获取到当前彩种信息,不能投注");
                        return;
                    }
                    initLotteryData();
                    Logger.e("提交被点击了");
                    Intent mIntent = new Intent(getActivity(), NumLotteryDingdanActivity.class);
                    mIntent.putExtra("Pl5Activity", (Serializable) mNumLotOrderBeans);
                    startActivityForResult(mIntent, Configs.NUMLOTT_REQUESTCODE);
                    break;
                case R.id.shake_layout:
                    shake();
                    break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Configs.NUMLOTT_REQUESTCODE) {
            if (resultCode == Configs.NUMLOTT_RESULTCODE) {
                clearData();
                Bundle bundle = data.getExtras();
                mNumLotOrderBeans = (List<NumLotOrderBean>) bundle.getSerializable(Configs.SFCDINGDAN_RESULTKEY);
                Pl3Evenbus pl3Evenbus = new Pl3Evenbus();
                pl3Evenbus.setmNumLotOrderBeans(mNumLotOrderBeans);
                EventBus.getDefault().post(pl3Evenbus);
            } else {
                getActivity().finish();
            }
        }
    }
    @Override
    protected String getTitle() {
        return "";
    }
}
