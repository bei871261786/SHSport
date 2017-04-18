package shlottery.gov.cn.lotterycenter.bean;

import java.io.Serializable;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/18 18:01
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class  BaseBean implements Serializable {
    private boolean isDan;
    private String msg;
    private boolean isSelected;
    private boolean isRed = true;

    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean isRed) {
        this.isRed = isRed;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getMinCount() {
        return minCount;
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }

    private int minCount;

    public boolean isDan() {
        return isDan;
    }

    public void setDan(boolean dan) {
        isDan = dan;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg + ":" + isDan();
    }
}
