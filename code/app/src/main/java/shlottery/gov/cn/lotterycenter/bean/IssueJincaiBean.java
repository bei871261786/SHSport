package shlottery.gov.cn.lotterycenter.bean;

import java.util.List;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/27 16:34
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class IssueJincaiBean {
    /**
     * issueNo : 20160717
     * matchNo : 7001
     * weekNo : 周日001
     * leagueId : 138
     * leagueName : J1联赛
     * leagueColor : #3473B2
     * hostName : 甲府风林
     * hostRank : 17
     * visitName : 鹿岛鹿角
     * visitRank : 2
     * matchTime : 1468746000
     * hostGoal : 3
     * visitGoal : 3
     * hostHalfGoal : 1
     * visitHalfGoal : 2
     * bonusList : [{"lotId":"jzxspf","hand":1,"bonusResult":"胜"},{"lotId":"jzspf","hand":0,"bonusResult":"平"},{"lotId":"jzjqs","bonusResult":"6"},{"lotId":"jzbqc","bonusResult":"负-平"},{"lotId":"jzbf","bonusResult":"3:3"}]
     */

    private String issueNo;
    private int matchNo;
    private String weekNo;
    private int leagueId;
    private String leagueName;
    private String leagueColor;
    private String hostName;
    private String hostRank;
    private String visitName;
    private String visitRank;
    private int matchTime;
    private int hostGoal;
    private int visitGoal;
    private int hostHalfGoal;
    private int visitHalfGoal;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * lotId : jzxspf
     * hand : 1
     * bonusResult : 胜
     */

    private List<BonusListBean> bonusList;

    public String getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(String issueNo) {
        this.issueNo = issueNo;
    }

    public int getMatchNo() {
        return matchNo;
    }

    public void setMatchNo(int matchNo) {
        this.matchNo = matchNo;
    }

    public String getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(String weekNo) {
        this.weekNo = weekNo;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getLeagueColor() {
        return leagueColor;
    }

    public void setLeagueColor(String leagueColor) {
        this.leagueColor = leagueColor;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostRank() {
        return hostRank;
    }

    public void setHostRank(String hostRank) {
        this.hostRank = hostRank;
    }

    public String getVisitName() {
        return visitName;
    }

    public void setVisitName(String visitName) {
        this.visitName = visitName;
    }

    public String getVisitRank() {
        return visitRank;
    }

    public void setVisitRank(String visitRank) {
        this.visitRank = visitRank;
    }

    public int getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(int matchTime) {
        this.matchTime = matchTime;
    }

    public int getHostGoal() {
        return hostGoal;
    }

    public void setHostGoal(int hostGoal) {
        this.hostGoal = hostGoal;
    }

    public int getVisitGoal() {
        return visitGoal;
    }

    public void setVisitGoal(int visitGoal) {
        this.visitGoal = visitGoal;
    }

    public int getHostHalfGoal() {
        return hostHalfGoal;
    }

    public void setHostHalfGoal(int hostHalfGoal) {
        this.hostHalfGoal = hostHalfGoal;
    }

    public int getVisitHalfGoal() {
        return visitHalfGoal;
    }

    public void setVisitHalfGoal(int visitHalfGoal) {
        this.visitHalfGoal = visitHalfGoal;
    }

    public List<BonusListBean> getBonusList() {
        return bonusList;
    }


    public void setBonusList(List<BonusListBean> bonusList) {
        this.bonusList = bonusList;
    }

    public static class BonusListBean {
        private String lotId;
        private int hand;
        private String bonusResult;

        public String getLotId() {
            return lotId;
        }

        public void setLotId(String lotId) {
            this.lotId = lotId;
        }

        public int getHand() {
            return hand;
        }

        public void setHand(int hand) {
            this.hand = hand;
        }

        public String getBonusResult() {
            return bonusResult;
        }

        public void setBonusResult(String bonusResult) {
            this.bonusResult = bonusResult;
        }
    }
}
