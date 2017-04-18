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
 */
public class ResettingPswActivity extends BaseActivity {


    @BindView(R.id.userinfo_titlebar_back)
    LinearLayout userinfoTitlebarBack;
    @BindView(R.id.user_titlebar_Tv)
    TextView userTitlebarTv;
    @BindView(R.id.userinfo_filtrate)
    ImageView userinfoFiltrate;
    @BindView(R.id.resetting_psw_btn)
    Button resettingPswBtn;
    @BindView(R.id.resetting_psw_et)
    EditText resettingPswEt;
    @BindView(R.id.re_resetting_psw_et)
    EditText reResettingPswEt;

    private String mNewPassWord;
    private String mReNewPassWord;
    private String mVerCode;
    private String mPhone;

    private String token = BaseApplication.getApplication().getmToken();
    private String signkey = BaseApplication.signKey;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_resettingpsw);
        ButterKnife.bind(this);
        userTitlebarTv.setText("重置密码");//设置标题
        ResettingPswOnlickListener mResettingPswOnlickListener = new ResettingPswOnlickListener();
        userinfoTitlebarBack.setOnClickListener(mResettingPswOnlickListener);
        resettingPswBtn.setOnClickListener(mResettingPswOnlickListener);
        Intent mIntent = getIntent();
        mVerCode = mIntent.getStringExtra("VerCode");
        mPhone = mIntent.getStringExtra("phone");
    }

    @Override
    protected void init() {

    }


    private class ResettingPswOnlickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.userinfo_titlebar_back:
                    finish();
                    break;
                case R.id.resetting_psw_btn:
                    mNewPassWord = resettingPswEt.getText().toString();
                    mReNewPassWord = reResettingPswEt.getText().toString();
                    if ("".equals(mNewPassWord)) {
                        UIUtils.showToastSafe("新密码不能为空");
                        return;
                    }
                    if (!RegexUtils.checkPassWord(mNewPassWord)) {
                        UIUtils.showToastSafe("新密码格式不正确,密码由6-16个字母数字组合而成,区分大小写");
                        return;
                    }
                    if ("".equals(mReNewPassWord)) {
                        UIUtils.showToastSafe("确认密码不能为空");
                        return;
                    }
                    if (!RegexUtils.checkPassWord(mReNewPassWord)) {
                        UIUtils.showToastSafe("确认密码格式不正确,密码由6-16个字母数字组合而成,区分大小写");
                        return;
                    }
                    if (!mNewPassWord.equals(mReNewPassWord)) {
                        UIUtils.showToastSafe("两次密码输入不一致");
                        return;

                    } else {
                        new Thread() {
                            @Override
                            public void run() {
                                requestResettingPwd();
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
    public void requestResettingPwd() {
        int mResult = 0;
        String password = StringUtils.getMD5(mPhone + mNewPassWord);
        List<NameValuePair> params = new ArrayList<>();
        String sign = StringUtils.getMD5(token + signkey + mPhone + mVerCode + password);
        LogUtils.e(mNewPassWord + mVerCode + "旧密码");
        params.add(new BasicNameValuePair("token", token));
        params.add(new BasicNameValuePair("mobile", mPhone));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("smsCode", mVerCode));
        params.add(new BasicNameValuePair("sign", sign));
        String mRegisterUrl = HttpHelper.CeshiJCZQURL + HttpHelper.resetPwd;
        LogUtils.e(token + "signkey-----" + signkey + "------password" + password + "phone" + mPhone + "__mVerCode_" + mVerCode);

        HttpHelper.HttpResult result = HttpHelper.post(mRegisterUrl, params);
//        LogUtils.i("修改密码：" + mRegisterUrl + "?token=" + token + "&oldPwd=" + getNewPassWord(mOldPassWord) + "&newPwd=" + getNewPassWord(mNewPassWord) + "&smsCode=" + mVerCode + "&sign=" + sign);
        if (result != null) {
            LogUtils.e(result.getString() + "重置密码返回值");
            String json = result.getString();
            LogUtils.e(result.getCode() + "");
            Gson gson = new Gson();
            mResult = gson.fromJson(json, UpdateBean.class).getRet();
            if (100 == mResult) {
                UIUtils.showToastSafe("重置成功");//提示更新成功
                setResult(6);
                finish();
            } else {
                String msg = gson.fromJson(json, UpdateBean.class).getMsg();
                UIUtils.showToastSafe(msg);//提示错误信息
            }
        } else {
            UIUtils.showToastSafe("请检查网络设置");
        }
    }
    @Override
    protected String getBaidutitle() {
        return "";
    }
}
