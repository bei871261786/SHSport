package shlottery.gov.cn.lotterycenter.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.Base.BaseFragment;
import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.LoginBean;
import shlottery.gov.cn.lotterycenter.bean.UserInfoBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.callback.LoginCallBack;
import shlottery.gov.cn.lotterycenter.callback.LoginDialogEventBus;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.LoginProtacol;
import shlottery.gov.cn.lotterycenter.ui.activity.ForgetPwdActivity;
import shlottery.gov.cn.lotterycenter.ui.activity.RegisterActivityNew;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingPager;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.RegexUtils;
import shlottery.gov.cn.lotterycenter.utils.SharedPreferencesUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/15 15:10
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public abstract class LoginBaseFragment extends BaseFragment implements LoadCallback<LoginBean> {

    @BindView(R.id.username_tv)
    ImageView usernameTv;
    @BindView(R.id.home_login_username_et)
    EditText mUserNameEdit;
    @BindView(R.id.home_login_delete_ib)
    ImageButton homeLoginDeleteIb;
    @BindView(R.id.home_login_history_ib)
    ImageButton homeLoginHistoryIb;
    @BindView(R.id.home_login_delete_ll)
    LinearLayout homeLoginDeleteLl;
    @BindView(R.id.home_login_username_rl)
    RelativeLayout homeLoginUsernameRl;
    @BindView(R.id.password_tv)
    ImageView passwordTv;
    @BindView(R.id.home_login_password_et)
    EditText mPasswordEdit;
    @BindView(R.id.home_password_delete_ib)
    ImageButton homePasswordDeleteIb;
    @BindView(R.id.home_password_history_ib)
    ImageButton homePasswordHistoryIb;
    @BindView(R.id.home_password_delete_ll)
    LinearLayout homePasswordDeleteLl;
    @BindView(R.id.home_login_vertifyCode_et)
    EditText homeLoginVertifyCodeEt;
    @BindView(R.id.home_login_vertifycode_iv)
    ImageView homeLoginVertifycodeIv;
    @BindView(R.id.home_login_vertifycode_rl)
    RelativeLayout homeLoginVertifycodeRl;
    @BindView(R.id.home_login_login_btn)
    Button mLogin;
    @BindView(R.id.home_login_forgetpasswrod_tv)
    TextView homeLoginForgetpasswrodTv;
    @BindView(R.id.home_login_regist_btn)
    TextView homeLoginRegistBtn;
    @BindView(R.id.home_login_input_ll)
    LinearLayout homeLoginInputLl;
    @BindView(R.id.activity_user_center)
    @Nullable
    RelativeLayout activityUserCenter;
    private MyListener myListener;
    //    private Button mLogin;
    private LoginCallBack mLoginCallback;
    private LoginBean.DataBean mLoginData;
    private UserInfoBean mUserInfoBean;

    protected ImageView mDeleteUser;
    protected ImageView mDeletePassWord;

    protected abstract View prepareView();

    protected abstract void handleSuccess(Activity mLoginActivity);

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = prepareView();
        ButterKnife.bind(this, view);
        mLogin = (Button) view.findViewById(R.id.home_login_login_btn);
        mUserNameEdit = (EditText) view.findViewById(R.id.home_login_username_et);
        mPasswordEdit = (EditText) view.findViewById(R.id.home_login_password_et);
        mDeleteUser = (ImageView) view.findViewById(R.id.home_login_delete_ib);
        mDeletePassWord = (ImageView) view.findViewById(R.id.home_password_delete_ib);
        myListener = new MyListener();
        if (getActivity() instanceof LoginCallBack) {
            mLoginCallback = (LoginCallBack) getActivity();
        }
        mLogin.setOnClickListener(myListener);
        homeLoginForgetpasswrodTv.setOnClickListener(myListener);
        homeLoginRegistBtn.setOnClickListener(myListener);
        mDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserNameEdit.setText("");
                mDeleteUser.setVisibility(View.GONE);
            }

        });
        mDeletePassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPasswordEdit.setText("");
                mDeletePassWord.setVisibility(View.GONE);
            }

        });
        //editor 手机号监听
        mUserNameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LogUtils.i("mUserNameEdit afterTextChanged:" + mUserNameEdit.getText().toString() + ":::" + TextUtils.isEmpty(mUserNameEdit.getText()));
                if (!TextUtils.isEmpty(mUserNameEdit.getText()) && !"".equals(mUserNameEdit.getText().toString().trim())) {
                    mDeleteUser.setVisibility(View.VISIBLE);
                } else {
                    mDeletePassWord.setVisibility(View.GONE);
                }
            }
        });

        //密码监控
        mPasswordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(mPasswordEdit.getText()) || "".equals(mPasswordEdit.getText().toString().trim())) {
                    mDeletePassWord.setVisibility(View.VISIBLE);

                }

            }
        });
        return view;
    }

    @Override
    protected View createLoadedView() {
        return null;
    }

    @Override
    protected LoadingPager.LoadResult Load() {
        return check(0);
    }

    @Override
    public void onSucess(LoginBean data) {
        if (data != null && data.getData() != null) {
            LogUtils.i("LoginFragment 登陆成功" + data);
            mLoginData = data.getData();
            mUserInfoBean = new UserInfoBean();
            mUserInfoBean.setLogoUrl(mLoginData.getLogoUrl());
            mUserInfoBean.setMobile(mLoginData.getMobile());
            mUserInfoBean.setNickName(mLoginData.getNickName());
            mUserInfoBean.setRealName(mLoginData.getRealName());
            mUserInfoBean.setSecret(mLoginData.getSecret());
            mUserInfoBean.setUserType(mLoginData.getUserType());
            BaseApplication.setmLoginStatus(true);
            SharedPreferencesUtils.putObject(Configure.SPKEY_USERINFO, mUserInfoBean);
            if (mLoginCallback != null) {
                mLoginCallback.toggleLoginFragment(true);
            }

            handleSuccess(getActivity());
            EventBus.getDefault().post(new LoginDialogEventBus(true));
        } else {
                UIUtils.showToastSafe(data.getMsg());
        }
    }

    @Override
    public void onError() {
        LogUtils.i("LoginFragment 登陆失败");
    }


    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.home_login_login_btn:
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mPasswordEdit.getWindowToken(), 0);
                    login();
                    break;
                case R.id.home_login_regist_btn:
//                    startActivity(new Intent(getActivity(), RegisterActivity.class));
                    startActivity(new Intent(getActivity(), RegisterActivityNew.class));
                    break;
                case R.id.home_login_forgetpasswrod_tv:
                    startActivity(new Intent(getActivity(), ForgetPwdActivity.class));
                    break;
            }
        }
    }

    private void login() {
        final String userName = mUserNameEdit.getText().toString();
        final String pwd = mPasswordEdit.getText().toString();
        if (RegexUtils.checkMobile(userName)) {
            if (RegexUtils.checkPassWord(pwd)) {
                LoginProtacol protacol = new LoginProtacol(getActivity());
                final String userPwd = StringUtils.getMD5(userName + pwd);
                protacol.load(this, new ParamsHelperInterface() {
                    @Override
                    public LinkedHashMap<String, String> getParamas() {
                        LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                        params.put("mobile", userName);
                        params.put("password", userPwd);
                        LogUtils.i("login params:" + userName + ":::" + userPwd);
                        return params;
                    }
                }, this);
            } else {
                UIUtils.showToastSafe("请输入正确的密码");
            }
        } else {
            UIUtils.showToastSafe("请输入正确的手机号");
        }
    }


}
