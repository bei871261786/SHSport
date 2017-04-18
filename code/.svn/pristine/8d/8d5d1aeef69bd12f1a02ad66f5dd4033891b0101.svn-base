package shlottery.gov.cn.lotterycenter.protool;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.LottersRoomBean;
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

public class LotterysRoomProtocol extends BaseProtocol {

    public LotterysRoomProtocol() {
        super();
    }
    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.getLotList;
    }

    @Override
    protected Object parseData(String json) {
        Gson gson=new Gson();
        LottersRoomBean bean=gson.fromJson(json,LottersRoomBean.class);
        return bean;
    }

    @Override
    protected boolean isNeedLogin() {
        return false;
    }
}
