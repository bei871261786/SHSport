package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by booob on 2016-08-30-0030.
 */
public class JcScoreEventBus {
    public int mMatchStatus; //比赛状态(0全部,1未开赛,2进行中,3已完场)
    public boolean mSortAsTime; //是否按照时间排序(true按照时间排序,false按照赛事排序)
    public String mMatchTime;   //比赛日期
    public ArrayList<String> mLeagueList;    //赛事筛选集合



    public JcScoreEventBus(int mMatchStatus, boolean mSortAsTime, String mMatchTime, ArrayList<String> mLeagueList) {
        this.mMatchStatus = mMatchStatus;
        this.mSortAsTime = mSortAsTime;
        this.mMatchTime = mMatchTime;
        this.mLeagueList = mLeagueList;
    }

    public boolean ismSortAsTime() {
        return mSortAsTime;
    }

    public void setmSortAsTime(boolean mSortAsTime) {
        this.mSortAsTime = mSortAsTime;
    }


    public int getmMatchStatus() {
        return mMatchStatus;
    }

    public void setmMatchStatus(int mMatchStatus) {
        this.mMatchStatus = mMatchStatus;
    }

    public String getmMatchTime() {
        return mMatchTime;
    }

    public void setmMatchTime(String mMatchTime) {
        this.mMatchTime = mMatchTime;
    }

    public ArrayList<String> getmLeagueList() {
        return mLeagueList;
    }

    public void setmLeagueList(ArrayList<String> mLeagueList) {
        this.mLeagueList = mLeagueList;
    }
}
