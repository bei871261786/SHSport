package shlottery.gov.cn.lotterycenter.ui.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import shlottery.gov.cn.lotterycenter.Base.BaseMainFragment;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.LottersRoomBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.protool.LotterysRoomProtocol;
import shlottery.gov.cn.lotterycenter.ui.activity.IssueBounsDetailActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.JiangjiActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.NewsDetailActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.ZoushiActivity;
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
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingPager;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

import static shlottery.gov.cn.lotterycenter.Base.Configure.DETAIL_JINGCAI;
import static shlottery.gov.cn.lotterycenter.Base.Configure.DETAIL_NUMBER;
import static shlottery.gov.cn.lotterycenter.Base.Configure.DETAIL_SH115;
import static shlottery.gov.cn.lotterycenter.Base.Configure.DETAIL_SOCCER;
import static shlottery.gov.cn.lotterycenter.R.id.lotterys_bqc;
import static shlottery.gov.cn.lotterycenter.R.id.lotterys_jqc;
import static shlottery.gov.cn.lotterycenter.R.id.lotterys_qx;
import static shlottery.gov.cn.lotterycenter.R.id.lotterys_sort3;
import static shlottery.gov.cn.lotterycenter.R.id.lotterys_sort5;


/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/14 13:26
 * 描    述：彩种大厅
 * 修订历史：
 * ************************************************
 */

