package shlottery.gov.cn.lotterycenter.protool;

import android.content.Context;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.LoginBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/16 11:48
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class LoginProtacol extends BaseProtocol<LoginBean> {
    public LoginProtacol(Context context) {
        super(context);
    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.getLogin;
    }

    @Override
    protected LoginBean parseData(String json) {
        Gson gson = new Gson();
        LoginBean.DataBean dataBean;
        LoginBean loginBean = gson.fromJson(json, LoginBean.class);
        return loginBean;
    }

    @Override
    protected boolean isNeedLogin() {
        return false;
    }
}
