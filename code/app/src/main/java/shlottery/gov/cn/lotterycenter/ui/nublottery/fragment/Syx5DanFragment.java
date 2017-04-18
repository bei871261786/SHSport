package shlottery.gov.cn.lotterycenter.ui.nublottery.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
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
import shlottery.gov.cn.lotterycenter.utils.ShakeUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-31-0031 16:38
 * 描    述：
 * 修订历史：
 * ================================================
 */

@SuppressLint("ValidFragment")
public class Syx5DanFragment extends BaseFragment {


    @BindView(R.id.baselottery_deleteall_tv)
    TextView baselotteryDeleteallTv;
    @BindView(R.id.baselottery_total_tv)
    TextView baselotteryTotalTv;
    @BindView(R.id.baselottery_money_tv)
    TextView baselotteryMoneyTv;
    @BindView(R.id.baselottery_sum_bt)
    TextView baselotterySumBt;
    @BindView(R.id.baselottery_bottom_ll)
    LinearLayout baselotteryBottomLl;
    //    @BindView(R.id.syx5_rx_leastnum_tv)
//    TextView syx5RxLeastnumTv;//至少选几个号码,胆里面不需要
    @BindView(R.id.syx5_issuedetaile_tv)
    TextView syx5IssuedetaileTv;
    //    @BindView(R.id.pl3_shake_tv)
//    TextView pl3ShakeTv;
//    @BindView(R.id.shake_layout)
//    RelativeLayout mShake;
    @BindView(R.id.syx5dan_l1gridview)
    GridViewWithoutScroll syx5danL1gridview;
    @BindView(R.id.syx5dan_l2gridview)
    GridViewWithoutScroll syx5danL2gridview;
    @BindView(R.id.syx5dan_scorll)
    ScrollView syx5danScorll;
    @BindView(R.id.tuomadetail_tv)
    TextView tuomaDetailTv;
    @BindView(R.id.dandetail_tv)
    TextView danDetailTv;
    private List<String> mMissList = new ArrayList<>();//遗漏的集合
    private NumGridViewAdapter mSyx5L1GridViewAdapter;
    private NumGridViewAdapter mSyx5L2GridViewAdapter;

    private int count1;
    private int count2;
    private int danSelectedNum;
    private int tuoSelectedNum;
    private int danMost;//胆做多可选个数

    private ShakeUtils mShakeUtils = null;//摇一摇
    public int mType;


    private List<Map<Integer, Boolean>> lotteryData = new ArrayList<>();//提交数据
    private List<NumLotOrderBean> mNumLotOrderBeans;
    private NumLotOrderBean numLotOrderBean;
    int total;
    private MyListener mListener;

    private NumLotteryBean numLotteryBean;//请求获取到的数据

    private int selectPostion;//当前期所在位置

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    total = 0;
//                    total = CalculateStage.calculateZhix2Stage(mSyx5L1GridViewAdapter.getSelectionMap(), mSyx5L2GridViewAdapter.getSelectionMap());
                    Logger.e(mType + "_____mType");
                    if (mType == 7) {//前二组选
                        total = count2 * count1;
                    } else if (mType == 8) {//千三组选 其实下面公式也适用,因为情况较少,单独列出来计算
                        if (count1 == 1) {
                            if (count2 == 1) {
                                total = 0;
                            } else {
                                total = count2 * (count2 - 1) / 2;
                            }
                        } else {//其实count1只能等于2  0的情况不会发送消息
                            total = count2;
                        }
                    } else {
                        if ((count2 - mType - 2 + count1) == 0 || (mType + 2 - count1) == 0) {
                            total = 1;
                        } else {
                            total = CalculateStage.getFactorial(count2).divide(CalculateStage.getFactorial(count2 - mType - 2 + count1)).divide(CalculateStage.getFactorial(mType + 2 - count1)).intValue();
                        }
                    }
                    if (total == 0) {
                        baselotterySumBt.setEnabled(false);
                        baselotteryTotalTv.setText("请选择投注号码");
                        break;
                    }
                    baselotterySumBt.setEnabled(true);
                    baselotteryTotalTv.setText("共" + total + "注" + "  " + total * 2 + "元");
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(Pl3Evenbus messageEvent) {
        mNumLotOrderBeans = messageEvent.getmNumLotOrderBeans();
        Logger.e(mNumLotOrderBeans.size()+"十一选五胆拖 mNumLotOrderBeans 的长度");
    }

