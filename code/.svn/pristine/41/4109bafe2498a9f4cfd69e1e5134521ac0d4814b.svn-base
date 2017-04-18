package shlottery.gov.cn.lotterycenter.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.Jincai.JingCaiZuqiuInfo;
import shlottery.gov.cn.lotterycenter.bean.Jincai.LeaguesBean;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;
import shlottery.gov.cn.lotterycenter.bean.Jincai.TwoXuanYiBean;

/**
 * Created by yongchao.bei on 2016/6/23.
 */
public class JcDataUtils {


    public static HashMap<Integer, Boolean> getSpsStateMap(MatchsBean data) {
        if (null != data) {
            return data.getSpsStateMap();
        }
        return null;
    }

    public static Boolean isSpsSelected(MatchsBean data) {
        if (getSpsStateMap(data).containsValue(true))
            return true;
        return false;
    }

    //获得混合每一行的hashmap中选中的的id
    public static int getIndicator(HashMap<Integer, MatchsBean> data) {
        int mixId = 0;
        if (null != data) {
            if (data.get(Configure_JC.TAB_BQC) != null && JcDataUtils.isSpsSelected(data.get(Configure_JC.TAB_BQC))) {
                mixId = Configure_JC.TAB_BQC;
            } else if (data.get(Configure_JC.TAB_BF) != null && JcDataUtils.isSpsSelected(data.get(Configure_JC.TAB_BF))) {
                mixId = Configure_JC.TAB_BF;
            } else if (data.get(Configure_JC.TAB_SPF) != null && JcDataUtils.isSpsSelected(data.get(Configure_JC.TAB_SPF))) {
                mixId = Configure_JC.TAB_SPF;
            } else if (data.get(Configure_JC.TAB_RQSFP) != null && JcDataUtils.isSpsSelected(data.get(Configure_JC.TAB_RQSFP))) {
                mixId = Configure_JC.TAB_RQSFP;
            } else if (data.get(Configure_JC.TAB_ZJQ) != null && JcDataUtils.isSpsSelected(data.get(Configure_JC.TAB_ZJQ))) {
                mixId = Configure_JC.TAB_ZJQ;
            } else if (data.get(Configure_JC.TAB_SF) != null && JcDataUtils.isSpsSelected(data.get(Configure_JC.TAB_SF))) {
                mixId = Configure_JC.TAB_SF;
            } else if (data.get(Configure_JC.TAB_XSF) != null && JcDataUtils.isSpsSelected(data.get(Configure_JC.TAB_XSF))) {
                mixId = Configure_JC.TAB_XSF;
            } else if (data.get(Configure_JC.TAB_DXF) != null && JcDataUtils.isSpsSelected(data.get(Configure_JC.TAB_DXF))) {
                mixId = Configure_JC.TAB_DXF;
            } else if (data.get(Configure_JC.TAB_SFC) != null && JcDataUtils.isSpsSelected(data.get(Configure_JC.TAB_SFC))) {
                mixId = Configure_JC.TAB_SFC;
            } else {
                mixId = 0;
            }
        }
        return mixId;
    }

    //获得混合过关中最小的限制
    public static int getMinLimitType(List<HashMap<Integer, MatchsBean>> data) {
        int limitCount = 100;
        int tempCount = 0;
        int type;
        int minType = 0;
        for (int i = 0; i < data.size(); i++) {
            tempCount = 100;
            type = JcDataUtils.getIndicator(data.get(i));
            if (BaseApplication.getCurrentLotId().equals("jczq") && type == Configure_JC.TAB_BQC) {
                tempCount = 4;
                if (tempCount < limitCount) {
                    minType = type;
                }
                break;
            } else if (BaseApplication.getCurrentLotId().equals("jclq") && type == Configure_JC.TAB_SFC) {
                LogUtils.i("getMinLimitType type:" + type);
                tempCount = 4;
                if (tempCount < limitCount) {
                    minType = type;
                }
                break;
            } else if (BaseApplication.getCurrentLotId().equals("jczq") && type == Configure_JC.TAB_BF) {
                LogUtils.i("getMinLimitType type:" + type);
                tempCount = 4;
                if (tempCount < limitCount) {
                    minType = type;
                }
                break;
            } else if (BaseApplication.getCurrentLotId().equals("jczq") && type == Configure_JC.TAB_ZJQ) {
                LogUtils.i("getMinLimitType type:" + type);
                tempCount = 6;
                if (tempCount < limitCount) {
                    minType = type;
                }
            } else {
                if (tempCount < limitCount) {
                    LogUtils.i("getMinLimitType   replace:" );
                    minType = type;
                }
            }
        }
        LogUtils.i("getMinLimitType:" + minType);
        return minType;
    }

