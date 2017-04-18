package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.Jincai.UpdateBean;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.http.HttpHelper;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.RegexUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * Created by booob on 2016-08-16-0016.
 * w忘记密码的activity
 */
public class ForgetPasswordActivity extends BaseActivity {

    @BindView(R.id.userinfo_titlebar_back)
    LinearLayout userinfoTitlebarBack;
    @BindView(R.id.user_titlebar_Tv)
    TextView userTitlebarTv;
    @BindView(R.id.userinfo_filtrate)
    ImageView userinfoFiltrate;
    @BindView(R.id.mobilennumber_forgetpassword_et)
    EditText mobilennumberForgetpasswordEt;
    @BindView(R.id.forgetpassword_getcode_tv)
    TextView forgetpasswordGetcodeTv;
    @BindView(R.id.forgetpsw_verificationcode_et)
    EditText forgetpswVerificationcodeEt;
    @BindView(R.id.forget_psw_btn)
    Button forgetPswBtn;
    private ForgetPSWListener mForgetPSWListener;

    private String mVerCode;
    private String mPhone;

    private String token = BaseApplication.getApplication().getmToken();
    private String signkey = BaseApplication.signKey;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_forgetpsw1);
        ButterKnife.bind(this);
        userTitlebarTv.setText("忘记密码");//设置标题
        mForgetPSWListener = new ForgetPSWListener();
        userinfoTitlebarBack.setOnClickListener(mForgetPSWListener);
        forgetPswBtn.setOnClickListener(mForgetPSWListener);
        forgetpasswordGetcodeTv.setOnClickListener(mForgetPSWListener);
    }

    @Override
    protected void init() {

    }

    private class ForgetPSWListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.userinfo_titlebar_back:
                    finish();
                    break;
                case R.id.forgetpassword_getcode_tv:
                    mPhone = mobilennumberForgetpasswordEt.getText().toString();
                    if ("".equals(mPhone)) {
                        UIUtils.showToastSafe("手机号不能为空");
                        return;
                    }
                    if (!RegexUtils.checkMobile(mPhone)) {
                        UIUtils.showToastSafe("请输入正确的手机号");
                        return;
                    }
                    new Thread() {
                        @Override
                        public void run() {
                            requestGetCode();
                        }
                    }.start();
                    break;
                case R.id.forget_psw_btn:
                    mPhone = mobilennumberForgetpasswordEt.getText().toString();
                    mVerCode = forgetpswVerificationcodeEt.getText().toString();
                    if ("".equals(mPhone)) {
                        UIUtils.showToastSafe("手机号不能为空");
                        return;
                    }
                    if (!RegexUtils.checkMobile(mPhone)) {
                        UIUtils.showToastSafe("请输入正确的手机号");
                        return;
                    }/*验证码*/ else if ("".equals(mVerCode)) {
                        UIUtils.showToastSafe("验证码不能为空");
                        return;
                    } else if (!RegexUtils.checkSmsCode(mVerCode)) {
                        UIUtils.showToastSafe("验证码格式错误");
                        return;
                    } else {
                        new Thread() {
                            @Override
                            public void run() {
                                findPwdRequest();
                            }
                        }.start();
                    }
                    break;
                default:
                    break;
            }

        }
    }


    /**
     * 修改密码请求
     */
    public void findPwdRequest() {
        int mResult = 0;
        List<NameValuePair> params = new ArrayList<>();
        String sign = StringUtils.getMD5(token + signkey + mPhone + mVerCode);
        params.add(new BasicNameValuePair("token", token));
        params.add(new BasicNameValuePair("mobile", mPhone));
        params.add(new BasicNameValuePair("smsCode", mVerCode));
        params.add(new BasicNameValuePair("sign", sign));
        String mRegisterUrl = HttpHelper.CeshiJCZQURL + HttpHelper.findUser;
        LogUtils.i("找回密码 url：" + mRegisterUrl);
        HttpHelper.HttpResult result = HttpHelper.post(mRegisterUrl, params);
//        LogUtils.i("修改密码：" + mRegisterUrl + "?token=" + token + "&oldPwd=" + getNewPassWord(mOldPassWord) + "&newPwd=" + getNewPassWord(mNewPassWord) + "&smsCode=" + mVerCode + "&sign=" + sign);
        if (result != null) {
            LogUtils.e(result.getString() + "找回密码返回值");
            String json = result.getString();
            LogUtils.e(result.getCode() + "");
            Gson gson = new Gson();
            mResult = gson.fromJson(json, UpdateBean.class).getRet();

            if (100 == mResult) {
                if (gson.fromJson(json, UpdateBean.class).getData().getMobile().equals(mPhone)) {
                    UIUtils.showToastSafe("更改成功");
                    Intent mIntent = new Intent(ForgetPasswordActivity.this, ResettingPswActivity.class);
                    mIntent.putExtra("phone", mPhone);
                    mIntent.putExtra("VerCode", mVerCode);
                    startActivityForResult(mIntent, 6);
                } else {
                    UIUtils.showToastSafe("两次手机号不一致");//提示更新成功
                }

            } else {
                String msg = gson.fromJson(json, UpdateBean.class).getMsg();
                UIUtils.showToastSafe(msg);//提示错误信息
            }
        }else {
            UIUtils.showToastSafe("请检查网络设置");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 6 && resultCode == 6) {
            finish();
        }
    }
//获取验证码

    private void requestGetCode() {
        List<NameValuePair> params = new ArrayList<>();
        String type = "2";//类型(1-注册 2-找回密码 3-修改密码)
        String sign = StringUtils.getMD5(token + signkey + mPhone + type);
        LogUtils.e(token + "_" + signkey + "_" + mPhone + "_" + type + "_" + "签名");
        params.add(new BasicNameValuePair("token", token));
        params.add(new BasicNameValuePair("mobile", mPhone));
        params.add(new BasicNameValuePair("type", type));
        params.add(new BasicNameValuePair("sign", sign));
        String mRegisterUrl = HttpHelper.CeshiJCZQURL + HttpHelper.sendVerify;
        HttpHelper.HttpResult result = HttpHelper.post(mRegisterUrl, params);
        if (result != null) {
            LogUtils.e(mRegisterUrl + "获取短信验证码地址");
            LogUtils.e(result.getString() + "短信验证码值");
            LogUtils.e(token + signkey + mPhone + type + ":" + sign);
            Gson mGson = new Gson();
            UpdateBean mUpdateBean = mGson.fromJson(result.getString(), UpdateBean.class);
            if (mUpdateBean.getRet() == 100) {
                UIUtils.showToastSafe("验证码获取成功,请等待");
            } else {
                UIUtils.showToastSafe(mUpdateBean.getMsg());
            }
        } else {
            UIUtils.showToastSafe("网络错误,请检查网络");
        }
    }

    @Override
    protected String getBaidutitle() {
        return "";
    }
}
