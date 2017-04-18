package shlottery.gov.cn.lotterycenter.ui.nublottery.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

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
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.TitleGdvAdapter;
import shlottery.gov.cn.lotterycenter.ui.nublottery.fragment.Syx5ContentFragment;
import shlottery.gov.cn.lotterycenter.ui.view.GridViewWithoutScroll;
import shlottery.gov.cn.lotterycenter.utils.ShakeUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-26-0026 09:51
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class SyxwActivity extends BaseActivity {
    @BindView(R.id.titlebar_back_ll)
    LinearLayout titlebarBackLl;
    @BindView(R.id.titlebar_tv)
    TextView titlebarTv;
    @BindView(R.id.titlebar_indicator)
    ImageView titlebarIndicator;
    @BindView(R.id.titlebar_indicatordown)
    ImageView titlebarIndicatordown;
    @BindView(R.id.titlebar_title)
    LinearLayout titlebarTitle;
    @BindView(R.id.filtrate_tv)
    ImageView filtrateTv;
    @BindView(R.id.main_calendar)
    ImageView mainCalendar;
    @BindView(R.id.base_titleBar)
    RelativeLayout baseTitleBar;
    @BindView(R.id.syx5_gridview)
    GridViewWithoutScroll syx5Gridview;
    @BindView(R.id.syxw_fragment)
    FrameLayout syxwFragment;
    @BindView(R.id.activity_11x5)
    LinearLayout activity11x5;
    private MyOnclikListener mMyOnclikListener;

    private PopupWindow mPopuWindow;

    private TitleGdvAdapter mTitleGdvAdapter;
    private TitleGdvAdapter mDanTuoTitleGdvAdapter;
    private BaseFragment mFragment;
    private ShakeUtils mShakeUtils;
    private String[] mTitles;
    private String[] mDanTitles;
    private int currentPosition = 0;
    private int currentDanPosition = 0;
    private boolean mFlag = false;
    private boolean isDan = false;
    public List<NumLotOrderBean> mNumLotOrderBeans = new ArrayList<>();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_11x5);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mShakeUtils = new ShakeUtils(this);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
//        mFragment = FragmentFactory.createSyx5Fragment(0, mShakeUtils);
        mFragment = new Syx5ContentFragment(mShakeUtils, 0, mNumLotOrderBeans);
        fragmentTransaction.replace(R.id.syxw_fragment, mFragment).commit();
        mTitles = getResources().getStringArray(R.array.syx5_title);
        mDanTitles = getResources().getStringArray(R.array.syx5dan_title);
        titlebarTv.setText(mTitles[currentPosition]);
        changeTitleState(mFlag);
        mTitleGdvAdapter = new TitleGdvAdapter(SyxwActivity.this, false);
        syx5Gridview.setAdapter(mTitleGdvAdapter);
        mMyOnclikListener = new MyOnclikListener();
        titlebarBackLl.setOnClickListener(mMyOnclikListener);
        titlebarTitle.setOnClickListener(mMyOnclikListener);
        syx5Gridview.setOnItemClickListener(mMyOnclikListener);
    }

    @Override
    protected void init() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(Pl3Evenbus messageEvent) {
        mNumLotOrderBeans = messageEvent.getmNumLotOrderBeans();
        Logger.e(mNumLotOrderBeans.size()+"十一选五Activity mNumLotOrderBeans 的长度");
    }

    private void changeTitleState(boolean flag) {
        if (flag) {//flag位true时展开,
            titlebarIndicatordown.setVisibility(View.GONE);
            titlebarIndicator.setVisibility(View.VISIBLE);
            syx5Gridview.setVisibility(View.VISIBLE);
            mFlag = false;
        } else {
            titlebarIndicatordown.setVisibility(View.VISIBLE);
            titlebarIndicator.setVisibility(View.GONE);
            syx5Gridview.setVisibility(View.GONE);
            mFlag = true;
        }
    }


    class MyOnclikListener implements View.OnClickListener, AdapterView.OnItemClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.titlebar_back_ll:
                    finish();
                    break;
                case R.id.titlebar_title:
                    changeTitleState(mFlag);

