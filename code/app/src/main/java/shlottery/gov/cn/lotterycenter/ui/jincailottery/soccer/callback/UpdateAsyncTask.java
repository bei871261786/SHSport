package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import org.apache.http.NameValuePair;

import java.util.List;
import shlottery.gov.cn.lotterycenter.protool.BaseProtocol;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * Created by yongchao.bei on 2016/7/19.
 */
public class UpdateAsyncTask extends AsyncTask {

    private BaseProtocol protocol;
    private UpdataCallback callback;
    private boolean isJustNet = false;

    public UpdateAsyncTask(Context context, BaseProtocol protocol) {
        this.protocol = protocol;
        this.callback = (UpdataCallback) context;
    }

    public UpdateAsyncTask(Fragment fragment, BaseProtocol protocol, boolean isJustNet) {
        this.protocol = protocol;
        this.callback = (UpdataCallback) fragment;
        this.isJustNet = isJustNet;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        List<NameValuePair> param = null;
        if (null != params && params.length > 0) {
            param = (List<NameValuePair>) params[0];
        }
        LogUtils.i("asyntask doinback params:" + param + "::" + protocol);
//        Object o = protocol.load(0, param, isJustNet);
        Object o =null;
        if (null != o) {
            Object data = o;
            return data;
        } else
            return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        LogUtils.i("asyntask doinback onpost:" + o);
        callback.getResultFromAysntask(o);
    }
}
