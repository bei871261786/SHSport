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
 * 创建日期：2016/11/23 15:24
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class SaveStakeProtocol extends BaseProtocol<VcodeBean> {
    public SaveStakeProtocol(Context context) {
        super(context);
    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.addStake;
    }

    @Override
    protected VcodeBean parseData(String json) {
        Gson gson = new Gson();
        VcodeBean vcodeBean = gson.fromJson(json, VcodeBean.class);
        return vcodeBean;
    }

    @Override
    protected boolean isNeedLogin() {
        return true;
    }
}
