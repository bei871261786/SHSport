package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.activity;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.bean.Jincai.DingdanBean;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter.DingDanMixListViewAdapter;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter.DingDanMixListViewBasketAdapter;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.confirg.DataPool;
import shlottery.gov.cn.lotterycenter.utils.JcDataUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.SoccerDialogUtils;

public class FB_DingDanActivity_Mix extends FB_DingDanBaseActivity {

    protected void init() {
        mIntent = getIntent();
        mSubType = mIntent.getIntExtra("mJingcaiType", 0);
        mTitleName = mIntent.getStringExtra("mTitleName");
        mType = mIntent.getIntExtra("mType", Configure_JC.TYPE_SOCCER);
        isNormal = false;
        DingdanBean dingdanBean = new DingdanBean();
        DataPool.setPrice(dingdanBean.getPRICE());
        DataPool.setMultiple(dingdanBean.getMultiple());
        mJcType = mSubType;
        mDingDanMixDatas = (List<HashMap<Integer, MatchsBean>>) mIntent.getSerializableExtra("mSelectHashMap");
        mMinLimitType = JcDataUtils.getMinLimitType(mDingDanMixDatas);
        LogUtils.i("minLimitType:"+mMinLimitType);
        if(mType== Configure_JC.TYPE_BASKETBALL)
        {
            mDingDanAdapter = new DingDanMixListViewBasketAdapter(this, mDingDanMixDatas, handler, mType);
        }
        else {
            mDingDanAdapter = new DingDanMixListViewAdapter(this, mDingDanMixDatas, handler, mType);
        }

        saveMixDingdanInfo(mDingDanMixDatas, mDingDanAdapter.getDanList());
        mDingdanLength = mDingdans.size();
        mGuoGuanFangShi = mIntent.getStringExtra("guoguanfangshi");
        mDialogUtil = new SoccerDialogUtils();
        if (null != mDingDanMixDatas && null != mDingDanMixDatas.get(0)) {
            int indicator = JcDataUtils.getIndicator(mDingDanMixDatas.get(0));
            mIssueNo = mDingDanMixDatas.get(0).get(indicator).getIssueNo();
        }
        //注册EventBus
        EventBus.getDefault().register(this);
        SoccerDialogUtils.removeDialog();
    }

    //去掉因为订单中点击而取消的数据
    public void chooseMatchsBeans() {
        mDingdanLength = mDingDanMixDatas.size();
        for (int i = 0; i < mDingDanMixDatas.size(); i++) {
            if ((null == JcDataUtils.getSpsStateMap(mDingDanMixDatas.get(i).get(Configure_JC.TAB_RQSFP)) || !JcDataUtils.getSpsStateMap(mDingDanMixDatas.get(i).get(Configure_JC.TAB_RQSFP)).containsValue(true))
                    && (null == JcDataUtils.getSpsStateMap(mDingDanMixDatas.get(i).get(Configure_JC.TAB_SPF)) || !JcDataUtils.getSpsStateMap(mDingDanMixDatas.get(i).get(Configure_JC.TAB_SPF)).containsValue(true))
                    && (null == JcDataUtils.getSpsStateMap(mDingDanMixDatas.get(i).get(Configure_JC.TAB_BF)) || !JcDataUtils.getSpsStateMap(mDingDanMixDatas.get(i).get(Configure_JC.TAB_BF)).containsValue(true))
                    && (null == JcDataUtils.getSpsStateMap(mDingDanMixDatas.get(i).get(Configure_JC.TAB_BQC)) || !JcDataUtils.getSpsStateMap(mDingDanMixDatas.get(i).get(Configure_JC.TAB_BQC)).containsValue(true))
                    && (null == JcDataUtils.getSpsStateMap(mDingDanMixDatas.get(i).get(Configure_JC.TAB_ZJQ)) || !JcDataUtils.getSpsStateMap(mDingDanMixDatas.get(i).get(Configure_JC.TAB_ZJQ)).containsValue(true))) {
                LogUtils.i("dingdan 移除");
                mDingDanMixDatas.remove(i);
                mDingdanLength--;
            }
        }
    }
    @Override
    protected String getBaidutitle() {
        return "竞彩注单保存";
    }

}
