package shlottery.gov.cn.lotterycenter.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class FogtPwdFragment extends BaseFragment {
    @BindView(R.id.fogt_password_et)
    EditText fogtPasswordEt;
    @BindView(R.id.refogt_password_et)
    EditText refogtPasswordEt;
    @BindView(R.id.forget_next_btn)
    Button forgetNextBtn;

    private String phoneNum;
    private String smsCode;
    private String newPwd;//新密码
    private String reNewPwd;//重复新密码

    @Override
    protected View createLoadedView() {
        View view = UIUtils.inflate(R.layout.fragment_forgetpwd_pwd);
        ButterKnife.bind(this, view);
        phoneNum = getArguments().getString("mobile");
        smsCode = getArguments().getString("smsCode");
        return view;
    }

    @Override
    protected LoadingPager.LoadResult Load() {
        return check(0);
    }

    @OnClick(R.id.forget_next_btn)
    void reSetPws() {
        reNewPwd = refogtPasswordEt.getText().toString();
        newPwd = fogtPasswordEt.getText().toString();
        if ("".equals(newPwd)) {
            UIUtils.showToastSafe("密码不能为空");
            return;
        } else if (!RegexUtils.checkPassWord(newPwd)) {
            UIUtils.showToastSafe("密码由6-16个英文+数字组成,区分大小写");
            return;
        } else if ("".equals(reNewPwd)) {
            UIUtils.showToastSafe("重复密码不能为空");
            return;
        } else if (!RegexUtils.checkPassWord(reNewPwd)) {
            UIUtils.showToastSafe("重复密码由6-16个英文+数字组成,区分大小写");
            return;
        } else if (!newPwd.equals(reNewPwd)) {
            UIUtils.showToastSafe("两次输入的密码不一致,请重新输入");
            return;
        }
        OkGo.get(UrlManager.resetPwd(getActivity(), phoneNum, newPwd, smsCode)).cacheMode(CacheMode.NO_CACHE).connTimeOut(5000).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                VcodeBean vcodeBean = gson.fromJson(s, VcodeBean.class);
                if (null != vcodeBean) {
                    if (vcodeBean.getRet() == 100) {
                        UIUtils.showToastSafe("修改成功");
                        getActivity().finish();
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
    @Override
    protected String getTitle() {
        return "忘记密码step 2";
    }
}