    public static void setSpsState(MatchsBean data, int position, Boolean flag) {
        if (null != data) {
            data.setSpsState(position, flag);
        }
    }

    public static void removeAllSelected(MatchsBean data) {
        if (null != data) {
            for (int i = 0; i < data.getSps().size(); i++) {
                setSpsState(data, i, false);
            }
        }
    }

    public static void removeListAllSelected(List<MatchsBean> datas) {
        if (null != datas) {
            for (int i = 0; i < datas.size(); i++) {
                for (int j = 0; j < datas.get(i).getSps().size(); j++) {
                    setSpsState(datas.get(i), j, false);
                }
            }
        }
    }

    public static void removeAllSelected(List<List<MatchsBean>> datas) {
        if (null != datas) {
            for (int i = 0; i < datas.size(); i++) {
                for (int j = 0; j < datas.get(i).size(); j++) {
                    for (int k = 0; k < datas.get(i).get(j).getSps().size(); k++) {
                        setSpsState(datas.get(i).get(j), k, false);
                    }
                }
            }
        }
    }

    public static boolean getSpsState(MatchsBean data, int position) {
        return data.getSpsState(position);
    }

    public static boolean compareData(MatchsBean data1, MatchsBean data2) {
        if (data1.getIssueNo().equals(data2.getIssueNo()) && (data1.getMatchNo() == data2.getMatchNo()) && data1.getLeagueId() == data2.getLeagueId()) {
            return true;
        }
        return false;
    }


    /**
     * 将数据分类,分为单关集合和过关集合
     */
    public static List<MatchsBean> getDanGuanData(String lotId, JingCaiZuqiuInfo.DataBean mDatas) {
        MatchsBean mMatchsBean;
        LogUtils.i("mDanGuanDatas mDatas" + mDatas);
        List<MatchsBean> mDanGuanDatas = new ArrayList<>();
        for (int i = 0; i < mDatas.getMatchs().size(); i++) {
            LogUtils.i("mDanGuanDatas 循环");
            for (int j = 0; j < mDatas.getMatchs().get(i).getSpList().size(); j++) {
                LogUtils.i("mDanGuanDatas 循环1");
                if (mDatas.getMatchs().get(i).getSpList().get(j).getLotId().equals(lotId)) {
                    LogUtils.i("mDanGuanDatas 循环2" + lotId + "::::::" + mDatas.getMatchs().get(i).getSpList().get(j).getLotId());
                    if ("dg".equals(mDatas.getMatchs().get(i).getSpList().get(j).getSaleType()) || "d".equals(mDatas.getMatchs().get(i).getSpList().get(j).getSaleType())) {
                        LogUtils.i("mDanGuanDatas 循环3");
                        mMatchsBean = new MatchsBean();
                        mMatchsBean.setHostRank(mDatas.getMatchs().get(i).getHostRank());
                        mMatchsBean.setIssueNo(mDatas.getMatchs().get(i).getIssueNo());
                        mMatchsBean.setLotId(mDatas.getMatchs().get(i).getSpList().get(j).getLotId());
                        mMatchsBean.setMatchDay(mDatas.getMatchs().get(i).getMatchDay());
                        mMatchsBean.setMatchNo(mDatas.getMatchs().get(i).getMatchNo());
                        mMatchsBean.setHostName(mDatas.getMatchs().get(i).getHostName());
                        mMatchsBean.setLeagueId(mDatas.getMatchs().get(i).getLeagueId());
                        mMatchsBean.setLeagueColor(mDatas.getMatchs().get(i).getLeagueColor());
                        mMatchsBean.setLeagueName(mDatas.getMatchs().get(i).getLeagueName());
                        mMatchsBean.setStopTime(mDatas.getMatchs().get(i).getStopTime());
                        mMatchsBean.setVisitName(mDatas.getMatchs().get(i).getVisitName());
                        mMatchsBean.setVisitRank(mDatas.getMatchs().get(i).getVisitRank());
                        mMatchsBean.setSps(mDatas.getMatchs().get(i).getSpList().get(j).getSps());
                        mMatchsBean.setWeekNo(mDatas.getMatchs().get(i).getWeekNo());
                        mMatchsBean.setMatchNo(mDatas.getMatchs().get(i).getMatchNo());
                        mMatchsBean.setHand(mDatas.getMatchs().get(i).getSpList().get(j).getHand());
                        mMatchsBean.setMatchId(mDatas.getMatchs().get(i).getMatchId());
                        mDanGuanDatas.add(mMatchsBean);
                        Log.e("mDanGuanDatas", mDanGuanDatas.size() + "");
                    }
                }
            }
        }

        Log.e("mDanGuanDatas return", mDanGuanDatas.size() + "");
        return mDanGuanDatas;
    }