    @SuppressLint("ValidFragment")
    public Syx5DanFragment(int mType, ShakeUtils mShakeUtils, List<NumLotOrderBean> mNumLotOrderBeans) {
        this.mType = mType;
        this.mShakeUtils = mShakeUtils;
        this.mNumLotOrderBeans = mNumLotOrderBeans;
    }

    @Override
    protected View createLoadedView() {
        View mView = UIUtils.inflate(R.layout.fragment_syx5dan);
        ButterKnife.bind(this, mView);
        for (int i = 0; i < 11; i++) {
            mMissList.add("-");
        }
        mSyx5L1GridViewAdapter = new NumGridViewAdapter(mMissList, getActivity(), 1);
        mSyx5L2GridViewAdapter = new NumGridViewAdapter(mMissList, getActivity(), 1);
        setAdapter(syx5danL1gridview, mSyx5L1GridViewAdapter, mSyx5L2GridViewAdapter);
        setAdapter(syx5danL2gridview, mSyx5L2GridViewAdapter, mSyx5L1GridViewAdapter);
        baselotteryTotalTv.setText("请选择投注号码");
        baselotteryMoneyTv.setVisibility(View.GONE);
//        syx5RxLeastnumTv.setVisibility(View.GONE);
//        mShake.setVisibility(View.GONE);//胆拖不需要摇一摇
        danMost = cacluceDan(mType);//胆的最多个数
        danDetailTv.setText("我认为必出的号码，至少选1个，最多" + danMost + "个");
        String danDetail[] = getResources().getStringArray(R.array.syx5dan_detail);
        String danColorText[] = getResources().getStringArray(R.array.syx5dan_detail_colorText);
        TextUtils.setStrColor(syx5IssuedetaileTv, danDetail[mType], danColorText[mType], BaseApplication.getApplication().getResources().getColor(R.color.select_red));
        mListener = new MyListener();
        baselotterySumBt.setEnabled(false);
        baselotteryDeleteallTv.setOnClickListener(mListener);
        baselotterySumBt.setOnClickListener(mListener);
//        mShake.setOnClickListener(mListener);
        EventBus.getDefault().register(this);
        return mView;
    }

    //计算胆的最多个数
    private int cacluceDan(int mType) {
        int dan;
        if (mType == 7) {
            dan = 1;
        } else if (mType == 8) {
            dan = 2;
        } else {
            dan = mType + 1;
        }
        return dan;
    }

