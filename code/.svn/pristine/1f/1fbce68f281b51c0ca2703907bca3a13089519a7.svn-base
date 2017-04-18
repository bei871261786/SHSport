package shlottery.gov.cn.lotterycenter.protool;

import android.content.Context;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.NewsListBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/25 15:41
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class NewsListProtocol extends BaseProtocol {
    public NewsListProtocol(Context context) {
        super(context);
    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.getNewsList;
    }

    @Override
    protected Object parseData(String json) {
        LogUtils.i("parserData:" + json);
        Gson gson = new Gson();
        NewsListBean bean = gson.fromJson(json, NewsListBean.class);
        return bean;
    }

    @Override
    protected boolean isNeedLogin() {
        return false;
    }

    @Override
    protected boolean isNeedDialog() {
        return false;
    }
}
