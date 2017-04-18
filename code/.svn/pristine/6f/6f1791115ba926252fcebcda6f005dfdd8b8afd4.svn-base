package shlottery.gov.cn.lotterycenter.ui.nublottery.fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.Base.BaseFragment;
import shlottery.gov.cn.lotterycenter.Base.FragmentFactory;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.NumLotOrderBean;
import shlottery.gov.cn.lotterycenter.bean.NumLotteryBean;
import shlottery.gov.cn.lotterycenter.callback.Syx5EventBus;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.adapter.Syx5VpgAdapter;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingPager;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.ShakeUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-02-0002 15:13
 * 描    述：主要用作嵌套其他fragment 以便viewpager切换各种十一选五fragment
 * 修订历史：
 * ================================================
 */

@SuppressLint("ValidFragment")
public class Syx5ContentFragment extends BaseFragment {
    @BindView(R.id.syx5_nomal_rdb)
    RadioButton syx5NomalRdb;
    @BindView(R.id.syx5_dan_rdb)
    RadioButton syx5DanRdb;
    @BindView(R.id.syx5_rdg)
    RadioGroup syx5Rdg;
    @BindView(R.id.syx5_nomal_view)
    View syx5NomalView;
    @BindView(R.id.syx5_dan_view)
    View syx5DanView;
    @BindView(R.id.dan_indiactor_ll)
    LinearLayout danIndiactorLl;
    @BindView(R.id.syx5_issue_tv)
    TextView syx5IssueTv;
    @BindView(R.id.syx5_stoptime_tv)
    TextView syx5StoptimeTv;
    @BindView(R.id.syx5_stoptimetext_tv)
    TextView syx5StoptimetextTv;
    @BindView(R.id.syxw_vpg)
    ViewPager syxwVpg;
    private List<BaseFragment> baseFragments = new ArrayList<>();
    private ShakeUtils mShakeUtils;
    private String[] mTitles;
    private String[] mDanTitles;
    private MyOnclikListener mMyOnclikListener;
    private List<NumLotteryBean> numLotteryBeanList = new ArrayList<>();

