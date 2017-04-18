package shlottery.gov.cn.lotterycenter.bean;

import java.util.HashMap;
import java.util.List;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/24 12:10
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class OrderBean {
    private String orderType;
    private long count;//注数
    private String issueNo;//开奖期号
    private String multiple;//倍数
    private String stakeAdd;//是否追加
    private String totalMoney;//总金额
    private String stakeCode;//投注类容
    private HashMap<String, List<BaseBean>> data;

    public HashMap<String, List<LottoSelectData>> getSelectdata() {
        return Selectdata;
    }

    private HashMap<String, List<LottoSelectData>> Selectdata;

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


    public HashMap<String, List<BaseBean>> getData() {
        return data;
    }

    public void setData(String key, List<BaseBean> list) {
        if (data == null) {
            data = new HashMap<>();
        }
        data.put(key, list);
    }

    public void setSelectdata(String key, List<LottoSelectData> list) {
        if (Selectdata == null) {
            Selectdata = new HashMap<>();
        }
        Selectdata.put(key, list);
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

}
