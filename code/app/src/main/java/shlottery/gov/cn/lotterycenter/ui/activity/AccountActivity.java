package shlottery.gov.cn.lotterycenter.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.Base.BaseFragment2;
import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.ui.fragment.AccountJifenFragment;
import shlottery.gov.cn.lotterycenter.ui.fragment.AccountVoucherFragment;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

public class AccountActivity extends BaseActivity {
    private FragmentManager mFragmentManager;
    private BaseFragment2 mJifenFragment;
    private BaseFragment2 mVoucherFragment;
    private RadioGroup mHeadGrouup;
    private MyListener mListener;
    private LinearLayout mBack;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_account);
        //  mCashing = (ImageView) findViewById(R.id.main_cashing);
        mBack = (LinearLayout) findViewById(R.id.titlebar_back_ll);
        mHeadGrouup = (RadioGroup) findViewById(R.id.radioGroup);
        //  mCashing.setOnClickListener(mListener);
        mBack.setOnClickListener(mListener);

        mHeadGrouup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioGroup.getChildAt(0).getId() == i) {
                    toggleFragment(0);
                } else if (radioGroup.getChildAt(1).getId() == i) {
                    toggleFragment(1);
                }
            }
        });
        initTitleBar();
        toggleFragment(0);
    }

    @Override
    protected void init() {
        mListener = new MyListener();
        mFragmentManager = getSupportFragmentManager();
    }

    private void initTitleBar() {
        LinearLayout mBack = (LinearLayout) findViewById(R.id.titlebar_back_ll);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titlebarTv = (TextView) findViewById(R.id.titlebar_tv);
        titlebarTv.setText("账户");
        ImageView mSelfQuestion = (ImageView) findViewById(R.id.titlebar_indicator);
        mSelfQuestion.setVisibility(View.GONE);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void toggleFragment(int type) {
        LogUtils.i("切换fragment");
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        switch (type) {
            case 0:
                if (null == mVoucherFragment) {
                    mVoucherFragment = new AccountVoucherFragment();
                    transaction.add(R.id.account_fragment, mVoucherFragment);
                }
                transaction.show(mVoucherFragment);
                if (mJifenFragment != null) {
                    transaction.hide(mJifenFragment);
                }
                break;

            case 1:
                if (null == mJifenFragment) {
                    mJifenFragment = new AccountJifenFragment();
                    transaction.add(R.id.account_fragment, mJifenFragment);
                }
                transaction.show(mJifenFragment);
                if (mVoucherFragment != null) {
                    transaction.hide(mVoucherFragment);
                }
                break;

        }
        transaction.commit();
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.titlebar_back_ll:
                    setResult(Configure.RESULT_UPDATE);
                    finish();
                    break;
                // 兑换
//                case R.id.main_cashing:
//                    Intent intent = new Intent(AccountActivity.this, CashingActivity.class);
//                    startActivityForResult(intent, 0);
//                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mJifenFragment != null) {
            mJifenFragment.refreshLoad();
        }
        if (mVoucherFragment != null) {
            mVoucherFragment.refreshLoad();
        }
    }

    @Override
    public void onBackPressed() {
        setResult(Configure.RESULT_UPDATE);
        super.onBackPressed();
    }
    @Override
    protected String getBaidutitle() {
        return "账户";
    }

}
