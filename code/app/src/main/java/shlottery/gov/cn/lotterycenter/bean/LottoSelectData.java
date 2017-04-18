package shlottery.gov.cn.lotterycenter.bean;

import java.io.Serializable;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/17 13:52
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class LottoSelectData extends BaseBean implements Serializable{

    private int location;//用来判断是否在胆码
    private boolean isRedArea;

    public boolean getIsRedArea() {
        return isRedArea;
    }

    public void setIsRedArea(boolean isRedArea) {
        this.isRedArea = isRedArea;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
