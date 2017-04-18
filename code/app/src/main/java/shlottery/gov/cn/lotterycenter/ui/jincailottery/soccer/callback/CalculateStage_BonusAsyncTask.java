package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;

import java.util.List;

import shlottery.gov.cn.lotterycenter.bean.Jincai.DingdanItemBean;
import shlottery.gov.cn.lotterycenter.utils.JincaiCaculateStage;
import shlottery.gov.cn.lotterycenter.utils.JincaiCalculateBonu;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * Created by yongchao.bei on 2016/7/26.
 * 执行计算奖金和注数的异步任务
 */
public class CalculateStage_BonusAsyncTask extends AsyncTask {
    UpdataCallback callback;

    public CalculateStage_BonusAsyncTask(Context context) {
        callback = (UpdataCallback) context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        if (null != params[0] && null != params[1]) {
            List<DingdanItemBean> mDingdans = (List<DingdanItemBean>) params[0];
            List<String> mChuanlianList = (List<String>) params[1];
            String mGuoguanfangshi = (String) params[2];
            int count = 0;
            String bonus = "";
            if ("guoguan".equals(mGuoguanfangshi) && null != mChuanlianList) {
                LogUtils.i("doInBackground mDingdans:" + mDingdans);
                int danCount = 0;
                for (int i = 0; i < mDingdans.size(); i++) {
                    if (mDingdans.get(i).isDan()) {
                        danCount++;
                    }
                }
                LogUtils.i("doInBackground mDingdans:" + danCount);
                count = JincaiCaculateStage.getCalculateStage(mDingdans, mChuanlianList);
                bonus = JincaiCalculateBonu.getMaxCalculateBouns(mDingdans, mChuanlianList);
            } else if ("danguan".equals(mGuoguanfangshi)) {
                count = JincaiCaculateStage.getDanCalculateStage(mDingdans);
                bonus = JincaiCalculateBonu.getDanMaxCalculateBonus(mDingdans);
            }
            Message msg = new Message();
            msg.arg1 = count;
            msg.obj = bonus;
            return msg;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        // Message msg = (Message) o;
        //int count = msg.arg1;
        //double bonus = (double) msg.obj;
        //LogUtils.i("asyntask count :" + count + "   bonus:" + bonus);
        callback.getResultFromAysntask(o);
    }
}
