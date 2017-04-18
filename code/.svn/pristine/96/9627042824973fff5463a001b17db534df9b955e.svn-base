package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.activity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.bean.Jincai.DingdanBean;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter.SwitchDingdanListViewAdapter;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.confirg.DataPool;
import shlottery.gov.cn.lotterycenter.utils.JcDataUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.SoccerDialogUtils;

public class FB_DingDanActivity extends FB_DingDanBaseActivity {

    protected void init() {
        mIntent = getIntent();
        mSubType = mIntent.getIntExtra("mSubType", 0);
        mType = mIntent.getIntExtra("mType", Configure_JC.TYPE_SOCCER);
        mTitleName = mIntent.getStringExtra("mTitleName");
        isNormal = true;
        DingdanBean dingdanBean = new DingdanBean();
        DataPool.setPrice(dingdanBean.getPRICE());
        DataPool.setMultiple(dingdanBean.getMultiple());
        mDingDanDatas = (List<MatchsBean>) mIntent.getSerializableExtra("mSelectHashMap");
        LogUtils.i("fbDingdanactivity mType" + mType + ":::" + mSubType);
        mGuoGuanFangShi = mIntent.getStringExtra("guoguanfangshi");
        if (mType == Configure_JC.TYPE_SOCCER) {
            if (mGuoGuanFangShi.equals("danguan")) {
                mDingDanAdapter = SwitchDingdanListViewAdapter.createSoccerDingdanAdapter(mSubType, this, mDingDanDatas, handler, true);//订单listview适配器
            } else {
                mDingDanAdapter = SwitchDingdanListViewAdapter.createSoccerDingdanAdapter(mSubType, this, mDingDanDatas, handler, false);//订单listview适配器
            }

        } else if (mType == Configure_JC.TYPE_BASKETBALL) {
            if (mGuoGuanFangShi.equals("danguan")) {
                mDingDanAdapter = SwitchDingdanListViewAdapter.createBaskDingdanAdapter(mSubType, this, mDingDanDatas, handler, true);//订单listview适配器
            } else {
                mDingDanAdapter = SwitchDingdanListViewAdapter.createBaskDingdanAdapter(mSubType, this, mDingDanDatas, handler, false);//订单listview适配器
            }
        }
        //保存订单数据
        saveDingdanInfo(mDingDanDatas);
        mDingdanLength = mDingdans.size();
        LogUtils.i("dingdan datas:" + mDingDanDatas);

        mDialogUtil = new SoccerDialogUtils();
        if (null != mDingDanDatas && null != mDingDanDatas.get(0)) {
            mIssueNo = mDingDanDatas.get(0).getIssueNo();
        }
        mJcType = mSubType;
        mMinLimitType = mSubType;
        LogUtils.i(mDingDanDatas.size() + "mDingDanDatas长度" + mDingDanDatas.get(0).getHostName());
        //注册EventBus
        EventBus.getDefault().register(this);

        SoccerDialogUtils.removeDialog();
    }

    //去掉因为订单中点击而取消的数据
    protected void chooseMatchsBeans() {
        mDingdanLength = mDingDanDatas.size();
        for (int i = 0; i < mDingDanDatas.size(); i++) {
            if (null != JcDataUtils.getSpsStateMap(mDingDanDatas.get(i)) && !JcDataUtils.getSpsStateMap(mDingDanDatas.get(i)).containsValue(true)) {
                if (mSubType == Configure_JC.TAB_SPF || mSubType == Configure_JC.TAB_RQSFP || (mSubType == Configure_JC.TAB_DXF && mType == Configure_JC.TYPE_BASKETBALL)) {
                    //JcDataUtils.removeAllDan(mDingDanDatas);
                } else {
                    mDingDanDatas.remove(i);
                }
                mDingdanLength--;
            }
        }
    }

    @Override
    protected String getBaidutitle() {
        return "竞彩注单保存";
    }

}
