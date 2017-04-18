package shlottery.gov.cn.lotterycenter.utils;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shlottery.gov.cn.lotterycenter.bean.JzScoreMatchBean;

/**
 * Created by booob on 2016-08-25-0025.
 * 处理竞彩比分中的数据
 * 大致思路: 一共6个方法 按时间/赛事分  按未开赛/进行中/已结束分  赛事筛选
 * 1    首先根据比赛状态将比赛分为4种:全部,未开赛,进行中,已结束.
 * 2    新建一个 JzScoreMatchBean,按状态将数据源中的数据分类,取需要的部分,
 * 添加到新建的JzScoreMatchBean中(分别获取有用的matchbean,leaguebean,添加进去)
 * 3    然后在处理按照赛事或者时间分类
 */
public class ManageJCScoreUtils {

    //将竞彩比分数据按时间分类,方便expandlistview使用
    public static List<List<JzScoreMatchBean.DataBean.MatchsBean>> sortAsTime(JzScoreMatchBean mJzScoreMatchBean) {
        if (null != mJzScoreMatchBean && mJzScoreMatchBean.getData() != null && mJzScoreMatchBean.getData().getMatchs() != null && mJzScoreMatchBean.getData().getMatchs().size() != 0) {
            List<List<JzScoreMatchBean.DataBean.MatchsBean>> mMatchDataBeanLists = new ArrayList<>();
            List<List<JzScoreMatchBean.DataBean.MatchsBean>> mMatchDataBeanLists1 = new ArrayList<>();

            for (int j = 0; j < mJzScoreMatchBean.getData().getDays().size(); j++) {
                List<JzScoreMatchBean.DataBean.MatchsBean> mList = new ArrayList<>();
                List<JzScoreMatchBean.DataBean.MatchsBean> mList1 = new ArrayList<>();
                mMatchDataBeanLists.add(mList);//初始化
                mMatchDataBeanLists1.add(mList1);//初始化
            }

            for (int i = 0; i < mJzScoreMatchBean.getData().getMatchs().size(); i++) {
                for (int j = 0; j < mJzScoreMatchBean.getData().getDays().size(); j++) {
                    if (mJzScoreMatchBean.getData().getMatchs().get(i).getMatchDay().equals(mJzScoreMatchBean.getData().getDays().get(j))) {
                        mMatchDataBeanLists.get(j).add(mJzScoreMatchBean.getData().getMatchs().get(i));
                    }
                }
            }
            List<JzScoreMatchBean.DataBean.MatchsBean> list1 = new ArrayList<>();
            List<JzScoreMatchBean.DataBean.MatchsBean> list2 = new ArrayList<>();
            List<JzScoreMatchBean.DataBean.MatchsBean> list3 = new ArrayList<>();
            List<JzScoreMatchBean.DataBean.MatchsBean> list4 = new ArrayList<>();
            String sid;
            for (int m = 0; m < mMatchDataBeanLists.size(); m++) {

                for (int n = 0; n < mMatchDataBeanLists.get(m).size(); n++) {
                    sid = mMatchDataBeanLists.get(m).get(n).getStatusId();
                    if (sid.equals("1")) {//未开赛

                        LogUtils.e("未开赛");
                        list1.add(mMatchDataBeanLists.get(m).get(n));
                    } else if (sid.equals("2") || sid.equals("6") || sid.equals("12") || sid.equals("13") || sid.equals("14") || sid.equals("15") || sid.equals("16") || sid.equals("7") || sid.equals("8") || sid.equals("9") || sid.equals("10") || sid.equals("11")) {//已完场
                        list2.add(mMatchDataBeanLists.get(m).get(n));
                        LogUtils.e("已完场");
                    } else {//进行中
                        LogUtils.e("进行中");
                        list3.add(mMatchDataBeanLists.get(m).get(n));
                    }
                    if (n == mMatchDataBeanLists.get(m).size() - 1) {
                        if (list3.size() > 0) {
                            list4.addAll(list3);
                        }
                        if (list1.size() > 0) {
                            list4.addAll(list1);
                        }
                        if (list2.size() > 0) {
                            list4.addAll(list2);
                        }
                        mMatchDataBeanLists1.get(m).addAll(list4);
                        list4.clear();
                        list1.clear();
                        list2.clear();
                        list3.clear();
                    }
                    LogUtils.e(list4.size() + "list4的长度");
                }
            }
            return mMatchDataBeanLists1;
        } else {
            return null;
        }
    }

