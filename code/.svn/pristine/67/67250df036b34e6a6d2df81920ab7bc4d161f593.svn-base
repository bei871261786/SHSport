package shlottery.gov.cn.lotterycenter.bean.Jincai;

import java.util.List;

/**
 * Created by yongchao.bei on 2016/8/1.
 */
public class VoucherBean {

    /**
     * ret : 100
     * data : {"count":2,"pageNo":1,"pageCount":1,"list":[{"voucherId":1,"voucherName":"竞彩5元体验券","voucherMoney":5,"allowMoney":0,"exprieDate":"2016-08-06","status":"未使用"},{"voucherId":2,"voucherName":"新用户2元彩金券","voucherMoney":2,"allowMoney":0,"exprieDate":"2016-08-23","status":"未使用"}]}
     */

    private int ret;
    /**
     * count : 2
     * pageNo : 1
     * pageCount : 1
     * list : [{"voucherId":1,"voucherName":"竞彩5元体验券","voucherMoney":5,"allowMoney":0,"exprieDate":"2016-08-06","status":"未使用"},{"voucherId":2,"voucherName":"新用户2元彩金券","voucherMoney":2,"allowMoney":0,"exprieDate":"2016-08-23","status":"未使用"}]
     */

    private DataBean data;

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
        private int count;
        private int pageNo;
        private int pageCount;
        /**
         * voucherId : 1
         * voucherName : 竞彩5元体验券
         * voucherMoney : 5
         * allowMoney : 0
         * exprieDate : 2016-08-06
         * status : 未使用
         */

        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

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
            private int allowMoney;
            private String exprieDate;
            private String status;

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

            public int getAllowMoney() {
                return allowMoney;
            }

            public void setAllowMoney(int allowMoney) {
                this.allowMoney = allowMoney;
            }

            public String getExprieDate() {
                return exprieDate;
            }

            public void setExprieDate(String exprieDate) {
                this.exprieDate = exprieDate;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
