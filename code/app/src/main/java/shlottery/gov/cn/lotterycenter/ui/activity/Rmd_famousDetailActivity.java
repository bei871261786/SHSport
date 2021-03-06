package shlottery.gov.cn.lotterycenter.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.FamousDetailBean;
import shlottery.gov.cn.lotterycenter.bean.Sf14Bean;
import shlottery.gov.cn.lotterycenter.bean.SfcDingdanBean;
import shlottery.gov.cn.lotterycenter.bean.VcodeBean;
import shlottery.gov.cn.lotterycenter.bean.VcodeBean2;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.callback.LoginDialogEventBus;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.manager.LoginManager;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.AddSubsribeProtocol;
import shlottery.gov.cn.lotterycenter.protool.DeleteSubsribeProtocol;
import shlottery.gov.cn.lotterycenter.protool.FamousDetailProtocol;
import shlottery.gov.cn.lotterycenter.protool.RecommednStakeProtocol;
import shlottery.gov.cn.lotterycenter.ui.adapter.RmdJincaiDingdanAdapter;
import shlottery.gov.cn.lotterycenter.ui.adapter.RmdJzhhAdapter;
import shlottery.gov.cn.lotterycenter.ui.adapter.RmdSoccerDingdanAdapter;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

import static shlottery.gov.cn.lotterycenter.R.id.titlebar_indicator;

public class Rmd_famousDetailActivity extends BaseActivity implements LoadCallback {

    private int mId;
    private String mLotId;//彩种
    private String mIssueNo = "";//期号
    private String mDate;
    private int mMultiple = 0;//倍数
    private String mTotalMoney = "";//金额
    private String mForcastMoney = "";//预测中奖金额
    private String mTitle = "";
    private String mCount;//注数
    private String mStakeCode = "";//类容
    private String mLotid = "";
    private BaseAdapter mAdapter;
    private SfcDingdanBean mAdapterData = new SfcDingdanBean();
    private FamousDetailBean mLoadData;

