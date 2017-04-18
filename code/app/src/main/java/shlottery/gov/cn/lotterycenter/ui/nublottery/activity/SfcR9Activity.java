package shlottery.gov.cn.lotterycenter.ui.nublottery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
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
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.Sf14Bean;
import shlottery.gov.cn.lotterycenter.bean.SfcDingdanBean;
import shlottery.gov.cn.lotterycenter.callback.IDataCallBack;
import shlottery.gov.cn.lotterycenter.callback.SfcEventbus;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.Sf14LsvAdapter;
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.SfcTitleGdvAdapter;
import shlottery.gov.cn.lotterycenter.ui.view.GridViewWithoutScroll;
import shlottery.gov.cn.lotterycenter.utils.AsyncTaskUtils;
import shlottery.gov.cn.lotterycenter.utils.CalculateStage;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-07-0007 10:58
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class SfcR9Activity extends BaseActivity {

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
    @BindView(R.id.syx5_issue_tv)
    TextView syx5IssueTv;
    @BindView(R.id.syx5_stoptime_tv)
    TextView syx5StoptimeTv;
    @BindView(R.id.syx5_stoptimetext_tv)
    TextView syx5StoptimetextTv;
    @BindView(R.id.r9_listview)
    ListView r9Listview;
    @BindView(R.id.r9_swl)
    SwipeRefreshLayout r9Swl;
    @BindView(R.id.sfc_deleteall_tv)
    TextView sfcDeleteallTv;
    @BindView(R.id.sfcBottom_matchCount_txt)
    TextView sfcBottomMatchCountTxt;
    @BindView(R.id.sfcBottom_sub_txt)
    TextView sfcBottomSubTxt;
    @BindView(R.id.sfc_sum_bt)
    TextView sfcSumBt;
    @BindView(R.id.activity_r9)
    LinearLayout activityR9;
    private List<List<Sf14Bean.DataBean.IssueListBean.MatchListBean>> MatchLists = new ArrayList<>();
    private List<String> issues = new ArrayList<>();//期号
    private List<String> stopTimes = new ArrayList<>();//期号
    private boolean mFlag = false;//默认隐藏
    private MyOnclikListener mMyOnclikListener;
    private SfcTitleGdvAdapter mSfcTitleGdvAdapter;
    private Sf14LsvAdapter mSf14LsvAdapter;
    private int mSelectedPostion = 0;//选中的期次
    private int ActivityType;//区分任选九和胜负14
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            int money = CacluteZhushu(mSf14LsvAdapter.getmSf14Beans());
//            int money = 10;
//            if (money > 0) {
//                initBottom(true, money + "串" + " " + 2 * money + "元", View.GONE);
//            } else {
//                initBottom(false, "0注 0元", View.VISIBLE);
//            }
        }
    };

    //初始化底部
    private void initBottom(boolean enabled, String text, int visible, int i) {
        sfcSumBt.setEnabled(enabled);
        sfcBottomMatchCountTxt.setText(text);
        sfcBottomSubTxt.setVisibility(visible);
        TextUtils.setStrColor(sfcBottomMatchCountTxt, i + "注" + " " + 2 * i + "元", 2 * i + "", BaseApplication.getApplication().getResources().getColor(R.color.select_red));
    }

    int count = 0;//选中的比赛数目
    int zhushu = 0;//注数
    int DanNum = 0;
    private List<Integer> checkNum = new ArrayList<>();//每行选中个数的集合
    private List<Integer> danSelectNum = new ArrayList<>();//胆所在行选中个数的集合
    private List<Integer> unDanNum = new ArrayList<>();//非胆所在行选中个数的集合

    private Sf14Bean mSf14Bean;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ren9);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        titlebarTv.setText("足彩-任选9");
        syx5StoptimetextTv.setText("投注截止时间:");
        mMyOnclikListener = new MyOnclikListener();
        titlebarBackLl.setOnClickListener(mMyOnclikListener);
        titlebarTitle.setOnClickListener(mMyOnclikListener);
        sfcDeleteallTv.setOnClickListener(mMyOnclikListener);
        sfcSumBt.setOnClickListener(mMyOnclikListener);
        r9Swl.setColorSchemeResources(R.color.homeblue);
        syx5Gridview.setOnItemClickListener(mMyOnclikListener);
        changeTitleState(mFlag);
        sfcBottomSubTxt.setText("请至少选择9场比赛");
        sfcSumBt.setEnabled(false);//提交按钮默认不可点击
        refreshData();
        r9Swl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(SfcEventbus messageEvent) {
        DanNum = 0;
        count = 0;
        Logger.e(DanNum + "胆的数量");
        for (int i = 0; i < mSf14LsvAdapter.getmSf14Beans().size(); i++) {
            if (!(mSf14LsvAdapter.getmSf14Beans().get(i).getCheckedHashMap().get(0)) && (!mSf14LsvAdapter.getmSf14Beans().get(i).getCheckedHashMap().get(1)) && (!mSf14LsvAdapter.getmSf14Beans().get(i).getCheckedHashMap().get(2))) {
                mSf14LsvAdapter.getmSf14Beans().get(i).setChecked(false);
                mSf14LsvAdapter.getmSf14Beans().get(i).setCanChecked(false);
            } else {
                mSf14LsvAdapter.getmSf14Beans().get(i).setCanChecked(true);
                count++;
            }
            if (mSf14LsvAdapter.getmSf14Beans().get(i).isChecked()) {
                DanNum++;
            }
        }
//        count = CacluteZhushu(mSf14LsvAdapter.getmSf14Beans());
        if (count <= 9) {//注数小于9的时候 不能设置胆
            for (int i = 0; i < mSf14LsvAdapter.getmSf14Beans().size(); i++) {
                mSf14LsvAdapter.getmSf14Beans().get(i).setChecked(false);
                mSf14LsvAdapter.getmSf14Beans().get(i).setCanChecked(false);
            }
        } else {
            if (DanNum >= 8 || DanNum >= count - 1) {//胆的最大数量为8,并且胆的数量必须小于注数
                for (int i = 0; i < mSf14LsvAdapter.getmSf14Beans().size(); i++) {
                    Logger.e(i + "position---" + mSf14LsvAdapter.getmSf14Beans().get(i).isChecked() + "----是否被选中---" + mSf14LsvAdapter.getmSf14Beans().get(i).isCanChecked() + "---是否可以被选中");
                    if (mSf14LsvAdapter.getmSf14Beans().get(i).isCanChecked() && !mSf14LsvAdapter.getmSf14Beans().get(i).isChecked()) {
                        Logger.e(DanNum + "更改单的状态");
                        mSf14LsvAdapter.getmSf14Beans().get(i).setCanChecked(false);
                    }
                    Logger.e(i + "---position---" + mSf14LsvAdapter.getmSf14Beans().get(i).isChecked() + "----是否被选中---" + mSf14LsvAdapter.getmSf14Beans().get(i).isCanChecked() + "---是否可以被选中");
                }
            }
        }
        mSf14LsvAdapter.setCountNum(count);
        mSf14LsvAdapter.setDanNum(DanNum);
        mSf14LsvAdapter.notifyDataSetChanged();
        AsyncTaskUtils.doAsync(new IDataCallBack<Integer>() {

            @Override
            public void onTaskBefore() {

            }

            @Override
            public Integer onTasking(Void... params) {
                int money = CacluteZhushu(mSf14LsvAdapter.getmSf14Beans());
                return money;
            }

            @Override
            public void onTaskAfter(Integer result) {
                if (result > 0) {
                    initBottom(true, result + "注" + " " + 2 * result + "元", View.GONE, result);
                } else {
                    initBottom(false, "0注 0元", View.VISIBLE, result);
                }
            }
        });
        /*int money = CacluteZhushu(mSf14LsvAdapter.getmSf14Beans());
        if (money > 0) {
            initBottom(true, money + "串" + " " + 2 * money + "元", View.GONE);
        } else {
            initBottom(false, "0注 0元", View.VISIBLE);
        }*/
    }

    //改变titlebar下拉按钮显示
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

    private void refreshData() {
        String lotid = "sfc";
        OkGo.get(UrlManager.getSaleIssueUrl(this, lotid)).cacheMode(CacheMode.NO_CACHE).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Logger.e(s + "请求成功");
                Gson gson = new Gson();
                mSf14Bean = gson.fromJson(s, Sf14Bean.class);
                if (null != mSf14Bean) {
                    MatchLists.clear();
                    issues.clear();
                    stopTimes.clear();
                    for (int i = 0; i < mSf14Bean.getData().getIssueList().size(); i++) {
                        MatchLists.add(mSf14Bean.getData().getIssueList().get(i).getMatchList());
                        issues.add("第" + mSf14Bean.getData().getIssueList().get(i).getIssueNo() + "期");
                        long time = mSf14Bean.getData().getIssueList().get(i).getStopTime();
                        String stopTime = DateUtils.getPl5DateAndTime(time * 1000);
                        stopTimes.add(stopTime);
                    }
                    initPosition(mSf14Bean);
                    mSfcTitleGdvAdapter = new SfcTitleGdvAdapter(SfcR9Activity.this, issues);
                    mSfcTitleGdvAdapter.setSelectedPosition(mSelectedPostion);
                    mSf14LsvAdapter = new Sf14LsvAdapter(MatchLists.get(mSelectedPostion), SfcR9Activity.this, mHandler, ActivityType);
                    syx5Gridview.setAdapter(mSfcTitleGdvAdapter);
                    r9Listview.setAdapter(mSf14LsvAdapter);
                    syx5IssueTv.setText(issues.get(mSelectedPostion));
                    syx5StoptimeTv.setText(stopTimes.get(mSelectedPostion));
                    syx5StoptimeTv.setTextColor(SfcR9Activity.this.getResources().getColor(R.color.black_gray));
                    initBottom(false, "0注 0元", View.VISIBLE, 0);
                    r9Swl.setRefreshing(false);
                }
            }

            @Override
            public void onBefore(BaseRequest request) {
                super.onBefore(request);
                r9Swl.setRefreshing(true);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                UIUtils.showToastSafe("网络异常,请检查网络");
                r9Swl.setRefreshing(false);
                Logger.e("网络异常,请检查网络");
            }
        });
    }

    private void initPosition(Sf14Bean mSf14Bean) {
        for (int i = 0; i < mSf14Bean.getData().getIssueList().size(); i++) {
            for (int m = 0; m < mSf14Bean.getData().getIssueList().get(i).getMatchList().size(); m++) {
                mSf14Bean.getData().getIssueList().get(i).getMatchList().get(m).setSelectPosition(m + 1);
            }
        }
    }


    @Override
    protected void init() {
        ActivityType = Configs.ACTIVITYTYPE_R9;
    }

    /**
     * 计算注数,一遍handler计算
     *
     * @param matchListBeans
     * @return
     */
    private int CacluteZhushu(List<Sf14Bean.DataBean.IssueListBean.MatchListBean> matchListBeans) {
        if (null != matchListBeans) {
            checkNum.clear();
            danSelectNum.clear();
            unDanNum.clear();
            for (int i = 0; i < matchListBeans.size(); i++) {
                int j = 0;
                if (matchListBeans.get(i).getCheckedHashMap().get(0)) {
                    j++;
                }
                if (matchListBeans.get(i).getCheckedHashMap().get(1)) {
                    j++;
                }
                if (matchListBeans.get(i).getCheckedHashMap().get(2)) {
                    j++;
                }
                if (j > 0) {
                    if (matchListBeans.get(i).isChecked()) {
                        danSelectNum.add(j);
                    } else {
                        unDanNum.add(j);
                    }
                }
                checkNum.add(j);
            }
            zhushu = 0;
            for (int m = 0; m < checkNum.size(); m++) {
                if (checkNum.get(m) > 0) {
                    zhushu++;
                }
            }
//            CalculateStage. getRenxuan9Count1(unDanNum, danSelectNum);
            zhushu = CalculateStage.getRenxuan9Count1(unDanNum, danSelectNum);
            return zhushu;
        }
        return zhushu;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Configs.SFC14_REQUESTCODE) {
            if (resultCode == Configs.SFC14_RESULTCODE) {
                Bundle bundle = data.getExtras();
                List<Sf14Bean.DataBean.IssueListBean.MatchListBean> matchListBean = (List<Sf14Bean.DataBean.IssueListBean.MatchListBean>) bundle.getSerializable(Configs.SFCDINGDAN_RESULTKEY);
                for (int i = 0; i < MatchLists.get(mSelectedPostion).size(); i++) {
                    MatchLists.get(mSelectedPostion).get(i).setCheckedHashMap(matchListBean.get(i).getCheckedHashMap());
                }
                mSf14LsvAdapter.notifyDataSetChanged();
                if ((CacluteZhushu(MatchLists.get(mSelectedPostion)) < 1)) {//如果清空列表返回了需要更新下ui
                    initBottom(false, "0注 0元", View.VISIBLE, 0);
                }
            } else if (resultCode == Configs.SFC14SAVE_RESULTCODE) {//如果投注成功返回,清空界面数据
                deleteData(MatchLists.get(mSelectedPostion));
                mSf14LsvAdapter.notifyDataSetChanged();
            } else {
                finish();
            }

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
                    break;
                case R.id.sfc_sum_bt:
                    if (zhushu > 10000) {
                        UIUtils.showToastSafe("投注注数不能超过10000注");
                        return;
                    }
                    SfcDingdanBean sfcDingdanBean = new SfcDingdanBean();
                    sfcDingdanBean.setMatchListBeans(MatchLists.get(mSelectedPostion));
                    sfcDingdanBean.setCount(zhushu);
                    if (count == 9) {
                        if (zhushu == 1) {
                            sfcDingdanBean.setDanOrfushi("d");//单式
                        } else {
                            sfcDingdanBean.setDanOrfushi("f");//复式
                        }
                    } else {
                        sfcDingdanBean.setDanOrfushi("dt");//胆拖
                    }
                    sfcDingdanBean.setIssueNo(mSf14Bean.getData().getIssueList().get(mSelectedPostion).getIssueNo());
//                    sfcDingdanBean.setLotId(mSf14Bean.getData().getLotId());
                    sfcDingdanBean.setLotId("sf9");
                    Intent mIntent = new Intent(SfcR9Activity.this, SfcDingdanActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Configs.SFCDINGDAN_KEY, sfcDingdanBean);
                    bundle.putInt(Configs.SFCDINGDAN_INT_KEY, ActivityType);
                    mIntent.putExtras(bundle);
                    startActivityForResult(mIntent, Configs.SFC14_REQUESTCODE);
                    break;
                case R.id.sfc_deleteall_tv:
                    deleteData(MatchLists.get(mSelectedPostion));
                    mSf14LsvAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mSfcTitleGdvAdapter.setSelectedPosition(position);
            mSelectedPostion = position;
            mSfcTitleGdvAdapter.notifyDataSetChanged();
            if (null == MatchLists.get(mSelectedPostion).get(0).getCheckedHashMap()) {
                mSf14LsvAdapter.intData(MatchLists.get(mSelectedPostion));
            }
            mSf14LsvAdapter.setmSf14Beans(MatchLists.get(mSelectedPostion));

            syx5IssueTv.setText(issues.get(mSelectedPostion));
            syx5StoptimeTv.setText(stopTimes.get(mSelectedPostion));
            mSf14LsvAdapter.notifyDataSetChanged();
            mHandler.sendMessage(new Message());//此处发送消息是为了更新底部的数据
            changeTitleState(mFlag);
        }
    }

    public void deleteData(List<Sf14Bean.DataBean.IssueListBean.MatchListBean> mSf14Beans) {
        for (int i = 0; i < mSf14Beans.size(); i++) {
            HashMap<Integer, Boolean> map = new HashMap<>();
            map.put(0, false);
            map.put(1, false);
            map.put(2, false);
            mSf14Beans.get(i).setCheckedHashMap(map);
            if (ActivityType == Configs.ACTIVITYTYPE_R9) {
                mSf14Beans.get(i).setChecked(false);
                mSf14Beans.get(i).setCanChecked(false);
            }
        }
    }
    @Override
    protected String getBaidutitle() {
        return "胜负彩任选9";
    }
}