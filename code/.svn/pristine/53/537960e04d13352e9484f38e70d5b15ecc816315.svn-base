package shlottery.gov.cn.lotterycenter.protool;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.OrderListBean;
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

public class OrderListProtocol extends BaseProtocol {

    public OrderListProtocol(Context context) {
        super(context);
    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.getStakeList;
    }

    @Override
    protected OrderListBean parseData(String json) {
        if (json != null) {
            Gson gson = new Gson();
            OrderListBean bean = gson.fromJson(json, OrderListBean.class);
            if (bean != null && bean.getRet() == 100) {
                ArrayList<OrderListBean.DataBean.ListBean> list = (ArrayList<OrderListBean.DataBean.ListBean>) bean.getData().getList();
                for (int i = 0; i < list.size(); i++) {
                    OrderListBean.DataBean.ListBean listbean = list.get(i);
                    listbean.setLogo(getLogoId(listbean.getLotId()));
                }
                bean.getData().setList(list);
                return bean;
            }
        }
        return null;
    }


    private int getLogoId(String lotId) {
        int logoId = 0;

        if (lotId.equals("jzspf") || lotId.equals("jzxspf") || lotId.equals("jzbf") || lotId.equals("jzjqs") || lotId.equals("jzbqc") || lotId.equals("jzhh") || lotId.equals("jczq")) {
            logoId = R.mipmap.logo_jinzu;
        } else if (lotId.equals("jclq") || lotId.equals("jlsf") || lotId.equals("jlrsf") || lotId.equals("jldx") || lotId.equals("jlfc") || lotId.equals("jlhh")) {
            logoId = R.mipmap.logo_jinlan;
        } else if (lotId.equals("dlt")) {
            logoId = R.mipmap.logo_lotto;
        } else if (lotId.equals("pl3")) {
            logoId = R.mipmap.logo_pl3;
        } else if (lotId.equals("pl5")) {
            logoId = R.mipmap.logo_pl5;
        } else if (lotId.equals("qxc")) {
            logoId = R.mipmap.logo_qixin;
        } else if (lotId.equals("sh115")) {
            logoId = R.mipmap.logo_sh11x5;
        } else if (lotId.equals("sfc") || lotId.equals("sf14") || lotId.equals("sf9") || lotId.equals("bqc") || lotId.equals("jqc")) {
            logoId = R.mipmap.logo_soccer_2;
        }
        return logoId;
    }

    @Override
    protected boolean isNeedLogin() {
        return true;
    }
}
