package shlottery.gov.cn.lotterycenter.bean.Jincai;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by booob on 2016-05-23-0023.
 */
public class MatchsBean extends JingCaiZuqiuInfo.DataBean.MatchsBean implements Serializable {
    private String issueNo;
    private String matchDay;
    private int matchNo;
    private int hot;
    private String weekNo;
    private int leagueId;
    private String matchId;
    private String leagueName;
    private String leagueColor;
    private String hostName;
    private String hostRank;
    private String visitName;
    private String visitRank;
    private int matchTime;
    private int stopTime;
    private int status;

    public boolean isDan() {
        return isDan;
    }

    public void setDan(boolean dan) {
        isDan = dan;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    private boolean isDan;
    /**
     * lotId : jzxspf
     * saleType : g
     * hand : 1
     * sps : ["1.40","4.00","6.10"]
     */
    //胜平负是否选中
    private List<SpListBean> spList;

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

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

    public List<SpListBean> getSpList() {
        return spList;
    }

    public void setSpList(List<SpListBean> spList) {
        this.spList = spList;
    }

    private String lotId;
    private String saleType;
    private double hand;
    private List<String> sps;


    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public double getHand() {
        return hand;
    }

    public void setHand(double hand) {
        this.hand = hand;
    }

    public List<String> getSps() {
        return sps;
    }

    public void setSps(List<String> sps) {
        this.sps = sps;
    }


    private ArrayList<Integer> zjqCount;

    public ArrayList<Integer> getZjqCount() {
        return zjqCount;
    }

    public void addZjqCount(int count) {
        if (null == this.zjqCount) {
            this.zjqCount = new ArrayList<>();
        }
        this.zjqCount.add(count);
    }

    public void removeZjqCount(int count) {
        if (null != this.zjqCount && this.zjqCount.contains(count)) {
            zjqCount.remove(zjqCount.indexOf(count));
        }
    }

    private HashMap<Integer, Boolean> spsSelectedStateMap;

    public Boolean getSpsState(int position) {
        if (null != spsSelectedStateMap && spsSelectedStateMap.containsKey(position)) {
            return spsSelectedStateMap.get(position);
        }
        return false;
    }

    public void setSpsState(int position, Boolean j) {
        if (null == this.spsSelectedStateMap) {
            this.spsSelectedStateMap = new HashMap<>();
        }
        this.spsSelectedStateMap.put(position, j);
    }

    public HashMap<Integer, Boolean> getSpsStateMap() {
        if (null != spsSelectedStateMap) {
            return spsSelectedStateMap;
        }
        return null;
    }

}