package shlottery.gov.cn.lotterycenter.protool;

import android.content.Context;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.MyQuestionsBean;
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

public class MyQuestionsProtocol extends BaseProtocol {
    public MyQuestionsProtocol(Context context) {
        super(context);
    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.getMyQuestions;
    }

    @Override
    protected Object parseData(String json) {
        Gson gson=new Gson();
        MyQuestionsBean bean=gson.fromJson(json,MyQuestionsBean.class);
        return bean;
    }

    @Override
    protected boolean isNeedLogin() {
        return true;
    }
}