    private ListView mListview;
    private TextView mStakeMoneyTv;
    private TextView mJcStakeMoneyTv;
    private TextView mForsestMoneyTv;
    private TextView mPlayeTypeTv;
    private TextView mCountTv;
    private TextView mMultipleTv;
    private TextView mJcMultipleTv;
    private TextView mIssuenoTv;
    private TextView mDateTv;
    private ImageView mFamousIcon;
    private TextView mFamousName;
    private TextView mFamousProfile;
    private TextView mReason;
    private CheckBox mSubsribe;
    private LinearLayout mTidianLayout;
    private int mActivityType;
    private MyListener mListener;
    private boolean isSubcribe = false;
    private int mFid;
    private TextView mSubmit;
    private LinearLayout mSoccerLayout;
    private LinearLayout mJincaiLayout;
    private LinearLayout mDescriLayout;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_rmd_famous_detail);
        mListview = (ListView) findViewById(R.id.famousdetail_listview);
        mStakeMoneyTv = (TextView) findViewById(R.id.famousdetail_stakeMoney);
        mJcStakeMoneyTv = (TextView) findViewById(R.id.famousdetail_jcstakeMoney);
        mForsestMoneyTv = (TextView) findViewById(R.id.famousdetail_forestMoney);
        mCountTv = (TextView) findViewById(R.id.famousdetail_count);
        mMultipleTv = (TextView) findViewById(R.id.famousdetail_multiple);
        mJcMultipleTv = (TextView) findViewById(R.id.famousdetail_jcmultiple);
        mPlayeTypeTv = (TextView) findViewById(R.id.famousdetail_playtype);
        mSubsribe = (CheckBox) findViewById(R.id.famousdetail_subscribe);
        mIssuenoTv = (TextView) findViewById(R.id.famousdetail_issueNo);
        mDateTv = (TextView) findViewById(R.id.famousdetail_date);
        mFamousName = (TextView) findViewById(R.id.famousdetail_name);
        mFamousProfile = (TextView) findViewById(R.id.famousdetail_profile);
        mReason = (TextView) findViewById(R.id.famousdetail_tidian);
        mSubmit = (TextView) findViewById(R.id.sfc_zdsave_tv);
        mFamousIcon = (ImageView) findViewById(R.id.famous_icon);
        mTidianLayout = (LinearLayout) findViewById(R.id.famousdetail_tidianLayout);
        mSoccerLayout = (LinearLayout) findViewById(R.id.famousdetail_soccerInfo);
        mJincaiLayout = (LinearLayout) findViewById(R.id.famousdetail_jincaiInfo);
        mDescriLayout = (LinearLayout) findViewById(R.id.famousdetail_decreLayout);
        mListview.setAdapter(mAdapter);
        mSubsribe.setOnClickListener(mListener);
        mSubmit.setOnClickListener(mListener);
        initTitleBar();
        beginLoad();
    }

    private void initTitleBar() {
        LinearLayout mBack = (LinearLayout) findViewById(R.id.titlebar_back_ll);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeThisActivity();
            }
        });
        titlebarTv = (TextView) findViewById(R.id.titlebar_tv);
        titlebarTv.setText(mTitle);
        ImageView mIndicatorImg = (ImageView) findViewById(titlebar_indicator);
        mIndicatorImg.setVisibility(View.GONE);
    }

    @Override
    protected void init() {
        //注册接收登陆窗登录成功后事件
        EventBus.getDefault().register(this);
        Bundle bundle = getIntent().getExtras();
        mId = bundle.getInt("id");
        mTitle = bundle.getString("title");
        mActivityType = (int) bundle.get(Configs.SFCDINGDAN_INT_KEY);
        if (mActivityType == Configs.ACTIVITYTYPE_R9) {
            mAdapter = new RmdSoccerDingdanAdapter(this, mAdapterData, mActivityType);
        } else if (mActivityType == Configs.ACTIVITYTYPE_JZHH ||
                mActivityType == Configs.ACTIVITYTYPE_JZBQC ||
                mActivityType == Configs.ACTIVITYTYPE_JZBF ||
                mActivityType == Configs.ACTIVITYTYPE_JZJQS) {
            //竞足混合过关 半全场 比分 进球数
            mAdapter = new RmdJzhhAdapter(this, mAdapterData);
        } else {
            mAdapter = new RmdJincaiDingdanAdapter(this, mAdapterData, mActivityType);
        }
        mListener = new MyListener();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogin(LoginDialogEventBus loginDialogEventBus) {
        //登录成功后，刷新界面
        if (loginDialogEventBus.isLoginSuccessful()) {
            beginLoad();
        }
    }

    private void saveOrder() {
        RecommednStakeProtocol protocol = new RecommednStakeProtocol(this);
        protocol.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                params.put("id", mId + "");
                return params;
            }
        }, this);
    }

    private void beginLoad() {
        FamousDetailProtocol protocol = new FamousDetailProtocol(this);
        protocol.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                params.put("id", mId + "");
                return params;
            }
        }, this);
    }

    @Override
    public void onSucess(Object o) {
        if (o != null) {
            if (o instanceof FamousDetailBean) {
                mLoadData = (FamousDetailBean) o;
                if (mLoadData.getRet() == 100) {
                    initInfo(mLoadData.getData());
                    initDingdan(mLoadData.getData());
                }
            }
            if (o instanceof VcodeBean) {
                VcodeBean bean = (VcodeBean) o;
                UIUtils.showToastSafe(bean.getMsg());
                if (bean.getRet() == 100) {
                    isSubcribe = !isSubcribe;
                    initSubsreiTv(isSubcribe);
                }
            }
            if (o instanceof VcodeBean2) {
                VcodeBean2 bean = (VcodeBean2) o;
                if (bean.getRet() == 100) {
                    UIUtils.showToastSafe("投注成功");
                } else {
                    UIUtils.showToastSafe(bean.getMsg());
                }
            }
        }
    }

    @Override
    public void onError() {
    }

    private void initDingdan(FamousDetailBean.DataBean data) {
        List<FamousDetailBean.DataBean.MatchListBean> matchBean = data.getMatchList();
        mAdapterData.setCount(Integer.parseInt(mCount));
        mAdapterData.setIssueNo(mIssueNo);
        mAdapterData.setMultiple(mMultiple);
        mAdapterData.setTotalMoney(mTotalMoney);
        mAdapterData.setLotId(mLotid);

        ArrayList<Sf14Bean.DataBean.IssueListBean.MatchListBean> matchList = new ArrayList<>();
        for (int i = 0; i < matchBean.size(); i++) {
            Sf14Bean.DataBean.IssueListBean.MatchListBean bean = new Sf14Bean.DataBean.IssueListBean.MatchListBean();
            FamousDetailBean.DataBean.MatchListBean match = matchBean.get(i);
            bean.setMatchNo(match.getMatchNo());
            bean.setHostName(match.getHostName());
            bean.setVisitName(match.getVisitName());
            bean.setLeagueName(match.getLeagueName());
            bean.setMatchTime(match.getMatchTime());
            bean.setCheckedHashMap(initCheckedMap(match));
            bean.setWeekNo(match.getWeekNo());
            bean.setHand(match.getHand());
            LogUtils.i("setValidtime:" + match.getStopTime());
            bean.setValidTime(match.getStopTime());
            //增加混合过关 需要增加以下字段信息 stakeOption logId
            bean.setStakeOption(match.getStakeOption());
            bean.setLotId(match.getLotId());
            matchList.add(bean);
        }
        mAdapterData.setMatchListBeans(matchList);
        LogUtils.i("getCheckedHashMap statue:---------------------");
        mAdapter.notifyDataSetChanged();
    }

    private HashMap<Integer, Boolean> initCheckedMap(FamousDetailBean.DataBean.MatchListBean match) {
        LogUtils.i("initCheckedMap initCheckedMap:---------------------------------");
        HashMap<Integer, Boolean> map = new HashMap<>();
        Boolean winStatus = false;
        Boolean pingStatus = false;
        Boolean lostStatus = false;
        ArrayList<String> optionList = (ArrayList<String>) match.getStakeOption();
        for (int i = 0; i < optionList.size(); i++) {
            String option = optionList.get(i);
            LogUtils.i("initCheckedMap option:" + option + "::::" + i);
            if (!winStatus) {
                if (option.contains("胜") || option.contains("小") || option.contains("3")) {
                    map.put(0, true);
                    winStatus = true;
                } else {
                    map.put(0, false);
                }
            }
            if (!pingStatus) {
                if (option.contains("平") || option.contains("1")) {
                    LogUtils.i("initCheckedMap  true");
                    map.put(1, true);
                    pingStatus = true;
                } else {
                    LogUtils.i("initCheckedMap  false");
                    map.put(1, false);
                }
            }
            if (!lostStatus) {
                if (option.contains("负") || option.contains("大") || option.contains("0")) {
                    map.put(2, true);
                    lostStatus = true;
                } else {
                    map.put(2, false);
                }
            }


        }
        Set<Integer> set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            LogUtils.i("initCheckedMap map status:" + map.get(iterator.next()));
        }
        return map;
    }

    private void initInfo(FamousDetailBean.DataBean data) {
        //获取到名字后再修改一次title, 从通知栏进来页面的时候没有lotname信息
        String lotName = data.getLotName();
        changeTitle(lotName);
        mIssueNo = data.getIssueNo();
        mLotid = data.getLotId();
        mFid = data.getFid();
        String playType = data.getPlayType();
        Long time = Long.parseLong(data.getValidTime() + "");
        mDate = DateUtils.formatDateAndTime(time * 1000);
        mMultiple = data.getMultiple();
        mCount = data.getAmount() + "";
        mTotalMoney = data.getTotalMoney() + "";
        mForcastMoney = TextUtils.formatMoney(data.getBonusEstimate());
        String url = data.getLogo();
        if (url != null && !url.isEmpty()) {
            Picasso.with(this).load(url).into(mFamousIcon);
        }
        mFamousName.setText(data.getName());
        mFamousProfile.setText(data.getIntro());
        String reasonStr = data.getRecomReason();
        LogUtils.i("rmd detail resaon:" + reasonStr);
        if (reasonStr == null || reasonStr.isEmpty()) {
            mTidianLayout.setVisibility(View.GONE);
        } else {
            mReason.setText(reasonStr);
        }
        int isSubsribe = data.getSubscribe();
        if (isSubsribe == 1) {
            isSubcribe = true;
        } else {
            isSubcribe = false;
        }
        initSubsreiTv(isSubcribe);
        mIssuenoTv.setText("第" + mIssueNo + "期");
        mDateTv.setText("截止时间:" + mDate);
        if (mActivityType == Configs.ACTIVITYTYPE_R9) {
            mSoccerLayout.setVisibility(View.VISIBLE);
            mJincaiLayout.setVisibility(View.GONE);
            TextUtils.setStrColor(mStakeMoneyTv, "投注金额" + mTotalMoney + "元", mTotalMoney + "", getResources()
                    .getColor(R.color.select_red));
            TextUtils.setStrColor(mMultipleTv, "倍数" + mMultiple + "倍", mMultiple + "", getResources().getColor(R
                    .color.select_red));
            TextUtils.setStrColor(mCountTv, "注数" + mCount + "注", mCount, getResources().getColor(R.color.select_red));
        } else {
            mDescriLayout.setVisibility(View.GONE);
            mJincaiLayout.setVisibility(View.VISIBLE);
            mSoccerLayout.setVisibility(View.GONE);
            TextUtils.setStrColor(mJcStakeMoneyTv, "投注金额" + mTotalMoney + "元", mTotalMoney + "", getResources()
                    .getColor(R.color.select_red));
            TextUtils.setStrColor(mJcMultipleTv, "倍数" + mMultiple + "倍", mMultiple + "", getResources().getColor(R
                    .color.select_red));
            TextUtils.setStrColor(mForsestMoneyTv, "奖金预测" + mForcastMoney + "元", mForcastMoney + "", getResources()
                    .getColor(R.color.select_red));
            TextUtils.setStrColor(mPlayeTypeTv, "过关" + playType, playType, getResources().getColor(R.color.select_red));
        }
    }

    private void changeTitle(String lotName) {
        String title = "";
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
        titlebarTv.setText(title);
    }

    private void initSubsreiTv(boolean isSubscribe) {
        if (isSubscribe) {
            mSubsribe.setText("取消订阅");
            mSubsribe.setChecked(false);
            mSubsribe.setTextColor(getResources().getColor(R.color.news_tab_txt));
            mSubsribe.setBackgroundResource(R.drawable.shape_subscri_whitegray_corner);
        } else {
            mSubsribe.setText("订阅");
            mSubsribe.setChecked(true);
            mSubsribe.setTextColor(getResources().getColor(R.color.expandtxt_red));
            mSubsribe.setBackgroundResource(R.drawable.shape_whitered_corner);
        }
    }

    private void toggleSubsribe() {
        if (LoginManager.getInstance().login(this)) {
            if (isSubcribe) {
                DeleteSubsribeProtocol protocol = new DeleteSubsribeProtocol(this);
                protocol.load(this, new ParamsHelperInterface() {
                    @Override
                    public LinkedHashMap<String, String> getParamas() {
                        LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                        params.put("id", mFid + "");
                        return params;
                    }
                }, this);
            } else {
                AddSubsribeProtocol protocol = new AddSubsribeProtocol(this);
                protocol.load(this, new ParamsHelperInterface() {
                    @Override
                    public LinkedHashMap<String, String> getParamas() {
                        LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                        params.put("id", mFid + "");
                        return params;
                    }
                }, this);
            }
        }
    }

    private class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.famousdetail_subscribe:
                    toggleSubsribe();
                    break;
                case R.id.sfc_zdsave_tv:
                    if (LoginManager.getInstance().login(Rmd_famousDetailActivity.this)) {
                        saveOrder();
                    }

                    break;
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected String getBaidutitle() {
        return "专家看彩详情";
    }

    @Override
    public void onBackPressed() {
        closeThisActivity();
    }
}
