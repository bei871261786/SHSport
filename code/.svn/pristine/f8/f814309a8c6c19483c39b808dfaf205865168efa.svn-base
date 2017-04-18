package shlottery.gov.cn.lotterycenter.ui.nublottery.activity;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.NumLotteryBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.protool.NumberProtocol;
import shlottery.gov.cn.lotterycenter.ui.nublottery.fragment.BaseNumberFragment;
import shlottery.gov.cn.lotterycenter.ui.nublottery.fragment.lotto_dantuoFragment;
import shlottery.gov.cn.lotterycenter.ui.nublottery.fragment.lotto_normalFragment;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.ShakeUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;

public class LottoActivity extends BaseActivity implements LoadCallback<NumLotteryBean> {
    private TextView titlebarTv;
    private TextView lottoIssue;
    private TextView lottoStopTime;
    private TextView lottoPool;
    private TextView lottoTuomaTxt;
//    private RadioButton mNormalExpandLayout;
//    private RadioButton mDantuoExpandLayout;
    private RadioGroup mExpandRadioGroup;
    private LinearLayout mHeadTitle;
    private LinearLayout mExpandLayout;
    private MyListener mListener;
    private FragmentManager mFragmentManager;
    private BaseNumberFragment mDantuoFragment;
    private BaseNumberFragment mNormalFragment;
    private ShakeUtils mShakeUtils;
    private String mFucationType;
    private ImageView titlebarIndicator;
    private ImageView mBack;
    private int mCurrentPlayType = 0;
    protected String mIssueNo = "";

    @Override
    protected void initView() {
        setContentView(R.layout.activity_lotto);
        mShakeUtils = new ShakeUtils(this);
        titlebarTv = (TextView) findViewById(R.id.titlebar_tv);
        lottoIssue = (TextView) findViewById(R.id.lotto_issue);
        lottoStopTime = (TextView) findViewById(R.id.lotto_stopTime);
        lottoPool = (TextView) findViewById(R.id.lotto_pool);
        lottoTuomaTxt = (TextView) findViewById(R.id.lotto_tuoma_txt);
        mExpandLayout = (LinearLayout) findViewById(R.id.head_expand);
        mHeadTitle = (LinearLayout) findViewById(R.id.titlebar_title);
        mExpandRadioGroup = (RadioGroup) findViewById(R.id.head_expand);
        titlebarIndicator = (ImageView) findViewById(R.id.titlebar_indicator);
        mBack = (ImageView) findViewById(R.id.titlebar_back);
        mHeadTitle.setOnClickListener(mListener);
        mBack.setOnClickListener(mListener);
        mExpandRadioGroup.setOnCheckedChangeListener(mListener);

        titlebarIndicator.setVisibility(View.VISIBLE);
        titlebarTv.setText("大乐透");
        refreshData();
        toggleFragment(mCurrentPlayType);
    }

    @Override
    protected void init() {
        mCurrentPlayType = getIntent().getIntExtra("mCurrentPlayType", 0);
        mListener = new MyListener();
        mFragmentManager = getSupportFragmentManager();
        mFucationType = getIntent().getStringExtra("type");
    }

    public String getmIssueNo() {
        return mIssueNo;
    }

    private void refreshData() {
        NumberProtocol protocol = new NumberProtocol("dlt", this);
        protocol.load(this, this);
    }

    @Override
    public void onSucess(NumLotteryBean data) {
        NumLotteryBean bean = data;
        if (null != bean) {
            if (bean.getRet() == 100) {
                NumLotteryBean.DataBean dataBean = data.getData();
                updateTitle(dataBean);
            }
        }
    }

    @Override
    public void onError() {
        LogUtils.i("lottoactivity onError");
    }

