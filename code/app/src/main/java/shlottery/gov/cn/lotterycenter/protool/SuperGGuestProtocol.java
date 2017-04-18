package shlottery.gov.cn.lotterycenter.protool;

import android.content.Context;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.SuperGGuestBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/24 16:45
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class SuperGGuestProtocol extends BaseProtocol {

    public SuperGGuestProtocol(Context context) {
        super(context);
    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.getGuest;
    }

    @Override
    protected SuperGGuestBean parseData(String json) {
        Gson gson = new Gson();
        SuperGGuestBean bean = gson.fromJson(json, SuperGGuestBean.class);
        return bean;
    }

    @Override
    protected boolean isNeedLogin() {
        return false;
    }
}

