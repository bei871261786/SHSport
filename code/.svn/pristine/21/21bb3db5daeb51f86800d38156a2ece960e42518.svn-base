package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter;

import android.content.Context;
import android.os.Handler;

import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;

/**
 * Created by yongchao.bei on 2016/6/22.
 */
public class SwitchDingdanListViewAdapter {

    public static DingdanBaseAdapter createSoccerDingdanAdapter(int position, Context context, List<MatchsBean> mDingDanDatas, Handler handler,boolean isDanguan) {
        DingdanBaseAdapter mDingDanAdapter = null;
        switch (position) {
            case Configure_JC.TAB_BF:
                mDingDanAdapter = new DingdanBaseDialogAdapter(context, mDingDanDatas, handler, Configure_JC.TYPE_SOCCER, Configure_JC.TAB_BF,isDanguan);
                break;
            case Configure_JC.TAB_BQC:
                mDingDanAdapter = new DingdanBaseDialogAdapter(context, mDingDanDatas, handler, Configure_JC.TYPE_SOCCER, Configure_JC.TAB_BQC,isDanguan);
                break;
            case Configure_JC.TAB_RQSFP:
                mDingDanAdapter = new DingDanSpfListViewAdapter(context, mDingDanDatas, handler,isDanguan);
                break;
            case Configure_JC.TAB_SPF:
                mDingDanAdapter = new DingDanSpfListViewAdapter(context, mDingDanDatas, handler,isDanguan);
                break;
            case Configure_JC.TAB_ZJQ:
                mDingDanAdapter = new DingdanBaseDialogAdapter(context, mDingDanDatas, handler, Configure_JC.TYPE_SOCCER, Configure_JC.TAB_ZJQ,isDanguan);
            default:
                break;
        }
        return mDingDanAdapter;
    }

    public static DingdanBaseAdapter createBaskDingdanAdapter(int position, Context context, List<MatchsBean> mDingDanDatas, Handler handler,boolean isDanguan) {
        DingdanBaseAdapter mDingDanAdapter = null;
        switch (position) {
            case Configure_JC.TAB_SF:
                mDingDanAdapter = new DingDanSfListViewAdapter(context, mDingDanDatas, handler,isDanguan);
                break;
            case Configure_JC.TAB_SFC:
                mDingDanAdapter = new DingdanBasketBaseDialogAdapter(context, mDingDanDatas, handler, Configure_JC.TYPE_BASKETBALL, Configure_JC.TAB_SFC,isDanguan);
                break;
            case Configure_JC.TAB_XSF:
                mDingDanAdapter = new DingDanXsfListViewAdapter(context, mDingDanDatas, handler,isDanguan);
                break;
            case Configure_JC.TAB_DXF:
                mDingDanAdapter = new DingDanDxListViewAdapter(context, mDingDanDatas, handler,isDanguan);
                break;
        }
        return mDingDanAdapter;
    }
}
