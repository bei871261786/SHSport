package shlottery.gov.cn.lotterycenter.protool;

import android.content.Context;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.VcodeBean;
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

public class AskProtocol extends BaseProtocol {
    public AskProtocol(Context context) {
        super(context);
    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.addQuestion;
    }

    @Override
    protected Object parseData(String json) {
        Gson gson=new Gson();
        VcodeBean bean=gson.fromJson(json,VcodeBean.class);
        return bean;
    }

    @Override
    protected boolean isNeedLogin() {
        return true;
    }
}
