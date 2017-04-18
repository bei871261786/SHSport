package shlottery.gov.cn.lotterycenter.callback;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;

import java.util.HashMap;

import shlottery.gov.cn.lotterycenter.utils.LogUtils;
/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/4 11:42
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class HttpHelper {
    public void get(String url, Object tag, AbsCallback callback) {
        OkGo.get(url).tag(tag).connTimeOut(5000).execute(callback);//网络连接超时时间5分钟
    }

    public void post(String url, Object tag, HashMap params, AbsCallback callBack) {
        LogUtils.i("Httphelper post:" + params.size());
        OkGo.post(url).tag(tag).params(params).connTimeOut(5000).execute(callBack);//网络连接超时时间5分钟
    }
}
