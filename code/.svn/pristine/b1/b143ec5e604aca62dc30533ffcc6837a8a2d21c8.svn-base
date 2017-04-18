package shlottery.gov.cn.lotterycenter.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import com.lzy.okgo.request.BaseRequest;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.Base.BaseMainFragment;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.HomeBean;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.MainActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.HuodongActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.IssueBounsDetailActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.IssueBountsActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.KefuActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.LotterysHelperActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.NewsDetailActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.RecommendActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.Rmd_famousDetailActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.SuperGListlActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.WangdianActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.WebViewActivity;
import shlottery.gov.cn.lotterycenter.ui.adapter.HomeGridViewAdapter;
import shlottery.gov.cn.lotterycenter.ui.adapter.HomeKJPagerAdapter;
import shlottery.gov.cn.lotterycenter.ui.adapter.HomeTopPagerAdapter;
import shlottery.gov.cn.lotterycenter.ui.adapter.HomeZXLstAdapter;
import shlottery.gov.cn.lotterycenter.ui.calculatebouns.CalculateActivity;
import shlottery.gov.cn.lotterycenter.ui.view.CircleImageView;
import shlottery.gov.cn.lotterycenter.ui.view.GridViewWithoutScroll;
import shlottery.gov.cn.lotterycenter.ui.view.ListViewForScrollView;
import shlottery.gov.cn.lotterycenter.ui.view.MarqueeView.MarqueeView;
import shlottery.gov.cn.lotterycenter.ui.view.MarqueeView.MarqueeViewAdapter;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingPager;
import shlottery.gov.cn.lotterycenter.utils.ClientUtils;
import shlottery.gov.cn.lotterycenter.utils.HomeHandler;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

import static shlottery.gov.cn.lotterycenter.Base.Configure.DETAIL_JINGCAI;
import static shlottery.gov.cn.lotterycenter.Base.Configure.DETAIL_NUMBER;
import static shlottery.gov.cn.lotterycenter.Base.Configure.DETAIL_SH115;
import static shlottery.gov.cn.lotterycenter.Base.Configure.DETAIL_SOCCER;
import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JCDX;
import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JLRSF;
import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JLSF;
import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JZRSPF;
import static shlottery.gov.cn.lotterycenter.entity.Configs.ACTIVITYTYPE_JZSPF;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-12-0012 上午 11:53
 * 描    述：app的主页的homeframgent
 * 修订历史：
 * ================================================
 */

