package shlottery.gov.cn.lotterycenter.utils;

import android.os.AsyncTask;

import shlottery.gov.cn.lotterycenter.callback.IDataCallBack;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-03-0003 13:21
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class AsyncTaskUtils {
    public static <T> void doAsync(final IDataCallBack<T> callBack) {
        new AsyncTask<Void, Void, T>() {

            protected void onPreExecute() {
                callBack.onTaskBefore();
            }

            @Override
            protected T doInBackground(Void... params) {
                // TODO
                return callBack.onTasking(params);
            }

            protected void onPostExecute(T result) {
                callBack.onTaskAfter(result);
            }

        }.execute();
    }

}
