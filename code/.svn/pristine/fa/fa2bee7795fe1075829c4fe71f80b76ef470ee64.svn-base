package shlottery.gov.cn.lotterycenter.protool;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.NumLotteryBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/14 11:35
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class NumberProtocol extends BaseProtocol<NumLotteryBean> {


    private String mLotid = "";

    public NumberProtocol(String mLotid, Context context) {
        super(context);
        this.mLotid = mLotid;
    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("lotId", mLotid);
        return params;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.getSaleIssue;
    }

    @Override
    protected NumLotteryBean parseData(String s) {
        LogUtils.i("JincaiProtocao json:" + s);
        try {
            Gson mGson = new Gson();
            Log.i(s, "parseFromJson: ");
            NumLotteryBean.DataBean dataBean;

            NumLotteryBean bean = mGson.fromJson(s, NumLotteryBean.class);
            // Log.i("mDatas的长度" + list.size() + "", "parseFromJson: list长度");
            return bean;
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }

    @Override
    protected boolean isNeedLogin() {
        return false;
    }
}