public class HomeFragment extends BaseMainFragment implements MarqueeView.OnItemClickListener, AdapterView.OnItemClickListener {
    @BindView(R.id.titlebar_tv)
    TextView titlebarTv;
    @BindView(R.id.titlebar_indicator)
    ImageView titlebarIndicator;
    @BindView(R.id.titlebar_indicatordown)
    ImageView titlebarIndicatordown;
    @BindView(R.id.titlebar_title)
    LinearLayout titlebarTitle;
    @BindView(R.id.base_titleBar)
    RelativeLayout baseTitleBar;
    @BindView(R.id.home_banner)
    public ViewPager homeBanner;
    @BindView(R.id.viewpagertitle_tv)
    TextView viewpagertitleTv;
    @BindView(R.id.home_indicator_ll)
    LinearLayout homeIndicatorLl;
    @BindView(R.id.home_laba_im)
    ImageView homeLabaIm;
    @BindView(R.id.homenotice_tv)
    MarqueeView homenoticeTv;
    @BindView(R.id.home_gridview)
    GridViewWithoutScroll homeGridview;
    @BindView(R.id.kaijiang_vpg)
    ViewPager kaijiangVpg;
    @BindView(R.id.mingjia1)
    CircleImageView mingjia1;
    @BindView(R.id.mingjia1_tv)
    TextView mingjia1Tv;
    @BindView(R.id.shenglvtitle_tv)
    TextView shenglvtitleTv;
    @BindView(R.id.home_summary_tv)
    TextView homeSummaryTv;
    @BindView(R.id.mingjia2)
    CircleImageView mingjia2;
    @BindView(R.id.mingjia2_tv)
    TextView mingjia2Tv;
    @BindView(R.id.shenglvtitle2_tv)
    TextView shenglvtitle2Tv;
    @BindView(R.id.tuijian_im)
    ImageView tuijianIm;
    @BindView(R.id.tuijian_name_tv)
    TextView tuijianNameTv;
    @BindView(R.id.tuijianfangan_ll)
    LinearLayout tuijianfanganLl;
    @BindView(R.id.guoguan_fs_tv)
    TextView guoguanFsTv;
    @BindView(R.id.home_guoguanfangshi_ll)
    LinearLayout homeGuoguanfangshiLl;
    @BindView(R.id.home_touzhubeishu_ll)
    LinearLayout homeTouzhubeishuLl;
    @BindView(R.id.shouyi_jine_tv)
    TextView shouyiJineTv;
    @BindView(R.id.home_jiangjinshouyi_ll)
    LinearLayout homeJiangjinshouyiLl;
    @BindView(R.id.home_newsLayout)
    RelativeLayout homeNewsLayout;
    @BindView(R.id.zixun_lst)
    ListViewForScrollView zixunLst;
    @BindView(R.id.homecontent_ll)
    LinearLayout homecontentLl;
    @BindView(R.id.home_scr)
    ScrollView homeScr;
    @BindView(R.id.fangshi_tv)
    TextView fangshiTv;
    @BindView(R.id.shouyi_title_tv)
    TextView shouyiTitleTv;
    @BindView(R.id.zhuanjia1_rl)
    RelativeLayout zhuanjia1Rl;
    @BindView(R.id.zhuanjia2_rl)
    RelativeLayout zhuanjia2Rl;
    @BindView(R.id.home_tuijian_rl)
    LinearLayout hometuijianRl;
    @BindView(R.id.home_refresh_srl)
    SwipeRefreshLayout homerefreshSrl;
    private List<String> mList = new ArrayList();
    private List<String> mNotices = new ArrayList();//通知栏
    private List<String> mVpNotices = new ArrayList();//viewpager通知栏
    private List<String> mResults = new ArrayList();
    private List<String> mPictureUrl = new ArrayList();
    private HomeGridViewAdapter mGridviewAdapter;
    private HomeTopPagerAdapter mHomeTopPagerAdapter;
    private HomeKJPagerAdapter mHomeKJPagerAdapter;
    //获取 fragment 依赖的 Activity，方便使用 Context
    private Activity mAct;
    private static final int UPTATE_VIEWPAGER = 0;
    private static final int MSG_KEEP_SILENT = 1;
    private static final int LINEPARAMS = 0;
    private static final int RELAPARAMS = 1;
    //设置当前 第几个图片 被选中
    private int autoCurrIndex = 0;
    private ImageView[] mBottomImages;//底部只是当前页面的小圆点
    private TextView[] mBottomTextViews;//开奖数据集合
    private Timer timer = new Timer(); //为了方便取消定时轮播，将 Timer 设为全局
    private MyListener mListener;
    private HomeBean homeBean;//主页的数据
    private HomeZXLstAdapter homeZXLstAdapter;
    public HomeHandler homeHandler = new HomeHandler(
            new WeakReference<HomeFragment>(this));

