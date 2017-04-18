package shlottery.gov.cn.lotterycenter.callback;

import android.os.AsyncTask;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import shlottery.gov.cn.lotterycenter.bean.BaseBean;
import shlottery.gov.cn.lotterycenter.utils.CalculateStage;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/21 17:34
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class CaculateAnsyTask extends AsyncTask {

    private CaculateResulCallback callBack;

    public CaculateAnsyTask(CaculateResulCallback callBack) {
        this.callBack = callBack;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        List<List<BaseBean>> list = new ArrayList<>();
        ArrayList<BigInteger> resultList = new ArrayList<>();
        for (int i = 0; i < objects.length; i++) {
            list.add((List<BaseBean>) objects[i]);
        }
        for (int i = 0; i < list.size(); i++) {
            List<List<BaseBean>> result;
            resultList.add(CalculateStage.getCaculateCount(list.get(i)));
        }
        return resultList;
    }

    @Override
    protected void onPostExecute(Object o) {
        ArrayList<BigInteger> resultList = (ArrayList<BigInteger>) o;
        BigInteger size1 = new BigInteger("0");
        BigInteger size2 = new BigInteger("0");
        if (resultList.size() >= 2) {
            size1 = resultList.get(0);
            size2 = resultList.get(1);
        }
        callBack.updateBounds(size1.multiply(size2));
        super.onPostExecute(o);
    }
}