    /**
     * 将数据分类,分为单关集合和过关集合,
     */
    public static List<MatchsBean> getGuoGuanData(String lotId, JingCaiZuqiuInfo.DataBean mDatas) {
        MatchsBean mMatchsBean;
        List<MatchsBean> mGuoguanDatas = new ArrayList<>();
        Log.e("mGuoguanDatas info1:", mDatas.getMatchs().size() + "");
        for (int i = 0; i < mDatas.getMatchs().size(); i++) {
            Log.e("mGuoguanDatas info:::::", mDatas.getMatchs().get(i).getSpList().size() + "");
            for (int j = 0; j < mDatas.getMatchs().get(i).getSpList().size(); j++) {
                if (mDatas.getMatchs().get(i).getSpList().get(j).getLotId().equals(lotId)) {
                    LogUtils.i("mGuoguanDatas info:::type:" + mDatas.getMatchs().get(i).getSpList().get(j).getSaleType());
                    if ("g".equals(mDatas.getMatchs().get(i).getSpList().get(j).getSaleType()) || "dg".equals(mDatas.getMatchs().get(i).getSpList().get(j).getSaleType())) {
                        mMatchsBean = new MatchsBean();
                        mMatchsBean.setHostRank(mDatas.getMatchs().get(i).getHostRank());
                        mMatchsBean.setIssueNo(mDatas.getMatchs().get(i).getIssueNo());
                        mMatchsBean.setLotId(mDatas.getMatchs().get(i).getSpList().get(j).getLotId());
                        mMatchsBean.setMatchDay(mDatas.getMatchs().get(i).getMatchDay());
                        mMatchsBean.setMatchNo(mDatas.getMatchs().get(i).getMatchNo());
                        mMatchsBean.setHostName(mDatas.getMatchs().get(i).getHostName());
                        mMatchsBean.setLeagueId(mDatas.getMatchs().get(i).getLeagueId());
                        mMatchsBean.setLeagueColor(mDatas.getMatchs().get(i).getLeagueColor());
                        mMatchsBean.setLeagueName(mDatas.getMatchs().get(i).getLeagueName());
                        mMatchsBean.setStopTime(mDatas.getMatchs().get(i).getStopTime());
                        mMatchsBean.setVisitName(mDatas.getMatchs().get(i).getVisitName());
                        mMatchsBean.setVisitRank(mDatas.getMatchs().get(i).getVisitRank());
                        mMatchsBean.setSps(mDatas.getMatchs().get(i).getSpList().get(j).getSps());
                        mMatchsBean.setWeekNo(mDatas.getMatchs().get(i).getWeekNo());
                        mMatchsBean.setMatchNo(mDatas.getMatchs().get(i).getMatchNo());
                        mMatchsBean.setHand(mDatas.getMatchs().get(i).getSpList().get(j).getHand());
                        mMatchsBean.setMatchId(mDatas.getMatchs().get(i).getMatchId());
                        mGuoguanDatas.add(mMatchsBean);
                        Log.e("mGuoguanDatas", mGuoguanDatas.size() + "");
                    }
                }
            }
        }
        return mGuoguanDatas;
    }


