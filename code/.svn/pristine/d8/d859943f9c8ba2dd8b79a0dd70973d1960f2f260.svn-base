package shlottery.gov.cn.lotterycenter.protool;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.Jincai.JingCaiZuqiuInfo;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/4 14:37
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class JincaiSoccerProtocol extends BaseProtocol<JingCaiZuqiuInfo.DataBean> {

    private String mSubId = "";

    public JincaiSoccerProtocol(Context context) {
        super(context);
    }


    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("lotId", "jczq");
        params.put("subId", mSubId);
        return params;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.getSaleMatch;
    }

    @Override
    protected JingCaiZuqiuInfo.DataBean parseData(String s) {
        LogUtils.i("JincaiProtocao json:" + s);
        Gson mGson = new Gson();
        Log.i(s, "parseFromJson: ");
        JingCaiZuqiuInfo.DataBean dataBean;
        JingCaiZuqiuInfo mJingCaiZuQiuInfo = mGson.fromJson(s, JingCaiZuqiuInfo.class);
        dataBean = mJingCaiZuQiuInfo.getData();
        // Log.i("mDatas的长度" + list.size() + "", "parseFromJson: list长度");
        return dataBean;
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
