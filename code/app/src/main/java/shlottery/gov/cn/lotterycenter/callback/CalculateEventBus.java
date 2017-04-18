package shlottery.gov.cn.lotterycenter.callback;

import shlottery.gov.cn.lotterycenter.bean.IssueBonusBean;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusNumberDetailBean;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusSoccerDetailBean;
import shlottery.gov.cn.lotterycenter.bean.NumLotteryBean;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-04-0004 21:42
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class CalculateEventBus {
    private IssueBonusBean issueBonusBean;

    public IssueBonusNumberDetailBean getIssueBonusNumberDetailBean() {
        return issueBonusNumberDetailBean;
    }

    public void setIssueBonusNumberDetailBean(IssueBonusNumberDetailBean issueBonusNumberDetailBean) {
        this.issueBonusNumberDetailBean = issueBonusNumberDetailBean;
    }

    private IssueBonusNumberDetailBean issueBonusNumberDetailBean;//开奖详细数据
    private IssueBonusSoccerDetailBean issueBonusSoccerDetailBean;//开奖详细数据

    public IssueBonusSoccerDetailBean getIssueBonusSoccerDetailBean() {
        return issueBonusSoccerDetailBean;
    }

    public void setIssueBonusSoccerDetailBean(IssueBonusSoccerDetailBean issueBonusSoccerDetailBean) {
        this.issueBonusSoccerDetailBean = issueBonusSoccerDetailBean;
    }

    private String pl3Issue;//排列三选中的期号
    private String pl5Issue;

    public IssueBonusBean getIssueBonusBean() {
        return issueBonusBean;
    }

    public void setIssueBonusBean(IssueBonusBean issueBonusBean) {
        this.issueBonusBean = issueBonusBean;
    }

    public String getPl3Issue() {
        return pl3Issue;
    }

    public void setPl3Issue(String pl3Issue) {
        this.pl3Issue = pl3Issue;
    }

    public String getPl5Issue() {
        return pl5Issue;
    }

    public void setPl5Issue(String pl5Issue) {
        this.pl5Issue = pl5Issue;
    }

    public String getQxcIssue() {
        return qxcIssue;
    }

    public void setQxcIssue(String qxcIssue) {
        this.qxcIssue = qxcIssue;
    }

    public String getDltIssue() {
        return dltIssue;
    }

    public void setDltIssue(String dltIssue) {
        this.dltIssue = dltIssue;
    }

    public String getSfcIssue() {
        return sfcIssue;
    }

    public void setSfcIssue(String sfcIssue) {
        this.sfcIssue = sfcIssue;
    }

    public String getBqcIssue() {
        return bqcIssue;
    }

    public void setBqcIssue(String bqcIssue) {
        this.bqcIssue = bqcIssue;
    }

    public String getJqcIssue() {
        return jqcIssue;
    }

    public void setJqcIssue(String jqcIssue) {
        this.jqcIssue = jqcIssue;
    }

    private String qxcIssue;
    private String dltIssue;
    private String sfcIssue;
    private String bqcIssue;
    private String jqcIssue;
}
