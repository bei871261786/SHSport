package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;

import java.util.List;

import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.http.HttpHelper;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * Created by yongchao.bei on 2016/8/8.
 */
public class RequestAsyncTask extends AsyncTask {
    Context context;
    RequestCallback callback;
    int id;

    public RequestAsyncTask(Context context) {
        this.context = context;
        this.callback = (RequestCallback) context;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        HttpHelper.HttpResult result = null;
        LogUtils.i("payment params doinbackground:" + (params == null) + params.length);
        String json = null;
        if (null != params && params.length > 1) {
            String url = (String) params[0];
            List<NameValuePair> param = (List<NameValuePair>) params[1];
            LogUtils.i("payment params:" + url + param);
            result = HttpHelper.post(url, param);
            if (null != result) {
                json = result.getString();
            }
        }
        LogUtils.i("payment params json:" + json);
        return json;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        callback.getRequestResultFromAsyntask(o);
    }
}
