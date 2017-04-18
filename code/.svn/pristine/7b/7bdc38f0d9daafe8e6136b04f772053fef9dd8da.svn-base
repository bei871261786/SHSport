package shlottery.gov.cn.lotterycenter.ui.nublottery.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.Base.BaseFragment;
import shlottery.gov.cn.lotterycenter.Base.FragmentFactory;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.NumLotOrderBean;
import shlottery.gov.cn.lotterycenter.callback.Pl3Evenbus;
import shlottery.gov.cn.lotterycenter.utils.ShakeUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-14-0014 09:43
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class PL3Activity extends BaseActivity {

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
    @BindView(R.id.pl3_zhixuan_rb)
    RadioButton pl3ZhixuanRb;
    @BindView(R.id.pl3_zu3_rb)
    RadioButton pl3Zu3Rb;
    @BindView(R.id.pl3_zu6_rb)
    RadioButton pl3Zu6Rb;
    @BindView(R.id.pl3_rg)
    RadioGroup pl3Rg;
    @BindView(R.id.pl3_fragment)
    FrameLayout pl3Fragment;
    @BindView(R.id.activity_pl3)
    LinearLayout activityPl3;
    @BindView(R.id.titlebar_indicatordown)
    ImageView titlebarIndicatordown;
    @BindView(R.id.main_calendar)
    ImageView mainCalendar;
    private MyOnclickListener mMyOnclickListener;
    private BaseFragment mFragment;
    private ShakeUtils mShakeUtils;
    private boolean mFlag = false;
    List<NumLotOrderBean> mNumLotOrderBeans = new ArrayList<>();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_pl3);
        EventBus.getDefault().register(this);
        mShakeUtils = new ShakeUtils(this);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        mFragment = FragmentFactory.createPL3Fragment(5, mShakeUtils, mNumLotOrderBeans);
        fragmentTransaction.add(R.id.pl3_fragment, mFragment).commit();
        ButterKnife.bind(this);
        titlebarTv.setText("排列三直选");
        titlebarIndicator.setVisibility(View.VISIBLE);
        changeTitleState(mFlag);
        mMyOnclickListener = new MyOnclickListener();
        titlebarBackLl.setOnClickListener(mMyOnclickListener);
        pl3Rg.check(R.id.pl3_zhixuan_rb);
        pl3Rg.setOnCheckedChangeListener(mMyOnclickListener);
        titlebarTitle.setOnClickListener(mMyOnclickListener);
        pl3ZhixuanRb.setOnClickListener(mMyOnclickListener);
        pl3Zu3Rb.setOnClickListener(mMyOnclickListener);
        pl3Zu6Rb.setOnClickListener(mMyOnclickListener);

    }

    //传回的数据继续传给其他frament用
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(Pl3Evenbus messageEvent) {
        mNumLotOrderBeans = messageEvent.getmNumLotOrderBeans();
    }

    @Override
    protected void init() {

    }


    private class MyOnclickListener implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.titlebar_back_ll:
                    finish();
                    FragmentFactory.removePl3Fragments();
                    break;
                case R.id.titlebar_title:
                    changeTitleState(mFlag);
                    break;
                case R.id.pl3_zhixuan_rb:
                    titlebarTv.setText("排列三直选");
                    changeTitleState(false);
                    break;
                case R.id.pl3_zu3_rb:
                    titlebarTv.setText("排列三组三");
                    changeTitleState(false);
                    break;
                case R.id.pl3_zu6_rb:
                    titlebarTv.setText("排列三组六");
                    changeTitleState(false);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            switch (checkedId) {
                case R.id.pl3_zhixuan_rb:
                    mFragment = FragmentFactory.createPL3Fragment(5, mShakeUtils, mNumLotOrderBeans);
                    ft.replace(R.id.pl3_fragment, mFragment, "zhixuan");
                    break;
                case R.id.pl3_zu3_rb:
                    mFragment = FragmentFactory.createPL3Fragment(6, mShakeUtils, mNumLotOrderBeans);
                    ft.replace(R.id.pl3_fragment, mFragment, "zu3");
                    break;
                case R.id.pl3_zu6_rb:
                    mFragment = FragmentFactory.createPL3Fragment(7, mShakeUtils, mNumLotOrderBeans);
                    ft.replace(R.id.pl3_fragment, mFragment, "zu6");
                    break;
                default:
                    break;
            }
            ft.commit();
        }
    }

    private void changeTitleState(boolean flag) {
        if (flag) {
            pl3Rg.setVisibility(View.VISIBLE);
            titlebarIndicatordown.setVisibility(View.GONE);
            titlebarIndicator.setVisibility(View.VISIBLE);
            mFlag = false;
        } else {
            pl3Rg.setVisibility(View.GONE);
            titlebarIndicatordown.setVisibility(View.VISIBLE);
            titlebarIndicator.setVisibility(View.GONE);
            mFlag = true;
        }
    }

    //重写返回键,清除排列三的缓存,避免下次进入记录住了上次状态
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentFactory.removePl3Fragments();
    }

    @Override
    protected void onPause() {
        mShakeUtils.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mShakeUtils.onResume();
        super.onResume();
    }
    @Override
    protected String getBaidutitle() {
        return "";
    }
}
