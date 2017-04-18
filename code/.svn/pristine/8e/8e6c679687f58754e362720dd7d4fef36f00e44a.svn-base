package shlottery.gov.cn.lotterycenter.ui.nublottery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.NumLotOrderBean;
import shlottery.gov.cn.lotterycenter.bean.NumLotteryBean;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.NumGridViewAdapter;
import shlottery.gov.cn.lotterycenter.ui.view.GridViewWithoutScroll;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.RandomUtils;
import shlottery.gov.cn.lotterycenter.utils.ShakeUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;
import shlottery.gov.cn.lotterycenter.utils.VibratorUtil;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-17-0017 13:18
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class QXCActivity extends BaseActivity {
    @BindView(R.id.titlebar_back_ll)
    LinearLayout titlebarBackLl;
    @BindView(R.id.titlebar_tv)
    TextView titlebarTv;
    @BindView(R.id.titlebar_indicator)
    ImageView titlebarIndicator;
    @BindView(R.id.titlebar_title)
    LinearLayout titlebarTitle;
    @BindView(R.id.filtrate_tv)
    ImageView filtrateTv;
    @BindView(R.id.base_titleBar)
    RelativeLayout baseTitleBar;
    @BindView(R.id.qxc_issue_tv)
    TextView qxcIssueTv;
    @BindView(R.id.qxc_stoptime_tv)
    TextView qxcStoptimeTv;
    @BindView(R.id.qxc_shake_tv)
    TextView qxcShakeTv;
    @BindView(R.id.qxc_shake_rl)
    RelativeLayout qxcShakeRl;
    @BindView(R.id.qxc_l1gridview)
    GridViewWithoutScroll qxcL1gridview;
    @BindView(R.id.qxc_l2gridview)
    GridViewWithoutScroll qxcL2gridview;
    @BindView(R.id.qxc_l3gridview)
    GridViewWithoutScroll qxcL3gridview;
    @BindView(R.id.qxc_l4gridview)
    GridViewWithoutScroll qxcL4gridview;
    @BindView(R.id.qxc_l6gridview)
    GridViewWithoutScroll qxcL6gridview;
    @BindView(R.id.qxc_l7gridview)
    GridViewWithoutScroll qxcL7gridview;
    @BindView(R.id.qxc_l5gridview)
    GridViewWithoutScroll qxcL5gridview;
    @BindView(R.id.qxc_ll)
    LinearLayout qxcLl;
    @BindView(R.id.qxc_scorll)
    ScrollView qxcScorll;
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
    @BindView(R.id.titlebar_indicatordown)
    ImageView titlebarIndicatordown;
    @BindView(R.id.main_calendar)
    ImageView mainCalendar;
    @BindView(R.id.qxc_yilou1_tv)
    TextView qxcYilou1Tv;
    @BindView(R.id.qxc_yilou2_tv)
    TextView qxcYilou2Tv;
    @BindView(R.id.qxc_yilou3_tv)
    TextView qxcYilou3Tv;
    @BindView(R.id.qxc_yilou4_tv)
    TextView qxcYilou4Tv;
    @BindView(R.id.qxc_yilou5_tv)
    TextView qxcYilou5Tv;
    @BindView(R.id.qxc_yilou6_tv)
    TextView qxcYilou6Tv;
    @BindView(R.id.qxc_yilou7_tv)
    TextView qxcYilou7Tv;
    private List<String> mMissList = new ArrayList<>();//遗漏的集合
    private NumGridViewAdapter mQXCL1GridViewAdapter;
    private NumGridViewAdapter mQXCL2GridViewAdapter;
    private NumGridViewAdapter mQXCL3GridViewAdapter;
    private NumGridViewAdapter mQXCL4GridViewAdapter;
    private NumGridViewAdapter mQXCL5GridViewAdapter;
    private NumGridViewAdapter mQXCL6GridViewAdapter;
    private NumGridViewAdapter mQXCL7GridViewAdapter;

    private boolean changeFlag;
    private int count1;
    private int count2;
    private int count3;
    private int count4;
    private int count5;
    private int count6;
    private int count7;

    private ShakeUtils mShakeUtils = null;//摇一摇
    private List<Map<Integer, Boolean>> lotteryData = new ArrayList<>();//提交数据
    private List<NumLotOrderBean> mNumLotOrderBeans = new ArrayList<>();
    private NumLotOrderBean numLotOrderBean;
    int total;//总注数
    private NumLotteryBean mNumLotteryBean;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            total = count1 * count2 * count3 * count4 * count5 * count6 * count7;
            switch (msg.what) {
                case 1:
                    baselotterySumBt.setEnabled(true);
                    baselotteryTotalTv.setText("共" + total + "注" + "  " + total * 2 + "元");
                    break;
                case 2:
                    baselotterySumBt.setEnabled(false);
                    baselotteryTotalTv.setText("请选择投注号码");
                    break;
            }

        }
    };


    @Override
    protected void initView() {
        setContentView(R.layout.activity_qxc);
        ButterKnife.bind(this);
        mQXCL1GridViewAdapter = new NumGridViewAdapter(mMissList, QXCActivity.this);
        mQXCL2GridViewAdapter = new NumGridViewAdapter(mMissList, QXCActivity.this);
        mQXCL3GridViewAdapter = new NumGridViewAdapter(mMissList, QXCActivity.this);
        mQXCL4GridViewAdapter = new NumGridViewAdapter(mMissList, QXCActivity.this);
        mQXCL5GridViewAdapter = new NumGridViewAdapter(mMissList, QXCActivity.this);
        mQXCL6GridViewAdapter = new NumGridViewAdapter(mMissList, QXCActivity.this);
        mQXCL7GridViewAdapter = new NumGridViewAdapter(mMissList, QXCActivity.this);
        setAdapter(qxcL1gridview, mQXCL1GridViewAdapter);
        setAdapter(qxcL2gridview, mQXCL2GridViewAdapter);
        setAdapter(qxcL3gridview, mQXCL3GridViewAdapter);
        setAdapter(qxcL4gridview, mQXCL4GridViewAdapter);
        setAdapter(qxcL5gridview, mQXCL5GridViewAdapter);
        setAdapter(qxcL6gridview, mQXCL6GridViewAdapter);
        setAdapter(qxcL7gridview, mQXCL7GridViewAdapter);

        qxcYilou1Tv.setVisibility(View.INVISIBLE);
        qxcYilou2Tv.setVisibility(View.INVISIBLE);
        qxcYilou3Tv.setVisibility(View.INVISIBLE);
        qxcYilou4Tv.setVisibility(View.INVISIBLE);
        qxcYilou5Tv.setVisibility(View.INVISIBLE);
        qxcYilou6Tv.setVisibility(View.INVISIBLE);
        qxcYilou7Tv.setVisibility(View.INVISIBLE);
        baselotteryTotalTv.setText("请选择投注号码");
        baselotteryMoneyTv.setVisibility(View.GONE);
        titlebarTv.setText("七星彩");
        baselotterySumBt.setEnabled(false);
        titlebarBackLl.setOnClickListener(new MyOnclickListenler());
        baselotteryDeleteallTv.setOnClickListener(new MyOnclickListenler());
        qxcShakeRl.setOnClickListener(new MyOnclickListenler());
        baselotterySumBt.setOnClickListener(new MyOnclickListenler());
        initShakeUtils();//监听摇一摇
        UpDateData();
    }

    private void UpDateData() {
        String lotid = "qxc";
        OkGo.get(UrlManager.getSaleIssueUrl(QXCActivity.this, lotid)).cacheMode(CacheMode.NO_CACHE).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                mNumLotteryBean = gson.fromJson(s, NumLotteryBean.class);
                if (null != mNumLotteryBean) {
                    Logger.e(mNumLotteryBean.getData().getLotName() + "lotnmae");
                    long time = mNumLotteryBean.getData().getIssueList().get(0).getStopTime();
                    String stime = DateUtils.getPl5DateAndTime(time * 1000);
                    String issue = "第" + mNumLotteryBean.getData().getIssueList().get(0).getIssueNo() + "期";
                    qxcIssueTv.setText(issue);
                    qxcStoptimeTv.setText("" + stime);
                    mQXCL1GridViewAdapter.setmMissList(mMissList);
                    mQXCL1GridViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                UIUtils.showToastSafe("网络异常,请检查网络设置");
            }
        });
    }

    private void setAdapter(GridViewWithoutScroll g, final NumGridViewAdapter p) {
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

    private void initLotteryData() {
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
        if (mNumLotteryBean != null) {
            numLotOrderBean.setIssueNo(mNumLotteryBean.getData().getIssueList().get(0).getIssueNo());
        }
        if (total > 1) {
            numLotOrderBean.setGuoguanType("f");
        } else {
            numLotOrderBean.setGuoguanType("d");
        }
//        mNumLotOrderBeans.clear();
        mNumLotOrderBeans.add(0, numLotOrderBean);
    }

    private void meath(NumGridViewAdapter p) {
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
    protected void init() {
        for (int i = 0; i < 10; i++) {
            mMissList.add("");
        }
    }

    private class MyOnclickListenler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.titlebar_back_ll:
                    finish();
                    break;
                case R.id.baselottery_deleteall_tv:
                    removeAllData();
                    break;
                case R.id.baselottery_sum_bt:

                    if (mNumLotteryBean == null) {
                        UIUtils.showToastSafe("未能获取到当前彩种信息,不能投注");
                        return;
                    }
                    if (total > 10000) {
                        UIUtils.showToastSafe("投注注数不能超过10000注");
                        return;
                    }
                    initLotteryData();
                    Intent mIntent = new Intent(QXCActivity.this, NumLotteryDingdanActivity.class);
                    mIntent.putExtra("Pl5Activity", (Serializable) mNumLotOrderBeans);
                    startActivityForResult(mIntent, Configs.NUMLOTT_REQUESTCODE);
                    break;
                case R.id.qxc_shake_rl:
                    Logger.e("VibratorUtil执行了");
                    shakeToChoose();
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Configs.NUMLOTT_REQUESTCODE) {
            if (resultCode == Configs.NUMLOTT_RESULTCODE) {
                Bundle bundle = data.getExtras();
                mNumLotOrderBeans = (List<NumLotOrderBean>) bundle.getSerializable(Configs.SFCDINGDAN_RESULTKEY);
                removeAllData();
            } else {
                finish();
            }
        }
    }

    //监听摇一摇,并机选数据
    private void initShakeUtils() {
        mShakeUtils = new ShakeUtils(this);
        mShakeUtils.setOnShakeListener(new ShakeUtils.OnShakeListener() {
            @Override
            public void onShake() {
                VibratorUtil.Vibrate(QXCActivity.this, 300);
                shakeToChoose();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null != mShakeUtils) {
            mShakeUtils.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != mShakeUtils) {
            mShakeUtils.onPause();
        }
    }

    //机选一注并刷新页面
    private void shakeToChoose() {
        VibratorUtil.Vibrate(QXCActivity.this, 300);
        removeAllData();
        char[] mchar = RandomUtils.randomInt(7).toCharArray();

        mQXCL1GridViewAdapter.getSelectionMap().put(Integer.parseInt(String.valueOf(mchar[0])), true);
        mQXCL2GridViewAdapter.getSelectionMap().put(Integer.parseInt(String.valueOf(mchar[1])), true);
        mQXCL3GridViewAdapter.getSelectionMap().put(Integer.parseInt(String.valueOf(mchar[2])), true);
        mQXCL4GridViewAdapter.getSelectionMap().put(Integer.parseInt(String.valueOf(mchar[3])), true);
        mQXCL5GridViewAdapter.getSelectionMap().put(Integer.parseInt(String.valueOf(mchar[4])), true);
        mQXCL6GridViewAdapter.getSelectionMap().put(Integer.parseInt(String.valueOf(mchar[5])), true);
        mQXCL7GridViewAdapter.getSelectionMap().put(Integer.parseInt(String.valueOf(mchar[6])), true);
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

    private void removeAllData() {
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
    protected String getBaidutitle() {
        return "七星彩";
    }
}
