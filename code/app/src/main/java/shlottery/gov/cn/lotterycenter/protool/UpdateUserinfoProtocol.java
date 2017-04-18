package shlottery.gov.cn.lotterycenter.protool;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.UserBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/17 13:34
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class UpdateUserinfoProtocol extends BaseProtocol<UserBean> {


    public UpdateUserinfoProtocol() {
        super(null);
    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.getUserInfo;
    }

    @Override
    protected UserBean parseData(String json) {
        Gson gson = new Gson();
        UserBean bean = gson.fromJson(json, UserBean.class);
        return bean;
    }

    @Override
    protected boolean isNeedLogin() {
        return true;
    }
}
