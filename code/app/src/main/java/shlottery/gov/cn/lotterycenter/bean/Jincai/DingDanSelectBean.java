package shlottery.gov.cn.lotterycenter.bean.Jincai;

import java.util.ArrayList;
import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * Created by booob on 2016-07-04-0004.
 */

//订单中过关方式选择的javabean
public class DingDanSelectBean {
    private List<Boolean> mSelectMoreChuanLian = new ArrayList<>();
    private List<Boolean> mSelectChuanLian = new ArrayList<>();
    private int mMoreParentPosition;

    public int getmMoreParentPosition() {
        return mMoreParentPosition;
    }

    public void setmMoreParentPosition(int mMoreParentPosition, int mJingcaiType) {
        if (BaseApplication.getCurrentLotId().equals("jczq") && (mJingcaiType == Configure_JC.TAB_BF || mJingcaiType == Configure_JC.TAB_BQC) && mMoreParentPosition == 1) {
            this.mMoreParentPosition = 1;
        } else if (BaseApplication.getCurrentLotId().equals("jczq") && (mJingcaiType == Configure_JC.TAB_BF || mJingcaiType == Configure_JC.TAB_BQC) && mMoreParentPosition > 1) {
            this.mMoreParentPosition = -1;
        } else if (BaseApplication.getCurrentLotId().equals("jclq") && (mJingcaiType == Configure_JC.TAB_SFC) && mMoreParentPosition == 1) {
            this.mMoreParentPosition = 1;
        } else if (BaseApplication.getCurrentLotId().equals("jclq") && (mJingcaiType == Configure_JC.TAB_SFC) && mMoreParentPosition > 1) {
            this.mMoreParentPosition = -1;
        } else if (BaseApplication.getCurrentLotId().equals("jczq") && (mJingcaiType == Configure_JC.TAB_ZJQ) && mMoreParentPosition == 3) {
            this.mMoreParentPosition = 3;
        } else if (BaseApplication.getCurrentLotId().equals("jczq") && (mJingcaiType == Configure_JC.TAB_ZJQ) && mMoreParentPosition > 3) {
            this.mMoreParentPosition = -1;
        } else if (mMoreParentPosition > 5) {
            this.mMoreParentPosition = 5;
        } else {
            this.mMoreParentPosition = mMoreParentPosition;
        }
        LogUtils.i("setmMoreParentPosition:" + BaseApplication.getCurrentLotId() + "::::" + mMoreParentPosition + "::::" + mJingcaiType + "::::" + this.getmMoreParentPosition());
    }

    public List<Boolean> getmSelectChuanLian() {
        return mSelectChuanLian;
    }

    public void setmSelectChuanLian(List<Boolean> mSelectChuanLian) {
        this.mSelectChuanLian = mSelectChuanLian;
    }

    public List<Boolean> getmSelectMoreChuanLian() {
        return mSelectMoreChuanLian;
    }

    public void setmSelectMoreChuanLian(List<Boolean> mSelectMoreChuanLian) {
        LogUtils.i("setmSelectMoreChuanLian size:" + mSelectMoreChuanLian.size());
        this.mSelectMoreChuanLian = mSelectMoreChuanLian;
    }
}
