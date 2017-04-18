package shlottery.gov.cn.lotterycenter.bean;

import java.util.List;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/24 16:41
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class CashingListBean {

    /**
     * ret : 100
     * data : {"list":[{"voucherId":1,"voucherName":"2元代金券","voucherMoney":2,"integral":200},{"voucherId":2,"voucherName":"5元代金券","voucherMoney":5,"integral":500},{"voucherId":3,"voucherName":"10元代金券","voucherMoney":10,"integral":1000}]}
     */

    private int ret;
    private DataBean data;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * voucherId : 1
         * voucherName : 2元代金券
         * voucherMoney : 2
         * integral : 200
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int voucherId;
            private String voucherName;
            private int voucherMoney;
            private int integral;

            public int getVoucherId() {
                return voucherId;
            }

            public void setVoucherId(int voucherId) {
                this.voucherId = voucherId;
            }

            public String getVoucherName() {
                return voucherName;
            }

            public void setVoucherName(String voucherName) {
                this.voucherName = voucherName;
            }

            public int getVoucherMoney() {
                return voucherMoney;
            }

            public void setVoucherMoney(int voucherMoney) {
                this.voucherMoney = voucherMoney;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }
        }
    }
}