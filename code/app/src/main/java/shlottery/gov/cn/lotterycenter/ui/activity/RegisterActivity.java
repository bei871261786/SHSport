package shlottery.gov.cn.lotterycenter.ui.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.LoginBean;
import shlottery.gov.cn.lotterycenter.bean.UserInfoBean;
import shlottery.gov.cn.lotterycenter.bean.VcodeBean;
import shlottery.gov.cn.lotterycenter.callback.LoginEvenbus;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.MyCountDownTimer;
import shlottery.gov.cn.lotterycenter.utils.RegexUtils;
import shlottery.gov.cn.lotterycenter.utils.SharedPreferencesUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-15-0015 13:11
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.registertop_back_ll)
    LinearLayout registertopBackLl;
    @BindView(R.id.registertop_tv)
    TextView registertopTv;
    @BindView(R.id.registertop_ll)
    LinearLayout registertopLl;
    @BindView(R.id.base_titleBar)
    RelativeLayout baseTitleBar;
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.getcode_tv)
    TextView getcodeTv;
    @BindView(R.id.oldphone_ll)
    TableRow oldphoneLl;
    @BindView(R.id.verificationcode_et)
    EditText verificationcodeEt;
    @BindView(R.id.username_et)
    EditText usernameEt;
    @BindView(R.id.username_tl)
    TableRow usernameTl;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.re_password_et)
    EditText rePasswordEt;
    @BindView(R.id.register_btn)
    Button registerBtn;

    private String userName;
    private String password;
    private String rePassword;
    private String phoneNum;
    private String smsCode;
    private MyCountDownTimer myCountDownTimer;//验证码倒计时工具类

    @Override
    protected void initView() {
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.registertop_back_ll)
    public void finishActiviy() {
        finish();
    }

    @OnClick(R.id.getcode_tv)
    public void getCode() {
        getVcode();//获取验证码
    }

    private void getVcode() {
        userName = usernameEt.getText().toString();
        phoneNum = phoneEt.getText().toString();
        password = passwordEt.getText().toString();
        rePassword = rePasswordEt.getText().toString();//重复密码
        smsCode = verificationcodeEt.getText().toString();

        if (StringUtils.isEmpty(phoneNum)) {
            UIUtils.showToastSafe("手机号不能为空");
            return;
        } else if (!RegexUtils.checkMobile(phoneNum)) {
            UIUtils.showToastSafe("手机号格式错误");
            return;
        }
        getcodeTv.setEnabled(false);
        OkGo.get(UrlManager.getVcodeUrl(this, 1, phoneNum)).cacheMode(CacheMode.NO_CACHE).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                VcodeBean vcodeBean = gson.fromJson(s, VcodeBean.class);
                if (null != vcodeBean) {
                    if (vcodeBean.getRet() == 100) {
                        UIUtils.showToastSafe("验证码获取成功,请等待");
                        myCountDownTimer = new MyCountDownTimer(60000, 500, getcodeTv);
                        myCountDownTimer.start();
                    } else {
                        UIUtils.showToastSafe(vcodeBean.getMsg());
                        getcodeTv.setEnabled(true);
                    }
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                UIUtils.showToastSafe("网络错误,请检查网络");
                getcodeTv.setEnabled(true);
                getcodeTv.setText("获取验证码");
            }
        });
    }

    @OnClick(R.id.register_btn)
    public void register() {
        registerAccount();//注册账号

    }

    private void registerAccount() {
        userName = usernameEt.getText().toString();
        phoneNum = phoneEt.getText().toString();
        password = passwordEt.getText().toString();
        rePassword = rePasswordEt.getText().toString();//重复密码
        smsCode = verificationcodeEt.getText().toString();
        if ("".equals(phoneNum)) {
            UIUtils.showToastSafe("手机号不能为空");
            return;
        } else if (!RegexUtils.checkMobile(phoneNum)) {
            UIUtils.showToastSafe("手机号格式有误");
            return;
        } else if ("".equals(userName)) {
            UIUtils.showToastSafe("用户名不能为空");
            return;
        } else if (!RegexUtils.checkNickName(userName)) {
            UIUtils.showToastSafe("请输入昵称(2-16个中英文字或者数字组成)");
            return;
        } else if ("".equals(smsCode)) {
            UIUtils.showToastSafe("验证码不能为空");
            return;
        } else if (!smsCode.matches("^\\d{6}$")) {
            UIUtils.showToastSafe("验证码格式错误");
            return;
        } else if ("".equals(password)) {
            UIUtils.showToastSafe("密码不能为空");
            return;
        } else if (!RegexUtils.checkPassWord(password)) {
            UIUtils.showToastSafe("密码由6-16个英文+数字组成,区分大小写");
            return;
        } else if ("".equals(rePassword)) {
            UIUtils.showToastSafe("重复密码不能为空");
            return;
        } else if (!password.equals(rePassword)) {
            UIUtils.showToastSafe("两次输入的密码不一致");
            return;
        } else {
            OkGo.get(UrlManager
                    .getRegister(this, phoneNum, smsCode, userName, password))
                    .cacheMode(CacheMode.NO_CACHE)
                    .execute(new StringCallback() {
                                 @Override
                                 public void onSuccess(String s, Call call, Response response) {
                                     Gson gson = new Gson();
                                     LoginBean data = gson.fromJson(s, LoginBean.class);
                                     if (null != data) {
                                         if (data.getRet() == 100) {
                                             if (data != null && data.getData() != null) {
                                                 LogUtils.i("LoginFragment 登陆成功" + data);
                                                 LoginBean.DataBean mLoginData = data.getData();
                                                 UserInfoBean mUserInfoBean = new UserInfoBean();
                                                 mUserInfoBean.setLogoUrl(mLoginData.getLogoUrl());
                                                 mUserInfoBean.setMobile(mLoginData.getMobile());
                                                 mUserInfoBean.setNickName(mLoginData.getNickName());
                                                 mUserInfoBean.setRealName(mLoginData.getRealName());
                                                 mUserInfoBean.setSecret(mLoginData.getSecret());
                                                 mUserInfoBean.setUserType(mLoginData.getUserType());
                                                 BaseApplication.setmLoginStatus(true);
                                                 SharedPreferencesUtils.putObject(Configure.SPKEY_USERINFO,
                                                         mUserInfoBean);
                                                 UIUtils.showToastSafe("注册成功");
                                                 LoginEvenbus eventbus = new LoginEvenbus();
                                                 eventbus.setLogin(true);
                                                 EventBus.getDefault().post(eventbus);
                                                 finish();
                                             }
                                         } else {
                                             UIUtils.showToastSafe(data.getMsg());
                                         }
                                     }
                                 }

                                 @Override
                                 public void onError(Call call, Response response, Exception e) {
                                     super.onError(call, response, e);
                                     UIUtils.showToastSafe("网络错误,请检查网络");
                                 }
                             }

                    );
        }

    }

    @Override
    protected void init() {

    }

    @Override
    protected String getBaidutitle() {
        return "注册";
    }
}
