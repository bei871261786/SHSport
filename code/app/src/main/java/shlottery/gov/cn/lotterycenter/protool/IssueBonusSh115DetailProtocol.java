package shlottery.gov.cn.lotterycenter.protool;

import android.content.Context;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.IssueBonusSh115DetailBean;
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

public class IssueBonusSh115DetailProtocol extends BaseProtocol<IssueBonusSh115DetailBean> {

    public IssueBonusSh115DetailProtocol(Context context) {
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
    protected IssueBonusSh115DetailBean parseData(String json) {
        Gson mGson = new Gson();
        IssueBonusSh115DetailBean issueBonusBean = mGson.fromJson(json, IssueBonusSh115DetailBean.class);

        return issueBonusBean;
    }

    @Override
    protected boolean isNeedLogin() {
        return false;
    }

}
