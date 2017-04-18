package shlottery.gov.cn.lotterycenter.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.Base.BaseFragment;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.VcodeBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingPager;
import shlottery.gov.cn.lotterycenter.utils.MyCountDownTimer;
import shlottery.gov.cn.lotterycenter.utils.RegexUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;


/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-17-0017 23:59
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class FogtPwdVcodeFragment extends BaseFragment {
    @BindView(R.id.forget_phone_et)
    EditText forgetPhoneEt;
    @BindView(R.id.forget_getcode_tv)
    TextView forgetGetcodeTv;
    @BindView(R.id.forget_pwd_tbr1)
    TableRow forgetPwdTbr1;
    @BindView(R.id.forget_vcode_et)
    EditText forgetVcodeEt;
    @BindView(R.id.forget_next_btn)
    Button forgetNextBtn;
    private String phoneNum;
    private String smsCode;
    private MyCountDownTimer myCountDownTimer;

    @Override
    protected View createLoadedView() {
        View view = UIUtils.inflate(R.layout.fragment_forgetpws_vcode);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected LoadingPager.LoadResult Load() {
        return check(0);
    }

    @OnClick(R.id.forget_next_btn)
    void nextStep() {
        phoneNum = forgetPhoneEt.getText().toString();
        smsCode = forgetVcodeEt.getText().toString();
        if ("".equals(phoneNum)) {
            UIUtils.showToastSafe("手机号不能为空");
            return;
        } else if (!RegexUtils.checkMobile(phoneNum)) {
            UIUtils.showToastSafe("手机号格式有误");
            return;
        } else if ("".equals(smsCode)) {
            UIUtils.showToastSafe("验证码不能为空");
            return;
        } else if (!smsCode.matches("^\\d{6}$")) {
            UIUtils.showToastSafe("验证码格式错误");
            return;
        }
        OkGo.get(UrlManager.findUser(getActivity(), phoneNum, smsCode)).cacheMode(CacheMode.NO_CACHE).connTimeOut(5000).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                VcodeBean vcodeBean = gson.fromJson(s, VcodeBean.class);
                if (null != vcodeBean) {
                    if (vcodeBean.getRet() == 100) {
                        FogtPwdFragment fogtPwdFragment = new FogtPwdFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("smsCode", smsCode);
                        bundle.putString("mobile", phoneNum);
                        fogtPwdFragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fogt_framelayout, fogtPwdFragment).commit();
                    } else {
                        UIUtils.showToastSafe(vcodeBean.getMsg());
                    }
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                UIUtils.showToastSafe("网络错误,请检查网络");
            }
        });

    }

    @OnClick(R.id.forget_getcode_tv)
    void getVcode() {
        phoneNum = forgetPhoneEt.getText().toString();
        if ("".equals(phoneNum)) {
            UIUtils.showToastSafe("手机号不能为空");
            return;
        } else if ("".equals(phoneNum) || !RegexUtils.checkMobile(phoneNum)) {
            UIUtils.showToastSafe("手机号格式有误");
            return;
        }
        forgetGetcodeTv.setEnabled(false);
        OkGo.get(UrlManager.getVcodeUrl(getActivity(), 2, phoneNum)).cacheMode(CacheMode.NO_CACHE).connTimeOut(5000).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                VcodeBean vcodeBean = gson.fromJson(s, VcodeBean.class);
                if (null != vcodeBean) {
                    if (vcodeBean.getRet() == 100) {
                        myCountDownTimer = new MyCountDownTimer(60000, 500, forgetGetcodeTv);
                        myCountDownTimer.start();
                        UIUtils.showToastSafe("验证码获取成功,请等待");
                    } else {
                        UIUtils.showToastSafe(vcodeBean.getMsg());
                        forgetGetcodeTv.setEnabled(true);
                    }
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                UIUtils.showToastSafe("网络错误,请检查网络");
                if (myCountDownTimer != null) {
                    myCountDownTimer.cancel();
                }
//                updateDialog(forgetGetcodeTv, "获取验证码", 1);
                forgetGetcodeTv.setEnabled(true);
                forgetGetcodeTv.setText("获取验证码");
            }
        });
    }
    @Override
    protected String getTitle() {
        return "忘记密码step 1";
    }
}