    //将竞彩比分数据按赛事分类,方便expandlistview使用
    public static List<List<JzScoreMatchBean.DataBean.MatchsBean>> sortAsMatch(JzScoreMatchBean mJzScoreMatchBean) {
        if (null != mJzScoreMatchBean && mJzScoreMatchBean.getData() != null && mJzScoreMatchBean.getData().getMatchs() != null && mJzScoreMatchBean.getData().getMatchs().size() != 0) {
            List<List<JzScoreMatchBean.DataBean.MatchsBean>> mMatchDataBeanLists = new ArrayList<>();
            List<List<JzScoreMatchBean.DataBean.MatchsBean>> mMatchDataBeanLists1 = new ArrayList<>();
            for (int j = 0; j < mJzScoreMatchBean.getData().getMatchs().size(); j++) {
                List<JzScoreMatchBean.DataBean.MatchsBean> mList = new ArrayList<>();
                List<JzScoreMatchBean.DataBean.MatchsBean> mList1 = new ArrayList<>();
                mMatchDataBeanLists.add(mList);
                mMatchDataBeanLists1.add(mList1);

            }

            for (int i = 0; i < mJzScoreMatchBean.getData().getMatchs().size(); i++) {
                for (int j = 0; j < mJzScoreMatchBean.getData().getLeagues().size(); j++) {
                    if (mJzScoreMatchBean.getData().getLeagues().get(j).getLeagueId().equals(mJzScoreMatchBean.getData().getLeagues().get(i).getLeagueId())) {
                        mMatchDataBeanLists.get(j).add(mJzScoreMatchBean.getData().getMatchs().get(i));
                    }
                }
            }

            List<JzScoreMatchBean.DataBean.MatchsBean> list1 = new ArrayList<>();
            List<JzScoreMatchBean.DataBean.MatchsBean> list2 = new ArrayList<>();
            List<JzScoreMatchBean.DataBean.MatchsBean> list3 = new ArrayList<>();
            List<JzScoreMatchBean.DataBean.MatchsBean> list4 = new ArrayList<>();
            String sid;
            for (int m = 0; m < mMatchDataBeanLists.size(); m++) {

                for (int n = 0; n < mMatchDataBeanLists.get(m).size(); n++) {
                    sid = mMatchDataBeanLists.get(m).get(n).getStatusId();
                    if (sid.equals("1")) {//未开赛

                        LogUtils.e("未开赛");
                        list1.add(mMatchDataBeanLists.get(m).get(n));
                    } else if (sid.equals("2") || sid.equals("6") || sid.equals("12") || sid.equals("13") || sid.equals("14") || sid.equals("15") || sid.equals("16") || sid.equals("7") || sid.equals("8") || sid.equals("9") || sid.equals("10") || sid.equals("11") ) {//已完场
                        list2.add(mMatchDataBeanLists.get(m).get(n));
                        LogUtils.e("已完场");
                    } else {//进行中
                        LogUtils.e("进行中");
                        list3.add(mMatchDataBeanLists.get(m).get(n));
                    }
                    if (n == mMatchDataBeanLists.get(m).size() - 1) {
                        if (list3.size() > 0) {
                            list4.addAll(list3);
                        }
                        if (list1.size() > 0) {
                            list4.addAll(list1);
                        }
                        if (list2.size() > 0) {
                            list4.addAll(list2);
                        }
                        mMatchDataBeanLists1.get(m).addAll(list4);
                        list4.clear();
                        list1.clear();
                        list2.clear();
                        list3.clear();
                    }
                }
            }
            return mMatchDataBeanLists1;
        } else {
            return null;
        }
    }

    /**
     * @param mJzScoreMatchBean 竞彩原始数据
     * @return JzScoreMatchBean 筛选过后的数据
     */

