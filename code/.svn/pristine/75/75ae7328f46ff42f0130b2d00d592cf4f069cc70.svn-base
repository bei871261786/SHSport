package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.HashMap;
import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;
import shlottery.gov.cn.lotterycenter.utils.JcDataUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;


/**
 * Created by yongchao.bei on 2016/6/30.
 */
public abstract class BaseExpandableAdapter extends BaseExpandableListAdapter {

    public abstract void setData(List<List<MatchsBean>> mDatas);

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    protected String[] getItemInfo(MatchsBean data, String[] strings) {
        //每个checkbox状态集合
        HashMap<Integer, Boolean> spsMap = JcDataUtils.getSpsStateMap(data);
        LogUtils.i("baseWxpandAdapter spsMap:" + spsMap);
        String[] infos = new String[4];
        String strInfo;
        String hasSelected = 0 + "";//0是没有被选中的，1是有被选中的
        if (null == spsMap || !spsMap.containsValue(true)) {
            strInfo = ("点击展开投注区");
            hasSelected = 0 + "";
        } else {
            StringBuilder itemInfo = new StringBuilder();
            //每一个item上的信息
            LogUtils.i("dingdan spsMap长度：" + spsMap.size());
            for (int j = 0; j < spsMap.size(); j++) {
                if (spsMap.get(j)) {
                    LogUtils.i("bf 中啦");
                    itemInfo.append(strings[j] + " , ");
                }
            }
            strInfo = itemInfo.toString().substring(0, itemInfo.toString().lastIndexOf(" ,"));
            hasSelected = 1 + "";
        }
        infos[0] = strInfo;
        infos[1] = hasSelected;
        LogUtils.i("bf itemInfo:" + strInfo);
        return infos;
    }

    protected String[] getTipItemInfo(MatchsBean data, String[] strings, String[] tipStrings) {
        //每个checkbox状态集合
        HashMap<Integer, Boolean> spsMap = JcDataUtils.getSpsStateMap(data);
        LogUtils.i("baseWxpandAdapter spsMap:" + spsMap);
        String[] infos = new String[4];
        String strInfo;
//        String backColorcolor;
        //String textColor;
        String hasSelected = 0 + "";//0是没有被选中的，1是有被选中的
        if (null == spsMap || !spsMap.containsValue(true)) {
            strInfo = ("点击展开投注区");
//            backColorcolor = "#e9e9e9";
//            textColor = "#e11f1d";
            hasSelected = 0 + "";
        } else {
            StringBuilder itemInfo = new StringBuilder();
            //每一个item上的信息
            LogUtils.i("dingdan spsMap长度：" + spsMap.size());
            for (int j = 0; j < spsMap.size(); j++) {
                if (spsMap.get(j)) {
                    LogUtils.i("bf 中啦");
                    itemInfo.append(tipStrings[j] + strings[j] + " , ");
                }
            }
            strInfo = itemInfo.toString().substring(0, itemInfo.toString().lastIndexOf(" ,"));
//            backColorcolor = "#df1f1d";
//            textColor = "#FFFFFFFF";
            hasSelected = 1 + "";
        }
        infos[0] = strInfo;
//        infos[1] = backColorcolor;
//        infos[2] = textColor;
        infos[1] = hasSelected;
        LogUtils.i("bf itemInfo:" + strInfo);
        return infos;
    }