    //加载筛选数据
    public static TreeSet<LeaguesBean> loadFlitrateData(Object mDatas) {
        TreeSet<LeaguesBean> flitrateData = new TreeSet<>();
        if (mDatas instanceof JingCaiZuqiuInfo.DataBean) {
            JingCaiZuqiuInfo.DataBean parseData = (JingCaiZuqiuInfo.DataBean) mDatas;
            for (int i = 0; i < parseData.getLeagues().size(); i++) {
                LeaguesBean bean = new LeaguesBean();
                bean.setIsHot(parseData.getLeagues().get(i).getIsHot());
                bean.setLeagueId(parseData.getLeagues().get(i).getLeagueId());
                bean.setLeagueColor(parseData.getLeagues().get(i).getLeagueColor());
                bean.setLeagueName(parseData.getLeagues().get(i).getLeagueName());
                bean.setFlitrate(true);
                bean.setChecked(true);
                flitrateData.add(bean);
            }
        } else if (mDatas instanceof TwoXuanYiBean.DataBean) {
            TwoXuanYiBean.DataBean parseData = (TwoXuanYiBean.DataBean) mDatas;
            for (int i = 0; i < parseData.getLeagues().size(); i++) {
                LeaguesBean bean = new LeaguesBean();
                bean.setIsHot(parseData.getLeagues().get(i).getIsHot());
                bean.setLeagueId(parseData.getLeagues().get(i).getLeagueId());
                bean.setLeagueColor(parseData.getLeagues().get(i).getLeagueColor());
                bean.setLeagueName(parseData.getLeagues().get(i).getLeagueName());
                bean.setFlitrate(true);
                bean.setChecked(true);
                flitrateData.add(bean);
            }
        }
        LogUtils.i("flitrate loadover:" + flitrateData.size());
        return flitrateData;
    }

    public static List<HashMap<Integer, MatchsBean>> getSameMatch(List<MatchsBean> mDatas) {
        ArrayList<Integer> ids = new ArrayList<>();
        List<HashMap<Integer, MatchsBean>> sameMatchData = new ArrayList<>();
        HashMap<Integer, ArrayList<MatchsBean>> matchMap = new HashMap<>();
        ArrayList<MatchsBean> list = null;
        //将相同比赛都归为一类，添加在一个集合中
        for (int i = 0; i < mDatas.size(); i++) {
            if (!matchMap.containsKey(mDatas.get(i).getMatchNo())) {
                LogUtils.i("util same 新类型:" + mDatas.get(i).getMatchNo());
                matchMap.put(mDatas.get(i).getMatchNo(), new ArrayList<MatchsBean>());
                ids.add(mDatas.get(i).getMatchNo());
            }
            list = matchMap.get(mDatas.get(i).getMatchNo());
            list.add(mDatas.get(i));
            //根据比赛编号来归类比赛，同一个比赛的各种类型归为一类
            matchMap.put(mDatas.get(i).getMatchNo(), list);
        }
        for (int i = 0; i < ids.size(); i++) {
            HashMap<Integer, MatchsBean> map = new HashMap<>();
            //把归类好的数据再根据lotid转换为map集合
            for (int j = 0; j < matchMap.get(ids.get(i)).size(); j++) {
                switch (matchMap.get(ids.get(i)).get(j).getLotId()) {
                    case "jzxspf":
                        map.put(Configure_JC.TAB_RQSFP, matchMap.get(ids.get(i)).get(j));
                        break;
                    case "jzspf":
                        map.put(Configure_JC.TAB_SPF, matchMap.get(ids.get(i)).get(j));
                        break;
                    case "jzjqs":
                        map.put(Configure_JC.TAB_ZJQ, matchMap.get(ids.get(i)).get(j));
                        break;
                    case "jzbqc":
                        map.put(Configure_JC.TAB_BQC, matchMap.get(ids.get(i)).get(j));
                        break;
                    case "jzbf":
                        map.put(Configure_JC.TAB_BF, matchMap.get(ids.get(i)).get(j));
                        break;
                    default:
                        break;
                }
            }
            sameMatchData.add(map);
        }
        LogUtils.i("util same:" + sameMatchData.size() + ":::" + ids.size());
        return sameMatchData;
    }

