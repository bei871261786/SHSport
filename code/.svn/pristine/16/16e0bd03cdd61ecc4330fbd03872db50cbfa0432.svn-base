package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import shlottery.gov.cn.lotterycenter.bean.Jincai.DingdanItemBean;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.activity.FB_DingDanActivity;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.activity.FB_DingDanActivity_Mix;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.activity.FB_DingDanBaseActivity;
import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.utils.JcDataUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;


/**
 * Created by yongchao.bei on 2016/6/23.
 */
public abstract class DingdanBaseAdapter extends BaseAdapter {

    protected FB_DingDanBaseActivity activity;
    protected int mDanShouldCunt = 0;
    protected boolean mIsCleanDan = false;
    protected int mDanCunt;
    protected ArrayList<Boolean> mDanList = new ArrayList<>();

    public boolean ismIsCleanDan() {
        return mIsCleanDan;
    }

    public ArrayList<Boolean> getDanList() {
        return mDanList;
    }

    public int getDanCount() {
        return mDanCunt;
    }

    public void setmIsCleanDan(boolean mIsCleanDan) {
        this.mIsCleanDan = mIsCleanDan;
        LogUtils.i("adapter spf dan  setmIsCleanDan:" + this.mIsCleanDan);
    }

    DingdanBaseAdapter(Context context) {
        LogUtils.i("dingdan baseada context为空么" + (context == null));
        if (context instanceof FB_DingDanActivity) {
            LogUtils.i("dingdan baseada 属于dingdan activity");
            activity = (FB_DingDanActivity) context;
        }
        if (context instanceof FB_DingDanActivity_Mix) {
            LogUtils.i("dingdan baseada 属于dingdan Mix activity");
            activity = (FB_DingDanBaseActivity) context;
        }
    }

    public void setDanCount(int mDanShouldCunt) {
        this.mDanShouldCunt = mDanShouldCunt;
    }

    protected DingdanItemBean getDingdanInfo(int position) {
        if (null != activity.getmDingdans()) {
            LogUtils.i("dingdanbaseAdapter info:"+activity.getmDingdans().get(position).getLotId());
            return activity.getmDingdans().get(position);
        }
        return null;
    }

    protected ArrayList<DingdanItemBean> getDingdanInfo() {
        return activity.getmDingdans();

    }

    protected String[] getSuitableItemInfo(String lotId) {
        String[] infos = null;
        switch (lotId) {
            case "jzxspf":
                infos = Configure_JC.FB_DIALOG_XSPF;
                break;
            case "jzspf":
                infos = Configure_JC.FB_DIALOG_SPF;
                break;
            case "jzjqs":
                infos = Configure_JC.FB_DIALOG_ZJQ;
                break;
            case "jzbf":
                infos = Configure_JC.FB_DIALOG_BF;
                break;
            case "jzbqc":
                infos = Configure_JC.FB_DIALOG_BQC;
                break;
            default:
                break;
        }
        return infos;
    }

    protected void initDanCount(List<MatchsBean> mDatas) {
        mDanCunt = JcDataUtils.getDanCount(mDatas);
    }

}
