package shlottery.gov.cn.lotterycenter.ui.activity;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.BasketBallDetailBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.adapter.JcLqVsAdapter;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingDialog;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
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

public class BasketBallShujufenxiActivity extends BaseActivity {

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
    @BindView(R.id.tab_hostname_tv)
    TextView tabHostnameTv;
    @BindView(R.id.hostfirst_num_tv)
    TextView hostfirstNumTv;
    @BindView(R.id.hostsecond_num_tv)
    TextView hostsecondNumTv;
    @BindView(R.id.hostthird_num_tv)
    TextView hostthirdNumTv;
    @BindView(R.id.hostforth_num_tv)
    TextView hostforthNumTv;
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
    @BindView(R.id.analytics_vs_tv)
    TextView analyticsVsTv;
    @BindView(R.id.baseclose_im)
    ImageView basecloseIm;
    @BindView(R.id.baseopen_im)
    ImageView baseopenIm;
    @BindView(R.id.analysis_base_rl)
    RelativeLayout analysisBaseRl;
    @BindView(R.id.base_host_tv)
    TextView baseHostTv;
    @BindView(R.id.base_ping_tv)
    TextView basePingTv;
    @BindView(R.id.base_visit_tv)
    TextView baseVisitTv;
    @BindView(R.id.base_vs_ll)
    LinearLayout baseVsLl;
    @BindView(R.id.base_listv)
    ListView baseListv;
    @BindView(R.id.base_ll)
    LinearLayout baseLl;
    @BindView(R.id.host_zhanji_tv)
    TextView hostZhanjiTv;
    @BindView(R.id.hostclose_im)
    ImageView hostcloseIm;
    @BindView(R.id.hostopen_im)
    ImageView hostopenIm;
    @BindView(R.id.host_rl)
    RelativeLayout hostRl;
    @BindView(R.id.host_host_im)
    ImageView hostHostIm;
    @BindView(R.id.host_host_tv)
    TextView hostHostTv;
    @BindView(R.id.host_win_tv)
    TextView hostWinTv;
    @BindView(R.id.host_ping_tv)
    TextView hostPingTv;
    @BindView(R.id.host_lose_tv)
    TextView hostLoseTv;
    @BindView(R.id.host_listv)
    ListView hostListv;
    @BindView(R.id.host_visit_im)
    ImageView hostVisitIm;
    @BindView(R.id.visit_name_tv)
    TextView visitNameTv;
    @BindView(R.id.visit_win_tv)
    TextView visitWinTv;
    @BindView(R.id.visit_ping_tv)
    TextView visitPingTv;
    @BindView(R.id.visit_lose_tv)
    TextView visitLoseTv;
    @BindView(R.id.visit_listv)
    ListView visitListv;
    @BindView(R.id.host_ll)
    LinearLayout hostLl;
    @BindView(R.id.analysis_scr)
    ScrollView analysisScr;
    @BindView(R.id.activity_matchanalytics)
    LinearLayout activityMatchanalytics;
    @BindView(R.id.title_jia1_tv)
    TextView titleJia1Tv;
    @BindView(R.id.title_jia2_tv)
    TextView titleJia2Tv;
    @BindView(R.id.title_jia3_tv)
    TextView titleJia3Tv;
    @BindView(R.id.title_jia4_tv)
    TextView titleJia4Tv;
    @BindView(R.id.hostjia1_num_tv)
    TextView hostjia1NumTv;
    @BindView(R.id.hostjia2_num_tv)
    TextView hostjia2NumTv;
    @BindView(R.id.hostjia3_num_tv)
    TextView hostjia3NumTv;
    @BindView(R.id.hostjia4_num_tv)
    TextView hostjia4NumTv;
    @BindView(R.id.visitjia1_num_tv)
    TextView visitjia1NumTv;
    @BindView(R.id.visitjia2_num_tv)
    TextView visitjia2NumTv;
    @BindView(R.id.visitjia3_num_tv)
    TextView visitjia3NumTv;
    @BindView(R.id.visitjia4_num_tv)
    TextView visitjia4NumTv;
    @BindView(R.id.hostrank_tv)
    TextView hostrankTv;
    @BindView(R.id.visitrank_tv)
    TextView visitrankTv;
    @BindView(R.id.table_layout)
    TableLayout tablelayout;
    private BasketBallDetailBean basketballdetailbean;
    private String id = "643551";//比赛id

    private boolean baseFlag;//当为true表示数据不为空,默认展开 两队
    private boolean hostFlag;//当为true表示数据不为空,默认展开 两队各自

