package shlottery.gov.cn.lotterycenter.callback;

import shlottery.gov.cn.lotterycenter.bean.NumLotOrderBean;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-02-0002 11:11
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class CalculatePl3EventBus {

    private NumLotOrderBean numLotOrderBean;

    private boolean isenable;//查询按钮是否可点

    public boolean isenable() {
        return isenable;
    }

    public void setIsenable(boolean isenable) {
        this.isenable = isenable;
    }

    public NumLotOrderBean getNumLotOrderBean() {
        return numLotOrderBean;
    }

    public void setNumLotOrderBean(NumLotOrderBean numLotOrderBean) {
        this.numLotOrderBean = numLotOrderBean;
    }
}