    //定时轮播图片，需要在主线程里面修改 UI
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPTATE_VIEWPAGER:
                    if (msg.arg1 != 0) {
                        homeBanner.setCurrentItem(msg.arg1 + mPictureUrl.size() * 200, false);
                    } else {
                        //false 当从末页调到首页是，不显示翻页动画效果，
                        homeBanner.setCurrentItem(msg.arg1 + mPictureUrl.size() * 200, false);
                    }
                    break;
            }
        }
    };
    private static final int DELAY_TIME = 3000;
    public static final int SCROLL_WHAT = 0;
    private List<View> mViews = new ArrayList<>();
    private int currentPosition;//轮播图的选中position
    private int kjcurrentPosition;//开奖viewpagerposition
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    @Override
    protected void initTitlebar() {
        mFiltrate.setVisibility(View.GONE);
        mCalendar.setVisibility(View.GONE);
        mSetting.setVisibility(View.GONE);
    }

    @Override
    protected View createLoadedView() {
        mListener = new MyListener();
        View mView = UIUtils.inflate(R.layout.fragment_home);
        ButterKnife.bind(this, mView);
        mAct = getActivity();
        String[] mTitle = BaseApplication.getApplication().getResources().getStringArray(R.array.homegridviewitem);//gridview的标题栏
        mGridviewAdapter = new HomeGridViewAdapter(mAct, mTitle);
        homerefreshSrl.setColorSchemeResources(R.color.homeblue);
        Logger.e(mViews.size() + "mViews的长度");
        //ViewUtils.setListViewHeightBasedOnChildren(zixunLst);
        homeGridview.setAdapter(mGridviewAdapter);
        homeNewsLayout.setOnClickListener(mListener);

        homerefreshSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        homeGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public String Client;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent mIntent = new Intent(mAct, IssueBountsActivity.class);
                    startActivity(mIntent);
                }
                if (position == 1) {
                    Intent mIntent = new Intent(mAct, LotterysHelperActivity.class);
                    startActivity(mIntent);
                }
                if (position == 2) {
                    Intent mIntent = new Intent(mAct, CalculateActivity.class);
                    startActivity(mIntent);
                }
                if (position == 3) {
                    Intent mIntent = new Intent(mAct, RecommendActivity.class);
                    startActivity(mIntent);
                }
                if (position == 4) {
                    Intent mIntent = new Intent(mAct, HuodongActivity.class);
                    startActivity(mIntent);
                }
                if (position == 5) {
                    Intent mIntent = new Intent(mAct, SuperGListlActivity.class);
                    startActivity(mIntent);
                }
                if (position == 6) {
                    Intent mIntent = new Intent(mAct, WangdianActivity.class);
                    startActivity(mIntent);
                }
                if (position == 7) {
                    Intent mIntent = new Intent(mAct, KefuActivity.class);
                    startActivity(mIntent);
                }
            }
        });

        loadData();
        return mView;
    }


    private void initKjVpg(List<String> mResults, final HomeBean homeBean) {
        LayoutInflater inflater = LayoutInflater.from(mAct);
        for (int i = 0; i < mResults.size(); i++) {
            Logger.e(i + "当前位置");
            View mView = inflater.inflate(R.layout.home_kaijiang_vpitem, null);
            LinearLayout mLinearLayout = (LinearLayout) mView.findViewById(R.id.kaijiang_num_tv);
            TextView lotteryTv = (TextView) mView.findViewById(R.id.home_lotteryname_tv);
            TextView issueTv = (TextView) mView.findViewById(R.id.home_issue_tv);
            ImageView plusAward = (ImageView) mView.findViewById(R.id.kaijiang_plusAward_img);

//            if (i == 0) {
//                left.setVisibility(View.GONE);
//                right.setVisibility(View.VISIBLE);
//            } else if (i == mResults.size() - 1) {
//                right.setVisibility(View.GONE);
//                left.setVisibility(View.VISIBLE);
//            } else {

//            }
            //创建开奖文字
            if (homeBean.getData().getBonus().get(i).getLotId().equals("dlt")) {
                lotteryTv.setText("大乐透");
                issueTv.setText(homeBean.getData().getBonus().get(i).getIssueNo() + "期");
                String[] arr0 = homeBean.getData().getBonus().get(i).getBonusCode().split("\\#");
                String[] arr1 = arr0[0].split("\\,");
                String[] arr2 = arr0[1].split("\\,");
                List<String> dltList = new ArrayList<>();
                dltList.clear();
                for (int m = 0; m < arr1.length; m++) {
                    dltList.add(arr1[m]);
                }
                for (int n = 0; n < arr2.length; n++) {
                    dltList.add(arr2[n]);
                }
                mBottomTextViews = new TextView[dltList.size()];
                for (int j = 0; j < mBottomTextViews.length; j++) {
                    TextView textView = new TextView(mAct);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.gravity = Gravity.CENTER;
                    params.setMargins(10, 0, 10, 0);
                    textView.setLayoutParams(params);
                    textView.setGravity(Gravity.CENTER);
                    if (j >= 5) {
                        textView.setBackgroundResource(R.mipmap.ball_blueandroid);
//                        textView.setPadding(0, 0, 3, 12);
                    } else {
                        textView.setBackgroundResource(R.mipmap.ball_red_android);
//                        textView.setPadding(0, 0, 8, 8);
                    }
                    textView.setText(dltList.get(j));
                    textView.setTextColor(getActivity().getResources().getColor(R.color.white));
                    mBottomTextViews[j] = textView;

                    mLinearLayout.addView(mBottomTextViews[j]);
                }
                if (homeBean.getData().getBonus().get(i).getPlusAward() >= 0) {
                    plusAward.setVisibility(View.VISIBLE);
                } else {
                    plusAward.setVisibility(View.GONE);
                }
                mViews.add(mView);
            } else if (("sfcsf14sf9bqcjqc".contains(homeBean.getData().getBonus().get(i).getLotId()))) {
                lotteryTv.setText(homeBean.getData().getBonus().get(i).getLotName());
                issueTv.setText(homeBean.getData().getBonus().get(i).getIssueNo() + "期");
                String[] arr = homeBean.getData().getBonus().get(i).getBonusCode().split("\\,");
                List<String> shengfulist = new ArrayList<>();
                shengfulist.clear();
                for (int m = 0; m < arr.length; m++) {
                    shengfulist.add(arr[m]);
                }
                mBottomTextViews = new TextView[shengfulist.size()];
                for (int j = 0; j < mBottomTextViews.length; j++) {
                    TextView textView = new TextView(mAct);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.gravity = Gravity.CENTER;

                    params.setMargins(10, 0, 10, 0);
                    textView.setLayoutParams(params);
                    textView.setGravity(Gravity.CENTER);
                    //textView.setPadding(0, 0, 8, 8);
                    textView.setBackgroundResource(R.mipmap.sfc_num_bg);
                    textView.setText(shengfulist.get(j));
                    textView.setTextColor(getActivity().getResources().getColor(R.color.select_red));
                    mBottomTextViews[j] = textView;
                    mLinearLayout.addView(mBottomTextViews[j]);
                }
                mViews.add(mView);
            } else {
                lotteryTv.setText(homeBean.getData().getBonus().get(i).getLotName());
                issueTv.setText(homeBean.getData().getBonus().get(i).getIssueNo() + "期");
                String[] arr = homeBean.getData().getBonus().get(i).getBonusCode().split("\\,");
                List<String> shuziList = new ArrayList<>();
                shuziList.clear();
                for (int m = 0; m < arr.length; m++) {
                    shuziList.add(arr[m]);
                }
                mBottomTextViews = new TextView[shuziList.size()];
                for (int j = 0; j < mBottomTextViews.length; j++) {
                    TextView textView = new TextView(mAct);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.gravity = Gravity.CENTER;
                    if (homeBean.getData().getBonus().get(i).getLotId().equals("qxc")) {
                        params.setMargins(10, 0, 10, 0);
                    } else {
                        params.setMargins(15, 0, 15, 0);
                    }
                    textView.setLayoutParams(params);
                    textView.setGravity(Gravity.CENTER);
                    //textView.setPadding(0, 0, 8, 8);
                    textView.setBackgroundResource(R.mipmap.ball_red_android);
                    textView.setText(shuziList.get(j));
                    textView.setTextColor(getActivity().getResources().getColor(R.color.white));
                    mBottomTextViews[j] = textView;
                    mLinearLayout.addView(mBottomTextViews[j]);
                }
                mViews.add(mView);
            }
        }
        kaijiangVpg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        homerefreshSrl.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        homerefreshSrl.setEnabled(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        endX = (int) event.getX();
                        endY = (int) event.getY();
                        if (Math.abs(endX - startX) < 50 && Math.abs(endY - startY) < 50) {
                            try {
                                Resources mResource = BaseApplication.getApplication().getResources();
                                Intent intent = new Intent();
                                intent.setClass(getActivity(), IssueBounsDetailActivity.class);
                                Bundle bundle = new Bundle();
                                switch (homeBean.getData().getBonus().get(kjcurrentPosition).getLotId()) {
                                    case "sh115":
                                        bundle.putInt("detailType", DETAIL_SH115);
                                        bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_sh11x5));
                                        bundle.putSerializable("lotid", "sh115");
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        break;
                                    case "jczq":
                                        bundle.putInt("detailType", DETAIL_JINGCAI);
                                        bundle.putSerializable("issueNumber", null);
                                        bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_jcSoccer));
                                        bundle.putSerializable("lotid", "jczq");
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        break;
                                    case "jclq":
                                        bundle.putInt("detailType", DETAIL_JINGCAI);
                                        bundle.putSerializable("issueNumber", null);
                                        bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_jcBasketball));
                                        bundle.putSerializable("lotid", "jclq");
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        break;
                                    case "dlt":
                                        bundle.putInt("detailType", DETAIL_NUMBER);
                                        bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_lotto));
                                        bundle.putSerializable("lotid", "dlt");
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        break;
                                    case "pl3":
                                        bundle.putInt("detailType", DETAIL_NUMBER);
                                        bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_pl3));
                                        bundle.putSerializable("lotid", "pl3");
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        break;
                                    case "pl5":
                                        bundle.putInt("detailType", DETAIL_NUMBER);
                                        bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_pl5));
                                        bundle.putSerializable("lotid", "pl5");
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        break;
                                    case "qxc":
                                        bundle.putInt("detailType", DETAIL_NUMBER);
                                        bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_qixing));
                                        bundle.putSerializable("lotid", "qxc");
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        break;
                                    case "sfc":
                                        bundle.putInt("detailType", DETAIL_SOCCER);
                                        bundle.putSerializable("issueNumber", null);
                                        bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_sf));
                                        bundle.putSerializable("lotid", "sfc");
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        break;
                                    case "bqc":
                                        bundle.putInt("detailType", DETAIL_SOCCER);
                                        bundle.putSerializable("issueNumber", null);
                                        bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_bqc6));
                                        bundle.putSerializable("lotid", "bqc");
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        break;
                                    case "jqc":
                                        bundle.putInt("detailType", DETAIL_SOCCER);
                                        bundle.putSerializable("issueNumber", null);
                                        bundle.putSerializable("issueName", mResource.getString(R.string.lotterys_jqc4));
                                        bundle.putSerializable("lotid", "jqc");
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        break;
                                }
                            } catch (Exception e) {
                            }
                        } else {
                            homerefreshSrl.setEnabled(true);
                        }
                        break;
                }
                return false;
            }
        });

        //创建底部指示位置的导航栏
        mBottomImages = new ImageView[mViews.size()];
        for (int i = 0; i < mViews.size(); i++) {
            ImageView imageView = new ImageView(mAct);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 0, 10, 0);
            imageView.setLayoutParams(params);
            if (i == 0) {
                imageView.setBackgroundResource(R.mipmap.point_selected);
            } else {
                imageView.setBackgroundResource(R.mipmap.point_nosele);
            }
            mBottomImages[i] = imageView;
            //把指示作用的原点图片加入底部的视图中
            homeIndicatorLl.addView(mBottomImages[i]);
        }

        kaijiangVpg.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mBottomImages[kjcurrentPosition].setBackgroundResource(R.mipmap.point_nosele);
                kjcurrentPosition = position;//记录当前的位置
                mBottomImages[kjcurrentPosition].setBackgroundResource(R.mipmap.point_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //初始化开奖viewpager的指示圆点
    private void initViewPagerPoints() {

    }

    private void setUpViewPager() {
        homeBanner.setAdapter(mHomeTopPagerAdapter);

        homeBanner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        homerefreshSrl.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        homerefreshSrl.setEnabled(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        endX = (int) event.getX();
                        endY = (int) event.getY();
                        if (Math.abs(endX - startX) < 50 && Math.abs(endY - startY) < 50) {
                            try {
                                Intent intent;
                                Bundle bundle;
                                LogUtils.i("HomeFragment linkType:" + homeBean.getData().getFocus().get(currentPosition).getLinkType());
                                switch (homeBean.getData().getFocus().get(currentPosition).getLinkType()) {
                                    case 0:
                                        break;
                                    case 1:
                                        intent = new Intent(getActivity(), WebViewActivity.class);
                                        bundle = new Bundle();
                                        bundle.putString("mUrl", homeBean.getData().getFocus().get(currentPosition).getLinkUrl());
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        break;
                                    case 2:
                                        Intent intent2 = new Intent(getActivity(), NewsDetailActivity.class);
                                        Bundle bundle2 = new Bundle();
                                        bundle2.putInt("id", homeBean.getData().getFocus().get(currentPosition).getLinkItem());
                                        bundle2.putString("title", "资讯");
                                        intent2.putExtras(bundle2);
                                        startActivity(intent2);
                                        break;
                                    case 3:
                                        break;
                                    case 4:
                                        break;
                                    case 5:
                                        break;
                                }
                            } catch (Exception e) {
                            }
                        } else {
                            homerefreshSrl.setEnabled(true);
                        }
                        break;
                }
                return false;
            }
        });
        homeBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                                               //图片左右滑动时候，将当前页的圆点图片设为选中状态
                                               @Override
                                               public void onPageSelected(int position) {

                                                   homeHandler.sendMessage(Message.obtain(
                                                           homeHandler,
                                                           HomeHandler.MSG_PAGE_CHANGED,
                                                           position, 0));

                                                   currentPosition = position % mPictureUrl.size();
                                                   for (int j = 0; j < mPictureUrl.size(); j++) {
                                                       if (j == currentPosition) {
//                                                           mBottomImages[j].setBackgroundResource(R.mipmap.selected);
                                                           viewpagertitleTv.setText(mVpNotices.get(j));
                                                       } else {
//                                                           mBottomImages[j].setBackgroundResource(R.mipmap.unselect);
                                                       }
                                                   }
                                               }

                                               @Override
                                               public void onPageScrolled(int i, float v, int i1) {
                                               }

                                               @Override
                                               public void onPageScrollStateChanged(int state) {
                                                   if (mPictureUrl.size() != 1 && mPictureUrl.size() > 1) {
                                                       switch (state) {


                                                           case ViewPager.SCROLL_STATE_DRAGGING:
                                                               homeHandler
                                                                       .sendEmptyMessage(HomeHandler.MSG_KEEP_SILENT);

                                                               break;
                                                           case ViewPager.SCROLL_STATE_IDLE:
                                                               homeHandler
                                                                       .sendEmptyMessageDelayed(
                                                                               HomeHandler.MSG_UPDATE_IMAGE,
                                                                               HomeHandler.MSG_DELAY);
                                                               break;


                                                           default:
                                                               break;
                                                       }
                                                   }
                                               }
                                           }
        );
    }

    private void startTimeTask(final int i) {
        // 设置自动轮播图片，5s后执行，周期是5s
        if (null != timer) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = UPTATE_VIEWPAGER;
                    if (autoCurrIndex == i - 1) {
                        autoCurrIndex = -1;
                    }
                    message.arg1 = autoCurrIndex + 1;
                    mHandler.sendMessageDelayed(message, 3000);
                }
            }, 3000, 3000);
        }
    }


    @Override
    protected LoadingPager.LoadResult Load() {

        mList.add("1");
        return check(mList);
    }

    //加载首页数据
    private void loadData() {
        OkGo.get(UrlManager.getHome()).cacheMode(CacheMode.NO_CACHE).connTimeOut(5000).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                LogUtils.i("homefragment json:"+s);
                Gson gson = new Gson();
                homeBean = null;
                homeBean = gson.fromJson(s, HomeBean.class);
                if (homeBean != null) {
                    if (homeBean.getRet() == 100) {
                        homeScr.setVisibility(View.VISIBLE);
                        //设置viewpager的数据
                        mPictureUrl.clear();
                        mVpNotices.clear();
                        mNotices.clear();
                        mResults.clear();
                        LogUtils.i("homeLoadData :" + homeBean.getData().getNews().size());
                        for (int i = 0; i < homeBean.getData().getFocus().size(); i++) {
                            mPictureUrl.add(homeBean.getData().getFocus().get(i).getPicUrl());
                            mVpNotices.add(homeBean.getData().getFocus().get(i).getTitle());
                        }

                        for (int m = 0; m < homeBean.getData().getNotice().size(); m++) {
                            mNotices.add(homeBean.getData().getNotice().get(m).getTitle());
                        }
                       /* //增加一条假数据
                        HomeBean.DataBean.BonusBean bonusbean = new HomeBean.DataBean.BonusBean();
                        bonusbean.setLotId("sfc");
                        bonusbean.setLotName("胜负14");
                        bonusbean.setIssueNo("20161221");
                        bonusbean.setBonusCode("1,2,3,4,5,6,7,8,9,0,1,2,3,4");
                        homeBean.getData().getBonus().add(bonusbean);*/

                        for (int m = 0; m < homeBean.getData().getBonus().size(); m++) {
                            mResults.add(homeBean.getData().getBonus().get(m).getBonusCode());
                        }

                        //设置专家看彩以及推荐方案的数据
                        intView(homeBean);
                        if (mHomeKJPagerAdapter == null) {
                            initKjVpg(mResults, homeBean);
                            mHomeKJPagerAdapter = new HomeKJPagerAdapter(mAct, mViews);
                            kaijiangVpg.setAdapter(mHomeKJPagerAdapter);
                            initViewPagerPoints();
                        } else {
                            mHomeKJPagerAdapter.notifyDataSetChanged();
                        }
                        if (mHomeTopPagerAdapter == null) {
                            mHomeTopPagerAdapter = new HomeTopPagerAdapter(mAct, mPictureUrl);
                            setUpViewPager();
                        } else {
                            mHomeTopPagerAdapter.notifyDataSetChanged();
                        }
                        homeZXLstAdapter = new HomeZXLstAdapter(homeBean);
                        zixunLst.setAdapter(homeZXLstAdapter);
                        zixunLst.setOnItemClickListener(HomeFragment.this);
                        homeBanner.setCurrentItem(2500, false);
//                        startTimeTask(mPictureUrl.size());
                        // 开始轮播效果
                        homeHandler.sendEmptyMessageDelayed(
                                HomeHandler.MSG_BREAK_SILENT,
                                HomeHandler.MSG_DELAY);
                        MarqueeViewAdapter marqueeViewAdapter = new MarqueeViewAdapter<String>(mNotices) {
                            @Override
                            public View getView(MarqueeView parent, int position, String s) {
                                final View view = LayoutInflater.from(mAct).inflate(R.layout.marqueeview_item, homenoticeTv, false);
                                TextView textView = (TextView) view.findViewById(R.id.textView);
                                textView.setText(mNotices.get(position));
                                return view;
                            }
                        };
                        homenoticeTv.setAdapter(marqueeViewAdapter);//设置适配器
                        homenoticeTv.setOnItemClickListener(HomeFragment.this);
//                        homeScr.smoothScrollTo(0, 20);
                        ClientUtils.regPushToken();//注册推送id
                    } else {
                        if (homeBean.getRet() == 201) {
                            ClientUtils.startGetClient();
                        }
                        if (homeBean.getMsg() != null) {
                            UIUtils.showToastSafe(homeBean.getMsg());
                        }
                    }
                }
            }

            @Override
            public void onAfter(@Nullable String s, @Nullable Exception e) {
                super.onAfter(s, e);
                if (homerefreshSrl != null) {
                    homerefreshSrl.setRefreshing(false);
                }
            }

            @Override
            public void onBefore(BaseRequest request) {
                super.onBefore(request);
                if (homerefreshSrl != null) {
                    homerefreshSrl.setRefreshing(true);
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                if (homerefreshSrl != null) {
                    homerefreshSrl.setRefreshing(false);
                }
                UIUtils.showToastSafe("网络异常,请检查网路设置");
            }
        });

    }

    //设置名家订阅以及推荐方案的数据
    private void intView(HomeBean homeBean) {
        if (homeBean.getData().getFamous() != null) {
            if (homeBean.getData().getFamous().size() == 1) {
                mingjia1Tv.setText(homeBean.getData().getFamous().get(0).getName());
                shenglvtitleTv.setText(homeBean.getData().getFamous().get(0).getIntro());
                Picasso.with(BaseApplication.getApplication()).load(homeBean.getData().getFamous().get(0).getLogo()).into(mingjia1);
            }
            if (homeBean.getData().getFamous().size() >= 2) {
                mingjia1Tv.setText(homeBean.getData().getFamous().get(0).getName());
                shenglvtitleTv.setText(homeBean.getData().getFamous().get(0).getIntro());
                Picasso.with(BaseApplication.getApplication()).load(homeBean.getData().getFamous().get(0).getLogo()).into(mingjia1);
                mingjia2Tv.setText(homeBean.getData().getFamous().get(1).getName());
                shenglvtitle2Tv.setText(homeBean.getData().getFamous().get(1).getIntro());
                Picasso.with(BaseApplication.getApplication()).load(homeBean.getData().getFamous().get(1).getLogo()).into(mingjia2);
            }
            zhuanjia1Rl.setOnClickListener(mListener);
            zhuanjia2Rl.setOnClickListener(mListener);
        }
        if (homeBean.getData().getRecom() != null) {
            if (homeBean.getData().getRecom().size() > 0) {

                if (homeBean.getData().getRecom().get(0).getLotName().contains(" ")) {
                    tuijianNameTv.setText(homeBean.getData().getRecom().get(0).getLotName().split("\\ ")[0]);//根据竞彩前面得彩种判断图片
                    selectImage(homeBean, homeBean.getData().getRecom().get(0).getLotName().split("\\ ")[0], tuijianIm, fangshiTv, shouyiTitleTv);
                } else {
                    tuijianNameTv.setText(homeBean.getData().getRecom().get(0).getLotName());
                    selectImage(homeBean, homeBean.getData().getRecom().get(0).getLotName(), tuijianIm, fangshiTv, shouyiTitleTv);
                }

                String fangshi = homeBean.getData().getRecom().get(0).getPlayType();
                if (fangshi.contains("串")) {
                    TextUtils.setStrColor(guoguanFsTv, fangshi, "串", getResources().getColor(R.color.changCiColor));
                } else {
                    guoguanFsTv.setText((homeBean.getData().getRecom().get(0).getPlayType()));
                    guoguanFsTv.setTextColor(getResources().getColor(R.color.changCiColor));
                }

            }
            hometuijianRl.setOnClickListener(mListener);
        }
    }

    private void selectImage(HomeBean homeBean, String lotId, ImageView imageView, TextView textview1, TextView textview2) {
        switch (lotId) {
            case "胜负彩":
                imageView.setImageResource(R.mipmap.logo_soccer_2);
                textview1.setText("投注方式");
                textview2.setText("投注金额");
                String shouyi = homeBean.getData().getRecom().get(0).getTotalMoney() + "元";
                TextUtils.setStrColor(shouyiJineTv, shouyi, "元", getResources().getColor(R.color.changCiColor));
                break;
            case "竞彩足球":
                imageView.setImageResource(R.mipmap.logo_jinzu);
                textview1.setText("过关方式");
                textview2.setText("奖金收益");
                String shouyi2 = homeBean.getData().getRecom().get(0).getBonusEstimate() + "元";
                TextUtils.setStrColor(shouyiJineTv, shouyi2, "元", getResources().getColor(R.color.changCiColor));
                break;
            case "竞彩篮球":
                imageView.setImageResource(R.mipmap.logo_jinlan);
                textview1.setText("过关方式");
                textview2.setText("奖金收益");
                String shouyi3 = homeBean.getData().getRecom().get(0).getBonusEstimate() + "元";
                TextUtils.setStrColor(shouyiJineTv, shouyi3, "元", getResources().getColor(R.color.changCiColor));
                break;
            default:
                break;

        }

    }


    private class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.home_newsLayout:
                    MainActivity main = (MainActivity) getActivity();
                    main.toggleFragment(1);
                    break;
                case R.id.zhuanjia1_rl:
                case R.id.zhuanjia2_rl:
                    Intent intent = new Intent(getActivity(), RecommendActivity.class);
                    startActivity(intent);
                    break;
                case R.id.home_tuijian_rl:
                    if (homeBean.getData().getRecom() != null && homeBean.getData().getRecom().size() > 0) {
                        Intent intent1 = new Intent(getActivity(), Rmd_famousDetailActivity.class);
                        Bundle bundle = getDetailBundle(homeBean.getData().getRecom().get(0));
                        intent1.putExtras(bundle);
                        startActivity(intent1);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //开始轮播
        if (null != homenoticeTv) {
            homenoticeTv.start();
        }
        if (homeBean != null) {
            homeHandler.sendEmptyMessageDelayed(
                    HomeHandler.MSG_BREAK_SILENT,
                    HomeHandler.MSG_DELAY);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止轮播
        if (homenoticeTv != null) {
            homenoticeTv.stop();
        }
    }

    //滚动条的点击事件
    @Override
    public void onItemClick(MarqueeView parent, View view, int position) {
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", homeBean.getData().getNotice().get(position).getId());
        bundle.putString("title", "资讯");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //listview的点击事假
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", homeBean.getData().getNews().get(position).getId());
        bundle.putString("title", "资讯");
        intent.putExtras(bundle);
        startActivity(intent);
    }


    private Bundle getDetailBundle(HomeBean.DataBean.RecomBean bean) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", bean.getId());
        String title = "";
        String lotName = bean.getLotName();
        String lotId = bean.getLotId();
        int activityType = 0;
        LogUtils.i("getDetailBundle" + lotName + ":::" + lotId);
        switch (lotId) {
            case "jldx":
                activityType = ACTIVITYTYPE_JCDX;
                break;
            case "jlrsf":
                activityType = ACTIVITYTYPE_JLRSF;
                break;
            case "jzxspf":
                activityType = ACTIVITYTYPE_JZRSPF;
                break;
            case "jzspf":
                activityType = ACTIVITYTYPE_JZSPF;
                break;
            case "jlsf":
                activityType = ACTIVITYTYPE_JLSF;
                break;
            case "sf14":
                activityType = Configs.ACTIVITYTYPE_R9;
                break;
            case "sf9":
                activityType = Configs.ACTIVITYTYPE_R9;
                break;

            //竞彩足球 半全场
            case "jzbqc":
                activityType = Configs.ACTIVITYTYPE_JZBQC;
                break;
            //竞彩足球 比分
            case "jzbf":
                activityType = Configs.ACTIVITYTYPE_JZBF;
                break;
            //竞彩足球 总进球数
            case "jzjqs":
                activityType = Configs.ACTIVITYTYPE_JZJQS;
                break;
            //竞彩足球 混合过关
            case "jzhh":
                activityType = Configs.ACTIVITYTYPE_JZHH;
                break;

            default:
                break;
        }
//        if (lotId.equals("jldx")) {
//            activityType = ACTIVITYTYPE_JCDX;
//        }
//        if (lotId.equals("jlrsf")) {
//            activityType = ACTIVITYTYPE_JLRSF;
//        }
//        if (lotId.equals("jzxspf")) {
//            activityType = ACTIVITYTYPE_JZRSPF;
//        }
//        if (lotId.equals("jzspf")) {
//            activityType = ACTIVITYTYPE_JZSPF;
//        }
//        if (lotId.equals("jlsf")) {
//            activityType = ACTIVITYTYPE_JLSF;
//        }
//        if (lotId.equals("sf14") || lotId.equals("sf9")) {
//            activityType = Configs.ACTIVITYTYPE_R9;
//        }

        if (lotName.contains("竞彩")) {
            LogUtils.i("getDetailBundle contains jincai");
            title = lotName;
//            title = title.replace("球", "");
            title = title.replace(" ", "-");
        } else if (lotName.contains("胜负彩")) {
            LogUtils.i("getDetailBundle contains shengfu");
            title = lotName.replace(" ", "");
        }
        title = title + "推荐";
        bundle.putInt(Configs.SFCDINGDAN_INT_KEY, activityType);
        bundle.putString("title", title);
        return bundle;
    }



    @Override
    protected String getTitle() {
        return "首页";
    }
}
