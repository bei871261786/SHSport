package shlottery.gov.cn.lotterycenter.protool;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.AdvertBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/25 15:41
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class AdvertProtocol extends BaseProtocol {
    public AdvertProtocol() {

    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.getBootPic;
    }

    @Override
    protected Object parseData(String json) {
        LogUtils.i("advert json:"+json);
        Gson gson=new Gson();
        AdvertBean bean=gson.fromJson(json,AdvertBean.class);
        return bean;
    }

    @Override
    protected boolean isNeedLogin() {
        return false;
    }
}
