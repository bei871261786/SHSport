package shlottery.gov.cn.lotterycenter.bean;

import java.util.List;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/21 15:42
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class JifenBean {
    /**
     * ret : 100
     * data : {"integral":2,"count":1,"pageNo":1,"pageCount":1,"list":[{"date":"2016-11-18 14:17:48","integral":2,"type":"签到"}]}
     */

    private int ret;
    /**
     * integral : 2
     * count : 1
     * pageNo : 1
     * pageCount : 1
     * list : [{"date":"2016-11-18 14:17:48","integral":2,"type":"签到"}]
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
        private int integral;
        private int count;
        private int pageNo;
        private int pageCount;
        /**
         * date : 2016-11-18 14:17:48
         * integral : 2
         * type : 签到
         */

        private List<ListBean> list;

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

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
            private String date;
            private int integral;
            private String type;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