    public static JzScoreMatchBean filterAsNoStart(JzScoreMatchBean mJzScoreMatchBean) {
        JzScoreMatchBean mJzScoreMatchBean1 = new JzScoreMatchBean();
        JzScoreMatchBean.DataBean dataBean = new JzScoreMatchBean.DataBean();
        List<JzScoreMatchBean.DataBean.MatchsBean> matchDataBeen = new ArrayList<>();
        List<JzScoreMatchBean.DataBean.LeaguesBean> leagueDataBeen = new ArrayList<>();
        if (null != mJzScoreMatchBean && mJzScoreMatchBean.getData() != null && mJzScoreMatchBean.getData().getMatchs() != null && mJzScoreMatchBean.getData().getMatchs().size() != 0) {
            Iterator<JzScoreMatchBean.DataBean.MatchsBean> it = mJzScoreMatchBean.getData().getMatchs().iterator();
            while (it.hasNext()) {
                JzScoreMatchBean.DataBean.MatchsBean s = it.next();
                if (null != s && s.getStatusId().equals("1")) {
                    // it.remove();
                    matchDataBeen.add(s);
                }
            }
            dataBean.setMatchs(matchDataBeen);
            mJzScoreMatchBean1.setData(dataBean);
           /* for (Iterator it = mJzScoreMatchBean.getData().getMatchs()().iterator(); it.hasNext(); ) {
                JzScoreMatchBean.DataBean.MatchsBean s = (JzScoreMatchBean.DataBean.MatchsBean) it.next();
                if (s.getSid().equals("1") ) {
                    mJzScoreMatchBean.getData().getMatchs()().remove(s);
                }
            }*/
            List<String> mLeagueList = new ArrayList<>();

            //遍历获取赛事集合
            for (int i = 0; i < mJzScoreMatchBean1.getData().getMatchs().size(); i++) {
                mLeagueList.add(mJzScoreMatchBean1.getData().getMatchs().get(i).getLeagueId());
            }

            //去除获取的赛事集合中重复的赛事
            for (int i = 0; i < mLeagueList.size() - 1; i++) {
                for (int j = mLeagueList.size() - 1; j > i; j--) {
                    if (mLeagueList.get(j).equals(mLeagueList.get(i))) {
                        mLeagueList.remove(j);
                    }
                }
            }

            Iterator<JzScoreMatchBean.DataBean.LeaguesBean> it2 = mJzScoreMatchBean.getData().getLeagues().iterator();
            while (it2.hasNext()) {
                JzScoreMatchBean.DataBean.LeaguesBean s = it2.next();
                if (null != s && mLeagueList.contains(s.getLeagueId())) {
                    //it2.remove();
                    leagueDataBeen.add(s);

                }
            }
            dataBean.setMatchs(matchDataBeen);
            mJzScoreMatchBean1.setData(dataBean);

            List<String> mDayList = new ArrayList<>();

            //遍历获取赛事集合
            for (int i = 0; i < mJzScoreMatchBean1.getData().getMatchs().size(); i++) {
                mDayList.add(mJzScoreMatchBean1.getData().getMatchs().get(i).getMatchDay());
            }

            //去除获取的赛事集合中重复的赛事
            for (int i = 0; i < mDayList.size() - 1; i++) {
                for (int j = mDayList.size() - 1; j > i; j--) {
                    if (mDayList.get(j).equals(mDayList.get(i))) {
                        mDayList.remove(j);
                    }
                }
            }


            mJzScoreMatchBean1.getData().setDays(mDayList);

            return mJzScoreMatchBean1;
        } else {
            return null;
        }
    }