public class LotterysFragment extends BaseMainFragment implements LoadCallback, SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSrlLotterysContent;
    private LinearLayout       mSh11x5ExpandLayout;
    private LinearLayout       mSh11x5IndicatorLayout;
    private LinearLayout       mSh11x5Verture;
    private LinearLayout       mSh11x5Zoushi;
    private LinearLayout       mSh11x5Issue;
    private LinearLayout       mSh11x5Jiangji;
    private LinearLayout       mSh11x5PlayRule;


    private LinearLayout mSh11x5showLayout;
    private LinearLayout mJinzushowLayout;
    private LinearLayout mJinlanshowLayout;
    private LinearLayout mLottoshowLayout;
    private LinearLayout mPl3showLayout;
    private LinearLayout mPl5showLayout;
    private LinearLayout mQxcshowLayout;
    private LinearLayout mSfc14showLayout;
    private LinearLayout mSfc9showLayout;
    private LinearLayout mBqcshowLayout;
    private LinearLayout mJqcshowLayout;


    private ImageView mSh11x5Indicator;
    private TextView  mSh11x5Issueno;
    private TextView  mSh11x5Intro;
    private TextView  mHour1;
    private TextView  mHour2;
    private TextView  mMinus1;
    private TextView  mMinus2;

    private LinearLayout mJzExpandLayout;
    private LinearLayout mJzIndicatorLayout;
    private LinearLayout mJzVerture;
    private LinearLayout mJzIssue;
    private LinearLayout mJzPlayRule;
    private ImageView    mJzIndicator;
    private TextView     mJzIntro;

    private LinearLayout mLottoExpandLayout;
    private LinearLayout mLottoIndicatorLayout;
    private LinearLayout mLottoVerture;
    private LinearLayout mLottoIssue;
    private LinearLayout mLottoPlayRule;
    private LinearLayout mLottoZoushi;
    private LinearLayout mLottoJiangji;
    private ImageView    mLottoIndicator;
    private TextView     mLottoIntro;
    private LinearLayout mPl3ExpandLayout;
    private LinearLayout mPl3PlayRule;
    private LinearLayout mPl3IndicatorLayout;
    private LinearLayout mPl3Verture;
    private LinearLayout mPl3Zoushi;
    private LinearLayout mPl3Issue;
    private ImageView    mPl3Indicator;
    private TextView     mPl3Intro;

    private LinearLayout mPl5ExpandLayout;
    private LinearLayout mPl5IndicatorLayout;
    private LinearLayout mPl5Verture;
    private LinearLayout mPl5Issue;
    private LinearLayout mPl5Zoushi;
    private LinearLayout mPl5PlayRule;
    private ImageView    mPl5Indicator;
    private TextView     mPl5Intro;

    private LinearLayout mQxExpandLayout;
    private LinearLayout mQxIndicatorLayout;
    private LinearLayout mQxVerture;
    private LinearLayout mQxZoushi;
    private LinearLayout mQxIssue;
    private LinearLayout mQxJiangji;
    private LinearLayout mQxPlayRule;
    private ImageView    mQxIndicator;
    private TextView     mQxIntro;

    private LinearLayout mSf14ExpandLayout;
    private LinearLayout mSf14IndicatorLayout;
    private LinearLayout mSf14Verture;
    private LinearLayout mSf14Issue;
    private LinearLayout mSf14PlayRule;
    private ImageView    mSf14Indicator;
    private TextView     mSf14Intro;

    private LinearLayout mSf9ExpandLayout;
    private LinearLayout mSf9IndicatorLayout;
    private LinearLayout mSf9Verture;
    private LinearLayout mSf9Issue;
    private LinearLayout mSf9PlayRule;
    private ImageView    mSf9Indicator;
    private TextView     mSf9Intro;

    private LinearLayout mBqcExpandLayout;
    private LinearLayout mBqcIndicatorLayout;
    private LinearLayout mBqcVerture;
    private LinearLayout mBqcIssue;
    private LinearLayout mBqcPlayRule;
    private ImageView    mBqcIndicator;
    private TextView     mBqcIntro;

    private LinearLayout mJqcExpandLayout;
    private LinearLayout mJqcIndicatorLayout;
    private LinearLayout mJqcVerture;
    private LinearLayout mJqcIssue;
    private LinearLayout mJqcPlayRule;
    private ImageView    mJqcIndicator;
    private TextView     mJqcIntro;


    private LinearLayout mJlExpandLayout;
    private LinearLayout mJlIndicatorLayout;
    private LinearLayout mJlVerture;
    private LinearLayout mJlIssue;
    private LinearLayout mJlPlayRule;
    private ImageView    mJlIndicator;
    private TextView     mJlIntro;

    private MyListener     mListener;
    private OptionListener mOptionListener;

    private final int   TAG_SH115    = 0;
    private final int   TAG_JINZU    = 1;
    private final int   TAG_JINLAN   = 2;
    private final int   TAG_LOTTO    = 3;
    private final int   TAG_PL3      = 4;
    private final int   TAG_PL5      = 5;
    private final int   TAG_QIXIN    = 6;
    private final int   TAG_SF14     = 7;
    private final int   TAG_SF9      = 8;
    private final int   TAG_BQC      = 9;
    private final int   TAG_JQC      = 10;
    private       Timer timer        = new Timer();
    private final int   BEGIN_TASK   = 5;//开始倒计时
    private final int   BEGIN_REFRSH = 6;//开始刷新页面
    private long stopTime;
    private int     count           = 0;
    private boolean mIsTimerRunning = false;
    private List<LottersRoomBean.DataBean.ListBean> mData;
    private TranslateAnimation                      mShowAnim;
    private TranslateAnimation                      HiddenAmin;


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
                        LogUtils.i("countdown time&minus  lotteryhome:" + hour + ":::" + minus);
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
    private LotterysRoomProtocol mProtocol;


    @Override
    protected void initTitlebar() {
        mFiltrate.setVisibility(View.GONE);
        mCalendar.setVisibility(View.GONE);
        mSetting.setVisibility(View.GONE);
    }

    private void initTitleBar(View view) {
        LinearLayout mBack = (LinearLayout) view.findViewById(R.id.titlebar_back_ll);
        mBack.setVisibility(View.GONE);
        TextView titlebarTv = (TextView) view.findViewById(R.id.titlebar_tv);
        titlebarTv.setText("彩种大厅");
        ImageView indicator = (ImageView) view.findViewById(R.id.titlebar_indicator);
        indicator.setVisibility(View.GONE);
    }

    @Override
    protected View createLoadedView() {
        View view = layoutInflater.inflate(R.layout.fragment_lotterys, null);
        mSrlLotterysContent = (SwipeRefreshLayout) view.findViewById(R.id.srl_lotterys_content);
        mSh11x5ExpandLayout = (LinearLayout) view.findViewById(R.id.lotterys_sh115_layout);
        mSh11x5IndicatorLayout = (LinearLayout) view.findViewById(R.id.lotterys_sh11x5_indicatorlayout);
        mSh11x5Indicator = (ImageView) view.findViewById(R.id.lotterys_sh11x5_indicator);
        mHour1 = (TextView) view.findViewById(R.id.lotterys_sh11x5_hour1);
        mHour2 = (TextView) view.findViewById(R.id.lotterys_sh11x5_hour2);
        mMinus1 = (TextView) view.findViewById(R.id.lotterys_sh11x5_minus1);
        mMinus2 = (TextView) view.findViewById(R.id.lotterys_sh11x5_minus2);
        mJzExpandLayout = (LinearLayout) view.findViewById(R.id.lotterys_jz_layout);
        mJzIndicatorLayout = (LinearLayout) view.findViewById(R.id.lotterys_jz_indicatorlayout);
        mJzIndicator = (ImageView) view.findViewById(R.id.lotterys_jz_indicator);
        mJlExpandLayout = (LinearLayout) view.findViewById(R.id.lotterys_jl_layout);
        mJlIndicatorLayout = (LinearLayout) view.findViewById(R.id.lotterys_jl_indicatorlayout);
        mJlIndicator = (ImageView) view.findViewById(R.id.lotterys_jl_indicator);
        mLottoExpandLayout = (LinearLayout) view.findViewById(R.id.lotterys_lotto_layout);
        mLottoIndicatorLayout = (LinearLayout) view.findViewById(R.id.lotterys_lotto_indicatorlayout);
        mLottoIndicator = (ImageView) view.findViewById(R.id.lotterys_lotto_indicator);
        mPl3ExpandLayout = (LinearLayout) view.findViewById(R.id.lotterys_sort3_layout);
        mPl3IndicatorLayout = (LinearLayout) view.findViewById(R.id.lotterys_sort3_indicatorlayout);
        mPl3Indicator = (ImageView) view.findViewById(R.id.lotterys_sort3_indicator);
        mPl5ExpandLayout = (LinearLayout) view.findViewById(R.id.lotterys_sort5_layout);
        mPl5IndicatorLayout = (LinearLayout) view.findViewById(R.id.lotterys_sort5_indicatorlayout);
        mPl5Indicator = (ImageView) view.findViewById(R.id.lotterys_sort5_indicator);

        mQxExpandLayout = (LinearLayout) view.findViewById(R.id.lotterys_qx_layout);
        mQxIndicatorLayout = (LinearLayout) view.findViewById(R.id.lotterys_qx_indicatorlayout);
        mQxIndicator = (ImageView) view.findViewById(R.id.lotterys_qx_indicator);
        mSf14ExpandLayout = (LinearLayout) view.findViewById(R.id.lotterys_sf14_layout);
        mSf14IndicatorLayout = (LinearLayout) view.findViewById(R.id.lotterys_sf14_indicatorlayout);
        mSf14Indicator = (ImageView) view.findViewById(R.id.lotterys_sf14_indicator);

        mSf9ExpandLayout = (LinearLayout) view.findViewById(R.id.lotterys_sf9_layout);
        mSf9IndicatorLayout = (LinearLayout) view.findViewById(R.id.lotterys_sf9_indicatorlayout);
        mSf9Indicator = (ImageView) view.findViewById(R.id.lotterys_sf9_indicator);

        mBqcExpandLayout = (LinearLayout) view.findViewById(R.id.lotterys_bqc_layout);
        mBqcIndicatorLayout = (LinearLayout) view.findViewById(R.id.lotterys_bqc_indicatorlayout);
        mBqcIndicator = (ImageView) view.findViewById(R.id.lotterys_bqc_indicator);

        mJqcExpandLayout = (LinearLayout) view.findViewById(R.id.lotterys_jqc_layout);
        mJqcIndicatorLayout = (LinearLayout) view.findViewById(R.id.lotterys_jqc_indicatorlayout);
        mJqcIndicator = (ImageView) view.findViewById(R.id.lotterys_jqc_indicator);


        mSh11x5showLayout = (LinearLayout) view.findViewById(R.id.lotterys_gaopingcai);
        mJinzushowLayout = (LinearLayout) view.findViewById(R.id.lotterys_jinzu);
        mJinlanshowLayout = (LinearLayout) view.findViewById(R.id.lotterys_jinlan);
        mLottoshowLayout = (LinearLayout) view.findViewById(R.id.lotterys_lotto);
        mPl3showLayout = (LinearLayout) view.findViewById(lotterys_sort3);
        mPl5showLayout = (LinearLayout) view.findViewById(lotterys_sort5);
        mQxcshowLayout = (LinearLayout) view.findViewById(lotterys_qx);
        mSfc14showLayout = (LinearLayout) view.findViewById(R.id.lotterys_sf14);
        mSfc9showLayout = (LinearLayout) view.findViewById(R.id.lotterys_sf9);
        mBqcshowLayout = (LinearLayout) view.findViewById(lotterys_bqc);
        mJqcshowLayout = (LinearLayout) view.findViewById(lotterys_jqc);

        mSrlLotterysContent.setColorSchemeColors(UIUtils.getColor(R.color.homeblue));
        mSrlLotterysContent.setOnRefreshListener(this);
        mListener = new MyListener();
        mOptionListener = new OptionListener();
        mSh11x5IndicatorLayout.setOnClickListener(mListener);
        mJzIndicatorLayout.setOnClickListener(mListener);
        mJlIndicatorLayout.setOnClickListener(mListener);
        mLottoIndicatorLayout.setOnClickListener(mListener);
        mPl3IndicatorLayout.setOnClickListener(mListener);
        mPl5IndicatorLayout.setOnClickListener(mListener);
        mQxIndicatorLayout.setOnClickListener(mListener);
        mSf14IndicatorLayout.setOnClickListener(mListener);
        mSf9IndicatorLayout.setOnClickListener(mListener);
        mBqcIndicatorLayout.setOnClickListener(mListener);
        mJqcIndicatorLayout.setOnClickListener(mListener);

        mSh11x5showLayout.setOnClickListener(mListener);
        mJinzushowLayout.setOnClickListener(mListener);
        mJinlanshowLayout.setOnClickListener(mListener);
        mLottoshowLayout.setOnClickListener(mListener);
        mPl3showLayout.setOnClickListener(mListener);
        mPl5showLayout.setOnClickListener(mListener);
        mSfc14showLayout.setOnClickListener(mListener);
        mQxcshowLayout.setOnClickListener(mListener);
        mSfc9showLayout.setOnClickListener(mListener);
        mBqcshowLayout.setOnClickListener(mListener);
        mJqcshowLayout.setOnClickListener(mListener);

        initOptionView(view);
        initTitleBar(view);
        beginLoad();
        mShowAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF
                , -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAnim.setDuration(500);

        //控件隐藏的动画
        HiddenAmin = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF
                , 0.0f, Animation.RELATIVE_TO_SELF, -1.0f);
        HiddenAmin.setDuration(500);

        return view;
    }

    private void initOptionView(View view) {
        mSh11x5Verture = (LinearLayout) view.findViewById(R.id.sh115_verture);
        mSh11x5Zoushi = (LinearLayout) view.findViewById(R.id.sh115_zoushi);
        mSh11x5Issue = (LinearLayout) view.findViewById(R.id.sh115_issuebonus);
        mSh11x5Jiangji = (LinearLayout) view.findViewById(R.id.sh115_jiangji);
        mSh11x5PlayRule = (LinearLayout) view.findViewById(R.id.sh115_playRule);
        mSh11x5Issueno = (TextView) view.findViewById(R.id.lotterys_sh11x5_issueno);
        mSh11x5Intro = (TextView) view.findViewById(R.id.lotterys_intro_sh115);

        mLottoVerture = (LinearLayout) view.findViewById(R.id.lotto_verture);
        mLottoZoushi = (LinearLayout) view.findViewById(R.id.lotto_zoushi);
        mLottoIssue = (LinearLayout) view.findViewById(R.id.lotto_issuebonus);
        mLottoJiangji = (LinearLayout) view.findViewById(R.id.lotto_jiangji);
        mLottoPlayRule = (LinearLayout) view.findViewById(R.id.lotto_playRule);
        mLottoIntro = (TextView) view.findViewById(R.id.lotterys_intro_lotto);

        mQxVerture = (LinearLayout) view.findViewById(R.id.qx_verture);
        mQxZoushi = (LinearLayout) view.findViewById(R.id.qx_zoushi);
        mQxIssue = (LinearLayout) view.findViewById(R.id.qx_issuebonus);
        mQxJiangji = (LinearLayout) view.findViewById(R.id.qx_jiangji);
        mQxPlayRule = (LinearLayout) view.findViewById(R.id.qx_playRule);
        mQxIntro = (TextView) view.findViewById(R.id.lotterys_intro_qx);

        mPl3Verture = (LinearLayout) view.findViewById(R.id.sort3_verture);
        mPl3Zoushi = (LinearLayout) view.findViewById(R.id.sort3_zoushi);
        mPl3Issue = (LinearLayout) view.findViewById(R.id.sort3_issuebonus);
        mPl3PlayRule = (LinearLayout) view.findViewById(R.id.sort3_playRule);
        mPl3Intro = (TextView) view.findViewById(R.id.lotterys_intro_sort3);

        mPl5Verture = (LinearLayout) view.findViewById(R.id.sort5_verture);
        mPl5Zoushi = (LinearLayout) view.findViewById(R.id.sort5_zoushi);
        mPl5Issue = (LinearLayout) view.findViewById(R.id.sort5_issuebonus);
        mPl5PlayRule = (LinearLayout) view.findViewById(R.id.sort5_playRule);
        mPl5Intro = (TextView) view.findViewById(R.id.lotterys_intro_sort5);

        mJzVerture = (LinearLayout) view.findViewById(R.id.jz_verture);
        mJzIssue = (LinearLayout) view.findViewById(R.id.jz_issuebonus);
        mJzPlayRule = (LinearLayout) view.findViewById(R.id.jz_playRule);
        mJzIntro = (TextView) view.findViewById(R.id.lotterys_intro_jz);

        mJlVerture = (LinearLayout) view.findViewById(R.id.jl_verture);
        mJlIssue = (LinearLayout) view.findViewById(R.id.jl_issuebonus);
        mJlPlayRule = (LinearLayout) view.findViewById(R.id.jl_playRule);
        mJlIntro = (TextView) view.findViewById(R.id.lotterys_intro_jl);

        mSf14Verture = (LinearLayout) view.findViewById(R.id.sf14_verture);
        mSf14Issue = (LinearLayout) view.findViewById(R.id.sf14_issuebonus);
        mSf14PlayRule = (LinearLayout) view.findViewById(R.id.sf14_playRule);
        mSf14Intro = (TextView) view.findViewById(R.id.lotterys_intro_sf14);

        mSf9Verture = (LinearLayout) view.findViewById(R.id.sf9_verture);
        mSf9Issue = (LinearLayout) view.findViewById(R.id.sf9_issuebonus);
        mSf9PlayRule = (LinearLayout) view.findViewById(R.id.sf9_playRule);
        mSf9Intro = (TextView) view.findViewById(R.id.lotterys_intro_sf9);

        mBqcVerture = (LinearLayout) view.findViewById(R.id.bqc_verture);
        mBqcIssue = (LinearLayout) view.findViewById(R.id.bqc_issuebonus);
        mBqcPlayRule = (LinearLayout) view.findViewById(R.id.bqc_playRule);
        mBqcIntro = (TextView) view.findViewById(R.id.lotterys_intro_bqc);

        mJqcVerture = (LinearLayout) view.findViewById(R.id.jqc_verture);
        mJqcIssue = (LinearLayout) view.findViewById(R.id.jqc_issuebonus);
        mJqcPlayRule = (LinearLayout) view.findViewById(R.id.jqc_playRule);
        mJqcIntro = (TextView) view.findViewById(R.id.lotterys_intro_jqc);

        mBqcPlayRule.setOnClickListener(mOptionListener);
        mBqcIssue.setOnClickListener(mOptionListener);
        mBqcVerture.setOnClickListener(mOptionListener);
        mSf9PlayRule.setOnClickListener(mOptionListener);
        mSf9Issue.setOnClickListener(mOptionListener);
        mSf9Verture.setOnClickListener(mOptionListener);
        mSf14PlayRule.setOnClickListener(mOptionListener);
        mSf14Issue.setOnClickListener(mOptionListener);
        mSf14Verture.setOnClickListener(mOptionListener);
        mJlPlayRule.setOnClickListener(mOptionListener);
        mJlIssue.setOnClickListener(mOptionListener);
        mJlVerture.setOnClickListener(mOptionListener);
        mJzPlayRule.setOnClickListener(mOptionListener);
        mJzIssue.setOnClickListener(mOptionListener);
        mJzVerture.setOnClickListener(mOptionListener);
        mPl5Issue.setOnClickListener(mOptionListener);
        mPl5Zoushi.setOnClickListener(mOptionListener);
        mPl5Verture.setOnClickListener(mOptionListener);
        mPl3PlayRule.setOnClickListener(mOptionListener);
        mPl3Issue.setOnClickListener(mOptionListener);
        mPl3Zoushi.setOnClickListener(mOptionListener);
        mPl3Verture.setOnClickListener(mOptionListener);
        mSh11x5Verture.setOnClickListener(mOptionListener);
        mSh11x5Zoushi.setOnClickListener(mOptionListener);
        mSh11x5Issue.setOnClickListener(mOptionListener);
        mSh11x5Jiangji.setOnClickListener(mOptionListener);
        mSh11x5PlayRule.setOnClickListener(mOptionListener);
        mLottoVerture.setOnClickListener(mOptionListener);
        mLottoZoushi.setOnClickListener(mOptionListener);
        mLottoJiangji.setOnClickListener(mOptionListener);
        mLottoJiangji.setOnClickListener(mOptionListener);
        mLottoPlayRule.setOnClickListener(mOptionListener);
        mQxVerture.setOnClickListener(mOptionListener);
        mQxZoushi.setOnClickListener(mOptionListener);
        mQxIssue.setOnClickListener(mOptionListener);
        mQxJiangji.setOnClickListener(mOptionListener);
        mQxPlayRule.setOnClickListener(mOptionListener);
        mJqcVerture.setOnClickListener(mOptionListener);
        mJqcIssue.setOnClickListener(mOptionListener);
        mJqcPlayRule.setOnClickListener(mOptionListener);
        mJqcIntro.setOnClickListener(mOptionListener);
        mPl5PlayRule.setOnClickListener(mOptionListener);
        mLottoIssue.setOnClickListener(mOptionListener);

        mSh11x5Indicator.setEnabled(false);
    }

    private void beginLoad() {
        if (mProtocol == null) {
            mProtocol = new LotterysRoomProtocol();
        }
        mProtocol.load(this, this);
    }

    @Override
    protected LoadingPager.LoadResult Load() {
        return check(0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSucess(Object o) {
        mSrlLotterysContent.setRefreshing(false);
        if (o != null) {
            LottersRoomBean loadData = (LottersRoomBean) o;
            if (loadData.getRet() == 100) {
                mData = loadData.getData().getList();
                initInfo(loadData.getData().getList());
                handlSh115Success(loadData.getData().getList());
            }
        }
    }

    private void handlSh115Success(List<LottersRoomBean.DataBean.ListBean> list) {
        LottersRoomBean.DataBean.ListBean mNumLotteryBean = null;
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
            mIsTimerRunning = true;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = BEGIN_TASK;
                    if (stopTime <= System.currentTimeMillis()) {
                        LogUtils.i("countdown time timeTask  stopTime <= System.currentTimeMillis():" + stopTime +
                                ":::" + System.currentTimeMillis());
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

    @Override
    public void onError() {
        mSrlLotterysContent.setRefreshing(false);
    }

    private void initInfo(List<LottersRoomBean.DataBean.ListBean> list) {
        for (int i = 0; i < list.size(); i++) {
            LottersRoomBean.DataBean.ListBean bean = list.get(i);
            if (bean.getLotId().equals("dlt")) {
                mLottoIntro.setText(bean.getIntro());
            } else if (bean.getLotId().equals("sh115")) {
                mSh11x5Intro.setText(bean.getIntro());
                mSh11x5Issueno.setText("第" + bean.getIssueNo() + "期");
            } else if (bean.getLotId().equals("pl3")) {
                mPl3Intro.setText(bean.getIntro());
            } else if (bean.getLotId().equals("pl5")) {
                mPl5Intro.setText(bean.getIntro());
            } else if (bean.getLotId().equals("qxc")) {
                mQxIntro.setText(bean.getIntro());
            } else if (bean.getLotId().equals("sf9")) {
                mSf9Intro.setText(bean.getIntro());
            } else if (bean.getLotId().equals("sf14")) {
                mSf14Intro.setText(bean.getIntro());
            } else if (bean.getLotId().equals("bqc")) {
                mBqcIntro.setText(bean.getIntro());
            } else if (bean.getLotId().equals("jqc")) {
                mJqcIntro.setText(bean.getIntro());
            } else if (bean.getLotId().equals("jczq")) {
                mJzIntro.setText(bean.getIntro());
            } else if (bean.getLotId().equals("jclq")) {
                mJlIntro.setText(bean.getIntro());
            }
        }
    }

    @Override
    public void onRefresh() {
        mProtocol.load(this,this);
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.lotterys_jinzu:
                    toggleExpand(mJzExpandLayout, mJzIndicator);
                    break;
                case R.id.lotterys_gaopingcai:
                    toggleExpand(mSh11x5ExpandLayout, mSh11x5Indicator);
                    break;
                case R.id.lotterys_jinlan:
                    toggleExpand(mJlExpandLayout, mJlIndicator);
                    break;
                case R.id.lotterys_lotto:
                    toggleExpand(mLottoExpandLayout, mLottoIndicator);
                    break;
                case R.id.lotterys_sort3:
                    toggleExpand(mPl3ExpandLayout, mPl3Indicator);
                    break;
                case R.id.lotterys_sort5:
                    toggleExpand(mPl5ExpandLayout, mPl5Indicator);
                    break;
                case R.id.lotterys_qx:
                    toggleExpand(mQxExpandLayout, mQxIndicator);
                    break;
                case R.id.lotterys_sf14:
                    toggleExpand(mSf14ExpandLayout, mSf14Indicator);
                    break;
                case R.id.lotterys_sf9:
                    toggleExpand(mSf9ExpandLayout, mSf9Indicator);
                    break;
                case R.id.lotterys_bqc:
                    toggleExpand(mBqcExpandLayout, mBqcIndicator);
                    break;
                case R.id.lotterys_jqc:
                    LogUtils.i("LotterysFragment jinqiucai");
                    toggleExpand(mJqcExpandLayout, mJqcIndicator);
                    break;
            }
        }
    }

    private class OptionListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.sh115_verture:
                    beginVerture(TAG_SH115);
                    break;
                case R.id.jz_verture:
                    beginVerture(TAG_JINZU);
                    break;
                case R.id.jl_verture:
                    beginVerture(TAG_JINLAN);
                    break;
                case R.id.lotto_verture:
                    beginVerture(TAG_LOTTO);
                    break;
                case R.id.sort3_verture:
                    beginVerture(TAG_PL3);
                    break;
                case R.id.sort5_verture:
                    beginVerture(TAG_PL5);
                    break;
                case R.id.qx_verture:
                    beginVerture(TAG_QIXIN);
                    break;
                case R.id.sf14_verture:
                    beginVerture(TAG_SF14);
                    break;
                case R.id.sf9_verture:
                    beginVerture(TAG_SF9);
                    break;
                case R.id.bqc_verture:
                    beginVerture(TAG_BQC);
                    break;
                case R.id.jqc_verture:
                    beginVerture(TAG_JQC);
                    break;

                case R.id.sh115_issuebonus:
                    beginIssue(TAG_SH115);
                    break;
                case R.id.jz_issuebonus:
                    beginIssue(TAG_JINZU);
                    break;
                case R.id.jl_issuebonus:
                    beginIssue(TAG_JINLAN);
                    break;
                case R.id.lotto_issuebonus:
                    beginIssue(TAG_LOTTO);
                    break;
                case R.id.sort3_issuebonus:
                    beginIssue(TAG_PL3);
                    break;
                case R.id.sort5_issuebonus:
                    beginIssue(TAG_PL5);
                    break;
                case R.id.qx_issuebonus:
                    beginIssue(TAG_QIXIN);
                    break;
                case R.id.sf14_issuebonus:
                    beginIssue(TAG_SF14);
                    break;
                case R.id.sf9_issuebonus:
                    beginIssue(TAG_SF9);
                    break;
                case R.id.bqc_issuebonus:
                    beginIssue(TAG_BQC);
                    break;
                case R.id.jqc_issuebonus:
                    beginIssue(TAG_JQC);
                    break;

                case R.id.qx_jiangji:
                    beginJiangji(TAG_QIXIN);
                    break;
                case R.id.lotto_jiangji:
                    beginJiangji(TAG_LOTTO);
                    break;
                case R.id.sh115_jiangji:
                    beginJiangji(TAG_SH115);
                    break;

                case R.id.sh115_playRule:
                    beginPlayRule(TAG_SH115);
                    break;
                case R.id.jz_playRule:
                    beginPlayRule(TAG_JINZU);
                    break;
                case R.id.jl_playRule:
                    beginPlayRule(TAG_JINLAN);
                    break;
                case R.id.lotto_playRule:
                    beginPlayRule(TAG_LOTTO);
                    break;
                case R.id.sf9_playRule:
                    beginPlayRule(TAG_SF9);
                    break;
                case R.id.sf14_playRule:
                    beginPlayRule(TAG_SF14);
                    break;
                case R.id.bqc_playRule:
                    beginPlayRule(TAG_BQC);
                    break;
                case R.id.sort5_playRule:
                    beginPlayRule(TAG_PL5);
                    break;
                case R.id.sort3_playRule:
                    beginPlayRule(TAG_PL3);
                    break;
                case R.id.qx_playRule:
                    beginPlayRule(TAG_QIXIN);
                    break;
                case R.id.jqc_playRule:
                    beginPlayRule(TAG_JQC);
                    break;


                case R.id.sh115_zoushi:
                    beginZoushi(TAG_SH115);
                    break;
                case R.id.lotto_zoushi:
                    beginZoushi(TAG_LOTTO);
                    break;
                case R.id.qx_zoushi:
                    beginZoushi(TAG_QIXIN);
                    break;
                case R.id.sort3_zoushi:
                    beginZoushi(TAG_PL3);
                    break;
                case R.id.sort5_zoushi:
                    beginZoushi(TAG_PL5);
                    break;
            }
        }
    }

    private void beginVerture(int tag) {
        Intent intent = null;
        switch (tag) {
            case TAG_SH115:
                intent = new Intent(getActivity(), SyxwActivity.class);
                startActivity(intent);
                break;
            case TAG_JINZU:
                intent = new Intent(getActivity(), JingcaiSoccerActivity.class);
                startActivity(intent);
                break;
            case TAG_JINLAN:
                intent = new Intent(getActivity(), JincaiBasketballActivity.class);
                startActivity(intent);
                break;
            case TAG_LOTTO:
                intent = new Intent(getActivity(), LottoActivity.class);
                startActivity(intent);
                break;
            case TAG_PL3:
                intent = new Intent(getActivity(), PL3Activity.class);
                startActivity(intent);
                break;
            case TAG_PL5:
                intent = new Intent(getActivity(), PL5Activity.class);
                startActivity(intent);
                break;
            case TAG_QIXIN:
                intent = new Intent(getActivity(), QXCActivity.class);
                startActivity(intent);
                break;
            case TAG_SF9:
                intent = new Intent(getActivity(), SfcR9Activity.class);
                startActivity(intent);
                break;
            case TAG_SF14:
                intent = new Intent(getActivity(), Sfc14Activity.class);
                startActivity(intent);
                break;
            case TAG_BQC:
                intent = new Intent(getActivity(), Bqc6Activity.class);
                startActivity(intent);
                break;
            case TAG_JQC:
                intent = new Intent(getActivity(), JqcActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void beginZoushi(int tag) {
        Intent intent = new Intent(getActivity(), ZoushiActivity.class);
        intent.putExtra("url", getURL(tag));
        intent.putExtra("title", getTitle(tag));
        startActivity(intent);
    }

    private void beginIssue(int tag) {
        Resources mResource = BaseApplication.getApplication().getResources();
        Intent intent = new Intent();
        intent.setClass(getActivity(), IssueBounsDetailActivity.class);
        Bundle bundle = new Bundle();
        switch (tag) {
            case TAG_SH115:
                bundle.putInt("detailType", DETAIL_SH115);
                bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_sh11x5));
                bundle.putSerializable("lotid", "sh115");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case TAG_JINZU:
                bundle.putInt("detailType", DETAIL_JINGCAI);
                bundle.putSerializable("issueNumber", null);
                bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_jcSoccer));
                bundle.putSerializable("lotid", "jczq");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case TAG_JINLAN:
                bundle.putInt("detailType", DETAIL_JINGCAI);
                bundle.putSerializable("issueNumber", null);
                bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_jcBasketball));
                bundle.putSerializable("lotid", "jclq");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case TAG_LOTTO:
                bundle.putInt("detailType", DETAIL_NUMBER);
                bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_lotto));
                bundle.putSerializable("lotid", "dlt");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case TAG_PL3:
                bundle.putInt("detailType", DETAIL_NUMBER);
                bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_pl3));
                bundle.putSerializable("lotid", "pl3");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case TAG_PL5:
                bundle.putInt("detailType", DETAIL_NUMBER);
                bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_pl5));
                bundle.putSerializable("lotid", "pl5");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case TAG_QIXIN:
                bundle.putInt("detailType", DETAIL_NUMBER);
                bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_qixing));
                bundle.putSerializable("lotid", "qxc");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case TAG_SF9:
                bundle.putInt("detailType", DETAIL_SOCCER);
                bundle.putSerializable("issueNumber", null);
                bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_sf));
                bundle.putSerializable("lotid", "sfc");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case TAG_SF14:
                bundle.putInt("detailType", DETAIL_SOCCER);
                bundle.putSerializable("issueNumber", null);
                bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_sf));
                bundle.putSerializable("lotid", "sfc");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case TAG_BQC:
                bundle.putInt("detailType", DETAIL_SOCCER);
                bundle.putSerializable("issueNumber", null);
                bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_bqc6));
                bundle.putSerializable("lotid", "bqc");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case TAG_JQC:
                bundle.putInt("detailType", DETAIL_SOCCER);
                bundle.putSerializable("issueNumber", null);
                bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_jqc4));
                bundle.putSerializable("lotid", "jqc");
                intent.putExtras(bundle);
                startActivity(intent);
                break;

        }
    }

    private void beginPlayRule(int tag) {
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", "玩法规则");
        int id = getRuleId(tag);
        bundle.putInt("id", id);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void beginJiangji(int tag) {
        Intent intent = new Intent(getActivity(), JiangjiActivity.class);
        Bundle bundle = new Bundle();
        switch (tag) {
            case TAG_SH115:
                bundle.putString("issueName", "上海11选5");
                break;
            case TAG_LOTTO:
                bundle.putString("issueName", "超级大乐透");
                break;
            case TAG_QIXIN:
                bundle.putString("issueName", "七星彩");
                break;
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private int getRuleId(int tag) {
        String lotId = "";
        if (tag == TAG_SH115) {
            lotId = "sh115";
        } else if (tag == TAG_JINZU) {
            lotId = "jczq";
        } else if (tag == TAG_JINLAN) {
            lotId = "jclq";
        } else if (tag == TAG_LOTTO) {
            lotId = "dlt";
        } else if (tag == TAG_PL3) {
            lotId = "pl3";
        } else if (tag == TAG_PL5) {
            lotId = "pl5";
        } else if (tag == TAG_QIXIN) {
            lotId = "qxc";
        } else if (tag == TAG_SF9) {
            lotId = "sf9";
        } else if (tag == TAG_SF14) {
            lotId = "sf14";
        } else if (tag == TAG_BQC) {
            lotId = "bqc";
        } else if (tag == TAG_JQC) {
            lotId = "jqc";
        }
        if (mData != null && !mData.isEmpty()) {
            for (int i = 0; i < mData.size(); i++) {
                if (lotId.equals(mData.get(i).getLotId())) {
                    return mData.get(i).getRuleId();
                }
            }
        }

        return 0;
    }

    private String getURL(int tag) {
        String lotId = "";
        if (tag == TAG_SH115) {
            lotId = "sh115";
        } else if (tag == TAG_JINZU) {
            lotId = "jczq";
        } else if (tag == TAG_JINLAN) {
            lotId = "jclq";
        } else if (tag == TAG_LOTTO) {
            lotId = "dlt";
        } else if (tag == TAG_PL3) {
            lotId = "pl3";
        } else if (tag == TAG_PL5) {
            lotId = "pl5";
        } else if (tag == TAG_QIXIN) {
            lotId = "qxc";
        } else if (tag == TAG_SF9) {
            lotId = "sf9";
        } else if (tag == TAG_SF14) {
            lotId = "sf14";
        } else if (tag == TAG_BQC) {
            lotId = "bqc";
        } else if (tag == TAG_JQC) {
            lotId = "jqc";
        }
        if (mData == null) {
            return "";
        }
        for (int i = 0; i < mData.size(); i++) {
            if (lotId.equals(mData.get(i).getLotId())) {
                LogUtils.i("mChartUrl:" + mData.get(i).getChartUrl());
                return mData.get(i).getChartUrl();
            }
        }
        return "";
    }

    private String getTitle(int tag) {
        String title = "";
        switch (tag) {
            case TAG_SH115:
                title = "上海11选5";
                break;
            case TAG_JINZU:
                title = "竞彩足球";
                break;
            case TAG_JINLAN:
                title = "竞彩篮球";
                break;
            case TAG_LOTTO:
                title = "超级大乐透";
                break;
            case TAG_PL3:
                title = "排列三";
                break;
            case TAG_PL5:
                title = "排列五";
                break;
            case TAG_QIXIN:
                title = "七星彩";
                break;
            case TAG_SF9:
                title = "胜负彩任9场";
                break;
            case TAG_SF14:
                title = "胜负彩14场";
                break;
            case TAG_BQC:
                title = "6场半全场";
                break;
            case TAG_JQC:
                title = "4场进球彩";
                break;
        }
        return title;
    }

    private void toggleExpand(LinearLayout expandLayout, ImageView indicator) {
        LogUtils.i("LotterysFragment toggleExpand");
        if (expandLayout.getVisibility() == View.GONE) {
            //            expandLayout.startAnimation(mShowAnim );
            expandLayout.setVisibility(View.VISIBLE);
            indicator.setEnabled(false);
        } else if (expandLayout.getVisibility() == View.VISIBLE) {
            //            expandLayout.startAnimation(HiddenAmin  );
            expandLayout.setVisibility(View.GONE);
            indicator.setEnabled(true);
        }
    }


    @Override
    protected String getTitle() {
        return "彩种大厅";
    }
}