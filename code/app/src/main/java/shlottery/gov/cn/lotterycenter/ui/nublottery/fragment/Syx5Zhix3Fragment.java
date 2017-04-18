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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
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
public class Syx5Zhix3Fragment extends BaseNumberFragment {
    @BindView(R.id.syx5_rx_leastnum_tv)
    TextView syx5RxLeastnumTv;
    @BindView(R.id.syx5_issuedetaile_tv)
    TextView syx5IssuedetaileTv;
    @BindView(R.id.syx5_issuedetaile2_tv)
    TextView syx5issuedetaile2tv;
    @BindView(R.id.pl3_shake_tv)
    TextView pl3ShakeTv;
    @BindView(R.id.shake_layout)
    RelativeLayout mShake;
    @BindView(R.id.syx5z2_l1gridview)
    GridViewWithoutScroll syx5z2L1gridview;
    @BindView(R.id.syx5z2_l2gridview)
    GridViewWithoutScroll syx5z2L2gridview;
    @BindView(R.id.syx5z2_l3gridview)
    GridViewWithoutScroll syx5z2L3gridview;
    @BindView(R.id.zu3_scorll)
    ScrollView zu3Scorll;
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
    private List<String> mMissList = new ArrayList<>();//遗漏的集合
    private List<String> mMissList1 = new ArrayList<>();//遗漏的集合
    private List<String> mMissList2 = new ArrayList<>();//遗漏的集合
    private NumGridViewAdapter mSyx5L1GridViewAdapter;
    private NumGridViewAdapter mSyx5L2GridViewAdapter;
    private NumGridViewAdapter mSyx5L3GridViewAdapter;

    private int count1;
    private int count2;
    private int count3;

    private ShakeUtils mShakeUtils = null;//摇一摇
    public int mType;


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
//                    int total;

//                    total = count1 * count2 * count3;
                    total = CalculateStage.calculateZhix3Stage(mSyx5L1GridViewAdapter.getSelectionMap(), mSyx5L2GridViewAdapter.getSelectionMap(), mSyx5L3GridViewAdapter.getSelectionMap());
                    if (total == 0) {
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
    }

    @SuppressLint("ValidFragment")
    public Syx5Zhix3Fragment(ShakeUtils mShakeUtils, int mType, List<NumLotOrderBean> mNumLotOrderBeans) {
        this.mShakeUtils = mShakeUtils;
        this.mNumLotOrderBeans = mNumLotOrderBeans;
        this.mType = mType;//上方item的position,根据位置加载fragment
    }

    @Override
    protected View createLoadedView() {
        View mView = UIUtils.inflate(R.layout.fragment_syx5_zhix3);
        ButterKnife.bind(this, mView);

        for (int i = 0; i < 11; i++) {
            mMissList.add("-");
            mMissList1.add("-");
            mMissList2.add("-");
        }
        mSyx5L1GridViewAdapter = new NumGridViewAdapter(mMissList, getActivity(), 1);
        mSyx5L2GridViewAdapter = new NumGridViewAdapter(mMissList1, getActivity(), 1);
        mSyx5L3GridViewAdapter = new NumGridViewAdapter(mMissList2, getActivity(), 1);
        setAdapter(syx5z2L1gridview, mSyx5L1GridViewAdapter, mSyx5L2GridViewAdapter, mSyx5L3GridViewAdapter);
        setAdapter(syx5z2L2gridview, mSyx5L2GridViewAdapter, mSyx5L1GridViewAdapter, mSyx5L3GridViewAdapter);
        setAdapter(syx5z2L3gridview, mSyx5L3GridViewAdapter, mSyx5L1GridViewAdapter, mSyx5L2GridViewAdapter);
        baselotteryTotalTv.setText("请选择投注号码");
        TextUtils.setStrColor(syx5IssuedetaileTv, "按位猜对前三位开奖号即中1170元", 1170 + "", BaseApplication.getApplication().getResources().getColor(R.color.select_red));
        syx5issuedetaile2tv.setVisibility(View.VISIBLE);
        syx5issuedetaile2tv.setText("异位同号不计为一注");
        baselotteryMoneyTv.setVisibility(View.GONE);
        initShakeUtils();
        mListener = new MyListener();
        baselotterySumBt.setEnabled(false);
        baselotteryDeleteallTv.setOnClickListener(mListener);
        baselotterySumBt.setOnClickListener(mListener);
        mShake.setOnClickListener(mListener);
        EventBus.getDefault().register(this);
        return mView;
    }

