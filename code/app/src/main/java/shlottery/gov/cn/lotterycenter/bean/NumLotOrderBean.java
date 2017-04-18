package shlottery.gov.cn.lotterycenter.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-04-0004 23:11
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class NumLotOrderBean implements Serializable {
    private String lotId;//彩种
    private int count;//注数

    public int getChuanshu() {
        return chuanshu;
    }

    public void setChuanshu(int chuanshu) {
        this.chuanshu = chuanshu;
    }

    private int chuanshu;//串数 任九需要
    private boolean isdan;//是否为胆
    private int mType;//小彩种  排列3组三 或者组6
    private String issueNo;//开奖期号
    private String multiple;//倍数
    private String stakeAdd;//是否追加
    private String totalMoney;//总金额
    private String stakeCode;//投注类容
    private String  guoguanType;//投注是区分胆拖dt,复式f,单式d 等

    private OrderBean orderBean;//大乐透的bean

    public SfcDingdanBean getSfcDingdanBean() {
        return sfcDingdanBean;
    }

    public void setSfcDingdanBean(SfcDingdanBean sfcDingdanBean) {
        this.sfcDingdanBean = sfcDingdanBean;
    }

    private SfcDingdanBean sfcDingdanBean;//胜负彩的订单bean


    public OrderBean getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(OrderBean orderBean) {
        this.orderBean = orderBean;
    }

    public String getGuoguanType() {
        return guoguanType;
    }

    public void setGuoguanType(String guoguanType) {
        this.guoguanType = guoguanType;
    }

    public boolean isdan() {
        return isdan;
    }

    public void setIsdan(boolean isdan) {
        this.isdan = isdan;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }


    public int getStoptime() {
        return stoptime;
    }

    public void setStoptime(int stoptime) {
        this.stoptime = stoptime;
    }

    private int stoptime;//截止时间

    private List<Map<Integer, Boolean>> lotteryData;

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(String issueNo) {
        this.issueNo = issueNo;
    }

    public String getMultiple() {
        return multiple;
    }

    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    public String getStakeAdd() {
        return stakeAdd;
    }

    public void setStakeAdd(String stakeAdd) {
        this.stakeAdd = stakeAdd;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getStakeCode() {
        return stakeCode;
    }

    public void setStakeCode(String stakeCode) {
        this.stakeCode = stakeCode;
    }

    public List<Map<Integer, Boolean>> getLotteryData() {
        return lotteryData;
    }

    public void setLotteryData(List<Map<Integer, Boolean>> lotteryData) {
        this.lotteryData = lotteryData;
    }
}
