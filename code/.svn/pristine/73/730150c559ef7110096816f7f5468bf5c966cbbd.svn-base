package shlottery.gov.cn.lotterycenter.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.Base.BaseFragment;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.JCScoreToCompareBean;
import shlottery.gov.cn.lotterycenter.bean.JzScoreMatchBean;
import shlottery.gov.cn.lotterycenter.bean.MatchIssueBean;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.activity.BasketBallDataActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.JcScoreFiltrateActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.MatchDetailActivity;
import shlottery.gov.cn.lotterycenter.ui.adapter.JLScoreMatchAdapter;
import shlottery.gov.cn.lotterycenter.ui.adapter.JZScoreMatchAdapter;
import shlottery.gov.cn.lotterycenter.ui.adapter.JcScorePopupWindowAdapter;
import shlottery.gov.cn.lotterycenter.ui.calculatebouns.adapter.CalculateTimeListAdapter;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback.JcScoreEventBus;
import shlottery.gov.cn.lotterycenter.ui.view.widget.MyWheelView;
import shlottery.gov.cn.lotterycenter.ui.view.widget.OnWheelChangedListener;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingPager;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.DensityUtil;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

import static shlottery.gov.cn.lotterycenter.R.id.match_explsv;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/25 16:34
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class MatchFragment extends BaseFragment {
    @BindView(R.id.matchtitle_tv)
    TextView           matchtitleTv;
    @BindView(R.id.match_emptyView)
    TextView           matchemptyView;
    @BindView(R.id.titlebar_indicatorup)
    ImageView          titlebarIndicatorup;
    @BindView(R.id.titlebar_indicatordown)
    ImageView          titlebarIndicatordown;
    @BindView(R.id.titlebar_title)
    LinearLayout       titlebarTitle;
    @BindView(R.id.filtrate_tv)
    ImageView          filtrateTv;
    @BindView(R.id.main_calendar)
    ImageView          mainCalendar;
    @BindView(R.id.match_titleBar)
    RelativeLayout     matchTitleBar;
    @BindView(R.id.matchall_rb)
    RadioButton        matchallRb;
    @BindView(R.id.matchnostart_rb)
    RadioButton        matchnostartRb;
    @BindView(R.id.matching_rb)
    RadioButton        matchingRb;
    @BindView(R.id.filished_rb)
    RadioButton        filishedRb;
    @BindView(R.id.match_rg)
    RadioGroup         matchRg;
    @BindView(match_explsv)
    ExpandableListView matchExplsv;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private int                  type;//根据type的不同  决定是 0足球还是 1篮球
    private JZScoreMatchAdapter  jzscorematchadapter;
    private JLScoreMatchAdapter  jlscorematchadapter;
    private AlertDialog          mTimePickDialog;//时间选择框dialog
    private MyWheelView          mTimeWheelPicker;//竞彩比分时间选择器
    private MatchOnclickListener matchonclicklistener;//点击监听器
    private int                  mMatchStatus;//比赛的状态  分全部 未开赛 进行中 已完场
    private boolean mFlag = false;//标记顶部的展开状态
    private PopupWindow popupWindow;//篮球 足球
    private PopupWindow popupWindowToast;//进球红黄牌
    private int         timepickselPosition;//记录滑轮滚动最后的位置
    private int selectedPositon = 0;
    private String         lotId;//竞彩id
    private MatchIssueBean matchissuebean;//赛事列表
    private List<String>   issuseList;
    private ArrayList<String> mLeagueListIs    = new ArrayList<>();//一级联赛
    private ArrayList<String> mLeagueListFalse = new ArrayList<>();//非一级联赛
    private ArrayList<String> mLeagueList      = new ArrayList<>();//筛选选中的比赛
    private String            issueNo          = "";//期号 (为空时请求默认期号,2到3场比赛,  指定期号返回指定期号数据)
    private Timer                     timer;//计时器 ,刷新数据
    private JcScorePopupWindowAdapter mJcScorePopupWindowAdapter;
    private JCScoreToCompareBean mJCScoreToCompareBean = new JCScoreToCompareBean();
    private JzScoreMatchBean     mJzScoreMatchBean1    = new JzScoreMatchBean();
    private JzScoreMatchBean     mJzScoreMatchBean2    = new JzScoreMatchBean();
    private String matchId;//竞彩id
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                loadData(type, issueNo, true);
            }
        }
    };

    @Override
    protected View createLoadedView() {
        View view = UIUtils.inflate(R.layout.fragment_match);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        matchonclicklistener = new MatchOnclickListener();
        filtrateTv.setOnClickListener(matchonclicklistener);
        mainCalendar.setOnClickListener(matchonclicklistener);
        titlebarTitle.setOnClickListener(matchonclicklistener);
        matchallRb.setOnClickListener(matchonclicklistener);
        matchnostartRb.setOnClickListener(matchonclicklistener);
        matchingRb.setOnClickListener(matchonclicklistener);
        filishedRb.setOnClickListener(matchonclicklistener);
        matchRg.check(R.id.matchall_rb);
        changeTitleState(mFlag);
        matchExplsv.setGroupIndicator(null);
        matchExplsv.setOnChildClickListener(matchonclicklistener);
        refreshLayout.setColorSchemeResources(R.color.homeblue);//设置进度动画的颜色
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(type, issueNo, false);
            }
        });
        loadIssueNum(type);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(JcScoreEventBus evenbus) {
        if (null != evenbus.getmLeagueList() && !(mLeagueList.containsAll(evenbus.getmLeagueList()) && evenbus
                .getmLeagueList().containsAll(mLeagueList))) {
            mLeagueList.clear();
            mLeagueList = evenbus.getmLeagueList();
            mMatchStatus = evenbus.getmMatchStatus();

            if (type == 0) {
                if (null != jzscorematchadapter.getmMatchDataBean()) {
                    jzscorematchadapter.getmMatchDataBean().clear();
                }
                jzscorematchadapter.setmMatchDataBean(jzscorematchadapter.getMatchDataBean(mMatchStatus, mLeagueList));
                toggleEmptyView(jzscorematchadapter.getMatchDataBean(mMatchStatus, mLeagueList));
                jzscorematchadapter.setmSortAsTime(true);
                jzscorematchadapter.notifyDataSetChanged();
                chooseExpandItem(jzscorematchadapter);
            } else {
                if (null != jlscorematchadapter.getmMatchDataBean()) {
                    jlscorematchadapter.getmMatchDataBean().clear();
                }
                jlscorematchadapter.setmMatchDataBean(jlscorematchadapter.getMatchDataBean(mMatchStatus, mLeagueList));
                toggleEmptyView(jlscorematchadapter.getMatchDataBean(mMatchStatus, mLeagueList));
                jlscorematchadapter.setmSortAsTime(true);
                jlscorematchadapter.notifyDataSetChanged();
                chooseExpandItem(jlscorematchadapter);
            }
        }
    }

    @Override
    public void refreshData() {
        loadData(type, issueNo, false);
    }

    private void loadData(final int type, final String issueNo, final boolean autoRefresh) {
        LogUtils.i("MatchFragment params:" + issueNo);
        OkGo.get(UrlManager.getJcLive(getActivity(), type, issueNo)).cacheMode(CacheMode.NO_CACHE).connTimeOut(5000)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        mJzScoreMatchBean2 = gson.fromJson(s, JzScoreMatchBean.class);
                        if (mJzScoreMatchBean2 != null && mJzScoreMatchBean2.getRet().equals("100")) {
                            //                    matchemptyView.setVisibility(View.GONE);
                            if (type == 0) {
                                //足球
                                if (issueNo.equals("")) {//如果是足球并且返回到当前期时  开始比赛数据对比
                                    setJCScoreToCompareBean(mJzScoreMatchBean1, mJzScoreMatchBean2,
                                            mJCScoreToCompareBean);
                                }

                                sortLeagueAsIs(mJzScoreMatchBean2);
                                if (jzscorematchadapter == null) {
                                    jzscorematchadapter = new JZScoreMatchAdapter(mJzScoreMatchBean2);
                                    jzscorematchadapter.setmMatchDataBean(jzscorematchadapter.getMatchDataBean
                                            (mMatchStatus,
                                                    mLeagueList));
                                    matchExplsv.setAdapter(jzscorematchadapter);
                                } else {
                                    jzscorematchadapter.setmJzScoreMatchBean(mJzScoreMatchBean2);
                                    jzscorematchadapter.setmMatchDataBean(jzscorematchadapter.getMatchDataBean
                                            (mMatchStatus,
                                                    mLeagueList));
                                }
                                toggleEmptyView(jzscorematchadapter.getMatchDataBean(mMatchStatus, mLeagueList));
                                jzscorematchadapter.notifyDataSetChanged();

                                if (!autoRefresh) {
                                    chooseExpandItem(jzscorematchadapter);
                                    refreshLayout.setRefreshing(false);
                                }
                            } else {
                                sortLeagueAsIs(mJzScoreMatchBean2);
                                if (jlscorematchadapter == null) {
                                    jlscorematchadapter = new JLScoreMatchAdapter(mJzScoreMatchBean2);
                                    jlscorematchadapter.setmMatchDataBean(jlscorematchadapter.getMatchDataBean
                                            (mMatchStatus,
                                                    mLeagueList));
                                    matchExplsv.setAdapter(jlscorematchadapter);
                                } else {
                                    jlscorematchadapter.setmJzScoreMatchBean(mJzScoreMatchBean2);
                                    jlscorematchadapter.setmMatchDataBean(jlscorematchadapter.getMatchDataBean
                                            (mMatchStatus,
                                                    mLeagueList));
                                }
                                toggleEmptyView(jlscorematchadapter.getMatchDataBean(mMatchStatus, mLeagueList));
                                jlscorematchadapter.notifyDataSetChanged();

                                if (!autoRefresh) {
                                    chooseExpandItem(jlscorematchadapter);
                                    if (refreshLayout != null) {
                                        refreshLayout.setRefreshing(false);
                                    }
                                }
                            }
                            matchRg.setOnCheckedChangeListener(matchonclicklistener);
                        } else {
                            //                    matchemptyView.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        if (!autoRefresh) {
                            if (refreshLayout != null) {
                                refreshLayout.setRefreshing(true);
                            }
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        if (!autoRefresh) {
                            if (refreshLayout != null) {
                                refreshLayout.setRefreshing(false);
                            }
                        }
                        UIUtils.showToastSafe("网络异常,请检查网络设置");
                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                    }
                });
    }

    //选择展开第几个groupitem
    private void chooseExpandItem(BaseExpandableListAdapter adapter) {
      /*  if (jlscorematchadapter.getmMatchDataBean() != null && jlscorematchadapter.getmMatchDataBean().size() > 0) {
            List<String> daylist = jlscorematchadapter.getmJzScoreMatchBean().getData().getDays();
            //获取当前需要展开的条目的序号
            int p = 0;
            long time = Long.parseLong(DateUtils.formatDateNoLine(System.currentTimeMillis()));

            for (int i = 0; i < jlscorematchadapter.getmMatchDataBean().size(); i++) {
                if ((Long.parseLong(daylist.get(i)) >= time)) {
                    p = i;
                    break;
                } else {
                    if (daylist.get(0).equals(jlscorematchadapter.getmMatchDataBean().get(i).get(0).getMatchDay())) {
                        p = i;
                    }
                }
            }
            for (int i = 0; i < jlscorematchadapter.getGroupCount(); i++) {
                if (i == p) {
                    matchExplsv.expandGroup(p);
                } else {
                    matchExplsv.collapseGroup(i);
                }
            }
        }*/
        for (int i = 0; i < adapter.getGroupCount(); i++) {

            matchExplsv.expandGroup(i);

        }
    }

    //初始化日期选择
    protected void showTimePickerDialog() {
        // TODO Auto-generated method stub
        View mDialogView = UIUtils.inflate(R.layout.dialog_issue_picker);
        if (null == mTimePickDialog) {
            mTimePickDialog = new AlertDialog.Builder(getActivity()).create();
        } else {
            mTimePickDialog = null;//避免复用dialog导致MywheelView当前可见条目与数据不符合
            mTimePickDialog = new AlertDialog.Builder(getActivity()).create();
        }
        mTimePickDialog.setView(mDialogView, 0, 0, 0, 0);

        TextView mTimeCancle = (TextView) mDialogView.findViewById(R.id.issuepicker_cancle_tv);
        TextView mTimeOk = (TextView) mDialogView.findViewById(R.id.issuepicker_ok_tv);
        TextView mTimeBackTo = (TextView) mDialogView.findViewById(R.id.issuepicker_backto_tv);

        mTimeCancle.setOnClickListener(matchonclicklistener);//取消
        mTimeOk.setOnClickListener(matchonclicklistener);//确定
        mTimeBackTo.setOnClickListener(matchonclicklistener);//返回当前期

        mTimeWheelPicker = (MyWheelView) mDialogView.findViewById(R.id.time_pick_whl);
        List<String> mIssueList = new ArrayList<>();
        if (matchissuebean == null) {
            return;
        }
        MatchIssueBean.DataBean data = matchissuebean.getData();
        if (data == null) {
            return;
        }
        if (data.getIssueList() == null) {
            return;
        }
        for (int i = 0; i < data.getIssueList().size(); i++) {
            if (matchissuebean.getData().getIssueList().get(i).getIsLive() == 1) {
                mIssueList.add(matchissuebean.getData().getIssueList().get(i).getIssueNo() + "期 •");
            } else {
                mIssueList.add(matchissuebean.getData().getIssueList().get(i).getIssueNo() + "期");
            }
        }
        //        JcScoreTimeListAdapter mTimeWheelAdapter = new JcScoreTimeListAdapter(this, mTimeList);
        CalculateTimeListAdapter mTimeWheelAdapter = new CalculateTimeListAdapter(getActivity(), mIssueList);
        mTimeWheelPicker.setViewAdapter(mTimeWheelAdapter);
        mTimeWheelPicker.setCurrentItem(selectedPositon);
        mTimeWheelPicker.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(MyWheelView wheel, int oldValue, int newValue) {
                timepickselPosition = newValue;
            }
        });
        mTimePickDialog.show();
    }


    @Override
    protected LoadingPager.LoadResult Load() {
        return check(0);
    }


    private class MatchOnclickListener implements View.OnClickListener, RadioGroup.OnCheckedChangeListener,
            ExpandableListView.OnChildClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.filtrate_tv://筛选
                    Intent mIntent = new Intent(getActivity(), JcScoreFiltrateActivity.class);
                    mIntent.putStringArrayListExtra("mLeagueListIs", mLeagueListIs);//热门联赛
                    mIntent.putStringArrayListExtra("mLeagueListFalse", mLeagueListFalse);//非热门联赛
                    mIntent.putExtra("mSortAsTime", true);
                    mIntent.putExtra("mMatchStatus", mMatchStatus);
                    mIntent.putStringArrayListExtra("mLeagueList", mLeagueList);
                    startActivity(mIntent);
                    break;
                case R.id.main_calendar://时间选择框
                    showTimePickerDialog();
                    break;
                case R.id.issuepicker_cancle_tv://时间选择器,取消
                    mTimePickDialog.dismiss();
                    break;
                case R.id.issuepicker_ok_tv://时间选择器,确定
                    if (type == 0) {
                        mJzScoreMatchBean1.setData(null);
                    }
                    mLeagueListIs.clear();//清除筛选数据
                    mLeagueListFalse.clear();
                    mLeagueList.clear();
                    selectedPositon = timepickselPosition;
                    issueNo = issuseList.get(timepickselPosition);
                    if (Integer.parseInt(DateUtils.formatDateNoLine(System.currentTimeMillis())) > Integer.parseInt
                            (issueNo)) {
                        if (timer != null) {//先取消倒计时,在重新开启
                            timer.cancel();
                            timer = null;
                        }
                    }
                    loadData(type, issueNo, false);
                    mTimePickDialog.dismiss();
                    break;
                case R.id.issuepicker_backto_tv://返回当前期
                    if (type == 0) {
                        mJzScoreMatchBean1.setData(null);
                    }
                    mLeagueListIs.clear();//清除筛选数据
                    mLeagueListFalse.clear();
                    mLeagueList.clear();

                    selectedPositon = 0;
                    issueNo = "";
                    loadData(type, "", false);
                    if (timer == null) {
                        timer = new Timer();
                    }
                    startTimer(issueNo);
                    mTimePickDialog.dismiss();
                    break;

                case R.id.basketball_button_tv://篮球
                    //胜负彩取消筛选联赛按钮,其他彩种显示筛选
                    filtrateTv.setVisibility(View.VISIBLE);
                    if (timer != null) {//先取消倒计时,在重新开启
                        timer.cancel();
                        timer = null;
                    }
                    if (jzscorematchadapter != null) {
                        jzscorematchadapter.clearData();
                    }
                    jzscorematchadapter = null;
                    mLeagueListIs.clear();//清除筛选数据
                    mLeagueListFalse.clear();
                    mLeagueList.clear();

                    matchtitleTv.setText("竞彩篮球");
                    issueNo = "";//期号重置为当前期
                    startTimer(issueNo);
                    selectedPositon = 0;//滑轮重置为默认
                    type = 1;
                    popupWindow.dismiss();
                    matchRg.check(R.id.matchall_rb);
                    loadIssueNum(type);
                    break;
                case R.id.football_button_tv://足球
                    //胜负彩取消筛选联赛按钮,其他彩种显示筛选
                    filtrateTv.setVisibility(View.VISIBLE);
                    mJzScoreMatchBean1.setData(null);
                    if (jlscorematchadapter != null) {
                        jlscorematchadapter.clearData();
                    }
                    jlscorematchadapter = null;
                    if (timer != null) {//先取消倒计时,在重新开启
                        timer.cancel();
                        timer = null;
                    }
                    mLeagueListIs.clear();//清除筛选数据
                    mLeagueListFalse.clear();
                    mLeagueList.clear();

                    matchtitleTv.setText("竞彩足球");
                    issueNo = "";//期号重置为当前期
                    startTimer(issueNo);
                    selectedPositon = 0;//滑轮重置为默认
                    type = 0;
                    loadIssueNum(type);
                    matchRg.check(R.id.matchall_rb);
                    popupWindow.dismiss();
                    break;

                //暂时取消胜负彩
                //                case R.id.sfc_button_tv: //胜负彩14场
                //                    //胜负彩取消筛选联赛按钮
                //                    filtrateTv.setVisibility(View.INVISIBLE);
                //                    matchtitleTv.setText("胜负彩14场");
                //                    popupWindow.dismiss();
                //                    break;
                case R.id.cancle_button_tv://取消
                    popupWindow.dismiss();
                    break;
                case R.id.titlebar_title://展开 收起
                    mFlag = true;
                    changeTitleState(mFlag);
                    showPopupWindow();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.matchall_rb://全部
                    mMatchStatus = Configs.MATCHALL;
                    break;
                case R.id.matchnostart_rb://未开赛
                    mMatchStatus = Configs.MATCHNOSTART;//未开赛
                    break;
                case R.id.matching_rb://进行中
                    mMatchStatus = Configs.MATCHSTARTING;//比赛进行中
                    break;
                case R.id.filished_rb://已完成
                    mMatchStatus = Configs.MATCHFINISH;//全场结束
                    break;
                default:
                    break;
            }

            if (type == 0) {
                if (jzscorematchadapter != null) {
                    jzscorematchadapter.setmMatchDataBean(jzscorematchadapter.getMatchDataBean(mMatchStatus,
                            mLeagueList));
                    toggleEmptyView(jzscorematchadapter.getMatchDataBean(mMatchStatus, mLeagueList));
                    chooseExpandItem(jzscorematchadapter);
                    jzscorematchadapter.notifyDataSetChanged();
                }
            } else {
                if (jlscorematchadapter != null) {
                    jlscorematchadapter.setmMatchDataBean(jlscorematchadapter.getMatchDataBean(mMatchStatus,
                            mLeagueList));
                    toggleEmptyView(jlscorematchadapter.getMatchDataBean(mMatchStatus, mLeagueList));
                    chooseExpandItem(jlscorematchadapter);
                    jlscorematchadapter.notifyDataSetChanged();
                }
            }
        }

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            Intent intent;
            if (type == 0) {
                //0 足球
                intent = new Intent(getActivity(), MatchDetailActivity.class);
                matchId = jzscorematchadapter.getmMatchDataBean().get(groupPosition).get(childPosition).getMatchId();
                intent.putExtra("matchId", matchId);
            } else {
                //1 篮球
                intent = new Intent(getActivity(), BasketBallDataActivity.class);
                matchId = jlscorematchadapter.getmMatchDataBean().get(groupPosition).get(childPosition).getMatchId();
                intent.putExtra("matchId", matchId);
            }
            startActivity(intent);
            return true;
        }
    }

    private void changeTitleState(boolean flag) {
        if (flag) {
            titlebarIndicatordown.setVisibility(View.GONE);
            titlebarIndicatorup.setVisibility(View.VISIBLE);
            mFlag = false;
        } else {
            titlebarIndicatordown.setVisibility(View.VISIBLE);
            titlebarIndicatorup.setVisibility(View.GONE);
            mFlag = true;
        }
    }

    private void showPopupWindow() {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.jc_score_popuwindow, null);
        // 设置按钮的点击事件
        TextView mTimeButton = (TextView) contentView.findViewById(R.id.football_button_tv);
        TextView mSaiShiButton = (TextView) contentView.findViewById(R.id.basketball_button_tv);
        //        TextView mSfcButton = (TextView) contentView.findViewById(R.id.sfc_button_tv);
        TextView mCancleButton = (TextView) contentView.findViewById(R.id.cancle_button_tv);
        mTimeButton.setOnClickListener(matchonclicklistener);
        mSaiShiButton.setOnClickListener(matchonclicklistener);
        //        mSfcButton.setOnClickListener(matchonclicklistener);
        mCancleButton.setOnClickListener(matchonclicklistener);
        if (null == popupWindow) {
            popupWindow = new PopupWindow(contentView,
                    FrameLayout.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
            //添加进出动画
        }
        popupWindow.setAnimationStyle(R.style.popupAnimation);
        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);
        //           popupWindow.setBackgroundDrawable(new BitmapDrawable());
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_match, null);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, DensityUtil.dip2px(getActivity(), 10));
        backgroundAlpha((float) 0.5);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((float) 1);
                mFlag = false;
                changeTitleState(mFlag);
            }
        });
    }

    /* 设置添加屏幕的背景透明度
     * @param bgAlpha
     * 让popupWindow实现dialog效果
     */

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }


    //加载赛事期号
    private void loadIssueNum(final int type) {
        switch (type) {
            case 0:
                lotId = "jczq";
                break;
            case 1:
                lotId = "jclq";
                break;
            default:
                break;
        }
        OkGo.get(UrlManager.getLiveIssue(getActivity(), lotId)).cacheMode(CacheMode.NO_CACHE).connTimeOut(5000)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        matchissuebean = gson.fromJson(s, MatchIssueBean.class);
                        if (matchissuebean != null) {
                            if (matchissuebean.getRet() == 100) {
                                issuseList = new ArrayList<String>();
                                for (int i = 0; i < matchissuebean.getData().getIssueList().size(); i++) {
                                    issuseList.add(matchissuebean.getData().getIssueList().get(i).getIssueNo());
                                }
                                loadData(type, issueNo, false);
                            } else {
                                UIUtils.showToastSafe(matchissuebean.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                    }
                });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {//判断当前fragment是否可见,可见的话调用倒计时刷新
        super.setUserVisibleHint(isVisibleToUser);
        Logger.e("isVisibleToUser");
        if (isVisibleToUser) {
            startTimer(issueNo);
        } else {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        }
    }

   /* @Override
    public void onResume() {
        startTimer();
        super.onResume();
    }

    @Override
    public void onPause() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        super.onPause();
    }*/

    private void startTimer(String issueNo) {
        if (issueNo.equals("")) {
            if (null == timer) {
                timer = new Timer();
            }
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mHandler.sendEmptyMessage(1);
                }
            }, 15000, 15000);
        }
    }

    private void showPopupWindow(JCScoreToCompareBean mJCScoreToCompareBean) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.jcscoretoast_popuwindow, null);
        // 设置按钮的点击事件
       /* TextView mTimeButton = (TextView) contentView.findViewById(R.id.time_button_tv);
        TextView mSaiShiButton = (TextView) contentView.findViewById(R.id.saishi_button_tv);
        TextView mCancleButton = (TextView) contentView.findViewById(R.id.cancle_button_tv);*/
        ListView listView = (ListView) contentView.findViewById(R.id.jcscore_toastscore);
        WindowManager manager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        mJcScorePopupWindowAdapter = new JcScorePopupWindowAdapter(mJCScoreToCompareBean, getActivity());
        listView.setAdapter(mJcScorePopupWindowAdapter);
        if (null == popupWindowToast) {
            popupWindowToast = new PopupWindow(contentView,
                    4 * manager.getDefaultDisplay().getWidth() / 5, WindowManager.LayoutParams.WRAP_CONTENT, true);
            //添加进出动画
        }
        popupWindowToast.setAnimationStyle(R.style.popupAnimation);
        // 使其聚集
        popupWindowToast.setFocusable(true);
        // 设置允许在外点击消失
        popupWindowToast.setOutsideTouchable(true);
        popupWindowToast.setBackgroundDrawable(new BitmapDrawable());
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main, null);
        //        popupWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);

        //        backgroundAlpha((float) 0.5);//设置透明度  设计不要背景变化
