package shlottery.gov.cn.lotterycenter.bean.Jincai;

import java.io.Serializable;
import java.util.List;

/**
 * Created by booob on 2016-07-06-0006.
 * 2选1的javaBean
 */
public class TwoXuanYiBean {

    /**
     * ret : 100
     * data : {"matchs":[{"issueNo":"20160706","matchDay":"20160706","matchNo":3101,"weekNo":"周三101","leagueId":533,"leagueName":"J2联赛","leagueColor":"#2C5F91","hostName":"山形山神","hostRank":"11","visitName":"熊本深红","visitRank":"12","matchTime":1467799200,"stopTime":1467798600,"status":1,"hand":-1,"sp1":"1.88","sp2":"1.70"},{"issueNo":"20160706","matchDay":"20160706","matchNo":3102,"weekNo":"周三102","leagueId":419,"leagueName":"友谊赛","leagueColor":"#FDAA57","hostName":"奈梅亨","hostRank":"","visitName":"布星","visitRank":"","matchTime":1467824400,"stopTime":1467820200,"status":1,"hand":1,"sp1":"1.61","sp2":"2.00"},{"issueNo":"20160706","matchDay":"20160706","matchNo":3049,"weekNo":"周三049","leagueId":9,"leagueName":"欧洲杯","leagueColor":"#448782","hostName":"葡萄牙","hostRank":"8","visitName":"威尔士","visitRank":"26","matchTime":1467831600,"stopTime":1467820200,"status":1,"hand":-1,"sp1":"1.95","sp2":"1.62"},{"issueNo":"20160706","matchDay":"20160706","matchNo":3103,"weekNo":"周三103","leagueId":434,"leagueName":"巴西杯","leagueColor":"#C29C9C","hostName":"费古埃伦","hostRank":"","visitName":"桑帕约科","visitRank":"","matchTime":1467844200,"stopTime":1467820200,"status":1,"hand":-1,"sp1":"1.35","sp2":"2.63"},{"issueNo":"20160706","matchDay":"20160706","matchNo":3104,"weekNo":"周三104","leagueId":38,"leagueName":"美大联盟","leagueColor":"#8A7571","hostName":"新英格兰","hostRank":"16","visitName":"纽约城","visitRank":"4","matchTime":1467847800,"stopTime":1467820200,"status":1,"hand":-1,"sp1":"1.65","sp2":"1.94"},{"issueNo":"20160706","matchDay":"20160706","matchNo":3105,"weekNo":"周三105","leagueId":336,"leagueName":"解放者杯","leagueColor":"#00DBC9","hostName":"圣保罗","hostRank":"","visitName":"麦国民","visitRank":"","matchTime":1467852300,"stopTime":1467820200,"status":1,"hand":-1,"sp1":"1.85","sp2":"1.72"},{"issueNo":"20160706","matchDay":"20160706","matchNo":3106,"weekNo":"周三106","leagueId":434,"leagueName":"巴西杯","leagueColor":"#C29C9C","hostName":"维多利亚","hostRank":"","visitName":"克鲁塞罗","visitRank":"","matchTime":1467852300,"stopTime":1467820200,"status":1,"hand":-1,"sp1":"2.26","sp2":"1.47"},{"issueNo":"20160707","matchDay":"20160707","matchNo":4050,"weekNo":"周四050","leagueId":9,"leagueName":"欧洲杯","leagueColor":"#448782","hostName":"法国","hostRank":"17","visitName":"德国","visitRank":"4","matchTime":1467918000,"stopTime":1467906600,"status":1,"hand":-1,"sp1":"2.45","sp2":"1.38"},{"issueNo":"20160707","matchDay":"20160707","matchNo":4108,"weekNo":"周四108","leagueId":434,"leagueName":"巴西杯","leagueColor":"#C29C9C","hostName":"米美洲","hostRank":"","visitName":"福塔雷萨","visitRank":"","matchTime":1467936000,"stopTime":1467906600,"status":1,"hand":-1,"sp1":"1.60","sp2":"2.02"},{"issueNo":"20160707","matchDay":"20160707","matchNo":4109,"weekNo":"周四109","leagueId":336,"leagueName":"解放者杯","leagueColor":"#00DBC9","hostName":"德尔瓦耶","hostRank":"","visitName":"博卡","visitRank":"","matchTime":1467938700,"stopTime":1467906600,"status":1,"hand":-1,"sp1":"2.05","sp2":"1.58"}],"leagues":[{"leagueId":533,"leagueName":"J2联赛","leagueColor":"#2C5F91","isHot":0},{"leagueId":419,"leagueName":"友谊赛","leagueColor":"#FDAA57","isHot":0},{"leagueId":9,"leagueName":"欧洲杯","leagueColor":"#448782","isHot":1},{"leagueId":434,"leagueName":"巴西杯","leagueColor":"#C29C9C","isHot":0},{"leagueId":38,"leagueName":"美大联盟","leagueColor":"#8A7571","isHot":1},{"leagueId":336,"leagueName":"解放者杯","leagueColor":"#00DBC9","isHot":1}],"days":["20160706","20160707"]}
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
        /**
         * issueNo : 20160706
         * matchDay : 20160706
         * matchNo : 3101
         * weekNo : 周三101
         * leagueId : 533
         * leagueName : J2联赛
         * leagueColor : #2C5F91
         * hostName : 山形山神
         * hostRank : 11
         * visitName : 熊本深红
         * visitRank : 12
         * matchTime : 1467799200
         * stopTime : 1467798600
         * status : 1
         * hand : -1
         * sp1 : 1.88
         * sp2 : 1.70
         */