    /**
     * 进行中
     *
     * @param mJzScoreMatchBean
     * @return
     */
    public static JzScoreMatchBean filterAsStarting(JzScoreMatchBean mJzScoreMatchBean) {
        JzScoreMatchBean mJzScoreMatchBean1 = new JzScoreMatchBean();
        JzScoreMatchBean.DataBean dataBean = new JzScoreMatchBean.DataBean();
        List<JzScoreMatchBean.DataBean.MatchsBean> matchDataBeen = new ArrayList<>();
        List<JzScoreMatchBean.DataBean.LeaguesBean> leagueDataBeen = new ArrayList<>();
        if (null != mJzScoreMatchBean && mJzScoreMatchBean.getData() != null && mJzScoreMatchBean.getData().getMatchs() != null && mJzScoreMatchBean.getData().getMatchs().size() != 0) {
            Iterator<JzScoreMatchBean.DataBean.MatchsBean> it = mJzScoreMatchBean.getData().getMatchs().iterator();
            while (it.hasNext()) {
                JzScoreMatchBean.DataBean.MatchsBean s = it.next();
                if (null != s && (s.getStatusId().equals("3") || s.getStatusId().equals("4") || s.getStatusId().equals("5"))) {
                    //  it.remove();
                    matchDataBeen.add(s);
                }
            }
            dataBean.setMatchs(matchDataBeen);
            mJzScoreMatchBean1.setData(dataBean);
           /* for (Iterator it = mJzScoreMatchBean.getData().getMatchs()().iterator(); it.hasNext(); ) {
                JzScoreMatchBean.DataBean.MatchsBean s = (JzScoreMatchBean.DataBean.MatchsBean) it.next();
                if (s.getSid().equals("1") ) {
                    mJzScoreMatchBean.getData().getMatchs()().remove(s);
                }
            }*/
            List<String> mLeagueList = new ArrayList<>();

            //遍历获取赛事集合
            for (int i = 0; i < mJzScoreMatchBean1.getData().getMatchs().size(); i++) {
                mLeagueList.add(mJzScoreMatchBean1.getData().getMatchs().get(i).getLeagueId());
                LogUtils.e(mJzScoreMatchBean1.getData().getMatchs().size() + "MatchData的长度");
            }

            //去除获取的赛事集合中重复的赛事
            for (int i = 0; i < mLeagueList.size() - 1; i++) {
                for (int j = mLeagueList.size() - 1; j > i; j--) {
                    if (mLeagueList.get(j).equals(mLeagueList.get(i))) {
                        mLeagueList.remove(j);
                    }
                }
            }

            Iterator<JzScoreMatchBean.DataBean.LeaguesBean> it2 = mJzScoreMatchBean.getData().getLeagues().iterator();
            while (it2.hasNext()) {
                JzScoreMatchBean.DataBean.LeaguesBean s = it2.next();
                if (null != s && mLeagueList.contains(s.getLeagueId())) {
                    //it2.remove();
                    leagueDataBeen.add(s);
                }
            }
            mJzScoreMatchBean1.getData().setLeagues(leagueDataBeen);

            List<String> mDayList = new ArrayList<>();

            //遍历获取赛事集合
            for (int i = 0; i < mJzScoreMatchBean1.getData().getMatchs().size(); i++) {
                mDayList.add(mJzScoreMatchBean1.getData().getMatchs().get(i).getMatchDay());
            }

            //去除获取的赛事集合中重复的赛事
            for (int i = 0; i < mDayList.size() - 1; i++) {
                for (int j = mDayList.size() - 1; j > i; j--) {
                    if (mDayList.get(j).equals(mDayList.get(i))) {
                        mDayList.remove(j);
                    }
                }
            }

            mJzScoreMatchBean1.getData().setDays(mDayList);
            return mJzScoreMatchBean1;
        } else {
            return null;
        }
    }


