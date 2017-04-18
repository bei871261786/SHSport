package shlottery.gov.cn.lotterycenter.ui.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.BasketBallDetailBean;
import shlottery.gov.cn.lotterycenter.bean.RightModel;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.adapter.BasketBallQiuDuiAdapter;
import shlottery.gov.cn.lotterycenter.ui.adapter.LeftAdapter;
import shlottery.gov.cn.lotterycenter.ui.adapter.RightAdapter;
import shlottery.gov.cn.lotterycenter.ui.view.SyncHorizontalScrollView;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingDialog;
import shlottery.gov.cn.lotterycenter.ui.widget.ObservableScrollView;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;
import shlottery.gov.cn.lotterycenter.utils.ViewUtils;


/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-07-0007 15:04
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class BasketBallDataActivity extends BaseActivity {
    @BindView(R.id.titlebar_back)
    ImageView titlebarBack;
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
    @BindView(R.id.main_setting)
    ImageView mainSetting;
    @BindView(R.id.base_titleBar)
    RelativeLayout baseTitleBar;
    @BindView(R.id.matchname_tv)
    TextView matchnameTv;
    @BindView(R.id.livematchtime_tv)
    TextView livematchtimeTv;
    @BindView(R.id.hostimage_im)
    ImageView hostimageIm;
    @BindView(R.id.visitimage_im)
    ImageView visitimageIm;
    @BindView(R.id.host_score_tv)
    TextView hostScoreTv;
    @BindView(R.id.live_maohao_tv)
    TextView liveMaohaoTv;
    @BindView(R.id.visitscore_tv)
    TextView visitscoreTv;
    @BindView(R.id.haltscore_tv)
    TextView haltscoreTv;
    @BindView(R.id.extrascore_tv)
    TextView extrascoreTv;
    @BindView(R.id.pointscore_tv)
    TextView pointscoreTv;
    @BindView(R.id.fenshu_ll)
    LinearLayout fenshuLl;
    @BindView(R.id.match_staus_tv)
    TextView matchStausTv;
    @BindView(R.id.hostteam_tv)
    TextView hostteamTv;
    @BindView(R.id.visitteam_tv)
    TextView visitteamTv;
    @BindView(R.id.hostrank_tv)
    TextView hostrankTv;
    @BindView(R.id.visitrank_tv)
    TextView visitrankTv;
    @BindView(R.id.title_jia1_tv)
    TextView titleJia1Tv;
    @BindView(R.id.title_jia2_tv)
    TextView titleJia2Tv;
    @BindView(R.id.title_jia3_tv)
    TextView titleJia3Tv;
    @BindView(R.id.title_jia4_tv)
    TextView titleJia4Tv;
    @BindView(R.id.tab_hostname_tv)
    TextView tabHostnameTv;
    @BindView(R.id.hostfirst_num_tv)
    TextView hostfirstNumTv;
    @BindView(R.id.tab_1_title)
    TextView tab1title;
    @BindView(R.id.tab_2_title)
    TextView tab2title;
    @BindView(R.id.tab_3_title)
    TextView tab3title;
    @BindView(R.id.tab_4_title)
    TextView tab4title;


    @BindView(R.id.hostsecond_num_tv)
    TextView hostsecondNumTv;
    @BindView(R.id.hostthird_num_tv)
    TextView hostthirdNumTv;
    @BindView(R.id.hostforth_num_tv)
    TextView hostforthNumTv;
    @BindView(R.id.hostjia1_num_tv)
    TextView hostjia1NumTv;
    @BindView(R.id.hostjia2_num_tv)
    TextView hostjia2NumTv;
    @BindView(R.id.hostjia3_num_tv)
    TextView hostjia3NumTv;
    @BindView(R.id.hostjia4_num_tv)
    TextView hostjia4NumTv;
    @BindView(R.id.tab_visitname_tv)
    TextView tabVisitnameTv;
    @BindView(R.id.visitfirst_num_tv)
    TextView visitfirstNumTv;
    @BindView(R.id.visitsecond_num_tv)
    TextView visitsecondNumTv;
    @BindView(R.id.visitthird_num_tv)
    TextView visitthirdNumTv;
    @BindView(R.id.visitforth_num_tv)
    TextView visitforthNumTv;
    @BindView(R.id.visitjia1_num_tv)
    TextView visitjia1NumTv;
    @BindView(R.id.visitjia2_num_tv)
    TextView visitjia2NumTv;
    @BindView(R.id.visitjia3_num_tv)
    TextView visitjia3NumTv;
    @BindView(R.id.visitjia4_num_tv)
    TextView visitjia4NumTv;
    @BindView(R.id.qiudui_vs_tv)
    TextView qiuduiVsTv;
    @BindView(R.id.qiuduiclose_im)
    ImageView qiuduicloseIm;
    @BindView(R.id.qiuduiopen_im)
    ImageView qiuduiopenIm;
    @BindView(R.id.qiudui_rl)
    RelativeLayout qiuduiRl;
    @BindView(R.id.base_listv)
    ListView baseListv;
    @BindView(R.id.base_ll)
    LinearLayout baseLl;
    @BindView(R.id.analytics_vs_tv)
    TextView analyticsVsTv;
    @BindView(R.id.baseclose_im)
    ImageView basecloseIm;
    @BindView(R.id.baseopen_im)
    ImageView baseopenIm;
    @BindView(R.id.analysis_base_rl)
    RelativeLayout analysisBaseRl;
    @BindView(R.id.baskethost_ll)
    LinearLayout baskethostLl;

    @BindView(R.id.right_title_container)
    LinearLayout rightTitleContainer;
    @BindView(R.id.title_horsv)
    SyncHorizontalScrollView titleHorsv;
    @BindView(R.id.left_container_listview)
    ListView leftContainerListview;
    @BindView(R.id.left_container)
    LinearLayout leftContainer;
    @BindView(R.id.right_container_listview)
    ListView rightContainerListview;
    @BindView(R.id.right_container)
    LinearLayout rightContainer;
    @BindView(R.id.content_horsv)
    SyncHorizontalScrollView contentHorsv;
    @BindView(R.id.visit_person_tv)
    TextView visitPersonTv;
    @BindView(R.id.visitclose_im)
    ImageView visitcloseIm;
    @BindView(R.id.visitopen_im)
    ImageView visitopenIm;
    @BindView(R.id.visitperson_rl)
    RelativeLayout visitpersonRl;
    @BindView(R.id.visitleft_title_container)
    LinearLayout visitleftTitleContainer;
    @BindView(R.id.visitright_title_container)
    LinearLayout visitrightTitleContainer;
    @BindView(R.id.visittitle_horsv)
    SyncHorizontalScrollView visittitleHorsv;
    @BindView(R.id.visitleft_container_listview)
    ListView visitleftContainerListview;
    @BindView(R.id.visitleft_container)
    LinearLayout visitleftContainer;
    @BindView(R.id.visitright_container_listview)
    ListView visitrightContainerListview;
    @BindView(R.id.visitright_container)
    LinearLayout visitrightContainer;
    @BindView(R.id.visitcontent_horsv)
    SyncHorizontalScrollView visitcontentHorsv;
    @BindView(R.id.analysis_scr)
    ObservableScrollView analysisScr;
    @BindView(R.id.activity_matchanalytics)
    LinearLayout activityMatchanalytics;
    @BindView(R.id.basketvisit_ll)
    LinearLayout basketvisitLl;

    @BindView(R.id.table_layout)
    TableLayout tablelayout;
    private BasketBallDetailBean basketballdetailbean;
    private String id = "643551";//比赛id
    private boolean qiuduiFlag;//记录球队展开还是收起
    private boolean hostFlag;//记录主队
    private boolean visitFlag;//客队
    private BasketBallQiuDuiAdapter mBasketBallQiuDuiAdapter;//足球球队
    private MyOnclickListener myOnclickListener;
    private LoadingDialog loadingDialog;//加载的dialog
    private LeftAdapter hostLeftAdapter;//主队左侧栏
    private LeftAdapter visitLeftAdapter;//客队主侧栏
    private RightAdapter hostRightAdapter;//主队侧栏
    private RightAdapter visitRightAdapter;//客队侧栏
    private List<String> hostLeftList = new ArrayList<>();//主队人名
    private List<String> hostLeftPosList = new ArrayList<>();//主队人对应的位置
    private List<RightModel> hostModels = new ArrayList<>();//主队数据模型
    private List<String> visitLeftList = new ArrayList<>();//客队人名
    private List<String> visitLefPostList = new ArrayList<>();//客队人对应的位置
    private List<RightModel> visitModels = new ArrayList<>();//客队数据模型
    private List<String> visitQiuduiList = new ArrayList<>();//客队数据
    private List<String> hostQiuduiList = new ArrayList<>();//主队数据
    private List<String> titleQiuduiList = new ArrayList<>();//title集合
    private int mMatchType;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_basketball_data);
        ButterKnife.bind(this);
        titlebarTv.setText("统计");
        mainCalendar.setImageResource(R.mipmap.shuaxin);
        mainCalendar.setVisibility(View.VISIBLE);
        loadData();
        myOnclickListener = new MyOnclickListener();
        mainCalendar.setOnClickListener(myOnclickListener);
        qiuduiRl.setOnClickListener(myOnclickListener);
        analysisBaseRl.setOnClickListener(myOnclickListener);
        visitpersonRl.setOnClickListener(myOnclickListener);
        analysisScr.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                LogUtils.i("Basketball onScrollChanged:" + x + "::::" + y + "::::" + oldx + "::::" + oldy);
            }
        });
        //        contentHorsv.setScrollView(analysisScr);
    }

    //初始化右边的数据
    private void initRightData() {
        hostModels.clear();
        visitModels.clear();
        for (int j = 0; j < basketballdetailbean.getData().getHostPersonStat().size(); j++) {
            BasketBallDetailBean.DataBean.HostPersonStatBean hostPersonStatBean = basketballdetailbean.getData()
                    .getHostPersonStat().get(j);
            hostModels.add(new RightModel(hostPersonStatBean.getTim(),
                    hostPersonStatBean.getFg(),
                    hostPersonStatBean.getThr(),
                    hostPersonStatBean.getPts(),
                    hostPersonStatBean.getTr(),
                    hostPersonStatBean.getA(),
                    hostPersonStatBean.getTot(),
                    hostPersonStatBean.getStl(),
                    hostPersonStatBean.getBlk(),
                    hostPersonStatBean.getPf()));
        }

        for (int j = 0; j < basketballdetailbean.getData().getVisitPersonStat().size(); j++) {
            BasketBallDetailBean.DataBean.VisitPersonStatBean visitPersonStatBean = basketballdetailbean.getData()
                    .getVisitPersonStat().get(j);
            visitModels.add(new RightModel(visitPersonStatBean.getTim(),
                    visitPersonStatBean.getFg(),
                    visitPersonStatBean.getThr(),
                    visitPersonStatBean.getPts(),
                    visitPersonStatBean.getTr(),
                    visitPersonStatBean.getA(),
                    visitPersonStatBean.getTot(),
                    visitPersonStatBean.getStl(),
                    visitPersonStatBean.getBlk(),
                    visitPersonStatBean.getPf()));
        }
    }

    //初始化左边的数据
    private void initLeftData() {
        hostLeftList.clear();
        for (int i = 0; i < basketballdetailbean.getData().getHostPersonStat().size(); i++) {
            hostLeftList.add(basketballdetailbean.getData().getHostPersonStat().get(i).getName());
            hostLeftPosList.add(basketballdetailbean.getData().getHostPersonStat().get(i).getPos());
        }
        visitLeftList.clear();
        for (int i = 0; i < basketballdetailbean.getData().getVisitPersonStat().size(); i++) {
            visitLeftList.add(basketballdetailbean.getData().getVisitPersonStat().get(i).getName());
            visitLefPostList.add(basketballdetailbean.getData().getVisitPersonStat().get(i).getPos());
        }
    }

    //初始化球队的数据
    private void initQiuduiData() {
        //初始化主队数据
        hostQiuduiList.clear();
        BasketBallDetailBean.DataBean.HostStatBean hostStat = basketballdetailbean.getData().getHostStat();
        hostQiuduiList.add(hostStat.getFg());
        hostQiuduiList.add(hostStat.getThr());
        hostQiuduiList.add(hostStat.getFt());
        hostQiuduiList.add(hostStat.getTr());
        hostQiuduiList.add(hostStat.getOtr());
        hostQiuduiList.add(hostStat.getDtr());
        hostQiuduiList.add(hostStat.getA());
        hostQiuduiList.add(hostStat.getTot());
        hostQiuduiList.add(hostStat.getStl());
        hostQiuduiList.add(hostStat.getBlk());
        hostQiuduiList.add(hostStat.getPf());

        //初始化主队数据
        visitQiuduiList.clear();
        BasketBallDetailBean.DataBean.VisitStatBean visitStat = basketballdetailbean.getData().getVisitStat();
        visitQiuduiList.add(visitStat.getFg());
        visitQiuduiList.add(visitStat.getThr());
        visitQiuduiList.add(visitStat.getFt());
        visitQiuduiList.add(visitStat.getTr());
        visitQiuduiList.add(visitStat.getOtr());
        visitQiuduiList.add(visitStat.getDtr());
        visitQiuduiList.add(visitStat.getA());
        visitQiuduiList.add(visitStat.getTot());
        visitQiuduiList.add(visitStat.getStl());
        visitQiuduiList.add(visitStat.getBlk());
        visitQiuduiList.add(visitStat.getPf());

        //初始化标题数据
        titleQiuduiList.clear();
        titleQiuduiList.add("总命中率");
        titleQiuduiList.add("三分命中");
        titleQiuduiList.add("罚球命中");
        titleQiuduiList.add("总篮板");
        titleQiuduiList.add("前场篮板");
        titleQiuduiList.add("后场篮板");
        titleQiuduiList.add("助攻");
        titleQiuduiList.add("失误");
        titleQiuduiList.add("抢断");
        titleQiuduiList.add("盖帽");
        titleQiuduiList.add("犯规");
        //        titleQiuduiList


    }

    //从网络加载数据
    private void loadData() {
        OkGo.get(UrlManager.getJclqMatchInfo(this, id)).cacheMode(CacheMode.NO_CACHE).connTimeOut(5000).execute(new StringCallback() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                basketballdetailbean = gson.fromJson(s, BasketBallDetailBean.class);
                if (basketballdetailbean != null) {
                    if (basketballdetailbean.getRet() == 100) {
                        BasketBallDetailBean.DataBean basketballdetailbeanData = basketballdetailbean.getData();
                        analysisScr.setVisibility(View.VISIBLE);
                        matchnameTv.setText(basketballdetailbeanData.getLeagueName() + " " +
                                basketballdetailbeanData.getLeagueLevel());
                        livematchtimeTv.setText(DateUtils.formatDateAndTime(basketballdetailbeanData
                                .getMatchTime() * 1000));
                        Picasso.with(BasketBallDataActivity.this).load(basketballdetailbeanData.getHostLogo())
                                .into(hostimageIm);
                        Picasso.with(BasketBallDataActivity.this).load(basketballdetailbeanData.getVisitLogo())
                                .into(visitimageIm);
                        tablelayout.setVisibility(View.VISIBLE);
                        mMatchType = basketballdetailbeanData.getMatchType();
                        LogUtils.i("Basketball Detail matchType:" + mMatchType);
                        //修改仅在完场状态下比赛比分为红色, 其他状态都为绿色
                        String statusId = basketballdetailbeanData.getStatusId();
                        int red = BaseApplication.getApplication().getResources().getColor(R.color.match_vs_red);
                        int green = BaseApplication.getApplication().getResources().getColor(R.color.green);
                        hostScoreTv.setTextColor(statusId.equals("6") ? red : green);
                        visitscoreTv.setTextColor(statusId.equals("6") ? red : green);
                        liveMaohaoTv.setTextColor(statusId.equals("6") ? red : green);
                        if (!StringUtils.isEmpty(basketballdetailbeanData.getHostGoal())) {
                            hostScoreTv.setText(basketballdetailbeanData.getHostGoal());
                            visitscoreTv.setText(basketballdetailbeanData.getVisitGoal());
                            liveMaohaoTv.setText(":");
                            hostScoreTv.setVisibility(View.VISIBLE);
                            visitscoreTv.setVisibility(View.VISIBLE);
                            liveMaohaoTv.setVisibility(View.VISIBLE);
                        }

                        if (!StringUtils.isEmpty(basketballdetailbeanData.getHostHalfGoal())) {//半场比分
                            haltscoreTv.setText("HT " + basketballdetailbeanData.getVisitHalfGoal() + ":" +
                                    basketballdetailbeanData.getHostHalfGoal());
                            haltscoreTv.setVisibility(View.VISIBLE);
                        }

                        toggleType(mMatchType);

                        //表格中的数据
                        if (!StringUtils.isEmpty(basketballdetailbeanData.getHost1st())) {//第一节比分
                            hostfirstNumTv.setText(basketballdetailbeanData.getHost1st());
                            visitfirstNumTv.setText(basketballdetailbeanData.getVisit1st());
                        }
                        if (!StringUtils.isEmpty(basketballdetailbeanData.getHost2nd())) {//第二节比分
                            hostsecondNumTv.setText(basketballdetailbeanData.getHost2nd());
                            visitsecondNumTv.setText(basketballdetailbeanData.getVisit2nd());
                        }
                        if (!StringUtils.isEmpty(basketballdetailbeanData.getHost3rd())) {//第3节比分
                            hostthirdNumTv.setText(basketballdetailbeanData.getHost3rd());
                            visitthirdNumTv.setText(basketballdetailbeanData.getVisit3rd());
                        }
                        if (!StringUtils.isEmpty(basketballdetailbeanData.getHost4th())) {//第4节比分
                            hostforthNumTv.setText(basketballdetailbeanData.getHost4th());
                            visitforthNumTv.setText(basketballdetailbeanData.getVisit4th());
                        }


                        if (!StringUtils.isEmpty(basketballdetailbeanData.getHost1a())) {//加1比分
                            hostjia1NumTv.setText(basketballdetailbeanData.getHost1a());
                            visitjia1NumTv.setText(basketballdetailbeanData.getVisit1a());
                            titleJia1Tv.setVisibility(View.VISIBLE);
                            hostjia1NumTv.setVisibility(View.VISIBLE);
                            visitjia1NumTv.setVisibility(View.VISIBLE);
                        } else {
                            titleJia1Tv.setVisibility(View.GONE);
                            hostjia1NumTv.setVisibility(View.GONE);
                            visitjia1NumTv.setVisibility(View.GONE);
                        }

                        if (!StringUtils.isEmpty(basketballdetailbeanData.getHost2a())) {//加2比分
                            hostjia2NumTv.setText(basketballdetailbeanData.getHost2a());
                            visitjia2NumTv.setText(basketballdetailbeanData.getVisit2a());
                            titleJia2Tv.setVisibility(View.VISIBLE);
                            hostjia2NumTv.setVisibility(View.VISIBLE);
                            visitjia2NumTv.setVisibility(View.VISIBLE);
                        } else {
                            titleJia2Tv.setVisibility(View.GONE);
                            hostjia2NumTv.setVisibility(View.GONE);
                            visitjia2NumTv.setVisibility(View.GONE);
                        }

                        if (!StringUtils.isEmpty(basketballdetailbeanData.getHost3a())) {//加3比分
                            hostjia3NumTv.setText(basketballdetailbeanData.getHost3a());
                            visitjia3NumTv.setText(basketballdetailbeanData.getVisit3a());
                            titleJia3Tv.setVisibility(View.VISIBLE);
                            hostjia3NumTv.setVisibility(View.VISIBLE);
                            visitjia3NumTv.setVisibility(View.VISIBLE);
                        } else {
                            titleJia3Tv.setVisibility(View.GONE);
                            hostjia3NumTv.setVisibility(View.GONE);
                            visitjia3NumTv.setVisibility(View.GONE);
                        }

                        if (!StringUtils.isEmpty(basketballdetailbeanData.getHost4a())) {//加4比分
                            hostjia4NumTv.setText(basketballdetailbeanData.getHost4a());
                            visitjia4NumTv.setText(basketballdetailbeanData.getVisit4a());
                            titleJia4Tv.setVisibility(View.VISIBLE);
                            hostjia4NumTv.setVisibility(View.VISIBLE);
                            visitjia4NumTv.setVisibility(View.VISIBLE);
                        } else {
                            titleJia4Tv.setVisibility(View.GONE);
                            hostjia4NumTv.setVisibility(View.GONE);
                            visitjia4NumTv.setVisibility(View.GONE);
                        }


                        hostteamTv.setText(basketballdetailbeanData.getHostName());
                        visitteamTv.setText(basketballdetailbeanData.getVisitName());

                        tabHostnameTv.setText(basketballdetailbeanData.getHostName());
                        tabVisitnameTv.setText(basketballdetailbeanData.getVisitName());

                        matchStausTv.setText(basketballdetailbeanData.getStatusName());

                        if (!StringUtils.isEmpty(basketballdetailbeanData.getHostRank()) &&
                                !basketballdetailbeanData.getHostRank().equals("0")) {
                            hostrankTv.setText("(" + basketballdetailbeanData.getHostRank() + ")");
                        }
                        if (!StringUtils.isEmpty(basketballdetailbeanData.getHostRank()) &&
                                !basketballdetailbeanData.getVisitRank().equals("0")) {
                            visitrankTv.setText("(" + basketballdetailbeanData.getVisitRank() + ")");
                        }
                        setMayChangeData(basketballdetailbean);//设置比赛状态
                        initFlag(basketballdetailbean);


                        if (basketballdetailbeanData.getHostPersonStat() != null && basketballdetailbean
                                .getData().getHostPersonStat().size() > 0) {//当服务器有数据返回且长度大于0 初始化主客队个人数据
                            initLeftData();
                            // 添加右边内容数据
                            initRightData();
                        }

                        if (basketballdetailbeanData.getHostStat() != null && !StringUtils.isEmpty
                                (basketballdetailbeanData.getHostStat().getFg())) {//如果服务器返回球队数据不为空 并且第一个数据不为空
                            initQiuduiData();
                        }
                        mBasketBallQiuDuiAdapter = new BasketBallQiuDuiAdapter(BasketBallDataActivity.this,
                                hostQiuduiList, titleQiuduiList, visitQiuduiList);
                        hostLeftAdapter = new LeftAdapter(BasketBallDataActivity.this, hostLeftList, hostLeftPosList,
                                basketballdetailbean, 0);//此处设置  0(主场) 1 (客场)主要是为了区分是主场还是客场
                        hostRightAdapter = new RightAdapter(BasketBallDataActivity.this, hostModels,
                                basketballdetailbean, 0);

                        visitLeftAdapter = new LeftAdapter(BasketBallDataActivity.this, visitLeftList,
                                visitLefPostList, basketballdetailbean, 1);
                        visitRightAdapter = new RightAdapter(BasketBallDataActivity.this, visitModels,
                                basketballdetailbean, 1);

                        baseListv.setAdapter(mBasketBallQiuDuiAdapter);
                        ViewUtils.setListViewHeightBasedOnChildren(baseListv);

                        leftContainerListview.setAdapter(hostLeftAdapter);
                        rightContainerListview.setAdapter(hostRightAdapter);
                        ViewUtils.setListViewHeightBasedOnChildren(leftContainerListview);
                        ViewUtils.setListViewHeightBasedOnChildren(rightContainerListview);

                        visitleftContainerListview.setAdapter(visitLeftAdapter);
                        visitrightContainerListview.setAdapter(visitRightAdapter);
                        ViewUtils.setListViewHeightBasedOnChildren(visitleftContainerListview);
                        ViewUtils.setListViewHeightBasedOnChildren(visitrightContainerListview);

                    } else {
                        if (StringUtils.isEmpty(basketballdetailbean.getMsg())) {
                            UIUtils.showToastSafe("服务器累坏了,请稍后重试...");
                        } else {
                            UIUtils.showToastSafe(basketballdetailbean.getMsg());
                        }
                    }
                }
            }

            @Override
            public void onBefore(BaseRequest request) {
                super.onBefore(request);
                loadingDialog = new LoadingDialog(BasketBallDataActivity.this, "正在加载...");
                loadingDialog.show();
                mainCalendar.setEnabled(false);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                if (loadingDialog != null) {
                    loadingDialog.close();
                }
                mainCalendar.setEnabled(true);
            }

            @Override
            public void onAfter(@Nullable String s, @Nullable Exception e) {
                super.onAfter(s, e);
                if (loadingDialog != null) {
                    loadingDialog.close();
                }
                mainCalendar.setEnabled(true);
            }
        });
    }

    private void setMayChangeData(BasketBallDetailBean basketballdetailbean) {
        //设置比赛状态
        switch (Integer.parseInt(basketballdetailbean.getData().getStatusId())) {
            case 1://未开赛
                matchStausTv.setBackgroundResource(R.mipmap.bb_score_time_yellow);
                matchStausTv.setText("未开赛");
                break;
            case 2://待定
                matchStausTv.setText("待定");
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusblue);
                break;
            case 3:
                // 上半场
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("上半场");

                break;
            case 4:
                // 下半场
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("下半场");

                break;
            case 5:
                // 半场休息
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("半场");
                break;
            case 6:
                // 全场结束
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusred);
                matchStausTv.setText("完场");
                break;
            case 7:
                //下半场完
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("下半场完");
                break;
            case 12:
                // 中断
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusblue);
                matchStausTv.setText("中断");
                break;
            case 13:
                // 腰斩
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusblue);
                matchStausTv.setText("腰斩");
                break;
            case 14:
                // 取消
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusblue);
                matchStausTv.setText("取消");
                break;
            case 15:
                // 延期
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusblue);
                matchStausTv.setText("延期");
                break;
            case 16:
                // 延时
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusblue);
                matchStausTv.setText("延时");
                break;
            case 17:
                // 第一节
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("第一节");
                break;
            case 18:
                // 第二节
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("第二节");
                break;
            case 19:
                // 第三节
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("第三节");
                break;
            case 20:
                // 第四节
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("第四节");
                break;
            case 21:
                // 加时1
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("加时1");
                break;
            case 22:
                // 加时 2
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("加时2");

                break;
            case 23:
                // 加时 3
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("加时3");
                break;
            case 24:
                // 加时 4
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("加时4");
                break;
            case 26:
                // 第一节完
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("第一节完");
                break;
            case 27:
                // 第二节完
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("第二节完");
                break;
            case 28:
                // 第三节完
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("第三节完");

                break;
            case 29:
                // 第四节完
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("第四节完");
                break;
            case 30:
                // 加时1完
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("加时1完");
                break;
            case 31:
                // 加时2完
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("加时2完");
                break;
            case 32:
                // 加时3完
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("加时3完");
                break;
            case 33:
                // 加时4完
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                matchStausTv.setText("加时完");

                break;
            default:
                break;
        }
    }

    @Override
    protected void init() {
        id = getIntent().getStringExtra("matchId");
    }

    @OnClick(R.id.titlebar_back_ll)
        //返回按钮
    void back() {
        finish();
    }

    private void initFlag(BasketBallDetailBean basketballdetailbean) {
        if (null != basketballdetailbean && basketballdetailbean.getData() != null && basketballdetailbean.getData()
                .getHostStat() != null && !StringUtils.isEmpty(basketballdetailbean.getData().getHostStat().getFg())) {
            qiuduiFlag = true;
        } else {
            qiuduiFlag = false;
        }
        if (qiuduiFlag) {
            qiuduiopenIm.setVisibility(View.INVISIBLE);
            qiuduicloseIm.setVisibility(View.VISIBLE);
            baseLl.setVisibility(View.VISIBLE);

        } else {
            qiuduicloseIm.setVisibility(View.INVISIBLE);
            qiuduiopenIm.setVisibility(View.VISIBLE);
            baseLl.setVisibility(View.GONE);
        }


        if (null != basketballdetailbean && basketballdetailbean.getData().getHostPersonStat() != null &&
                basketballdetailbean.getData().getHostPersonStat().size() > 0) {
            hostFlag = true;
        } else {
            hostFlag = false;
        }
        if (hostFlag) {
            baseopenIm.setVisibility(View.INVISIBLE);
            basecloseIm.setVisibility(View.VISIBLE);
            baskethostLl.setVisibility(View.VISIBLE);
        } else {
            basecloseIm.setVisibility(View.INVISIBLE);
            baseopenIm.setVisibility(View.VISIBLE);
            baskethostLl.setVisibility(View.GONE);
        }

        if (null != basketballdetailbean && basketballdetailbean.getData().getVisitPersonStat() != null &&
                basketballdetailbean.getData().getVisitPersonStat().size() > 0) {
            visitFlag = true;
        } else {
            visitFlag = false;
        }
        if (visitFlag) {
            visitopenIm.setVisibility(View.INVISIBLE);
            visitcloseIm.setVisibility(View.VISIBLE);
            basketvisitLl.setVisibility(View.VISIBLE);

        } else {
            visitcloseIm.setVisibility(View.INVISIBLE);
            visitopenIm.setVisibility(View.VISIBLE);
            basketvisitLl.setVisibility(View.GONE);
        }
    }

    //将样式根据服务器数据调整为四场或者上下场
    private void toggleType(int type) {
        if (type == 1) {
            tab1title.setText("上半场");
            tab2title.setVisibility(View.GONE);
            tab3title.setText("下半场");
            tab4title.setVisibility(View.GONE);
            hostsecondNumTv.setVisibility(View.GONE);
            hostforthNumTv.setVisibility(View.GONE);
            visitsecondNumTv.setVisibility(View.GONE);
            visitforthNumTv.setVisibility(View.GONE);
        } else {
            tab1title.setText("1节");
            tab2title.setText("2节");
            tab2title.setVisibility(View.VISIBLE);
            tab3title.setText("3节");
            tab4title.setText("4节");
            tab4title.setVisibility(View.VISIBLE);
            hostsecondNumTv.setVisibility(View.VISIBLE);
            hostforthNumTv.setVisibility(View.VISIBLE);
            visitsecondNumTv.setVisibility(View.VISIBLE);
            visitforthNumTv.setVisibility(View.VISIBLE);

        }
    }

    private class MyOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.qiudui_rl://两队
                    if (qiuduiFlag) {
                        qiuduiFlag = false;
                    } else {
                        qiuduiFlag = true;
                    }
                    if (qiuduiFlag) {
                        qiuduiopenIm.setVisibility(View.INVISIBLE);
                        qiuduicloseIm.setVisibility(View.VISIBLE);
                        baseLl.setVisibility(View.VISIBLE);

                    } else {
                        qiuduicloseIm.setVisibility(View.INVISIBLE);
                        qiuduiopenIm.setVisibility(View.VISIBLE);
                        baseLl.setVisibility(View.GONE);
                    }
                    break;
                case R.id.analysis_base_rl://主队
                    if (hostFlag) {
                        hostFlag = false;
                    } else {
                        hostFlag = true;
                    }
                    if (hostFlag) {
                        baseopenIm.setVisibility(View.INVISIBLE);
                        basecloseIm.setVisibility(View.VISIBLE);
                        baskethostLl.setVisibility(View.VISIBLE);

                    } else {
                        basecloseIm.setVisibility(View.INVISIBLE);
                        baseopenIm.setVisibility(View.VISIBLE);
                        baskethostLl.setVisibility(View.GONE);
                    }
                    break;
                case R.id.visitperson_rl://客队
                    if (visitFlag) {
                        visitFlag = false;
                    } else {
                        visitFlag = true;
                    }
                    if (visitFlag) {
                        visitopenIm.setVisibility(View.INVISIBLE);
                        visitcloseIm.setVisibility(View.VISIBLE);
                        basketvisitLl.setVisibility(View.VISIBLE);

                    } else {
                        visitcloseIm.setVisibility(View.INVISIBLE);
                        visitopenIm.setVisibility(View.VISIBLE);
                        basketvisitLl.setVisibility(View.GONE);
                    }
                    break;
                case R.id.main_calendar:
                    loadData();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected String getBaidutitle() {
        return "篮球赛事详情析";
    }
}