    private class MyListener implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.titlebar_title:
                    LogUtils.i("是否隐藏");
                    if (mExpandLayout.getVisibility() == View.GONE) {
                        mExpandLayout.setVisibility(View.VISIBLE);
                        titlebarIndicator.setEnabled(false);
                    } else {
                        mExpandLayout.setVisibility(View.GONE);
                        titlebarIndicator.setEnabled(true);
                    }
                    break;
                case R.id.baselottery_deleteall_tv:
                    break;
                case R.id.baselottery_sum_bt:
                    break;
                case R.id.titlebar_back:
                    finish();
                    break;
            }
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.lotto_headexpand_normal:
                    toggleFragment(0);
                    break;
                case R.id.lotto_headexpand_dantuo:
                    toggleFragment(1);
                    break;

            }
            titlebarIndicator.setEnabled(true);
            mExpandLayout.setVisibility(View.GONE);
        }
    }

    private void showBackDialog() {
        LogUtils.i("datasize showDialog");
        View mDialogView = LayoutInflater.from(this).inflate(R.layout.dingdan_football_backdialog, null);
        final Dialog mDialog = new android.app.AlertDialog.Builder(this).create();
        mDialog.show();
        Window window = mDialog.getWindow();
        window.setContentView(mDialogView);
        mDialog.setCanceledOnTouchOutside(true);
        Button cancel = (Button) mDialogView.findViewById(R.id.base_dialog_cancle_btn);
        Button querrn = (Button) mDialogView.findViewById(R.id.base_dialog_submit_btn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        querrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                finish();

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void toggleFragment(int type) {
        LogUtils.i("切换fragment");
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        switch (type) {
            case 1:
//                mDantuoExpandLayout.setBackground(getResources().getDrawable(R.drawable.shape_title_expand_selected));
//                mNormalExpandLayout.setBackground(getResources().getDrawable(R.drawable.shape_title_expand_noselected));
                if (null == mDantuoFragment) {
                    mDantuoFragment = new lotto_dantuoFragment(mShakeUtils, mFucationType);
                    transaction.add(R.id.lotto_fragment, mDantuoFragment);
                }
                mShakeUtils.setCan(false);
                transaction.show(mDantuoFragment);
                if (mNormalFragment != null) {
                    transaction.hide(mNormalFragment);
                }
                break;
            case 0:
//                mNormalExpandLayout.setBackground(getResources().getDrawable(R.drawable.shape_title_expand_selected));
//                mDantuoExpandLayout.setBackground(getResources().getDrawable(R.drawable.shape_title_expand_noselected));
                if (null == mNormalFragment) {
                    mNormalFragment = new lotto_normalFragment(mShakeUtils, mFucationType);
                    transaction.add(R.id.lotto_fragment, mNormalFragment);
                }
                mShakeUtils.setCan(true);
                transaction.show(mNormalFragment);
                if (mDantuoFragment != null) {
                    transaction.hide(mDantuoFragment);
                }
                break;
        }
        transaction.commit();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void updateTitle(NumLotteryBean.DataBean bean) {
        if (bean.getIssueList() != null && bean.getIssueList().size() > 0) {
            String poolMoney = TextUtils.checkNumber(bean.getPoolMoney());
            String poolStr = "奖池：" + poolMoney + "元";
            TextUtils.setStrColor(lottoPool, poolStr, poolMoney, getResources().getColor(R.color.select_red));
            NumLotteryBean.DataBean.IssueListBean issueBean = bean.getIssueList().get(0);
            long longtime = issueBean.getStopTime();
            String time = DateUtils.formatMonthDay(longtime * 1000) + " " + DateUtils.formatTimeSimple(longtime * 1000);
            if (issueBean != null) {
                mIssueNo = issueBean.getIssueNo();
                lottoIssue.setText("第" + mIssueNo + "期");
                lottoStopTime.setText("截止:" + time);
            }
        }
        LogUtils.i("lottoactivity updateTitle over");
    }

    @Override
    public void onBackPressed() {
//        if ((mDantuoFragment != null && mDantuoFragment.hasSelected()) || (mNormalFragment != null && mNormalFragment.hasSelected())) {
//            showBackDialog();
//        } else {
        finish();
//        }
    }

    @Override
    protected void onPause() {
        mShakeUtils.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mShakeUtils.onResume();
        super.onResume();
    }
    @Override
    protected String getBaidutitle() {
        return "";
    }
}
