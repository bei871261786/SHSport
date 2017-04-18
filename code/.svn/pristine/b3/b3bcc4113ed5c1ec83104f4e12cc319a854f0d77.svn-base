package shlottery.gov.cn.lotterycenter.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.ui.fragment.FogtPwdVcodeFragment;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-17-0017 23:56
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class ForgetPwdActivity extends BaseActivity {
    @BindView(R.id.registertop_back_ll)
    LinearLayout registertopBackLl;
    @BindView(R.id.registertop_tv)
    TextView registertopTv;
    @BindView(R.id.registertop_ll)
    LinearLayout registertopLl;
    @BindView(R.id.base_titleBar)
    RelativeLayout baseTitleBar;
    @BindView(R.id.fogt_framelayout)
    FrameLayout fogtFramelayout;
    FogtPwdVcodeFragment fogtPwdVcodeFragment;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_forgetpsw);
        ButterKnife.bind(this);
        registertopTv.setText("忘记密码");
        fogtPwdVcodeFragment = new FogtPwdVcodeFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.fogt_framelayout, fogtPwdVcodeFragment).commit();
    }

    @Override
    protected void init() {

    }

    @OnClick(R.id.registertop_back_ll)
    void close() {
        finish();
    }

    @Override
    protected String getBaidutitle() {
        return "忘记密码";
    }
}
