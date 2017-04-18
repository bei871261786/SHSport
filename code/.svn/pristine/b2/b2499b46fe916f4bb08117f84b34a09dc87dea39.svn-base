package shlottery.gov.cn.lotterycenter.protool;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.IssueBonusBean;
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

public class IssueBonusProtocol extends BaseProtocol<IssueBonusBean.DataBean> {
    public IssueBonusProtocol(Context context) {
        super(context);
    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.getIssuebonusList;
    }

    @Override
    protected IssueBonusBean.DataBean parseData(String json) {
        Gson mGson = new Gson();
        Log.i(json, "parseFromJson: ");
        IssueBonusBean.DataBean dataBean;
        IssueBonusBean mJingCaiZuQiuInfo = mGson.fromJson(json, IssueBonusBean.class);
        dataBean = mJingCaiZuQiuInfo.getData();
        // Log.i("mDatas的长度" + list.size() + "", "parseFromJson: list长度");
        return dataBean;
    }

    @Override
    protected boolean isNeedLogin() {
        return false;
    }
}