    private void setAdapter(GridViewWithoutScroll g, final NumGridViewAdapter p, final NumGridViewAdapter p1, final NumGridViewAdapter p2) {
        g.setAdapter(p);
        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if ((p1.getSelectionMap().get(position) || p2.getSelectionMap().get(position))) {
                    UIUtils.showToastSafe("异位同号不计一注");
                    return;
                }
                p.setSelectMap(position);
                p.notifyDataSetChanged();
                meath(p);

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
        } else {
            count3 = 0;
            for (int i = 0; i < mMissList.size(); i++) {
                if (p.getSelectionMap().get(i)) {
                    count3++;
                }
            }
        }

        if (count1 >= 1 && count2 >= 1 && count3 >= 1) {
            Message message = new Message();
            message.what = 1;
            mHandler.sendMessage(message);
        } else {
            Message message = new Message();
            message.what = 2;
            mHandler.sendMessage(message);
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

    @Override
    protected void clearData() {
        for (int i = 0; i < mMissList.size(); i++) {
            mSyx5L1GridViewAdapter.getSelectionMap().put(i, false);
            mSyx5L2GridViewAdapter.getSelectionMap().put(i, false);
            mSyx5L3GridViewAdapter.getSelectionMap().put(i, false);
        }
        mSyx5L1GridViewAdapter.notifyDataSetChanged();
        meath(mSyx5L1GridViewAdapter);
        mSyx5L2GridViewAdapter.notifyDataSetChanged();
        meath(mSyx5L2GridViewAdapter);
        mSyx5L3GridViewAdapter.notifyDataSetChanged();
        meath(mSyx5L3GridViewAdapter);
    }

    @Override
    protected boolean checkDingdan() {
        return false;
    }

    //机选一注并刷新页面
    protected void shake() {
        VibratorUtil.Vibrate(getActivity(), 300);
        clearData();
        int[] mNum = RandomUtils.randomCommon(1, 11, 3);
        mSyx5L1GridViewAdapter.getSelectionMap().put(mNum[0] - 1, true);
        mSyx5L2GridViewAdapter.getSelectionMap().put(mNum[1] - 1, true);
        mSyx5L3GridViewAdapter.getSelectionMap().put(mNum[2] - 1, true);
        mSyx5L1GridViewAdapter.notifyDataSetChanged();
        meath(mSyx5L1GridViewAdapter);
        mSyx5L2GridViewAdapter.notifyDataSetChanged();
        meath(mSyx5L2GridViewAdapter);
        mSyx5L3GridViewAdapter.notifyDataSetChanged();
        meath(mSyx5L3GridViewAdapter);
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
        mMissList1.clear();
        mMissList2.clear();
        for (int i = 0; i < omit2.length; i++) {
            mMissList.add(omit2[i]);
            mMissList1.add(omit3[i]);
            mMissList2.add(omit4[i]);
        }

        mSyx5L1GridViewAdapter.notifyDataSetChanged();
        mSyx5L2GridViewAdapter.notifyDataSetChanged();
        mSyx5L3GridViewAdapter.notifyDataSetChanged();
    }

    /**
     * 传递数据时添加数据
     */
    private void initLotteryData() {
        lotteryData.clear();
        lotteryData.add(mSyx5L1GridViewAdapter.getSelectionMap());
        lotteryData.add(mSyx5L2GridViewAdapter.getSelectionMap());
        lotteryData.add(mSyx5L3GridViewAdapter.getSelectionMap());
        numLotOrderBean = new NumLotOrderBean();
        numLotOrderBean.setLotteryData(lotteryData);
        numLotOrderBean.setCount(total);
        numLotOrderBean.setLotId("sh115");
        numLotOrderBean.setmType(Configs.SYX5_Q3ZHIX);
        numLotOrderBean.setIsdan(false);
        if (total < 2) {
            numLotOrderBean.setGuoguanType("d3q");
        } else {
            numLotOrderBean.setGuoguanType("f3q");
        }
        if (selectPostion >= 0) {
            numLotOrderBean.setIssueNo(numLotteryBean.getData().getIssueList().get(selectPostion).getIssueNo());
            numLotOrderBean.setStoptime(numLotteryBean.getData().getIssueList().get(selectPostion).getStopTime());
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
                    initLotteryData();
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
