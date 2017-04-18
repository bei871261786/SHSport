package shlottery.gov.cn.lotterycenter.utils;


import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import shlottery.gov.cn.lotterycenter.bean.Jincai.LeaguesBean;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;

/**
 * Created by yongchao.bei on 2016/6/28.
 */
public class EventBusUtil {

    public class DingdanResultEvent {
        private String           guoguanfangshi;
        private List<MatchsBean> data;
        //比赛类型 0 足球 1 篮球
        int type;
        //彩种类型
        private int subType;

        DingdanResultEvent(int type, int subType, String guoguanfangshi, List<MatchsBean> data) {
            this.type = type;
            this.subType = subType;
            this.guoguanfangshi = guoguanfangshi;
            this.data = data;
        }

        public int getType() {
            return type;
        }

        public int getSubType() {
            return subType;
        }

        public String getDingdanGuoguanfangshi() {
            return guoguanfangshi;
        }

        public List<MatchsBean> getDingdanData() {
            return data;
        }
    }

    public DingdanResultEvent getDingdanResultEvent(int type, int subType, String guoguanfangshi, List<MatchsBean>
            data) {
        return new DingdanResultEvent(type, subType, guoguanfangshi, data);
    }

    public class FilterResultEvent {
        private TreeSet<LeaguesBean> filterMatch;

        FilterResultEvent(TreeSet<LeaguesBean> filterMatch) {
            this.filterMatch = filterMatch;
        }

        public TreeSet<LeaguesBean> getFilterMatch() {
            return filterMatch;
        }

        public void setFilterMatch(TreeSet<LeaguesBean> filterMatch) {
            this.filterMatch = filterMatch;
        }
    }

    public FilterResultEvent getFilterResultEvent(TreeSet<LeaguesBean> filterMatch) {

        return new FilterResultEvent(filterMatch);
    }


    public class DingdanMixResultEvent {
        private String                             guoguanfangshi;
        private List<HashMap<Integer, MatchsBean>> data;
        //比赛类型 0 足球 1篮球
        private int type;
        //彩种类型
        private int                                subType;

        DingdanMixResultEvent(int type,int subType, String guoguanfangshi, List<HashMap<Integer, MatchsBean>> data) {
            this.type = type;
            this.subType = subType;
            this.guoguanfangshi = guoguanfangshi;
            this.data = data;
        }

        public int getType() {
            return type;
        }

        public int getSubType() {
            return subType;
        }

        public String getDingdanGuoguanfangshi() {
            return guoguanfangshi;
        }

        public List<HashMap<Integer, MatchsBean>> getDingdanData() {
            return data;
        }
    }

    public DingdanMixResultEvent getDingdanMixResultEvent(int type, int subType, String guoguanfangshi,
                                                          List<HashMap<Integer, MatchsBean>> data) {
        return new DingdanMixResultEvent(type,subType, guoguanfangshi, data);
    }


}
