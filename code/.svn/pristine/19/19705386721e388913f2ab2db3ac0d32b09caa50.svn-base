package shlottery.gov.cn.lotterycenter.protool;

import android.content.Context;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.JifenBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/21 16:10
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class JifenProtacol extends BaseProtocol<JifenBean> {
    public JifenProtacol(Context context) {
        super(context);
    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.getIntegralList;
    }

    @Override
    protected JifenBean parseData(String json) {
        Gson gson = new Gson();
        JifenBean signinBean = gson.fromJson(json, JifenBean.class);
        LogUtils.i("JifenProtacol: " + signinBean);
        return signinBean;
    }

    @Override
    protected boolean isNeedLogin() {
        return true;
    }
}
