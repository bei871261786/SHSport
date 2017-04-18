package shlottery.gov.cn.lotterycenter.protool;

import android.content.Context;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.IssueBonusSoccerDetailBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/16 15:50
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class IssueBonusSoccerDetailProtocol extends BaseProtocol<IssueBonusSoccerDetailBean> {

    public IssueBonusSoccerDetailProtocol(Context context) {
        super(context);
    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.getBonusDetail;
    }

    @Override
    protected IssueBonusSoccerDetailBean parseData(String json) {
        Gson mGson = new Gson();
        if (json != null) {
            IssueBonusSoccerDetailBean issueBonusBean = mGson.fromJson(json, IssueBonusSoccerDetailBean.class);
            return issueBonusBean;
        }
        return null;
    }

    @Override
    protected boolean isNeedLogin() {
        return false;
    }

}