//                    View mContentView = LayoutInflater.from(SyxwActivity.this).inflate(R.layout.popuwindow_11x52, null);
//                    GridViewWithoutScroll mGridViewWithoutScroll = (GridViewWithoutScroll) mContentView.findViewById(R.id.syx5_gridview);
//                    GridViewWithoutScroll mGridViewWithoutScrollDantuo = (GridViewWithoutScroll) mContentView.findViewById(R.id.syx5dantuo_gridview);
//                    if (null == mTitleGdvAdapter) {
//                        mTitleGdvAdapter = new TitleGdvAdapter(SyxwActivity.this, false);
//                        mDanTuoTitleGdvAdapter = new TitleGdvAdapter(SyxwActivity.this, true);
//                    }
//                    if (null == mPopuWindow) {
//                        mPopuWindow = new PopupWindow(mContentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
//                    }
//                    mGridViewWithoutScroll.setAdapter(mTitleGdvAdapter);
//                    mGridViewWithoutScrollDantuo.setAdapter(mDanTuoTitleGdvAdapter);
//                    mPopuWindow.setFocusable(true);
//                    mPopuWindow.setOutsideTouchable(false);
//                    mPopuWindow.showAsDropDown(findViewById(R.id.base_titleBar));
//                    mGridViewWithoutScroll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            mTitleGdvAdapter.setSelectedPosition(position);
//                            mTitleGdvAdapter.notifyDataSetChanged();
//                            mPopuWindow.dismiss();
//                            FragmentManager fm = getSupportFragmentManager();
//                            FragmentTransaction ft = fm.beginTransaction();
//                            mFragment = FragmentFactory.createSyx5Fragment(position, mShakeUtils);
//                            ft.replace(R.id.syxw_fragment, mFragment);
//                            ft.commit();
//                            titlebarTv.setText(mTitles[position]);
//                            currentPosition = position;
//                        }
//                    });
//
//                    //胆拖点击跳转
//                    mGridViewWithoutScrollDantuo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            mDanTuoTitleGdvAdapter.setSelectedPosition(position);
//                            mDanTuoTitleGdvAdapter.notifyDataSetChanged();
//                            mPopuWindow.dismiss();
//                            FragmentManager fm = getSupportFragmentManager();
//                            FragmentTransaction ft = fm.beginTransaction();
//                            mFragment = FragmentFactory.createSyx5DanFragment(position);
//                            ft.replace(R.id.syxw_fragment, mFragment);
//                            ft.commit();
//                            titlebarTv.setText(mDanTitles[position] + "胆拖");
//                            currentDanPosition = position;
//                        }
//                    });
                    break;
               /* case R.id.syx5_nomal_rdb:
                    syx5Rdg.check(R.id.syx5_nomal_rdb);
                    syx5NomalView.setBackgroundResource(R.color.homeblue);
                    syx5DanView.setBackgroundResource(R.color.white);
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    mFragment = FragmentFactory.createSyx5Fragment(currentPosition, mShakeUtils);
                    ft.replace(R.id.syxw_fragment, mFragment);
                    ft.commit();
                    break;
                case R.id.syx5_dan_rdb:
                    syx5Rdg.check(R.id.syx5_dan_rdb);
                    syx5DanView.setBackgroundResource(R.color.homeblue);
                    syx5NomalView.setBackgroundResource(R.color.white);

                    FragmentManager fm1 = getSupportFragmentManager();
                    FragmentTransaction ft1 = fm1.beginTransaction();
                    if (currentPosition == Configs.SYX5_Q2ZUX) {
                        mFragment = FragmentFactory.createSyx5DanFragment(7,mShakeUtils);
                    } else if (currentPosition == Configs.SYX5_Q3ZUX) {
                        mFragment = FragmentFactory.createSyx5DanFragment(8,mShakeUtils);
                    } else {
                        mFragment = FragmentFactory.createSyx5DanFragment(currentPosition,mShakeUtils);
                    }

                    ft1.replace(R.id.syxw_fragment, mFragment);
                    ft1.commit();
                    break;*/
                default:
                    break;

            }
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mTitleGdvAdapter.setSelectedPosition(position);
            mTitleGdvAdapter.notifyDataSetChanged();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
//            mFragment = FragmentFactory.createSyx5Fragment(position, mShakeUtils);
            titlebarTv.setText(mTitles[position]);
            currentPosition = position;
            mFragment = new Syx5ContentFragment(mShakeUtils, currentPosition, mNumLotOrderBeans);
            ft.replace(R.id.syxw_fragment, mFragment);
            ft.commit();
            changeTitleState(mFlag);
          /*  syx5Rdg.check(R.id.syx5_nomal_rdb);
            syx5NomalView.setBackgroundResource(R.color.homeblue);
            syx5DanView.setBackgroundResource(R.color.white);
            if (currentPosition == Configs.SYX5_QY || currentPosition == Configs.SYX5_Q2ZHIX || currentPosition == Configs.SYX5_Q3ZHIX) {
                syx5Rdg.setVisibility(View.GONE);
                danIndiactorLl.setVisibility(View.GONE);
            } else {
                syx5Rdg.setVisibility(View.VISIBLE);
                danIndiactorLl.setVisibility(View.VISIBLE);
            }*/
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FragmentFactory.removeSyx5Fragments();
        FragmentFactory.removeSyx5DanFragments();
    }
    @Override
    protected String getBaidutitle() {
        return "11选5";
    }
}