    /**
     * 此处主要的思路为:当适配器1选中某个数时,适配器2相同数字如果选中需要改变其状态.
     * 适配器2相同.适配器1只能选mType+1个数,任选n,n=mType+1.
     * 注数计算公式其实就是排列组合,
     * 组合方式为count2中选出 n-(count1)个数.
     *
     * @param g
     * @param p
     * @param p1
     */
    private void setAdapter(GridViewWithoutScroll g, final NumGridViewAdapter p, final NumGridViewAdapter p1) {
        g.setAdapter(p);
        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (p.equals(mSyx5L1GridViewAdapter)) {
                    danSelectedNum = 0;
                    for (int i = 0; i < 11; i++) {
                        if (p.getSelectionMap().get(i)) {
                            danSelectedNum++;
                            if (position == i) {
                                p.setSelectMap(position);
                                p.notifyDataSetChanged();
                                meath(p);
                                meath(p1);
                                return;
                            }
                        }
                    }
                    if (mType == 7) {
                        if (danSelectedNum >= 1) {//mType位position  从0开始,胆码比任选数值小一
                            UIUtils.showToastSafe("胆码只能选择" + 1 + "个");
                            return;
                        }
                    } else if (mType == 8) {
                        if (danSelectedNum >= 2) {//mType位position  从0开始,胆码比任选数值小一
                            UIUtils.showToastSafe("胆码只能选择" + 2 + "个");
                            return;
                        }
                    } else {
                        if (danSelectedNum >= mType + 1) {//mType位position  从0开始,胆码比任选数值小一
                            UIUtils.showToastSafe("胆码只能选择" + (mType + 1) + "个");
                            return;
                        }
                    }
                    if (p1.getSelectionMap().get(position)) {
                        p1.setSelectMap(position);
                        p1.notifyDataSetChanged();
                    }
                    p.setSelectMap(position);
                    p.notifyDataSetChanged();
                    meath(p);
                    meath(p1);
                } else {
                    tuoSelectedNum = 0;
                    for (int i = 0; i < 11; i++) {
                        if (p.getSelectionMap().get(i)) {
                            tuoSelectedNum++;
                            if (position == i) {
                                p.setSelectMap(position);
                                p.notifyDataSetChanged();
                                meath(p);
                                meath(p1);
                                return;
                            }
                        }
                    }
                    if (tuoSelectedNum >= 10) {//mType位position  从0开始,胆码比任选数值小一
                        UIUtils.showToastSafe("拖码只能选择" + 10 + "个");
                        return;
                    }
                    if (p1.getSelectionMap().get(position)) {//如果p1的position选中 改变其状态
                        p1.setSelectMap(position);
                        p1.notifyDataSetChanged();
                        meath(p);
                        meath(p1);
                    }
                    p.setSelectMap(position);
                    p.notifyDataSetChanged();
                    meath(p);
                    meath(p1);
                }

            }
        });
    }

    private void meath(NumGridViewAdapter p) {
        if (p.equals(mSyx5L1GridViewAdapter)) {
            count1 = 0;
            for (int i = 0; i < mMissList.size(); i++) {
                if (p.getSelectionMap().get(i)) {
                    count1++;
                }
            }
        } else if (p.equals(mSyx5L2GridViewAdapter)) {
            count2 = 0;
            for (int i = 0; i < mMissList.size(); i++) {
                if (p.getSelectionMap().get(i)) {
                    count2++;
                }
            }
        }
        if (mType == 7) {
            if (count1 >= 1 && count2 >= 1 && (count1 + count2) >= 2) {
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessage(message);
            } else {
                Message message = new Message();
                message.what = 2;
                mHandler.sendMessage(message);
            }
        } else if (mType == 8) {
            if (count1 >= 1 && count2 >= 1 && (count1 + count2) >= 3) {
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessage(message);
            } else {
                Message message = new Message();
                message.what = 2;
                mHandler.sendMessage(message);
            }
        } else {
            if (count1 >= 1 && count2 >= 1 && (count1 + count2) >= mType + 2) {
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

    protected void clearData() {
        for (int i = 0; i < mMissList.size(); i++) {
            mSyx5L1GridViewAdapter.getSelectionMap().put(i, false);
            mSyx5L2GridViewAdapter.getSelectionMap().put(i, false);
        }
        mSyx5L1GridViewAdapter.notifyDataSetChanged();
        meath(mSyx5L1GridViewAdapter);
        mSyx5L2GridViewAdapter.notifyDataSetChanged();
        meath(mSyx5L2GridViewAdapter);
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

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onShowMessageEvent(Syx5EventBus messageEvent) {
        Logger.e("收到消息+胆拖"+"--------彩种type"+mType);
        numLotteryBean = messageEvent.getNumLotteryBean();
        selectPostion = messageEvent.getSelectPosition();
        String[] omit = numLotteryBean.getData().getOmit().split("#");
        String[] omit1 = omit[0].split(",");//任选
        String[] omit2 = omit[1].split(",");//直一
        String[] omit3 = omit[2].split(",");//直二
        String[] omit4 = omit[3].split(",");//直三
        mMissList.clear();
        if (mType == Configs.SYX5_Q2ZUXDAN) {
            for (int i = 0; i < omit2.length; i++) {
//                int m = Integer.parseInt((omit1[i]));
                int o = Integer.parseInt((omit2[i]));
                int p = Integer.parseInt((omit3[i]));
//                int j = (m > o ? o : m);
                int j = p > o ? o : p;
                mMissList.add(j + "");
            }
        } else if (mType == Configs.SYX5_Q3ZUXDAN) {
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
        Logger.e(mMissList.size()+"mMissList的长度");
        mSyx5L1GridViewAdapter.notifyDataSetChanged();
        mSyx5L2GridViewAdapter.notifyDataSetChanged();
    }

    private void initLotteryData() {
        lotteryData.clear();
        lotteryData.add(mSyx5L1GridViewAdapter.getSelectionMap());
        lotteryData.add(mSyx5L2GridViewAdapter.getSelectionMap());
        numLotOrderBean = new NumLotOrderBean();
        numLotOrderBean.setLotteryData(lotteryData);
        numLotOrderBean.setCount(total);
        numLotOrderBean.setLotId("sh115");
        numLotOrderBean.setmType(mType);
        numLotOrderBean.setIsdan(true);
        switch (mType) {
            case Configs.SYX5_RXDAN2:
                numLotOrderBean.setGuoguanType("dt2");
                break;
            case Configs.SYX5_RXDAN3:
                numLotOrderBean.setGuoguanType("dt3");
                break;
            case Configs.SYX5_RXDAN4:
                numLotOrderBean.setGuoguanType("dt4");
                break;
            case Configs.SYX5_RXDAN5:
                numLotOrderBean.setGuoguanType("dt5");
                break;
            case Configs.SYX5_RXDAN6:
                numLotOrderBean.setGuoguanType("dt6");
                break;
            case Configs.SYX5_RXDAN7:
                numLotOrderBean.setGuoguanType("dt7");
                break;
            case Configs.SYX5_RXDAN8:
                numLotOrderBean.setGuoguanType("dt8");
                break;
            case Configs.SYX5_Q2ZUXDAN:
                numLotOrderBean.setGuoguanType("dt2z");
                break;
            case Configs.SYX5_Q3ZUXDAN:
                numLotOrderBean.setGuoguanType("dt3z");
                break;
            default:
                break;
        }

        if (selectPostion >= 0) {
            if (numLotteryBean != null) {
                numLotOrderBean.setIssueNo(numLotteryBean.getData().getIssueList().get(selectPostion).getIssueNo());
                numLotOrderBean.setStoptime(numLotteryBean.getData().getIssueList().get(selectPostion).getStopTime());
            }
        }
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
                    if (total == 1) {
                        switch (mType) {
                            case Configs.SYX5_RXDAN2:
                                UIUtils.showToastSafe("胆码加拖码必须大于2");
                                break;
                            case Configs.SYX5_RXDAN3:
                                UIUtils.showToastSafe("胆码加拖码必须大于3");
                                break;
                            case Configs.SYX5_RXDAN4:
                                UIUtils.showToastSafe("胆码加拖码必须大于4");
                                break;
                            case Configs.SYX5_RXDAN5:
                                UIUtils.showToastSafe("胆码加拖码必须大于5");
                                break;
                            case Configs.SYX5_RXDAN6:
                                UIUtils.showToastSafe("胆码加拖码必须大于6");
                                break;
                            case Configs.SYX5_RXDAN7:
                                UIUtils.showToastSafe("胆码加拖码必须大于7");
                                break;
                            case Configs.SYX5_RXDAN8:
//                                numLotOrderBean.setGuoguanType("dt8");
                                break;
                            case Configs.SYX5_Q2ZUXDAN:
                                UIUtils.showToastSafe("胆码加拖码必须大于2");
                                break;
                            case Configs.SYX5_Q3ZUXDAN:
                                UIUtils.showToastSafe("胆码加拖码必须大于3");
                                break;
                            default:
                                break;
                        }
                        break;
                    }
                    initLotteryData();
                    Logger.e("提交被点击了");
                    Intent mIntent = new Intent(getActivity(), NumLotteryDingdanActivity.class);
                    mIntent.putExtra("Pl5Activity", (Serializable) mNumLotOrderBeans);
                    startActivityForResult(mIntent, Configs.NUMLOTT_REQUESTCODE);
                    break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Configs.NUMLOTT_REQUESTCODE) {
            if (resultCode == Configs.NUMLOTT_RESULTCODE) {
                Bundle bundle = data.getExtras();
                mNumLotOrderBeans = (List<NumLotOrderBean>) bundle.getSerializable(Configs.SFCDINGDAN_RESULTKEY);
                Pl3Evenbus pl3Evenbus = new Pl3Evenbus();
                pl3Evenbus.setmNumLotOrderBeans(mNumLotOrderBeans);
                EventBus.getDefault().post(pl3Evenbus);
                clearData();
            } else {
                getActivity().finish();
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
    @Override
    protected String getTitle() {
        return "";
    }
}