/*        popupWindowToast.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((float) 1);//消失时取消透明度
            }
        });*/
        //获取xoff
        int xpos = manager.getDefaultDisplay().getWidth() / 2 - popupWindowToast.getWidth() / 2;//如果popupWindow不指定宽度,
        // 此处获取的popupWindow的宽度为-2
        popupWindowToast.showAsDropDown(getActivity().findViewById(R.id.match_rg), xpos, 0);
        popupWindowToast.update();
        final Timer timer1 = new Timer();//控制popupWindow显示的时间

        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                UIUtils.runInMainThread(new Runnable() {
                    @Override
                    public void run() {
                        popupWindowToast.dismiss();
                        timer1.cancel();
                    }
                });

            }
        }, 4000);
    }

    private void toggleEmptyView(List<List<JzScoreMatchBean.DataBean.MatchsBean>> data) {
        LogUtils.i("toggleEmptyView data:" + data);
        if (data != null && !data.isEmpty()) {
            matchemptyView.setVisibility(View.GONE);
        } else {
            matchemptyView.setVisibility(View.VISIBLE);
        }
        LogUtils.i("toggleEmptyView matchemptyView:" + matchemptyView.getVisibility());
    }

    private JzScoreMatchBean setJCScoreToCompareBean(JzScoreMatchBean mJcbean1, JzScoreMatchBean mJcbean2,
                                                     JCScoreToCompareBean mJCScoreToCompareBean) {

        if (mJcbean1 == null || mJcbean1.getData() == null || mJcbean1.getData().getMatchs() == null || mJcbean1
                .getData().getMatchs().size() == 0) {
            initScoreBean(mJcbean1, mJcbean2);
            Logger.e("原始数据为空");
        } else {
            Logger.e("原始数据非空");
            if (!mJcbean1.getData().getMatchs().get(0).getMatchId().equals(mJcbean2.getData().getMatchs().get(0)
                    .getMatchId())) {//开始日期变化说明说句变化了
                mJcbean1 = new JzScoreMatchBean();
                Logger.e("原始数据与本次数据不等");
            } else {
                Logger.e("原始数据与本次数据相等");
                if (mJcbean1.getData().getMatchs().size() > 0) {

                    List<JCScoreToCompareBean.MatchToCompareDataBean> list = new ArrayList<>();

                    for (int i = 0; i < mJcbean1.getData().getMatchs().size(); i++) {
                        int m = 0;
                        int m1 = 0;
                        int n = 0;
                        int n1 = 0;
                        int p = 0;
                        int p1 = 0;
                        int q = 0;
                        int q1 = 0;

                        if (!StringUtils.isEmpty(mJcbean1.getData().getMatchs().get(i).getHostGoal())) {
                            m = Integer.parseInt(mJcbean1.getData().getMatchs().get(i).getHostGoal());//主队刷新前分数
                        }
                        if (!StringUtils.isEmpty(mJcbean1.getData().getMatchs().get(i).getVisitGoal())) {
                            n = Integer.parseInt(mJcbean1.getData().getMatchs().get(i).getVisitGoal());//客队刷新前分数
                        }
                        if (!StringUtils.isEmpty(mJcbean1.getData().getMatchs().get(i).getHostRedCard())) {
                            p = Integer.parseInt(mJcbean1.getData().getMatchs().get(i).getHostRedCard());//主队刷新前红牌
                        }
                        if (!StringUtils.isEmpty(mJcbean1.getData().getMatchs().get(i).getVisitRedCard())) {
                            q = Integer.parseInt(mJcbean1.getData().getMatchs().get(i).getVisitRedCard());//客队刷新前红牌
                        }


                        if (!StringUtils.isEmpty(mJcbean2.getData().getMatchs().get(i).getHostGoal())) {
                            m1 = Integer.parseInt(mJcbean2.getData().getMatchs().get(i).getHostGoal());//主队刷新后分数
                        }
                        if (!StringUtils.isEmpty(mJcbean2.getData().getMatchs().get(i).getVisitGoal())) {
                            n1 = Integer.parseInt(mJcbean2.getData().getMatchs().get(i).getVisitGoal());//客队刷新后分数
                        }
                        if (!StringUtils.isEmpty(mJcbean2.getData().getMatchs().get(i).getHostRedCard())) {
                            p1 = Integer.parseInt(mJcbean2.getData().getMatchs().get(i).getHostRedCard());//主队刷新后红牌
                        }
                        if (!StringUtils.isEmpty(mJcbean2.getData().getMatchs().get(i).getVisitRedCard())) {
                            q1 = Integer.parseInt(mJcbean2.getData().getMatchs().get(i).getVisitRedCard());//客队刷新后红牌
                        }

                        if (m1 > m || n1 > n || p1 > p || q1 > q) {
                            Logger.e("原始数据发生了变化");
                            if (null == mJCScoreToCompareBean) {
                                mJCScoreToCompareBean = new JCScoreToCompareBean();
                            }
                            JCScoreToCompareBean.MatchToCompareDataBean matchToCompareDataBean = new
                                    JCScoreToCompareBean.MatchToCompareDataBean();
                            matchToCompareDataBean.setLeagueName(mJcbean2.getData().getMatchs().get(i).getLeagueName());
                            matchToCompareDataBean.setHostName(mJcbean2.getData().getMatchs().get(i).getHostName());
                            matchToCompareDataBean.setVisitName(mJcbean2.getData().getMatchs().get(i).getVisitName());
                            matchToCompareDataBean.setHostGoal(mJcbean2.getData().getMatchs().get(i).getHostGoal());
                            matchToCompareDataBean.setVisitGoal(mJcbean2.getData().getMatchs().get(i).getVisitGoal());
                            matchToCompareDataBean.setMatchTime(mJcbean2.getData().getMatchs().get(i).getMatchTime());
                            matchToCompareDataBean.setHalfTime(mJcbean2.getData().getMatchs().get(i).getHalfTime());
                            matchToCompareDataBean.setStatusId(mJcbean2.getData().getMatchs().get(i).getStatusId());
                            if (m1 - m > 0) {
                                Logger.e("原始数据发生了变化__主队进球");
                                matchToCompareDataBean.setIshomegoal(true);//主队进球
                                matchToCompareDataBean.setIsvisitgoal(false);//客队未进球
                            }
                            if (n1 - n > 0) {
                                Logger.e("原始数据发生了变化__客队进球");
                                matchToCompareDataBean.setIsvisitgoal(true);//客队进球
                                matchToCompareDataBean.setIshomegoal(false);//主队未进球
                            }
                            if (p1 - p > 0) {
                                Logger.e("原始数据发生了变化__主队红牌");
                                matchToCompareDataBean.setIshomeredcard(true);//主队得到红牌
                                matchToCompareDataBean.setIsvisitredcard(false);
                            } else if (q1 - q > 0) {
                                Logger.e("原始数据发生了变化__客队红牌");
                                matchToCompareDataBean.setIsvisitredcard(true);//客队得到红牌
                                matchToCompareDataBean.setIshomeredcard(false);
                            }
                            list.add(matchToCompareDataBean);
                        }
                        if (i == mJcbean1.getData().getMatchs().size() - 1) {//for循环最后一项进行判断
                            mJCScoreToCompareBean.setMatchData(list);
                            if (null != mJCScoreToCompareBean.getMatchData() && mJCScoreToCompareBean.getMatchData()
                                    .size() > 0) {//如果数据为空或长度wei0  不弹popupWindow,否则会弹空的
                                showPopupWindow(mJCScoreToCompareBean);
                            }
                            initScoreBean(mJcbean1, mJcbean2);
                        }
                    }
                }
            }
        }
        return mJcbean1;
    }

    /**
     * @param mJcbean1 需要初始化与新数据对比的JCScoreDefaultBean
     * @param mJcbean2 访问服务器获取到的新JCScoreDefaultBean
     */
    private void initScoreBean(JzScoreMatchBean mJcbean1, JzScoreMatchBean mJcbean2) {
        if (mJcbean1 == null) {
            mJcbean1 = new JzScoreMatchBean();
        }
        JzScoreMatchBean.DataBean dataBean = new JzScoreMatchBean.DataBean();
        dataBean.setMatchs(mJcbean2.getData().getMatchs());
        mJcbean1.setData(dataBean);
    }

    /**
     * @param jzscorematchbean 按照是否是一级联赛将比赛进行分类
     *                         以便赛事筛选  设计取消热门非热门区分
     */
    private void sortLeagueAsIs(JzScoreMatchBean jzscorematchbean) {
        mLeagueListIs.clear();
        mLeagueListFalse.clear();
        for (JzScoreMatchBean.DataBean.LeaguesBean s : jzscorematchbean.getData().getLeagues()
                ) {
           /* if (s.getIsHot().equals("1")) {
                mLeagueListIs.add(s.getLeagueName());
            } else {
                mLeagueListFalse.add(s.getLeagueName());
            }*/
            mLeagueListIs.add(s.getLeagueName());
        }
    }


    @Override
    protected String getTitle() {
        return "赛事首页";
    }
}