    /**
     * 已完场
     *
     * @param mJzScoreMatchBean
     * @return
     */
    public static JzScoreMatchBean filterAsFinsh(JzScoreMatchBean mJzScoreMatchBean) {
        List<JzScoreMatchBean.DataBean.MatchsBean> matchDataBeen = new ArrayList<>();
        List<JzScoreMatchBean.DataBean.LeaguesBean> leagueDataBeen = new ArrayList<>();
        JzScoreMatchBean mJzScoreMatchBean1 = new JzScoreMatchBean();
        JzScoreMatchBean.DataBean dataBean = new JzScoreMatchBean.DataBean();
        if (null != mJzScoreMatchBean && mJzScoreMatchBean.getData() != null && mJzScoreMatchBean.getData().getMatchs() != null && mJzScoreMatchBean.getData().getMatchs().size() != 0) {
            Iterator<JzScoreMatchBean.DataBean.MatchsBean> it = mJzScoreMatchBean.getData().getMatchs().iterator();
            while (it.hasNext()) {
                JzScoreMatchBean.DataBean.MatchsBean s = it.next();
                if (null != s && (s.getStatusId().equals("2") || s.getStatusId().equals("6") || s.getStatusId().equals("12") || s.getStatusId().equals("13") || s.getStatusId().equals("14") || s.getStatusId().equals("15") || s.getStatusId().equals("16") )) {
                    //  it.remove();
                    matchDataBeen.add(s);
                }
            }

            dataBean.setMatchs(matchDataBeen);
            mJzScoreMatchBean1.setData(dataBean);

            List<String> mLeagueList = new ArrayList<>();

            //遍历获取赛事集合
            for (int i = 0; i < mJzScoreMatchBean1.getData().getMatchs().size(); i++) {
                mLeagueList.add(mJzScoreMatchBean1.getData().getMatchs().get(i).getLeagueId());
                LogUtils.e(mJzScoreMatchBean1.getData().getMatchs().size() + "MatchData的长度");
            }

            //去除获取的赛事集合中重复的赛事
            for (int i = 0; i < mLeagueList.size() - 1; i++) {
                for (int j = mLeagueList.size() - 1; j > i; j--) {
                    if (mLeagueList.get(j).equals(mLeagueList.get(i))) {
                        mLeagueList.remove(j);
                    }
                }
            }

            Iterator<JzScoreMatchBean.DataBean.LeaguesBean> it2 = mJzScoreMatchBean.getData().getLeagues().iterator();
            while (it2.hasNext()) {
                JzScoreMatchBean.DataBean.LeaguesBean s = it2.next();
                if (null != s && mLeagueList.contains(s.getLeagueId())) {
                    // it2.remove();
                    leagueDataBeen.add(s);
                }
            }
            mJzScoreMatchBean1.getData().setLeagues(leagueDataBeen);
            List<String> mDayList = new ArrayList<>();

            //遍历获取赛事集合
            for (int i = 0; i < mJzScoreMatchBean1.getData().getMatchs().size(); i++) {
                mDayList.add(mJzScoreMatchBean1.getData().getMatchs().get(i).getMatchDay());
            }

            //去除获取的赛事集合中重复的赛事
            for (int i = 0; i < mDayList.size() - 1; i++) {
                for (int j = mDayList.size() - 1; j > i; j--) {
                    if (mDayList.get(j).equals(mDayList.get(i))) {
                        mDayList.remove(j);
                    }
                }
            }


            mJzScoreMatchBean1.getData().setDays(mDayList);
            return mJzScoreMatchBean1;
        } else {
            return null;
        }
    }

 /**
     * @param mJzScoreMatchBean 竞彩原始数据
     * @return JzScoreMatchBean 筛选过后的数据
     */

