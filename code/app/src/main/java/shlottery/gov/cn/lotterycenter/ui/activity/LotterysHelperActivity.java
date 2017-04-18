package shlottery.gov.cn.lotterycenter.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.LotteryHelperBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.manager.LoginManager;
import shlottery.gov.cn.lotterycenter.protool.LotterysHelperProtacol;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.activity.JincaiBasketballActivity;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.activity.JingcaiSoccerActivity;
import shlottery.gov.cn.lotterycenter.ui.nublottery.activity.Bqc6Activity;
import shlottery.gov.cn.lotterycenter.ui.nublottery.activity.JqcActivity;
import shlottery.gov.cn.lotterycenter.ui.nublottery.activity.LottoActivity;
import shlottery.gov.cn.lotterycenter.ui.nublottery.activity.PL3Activity;
import shlottery.gov.cn.lotterycenter.ui.nublottery.activity.PL5Activity;
import shlottery.gov.cn.lotterycenter.ui.nublottery.activity.QXCActivity;
import shlottery.gov.cn.lotterycenter.ui.nublottery.activity.Sfc14Activity;
import shlottery.gov.cn.lotterycenter.ui.nublottery.activity.SfcR9Activity;
import shlottery.gov.cn.lotterycenter.ui.nublottery.activity.SyxwActivity;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * 模拟购彩页面
 */
public class LotterysHelperActivity extends BaseActivity implements LoadCallback {

    private LinearLayout mSh115Layout;
    private TextView mSh11x5Issueno;
    private TextView mSh11x5Intro;
    private TextView mHour1;
    private TextView mHour2;
    private TextView mMinus1;
    private TextView mMinus2;

