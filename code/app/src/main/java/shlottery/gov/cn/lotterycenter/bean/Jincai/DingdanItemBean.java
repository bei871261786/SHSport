package shlottery.gov.cn.lotterycenter.bean.Jincai;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by yongchao.bei on 2016/7/29.
 */
public class DingdanItemBean implements Serializable {
    private ArrayList<String> spsList;
    private boolean isDan;
    private String itemInfo;
    private String lotId;
    private int matchNo;
    private String optionValue;

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public int getMatchNo() {
        return matchNo;
    }

    public void setMatchNo(int matchNo) {
        this.matchNo = matchNo;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private int position;

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public ArrayList<String> getSpsList() {
        return spsList;
    }

    public void setSpsList(ArrayList<String> spsList) {
        this.spsList = spsList;
    }

    public boolean isDan() {
        return isDan;
    }

    public void setDan(boolean dan) {
        isDan = dan;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String itemInfo) {
        this.itemInfo = itemInfo;
    }

    @Override
    public String toString() {
        return "itemInfo:" + itemInfo + "spsList:" + spsList + "isDan:" + isDan;
    }
}
