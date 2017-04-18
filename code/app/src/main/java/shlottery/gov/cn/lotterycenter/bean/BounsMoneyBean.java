package shlottery.gov.cn.lotterycenter.bean;

import java.io.Serializable;
import java.util.List;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-01-0001 15:07
 * 描    述：计算奖金的bean
 * 修订历史：
 * ================================================
 */

public class BounsMoneyBean implements Serializable {

    /**
     * ret : 100
     * data : {"issueNo":"2016323","bonusMoney":100000,"bonusList":[{"bonusLevel":1,"bonusName":"一等奖","bonusAmount":1,"bonusMoney":"100,000"}]}
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
         * issueNo : 2016323
         * bonusMoney : 100000
         * bonusList : [{"bonusLevel":1,"bonusName":"一等奖","bonusAmount":1,"bonusMoney":"100,000"}]
         */

        private String issueNo;
        private int bonusMoney;
        private List<BonusListBean> bonusList;

        public String getIssueNo() {
            return issueNo;
        }

        public void setIssueNo(String issueNo) {
            this.issueNo = issueNo;
        }

        public int getBonusMoney() {
            return bonusMoney;
        }

        public void setBonusMoney(int bonusMoney) {
            this.bonusMoney = bonusMoney;
        }

        public List<BonusListBean> getBonusList() {
            return bonusList;
        }

        public void setBonusList(List<BonusListBean> bonusList) {
            this.bonusList = bonusList;
        }

        public static class BonusListBean {
            /**
             * bonusLevel : 1
             * bonusName : 一等奖
             * bonusAmount : 1
             * bonusMoney : 100,000
             */

            private int bonusLevel;
            private String bonusName;
            private int bonusAmount;
            private String bonusMoney;

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

            public int getBonusAmount() {
                return bonusAmount;
            }

            public void setBonusAmount(int bonusAmount) {
                this.bonusAmount = bonusAmount;
            }

            public String getBonusMoney() {
                return bonusMoney;
            }

            public void setBonusMoney(String bonusMoney) {
                this.bonusMoney = bonusMoney;
            }
        }
    }
}