    private LinearLayout mLottoLayout;
    private TextView mLottoIssue;
    private LinearLayout mQxLayout;
    private TextView mQxIssue;
    private LinearLayout mPl3Layout;
    private TextView mPl3Issue;
    private LinearLayout mPl5Layout;
    private TextView mPl5Issue;
    private LinearLayout mJzLayout;
    private TextView mJzIssue;
    private LinearLayout mJlLayout;
    private TextView mJlIssue;
    private LinearLayout mJqcLayout;
    private TextView mJqcIssue;
    private LinearLayout mBqcLayout;
    private TextView mBqcIssue;
    private LinearLayout mSfc14Layout;
    private TextView mSfc14Issue;
    private LinearLayout mSfc9Layout;
    private TextView mSfc9Issue;
    private boolean mIsTimerRunning = false;
    private TextView mOrderTv;
    private Timer timer = new Timer();
    private final int BEGIN_TASK = 5;//开始倒计时
    private final int BEGIN_REFRSH = 6;//开始刷新页面
    private long stopTime;
    private int count = 0;
    private MyListener mListener;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == BEGIN_TASK) {
                if (msg.arg1 == BEGIN_REFRSH) {
                    beginLoad();
                } else {
                    String time = DateUtils.getTimeExpend(System.currentTimeMillis(), stopTime);
                    LogUtils.i("countdown time:" + time);
                    if (time.contains("小时")) {
                        mHour1.setText(1 + "");
                        mHour2.setText(0 + "");
                        mMinus1.setText(0 + "");
                        mMinus2.setText(0 + "");
                    } else if (time.contains(":")) {
                        String hour = time.substring(0, time.indexOf(":"));
                        String minus = time.substring(time.indexOf(":") + 1, time.length());
                        LogUtils.i("countdown time&minus  helper:" + hour + ":::" + minus);
//                        int hourTime = Integer.parseInt(hour);
                        mMinus1.setText(minus.substring(0, 1));
                        mMinus2.setText(minus.substring(1, 2));
                        mHour1.setText(hour.substring(0, 1));
                        mHour2.setText(hour.substring(1, 2));
                    }
                }
            }
        }
    };

    private void initTitleBar() {
        LinearLayout mBack = (LinearLayout) findViewById(R.id.titlebar_back_ll);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titlebarTv = (TextView) findViewById(R.id.titlebar_tv);
        titlebarTv.setText("模拟购彩");
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_lotterys_helper);
        mSh115Layout = (LinearLayout) findViewById(R.id.lotterys_gaopingcai);
        mSh11x5Issueno = (TextView) findViewById(R.id.lotterys_sh11x5_issueno);
        mSh11x5Intro = (TextView) findViewById(R.id.lotterys_intro_sh115);
        mHour1 = (TextView) findViewById(R.id.lotterys_sh11x5_hour1);
        mHour2 = (TextView) findViewById(R.id.lotterys_sh11x5_hour2);
        mMinus1 = (TextView) findViewById(R.id.lotterys_sh11x5_minus1);
        mMinus2 = (TextView) findViewById(R.id.lotterys_sh11x5_minus2);
        mSh115Layout.setOnClickListener(mListener);

        mLottoLayout = (LinearLayout) findViewById(R.id.lotteryshelper_lotto_layout);
        mLottoIssue = (TextView) findViewById(R.id.lotteryshelper_date_lotto);
        mLottoLayout.setOnClickListener(mListener);

        mQxLayout = (LinearLayout) findViewById(R.id.lotteryshelper_qx_layout);
        mQxIssue = (TextView) findViewById(R.id.lotteryshelper_date_qx);
        mQxLayout.setOnClickListener(mListener);

        mPl3Layout = (LinearLayout) findViewById(R.id.lotteryshelper_pl3_layout);
        mPl3Issue = (TextView) findViewById(R.id.lotteryshelper_date_sort3);
        mPl3Layout.setOnClickListener(mListener);

        mPl5Layout = (LinearLayout) findViewById(R.id.lotteryshelper_pl5_layout);
        mPl5Issue = (TextView) findViewById(R.id.lotteryshelper_date_pl5);
        mPl5Layout.setOnClickListener(mListener);

        mJzLayout = (LinearLayout) findViewById(R.id.lotteryshelper_jz_layout);
        mJzIssue = (TextView) findViewById(R.id.lotteryshelper_date_jz);
        mJzLayout.setOnClickListener(mListener);

        mJlLayout = (LinearLayout) findViewById(R.id.lotteryshelper_jl_layout);
        mJlIssue = (TextView) findViewById(R.id.lotteryshelper_date_jl);
        mJlLayout.setOnClickListener(mListener);

        mJqcLayout = (LinearLayout) findViewById(R.id.lotteryshelper_jqc_layout);
        mJqcIssue = (TextView) findViewById(R.id.lotteryshelper_date_jqc);
        mJqcLayout.setOnClickListener(mListener);

        mBqcLayout = (LinearLayout) findViewById(R.id.lotteryshelper_bqc_layout);
        mBqcIssue = (TextView) findViewById(R.id.lotteryshelper_date_bqc);
        mBqcLayout.setOnClickListener(mListener);

        mSfc14Layout = (LinearLayout) findViewById(R.id.lotteryshelper_sfc14_layout);
        mSfc14Issue = (TextView) findViewById(R.id.lotteryshelper_date_sfc14);
        mSfc14Layout.setOnClickListener(mListener);

        mSfc9Layout = (LinearLayout) findViewById(R.id.lotteryshelper_sfc9_layout);
        mSfc9Issue = (TextView) findViewById(R.id.lotteryshelper_date_sfc9);
        mSfc9Layout.setOnClickListener(mListener);

        mOrderTv = (TextView) findViewById(R.id.titlebar_order);
        mOrderTv.setOnClickListener(mListener);
        initTitleBar();
        beginLoad();
    }

    @Override
    protected void init() {
        mListener = new MyListener();
    }

    private void beginLoad() {
        LotterysHelperProtacol protocol = new LotterysHelperProtacol(getParent());
        protocol.load(this, this);
    }

    @Override
    public void onSucess(Object o) {
        if (o != null) {
            LotteryHelperBean loadData = (LotteryHelperBean) o;
            if (loadData.getRet() == 100) {
                initInfo(loadData.getData().getList());
                handlSh115Success(loadData.getData().getList());
            }
        }
    }

    @Override
    public void onError() {

    }

    private void initInfo(List<LotteryHelperBean.DataBean.ListBean> list) {
        String time = "";
        long timeLong;
        for (int i = 0; i < list.size(); i++) {
            LotteryHelperBean.DataBean.ListBean bean = list.get(i);
            timeLong = bean.getStopTime();
            time = DateUtils.formatSimpleDateAndTime(timeLong * 1000);
            int count = bean.getMatchCount();
            LogUtils.i("initinfo:" + time + ":::" + bean.getLotId());
            if (bean.getLotId().equals("dlt")) {
                mLottoIssue.setText(time);
            } else if (bean.getLotId().equals("sh115")) {
                mSh11x5Issueno.setText("第" + bean.getIssueNo() + "期");
            } else if (bean.getLotId().equals("pl3")) {
                mPl3Issue.setText(time);
            } else if (bean.getLotId().equals("pl5")) {
                mPl5Issue.setText(time);
            } else if (bean.getLotId().equals("qxc")) {
                mQxIssue.setText(time);
            } else if (bean.getLotId().equals("sfc")) {
                mSfc9Issue.setText(time);
                mSfc14Issue.setText(time);
            } else if (bean.getLotId().equals("bqc")) {
                mBqcIssue.setText(time);
            } else if (bean.getLotId().equals("jqc")) {
                mJqcIssue.setText(time);
            } else if (bean.getLotId().equals("jczq")) {
                mJzIssue.setText("还有" + count + "场可投注");
            } else if (bean.getLotId().equals("jclq")) {
                mJlIssue.setText("还有" + count + "场可投注");
            }
        }
    }

    private void handlSh115Success(List<LotteryHelperBean.DataBean.ListBean> list) {
        LotteryHelperBean.DataBean.ListBean mNumLotteryBean = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLotId().equals("sh115")) {
                mNumLotteryBean = list.get(i);
            }
        }

        if (null != mNumLotteryBean) {
            long currentTime = System.currentTimeMillis();//系统当前时间
            long stime = (long) mNumLotteryBean.getStopTime();
            stopTime = stime * 1000;
            if (currentTime < (stime * 1000)) {
                Logger.e(currentTime + "----系统当前时间" + stopTime + "---结束时间");
            }
            startTimeTask();
        }
    }

    private void startTimeTask() {
        // 更新 倒计时以及期号
        if (null != timer && !mIsTimerRunning) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mIsTimerRunning = true;
                    Message message = new Message();
                    message.what = BEGIN_TASK;
                    if (stopTime <= System.currentTimeMillis()) {
                        LogUtils.i("countdown time timeTask  stopTime <= System.currentTimeMillis():" + stopTime + ":::" + System.currentTimeMillis());
                        if (count % 5 == 0) {
                            Logger.e(count + "count的值1");
                            message.arg1 = BEGIN_REFRSH;
                        }
                        Logger.e(count + "count的值2");
                        count++;
                    }
                    mHandler.sendMessage(message);
                }
            }, 1000, 1000);
        }
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            switch (view.getId()) {
                case R.id.lotterys_gaopingcai:
                    intent.setClass(LotterysHelperActivity.this, SyxwActivity.class);
                    startActivity(intent);
                    break;
                case R.id.lotteryshelper_lotto_layout:
                    intent.setClass(LotterysHelperActivity.this, LottoActivity.class);
                    startActivity(intent);
                    break;
                case R.id.lotteryshelper_qx_layout:
                    intent.setClass(LotterysHelperActivity.this, QXCActivity.class);
                    startActivity(intent);
                    break;
                case R.id.lotteryshelper_pl3_layout:
                    intent.setClass(LotterysHelperActivity.this, PL3Activity.class);
                    startActivity(intent);
                    break;
                case R.id.lotteryshelper_pl5_layout:
                    intent.setClass(LotterysHelperActivity.this, PL5Activity.class);
                    startActivity(intent);
                    break;
                case R.id.lotteryshelper_jz_layout:
                    intent.setClass(LotterysHelperActivity.this, JingcaiSoccerActivity.class);
                    startActivity(intent);
                    break;
                case R.id.lotteryshelper_jl_layout:
                    intent.setClass(LotterysHelperActivity.this, JincaiBasketballActivity.class);
                    startActivity(intent);
                    break;
                case R.id.lotteryshelper_jqc_layout:
                    intent.setClass(LotterysHelperActivity.this, JqcActivity.class);
                    startActivity(intent);
                    break;
                case R.id.lotteryshelper_bqc_layout:
                    intent.setClass(LotterysHelperActivity.this, Bqc6Activity.class);
                    startActivity(intent);
                    break;
                case R.id.lotteryshelper_sfc9_layout:
                    intent.setClass(LotterysHelperActivity.this, SfcR9Activity.class);
                    startActivity(intent);
                    break;
                case R.id.lotteryshelper_sfc14_layout:
                    intent.setClass(LotterysHelperActivity.this, Sfc14Activity.class);
                    startActivity(intent);
                    break;
                case R.id.titlebar_order:
                    if (LoginManager.getInstance().login(LotterysHelperActivity.this)) {
                        intent.setClass(LotterysHelperActivity.this, UsercenterOrderActivity.class);
                        startActivity(intent);
                    }
                    break;
            }
        }
    }
    @Override
    protected String getBaidutitle() {
        return "模拟购彩";
    }
}
