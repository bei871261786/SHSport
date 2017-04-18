package shlottery.gov.cn.lotterycenter.ui.fragment;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import shlottery.gov.cn.lotterycenter.Base.BaseFragment2;
import shlottery.gov.cn.lotterycenter.bean.BaseBean;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/12/3 14:31
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public abstract class IBDetailBaseFragment extends BaseFragment2 {
    protected String mLotid = "";
    protected String mIssueNo = "";


    public void updateByIssueno(String mIssueNo) {
        Logger.i("updateBrIssueno ");
        this.mIssueNo = mIssueNo;
        beginLoad();
    }

    protected void initNumberData(String code, ArrayList<BaseBean> adapterData) {
        adapterData.clear();
        LogUtils.i("setAdapterData code:" + code);
        if (code.contains("#")) {
            String redData = code.substring(0, code.indexOf('#'));
            LogUtils.i("setAdapterData red:" + redData);
            String blueData = code.substring(code.indexOf('#') + 1, code.length());
            LogUtils.i("setAdapterData blue::::" + blueData);
            String[] red = redData.split(",");
            String[] blue = blueData.split(",");
            for (int i = 0; i < red.length; i++) {
                BaseBean bean = new BaseBean();
                bean.setMsg(red[i]);
                bean.setRed(true);
                adapterData.add(bean);
            }
            for (int i = 0; i < blue.length; i++) {
                BaseBean bean = new BaseBean();
                bean.setMsg(blue[i]);
                bean.setRed(false);
                adapterData.add(bean);
            }
        } else {
            String[] codeData = code.split(",");
            for (int i = 0; i < codeData.length; i++) {
                BaseBean bean = new BaseBean();
                bean.setMsg(codeData[i]);
                bean.setRed(true);
                adapterData.add(bean);
            }
        }
        LogUtils.i("setAdapterData dataSize:" + adapterData.size());
    }


}
