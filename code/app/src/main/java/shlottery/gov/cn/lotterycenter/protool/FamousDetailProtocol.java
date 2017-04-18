package shlottery.gov.cn.lotterycenter.protool;

import android.content.Context;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.FamousDetailBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/25 15:41
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class FamousDetailProtocol extends BaseProtocol {

    public FamousDetailProtocol(Context context) {
        super(context);
    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.getFamousDetail;
    }

    @Override
    protected Object parseData(String json) {
        Gson gson=new Gson();
        FamousDetailBean bean=gson.fromJson(json,FamousDetailBean.class);
        return bean;
    }

    @Override
    protected boolean isNeedLogin() {
        return false;
    }
}
