package shlottery.gov.cn.lotterycenter.bean;

import java.util.List;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/30 10:46
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class IssueBonusNumberDetailBean {

    /**
     * ret : 100
     * data : {"lotId":"dlt","lotName":"大乐透","issueNo":"2016140","bonusDate":"2016-11-28","bonusCode":"12,16,23,29,34#04,11","bonusCloseDate":"2017-01-27","salesMoney":188081802,"poolMoney":3845393921,"bonusList":[{"bonusLevel":1,"bonusName":"一等奖","bonusMoney":7220551,"bonusAmount":9},{"bonusLevel":2,"bonusName":"二等奖","bonusMoney":79106,"bonusAmount":121},{"bonusLevel":3,"bonusName":"三等奖","bonusMoney":4185,"bonusAmount":855},{"bonusLevel":4,"bonusName":"四等奖","bonusMoney":200,"bonusAmount":25605},{"bonusLevel":5,"bonusName":"五等奖","bonusMoney":10,"bonusAmount":429003},{"bonusLevel":6,"bonusName":"六等奖","bonusMoney":5,"bonusAmount":4139371},{"bonusLevel":11,"bonusName":"一等奖追加","bonusMoney":0,"bonusAmount":0},{"bonusLevel":12,"bonusName":"二等奖追加","bonusMoney":47463,"bonusAmount":28},{"bonusLevel":13,"bonusName":"三等奖追加","bonusMoney":2511,"bonusAmount":263},{"bonusLevel":14,"bonusName":"四等奖追加","bonusMoney":100,"bonusAmount":7944},{"bonusLevel":15,"bonusName":"五等奖追加","bonusMoney":5,"bonusAmount":139461}]}
     */

    private int ret;
    /**
     * lotId : dlt
     * lotName : 大乐透
     * issueNo : 2016140
     * bonusDate : 2016-11-28
     * bonusCode : 12,16,23,29,34#04,11
     * bonusCloseDate : 2017-01-27
     * salesMoney : 188081802
     * poolMoney : 3845393921
     * bonusList : [{"bonusLevel":1,"bonusName":"一等奖","bonusMoney":7220551,"bonusAmount":9},{"bonusLevel":2,"bonusName":"二等奖","bonusMoney":79106,"bonusAmount":121},{"bonusLevel":3,"bonusName":"三等奖","bonusMoney":4185,"bonusAmount":855},{"bonusLevel":4,"bonusName":"四等奖","bonusMoney":200,"bonusAmount":25605},{"bonusLevel":5,"bonusName":"五等奖","bonusMoney":10,"bonusAmount":429003},{"bonusLevel":6,"bonusName":"六等奖","bonusMoney":5,"bonusAmount":4139371},{"bonusLevel":11,"bonusName":"一等奖追加","bonusMoney":0,"bonusAmount":0},{"bonusLevel":12,"bonusName":"二等奖追加","bonusMoney":47463,"bonusAmount":28},{"bonusLevel":13,"bonusName":"三等奖追加","bonusMoney":2511,"bonusAmount":263},{"bonusLevel":14,"bonusName":"四等奖追加","bonusMoney":100,"bonusAmount":7944},{"bonusLevel":15,"bonusName":"五等奖追加","bonusMoney":5,"bonusAmount":139461}]
     */

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
        private String lotId;
        private String lotName;
        private String issueNo;
        private String bonusDate;
        private String bonusCode;
        private String bonusCloseDate;
        private int salesMoney;
        private long poolMoney;
        private int plusAward = -1;

        public int getPlusAward() {
            return plusAward;
        }

        public void setPlusAward(int plusAward) {
            this.plusAward = plusAward;
        }

        /**
         * bonusLevel : 1
         * bonusName : 一等奖
         * bonusMoney : 7220551
         * bonusAmount : 9
         */


        private List<BonusListBean> bonusList;

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

        public String getBonusDate() {
            return bonusDate;
        }

        public void setBonusDate(String bonusDate) {
            this.bonusDate = bonusDate;
        }

        public String getBonusCode() {
            return bonusCode;
        }

        public void setBonusCode(String bonusCode) {
            this.bonusCode = bonusCode;
        }

        public String getBonusCloseDate() {
            return bonusCloseDate;
        }

        public void setBonusCloseDate(String bonusCloseDate) {
            this.bonusCloseDate = bonusCloseDate;
        }

        public int getSalesMoney() {
            return salesMoney;
        }

        public void setSalesMoney(int salesMoney) {
            this.salesMoney = salesMoney;
        }

        public long getPoolMoney() {
            return poolMoney;
        }

        public void setPoolMoney(long poolMoney) {
            this.poolMoney = poolMoney;
        }

        public List<BonusListBean> getBonusList() {
            return bonusList;
        }

        public void setBonusList(List<BonusListBean> bonusList) {
            this.bonusList = bonusList;
        }

        public static class BonusListBean {
            private int bonusLevel;
            private String bonusName;
            private int addbonusMoney;
            private int bonusMoney;
            private int bonusAmount;
            private int addbonusAmount;
            private String lotid;

            public String getLotid() {
                return lotid;
            }

            public void setLotid(String lotid) {
                this.lotid = lotid;
            }

            public int getAddbonusMoney() {
                return addbonusMoney;
            }

            public void setAddbonusMoney(int addbonusMoney) {
                this.addbonusMoney = addbonusMoney;
            }

            public int getAddbonusAmount() {
                return addbonusAmount;
            }

            public void setAddbonusAmount(int addbonusAmount) {
                this.addbonusAmount = addbonusAmount;
            }

            public int getBonusLevel() {
                return bonusLevel;
            }

            public void setBonusLevel(int bonusLevel) {
                this.bonusLevel = bonusLevel;
            }

            public String getBonusName() {
                return bonusName;
            }

            public void setBonusName(String bonusName) {
                this.bonusName = bonusName;
            }

            public int getBonusMoney() {
                return bonusMoney;
            }

            public void setBonusMoney(int bonusMoney) {
                this.bonusMoney = bonusMoney;
            }

            public int getBonusAmount() {
                return bonusAmount;
            }

            public void setBonusAmount(int bonusAmount) {
                this.bonusAmount = bonusAmount;
            }
        }
    }
}
