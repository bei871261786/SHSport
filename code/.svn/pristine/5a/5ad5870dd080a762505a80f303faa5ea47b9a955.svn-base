package shlottery.gov.cn.lotterycenter.bean.Jincai;

import java.io.Serializable;

/**
 * Created by booob on 2016-05-23-0023.
 */
public class LeaguesBean implements Serializable, Comparable {
    private int leagueId;
    private String leagueName;
    private String leagueColor;
    private int isHot;
    private boolean isFlitrate;
    private boolean isChecked;

    public boolean isFlitrate() {
        return isFlitrate;
    }

    public void setFlitrate(boolean flitrate) {
        isFlitrate = flitrate;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
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

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    @Override
    public int compareTo(Object another) {
        LeaguesBean compareObject = (LeaguesBean) another;
        return this.getLeagueName().compareTo(compareObject.getLeagueName());
    }
}