    public static JzScoreMatchBean jLfilterAsNoStart(JzScoreMatchBean mJzScoreMatchBean) {
        JzScoreMatchBean mJzScoreMatchBean1 = new JzScoreMatchBean();
        JzScoreMatchBean.DataBean dataBean = new JzScoreMatchBean.DataBean();
        List<JzScoreMatchBean.DataBean.MatchsBean> matchDataBeen = new ArrayList<>();
        List<JzScoreMatchBean.DataBean.LeaguesBean> leagueDataBeen = new ArrayList<>();
        if (null != mJzScoreMatchBean && mJzScoreMatchBean.getData() != null && mJzScoreMatchBean.getData().getMatchs() != null && mJzScoreMatchBean.getData().getMatchs().size() != 0) {
            Iterator<JzScoreMatchBean.DataBean.MatchsBean> it = mJzScoreMatchBean.getData().getMatchs().iterator();
            while (it.hasNext()) {
                JzScoreMatchBean.DataBean.MatchsBean s = it.next();
                if (null != s && s.getStatusId().equals("1")) {
                    // it.remove();
                    matchDataBeen.add(s);
                }
            }
            dataBean.setMatchs(matchDataBeen);
            mJzScoreMatchBean1.setData(dataBean);
           /* for (Iterator it = mJzScoreMatchBean.getData().getMatchs()().iterator(); it.hasNext(); ) {
                JzScoreMatchBean.DataBean.MatchsBean s = (JzScoreMatchBean.DataBean.MatchsBean) it.next();
                if (s.getSid().equals("1") ) {
                    mJzScoreMatchBean.getData().getMatchs()().remove(s);
                }
            }*/
            List<String> mLeagueList = new ArrayList<>();

            //遍历获取赛事集合
            for (int i = 0; i < mJzScoreMatchBean1.getData().getMatchs().size(); i++) {
                mLeagueList.add(mJzScoreMatchBean1.getData().getMatchs().get(i).getLeagueId());
            }

            //去除获取的赛事集合中重复的赛事
            for (int i = 0; i < mLeagueList.size() - 1; i++) {
                for (int j = mLeagueList.size() - 1; j > i; j--) {
                    if (mLeagueList.get(j).equals(mLeagueList.get(i))) {
                        mLeagueList.remove(j);
                    }
                }
            }

            Iterator<JzScoreMatchBean.DataBean.LeaguesBean> it2 = mJzScoreMatchBean.getData().getLeagues().iterator();
            while (it2.hasNext()) {
                JzScoreMatchBean.DataBean.LeaguesBean s = it2.next();
                if (null != s && mLeagueList.contains(s.getLeagueId())) {
                    //it2.remove();
                    leagueDataBeen.add(s);

                }
            }
            dataBean.setMatchs(matchDataBeen);
            mJzScoreMatchBean1.setData(dataBean);

            List<String> mDayList = new ArrayList<>();

            //遍历获取赛事集合
            for (int i = 0; i < mJzScoreMatchBean1.getData().getMatchs().size(); i++) {
                mDayList.add(mJzScoreMatchBean1.getData().getMatchs().get(i).getMatchDay());
            }

            //去除获取的赛事集合中重复的赛事
            for (int i = 0; i < mDayList.size() - 1; i++) {
                for (int j = mDayList.size() - 1; j > i; j--) {
                    if (mDayList.get(j).equals(mDayList.get(i))) {
                        mDayList.remove(j);
                    }
                }
            }


            mJzScoreMatchBean1.getData().setDays(mDayList);

            return mJzScoreMatchBean1;
        } else {
            return null;
        }
    }

    /**
     * 进行中
     *
     * @param mJzScoreMatchBean
     * @return
     */
    public static JzScoreMatchBean jLfilterAsStarting(JzScoreMatchBean mJzScoreMatchBean) {
        JzScoreMatchBean mJzScoreMatchBean1 = new JzScoreMatchBean();
        JzScoreMatchBean.DataBean dataBean = new JzScoreMatchBean.DataBean();
        List<JzScoreMatchBean.DataBean.MatchsBean> matchDataBeen = new ArrayList<>();
        List<JzScoreMatchBean.DataBean.LeaguesBean> leagueDataBeen = new ArrayList<>();
        if (null != mJzScoreMatchBean && mJzScoreMatchBean.getData() != null && mJzScoreMatchBean.getData().getMatchs() != null && mJzScoreMatchBean.getData().getMatchs().size() != 0) {
            Iterator<JzScoreMatchBean.DataBean.MatchsBean> it = mJzScoreMatchBean.getData().getMatchs().iterator();
            while (it.hasNext()) {
                JzScoreMatchBean.DataBean.MatchsBean s = it.next();
                if (null != s && (s.getStatusId().equals("3") || s.getStatusId().equals("4") || s.getStatusId().equals("5")|| s.getStatusId().equals("7") || s.getStatusId().equals("17")|| s.getStatusId().equals("18") || s.getStatusId().equals("19")|| s.getStatusId().equals("20") || s.getStatusId().equals("21")|| s.getStatusId().equals("22")|| s.getStatusId().equals("23") || s.getStatusId().equals("24")|| s.getStatusId().equals("25") || s.getStatusId().equals("26")|| s.getStatusId().equals("27") || s.getStatusId().equals("28")|| s.getStatusId().equals("29")|| s.getStatusId().equals("30") || s.getStatusId().equals("31")|| s.getStatusId().equals("32") || s.getStatusId().equals("33"))) {
                    //  it.remove();
                    matchDataBeen.add(s);
                }
            }
            dataBean.setMatchs(matchDataBeen);
            mJzScoreMatchBean1.setData(dataBean);
           /* for (Iterator it = mJzScoreMatchBean.getData().getMatchs()().iterator(); it.hasNext(); ) {
                JzScoreMatchBean.DataBean.MatchsBean s = (JzScoreMatchBean.DataBean.MatchsBean) it.next();
                if (s.getSid().equals("1") ) {
                    mJzScoreMatchBean.getData().getMatchs()().remove(s);
                }
            }*/
            List<String> mLeagueList = new ArrayList<>();

            //遍历获取赛事集合
            for (int i = 0; i < mJzScoreMatchBean1.getData().getMatchs().size(); i++) {
                mLeagueList.add(mJzScoreMatchBean1.getData().getMatchs().get(i).getLeagueId());
                LogUtils.e(mJzScoreMatchBean1.getData().getMatchs().size() + "MatchData的长度");
            }

            //去除获取的赛事集合中重复的赛事
            for (int i = 0; i < mLeagueList.size() - 1; i++) {
                for (int j = mLeagueList.size() - 1; j > i; j--) {
                    if (mLeagueList.get(j).equals(mLeagueList.get(i))) {
                        mLeagueList.remove(j);
                    }
                }
            }

            Iterator<JzScoreMatchBean.DataBean.LeaguesBean> it2 = mJzScoreMatchBean.getData().getLeagues().iterator();
            while (it2.hasNext()) {
                JzScoreMatchBean.DataBean.LeaguesBean s = it2.next();
                if (null != s && mLeagueList.contains(s.getLeagueId())) {
                    //it2.remove();
                    leagueDataBeen.add(s);
                }
            }
            mJzScoreMatchBean1.getData().setLeagues(leagueDataBeen);

            List<String> mDayList = new ArrayList<>();

            //遍历获取赛事集合
            for (int i = 0; i < mJzScoreMatchBean1.getData().getMatchs().size(); i++) {
                mDayList.add(mJzScoreMatchBean1.getData().getMatchs().get(i).getMatchDay());
            }

            //去除获取的赛事集合中重复的赛事
            for (int i = 0; i < mDayList.size() - 1; i++) {
                for (int j = mDayList.size() - 1; j > i; j--) {
                    if (mDayList.get(j).equals(mDayList.get(i))) {
                        mDayList.remove(j);
                    }
                }
            }

            mJzScoreMatchBean1.getData().setDays(mDayList);
            return mJzScoreMatchBean1;
        } else {
            return null;
        }
    }


