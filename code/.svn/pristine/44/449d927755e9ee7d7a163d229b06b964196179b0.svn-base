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
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.Sf14LsvAdapter;
import shlottery.gov.cn.lotterycenter.ui.nublottery.adapter.SfcTitleGdvAdapter;
import shlottery.gov.cn.lotterycenter.ui.view.GridViewWithoutScroll;
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

public class Sfc14Activity extends BaseActivity {
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
    @BindView(R.id.sf14_listview)
    ListView sf14Listview;
    @BindView(R.id.sf14_swl)
    SwipeRefreshLayout sf14Swl;
    @BindView(R.id.activity_sf14)
    LinearLayout activitySf14;
    @BindView(R.id.sfc_deleteall_tv)
    TextView sfcDeleteallTv;
    @BindView(R.id.sfcBottom_matchCount_txt)
    TextView sfcBottomMatchCountTxt;
    @BindView(R.id.sfcBottom_sub_txt)
    TextView sfcBottomSubTxt;
    @BindView(R.id.sfc_sum_bt)
    TextView sfcSumBt;
    private List<List<Sf14Bean.DataBean.IssueListBean.MatchListBean>> MatchLists = new ArrayList<>();
    private List<String> issues = new ArrayList<>();//期号
    private List<String> stopTimes = new ArrayList<>();//期号
    private boolean mFlag = false;//默认隐藏
    private MyOnclikListener mMyOnclikListener;
    private SfcTitleGdvAdapter mSfcTitleGdvAdapter;
    private Sf14LsvAdapter mSf14LsvAdapter;
    private int mSelectedPostion = 0;
    private int ActivityType;//区分任选九和14场
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int money = handlerMeath(mSf14LsvAdapter.getmSf14Beans());
            if (money > 0) {
                initBottom(true, money + "注" + " " + 2 * money + "元", View.GONE);
                TextUtils.setStrColor(sfcBottomMatchCountTxt, money + "注" + " " + 2 * money + "元", 2 * money + "", BaseApplication.getApplication().getResources().getColor(R.color.select_red));
            } else {
                initBottom(false, "0注 0元", View.VISIBLE);
            }
        }
    };

    //初始化底部
    private void initBottom(boolean enabled, String text, int visible) {
        sfcSumBt.setEnabled(enabled);
        sfcBottomMatchCountTxt.setText(text);
        sfcBottomSubTxt.setVisibility(visible);
    }

    int count = 0;//串数
    private List<Integer> checkNum = new ArrayList<>();//每行选中个数的集合
    private Sf14Bean mSf14Bean;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_shengfu14);
        ButterKnife.bind(this);
        titlebarTv.setText("足彩-14场");
        syx5StoptimetextTv.setText("投注截止时间:");
        mMyOnclikListener = new MyOnclikListener();
        titlebarBackLl.setOnClickListener(mMyOnclikListener);
        titlebarTitle.setOnClickListener(mMyOnclikListener);
        sfcDeleteallTv.setOnClickListener(mMyOnclikListener);
        sfcSumBt.setOnClickListener(mMyOnclikListener);
        sf14Swl.setColorSchemeResources(R.color.homeblue);
        syx5Gridview.setOnItemClickListener(mMyOnclikListener);
        changeTitleState(mFlag);
        sfcSumBt.setEnabled(false);//提交按钮默认不可点击
        refreshData();
        sf14Swl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

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
                    mSfcTitleGdvAdapter = new SfcTitleGdvAdapter(Sfc14Activity.this, issues);
                    mSf14LsvAdapter = new Sf14LsvAdapter(MatchLists.get(mSelectedPostion), Sfc14Activity.this, mHandler, ActivityType);
                    syx5Gridview.setAdapter(mSfcTitleGdvAdapter);
                    sf14Listview.setAdapter(mSf14LsvAdapter);
                    syx5IssueTv.setText(issues.get(mSelectedPostion));
                    syx5StoptimeTv.setText(stopTimes.get(mSelectedPostion));
                    syx5StoptimeTv.setTextColor(Sfc14Activity.this.getResources().getColor(R.color.black_gray));
                    initBottom(false, "0注 0元", View.VISIBLE);
                    sf14Swl.setRefreshing(false);
                }
            }

            @Override
            public void onBefore(BaseRequest request) {
                super.onBefore(request);
                sf14Swl.setRefreshing(true);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                UIUtils.showToastSafe("网络异常,请检查网络");
                sf14Swl.setRefreshing(false);
                Logger.e("网络异常,请检查网络");
            }
        });
    }


    @Override
    protected void init() {
        ActivityType = Configs.ACTIVITYTYPE_SF14;
    }

    /**
     * 计算注数,一遍handler计算
     *
     * @param matchListBeans
     * @return
     */
    private int handlerMeath(List<Sf14Bean.DataBean.IssueListBean.MatchListBean> matchListBeans) {
        if (null != matchListBeans) {
            checkNum.clear();
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
                Logger.e(j + "=j=");
                checkNum.add(j);
            }
            for (int m = 0; m < checkNum.size(); m++) {
                if (m == 0) {
                    count = checkNum.get(0);
                    Logger.e(count + "__0__count的值");
                } else {
                    count = count * checkNum.get(m);
                }
                Logger.e(count + "____count的值");
            }
            return count;
        }
        return count;
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
//                mSf14LsvAdapter.setmSf14Beans((List<Sf14Bean.DataBean.IssueListBean.MatchListBean>) bundle.getSerializable(Configs.SFCDINGDAN_RESULTKEY));
                mSf14LsvAdapter.notifyDataSetChanged();
            } else if (resultCode == Configs.SFC14SAVE_RESULTCODE) {
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
                    if (count > 10000) {
                        UIUtils.showToastSafe("投注注数不能超过10000注");
                        return;
                    }
                    SfcDingdanBean sfcDingdanBean = new SfcDingdanBean();
                    sfcDingdanBean.setMatchListBeans(MatchLists.get(mSelectedPostion));
                    sfcDingdanBean.setCount(count);
                    if (count > 1) {
                        sfcDingdanBean.setDanOrfushi("f");
                    } else {
                        sfcDingdanBean.setDanOrfushi("d");
                    }
                    sfcDingdanBean.setIssueNo(mSf14Bean.getData().getIssueList().get(mSelectedPostion).getIssueNo());
//                    sfcDingdanBean.setLotId(mSf14Bean.getData().getLotId());
                    sfcDingdanBean.setLotId("sf14");
                    Intent mIntent = new Intent(Sfc14Activity.this, SfcDingdanActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt(Configs.SFCDINGDAN_INT_KEY, ActivityType);
                    bundle.putSerializable(Configs.SFCDINGDAN_KEY, sfcDingdanBean);
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

    private void deleteData(List<Sf14Bean.DataBean.IssueListBean.MatchListBean> mSf14Beans) {
        for (int i = 0; i < mSf14Beans.size(); i++) {
            HashMap<Integer, Boolean> map = new HashMap<>();
            map.put(0, false);
            map.put(1, false);
            map.put(2, false);
            mSf14Beans.get(i).setCheckedHashMap(map);
        }
    }
    @Override
    protected String getBaidutitle() {
        return "胜负14场";
    }
}