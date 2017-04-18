package shlottery.gov.cn.lotterycenter.ui.activity;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.MatchFtDetailBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.adapter.JcZqInjuryAdapter;
import shlottery.gov.cn.lotterycenter.ui.adapter.JcZqVsAdapter;
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

public class MatchAnalyticsActivity extends BaseActivity {


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
    @BindView(R.id.descri_tv)
    TextView descriTv;
    @BindView(R.id.descri_ll)
    LinearLayout descriLl;
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
    @BindView(R.id.statistics_live_tv)
    TextView statisticsLiveTv;
    @BindView(R.id.injury_close_im)
    ImageView injuryCloseIm;
    @BindView(R.id.injury_open_im)
    ImageView injuryOpenIm;
    @BindView(R.id.statistics_rl)
    RelativeLayout statisticsRl;
    @BindView(R.id.injury_host_im)
    ImageView injuryHostIm;
    @BindView(R.id.injury_visit_im)
    ImageView injuryVisitIm;
    @BindView(R.id.statistics_listv)
    ListView statisticsListv;
    @BindView(R.id.injury_ll)
    LinearLayout injuryLl;
    @BindView(R.id.activity_matchanalytics)
    LinearLayout activityMatchanalytics;
    @BindView(R.id.analysis_scr)
    ScrollView analysisScr;
    @BindView(R.id.injury_hostname_tv)
    TextView injuryHostnameTv;
    @BindView(R.id.injury_visitname_tv)
    TextView injuryVisitnameTv;
    @BindView(R.id.hostrank_tv)
    TextView hostrankTv;
    @BindView(R.id.visitrank_tv)
    TextView visitrankTv;
    private MatchFtDetailBean matchftdetailbean;
    private String id = "1173123";//比赛id

    private boolean baseFlag;//当为true表示数据不为空,默认展开 两队
    private boolean hostFlag;//当为true表示数据不为空,默认展开 两队各自
    private boolean injuryFlag;//当为true表示数据不为空,默认展开 伤病

    private JcZqVsAdapter mVsJcZqVsAdapter;//两队最近六场对战
    private JcZqVsAdapter mHostJcZqVsAdapter;//主队最近六场
    private JcZqVsAdapter mVisitJcZqVsAdapter;//客队最近六场
    private JcZqInjuryAdapter mJcZqInjuryAdapter;//足球伤病

    private MyOnclickListener myOnclickListener;
    private LoadingDialog loadingDialog;//加载的dialog

    @Override
    protected void initView() {
        setContentView(R.layout.activity_matchanalyticsl);
        ButterKnife.bind(this);
        titlebarTv.setText("分析");
        mainCalendar.setImageResource(R.mipmap.shuaxin);
        mainCalendar.setVisibility(View.VISIBLE);
        loadData();
        myOnclickListener = new MyOnclickListener();
        mainCalendar.setOnClickListener(myOnclickListener);
        analysisBaseRl.setOnClickListener(myOnclickListener);
        hostRl.setOnClickListener(myOnclickListener);
        statisticsRl.setOnClickListener(myOnclickListener);
    }