    public static List<HashMap<Integer, MatchsBean>> getSameBaskMatch(List<MatchsBean> mDatas) {
        ArrayList<Integer> ids = new ArrayList<>();
        List<HashMap<Integer, MatchsBean>> sameMatchData = new ArrayList<>();
        HashMap<Integer, ArrayList<MatchsBean>> matchMap = new HashMap<>();
        ArrayList<MatchsBean> list = null;
        //将相同比赛都归为一类，添加在一个集合中
        for (int i = 0; i < mDatas.size(); i++) {
            if (!matchMap.containsKey(mDatas.get(i).getMatchNo())) {
                LogUtils.i("util same 新类型:" + mDatas.get(i).getMatchNo());
                matchMap.put(mDatas.get(i).getMatchNo(), new ArrayList<MatchsBean>());
                ids.add(mDatas.get(i).getMatchNo());
            }
            list = matchMap.get(mDatas.get(i).getMatchNo());
            list.add(mDatas.get(i));
            //根据比赛编号来归类比赛，同一个比赛的各种类型归为一类
            matchMap.put(mDatas.get(i).getMatchNo(), list);
        }
        for (int i = 0; i < ids.size(); i++) {
            HashMap<Integer, MatchsBean> map = new HashMap<>();
            //把归类好的数据再根据lotid转换为map集合
            for (int j = 0; j < matchMap.get(ids.get(i)).size(); j++) {
                switch (matchMap.get(ids.get(i)).get(j).getLotId()) {
                    case "jlsf":
                        map.put(Configure_JC.TAB_SF, matchMap.get(ids.get(i)).get(j));
                        break;
                    case "jlrsf":
                        map.put(Configure_JC.TAB_XSF, matchMap.get(ids.get(i)).get(j));
                        break;
                    case "jlfc":
                        map.put(Configure_JC.TAB_SFC, matchMap.get(ids.get(i)).get(j));
                        break;
                    case "jldx":
                        map.put(Configure_JC.TAB_DXF, matchMap.get(ids.get(i)).get(j));
                        break;
                    default:
                        break;
                }
            }
            sameMatchData.add(map);
        }
        LogUtils.i("util same:" + sameMatchData.size() + ":::" + ids.size());
        return sameMatchData;
    }

    public static List<List<MatchsBean>> getFlatrateData(TreeSet<LeaguesBean> leagues, List<List<MatchsBean>> data, List<List<MatchsBean>> flitrateData) {
        flitrateData.clear();
        if (null != data && data.size() > 0 && null != leagues) {
            LogUtils.i("flitrate size:" + leagues.size());
            for (int i = 0; i < data.size(); i++) {
                List<MatchsBean> list = new ArrayList<>();
                for (int j = 0; j < data.get(i).size(); j++) {
                    LogUtils.i("flitrate j...");
                    for (LeaguesBean bean : leagues) {
                        LogUtils.i("flitrate bean is null?" + bean.getLeagueName() + ":::" + data.get(i).get(j).getLeagueName() + bean.isFlitrate());
                        if (bean.getLeagueName().equals(data.get(i).get(j).getLeagueName()) && bean.isFlitrate()) {
                            list.add(data.get(i).get(j));
                            break;
                        }
                    }
                }
                LogUtils.i("fragment JcData childList:" + list.size());
                if (list.size() > 0) {
                    flitrateData.add(list);
                }
            }
        } else {
            flitrateData.addAll(data);
        }
        LogUtils.i("fragment JcData get初始数据:" +  "::" + data.size() + "过滤数据："  + "::" + "::" + flitrateData.size());
      //  LogUtils.i("fragment JcData get初始数据:" + flitrateData.get(0).size());
        return flitrateData;
    }

