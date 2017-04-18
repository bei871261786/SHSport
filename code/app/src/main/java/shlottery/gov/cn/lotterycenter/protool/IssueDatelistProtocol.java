package shlottery.gov.cn.lotterycenter.protool;

import android.content.Context;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.IssueListBaseBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/12/2 18:20
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class IssueDatelistProtocol extends BaseProtocol {

    public IssueDatelistProtocol(Context context) {
        super(context);
    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.getBonusIssue;
    }

    @Override
    protected IssueListBaseBean parseData(String json) {
        Gson gson = new Gson();
        IssueListBaseBean signinBean = gson.fromJson(json, IssueListBaseBean.class);
        LogUtils.i("JifenProtacol: " + signinBean);
        return signinBean;
    }

    @Override
    protected boolean isNeedLogin() {
        return false ;
    }
}
