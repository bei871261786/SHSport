package shlottery.gov.cn.lotterycenter.ui.calculatebouns;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.Base.BaseFragment;
import shlottery.gov.cn.lotterycenter.Base.FragmentFactory;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusBean;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusNumberDetailBean;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusSoccerDetailBean;
import shlottery.gov.cn.lotterycenter.bean.IssueListBaseBean;
import shlottery.gov.cn.lotterycenter.callback.CalculateEventBus;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.calculatebouns.adapter.CalculateTimeListAdapter;
import shlottery.gov.cn.lotterycenter.ui.calculatebouns.adapter.CalculateTitleGdvAdapter;
import shlottery.gov.cn.lotterycenter.ui.view.GridViewWithoutScroll;
import shlottery.gov.cn.lotterycenter.ui.view.widget.MyWheelView;
import shlottery.gov.cn.lotterycenter.ui.view.widget.OnWheelChangedListener;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingDialog;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-26-0026 09:51
 * 描    述：奖金计算
 * 修订历史：
 * ================================================
 */

public class CalculateActivity extends BaseActivity {
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
    @BindView(R.id.calculate_gridview)
    GridViewWithoutScroll calculateGridview;
    @BindView(R.id.calculate_fragment)
    FrameLayout calculateFragment;
    @BindView(R.id.activity_calculate)
    LinearLayout activityCalculate;

