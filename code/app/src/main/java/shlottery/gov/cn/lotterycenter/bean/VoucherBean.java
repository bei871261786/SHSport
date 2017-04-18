package shlottery.gov.cn.lotterycenter.bean;

import java.util.List;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/24 14:22
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class VoucherBean {

    /**
     * ret : 100
     * data : {"count":1,"pageNo":1,"pageCount":1,"list":[{"voucherCode":"31001100000395","voucherName":"2元代金券","voucherMoney":2,"validDate":"2016-12-31","status":1}]}
     */

    private int ret;
    /**
     * count : 1
     * pageNo : 1
     * pageCount : 1
     * list : [{"voucherCode":"31001100000395","voucherName":"2元代金券","voucherMoney":2,"validDate":"2016-12-31","status":1}]
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
        private String detailUrl;

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        /**
         * voucherCode : 31001100000395
         * voucherName : 2元代金券
         * voucherMoney : 2
         * validDate : 2016-12-31
         * status : 1
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
            private String voucherCode;
            private String voucherName;
            private int voucherMoney;
            private String validDate;
            private int status;

            public String getVoucherCode() {
                return voucherCode;
            }

            public void setVoucherCode(String voucherCode) {
                this.voucherCode = voucherCode;
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

            public String getValidDate() {
                return validDate;
            }

            public void setValidDate(String validDate) {
                this.validDate = validDate;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