    //从网络加载数据
    private void loadData() {
        OkGo.get(UrlManager.getJczqMatchInfo(this, id)).cacheMode(CacheMode.NO_CACHE).connTimeOut(5000).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                matchftdetailbean = gson.fromJson(s, MatchFtDetailBean.class);
                if (matchftdetailbean != null) {
                    if (matchftdetailbean.getRet() == 100) {
                        analysisScr.setVisibility(View.VISIBLE);
                        matchnameTv.setText( matchftdetailbean.getData().getLeagueName() + " " + matchftdetailbean.getData().getLeagueLevel());
                        livematchtimeTv.setText(DateUtils.getPl5DateAndTime(matchftdetailbean.getData().getMatchTime() * 1000));
                        Picasso.with(MatchAnalyticsActivity.this).load(matchftdetailbean.getData().getHostLogo()).into(hostimageIm);
                        Picasso.with(MatchAnalyticsActivity.this).load(matchftdetailbean.getData().getVisitLogo()).into(visitimageIm);
                        if (!StringUtils.isEmpty(matchftdetailbean.getData().getHost90Goal())) {
                            hostScoreTv.setText(matchftdetailbean.getData().getHost90Goal());
                            visitscoreTv.setText(matchftdetailbean.getData().getVisit90Goal());
                            liveMaohaoTv.setText(":");
                            hostScoreTv.setVisibility(View.VISIBLE);
                            visitscoreTv.setVisibility(View.VISIBLE);
                            liveMaohaoTv.setVisibility(View.VISIBLE);

                        }
                        if (!StringUtils.isEmpty(matchftdetailbean.getData().getFullScore())) {//加时等描述,有就显示
                            descriTv.setText(matchftdetailbean.getData().getFullScore());
                            descriLl.setVisibility(View.VISIBLE);
                        }
                        if (!StringUtils.isEmpty(matchftdetailbean.getData().getHostHalfGoal())) {//半场比分
                            haltscoreTv.setText("HT " + matchftdetailbean.getData().getHostHalfGoal() + ":" + matchftdetailbean.getData().getVisitHalfGoal());
                            haltscoreTv.setVisibility(View.VISIBLE);
                        }
                        if (!StringUtils.isEmpty(matchftdetailbean.getData().getHost120Goal())) {//加时比分
                            extrascoreTv.setText("ET " + matchftdetailbean.getData().getHost120Goal() + ":" + matchftdetailbean.getData().getVisit120Goal());
                            extrascoreTv.setVisibility(View.VISIBLE);

                        }

                        if (!StringUtils.isEmpty(matchftdetailbean.getData().getHostRank()) && !matchftdetailbean.getData().getHostRank().equals("0")) {
                            hostrankTv.setText("[" + matchftdetailbean.getData().getHostRank() + "]");
                        }
                        if (!StringUtils.isEmpty(matchftdetailbean.getData().getHostRank()) && !matchftdetailbean.getData().getVisitRank().equals("0")) {
                            visitrankTv.setText("[" + matchftdetailbean.getData().getVisitRank() + "]");
                        }

                        if (!StringUtils.isEmpty(matchftdetailbean.getData().getHostPkGoal())) {//点球比分
                            int hostpk;//主场pk比分
                            int visitpk;//客场pk比分  点球 加上全场 加加时
                            if (!StringUtils.isEmpty(matchftdetailbean.getData().getHost120Goal())) {
                                hostpk = Integer.parseInt(matchftdetailbean.getData().getHost90Goal()) + Integer.parseInt(matchftdetailbean.getData().getHost120Goal()) + Integer.parseInt(matchftdetailbean.getData().getHostPkGoal());
                                visitpk = Integer.parseInt(matchftdetailbean.getData().getHost90Goal()) + Integer.parseInt(matchftdetailbean.getData().getVisit120Goal()) + Integer.parseInt(matchftdetailbean.getData().getVisitPkGoal());
                            } else {
                                hostpk = Integer.parseInt(matchftdetailbean.getData().getHost90Goal()) + Integer.parseInt(matchftdetailbean.getData().getHostPkGoal());
                                visitpk = Integer.parseInt(matchftdetailbean.getData().getHost90Goal()) + Integer.parseInt(matchftdetailbean.getData().getVisitPkGoal());
                            }
                            pointscoreTv.setText("PK " + hostpk + ":" + visitpk);
                            pointscoreTv.setVisibility(View.VISIBLE);
                        }
                        hostteamTv.setText(matchftdetailbean.getData().getHostName());
                        visitteamTv.setText(matchftdetailbean.getData().getVisitName());
                        matchStausTv.setText(matchftdetailbean.getData().getStatusName());
                        setMayChangeData(matchftdetailbean);//设置比赛状态
                        initFlag(matchftdetailbean);

                        mVsJcZqVsAdapter = new JcZqVsAdapter(matchftdetailbean, 0);//初始化适配器
                        mHostJcZqVsAdapter = new JcZqVsAdapter(matchftdetailbean, 1);
                        mVisitJcZqVsAdapter = new JcZqVsAdapter(matchftdetailbean, 2);
                        mJcZqInjuryAdapter = new JcZqInjuryAdapter(matchftdetailbean);
                        Logger.e(mJcZqInjuryAdapter.getCount()+"长度");

                        hostListv.setAdapter(mHostJcZqVsAdapter);
                        visitListv.setAdapter(mVisitJcZqVsAdapter);
                        baseListv.setAdapter(mVsJcZqVsAdapter);
                        statisticsListv.setAdapter(mJcZqInjuryAdapter);

                        ViewUtils.setListViewHeightBasedOnChildren(hostListv);

                        ViewUtils.setListViewHeightBasedOnChildren(statisticsListv);

                        ViewUtils.setListViewHeightBasedOnChildren(baseListv);

                        ViewUtils.setListViewHeightBasedOnChildren(visitListv);
                    } else {
                        if (StringUtils.isEmpty(matchftdetailbean.getMsg())) {
                            UIUtils.showToastSafe("服务器累坏了,请稍后重试...");
                        } else {
                            UIUtils.showToastSafe(matchftdetailbean.getMsg());
                        }

                    }
                }
            }