    private CalculateTitleGdvAdapter mTitleGdvAdapter;
    private BaseFragment mFragment;
    private String[] mTitles;
    private int currentPosition = 0;
    private boolean mFlag = false;
    private MyOnclikListener mMyOnclikListener;
    private IssueBonusBean issueBonusBean;//开奖列表的bean
    private IssueListBaseBean issueListBaseBean;//开奖日期的bean
    private IssueBonusSoccerDetailBean issueBonusSoccerDetailBean;//开奖列表的bean
    private IssueBonusNumberDetailBean issueBonusNumberDetailBean;//开奖详细bean
    private LoadingDialog loadingDialog;
    private AlertDialog mTimePickDialog;
    private MyWheelView mTimeWheelPicker;
    private String lotId;//彩种id
    private List<String> mIssueList;//开奖期号的String 集合  点击头部切换按钮时,需要清空,重新请求//或者做缓存
    private String selectedIssue;//选中日期
    private int timepickselPositin;
    private CalculateEventBus calculatestickyEventBus;//传递数据的延迟消息eventbus
    private CalculateEventBus calculateEventBus;//传递数据的及时eventbus
    private int selectedTimeItem = 0;//选中的时间所在位置
    private String pl3IssueNo;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_calculate);
        ButterKnife.bind(this);
        mTitles = getResources().getStringArray(R.array.calculate_title);
        titlebarTv.setText(mTitles[currentPosition]);
        changeTitleState(mFlag);
        mTitleGdvAdapter = new CalculateTitleGdvAdapter(CalculateActivity.this);
        mainCalendar.setVisibility(View.VISIBLE);//期号选择按钮
        calculateGridview.setAdapter(mTitleGdvAdapter);
        mMyOnclikListener = new MyOnclikListener();
        titlebarBackLl.setOnClickListener(mMyOnclikListener);//返回
        titlebarTitle.setOnClickListener(mMyOnclikListener);//彩种选项
        calculateGridview.setOnItemClickListener(mMyOnclikListener);
        loadIssueNum(currentPosition);
    }

    @Override
    protected void init() {

    }

    private void changeTitleState(boolean flag) {
        if (flag) {//flag位true时展开,
            titlebarIndicatordown.setVisibility(View.GONE);
            titlebarIndicator.setVisibility(View.VISIBLE);
            calculateGridview.setVisibility(View.VISIBLE);
            mFlag = false;
        } else {
            titlebarIndicatordown.setVisibility(View.VISIBLE);
            titlebarIndicator.setVisibility(View.GONE);
            calculateGridview.setVisibility(View.GONE);
            mFlag = true;
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
                case R.id.main_calendar:
                    showTimePicker();//显示期号选择框
                    break;
                case R.id.issuepicker_cancle_tv:
                    mTimePickDialog.dismiss();//显示期号选择框取消
                    break;
                case R.id.issuepicker_ok_tv://显示期号选择框确认
                    mTimePickDialog.dismiss();
                    selectedTimeItem = timepickselPositin;
                    loadIssueDetail(currentPosition, issueListBaseBean.getData().getIssueList().get(timepickselPositin).getIssueNo());
                    break;
                case R.id.issuepicker_backto_tv: //显示期号选择框 返回当前期
                    mTimePickDialog.dismiss();
                    selectedTimeItem = 0;
                    loadIssueDetail(currentPosition, issueListBaseBean.getData().getIssueList().get(0).getIssueNo());
                    break;
                default:
                    break;

            }
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mTitleGdvAdapter.setSelectedPosition(position);
            mTitleGdvAdapter.notifyDataSetChanged();
            titlebarTv.setText(mTitles[position]);
            currentPosition = position;
            selectedTimeItem = 0;
            changeTitleState(mFlag);
            loadIssueNum(currentPosition);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            if (issueBonusBean != null) {
                for (int i = 0; i < issueBonusBean.getData().getList().size(); i++) {
                    if (issueBonusBean.getData().getList().get(i).getLotId().equals("pl3")) {
                        issueBonusBean.getData().getList().get(i).setIssueNo(pl3IssueNo);
                    }
                }
                calculatestickyEventBus.setIssueBonusBean(issueBonusBean);
                EventBus.getDefault().postSticky(calculatestickyEventBus);
            }
            mFragment = FragmentFactory.createCalculateFragment(currentPosition);
            ft.replace(R.id.calculate_fragment, mFragment);
            ft.commit();
        }
    }


    //弹出时间选择框
    private void showTimePicker() {

        // TODO Auto-generated method stub
        View mDialogView = UIUtils.inflate(R.layout.dialog_issue_picker);
        if (null == mTimePickDialog) {
            mTimePickDialog = new AlertDialog.Builder(this).create();
        } else {
            mTimePickDialog = null;//避免复用dialog导致MywheelView当前可见条目与数据不符合
            mTimePickDialog = new AlertDialog.Builder(this).create();
        }
        mTimePickDialog.setView(mDialogView, 0, 0, 0, 0);
        TextView mTimeCancle = (TextView) mDialogView.findViewById(R.id.issuepicker_cancle_tv);
        TextView mTimeOk = (TextView) mDialogView.findViewById(R.id.issuepicker_ok_tv);
        TextView mTimeBack = (TextView) mDialogView.findViewById(R.id.issuepicker_backto_tv);

        mTimeCancle.setOnClickListener(mMyOnclikListener);//取消
        mTimeOk.setOnClickListener(mMyOnclikListener);//确定
        mTimeBack.setOnClickListener(mMyOnclikListener);//确定


        mTimeWheelPicker = (MyWheelView) mDialogView.findViewById(R.id.time_pick_whl);
        mIssueList = new ArrayList<>();
        for (int i = 0; i < issueListBaseBean.getData().getIssueList().size(); i++) {
            mIssueList.add(issueListBaseBean.getData().getIssueList().get(i).getIssueNo() + "期");
        }

        CalculateTimeListAdapter mTimeWheelAdapter = new CalculateTimeListAdapter(this, mIssueList);
        mTimeWheelPicker.setViewAdapter(mTimeWheelAdapter);
        mTimeWheelPicker.setCurrentItem(selectedTimeItem);
//        mCurrentMatchTime = mTimeList.get(mDefaultItem);//初始化期号的值
        // mMatchTime = mTimeList.get(mDefaultItem);//初始化期号的值
        mTimeWheelPicker.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(MyWheelView wheel, int oldValue, int newValue) {
                if (null != mIssueList && mIssueList.size() >= 1) {
                    selectedIssue = mIssueList.get(newValue);
                    timepickselPositin = newValue;
                    Logger.e(selectedIssue + "期号");
                }
            }
        });
        mTimePickDialog.show();
    }

    //加载开奖当前期号以及
    private void loadData() {
        OkGo.get(UrlManager.getIssuebonusList(this)).cacheMode(CacheMode.NO_CACHE).connTimeOut(5000).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                issueBonusBean = gson.fromJson(s, IssueBonusBean.class);
                if (issueBonusBean != null) {
                    if (issueBonusBean.getRet() == 100) {
                        if (calculatestickyEventBus == null) {
                            calculatestickyEventBus = new CalculateEventBus();
                        }
                        mainCalendar.setOnClickListener(mMyOnclikListener);//选择日期
                        calculatestickyEventBus.setIssueBonusBean(issueBonusBean);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        mFragment = FragmentFactory.createCalculateFragment(currentPosition);
                        fragmentManager.beginTransaction().replace(R.id.calculate_fragment, mFragment).commit();
                        for (int i = 0; i < issueBonusBean.getData().getList().size(); i++) {
                            if (issueBonusBean.getData().getList().get(i).getLotId().equals("pl3")) {
                                pl3IssueNo = issueBonusBean.getData().getList().get(i).getIssueNo();
                            }
                        }
                        EventBus.getDefault().postSticky(calculatestickyEventBus);
                        if (currentPosition > 3) {//如果是胜负彩,还得请求当前期的详细数据
                            loadIssueDetail(currentPosition, issueListBaseBean.getData().getIssueList().get(0).getIssueNo());
                        }
                    } else {
                        UIUtils.showToastSafe(issueBonusBean.getMsg());
                    }
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                UIUtils.showToastSafe("网络异常,请检查网络设置");
            }

        });
    }

    //加载开奖期号
    private void loadIssueNum(int postion) {
        switch (postion) {
            case 0:
                lotId = "dlt";
                break;
            case 1:
                lotId = "pl3";
                break;
            case 2:
                lotId = "pl5";
                break;
            case 3:
                lotId = "qxc";
                break;
            case 4:
                lotId = "sfc";
                break;
            case 5:
                lotId = "sfc";
                break;
            case 6:
                lotId = "bqc";
                break;
            case 7:
                lotId = "jqc";
                break;
            default:
                break;
        }
        OkGo.get(UrlManager.getBonusIssue(this, lotId)).cacheMode(CacheMode.NO_CACHE).connTimeOut(5000).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                issueListBaseBean = gson.fromJson(s, IssueListBaseBean.class);
                if (issueListBaseBean != null) {
                    if (issueListBaseBean.getRet() == 100) {
                        if (issueBonusBean == null) {//如果开奖列表的bean为空,则重新加载数据,否则不用重新加载
                            loadData();
                        }
                    } else {
                        UIUtils.showToastSafe(issueBonusBean.getMsg());
                    }
                }
            }

            @Override
            public void onBefore(BaseRequest request) {
                super.onBefore(request);
                loadingDialog = new LoadingDialog(CalculateActivity.this, "正在加载中...");
                loadingDialog.show();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                if (loadingDialog != null) {
                    loadingDialog.close();
                }
            }

            @Override
            public void onAfter(@Nullable String s, @Nullable Exception e) {
                super.onAfter(s, e);
                if (loadingDialog != null) {
                    loadingDialog.close();
                }
            }
        });
    }

    //加载开奖期号对应的详细数据
    private void loadIssueDetail(int postion, String issue) {

        switch (postion) {
            case 0:
                lotId = "dlt";
                break;
            case 1:
                lotId = "pl3";
                break;
            case 2:
                lotId = "pl5";
                break;
            case 3:
                lotId = "qxc";
                break;
            case 4:
                lotId = "sfc";
                break;
            case 5:
                lotId = "sfc";
                break;
            case 6:
                lotId = "bqc";
                break;
            case 7:
                lotId = "jqc";
                break;
            default:
                break;
        }
        OkGo.get(UrlManager.getBonusDetail(this, lotId, issue)).cacheMode(CacheMode.NO_CACHE).connTimeOut(5000).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                if (currentPosition > 3) {
                    issueBonusSoccerDetailBean = gson.fromJson(s, IssueBonusSoccerDetailBean.class);
                    Logger.e(currentPosition + "issueBonusSoccerDetailBean");
                    if (issueBonusSoccerDetailBean != null) {
                        if (issueBonusSoccerDetailBean.getRet() == 100) {
                            calculateEventBus = new CalculateEventBus();
                            calculateEventBus.setIssueBonusSoccerDetailBean(issueBonusSoccerDetailBean);
                            EventBus.getDefault().post(calculateEventBus);
                            Logger.e(currentPosition + "issueBonusSoccerDetailBean");
                        }
                    }
                } else {
                    issueBonusNumberDetailBean = gson.fromJson(s, IssueBonusNumberDetailBean.class);
                    if (issueBonusNumberDetailBean != null) {
                        if (issueBonusNumberDetailBean.getRet() == 100) {
                            if (currentPosition == 1) {//当为排列三时
                                for (int i = 0; i < issueBonusBean.getData().getList().size(); i++) {
                                    if (issueBonusBean.getData().getList().get(i).getLotId().equals("pl3")) {
                                        issueBonusBean.getData().getList().get(i).setIssueNo(issueBonusNumberDetailBean.getData().getIssueNo());
                                    }
                                }

                            }
                            calculateEventBus = new CalculateEventBus();
                            calculateEventBus.setIssueBonusNumberDetailBean(issueBonusNumberDetailBean);
                            EventBus.getDefault().post(calculateEventBus);
                        } else {
                            UIUtils.showToastSafe(issueBonusNumberDetailBean.getMsg());
                        }
                    }
                }
            }

            @Override
            public void onBefore(BaseRequest request) {
                super.onBefore(request);
                loadingDialog = new LoadingDialog(CalculateActivity.this, "正在加载中...");
                loadingDialog.show();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                if (loadingDialog != null) {
                    loadingDialog.close();
                }
            }

            @Override
            public void onAfter(@Nullable String s, @Nullable Exception e) {
                super.onAfter(s, e);
                if (loadingDialog != null) {
                    loadingDialog.close();
                }
            }
        });
    }
    @Override
    protected String getBaidutitle() {
        return "";
    }

}
