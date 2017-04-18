//package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.activity;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.View;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.google.gson.Gson;
//
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
//import shlottery.gov.cn.lotterycenter.R;
//import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
//import shlottery.gov.cn.lotterycenter.bean.Jincai.RegistBean;
//import shlottery.gov.cn.lotterycenter.bean.Jincai.UpdateBean;
//import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.http.HttpHelper;
//import shlottery.gov.cn.lotterycenter.utils.LogUtils;
//import shlottery.gov.cn.lotterycenter.utils.RegexUtils;
//import shlottery.gov.cn.lotterycenter.utils.SoccerDialogUtils;
//import shlottery.gov.cn.lotterycenter.utils.StringUtils;
//import shlottery.gov.cn.lotterycenter.utils.UIUtils;
//
///**
// * Created by booob on 2016-07-11-0011.
// */
//public class RegisterActivity extends BaseActivity {
//    private EditText mUserName;//用户名
//    private EditText mPhone;//手机号
//    private EditText mPassWord;//密码
//    private EditText mVerificationCode;//验证码
//    private TextView mVerfyCodeButton;//获取验证码
//    private Button mRegisterButton;//注册
//    private CheckBox mAgree;//是否同意协议
//    private LinearLayout mBack;//返回按钮
//    private TextView userinfoTitlebarTv;
//    protected TextView mRegitXieYi;//注册协议
//
//    private String userName;
//    private String password;
//    private String phoneNum;
//    private String smsCode;
//    private boolean isAgree;
//
//    String token = BaseApplication.getApplication().getmToken();
//    String signkey = BaseApplication.signKey;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    protected void initView() {
//        isAgree = true;
//        setContentView(R.layout.activity_register);
//        mUserName = (EditText) findViewById(R.id.username_et);
//        mPhone = (EditText) findViewById(R.id.phone_et);
//        mPassWord = (EditText) findViewById(R.id.password_et);
//        mVerificationCode = (EditText) findViewById(R.id.verificationcode_et);
//        mVerfyCodeButton = (TextView) findViewById(R.id.getcode_tv);
//        mRegisterButton = (Button) findViewById(R.id.register_btn);
////        mAgree = (CheckBox) findViewById(R.id.agree_cb);
//        mBack = (LinearLayout) findViewById(R.id.userinfo_titlebar_back);
//        userinfoTitlebarTv = (TextView) findViewById(R.id.user_titlebar_Tv);
//
////        mRegitXieYi = (TextView) findViewById(R.id.service_tv);
//        mRegitXieYi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SoccerDialogUtils.ShowXieYiDialog(RegisterActivity.this);
//            }
//        });
//        userinfoTitlebarTv.setText(R.string.register);
//        //checkbox勾选状态监听
//        mAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    isAgree = true;
//                } else {
//                    isAgree = false;
//                }
//            }
//        });
//
//
//        MyClickListener listener = new MyClickListener();
//        //注册事件
//        mRegisterButton.setOnClickListener(listener);
//        //获取验证码
//        mVerfyCodeButton.setOnClickListener(listener);
//        //返回
//        mBack.setOnClickListener(listener);
//    }
//
//    @Override
//    protected void init() {
//    }
//
//    private class MyClickListener implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//            userName = mUserName.getText().toString();
//            phoneNum = mPhone.getText().toString();
//            password = mPassWord.getText().toString();
//            smsCode = mVerificationCode.getText().toString();
//            switch (v.getId()) {
//                case R.id.register_btn://注册事件
//                    LogUtils.e("注册按钮点击了");
//                    /* 用户名为空 */
//                    if ("".equals(userName)) {
//                        UIUtils.showToastSafe("用户名不能为空");
//                        return;
//                        //Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
//                    }
//                    /*手机号不能为空*/
//                    else if ("".equals(phoneNum)) {
//                        UIUtils.showToastSafe("手机号不能为空");
//                        return;
//                    }
//                    /* 手机号填写不正确，必填的，判断是否为空 */
//                    else if ("".equals(phoneNum) || !phoneNum.matches("^(1[3|4|5|8])\\d{9}$")) {
//                        UIUtils.showToastSafe("手机号格式有误");
//                        return;
//                    }
//                    /* 密码为空 */
//                    else if ("".equals(password)) {
//                        UIUtils.showToastSafe("密码不能为空");
//                        return;
//                    }
//                    else if (!RegexUtils.checkPassWord(password)) {
//                        UIUtils.showToastSafe("密码为6-18位数字字母组合");
//                        return;
//                    }
//                    /*验证码*/
//                    else if ("".equals(smsCode)) {
//                        UIUtils.showToastSafe("验证码不能为空");
//                        return;
//                    } else if (!smsCode.matches("^\\d{6}$")) {
//                        UIUtils.showToastSafe("验证码格式错误");
//                        return;
//                    }
//                    /*购彩协议 */
//                    else if (!isAgree) {
//                        UIUtils.showToastSafe("未同意注册协议");
//                        return;
//                    } else {
//                        new Thread() {
//                            @Override
//                            public void run() {
//                                requestRegister();
//                            }
//                        }.start();
//                    }
//                    break;
//                case R.id.getcode_tv:
//                    if ("".equals(userName)) {
//                        UIUtils.showToastSafe("用户名不能为空");
//                        //Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
//                        return;
//                    }
//                    /*手机号不能为空*/
//                    else if ("".equals(phoneNum)) {
//                        UIUtils.showToastSafe("手机号不能为空");
//                        return;
//                    }
//                    /* 手机号填写不正确，必填的，判断是否为空 */
//                    else if (!RegexUtils.checkMobile(phoneNum)) {
//                        UIUtils.showToastSafe("手机号格式错误");
//                        return;
//                    }
//                    /* else if ("".equals(phoneNum) || !phoneNum.matches("^(1[3|4|5|8])\\d{9}$")) {
//                        UIUtils.showToastSafe("手机号格式错误");
//                    }*/
//                    /* 密码为空 */
//                    else if ("".equals(password)) {
//                        UIUtils.showToastSafe("密码不能为空");
//                        return;
//                    } else if (!RegexUtils.checkPassWord(password)) {
//                        UIUtils.showToastSafe("密码为6-18位数字字母组合");
//                        return;
//                    }
//                    new Thread() {
//                        @Override
//                        public void run() {
//                            requestGetCode();
//                        }
//                    }.start();
//                    break;
//                case R.id.userinfo_titlebar_back:
//                    finish();
//                    break;
//                default:
//                    break;
//            }
//
//        }
//
//        /**
//         * 注册请求
//         */
//        public void requestRegister() {
//            int mResult = 0;
//            List<NameValuePair> params = new ArrayList<>();
//            String mPassword = StringUtils.getMD5(phoneNum + password);
//            String sign = StringUtils.getMD5(token + signkey + phoneNum + smsCode + userName + mPassword);
//            params.add(new BasicNameValuePair("token", token));
//            params.add(new BasicNameValuePair("mobile", phoneNum));
//            params.add(new BasicNameValuePair("smsCode", smsCode));
//            params.add(new BasicNameValuePair("nickName", userName));
//            params.add(new BasicNameValuePair("password", mPassword));
//            params.add(new BasicNameValuePair("sign", sign));
//            String mRegisterUrl = HttpHelper.CeshiJCZQURL + HttpHelper.getRegister;
//            HttpHelper.HttpResult result = HttpHelper.post(mRegisterUrl, params);
//            if (result != null) {
//                LogUtils.e(mRegisterUrl + "注册地址");
//                LogUtils.e(result.getString() + "注册返回值");
//                String json = result.getString();
//                Gson gson = new Gson();
//                mResult = gson.fromJson(json, RegistBean.class).getRet();
//                if (100 == mResult) {
//                    String mSecret = gson.fromJson(json, RegistBean.class).getData().getSecret();
//                    String mUserUrl = gson.fromJson(json, RegistBean.class).getData().getLogoUrl();
//                    BaseApplication.setmSecret(mSecret);//将secret添加到baseApplication中
////                    BaseApplication.setmUserUrl(mUserUrl);
//                    BaseApplication.setmLoginStatus(true);
//                    finish();
//                } else {
//                    String msg = gson.fromJson(json, RegistBean.class).getMsg();
//                    UIUtils.showToastSafe(msg);//提示错误信息
//                }
//            }else {
//                UIUtils.showToastSafe("请检查网络设置");
//            }
//        }
//
//        //获取验证码
//        private void requestGetCode() {
//            List<NameValuePair> params = new ArrayList<>();
//            String type = "1";//类型(1-注册 2-找回密码 3-修改密码)
//            String sign = StringUtils.getMD5(token + signkey + phoneNum + type);
//            params.add(new BasicNameValuePair("token", token));
//            params.add(new BasicNameValuePair("mobile", phoneNum));
//            params.add(new BasicNameValuePair("type", type));
//            params.add(new BasicNameValuePair("sign", sign));
//            String mRegisterUrl = HttpHelper.CeshiJCZQURL + HttpHelper.sendVerify;
//            HttpHelper.HttpResult result = HttpHelper.post(mRegisterUrl, params);
//            if (result != null) {
//                LogUtils.e(mRegisterUrl + "获取短信验证码地址");
//                LogUtils.e(result.getString() + "短信验证码值");
//                LogUtils.e(token + signkey + phoneNum + type + ":" + sign);
//                Gson mGson = new Gson();
//                UpdateBean mUpdateBean = mGson.fromJson(result.getString(), UpdateBean.class);
//                if (mUpdateBean.getRet() == 100) {
//                    UIUtils.showToastSafe("验证码获取成功,请等待");
//                } else {
//                    UIUtils.showToastSafe(mUpdateBean.getMsg());
//                }
//            } else {
//                UIUtils.showToastSafe("网络错误,请检查网络");
//            }
//        }
//    }
//    @Override
//    protected String getBaidutitle() {
//        return "";
//    }
//}
