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
 * 创建日期：2016-10-14-0014 09:43
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class PL5Activity extends BaseActivity {

    @BindView(R.id.titlebar_back_ll)
    LinearLayout titlebarBackLl;
    @BindView(R.id.titlebar_tv)
    TextView titlebarTv;
    @BindView(R.id.filtrate_tv)
    ImageView filtrateTv;
    @BindView(R.id.base_titleBar)
    RelativeLayout baseTitleBar;
    @BindView(R.id.pl5_issue_tv)
    TextView pl5IssueTv;
    @BindView(R.id.pl5_stoptime_tv)
    TextView pl5StoptimeTv;
    @BindView(R.id.pl5_l1gridview)
    GridViewWithoutScroll pl5L1gridview;
    @BindView(R.id.pl5_l2gridview)
    GridViewWithoutScroll pl5L2gridview;
    @BindView(R.id.pl5_l3gridview)
    GridViewWithoutScroll pl5L3gridview;
    @BindView(R.id.pl5_l4gridview)
    GridViewWithoutScroll pl5L4gridview;
    @BindView(R.id.pl5_l5gridview)
    GridViewWithoutScroll pl5L5gridview;
    @BindView(R.id.scorll)
    ScrollView scorll;
    @BindView(R.id.baselottery_deleteall_tv)
    TextView baselotteryDeleteallTv;
    @BindView(R.id.baselottery_total_tv)
    TextView baselotteryTotalTv;
    @BindView(R.id.baselottery_sum_bt)
    TextView baselotterySumBt;
    @BindView(R.id.baselottery_bottom_ll)
    LinearLayout baselotteryBottomLl;
    @BindView(R.id.titlebar_indicator)
    ImageView titlebarIndicator;
    @BindView(R.id.titlebar_title)
    LinearLayout titlebarTitle;
    @BindView(R.id.pl5_shake_tv)
    TextView pl5ShakeTv;
    @BindView(R.id.pl5_shake_rl)
    RelativeLayout pl5ShakeRl;
    @BindView(R.id.baselottery_money_tv)
    TextView baselotteryMoneyTv;
    @BindView(R.id.titlebar_indicatordown)
    ImageView titlebarIndicatordown;
    @BindView(R.id.main_calendar)
    ImageView mainCalendar;
    @BindView(R.id.pl5_yl1_tv)
    TextView pl5Yl1Tv;
    @BindView(R.id.pl5_yl2_tv)
    TextView pl5Yl2Tv;
    @BindView(R.id.pl5_yl3_tv)
    TextView pl5Yl3Tv;
    @BindView(R.id.pl5_yl4_tv)
    TextView pl5Yl4Tv;
    @BindView(R.id.pl5_yl5_tv)
    TextView pl5Yl5Tv;

    private List<String> mMissList = new ArrayList<>();//遗漏的集合
    private NumGridViewAdapter mPL5L1GridViewAdapter;
    private NumGridViewAdapter mPL5L2GridViewAdapter;
    private NumGridViewAdapter mPL5L3GridViewAdapter;
    private NumGridViewAdapter mPL5L4GridViewAdapter;
    private NumGridViewAdapter mPL5L5GridViewAdapter;

    private int count1;
    private int count2;
    private int count3;
    private int count4;
    private int count5;

    private ShakeUtils mShakeUtils = null;//摇一摇
    private List<Map<Integer, Boolean>> lotteryData = new ArrayList<>();//提交数据
    private List<NumLotOrderBean> mNumLotOrderBeans = new ArrayList<>();
    private NumLotOrderBean numLotOrderBean;
    private int total;
    private NumLotteryBean mNumLotteryBean;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            total = count1 * count2 * count3 * count4 * count5;
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
        setContentView(R.layout.activity_pl5);
        ButterKnife.bind(this);
        mPL5L1GridViewAdapter = new NumGridViewAdapter(mMissList, PL5Activity.this);
        mPL5L2GridViewAdapter = new NumGridViewAdapter(mMissList, PL5Activity.this);
        mPL5L3GridViewAdapter = new NumGridViewAdapter(mMissList, PL5Activity.this);
        mPL5L4GridViewAdapter = new NumGridViewAdapter(mMissList, PL5Activity.this);
        mPL5L5GridViewAdapter = new NumGridViewAdapter(mMissList, PL5Activity.this);
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
        baselotteryTotalTv.setText("请选择投注号码");
        baselotteryMoneyTv.setVisibility(View.GONE);
        titlebarTv.setText("排列五");
        baselotterySumBt.setEnabled(false);
        baselotterySumBt.setEnabled(false);//未选号,将按钮设置为不可点击
        initShakeUtils();
        titlebarBackLl.setOnClickListener(new MyOnclickListenler());
        baselotteryDeleteallTv.setOnClickListener(new MyOnclickListenler());
        baselotterySumBt.setOnClickListener(new MyOnclickListenler());
        pl5ShakeRl.setOnClickListener(new MyOnclickListenler());
        UpDateData();
    }

    private void UpDateData() {
        String lotid = "pl5";
        OkGo.get(UrlManager.getSaleIssueUrl(PL5Activity.this, lotid)).cacheMode(CacheMode.NO_CACHE).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                mNumLotteryBean = gson.fromJson(s, NumLotteryBean.class);
                if (null != mNumLotteryBean) {
                    Logger.e(mNumLotteryBean.getData().getLotName() + "lotnmae");
                    long time = mNumLotteryBean.getData().getIssueList().get(0).getStopTime();
                    String stime = DateUtils.getPl5DateAndTime(time * 1000);
                    String issue = "第" + mNumLotteryBean.getData().getIssueList().get(0).getIssueNo() + "期";
                    pl5IssueTv.setText(issue);
                    pl5StoptimeTv.setText("" + stime);
                }
               /* mMissList.clear();
                for (int i = 0; i < 10; i++) {
                    mMissList.add( "");
                }
                mPL5L1GridViewAdapter.setmMissList(mMissList);
                mPL5L2GridViewAdapter.setmMissList(mMissList);
                mPL5L3GridViewAdapter.setmMissList(mMissList);
                mPL5L4GridViewAdapter.setmMissList(mMissList);
                mPL5L5GridViewAdapter.setmMissList(mMissList);

                 mPL5L1GridViewAdapter.notifyDataSetChanged();
                 mPL5L2GridViewAdapter.notifyDataSetChanged();
                 mPL5L3GridViewAdapter.notifyDataSetChanged();
                 mPL5L4GridViewAdapter.notifyDataSetChanged();
                 mPL5L5GridViewAdapter.notifyDataSetChanged();*/
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

    private void meath(NumGridViewAdapter p) {
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
            baselotterySumBt.setEnabled(true);
        } else {
            Message message = new Message();
            message.what = 2;
            mHandler.sendMessage(message);
            baselotterySumBt.setEnabled(false);
        }
    }

    @Override
    protected void init() {
        for (int i = 0; i < 10; i++) {
            mMissList.add("");
        }
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

    //监听摇一摇,并机选数据
    private void initShakeUtils() {
        mShakeUtils = new ShakeUtils(this);
        mShakeUtils.setOnShakeListener(new ShakeUtils.OnShakeListener() {
            @Override
            public void onShake() {
                VibratorUtil.Vibrate(PL5Activity.this, 300);
                shakeToChoose();
            }
        });
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
                    Logger.e(mNumLotOrderBeans.size() + "mNumLotOrderBeans");
                    Intent mIntent = new Intent(PL5Activity.this, NumLotteryDingdanActivity.class);
                    mIntent.putExtra("Pl5Activity", (Serializable) mNumLotOrderBeans);
                    startActivityForResult(mIntent, Configs.NUMLOTT_REQUESTCODE);
                    break;
                case R.id.pl5_shake_rl:
                    Logger.e("VibratorUtil执行了");
                    shakeToChoose();
                    break;
            }
        }
    }

    private void initLotteryData() {
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


    //机选一注并刷新页面
    private void shakeToChoose() {
        VibratorUtil.Vibrate(PL5Activity.this, 300);
        removeAllData();
        char[] mchar = RandomUtils.randomInt(5).toCharArray();
        mPL5L1GridViewAdapter.getSelectionMap().put(Integer.parseInt(String.valueOf(mchar[0])), true);
        mPL5L2GridViewAdapter.getSelectionMap().put(Integer.parseInt(String.valueOf(mchar[1])), true);
        mPL5L3GridViewAdapter.getSelectionMap().put(Integer.parseInt(String.valueOf(mchar[2])), true);
        mPL5L4GridViewAdapter.getSelectionMap().put(Integer.parseInt(String.valueOf(mchar[3])), true);
        mPL5L5GridViewAdapter.getSelectionMap().put(Integer.parseInt(String.valueOf(mchar[4])), true);
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

    private void removeAllData() {
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
    protected String getBaidutitle() {
        return "排列5";
    }
}