    /**
     * 已完场
     *
     * @param mJzScoreMatchBean
     * @return
     */
    public static JzScoreMatchBean jLfilterAsFinsh(JzScoreMatchBean mJzScoreMatchBean) {
        List<JzScoreMatchBean.DataBean.MatchsBean> matchDataBeen = new ArrayList<>();
        List<JzScoreMatchBean.DataBean.LeaguesBean> leagueDataBeen = new ArrayList<>();
        JzScoreMatchBean mJzScoreMatchBean1 = new JzScoreMatchBean();
        JzScoreMatchBean.DataBean dataBean = new JzScoreMatchBean.DataBean();
        if (null != mJzScoreMatchBean && mJzScoreMatchBean.getData() != null && mJzScoreMatchBean.getData().getMatchs() != null && mJzScoreMatchBean.getData().getMatchs().size() != 0) {
            Iterator<JzScoreMatchBean.DataBean.MatchsBean> it = mJzScoreMatchBean.getData().getMatchs().iterator();
            while (it.hasNext()) {
                JzScoreMatchBean.DataBean.MatchsBean s = it.next();
                if (null != s && (s.getStatusId().equals("2") || s.getStatusId().equals("6") || s.getStatusId().equals("12") || s.getStatusId().equals("13") || s.getStatusId().equals("14") || s.getStatusId().equals("15") || s.getStatusId().equals("16"))) {
                    //  it.remove();
                    matchDataBeen.add(s);
                }
            }

            dataBean.setMatchs(matchDataBeen);
            mJzScoreMatchBean1.setData(dataBean);

            List<String> mLeagueList = new ArrayList<>();

            //遍历获取赛事集合
            for (int i = 0; i < mJzScoreMatchBean1.getData().getMatchs().size(); i++) {
                mLeagueList.add(mJzScoreMatchBean1.getData().getMatchs().get(i).getLeagueId());
                LogUtils.e(mJzScoreMatchBean1.getData().getMatchs().size() + "MatchData的长度");
            }

            //去除获取的赛事集合中重复的赛事
            for (int i = 0; i < mLeagueList.size() - 1; i++) {
                for (int j = mLeagueList.size() - 1; j > i; j--) {
                    if (mLeagueList.get(j).equals(mLeagueList.get(i))) {
                        mLeagueList.remove(j);
                    }
                }
            }

            Iterator<JzScoreMatchBean.DataBean.LeaguesBean> it2 = mJzScoreMatchBean.getData().getLeagues().iterator();
            while (it2.hasNext()) {
                JzScoreMatchBean.DataBean.LeaguesBean s = it2.next();
                if (null != s && mLeagueList.contains(s.getLeagueId())) {
                    // it2.remove();
                    leagueDataBeen.add(s);
                }
            }
            mJzScoreMatchBean1.getData().setLeagues(leagueDataBeen);
            List<String> mDayList = new ArrayList<>();

            //遍历获取赛事集合
            for (int i = 0; i < mJzScoreMatchBean1.getData().getMatchs().size(); i++) {
                mDayList.add(mJzScoreMatchBean1.getData().getMatchs().get(i).getMatchDay());
            }

            //去除获取的赛事集合中重复的赛事
            for (int i = 0; i < mDayList.size() - 1; i++) {
                for (int j = mDayList.size() - 1; j > i; j--) {
                    if (mDayList.get(j).equals(mDayList.get(i))) {
                        mDayList.remove(j);
                    }
                }
            }


            mJzScoreMatchBean1.getData().setDays(mDayList);
            return mJzScoreMatchBean1;
        } else {
            return null;
        }
    }


