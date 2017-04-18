package shlottery.gov.cn.lotterycenter.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-07-0007 11:30
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class Sf14Bean implements Serializable {

    /**
     * ret : 100
     * data : {"lotId":"sfc","lotName":"胜负彩","issueList":[{"issueNo":"2016172","startTime":1478433600,
     * "stopTime":1478789520,"status":2,"matchList":[{"matchNo":1,"leagueId":130,"leagueName":"世南美预",
     * "leagueColor":"#528CEB","hostName":"哥伦比","hostRank":"A4","visitName":"智利","visitRank":"A5",
     * "matchTime":1478809800},{"matchNo":2,"leagueId":130,"leagueName":"世南美预","leagueColor":"#528CEB",
     * "hostName":"乌拉圭","hostRank":"A2","visitName":"厄瓜多","visitRank":"A3","matchTime":1478818800},{"matchNo":3,
     * "leagueId":130,"leagueName":"世南美预","leagueColor":"#528CEB","hostName":"巴拉圭","hostRank":"A7","visitName":"秘鲁",
     * "visitRank":"A8","matchTime":1478820600},{"matchNo":4,"leagueId":130,"leagueName":"世南美预",
     * "leagueColor":"#528CEB","hostName":"委内瑞","hostRank":"A10","visitName":"玻利维","visitRank":"A9",
     * "matchTime":1478820600},{"matchNo":5,"leagueId":130,"leagueName":"世南美预","leagueColor":"#528CEB",
     * "hostName":"巴西","hostRank":"A1","visitName":"阿根廷","visitRank":"A6","matchTime":1478821500},{"matchNo":6,
     * "leagueId":128,"leagueName":"世欧预","leagueColor":"#660099","hostName":"捷克","hostRank":"C5","visitName":"挪威",
     * "visitRank":"C4","matchTime":1478893500},{"matchNo":7,"leagueId":128,"leagueName":"世欧预",
     * "leagueColor":"#660099","hostName":"北爱","hostRank":"C3","visitName":"阿塞拜","visitRank":"C2",
     * "matchTime":1478893500},{"matchNo":8,"leagueId":128,"leagueName":"世欧预","leagueColor":"#660099",
     * "hostName":"圣马力","hostRank":"C6","visitName":"德国","visitRank":"C1","matchTime":1478893500},{"matchNo":9,
     * "leagueId":128,"leagueName":"世欧预","leagueColor":"#660099","hostName":"丹麦","hostRank":"E4","visitName":"哈萨克",
     * "visitRank":"E5","matchTime":1478893500},{"matchNo":10,"leagueId":128,"leagueName":"世欧预",
     * "leagueColor":"#660099","hostName":"罗马尼","hostRank":"E3","visitName":"波兰","visitRank":"E2",
     * "matchTime":1478893500},{"matchNo":11,"leagueId":128,"leagueName":"世欧预","leagueColor":"#660099",
     * "hostName":"英格兰","hostRank":"F1","visitName":"苏格兰","visitRank":"F4","matchTime":1478893500},{"matchNo":12,
     * "leagueId":128,"leagueName":"世欧预","leagueColor":"#660099","hostName":"马耳他","hostRank":"F6","visitName":"斯洛文",
     * "visitRank":"F3","matchTime":1478893500},{"matchNo":13,"leagueId":128,"leagueName":"世欧预",
     * "leagueColor":"#660099","hostName":"斯洛伐","hostRank":"F5","visitName":"立陶宛","visitRank":"F2",
     * "matchTime":1478893500},{"matchNo":14,"leagueId":128,"leagueName":"世欧预","leagueColor":"#660099",
     * "hostName":"法国","hostRank":"A2","visitName":"瑞典","visitRank":"A1","matchTime":1478893500}]},
     * {"issueNo":"2016173","startTime":1478692800,"stopTime":1478962320,"status":0,"matchList":[{"matchNo":1,
     * "leagueId":128,"leagueName":"世欧预","leagueColor":"#660099","hostName":"奥地利","hostRank":"D4","visitName":"爱尔兰",
     * "visitRank":"D2","matchTime":1478970000},{"matchNo":2,"leagueId":128,"leagueName":"世欧预",
     * "leagueColor":"#660099","hostName":"格鲁吉","hostRank":"D5","visitName":"摩尔多","visitRank":"D6",
     * "matchTime":1478970000},{"matchNo":3,"leagueId":128,"leagueName":"世欧预","leagueColor":"#660099",
     * "hostName":"克罗地","hostRank":"I1","visitName":"冰岛","visitRank":"I2","matchTime":1478970000},{"matchNo":4,
     * "leagueId":128,"leagueName":"世欧预","leagueColor":"#660099","hostName":"威尔士","hostRank":"D3","visitName":"塞尔维",
     * "visitRank":"D1","matchTime":1478979900},{"matchNo":5,"leagueId":128,"leagueName":"世欧预",
     * "leagueColor":"#660099","hostName":"阿尔巴","hostRank":"G3","visitName":"以色列","visitRank":"G4",
     * "matchTime":1478979900},{"matchNo":6,"leagueId":128,"leagueName":"世欧预","leagueColor":"#660099",
     * "hostName":"列支敦","hostRank":"G6","visitName":"意大利","visitRank":"G2","matchTime":1478979900},{"matchNo":7,
     * "leagueId":128,"leagueName":"世欧预","leagueColor":"#660099","hostName":"西班牙","hostRank":"G1","visitName":"马其顿",
     * "visitRank":"G5","matchTime":1478979900},{"matchNo":8,"leagueId":128,"leagueName":"世欧预",
     * "leagueColor":"#660099","hostName":"乌克兰","hostRank":"I3","visitName":"芬兰","visitRank":"I5",
     * "matchTime":1478979900},{"matchNo":9,"leagueId":128,"leagueName":"世欧预","leagueColor":"#660099",
     * "hostName":"塞浦路","hostRank":"H5","visitName":"直布罗","visitRank":"H6","matchTime":1479056400},{"matchNo":10,
     * "leagueId":128,"leagueName":"世欧预","leagueColor":"#660099","hostName":"保加利","hostRank":"A4","visitName":"白俄",
     * "visitRank":"A5","matchTime":1479056400},{"matchNo":11,"leagueId":128,"leagueName":"世欧预",
     * "leagueColor":"#660099","hostName":"匈牙利","hostRank":"B3","visitName":"安道尔","visitRank":"B6",
     * "matchTime":1479056400},{"matchNo":12,"leagueId":128,"leagueName":"世欧预","leagueColor":"#660099",
     * "hostName":"葡萄牙","hostRank":"B2","visitName":"拉脱维","visitRank":"B5","matchTime":1479066300},{"matchNo":13,
     * "leagueId":128,"leagueName":"世欧预","leagueColor":"#660099","hostName":"瑞士","hostRank":"B1","visitName":"法罗",
     * "visitRank":"B4","matchTime":1479056400},{"matchNo":14,"leagueId":128,"leagueName":"世欧预",
     * "leagueColor":"#660099","hostName":"比利时","hostRank":"H1","visitName":"爱沙尼","visitRank":"H4",
     * "matchTime":1479066300}]}]}
     */

    private int      ret;
    /**
     * lotId : sfc
     * lotName : 胜负彩
     * issueList : [{"issueNo":"2016172","startTime":1478433600,"stopTime":1478789520,"status":2,
     * "matchList":[{"matchNo":1,"leagueId":130,"leagueName":"世南美预","leagueColor":"#528CEB","hostName":"哥伦比",
     * "hostRank":"A4","visitName":"智利","visitRank":"A5","matchTime":1478809800},{"matchNo":2,"leagueId":130,
     * "leagueName":"世南美预","leagueColor":"#528CEB","hostName":"乌拉圭","hostRank":"A2","visitName":"厄瓜多",
     * "visitRank":"A3","matchTime":1478818800},{"matchNo":3,"leagueId":130,"leagueName":"世南美预",
     * "leagueColor":"#528CEB","hostName":"巴拉圭","hostRank":"A7","visitName":"秘鲁","visitRank":"A8",
     * "matchTime":1478820600},{"matchNo":4,"leagueId":130,"leagueName":"世南美预","leagueColor":"#528CEB",
     * "hostName":"委内瑞","hostRank":"A10","visitName":"玻利维","visitRank":"A9","matchTime":1478820600},{"matchNo":5,
     * "leagueId":130,"leagueName":"世南美预","leagueColor":"#528CEB","hostName":"巴西","hostRank":"A1","visitName":"阿根廷",
     * "visitRank":"A6","matchTime":1478821500},{"matchNo":6,"leagueId":128,"leagueName":"世欧预",
     * "leagueColor":"#660099","hostName":"捷克","hostRank":"C5","visitName":"挪威","visitRank":"C4",
     * "matchTime":1478893500},{"matchNo":7,"leagueId":128,"leagueName":"世欧预","leagueColor":"#660099",
     * "hostName":"北爱","hostRank":"C3","visitName":"阿塞拜","visitRank":"C2","matchTime":1478893500},{"matchNo":8,
     * "leagueId":128,"leagueName":"世欧预","leagueColor":"#660099","hostName":"圣马力","hostRank":"C6","visitName":"德国",
     * "visitRank":"C1","matchTime":1478893500},{"matchNo":9,"leagueId":128,"leagueName":"世欧预",
     * "leagueColor":"#660099","hostName":"丹麦","hostRank":"E4","visitName":"哈萨克","visitRank":"E5",
     * "matchTime":1478893500},{"matchNo":10,"leagueId":128,"leagueName":"世欧预","leagueColor":"#660099",
     * "hostName":"罗马尼","hostRank":"E3","visitName":"波兰","visitRank":"E2","matchTime":1478893500},{"matchNo":11,
     * "leagueId":128,"leagueName":"世欧预","leagueColor":"#660099","hostName":"英格兰","hostRank":"F1","visitName":"苏格兰",
     * "visitRank":"F4","matchTime":1478893500},{"matchNo":12,"leagueId":128,"leagueName":"世欧预",
     * "leagueColor":"#660099","hostName":"马耳他","hostRank":"F6","visitName":"斯洛文","visitRank":"F3",
     * "matchTime":1478893500},{"matchNo":13,"leagueId":128,"leagueName":"世欧预","leagueColor":"#660099",
     * "hostName":"斯洛伐","hostRank":"F5","visitName":"立陶宛","visitRank":"F2","matchTime":1478893500},{"matchNo":14,
     * "leagueId":128,"leagueName":"世欧预","leagueColor":"#660099","hostName":"法国","hostRank":"A2","visitName":"瑞典",
     * "visitRank":"A1","matchTime":1478893500}]},{"issueNo":"2016173","startTime":1478692800,"stopTime":1478962320,
     * "status":0,"matchList":[{"matchNo":1,"leagueId":128,"leagueName":"世欧预","leagueColor":"#660099",
     * "hostName":"奥地利","hostRank":"D4","visitName":"爱尔兰","visitRank":"D2","matchTime":1478970000},{"matchNo":2,
     * "leagueId":128,"leagueName":"世欧预","leagueColor":"#660099","hostName":"格鲁吉","hostRank":"D5","visitName":"摩尔多",
     * "visitRank":"D6","matchTime":1478970000},{"matchNo":3,"leagueId":128,"leagueName":"世欧预",
     * "leagueColor":"#660099","hostName":"克罗地","hostRank":"I1","visitName":"冰岛","visitRank":"I2",
     * "matchTime":1478970000},{"matchNo":4,"leagueId":128,"leagueName":"世欧预","leagueColor":"#660099",
     * "hostName":"威尔士","hostRank":"D3","visitName":"塞尔维","visitRank":"D1","matchTime":1478979900},{"matchNo":5,
     * "leagueId":128,"leagueName":"世欧预","leagueColor":"#660099","hostName":"阿尔巴","hostRank":"G3","visitName":"以色列",
     * "visitRank":"G4","matchTime":1478979900},{"matchNo":6,"leagueId":128,"leagueName":"世欧预",
     * "leagueColor":"#660099","hostName":"列支敦","hostRank":"G6","visitName":"意大利","visitRank":"G2",
     * "matchTime":1478979900},{"matchNo":7,"leagueId":128,"leagueName":"世欧预","leagueColor":"#660099",
     * "hostName":"西班牙","hostRank":"G1","visitName":"马其顿","visitRank":"G5","matchTime":1478979900},{"matchNo":8,
     * "leagueId":128,"leagueName":"世欧预","leagueColor":"#660099","hostName":"乌克兰","hostRank":"I3","visitName":"芬兰",
     * "visitRank":"I5","matchTime":1478979900},{"matchNo":9,"leagueId":128,"leagueName":"世欧预",
     * "leagueColor":"#660099","hostName":"塞浦路","hostRank":"H5","visitName":"直布罗","visitRank":"H6",
     * "matchTime":1479056400},{"matchNo":10,"leagueId":128,"leagueName":"世欧预","leagueColor":"#660099",
     * "hostName":"保加利","hostRank":"A4","visitName":"白俄","visitRank":"A5","matchTime":1479056400},{"matchNo":11,
     * "leagueId":128,"leagueName":"世欧预","leagueColor":"#660099","hostName":"匈牙利","hostRank":"B3","visitName":"安道尔",
     * "visitRank":"B6","matchTime":1479056400},{"matchNo":12,"leagueId":128,"leagueName":"世欧预",
     * "leagueColor":"#660099","hostName":"葡萄牙","hostRank":"B2","visitName":"拉脱维","visitRank":"B5",
     * "matchTime":1479066300},{"matchNo":13,"leagueId":128,"leagueName":"世欧预","leagueColor":"#660099",
     * "hostName":"瑞士","hostRank":"B1","visitName":"法罗","visitRank":"B4","matchTime":1479056400},{"matchNo":14,
     * "leagueId":128,"leagueName":"世欧预","leagueColor":"#660099","hostName":"比利时","hostRank":"H1","visitName":"爱沙尼",
     * "visitRank":"H4","matchTime":1479066300}]}]
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

    public static class DataBean implements Serializable {
        private String              lotId;
        private String              lotName;
        /**
         * issueNo : 2016172
         * startTime : 1478433600
         * stopTime : 1478789520
         * status : 2
         * matchList : [{"matchNo":1,"leagueId":130,"leagueName":"世南美预","leagueColor":"#528CEB","hostName":"哥伦比",
         * "hostRank":"A4","visitName":"智利","visitRank":"A5","matchTime":1478809800},{"matchNo":2,"leagueId":130,
         * "leagueName":"世南美预","leagueColor":"#528CEB","hostName":"乌拉圭","hostRank":"A2","visitName":"厄瓜多",
         * "visitRank":"A3","matchTime":1478818800},{"matchNo":3,"leagueId":130,"leagueName":"世南美预",
         * "leagueColor":"#528CEB","hostName":"巴拉圭","hostRank":"A7","visitName":"秘鲁","visitRank":"A8",
         * "matchTime":1478820600},{"matchNo":4,"leagueId":130,"leagueName":"世南美预","leagueColor":"#528CEB",
         * "hostName":"委内瑞","hostRank":"A10","visitName":"玻利维","visitRank":"A9","matchTime":1478820600},{"matchNo":5,
         * "leagueId":130,"leagueName":"世南美预","leagueColor":"#528CEB","hostName":"巴西","hostRank":"A1",
         * "visitName":"阿根廷","visitRank":"A6","matchTime":1478821500},{"matchNo":6,"leagueId":128,"leagueName":"世欧预",
         * "leagueColor":"#660099","hostName":"捷克","hostRank":"C5","visitName":"挪威","visitRank":"C4",
         * "matchTime":1478893500},{"matchNo":7,"leagueId":128,"leagueName":"世欧预","leagueColor":"#660099",
         * "hostName":"北爱","hostRank":"C3","visitName":"阿塞拜","visitRank":"C2","matchTime":1478893500},{"matchNo":8,
         * "leagueId":128,"leagueName":"世欧预","leagueColor":"#660099","hostName":"圣马力","hostRank":"C6",
         * "visitName":"德国","visitRank":"C1","matchTime":1478893500},{"matchNo":9,"leagueId":128,"leagueName":"世欧预",
         * "leagueColor":"#660099","hostName":"丹麦","hostRank":"E4","visitName":"哈萨克","visitRank":"E5",
         * "matchTime":1478893500},{"matchNo":10,"leagueId":128,"leagueName":"世欧预","leagueColor":"#660099",
         * "hostName":"罗马尼","hostRank":"E3","visitName":"波兰","visitRank":"E2","matchTime":1478893500},{"matchNo":11,
         * "leagueId":128,"leagueName":"世欧预","leagueColor":"#660099","hostName":"英格兰","hostRank":"F1",
         * "visitName":"苏格兰","visitRank":"F4","matchTime":1478893500},{"matchNo":12,"leagueId":128,
         * "leagueName":"世欧预","leagueColor":"#660099","hostName":"马耳他","hostRank":"F6","visitName":"斯洛文",
         * "visitRank":"F3","matchTime":1478893500},{"matchNo":13,"leagueId":128,"leagueName":"世欧预",
         * "leagueColor":"#660099","hostName":"斯洛伐","hostRank":"F5","visitName":"立陶宛","visitRank":"F2",
         * "matchTime":1478893500},{"matchNo":14,"leagueId":128,"leagueName":"世欧预","leagueColor":"#660099",
         * "hostName":"法国","hostRank":"A2","visitName":"瑞典","visitRank":"A1","matchTime":1478893500}]
         */

        private List<IssueListBean> issueList;

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

        public List<IssueListBean> getIssueList() {
            return issueList;
        }

        public void setIssueList(List<IssueListBean> issueList) {
            this.issueList = issueList;
        }

        public static class IssueListBean implements Serializable {
            private String              issueNo;
            private int                 startTime;
            private int                 stopTime;
            private int                 status;
            /**
             * matchNo : 1
             * leagueId : 130
             * leagueName : 世南美预
             * leagueColor : #528CEB
             * hostName : 哥伦比
             * hostRank : A4
             * visitName : 智利
             * visitRank : A5
             * matchTime : 1478809800
             */

            private List<MatchListBean> matchList;

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

            public List<MatchListBean> getMatchList() {
                return matchList;
            }

            public void setMatchList(List<MatchListBean> matchList) {
                this.matchList = matchList;
            }

            public static class MatchListBean implements Serializable {
                private int    matchNo;
                private int    leagueId;
                private String leagueName;
                private String leagueColor;
                private String hostName;
                private String hostRank;
                private String visitName;
                private String visitRank;

                public String getMatchId() {
                    return matchId;
                }

                public void setMatchId(String matchId) {
                    this.matchId = matchId;
                }

                private String       matchId;
                private int          matchTime;
                private int          selectPosition;
                private String       weekNo;
                private double       hand;
                private int          validTime;
                private List<String> winRate;
                //混合过关 下注情况
                private List<String> stakeOption;
                //混合过关 下注分值
                private List<String> stakeSp;
                //混合过关单场比赛的彩种
                private String       lotId;

                public List<String> getStakeOption() {
                    return stakeOption;
                }

                public void setStakeOption(List<String> stakeOption) {
                    this.stakeOption = stakeOption;
                }

                public List<String> getStakeSp() {
                    return stakeSp;
                }

                public void setStakeSp(List<String> stakeSp) {
                    this.stakeSp = stakeSp;
                }

                public String getLotId() {
                    return lotId;
                }

                public void setLotId(String lotId) {
                    this.lotId = lotId;
                }

                public List<String> getWinRate() {
                    return winRate;
                }

                public void setWinRate(List<String> winRate) {
                    this.winRate = winRate;
                }


                public int getValidTime() {
                    return validTime;
                }

                public void setValidTime(int validTime) {
                    this.validTime = validTime;
                }

                public double getHand() {
                    return hand;
                }

                public void setHand(double hand) {
                    this.hand = hand;
                }

                public String getWeekNo() {
                    return weekNo;
                }

                public void setWeekNo(String weekNo) {
                    this.weekNo = weekNo;
                }

                public int getSelectPosition() {
                    return selectPosition;
                }

                public void setSelectPosition(int selectPosition) {
                    this.selectPosition = selectPosition;
                }

                private HashMap<Integer, Boolean> CheckedHashMap;

                public HashMap<Integer, Boolean> getCheckedHashMap() {
                    return CheckedHashMap;
                }

                private boolean canChecked;//是否可以被选中
                private int     checkedNum;//选中的长度

                public boolean isChecked() {
                    return isChecked;
                }

                public void setChecked(boolean checked) {
                    isChecked = checked;
                }

                public boolean isCanChecked() {
                    return canChecked;
                }

                public void setCanChecked(boolean canChecked) {
                    this.canChecked = canChecked;
                }

                private boolean isChecked;//胆是否被选中

                public void setCheckedHashMap(HashMap<Integer, Boolean> checkedHashMap) {
                    CheckedHashMap = checkedHashMap;
                }

                public int getMatchNo() {
                    return matchNo;
                }

                public void setMatchNo(int matchNo) {
                    this.matchNo = matchNo;
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

                public Boolean getSpsState(int position) {
                    if (null != CheckedHashMap && CheckedHashMap.containsKey(position)) {
                        return CheckedHashMap.get(position);
                    }
                    return false;
                }

                public void setSpsState(int position, Boolean flag) {
                    if (null == this.CheckedHashMap) {
                        this.CheckedHashMap = new HashMap<>();
                    }
                    this.CheckedHashMap.put(position, flag);
                }

                @Override
                public String toString() {
                    return "MatchListBean{" +
                            "matchNo=" + matchNo +
                            ", leagueId=" + leagueId +
                            ", leagueName='" + leagueName + '\'' +
                            ", leagueColor='" + leagueColor + '\'' +
                            ", hostName='" + hostName + '\'' +
                            ", hostRank='" + hostRank + '\'' +
                            ", visitName='" + visitName + '\'' +
                            ", visitRank='" + visitRank + '\'' +
                            ", matchId='" + matchId + '\'' +
                            ", matchTime=" + matchTime +
                            ", selectPosition=" + selectPosition +
                            ", weekNo='" + weekNo + '\'' +
                            ", hand=" + hand +
                            ", validTime=" + validTime +
                            ", winRate=" + winRate +
                            ", stakeOption=" + stakeOption +
                            ", stakeSp=" + stakeSp +
                            ", lotId='" + lotId + '\'' +
                            ", CheckedHashMap=" + CheckedHashMap +
                            ", canChecked=" + canChecked +
                            ", checkedNum=" + checkedNum +
                            ", isChecked=" + isChecked +
                            '}';
                }
            }
        }
    }
}