    private int currentPosition = 0;
    private Timer timer = new Timer();
    private final int BEGIN_TASK = 5;//开始倒计时
    private final int BEGIN_REFRSH = 6;//开始刷新页面
    private long stopTime;
    private int count = 0;
    private int mListPosition;//由于list集合中有很多组数据,网络更新数据可能较慢,需要计算动态取值更新,mListPosition为所需数据的位置
    private List<NumLotOrderBean> mNumLotOrderBeans;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == BEGIN_TASK) {
                if (msg.arg1 == BEGIN_REFRSH) {
                    loadData();
                } else {
                    String time = DateUtils.getTimeExpend(System.currentTimeMillis(), stopTime);
                    syx5StoptimeTv.setText(time);
                }
            }

        }
    };

    @SuppressLint("ValidFragment")
    public Syx5ContentFragment(ShakeUtils mShakeUtils, int currentPosition, List<NumLotOrderBean> mNumLotOrderBeans) {
        this.mShakeUtils = mShakeUtils;
        this.currentPosition = currentPosition;
        this.mNumLotOrderBeans = mNumLotOrderBeans;
    }

    @Override
    protected View createLoadedView() {
        View mView = UIUtils.inflate(R.layout.syx5_content_fragment);
        ButterKnife.bind(this, mView);
        FragmentManager fm = getChildFragmentManager();
        initBaseFragments(baseFragments, currentPosition);
        syxwVpg.setAdapter(new Syx5VpgAdapter(fm, baseFragments));
        if (currentPosition == Configs.SYX5_QY || currentPosition == Configs.SYX5_Q2ZHIX || currentPosition == Configs.SYX5_Q3ZHIX || currentPosition == Configs.SYX5_RX8) {
            syx5Rdg.setVisibility(View.GONE);
            danIndiactorLl.setVisibility(View.GONE);
        } else {
            syx5Rdg.setVisibility(View.VISIBLE);
            danIndiactorLl.setVisibility(View.VISIBLE);
        }//根据所选的彩种选择性的隐藏普通胆拖导航
        mMyOnclikListener = new MyOnclikListener();
        syx5NomalRdb.setOnClickListener(mMyOnclikListener);
        syx5DanRdb.setOnClickListener(mMyOnclikListener);
        syxwVpg.setOnPageChangeListener(mMyOnclikListener);
        loadData();
        return mView;
    }

    //加载数据
    private void loadData() {
        String lotid = "sh115";
        OkGo.get(UrlManager.getSaleIssueUrl(getActivity(), lotid)).cacheMode(CacheMode.NO_CACHE).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Logger.e(s + "请求成功");
                Gson gson = new Gson();
                NumLotteryBean mNumLotteryBean = gson.fromJson(s, NumLotteryBean.class);
                if (null != mNumLotteryBean) {
                    Logger.e(mNumLotteryBean.getData().getLotName() + "lotnmae");
                    mListPosition = -1;
                    for (int i = 0; i < mNumLotteryBean.getData().getIssueList().size(); i++) {
                        long currentTime = System.currentTimeMillis();//系统当前时间
                        long startTime = (long) mNumLotteryBean.getData().getIssueList().get(i).getStartTime();
                        long stime = (long) mNumLotteryBean.getData().getIssueList().get(i).getStopTime();
                        Logger.e(currentTime + "----系统当前时间" + startTime + "----开始时间" + stime + "---结束时间");

                        if (currentTime < (stime * 1000)) {
                            mListPosition = i;
                            Logger.e(currentTime + "----系统当前时间" + startTime + "----开始时间" + stime + "---结束时间");
                            Logger.e(mListPosition + "当前位置mListPosition");
                            break;
                        }
                    }
                    if (mListPosition >= 0) {
                        stopTime = ((long) mNumLotteryBean.getData().getIssueList().get(mListPosition).getStopTime()) * 1000;
                        String stime = DateUtils.getTimeExpend(System.currentTimeMillis(), stopTime);
                        String issue = "第" + mNumLotteryBean.getData().getIssueList().get(mListPosition).getIssueNo() + "期";
                        syx5StoptimeTv.setText(stime);
                        syx5IssueTv.setText(issue);
                        startTimeTask();
                    }
                    Syx5EventBus syx5EventBus = new Syx5EventBus(mNumLotteryBean);
                    syx5EventBus.setSelectPosition(mListPosition);//传递当前期号所在位置
                    EventBus.getDefault().postSticky(syx5EventBus);
                }
            }


            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                UIUtils.showToastSafe("网络异常,请检查网络");
                Logger.e("网络异常,请检查网络");
            }
        });

    }

    private void startTimeTask() {
        // 更新 倒计时以及期号
        if (null != timer) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = BEGIN_TASK;
                    if (stopTime <= System.currentTimeMillis()) {
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

    private List<BaseFragment> initBaseFragments(List<BaseFragment> baseFragments, int position) {
        if (null == baseFragments) {
            baseFragments = new ArrayList<>();
        } else {
            baseFragments.clear();
        }
        switch (position) {
            case 0:
                baseFragments.add(FragmentFactory.createSyx5Fragment(0, mShakeUtils, mNumLotOrderBeans));
                baseFragments.add(FragmentFactory.createSyx5DanFragment(0, mShakeUtils, mNumLotOrderBeans));
                break;
            case 1:
                baseFragments.add(FragmentFactory.createSyx5Fragment(1, mShakeUtils, mNumLotOrderBeans));
                baseFragments.add(FragmentFactory.createSyx5DanFragment(1, mShakeUtils, mNumLotOrderBeans));
                break;
            case 2:
                baseFragments.add(FragmentFactory.createSyx5Fragment(2, mShakeUtils, mNumLotOrderBeans));
                baseFragments.add(FragmentFactory.createSyx5DanFragment(2, mShakeUtils, mNumLotOrderBeans));
                break;
            case 3:
                baseFragments.add(FragmentFactory.createSyx5Fragment(3, mShakeUtils, mNumLotOrderBeans));
                baseFragments.add(FragmentFactory.createSyx5DanFragment(3, mShakeUtils, mNumLotOrderBeans));
                break;
            case 4:
                baseFragments.add(FragmentFactory.createSyx5Fragment(4, mShakeUtils, mNumLotOrderBeans));
                baseFragments.add(FragmentFactory.createSyx5DanFragment(4, mShakeUtils, mNumLotOrderBeans));
                break;
            case 5:
                baseFragments.add(FragmentFactory.createSyx5Fragment(5, mShakeUtils, mNumLotOrderBeans));
                baseFragments.add(FragmentFactory.createSyx5DanFragment(5, mShakeUtils, mNumLotOrderBeans));
                break;
            case 6:
                baseFragments.add(FragmentFactory.createSyx5Fragment(6, mShakeUtils, mNumLotOrderBeans));
//                baseFragments.add(FragmentFactory.createSyx5DanFragment(6, mShakeUtils,mNumLotOrderBeans));
                break;
            case 7:
                baseFragments.add(FragmentFactory.createSyx5Fragment(7, mShakeUtils, mNumLotOrderBeans));
                break;
            case 8:
                baseFragments.add(FragmentFactory.createSyx5Fragment(8, mShakeUtils, mNumLotOrderBeans));
                break;
            case 9:
                baseFragments.add(FragmentFactory.createSyx5Fragment(9, mShakeUtils, mNumLotOrderBeans));
                baseFragments.add(FragmentFactory.createSyx5DanFragment(7, mShakeUtils, mNumLotOrderBeans));
                break;
            case 10:
                baseFragments.add(FragmentFactory.createSyx5Fragment(10, mShakeUtils, mNumLotOrderBeans));
                break;
            case 11:
                baseFragments.add(FragmentFactory.createSyx5Fragment(11, mShakeUtils, mNumLotOrderBeans));
                baseFragments.add(FragmentFactory.createSyx5DanFragment(8, mShakeUtils, mNumLotOrderBeans));
                break;
            default:
                break;
        }
        return baseFragments;
    }


    class MyOnclikListener implements View.OnClickListener, ViewPager.OnPageChangeListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.syx5_nomal_rdb:
                    syx5Rdg.check(R.id.syx5_nomal_rdb);
                    syx5NomalView.setBackgroundResource(R.color.homeblue);
                    syx5DanView.setBackgroundResource(R.color.white);
                    syxwVpg.setCurrentItem(0);
                    break;
                case R.id.syx5_dan_rdb:
                    syx5Rdg.check(R.id.syx5_dan_rdb);
                    syx5DanView.setBackgroundResource(R.color.homeblue);
                    syx5NomalView.setBackgroundResource(R.color.white);
                    syxwVpg.setCurrentItem(1);
                    break;
                default:
                    break;

            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                syx5Rdg.check(R.id.syx5_nomal_rdb);
                syx5NomalView.setBackgroundResource(R.color.homeblue);
                syx5DanView.setBackgroundResource(R.color.white);
            } else {
                syx5Rdg.check(R.id.syx5_dan_rdb);
                syx5DanView.setBackgroundResource(R.color.homeblue);
                syx5NomalView.setBackgroundResource(R.color.white);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    protected LoadingPager.LoadResult Load() {
        return check(0);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (timer == null) {
            timer = new Timer();
            startTimeTask();
        }
    }
    @Override
    protected String getTitle() {
        return "";
    }
}