    public static JzScoreMatchBean filterAsLeagues(JzScoreMatchBean mJzScoreMatchBean, List<String> mLeagueList) {
        List<JzScoreMatchBean.DataBean.MatchsBean> matchDataBeen = new ArrayList<>();
        List<JzScoreMatchBean.DataBean.LeaguesBean> leagueDataBeen = new ArrayList<>();
        JzScoreMatchBean mJzScoreMatchBean1 = new JzScoreMatchBean();
        JzScoreMatchBean.DataBean dataBean = new JzScoreMatchBean.DataBean();
        if (null != mJzScoreMatchBean && mJzScoreMatchBean.getData() != null && mJzScoreMatchBean.getData().getMatchs() != null && mJzScoreMatchBean.getData().getMatchs().size() != 0) {
            Iterator<JzScoreMatchBean.DataBean.MatchsBean> it = mJzScoreMatchBean.getData().getMatchs().iterator();
            while (it.hasNext()) {
                JzScoreMatchBean.DataBean.MatchsBean s = it.next();
                if (null != s && mLeagueList.contains(s.getLeagueName())) {
                    //  it.remove();
                    matchDataBeen.add(s);
                }
            }
            //往新集合中添加matchBean
            dataBean.setMatchs(matchDataBeen);
            mJzScoreMatchBean1.setData(dataBean);

            Iterator<JzScoreMatchBean.DataBean.LeaguesBean> it2 = mJzScoreMatchBean.getData().getLeagues().iterator();
            while (it2.hasNext()) {
                JzScoreMatchBean.DataBean.LeaguesBean s = it2.next();
                if (null != s && mLeagueList.contains(s.getLeagueName())) {
                    // it2.remove();
                    leagueDataBeen.add(s);
                }
            }
            mJzScoreMatchBean1.getData().setLeagues(leagueDataBeen);
            List<String> mDayList = new ArrayList<>();

            //遍历获取赛事集合
            for (int i = 0; i < mJzScoreMatchBean1.getData().getMatchs().size(); i++) {
                mDayList.add(mJzScoreMatchBean1.getData().getMatchs().get(i).getMatchDay());
            }

            //去除获取的赛事集合中重复的日期
            for (int i = 0; i < mDayList.size() - 1; i++) {
                for (int j = mDayList.size() - 1; j > i; j--) {
                    if (mDayList.get(j).equals(mDayList.get(i))) {
                        mDayList.remove(j);
                    }
                }
            }

            mJzScoreMatchBean1.getData().setDays(mDayList);
            return mJzScoreMatchBean1;
        } else {
            return null;
        }
    }
}
