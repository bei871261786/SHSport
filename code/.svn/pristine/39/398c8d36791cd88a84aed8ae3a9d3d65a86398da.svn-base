package shlottery.gov.cn.lotterycenter.ui;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.Base.BaseFragment;
import shlottery.gov.cn.lotterycenter.Base.FragmentFactory;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.callback.LoginCallBack;
import shlottery.gov.cn.lotterycenter.callback.LoginEvenbus;
import shlottery.gov.cn.lotterycenter.ui.view.NoScrollViewPager;
import shlottery.gov.cn.lotterycenter.utils.ClientUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;


/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-10-0010 下午 02:34
 * 描    述：app主activity
 * 修订历史：
 * ================================================
 */
public class MainActivity extends BaseActivity implements LoginCallBack {
    @BindView(R.id.titlebar_back_ll)
    LinearLayout titlebarBackLl;
    @BindView(R.id.titlebar_tv)
    TextView titlebarTv;
    @BindView(R.id.filtrate_tv)
    ImageView filtrateTv;
    @BindView(R.id.base_titleBar)
    RelativeLayout baseTitleBar;
    @BindView(R.id.homeViewpager)
    NoScrollViewPager homeViewpager;
    @BindView(R.id.mHomeSyRb)
    RadioButton mHomeSyRb;
    @BindView(R.id.mHomeZxRb)
    RadioButton mHomeZxRb;
    @BindView(R.id.mHomeCzRb)
    RadioButton mHomeCzRb;
    @BindView(R.id.mHomeSsRb)
    RadioButton mHomeSsRb;
    @BindView(R.id.mHomeUserRb)
    RadioButton mHomeUserRb;
    @BindView(R.id.mHomeRadioGroup)
    RadioGroup mHomeRadioGroup;
    @BindView(R.id.titlebar_indicator)
    ImageView titlebarIndicator;
    @BindView(R.id.titlebar_indicatordown)
    ImageView titlebarIndicatordown;
    @BindView(R.id.titlebar_title)
    LinearLayout titlebarTitle;
    @BindView(R.id.main_calendar)
    ImageView mainCalendar;
    @BindView(R.id.main_setting)
    ImageView mainSetting;
    private ViewPageAdapeter adapter;
    private int mCurrentItemPosition = 0;
    private String[] mTitles = new String[5];
    private String[] mTitleBarNames = new String[5];
    private int[] mTabIds = {R.id.mHomeSyRb, R.id.mHomeZxRb, R.id.mHomeCzRb, R.id.mHomeSsRb, R.id.mHomeUserRb};

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        titlebarBackLl.setVisibility(View.INVISIBLE);
        titlebarTv.setText("上海体彩");
        Resources res = this.getResources();
        mTitles = res.getStringArray(R.array.tab_names);
        mTitleBarNames = res.getStringArray(R.array.titlebar_names);
        //App每次启动到主界面后请求一次client注册,以更新diviceID 和对应的 App Version 信息
        ClientUtils.startGetClient();
        mHomeRadioGroup.check(R.id.mHomeSyRb);

      /*  FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BaseFragment mFragment = FragmentFactory.createFragment(0);
        fragmentTransaction.replace(R.id.mHomeContent, mFragment, "fragmentTag");        // fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/
        adapter = new ViewPageAdapeter(getSupportFragmentManager());
        homeViewpager.setAdapter(adapter);
        EventBus.getDefault().register(this);
//        homeViewpager.setOffscreenPageLimit(5);//预加载数量为5
        bindSelect();
    }

    //用于在fragment中控制fragment的切换,position是在Radiogroup中的位置
    public void toggleFragment(int position) {
        LogUtils.i("toggleFragment" + position);
        RadioButton rb = (RadioButton) mHomeRadioGroup.getChildAt(position);
        rb.setChecked(true);
    }

    /**
     * 将viewpager和下面的tab绑定在一起
     */
    private void bindSelect() {
        mHomeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.mHomeSyRb:
                        mCurrentItemPosition = 0;
                        break;
                    case R.id.mHomeZxRb:
                        mCurrentItemPosition = 1;
                        break;
                    case R.id.mHomeCzRb:
                        mCurrentItemPosition = 2;
//                        homeViewpager.setCurrentItem(2, false);

                        break;
                    case R.id.mHomeSsRb:
                        mCurrentItemPosition = 3;
//                        homeViewpager.setCurrentItem(3, false);
                        break;
                    case R.id.mHomeUserRb:
                        mCurrentItemPosition = 4;
                        adapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
                homeViewpager.setCurrentItem(mCurrentItemPosition, false);
            }
        });

        homeViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentItemPosition = position;
                mHomeRadioGroup.check(mTabIds[position]);
                BaseFragment fragment = FragmentFactory.createFragment(position, BaseApplication.ismLoginStatus());
                //如果切换到资讯，就刷新页面
               /* if (position == 1 || position == 3) {
                    fragment.refreshData();
                }*/

                titlebarTv.setText(mTitleBarNames[position]);
                fragment.show();
            }
        });
    }

    @Override
    protected void init() {
        BaseApplication.isMainActivityStart = true;
    }

    @Override
    public void toggleLoginFragment(boolean isLogin) {
        BaseApplication.setmLoginStatus(isLogin);
        LogUtils.i("MainActivityb toggleLoginFragment" + isLogin);
        adapter.notifyDataSetChanged();
        homeViewpager.setCurrentItem(4, false);
    }

    //处理切换登录状态指令
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void handleLogin(LoginEvenbus eventbus) {
        boolean isLogin = eventbus.isLogin();
        LogUtils.i("MainActivityb handleLogin" + isLogin);
        toggleLoginFragment(isLogin);
    }

    private class ViewPageAdapeter extends FragmentStatePagerAdapter {
        public ViewPageAdapeter(FragmentManager fm) {
            super(fm);
        }

        // 获取到title
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.createFragment(position, BaseApplication.ismLoginStatus());
        }

        @Override
        public int getItemPosition(Object object) {
            if (mCurrentItemPosition == 4) {
                return POSITION_NONE;
            }
            return super.getItemPosition(object);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();        //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再点一次,退出上海体彩", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.isMainActivityStart = false;
    }

    @Override
    protected String getBaidutitle() {
        return "";
    }

}