            @Override
            public void onBefore(BaseRequest request) {
                super.onBefore(request);

                loadingDialog = new LoadingDialog(MatchAnalyticsActivity.this, "正在加载...");
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


    private void setMayChangeData(MatchFtDetailBean matchftdetailbean) {
        //设置比赛状态
        switch (Integer.parseInt(matchftdetailbean.getData().getStatusId())) {
            case 1://未开赛
                matchStausTv.setBackgroundResource(R.mipmap.bb_score_time_yellow);
                matchStausTv.setText("未开赛");
//                matchStausTv.setText(DateUtils.getDayHourM(l));
                break;
            case 2:
                matchStausTv.setText("待定");
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusblue);
                break;
            case 3:
                // 上半场
               /* int mtf = DateUtils.formatFirstHalfLiveScoreMatchStatus(
                        new Date(), l);// 当前比赛时间差值
                if (mtf > 45) {
                    matchStausTv.setText("45+'");
                } else if (mtf < 0) {
                    matchStausTv.setText("上");
                } else {
                    matchStausTv.setText("上" + mtf + "'");
                }*/
                matchStausTv.setText("上半场");
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                break;
            case 4:
                // 下半场
               /* int mts = DateUtils.formatFirstHalfLiveScoreMatchStatus(
                        new Date(), l);// 当前比赛时间差值
                int n = mts + 46;
                if ((mts + 46) > 90) {
                    matchStausTv.setText("90+'");
                } else if (mts + 46 < 0) {
                    matchStausTv.setText("下");
                } else {
                    matchStausTv.setText("下" + n + "'");
                }*/
                matchStausTv.setText("下半场");
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                break;
            case 5:
                // 半场休息
                matchStausTv.setText("半场");
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusgreen);
                break;
            case 6:
                // 全场结束
               /* if (overType == OverType.NORMAL) {
                    matchStausTv.setText(DateTimeFormatUtil.formatJZBDSFBeginTime(fb.getMatchTime()));
                } else if (overType == OverType.ADDTIME) {
                    matchStausTv.setText(DateTimeFormatUtil.formatJZBDSFBeginTime(fb.getMatchTime()));
                } else {
                    matchStausTv.setText(DateTimeFormatUtil.formatJZBDSFBeginTime(fb.getMatchTime()));
                }*/
//                matchStausTv.setText(DateUtils.getDayHourM(l));
                matchStausTv.setText("完场");
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusred);
                break;
            case 7:
                //加时
                matchStausTv.setText("加时");
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusred);
            case 8:
                //加时上
                matchStausTv.setText("加时上");
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusred);
            case 9:
                matchStausTv.setText("加时下");
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusred);
                //加时下
            case 10:
                //加时完
                matchStausTv.setText("点球");
//                matchStausTv.setText(DateUtils.getDayHourM(l));
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusred);
                break;
            case 11:
                // 点球
                matchStausTv.setText("点球");
//                matchStausTv.setText(DateUtils.getDayHourM(l));
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusred);
                break;
            case 12:
                // 暂停
                matchStausTv.setText("暂停");
                // 图片是蓝色
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusblue);
                break;
            case 13:
                // 腰斩
                matchStausTv.setText("腰斩");
                // 图片是蓝色
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusblue);
                break;
            case 14:
                // 取消
                matchStausTv.setText("取消");
                // 图片是蓝色
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusblue);
                break;
            case 15:
                // 改期
                matchStausTv.setText("改期");
                // 图片是蓝色
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusblue);
                break;
            case 16:
                // 延期
                matchStausTv.setText("延期");
                // 图片是蓝色
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusblue);
                break;
            case 17:
                // 90分钟完场
