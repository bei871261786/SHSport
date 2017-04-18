package shlottery.gov.cn.lotterycenter.protool;

import android.content.Context;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.SuperGListBean;
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

public class SuperGListProtocol extends BaseProtocol {

    public SuperGListProtocol(Context context) {
        super(context);
    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.getSuperGList;
    }

    @Override
    protected SuperGListBean parseData(String json) {
        Gson gson = new Gson();
        SuperGListBean bean = gson.fromJson(json, SuperGListBean.class);
        return bean;
    }

    @Override
    protected boolean isNeedLogin() {
        return false;
    }
}

