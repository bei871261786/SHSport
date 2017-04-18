package shlottery.gov.cn.lotterycenter.protool;

import android.content.Context;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.LoginBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;

/**
 * @创建者 Tyler Wang.
 * @创建时间 2017/4/10  10:07.
 * @描述 ${注册用户}.
 */
public class RegisterAccountProtocol extends BaseProtocol {

    public RegisterAccountProtocol(Context context) {
        super(context);
    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.getRegister;
    }

    @Override
    protected Object parseData(String json) {
        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(json, LoginBean.class);
        return loginBean;
    }

    @Override
    protected boolean isNeedLogin() {
        return false;
    }
}
