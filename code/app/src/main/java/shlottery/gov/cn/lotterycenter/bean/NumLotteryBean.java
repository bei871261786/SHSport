package shlottery.gov.cn.lotterycenter.bean;

import java.io.Serializable;
import java.util.List;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-04-0004 09:59
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class NumLotteryBean implements Serializable {


    /**
     * ret : 100
     * data : {"lotId":"sh115","lotName":"上海11选5","omit":"0,1,4,1,0,8,0,0,2,1,0#6,28,10,7,15,12,30,0,2,23,1#5,11,33,4,0,17,3,35,6,1,7#0,6,4,15,3,13,10,31,28,9,8","issueList":[{"issueNo":"2016111343","startTime":1479022800,"stopTime":1479023995,"status":2},{"issueNo":"2016111344","startTime":1479023400,"stopTime":1479024595,"status":2},{"issueNo":"2016111386","startTime":1479048600,"stopTime":1479049795,"status":2},{"issueNo":"2016111387","startTime":1479049200,"stopTime":1479050395,"status":2}]}
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

    public static class DataBean implements Serializable {
        /**
         * lotId : sh115
         * lotName : 上海11选5
         * omit : 0,1,4,1,0,8,0,0,2,1,0#6,28,10,7,15,12,30,0,2,23,1#5,11,33,4,0,17,3,35,6,1,7#0,6,4,15,3,13,10,31,28,9,8
         * issueList : [{"issueNo":"2016111343","startTime":1479022800,"stopTime":1479023995,"status":2},{"issueNo":"2016111344","startTime":1479023400,"stopTime":1479024595,"status":2},{"issueNo":"2016111386","startTime":1479048600,"stopTime":1479049795,"status":2},{"issueNo":"2016111387","startTime":1479049200,"stopTime":1479050395,"status":2}]
         */

        private String lotId;
        private String lotName;
        private String omit;
        private List<IssueListBean> issueList;
        private String poolMoney;

        public String getPoolMoney() {
            return poolMoney;
        }

        public void setPoolMoney(String poolMoney) {
            this.poolMoney = poolMoney;
        }

        public String getLotId() {
            return lotId;
        }

        public void setLotId(String lotId) {
            this.lotId = lotId;
        }

        public String getLotName() {
            return lotName;
        }

        public void setLotName(String lotName) {
            this.lotName = lotName;
        }

        public String getOmit() {
            return omit;
        }

        public void setOmit(String omit) {
            this.omit = omit;
        }

        public List<IssueListBean> getIssueList() {
            return issueList;
        }

        public void setIssueList(List<IssueListBean> issueList) {
            this.issueList = issueList;
        }

        public static class IssueListBean implements Serializable {
            /**
             * issueNo : 2016111343
             * startTime : 1479022800
             * stopTime : 1479023995
             * status : 2
             */

            private String issueNo;
            private int startTime;
            private int stopTime;
            private int status;

            public String getIssueNo() {
                return issueNo;
            }

            public void setIssueNo(String issueNo) {
                this.issueNo = issueNo;
            }

            public int getStartTime() {
                return startTime;
            }

            public void setStartTime(int startTime) {
                this.startTime = startTime;
            }

            public int getStopTime() {
                return stopTime;
            }

            public void setStopTime(int stopTime) {
                this.stopTime = stopTime;
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