    private JcLqVsAdapter mVsJcLqVsAdapter;//两队最近六场对战
    private JcLqVsAdapter mHostJcLqVsAdapter;//主队最近六场
    private JcLqVsAdapter mVisitJcLqVsAdapter;//客队最近六场

    private MyOnclickListener myOnclickListener;
    private LoadingDialog loadingDialog;//加载的dialog

    @Override
    protected void initView() {
        setContentView(R.layout.activity_basketball_analyticsl);
        ButterKnife.bind(this);
        titlebarTv.setText("数据分析");
        mainCalendar.setImageResource(R.mipmap.shuaxin);
        mainCalendar.setVisibility(View.VISIBLE);
        loadData();
        myOnclickListener = new MyOnclickListener();
        mainCalendar.setOnClickListener(myOnclickListener);
        analysisBaseRl.setOnClickListener(myOnclickListener);
        hostRl.setOnClickListener(myOnclickListener);
    }

    @Override
    protected void init() {
        id = getIntent().getStringExtra("matchId");
    }

    //从网络加载数据
    private void loadData() {
        OkGo.get(UrlManager.getJclqMatchInfo(this, id)).cacheMode(CacheMode.NO_CACHE).connTimeOut(5000).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                basketballdetailbean = gson.fromJson(s, BasketBallDetailBean.class);
                if (basketballdetailbean != null) {
                    if (basketballdetailbean.getRet() == 100) {
                        analysisScr.setVisibility(View.VISIBLE);
                        tablelayout.setVisibility(View.GONE);

                        matchnameTv.setText( basketballdetailbean.getData().getLeagueName() + " " + basketballdetailbean.getData().getLeagueLevel());
                        livematchtimeTv.setText(DateUtils.getPl5DateAndTime(basketballdetailbean.getData().getMatchTime() * 1000));
                        Picasso.with(BasketBallShujufenxiActivity.this).load(basketballdetailbean.getData().getHostLogo()).into(hostimageIm);
                        Picasso.with(BasketBallShujufenxiActivity.this).load(basketballdetailbean.getData().getVisitLogo()).into(visitimageIm);
                        if (!StringUtils.isEmpty(basketballdetailbean.getData().getHostGoal())) {
                            hostScoreTv.setText(basketballdetailbean.getData().getHostGoal());
                            visitscoreTv.setText(basketballdetailbean.getData().getVisitGoal());
                            liveMaohaoTv.setText(":");
                            hostScoreTv.setVisibility(View.VISIBLE);
                            visitscoreTv.setVisibility(View.VISIBLE);
                            liveMaohaoTv.setVisibility(View.VISIBLE);

                        }

                        if (!StringUtils.isEmpty(basketballdetailbean.getData().getHostHalfGoal())) {//半场比分
                            haltscoreTv.setText("HT " + basketballdetailbean.getData().getHostHalfGoal() + ":" + basketballdetailbean.getData().getVisitHalfGoal());
                            haltscoreTv.setVisibility(View.VISIBLE);
                        }

                        //表格中的数据
                        if (!StringUtils.isEmpty(basketballdetailbean.getData().getHost1st())) {//第一节比分
                            hostfirstNumTv.setText(basketballdetailbean.getData().getHost1st());
                            visitfirstNumTv.setText(basketballdetailbean.getData().getVisit1st());
                        }
                        if (!StringUtils.isEmpty(basketballdetailbean.getData().getHost2nd())) {//第二节比分
                            hostsecondNumTv.setText(basketballdetailbean.getData().getHost2nd());
                            visitsecondNumTv.setText(basketballdetailbean.getData().getVisit2nd());
                        }
                        if (!StringUtils.isEmpty(basketballdetailbean.getData().getHost3rd())) {//第3节比分
                            hostthirdNumTv.setText(basketballdetailbean.getData().getHost3rd());
                            visitthirdNumTv.setText(basketballdetailbean.getData().getVisit3rd());
                        }
                        if (!StringUtils.isEmpty(basketballdetailbean.getData().getHost4th())) {//第4节比分
                            hostforthNumTv.setText(basketballdetailbean.getData().getHost4th());
                            visitforthNumTv.setText(basketballdetailbean.getData().getVisit4th());
                        }

                        if (!StringUtils.isEmpty(basketballdetailbean.getData().getHost1a())) {//加1比分
                            hostjia1NumTv.setText(basketballdetailbean.getData().getHost1a());
                            visitjia1NumTv.setText(basketballdetailbean.getData().getVisit1a());
                            titleJia1Tv.setVisibility(View.VISIBLE);
                            hostjia1NumTv.setVisibility(View.VISIBLE);
                            visitjia1NumTv.setVisibility(View.VISIBLE);
                        } else {
                            titleJia1Tv.setVisibility(View.GONE);
                            hostjia1NumTv.setVisibility(View.GONE);
                            visitjia1NumTv.setVisibility(View.GONE);
                        }

                        if (!StringUtils.isEmpty(basketballdetailbean.getData().getHost2a())) {//加2比分
                            hostjia2NumTv.setText(basketballdetailbean.getData().getHost2a());
                            visitjia2NumTv.setText(basketballdetailbean.getData().getVisit2a());
                            titleJia2Tv.setVisibility(View.VISIBLE);
                            hostjia2NumTv.setVisibility(View.VISIBLE);
                            visitjia2NumTv.setVisibility(View.VISIBLE);
                        } else {
                            titleJia2Tv.setVisibility(View.GONE);
                            hostjia2NumTv.setVisibility(View.GONE);
                            visitjia2NumTv.setVisibility(View.GONE);
                        }

                        if (!StringUtils.isEmpty(basketballdetailbean.getData().getHost3a())) {//加3比分
                            hostjia3NumTv.setText(basketballdetailbean.getData().getHost3a());
                            visitjia3NumTv.setText(basketballdetailbean.getData().getVisit3a());
                            titleJia3Tv.setVisibility(View.VISIBLE);
                            hostjia3NumTv.setVisibility(View.VISIBLE);
                            visitjia3NumTv.setVisibility(View.VISIBLE);
                        } else {
                            titleJia3Tv.setVisibility(View.GONE);
                            hostjia3NumTv.setVisibility(View.GONE);
                            visitjia3NumTv.setVisibility(View.GONE);
                        }

                        if (!StringUtils.isEmpty(basketballdetailbean.getData().getHost4a())) {//加4比分
                            hostjia4NumTv.setText(basketballdetailbean.getData().getHost4a());
                            visitjia4NumTv.setText(basketballdetailbean.getData().getVisit4a());
                            titleJia4Tv.setVisibility(View.VISIBLE);
                            hostjia4NumTv.setVisibility(View.VISIBLE);
                            visitjia4NumTv.setVisibility(View.VISIBLE);
                        } else {
                            titleJia4Tv.setVisibility(View.GONE);
                            hostjia4NumTv.setVisibility(View.GONE);
                            visitjia4NumTv.setVisibility(View.GONE);
                        }

                        hostteamTv.setText(basketballdetailbean.getData().getHostName());
                        visitteamTv.setText(basketballdetailbean.getData().getVisitName());

                        tabHostnameTv.setText(basketballdetailbean.getData().getHostName());
                        tabVisitnameTv.setText(basketballdetailbean.getData().getVisitName());
                        matchStausTv.setText(basketballdetailbean.getData().getStatusName());

                        if (!StringUtils.isEmpty(basketballdetailbean.getData().getHostRank()) && !basketballdetailbean.getData().getHostRank().equals("0")) {
                            hostrankTv.setText("[" + basketballdetailbean.getData().getHostRank() + "]");
                        }
                        if (!StringUtils.isEmpty(basketballdetailbean.getData().getHostRank()) && !basketballdetailbean.getData().getVisitRank().equals("0")) {
                            visitrankTv.setText("[" + basketballdetailbean.getData().getVisitRank() + "]");
                        }
                        setMayChangeData(basketballdetailbean);//设置比赛状态
                        initFlag(basketballdetailbean);

                        mVsJcLqVsAdapter = new JcLqVsAdapter(basketballdetailbean, 0);//初始化适配器
                        mHostJcLqVsAdapter = new JcLqVsAdapter(basketballdetailbean, 1);
                        mVisitJcLqVsAdapter = new JcLqVsAdapter(basketballdetailbean, 2);

                        hostListv.setAdapter(mHostJcLqVsAdapter);
                        visitListv.setAdapter(mVisitJcLqVsAdapter);
                        baseListv.setAdapter(mVsJcLqVsAdapter);

                        ViewUtils.setListViewHeightBasedOnChildren(hostListv);


                        ViewUtils.setListViewHeightBasedOnChildren(baseListv);

                        ViewUtils.setListViewHeightBasedOnChildren(visitListv);
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

                loadingDialog = new LoadingDialog(BasketBallShujufenxiActivity.this, "正在加载...");
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


    @OnClick(R.id.titlebar_back_ll)
        //返回按钮
    void back() {
        finish();
    }

    private void initFlag(BasketBallDetailBean basketballdetailbean) {
        if (null != basketballdetailbean && basketballdetailbean.getData() != null && basketballdetailbean.getData().getVsList().size() > 0) {
            baseFlag = true;
        } else {
            baseFlag = false;
        }
        if (baseFlag) {
            baseopenIm.setVisibility(View.INVISIBLE);
            basecloseIm.setVisibility(View.VISIBLE);
            baseLl.setVisibility(View.VISIBLE);

            baseHostTv.setText("主队" + basketballdetailbean.getData().getVsWin().get(0) + "胜");
//            basePingTv.setText(basketballdetailbean.getData().getVsWin().get(1) + "平");
            baseVisitTv.setText("客队" + basketballdetailbean.getData().getVsWin().get(2) + "胜");
        } else {
            basecloseIm.setVisibility(View.INVISIBLE);
            baseopenIm.setVisibility(View.VISIBLE);
            baseLl.setVisibility(View.GONE);
        }


        if (null != basketballdetailbean && basketballdetailbean.getData().getHostList() != null && basketballdetailbean.getData().getHostList().size() > 0) {
            hostFlag = true;
        } else {
            hostFlag = false;
        }
        if (hostFlag) {
            hostopenIm.setVisibility(View.INVISIBLE);
            hostcloseIm.setVisibility(View.VISIBLE);
            hostLl.setVisibility(View.VISIBLE);
            hostHostTv.setText("主队" + "  " + basketballdetailbean.getData().getHostName());//主队队名
            TextUtils.setStrColor(hostHostTv, "主队" + "  " + basketballdetailbean.getData().getHostName(), "主队", BaseApplication.getApplication().getResources().getColor(R.color.black_gray));
            visitNameTv.setText("客队" + "  " + basketballdetailbean.getData().getVisitName());//客队队名
            TextUtils.setStrColor(visitNameTv, "客队" + "  " + basketballdetailbean.getData().getVisitName(), "客队", BaseApplication.getApplication().getResources().getColor(R.color.black_gray));

            hostWinTv.setText(basketballdetailbean.getData().getHostWin().get(0) + "胜");
//            hostPingTv.setText(basketballdetailbean.getData().getHostWin().get(1) + "平");
            hostLoseTv.setText(basketballdetailbean.getData().getHostWin().get(2) + "负");


            Picasso.with(BasketBallShujufenxiActivity.this).load(basketballdetailbean.getData().getHostLogo()).into(hostHostIm);
            Picasso.with(BasketBallShujufenxiActivity.this).load(basketballdetailbean.getData().getVisitLogo()).into(hostVisitIm);

            visitWinTv.setText(basketballdetailbean.getData().getVisitWin().get(0) + "胜");
//            visitPingTv.setText(basketballdetailbean.getData().getVisitWin().get(1) + "平");
            visitLoseTv.setText(basketballdetailbean.getData().getVisitWin().get(2) + "负");

        } else {
            hostcloseIm.setVisibility(View.INVISIBLE);
            hostopenIm.setVisibility(View.VISIBLE);
            hostLl.setVisibility(View.GONE);
        }
    }


    private class MyOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.analysis_base_rl://两队
                    if (baseFlag) {
                        baseFlag = false;
                    } else {
                        baseFlag = true;
                    }
                    if (baseFlag) {
                        baseopenIm.setVisibility(View.INVISIBLE);
                        basecloseIm.setVisibility(View.VISIBLE);
                        if (null != basketballdetailbean.getData() && null != basketballdetailbean.getData().getVsList() && basketballdetailbean.getData().getVsList().size() > 0) {
                            baseLl.setVisibility(View.VISIBLE);
                        }
                    } else {
                        basecloseIm.setVisibility(View.INVISIBLE);
                        baseopenIm.setVisibility(View.VISIBLE);
                        baseLl.setVisibility(View.GONE);
                    }
                    break;
                case R.id.host_rl://主队
                    if (hostFlag) {
                        hostFlag = false;
                    } else {
                        hostFlag = true;
                    }
                    if (hostFlag) {
                        hostopenIm.setVisibility(View.INVISIBLE);
                        hostcloseIm.setVisibility(View.VISIBLE);
                        if (null != basketballdetailbean.getData() && null != basketballdetailbean.getData().getHostList() && basketballdetailbean.getData().getHostList().size() > 0) {
                            hostLl.setVisibility(View.VISIBLE);
                        }
                    } else {
                        hostcloseIm.setVisibility(View.INVISIBLE);
                        hostopenIm.setVisibility(View.VISIBLE);
                        hostLl.setVisibility(View.GONE);
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
        return "篮球赛事数据分析";
    }
}
