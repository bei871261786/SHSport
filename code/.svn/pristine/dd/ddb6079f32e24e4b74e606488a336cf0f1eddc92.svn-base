package shlottery.gov.cn.lotterycenter.bean;

import java.util.List;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/12/22 14:54
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class LotteryHelperBean {

    /**
     * ret : 100
     * data : {"list":[{"lotId":"sh115","lotName":"上海11选5","issueNo":"2016122237","stopTime":1482389995},{"lotId":"dlt","lotName":"大乐透","issueNo":"2016151","stopTime":1482580800},{"lotId":"pl3","lotName":"排列三","issueNo":"2016350","stopTime":1482408000},{"lotId":"pl5","lotName":"排列五","issueNo":"2016350","stopTime":1482408000},{"lotId":"qxc","lotName":"七星彩","issueNo":"2016151","stopTime":1482494400},{"lotId":"sfc","lotName":"胜负彩","issueNo":"2016198","stopTime":1482762120},{"lotId":"bqc","lotName":"半全场","issueNo":"2016204","stopTime":1482762120},{"lotId":"jqc","lotName":"进球彩","issueNo":"2016204","stopTime":1482762120},{"lotId":"jczq","lotName":"竞彩足球","issueNo":"20161222","matchCount":25},{"lotId":"jclq","lotName":"竞彩篮球","issueNo":"20161222","matchCount":9}]}
     */

    private int ret;
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * lotId : sh115
             * lotName : 上海11选5
             * issueNo : 2016122237
             * stopTime : 1482389995
             * matchCount : 25
             */

            private String lotId;
            private String lotName;
            private String issueNo;
            private int stopTime;
            private int matchCount;

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

            public String getIssueNo() {
                return issueNo;
            }

            public void setIssueNo(String issueNo) {
                this.issueNo = issueNo;
            }

            public int getStopTime() {
                return stopTime;
            }

            public void setStopTime(int stopTime) {
                this.stopTime = stopTime;
            }

            public int getMatchCount() {
                return matchCount;
            }

            public void setMatchCount(int matchCount) {
                this.matchCount = matchCount;
            }
        }
    }
}