    protected String[] getMixItemInfo(HashMap<Integer, MatchsBean> data) {
        String[] infos = new String[3];
        String strInfo = "";

        MatchsBean spfData = data.get(Configure_JC.TAB_SPF);
        String[] spfInfos = getItemInfo(spfData, Configure_JC.FB_DIALOG_SPF);

        MatchsBean rqspfData = data.get(Configure_JC.TAB_RQSFP);
        String[] rqspfInfos = getItemInfo(rqspfData, Configure_JC.FB_DIALOG_XSPF);

        MatchsBean bqcData = data.get(Configure_JC.TAB_BQC);
        String[] bqcInfos = getItemInfo(bqcData, Configure_JC.FB_DIALOG_BQC);

        MatchsBean bfData = data.get(Configure_JC.TAB_BF);
        String[] bfInfos = getItemInfo(bfData, Configure_JC.FB_DIALOG_BF);

        MatchsBean zjqData = data.get(Configure_JC.TAB_ZJQ);
        String[] zjqInfos = getItemInfo(zjqData, Configure_JC.FB_DIALOG_ZJQ);


        if (compareStr("点击展开投注区", spfInfos[0]) && compareStr("点击展开投注区", rqspfInfos[0]) &&
                compareStr("点击展开投注区", bqcInfos[0]) &&
                compareStr("点击展开投注区", bfInfos[0]) && compareStr("点击展开投注区", zjqInfos[0])) {
            strInfo = ("点击展开投注区");

        } else {
            LogUtils.i("mix getitemInfo infoStr:" + spfInfos[0] + "::" + rqspfInfos[0] + "::" + bqcInfos[0] + "::" + bfInfos[0] + "::" + zjqInfos[0]);

            if (!compareStr("点击展开投注区", spfInfos[0])) {
                strInfo += spfInfos[0];
            }
            if (!compareStr("点击展开投注区", rqspfInfos[0])) {
                strInfo += rqspfInfos[0];
            }
            if (!compareStr("点击展开投注区", bqcInfos[0])) {
                strInfo += bqcInfos[0];
            }
            if (!compareStr("点击展开投注区", bfInfos[0])) {
                strInfo += bfInfos[0];
            }
            if (!compareStr("点击展开投注区", zjqInfos[0])) {
                strInfo += zjqInfos[0];
            }
        }
        infos[0] = strInfo;
        LogUtils.i("mix getitemInfo:" + strInfo);
        return infos;
    }

    protected String[] getBaskMixItemInfo(HashMap<Integer, MatchsBean> data) {
        String[] infos = new String[3];
        String strInfo = "";

        MatchsBean sfData = data.get(Configure_JC.TAB_SF);
        String[] sfInfos = getItemInfo(sfData, Configure_JC.FB_DIALOG_SF);

        MatchsBean xsffData = data.get(Configure_JC.TAB_XSF);
        String[] xsfInfos = getItemInfo(xsffData, Configure_JC.FB_DIALOG_XSF);

        MatchsBean dxData = data.get(Configure_JC.TAB_DXF);
        String[] dxInfos = getItemInfo(dxData, Configure_JC.FB_DIALOG_DX);

        MatchsBean sfcData = data.get(Configure_JC.TAB_SFC);
        String[] sfcInfos = getItemInfo(sfcData, Configure_JC.FB_DIALOG_SFC);


        if (compareStr("点击展开投注区", sfInfos[0]) && compareStr("点击展开投注区", xsfInfos[0]) &&
                compareStr("点击展开投注区", dxInfos[0]) &&
                compareStr("点击展开投注区", sfcInfos[0])) {
            strInfo = ("点击展开投注区");

        } else {
            LogUtils.i("mix getitemInfo infoStr:" + sfInfos[0] + "::" + xsfInfos[0] + "::" + dxInfos[0] + "::" + sfcInfos[0]);

            if (!compareStr("点击展开投注区", sfInfos[0])) {
                strInfo += sfInfos[0];
            }
            if (!compareStr("点击展开投注区", xsfInfos[0])) {
                strInfo += xsfInfos[0];
            }
            if (!compareStr("点击展开投注区", dxInfos[0])) {
                strInfo += dxInfos[0];
            }
            if (!compareStr("点击展开投注区", sfcInfos[0])) {
                strInfo += sfcInfos[0];
            }
        }
        infos[0] = strInfo;
        LogUtils.i("mix getitemInfo:" + strInfo);
        return infos;
    }

    private boolean compareStr(String str1, String str2) {
        if (str1 != null && str2 != null) {
            if (str1.equals(str2)) {
                return true;
            }
        }
        return false;
    }

}
