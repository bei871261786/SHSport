package shlottery.gov.cn.lotterycenter.protool;

import android.content.Context;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.VoucherBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/23 11:11
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class VoucherPaotocal extends BaseProtocol {
    public VoucherPaotocal(Context context) {
        super(context);
    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.getVoucherList;
    }

    @Override
    protected Object parseData(String json) {
        Gson gson=new Gson();
        VoucherBean bean=gson.fromJson(json,VoucherBean.class);
        LogUtils.i("VoucherPaotocal: "+bean);
        return bean;
    }

    @Override
    protected boolean isNeedLogin() {
        return true;
    }
}