//                matchStausTv.setText(DateUtils.getDayHourM(l));
                matchStausTv.setText("完场");
                matchStausTv.setBackgroundResource(R.mipmap.matchstatusred);
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

    private void initFlag(MatchFtDetailBean matchftdetailbean) {
        if (null != matchftdetailbean && matchftdetailbean.getData() != null && matchftdetailbean.getData().getVsList().size() > 0) {
            baseFlag = true;
        } else {
            baseFlag = false;
        }
        if (baseFlag) {
            baseopenIm.setVisibility(View.INVISIBLE);
            basecloseIm.setVisibility(View.VISIBLE);
            baseLl.setVisibility(View.VISIBLE);

            baseHostTv.setText("主队" + matchftdetailbean.getData().getVsWin().get(0) + "胜");
            basePingTv.setText(matchftdetailbean.getData().getVsWin().get(1) + "平");
            baseVisitTv.setText("客队" + matchftdetailbean.getData().getVsWin().get(2) + "胜");
        } else {
            basecloseIm.setVisibility(View.INVISIBLE);
            baseopenIm.setVisibility(View.VISIBLE);
            baseLl.setVisibility(View.GONE);
        }

        if (null != matchftdetailbean && matchftdetailbean.getData() != null) {
            if (matchftdetailbean.getData().getHostInjury().size() > 0 || matchftdetailbean.getData().getVisitInjury().size() > 0) {
                injuryFlag = true;
            }else {
                injuryFlag = false;
            }
        } else {
            injuryFlag = false;
        }
        if (injuryFlag) {
            injuryOpenIm.setVisibility(View.INVISIBLE);
            injuryCloseIm.setVisibility(View.VISIBLE);
            injuryLl.setVisibility(View.VISIBLE);
            Picasso.with(MatchAnalyticsActivity.this).load(matchftdetailbean.getData().getHostLogo()).into(injuryHostIm);
            Picasso.with(MatchAnalyticsActivity.this).load(matchftdetailbean.getData().getVisitLogo()).into(injuryVisitIm);
            injuryHostnameTv.setText(matchftdetailbean.getData().getHostName());
            injuryVisitnameTv.setText(matchftdetailbean.getData().getVisitName());
        } else {
            injuryCloseIm.setVisibility(View.INVISIBLE);
            injuryOpenIm.setVisibility(View.VISIBLE);
            injuryLl.setVisibility(View.GONE);
        }

        if (null != matchftdetailbean && matchftdetailbean.getData().getHostList() != null && matchftdetailbean.getData().getHostList().size() > 0) {
            hostFlag = true;
        } else {
            hostFlag = false;
        }
        if (hostFlag) {
            hostopenIm.setVisibility(View.INVISIBLE);
            hostcloseIm.setVisibility(View.VISIBLE);
            hostLl.setVisibility(View.VISIBLE);
            hostHostTv.setText("主队" + "  " + matchftdetailbean.getData().getHostName());//主队队名
            TextUtils.setStrColor(hostHostTv, "主队" + "  " + matchftdetailbean.getData().getHostName(), "主队", BaseApplication.getApplication().getResources().getColor(R.color.black_gray));
            visitNameTv.setText("客队" + "  " + matchftdetailbean.getData().getVisitName());//客队队名
            TextUtils.setStrColor(visitNameTv, "客队" + "  " + matchftdetailbean.getData().getVisitName(), "客队", BaseApplication.getApplication().getResources().getColor(R.color.black_gray));

            hostWinTv.setText(matchftdetailbean.getData().getHostWin().get(0) + "胜");
            hostPingTv.setText(matchftdetailbean.getData().getHostWin().get(1) + "平");
            hostLoseTv.setText(matchftdetailbean.getData().getHostWin().get(2) + "负");


            Picasso.with(MatchAnalyticsActivity.this).load(matchftdetailbean.getData().getHostLogo()).into(hostHostIm);
            Picasso.with(MatchAnalyticsActivity.this).load(matchftdetailbean.getData().getVisitLogo()).into(hostVisitIm);

            visitWinTv.setText(matchftdetailbean.getData().getVisitWin().get(0) + "胜");
            visitPingTv.setText(matchftdetailbean.getData().getVisitWin().get(1) + "平");
            visitLoseTv.setText(matchftdetailbean.getData().getVisitWin().get(2) + "负");

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
                        if (null != matchftdetailbean.getData() && null != matchftdetailbean.getData().getVsList() && matchftdetailbean.getData().getVsList().size() > 0) {
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
                        if (null != matchftdetailbean.getData() && null != matchftdetailbean.getData().getHostList() && matchftdetailbean.getData().getHostList().size() > 0) {
                            hostLl.setVisibility(View.VISIBLE);
                        }
                    } else {
                        hostcloseIm.setVisibility(View.INVISIBLE);
                        hostopenIm.setVisibility(View.VISIBLE);
                        hostLl.setVisibility(View.GONE);
                    }
                    break;
                case R.id.statistics_rl://伤病
                    if (injuryFlag) {
                        injuryFlag = false;
                    } else {
                        injuryFlag = true;
                    }
                    if (injuryFlag) {
                        injuryOpenIm.setVisibility(View.INVISIBLE);
                        injuryCloseIm.setVisibility(View.VISIBLE);
                        if (null != matchftdetailbean.getData()) {
                            if ((null != matchftdetailbean.getData().getVisitInjury() && matchftdetailbean.getData().getVisitInjury().size() > 0) || (null != matchftdetailbean.getData().getHostInjury() && matchftdetailbean.getData().getHostInjury().size() > 0))
                                injuryLl.setVisibility(View.VISIBLE);
                        }
                    } else {
                        injuryCloseIm.setVisibility(View.INVISIBLE);
                        injuryOpenIm.setVisibility(View.VISIBLE);
                        injuryLl.setVisibility(View.GONE);
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
        return "数据分析";
    }
}