        private List<MatchsBean> matchs;
        /**
         * leagueId : 533
         * leagueName : J2联赛
         * leagueColor : #2C5F91
         * isHot : 0
         */

        private List<LeaguesBean> leagues;
        private List<String> days;

        public List<MatchsBean> getMatchs() {
            return matchs;
        }

        public void setMatchs(List<MatchsBean> matchs) {
            this.matchs = matchs;
        }

        public List<LeaguesBean> getLeagues() {
            return leagues;
        }

        public void setLeagues(List<LeaguesBean> leagues) {
            this.leagues = leagues;
        }

        public List<String> getDays() {
            return days;
        }

        public void setDays(List<String> days) {
            this.days = days;
        }

        public static class MatchsBean implements Serializable {
            private String issueNo;
            private String matchDay;
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
            private int stopTime;
            private int status;
            private int hand;
            private String sp1;
            private String sp2;

            private boolean isWinChecked;
            private boolean isLostChecked;


            public String getIssueNo() {
                return issueNo;
            }

            public void setIssueNo(String issueNo) {
                this.issueNo = issueNo;
            }

            public String getMatchDay() {
                return matchDay;
            }

            public void setMatchDay(String matchDay) {
                this.matchDay = matchDay;
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

            public int getHand() {
                return hand;
            }

            public void setHand(int hand) {
                this.hand = hand;
            }

            public String getSp1() {
                return sp1;
            }

            public void setSp1(String sp1) {
                this.sp1 = sp1;
            }

            public String getSp2() {
                return sp2;
            }

            public void setSp2(String sp2) {
                this.sp2 = sp2;
            }

            public boolean getWinChecked() {
                return isWinChecked;
            }

            public void setWinChecked(boolean winChecked) {
                isWinChecked = winChecked;
            }

            public boolean getLostChecked() {
                return isLostChecked;
            }

            public void setLostChecked(boolean lostChecked) {
                isLostChecked = lostChecked;
            }
        }

        public static class LeaguesBean {
            private int leagueId;
            private String leagueName;
            private String leagueColor;
            private int isHot;

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

            public int getIsHot() {
                return isHot;
            }

            public void setIsHot(int isHot) {
                this.isHot = isHot;
            }
        }
    }
}
