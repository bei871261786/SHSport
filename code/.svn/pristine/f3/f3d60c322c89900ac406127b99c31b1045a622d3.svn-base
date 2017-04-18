package shlottery.gov.cn.lotterycenter.ui.activity;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import shlottery.gov.cn.lotterycenter.bean.MatchFtDetailBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.adapter.JcLiveEventAdapter;
import shlottery.gov.cn.lotterycenter.ui.adapter.JcLiveTjAdapter;
import shlottery.gov.cn.lotterycenter.ui.adapter.JcLiveZrAdapter;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingDialog;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;
import shlottery.gov.cn.lotterycenter.utils.ViewUtils;



/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-07-0007 15:04
 * 描    述：赛事详情页面
 * 修订历史：进度需要事件和数据分析分开写
 * ================================================
 */

public class MatchDetailActivity extends BaseActivity {

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
    @BindView(R.id.event_live_tv)
    TextView eventLiveTv;
    @BindView(R.id.eventclose_im)
    ImageView eventcloseIm;
    @BindView(R.id.eventopen_im)
    ImageView eventopenIm;
    @BindView(R.id.event_rl)
    RelativeLayout eventRl;
    @BindView(R.id.event_listv)
    ListView eventListv;
    @BindView(R.id.team_live_tv)
    TextView teamLiveTv;
    @BindView(R.id.teamclose_im)
    ImageView teamcloseIm;
    @BindView(R.id.teamopen_im)
    ImageView teamopenIm;
    @BindView(R.id.team_rl)
    RelativeLayout teamRl;
    @BindView(R.id.team_listv)
    ListView teamListv;
    @BindView(R.id.statistics_live_tv)
    TextView statisticsLiveTv;
    @BindView(R.id.statistics_close_im)
    ImageView statisticsCloseIm;
    @BindView(R.id.statistics_open_im)
    ImageView statisticsOpenIm;
    @BindView(R.id.statistics_rl)
    RelativeLayout statisticsRl;
    @BindView(R.id.statisticson_im)
    ImageView statisticsonIm;
    @BindView(R.id.statisticsoff_im)
    ImageView statisticsoffIm;
    @BindView(R.id.statistics_onoff_rl)
    RelativeLayout statisticsOnoffRl;
    @BindView(R.id.statistics_listv)
    ListView statisticsListv;
    @BindView(R.id.activity_matchdetail)
    LinearLayout activityMatchdetail;
    @BindView(R.id.hostrank_tv)
    TextView hostrankTv;
    @BindView(R.id.visitrank_tv)
    TextView visitrankTv;
    private MatchFtDetailBean matchftdetailbean;
    private String id = "1173123";//比赛id

    private boolean teamFlag;//当为true表示数据不为空,默认展开 阵容
    private boolean eventFlag;//当为true表示数据不为空,默认展开 赛事
    private boolean AnalyzeFlag;//当为true表示数据不为空,默认展开 统计

    private JcLiveZrAdapter mJcLiveZrAdapter;//阵容的适配器
    private JcLiveTjAdapter mJcLiveTjAdapter;//统计的适配器
    private JcLiveEventAdapter mJcLiveEventAdapter;//事件的适配器

    private MyOnclickListener myOnclickListener;
    private LoadingDialog loadingDialog;//加载的dialog

    @Override
    protected void initView() {
        setContentView(R.layout.activity_matchdetail);
        ButterKnife.bind(this);
        titlebarTv.setText("赛事");
        mainCalendar.setImageResource(R.mipmap.shuaxin);
        mainCalendar.setVisibility(View.VISIBLE);
        loadData();
        myOnclickListener = new MyOnclickListener();
        mainCalendar.setOnClickListener(myOnclickListener);
        eventRl.setOnClickListener(myOnclickListener);
        teamRl.setOnClickListener(myOnclickListener);
        statisticsRl.setOnClickListener(myOnclickListener);
    }

