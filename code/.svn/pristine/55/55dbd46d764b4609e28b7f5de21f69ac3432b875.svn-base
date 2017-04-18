package shlottery.gov.cn.lotterycenter.bean;

import java.io.Serializable;
import java.util.List;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/16 15:45
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class IssueBonusBean implements Serializable {


    /**
     * ret : 100
     * data : {"list":[{"lotId":"sh115","lotName":"上海11选5","issueNo":"2016122238","bonusTime":1482390595,"bonusCode":"06,08,11,07,10"},{"lotId":"dlt","lotName":"大乐透","issueNo":"2016150","bonusTime":1482321600,"bonusCode":"01,10,18,24,29#07,10"},{"lotId":"pl3","lotName":"排列三","issueNo":"2016349","bonusTime":1482321600,"bonusCode":"1,5,4"},{"lotId":"pl5","lotName":"排列五","issueNo":"2016349","bonusTime":1482321600,"bonusCode":"1,5,4,4,4"},{"lotId":"qxc","lotName":"七星彩","issueNo":"2016150","bonusTime":1482235200,"bonusCode":"1,0,8,9,5,1,1"},{"lotId":"sfc","lotName":"胜负彩","issueNo":"2016196","bonusTime":1482067200,"bonusCode":"3,3,0,0,3,0,3,3,0,3,1,3,3,0"},{"lotId":"bqc","lotName":"半全场","issueNo":"2016202","bonusTime":1482067200,"bonusCode":"3,3,0,0,3,3,3,3,1,0,3,3"},{"lotId":"jqc","lotName":"进球彩","issueNo":"2016202","bonusTime":1482067320,"bonusCode":"2,1,3,3,3,3,3,1"},{"lotId":"jczq","lotName":"竞彩足球","issueNo":"20161221","matchCount":29},{"lotId":"jclq","lotName":"竞彩篮球","issueNo":"20161221","matchCount":12}]}
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
             * issueNo : 2016122238
             * bonusTime : 1482390595
             * bonusCode : 06,08,11,07,10
             * matchCount : 29
             */

            private String lotId;
            private String lotName;
            private String issueNo;
            private int bonusTime;
            private String bonusCode;
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

            public int getBonusTime() {
                return bonusTime;
            }

            public void setBonusTime(int bonusTime) {
                this.bonusTime = bonusTime;
            }

            public String getBonusCode() {
                return bonusCode;
            }

            public void setBonusCode(String bonusCode) {
                this.bonusCode = bonusCode;
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