    public static List<List<HashMap<Integer, MatchsBean>>> getMixFlatrateData(TreeSet<LeaguesBean> leagues, List<List<HashMap<Integer, MatchsBean>>> data, List<List<HashMap<Integer, MatchsBean>>> flitrateData) {
        flitrateData.clear();
        if (null != data && data.size() > 0 && null != leagues) {
            LogUtils.i("flitrate size:" + leagues.size());
            for (int i = 0; i < data.size(); i++) {
                List<HashMap<Integer, MatchsBean>> list = new ArrayList<>();
                for (int j = 0; j < data.get(i).size(); j++) {
                    MatchsBean msgData = null;
                    if (null != data.get(i).get(j).get(Configure_JC.TAB_SPF)) {
                        msgData = data.get(i).get(j).get(Configure_JC.TAB_SPF);
                    } else if (null != data.get(i).get(j).get(Configure_JC.TAB_BF)) {
                        msgData = data.get(i).get(j).get(Configure_JC.TAB_BF);
                    } else if (null != data.get(i).get(j).get(Configure_JC.TAB_BQC)) {
                        msgData = data.get(i).get(j).get(Configure_JC.TAB_BQC);
                    } else if (null != data.get(i).get(j).get(Configure_JC.TAB_RQSFP)) {
                        msgData = data.get(i).get(j).get(Configure_JC.TAB_RQSFP);
                    } else if (null != data.get(i).get(j).get(Configure_JC.TAB_ZJQ)) {
                        msgData = data.get(i).get(j).get(Configure_JC.TAB_ZJQ);
                    }
                    LogUtils.i("flitrate j..." + j + "::" + (null == data.get(i).get(j)) + ":::" + msgData);
                    for (LeaguesBean bean : leagues) {

                        if (bean.getLeagueName().equals(msgData.getLeagueName()) && bean.isFlitrate()) {
                            list.add(data.get(i).get(j));
                            break;
                        }
                    }
                }
                if (list.size() > 0) {
                    flitrateData.add(list);
                }
            }
        } else {
            flitrateData.addAll(data);
        }
        LogUtils.i("fragment JcData get初始数据:" + (null == data) + "::" + data.size() + "过滤数据：" + (null == flitrateData) + "::" + "::" + flitrateData.size());
        return flitrateData;
    }


    public static List<List<HashMap<Integer, MatchsBean>>> getBaskMixFlatrateData(TreeSet<LeaguesBean> leagues, List<List<HashMap<Integer, MatchsBean>>> data, List<List<HashMap<Integer, MatchsBean>>> flitrateData) {
        flitrateData.clear();
        if (null != data && data.size() > 0 && null != leagues) {
            LogUtils.i("flitrate size:" + leagues.size() + "::::" + data.size());
            for (int i = 0; i < data.size(); i++) {
                List<HashMap<Integer, MatchsBean>> list = new ArrayList<>();
                for (int j = 0; j < data.get(i).size(); j++) {
                    MatchsBean msgData = null;
                    if (null != data.get(i).get(j).get(Configure_JC.TAB_SF)) {
                        msgData = data.get(i).get(j).get(Configure_JC.TAB_SF);
                    } else if (null != data.get(i).get(j).get(Configure_JC.TAB_XSF)) {
                        msgData = data.get(i).get(j).get(Configure_JC.TAB_XSF);
                    } else if (null != data.get(i).get(j).get(Configure_JC.TAB_SFC)) {
                        msgData = data.get(i).get(j).get(Configure_JC.TAB_SFC);
                    } else if (null != data.get(i).get(j).get(Configure_JC.TAB_DXF)) {
                        msgData = data.get(i).get(j).get(Configure_JC.TAB_DXF);
                    }
                    LogUtils.i("flitrate j..." + j + "::" + (null == data.get(i).get(j)) + ":::" + msgData);
                    for (LeaguesBean bean : leagues) {
                        if (bean.getLeagueName().equals(msgData.getLeagueName()) && bean.isFlitrate()) {
                            list.add(data.get(i).get(j));
                            break;
                        }
                    }
                }
                if (list.size() > 0) {
                    flitrateData.add(list);
                }
            }
        } else {
            flitrateData.addAll(data);
        }
        LogUtils.i("fragment JcData get初始数据:" + (null == data) + "::" + data.size() + "过滤数据：" + (null == flitrateData) + "::" + "::" + flitrateData.size());
        return flitrateData;
    }

    //获得订单列表中当前定胆的数量
    public static int getDanCount(List<MatchsBean> mDatas) {
        int count = 0;
        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).isDan()) {
                count++;
            }
        }
        return count;
    }

    public static void removeAllDan(List<MatchsBean> mDatas) {
        for (int i = 0; i < mDatas.size(); i++) {
            mDatas.get(i).setDan(false);
        }
    }

    public static void removeAllMixDan(List<HashMap<Integer, MatchsBean>> mDatas) {
        for (int i = 0; i < mDatas.size(); i++) {
            HashMap<Integer, MatchsBean> dataMap = mDatas.get(i);
            dataMap.get(getIndicator(dataMap)).setDan(false);
        }
    }
}