    @Override
    protected void init() {
        id = getIntent().getStringExtra("matchId");
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
                        matchnameTv.setText(matchftdetailbean.getData().getLeagueName() + " " + matchftdetailbean.getData().getLeagueLevel());
                        livematchtimeTv.setText(DateUtils.formatDateAndTime(matchftdetailbean.getData().getMatchTime() * 1000));
                        Picasso.with(MatchDetailActivity.this).load(matchftdetailbean.getData().getHostLogo()).into(hostimageIm);
                        Picasso.with(MatchDetailActivity.this).load(matchftdetailbean.getData().getVisitLogo()).into(visitimageIm);
                        String statusId = matchftdetailbean.getData().getStatusId();
                        int red = BaseApplication.getApplication().getResources().getColor(R.color.match_vs_red);
                        int green = BaseApplication.getApplication().getResources().getColor(R.color.green);
                        hostScoreTv.setTextColor(statusId.equals("6")?red:green);
                        visitscoreTv.setTextColor(statusId.equals("6")?red:green);
                        liveMaohaoTv.setTextColor(statusId.equals("6")?red:green);
                        if (!StringUtils.isEmpty(matchftdetailbean.getData().getHost90Goal())) {
                            hostScoreTv.setText(matchftdetailbean.getData().getHost90Goal());
                            visitscoreTv.setText(matchftdetailbean.getData().getVisit90Goal());
                            liveMaohaoTv.setText(":");
                            hostScoreTv.setVisibility(View.VISIBLE);
                            visitscoreTv.setVisibility(View.VISIBLE);
                            liveMaohaoTv.setVisibility(View.VISIBLE);

                        }
                        if (!StringUtils.isEmpty(matchftdetailbean.getData().getFullScore())) {
                            descriTv.setText(matchftdetailbean.getData().getFullScore());
                            descriLl.setVisibility(View.VISIBLE);
                        }
                        if (!StringUtils.isEmpty(matchftdetailbean.getData().getHostHalfGoal())) {
                            haltscoreTv.setText("HT " + matchftdetailbean.getData().getHostHalfGoal() + ":" + matchftdetailbean.getData().getVisitHalfGoal());
                            haltscoreTv.setVisibility(View.VISIBLE);
                        }
                        if (!StringUtils.isEmpty(matchftdetailbean.getData().getHost120Goal())) {
                            extrascoreTv.setText("ET " + matchftdetailbean.getData().getHost120Goal() + ":" + matchftdetailbean.getData().getVisit120Goal());
                            extrascoreTv.setVisibility(View.VISIBLE);

                        }

                        if (!StringUtils.isEmpty(matchftdetailbean.getData().getHostRank()) && !matchftdetailbean.getData().getHostRank().equals("0")) {
                            hostrankTv.setText("(" + matchftdetailbean.getData().getHostRank() + ")");
                        }
                        if (!StringUtils.isEmpty(matchftdetailbean.getData().getHostRank()) && !matchftdetailbean.getData().getVisitRank().equals("0")) {
                            visitrankTv.setText("(" + matchftdetailbean.getData().getVisitRank() + ")");
                        }

                        if (!StringUtils.isEmpty(matchftdetailbean.getData().getHostPkGoal())) {
                            int hostpk;//主场pk比分
                            int visitpk;//客场pk比分  点球 加上全场 加加时
                            hostpk = Integer.parseInt(matchftdetailbean.getData().getHostPkGoal());
                            visitpk = Integer.parseInt(matchftdetailbean.getData().getVisitPkGoal());
                            pointscoreTv.setText("PK " + hostpk + ":" + visitpk);
                            pointscoreTv.setVisibility(View.VISIBLE);
                        }
                        hostteamTv.setText(matchftdetailbean.getData().getHostName());
                        visitteamTv.setText(matchftdetailbean.getData().getVisitName());
                        matchStausTv.setText(matchftdetailbean.getData().getStatusName());
                        setMayChangeData(matchftdetailbean);

                        initFlag(matchftdetailbean);

                        mJcLiveZrAdapter = new JcLiveZrAdapter(matchftdetailbean);//初始化适配器
                        mJcLiveTjAdapter = new JcLiveTjAdapter(matchftdetailbean);
                        mJcLiveEventAdapter = new JcLiveEventAdapter(matchftdetailbean);

                        teamListv.setAdapter(mJcLiveZrAdapter);
                        statisticsListv.setAdapter(mJcLiveTjAdapter);
                        eventListv.setAdapter(mJcLiveEventAdapter);

                        ViewUtils.setListViewHeightBasedOnChildren(teamListv);

                        ViewUtils.setListViewHeightBasedOnChildren(statisticsListv);

                        ViewUtils.setListViewHeightBasedOnChildren(eventListv);
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

                loadingDialog = new LoadingDialog(MatchDetailActivity.this, "正在加载...");
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


    @OnClick(R.id.titlebar_back_ll)
        //返回按钮
    void back() {
        finish();
    }

    private void initFlag(MatchFtDetailBean matchftdetailbean) {
        if (null != matchftdetailbean && matchftdetailbean.getData() != null && matchftdetailbean.getData().getHostFirst().size() > 0) {
            teamFlag = true;
        } else {
            teamFlag = false;
        }
        if (teamFlag) {
            teamopenIm.setVisibility(View.INVISIBLE);
            teamcloseIm.setVisibility(View.VISIBLE);
            teamListv.setVisibility(View.VISIBLE);
        } else {
            teamcloseIm.setVisibility(View.INVISIBLE);
            teamopenIm.setVisibility(View.VISIBLE);
            teamListv.setVisibility(View.GONE);
        }
        if (null != matchftdetailbean && matchftdetailbean.getData().getHostFirst() != null && matchftdetailbean.getData().getStatList().size() > 0) {
            AnalyzeFlag = true;
        } else {
            AnalyzeFlag = false;
        }
        if (AnalyzeFlag) {
            statisticsOpenIm.setVisibility(View.INVISIBLE);
            statisticsCloseIm.setVisibility(View.VISIBLE);
            statisticsListv.setVisibility(View.VISIBLE);
        } else {
            statisticsCloseIm.setVisibility(View.INVISIBLE);
            statisticsOpenIm.setVisibility(View.VISIBLE);
            statisticsListv.setVisibility(View.GONE);
        }

        if (null != matchftdetailbean && matchftdetailbean.getData().getLogList() != null && matchftdetailbean.getData().getLogList().size() > 0) {
            eventFlag = true;
        } else {
            eventFlag = false;
        }
        if (eventFlag) {
            eventopenIm.setVisibility(View.INVISIBLE);
            eventcloseIm.setVisibility(View.VISIBLE);
            eventListv.setVisibility(View.VISIBLE);
        } else {
            eventcloseIm.setVisibility(View.INVISIBLE);
            eventopenIm.setVisibility(View.VISIBLE);
            eventListv.setVisibility(View.GONE);
        }
    }


    private class MyOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.event_rl://事件
                    if (eventFlag) {
                        eventFlag = false;
                    } else {
                        eventFlag = true;
                    }
                    if (eventFlag) {
                        eventopenIm.setVisibility(View.INVISIBLE);
                        eventcloseIm.setVisibility(View.VISIBLE);
                        if (null != matchftdetailbean.getData() && null != matchftdetailbean.getData().getLogList() && matchftdetailbean.getData().getLogList().size() > 0) {
                            eventListv.setVisibility(View.VISIBLE);
                        }
                    } else {
                        eventcloseIm.setVisibility(View.INVISIBLE);
                        eventopenIm.setVisibility(View.VISIBLE);
                        eventListv.setVisibility(View.GONE);
                    }
                    break;
                case R.id.team_rl://阵容
                    if (teamFlag) {
                        teamFlag = false;
                    } else {
                        teamFlag = true;
                    }
                    if (teamFlag) {
                        teamopenIm.setVisibility(View.INVISIBLE);
                        teamcloseIm.setVisibility(View.VISIBLE);
                        if (null != matchftdetailbean.getData() && null != matchftdetailbean.getData().getHostFirst() && matchftdetailbean.getData().getHostFirst().size() > 0) {
                            teamListv.setVisibility(View.VISIBLE);
                        }
                    } else {
                        teamcloseIm.setVisibility(View.INVISIBLE);
                        teamopenIm.setVisibility(View.VISIBLE);
                        teamListv.setVisibility(View.GONE);
                    }
                    break;
                case R.id.statistics_rl://分析
                    if (AnalyzeFlag) {
                        AnalyzeFlag = false;
                    } else {
                        AnalyzeFlag = true;
                    }
                    if (AnalyzeFlag) {
                        statisticsOpenIm.setVisibility(View.INVISIBLE);
                        statisticsCloseIm.setVisibility(View.VISIBLE);
                        if (null != matchftdetailbean.getData() && null != matchftdetailbean.getData().getStatList() && matchftdetailbean.getData().getStatList().size() > 0) {
                            statisticsListv.setVisibility(View.VISIBLE);
                        }
                    } else {
                        statisticsCloseIm.setVisibility(View.INVISIBLE);
                        statisticsOpenIm.setVisibility(View.VISIBLE);
                        statisticsListv.setVisibility(View.GONE);
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
        return "足球赛事详情析";
    }
}
