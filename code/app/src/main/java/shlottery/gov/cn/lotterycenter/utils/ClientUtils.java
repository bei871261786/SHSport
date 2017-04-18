package shlottery.gov.cn.lotterycenter.utils;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.orhanobut.logger.Logger;

import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.ClientBean;
import shlottery.gov.cn.lotterycenter.bean.VcodeBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-22-0022 15:17
 * 描    述：client 失效的情况下可以调用此方法
 * 修订历史：
 * ================================================
 */

public class ClientUtils {

    public static ClientBean mClientBean;
    //异步获取Client
    public static String mClient;

    public static String startGetClient() {
        LogUtils.i("splashActivity startGetClient");

        OkGo.get(UrlManager.url + "?" + UrlManager.getParames()).connTimeOut(5000).cacheMode(CacheMode.NO_CACHE).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                mClientBean = gson.fromJson(s, ClientBean.class);
                LogUtils.i("startGetClient gson:"+s);
                if (null != mClientBean) {
                    if (mClientBean.getRet() == 100) {
//                        UIUtils.showToastSafe("请刷新重试");
                        PrefUtils.setString(BaseApplication.getApplication(), "Client", mClientBean.getData().getClient());
                        mClient = mClientBean.getData().getClient();
                    } else {
                        ToastUtils.showShort(BaseApplication.getApplication(), mClientBean.getMsg());
                    }
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                ToastUtils.showShort(BaseApplication.getApplication(), "网络异常,请检查网络");
            }


        });
        return mClient;
    }

    public static void regPushToken() {
        String pushToken = (String) SharedPreferencesUtils.get(BaseApplication.getApplication(), "registrationId", "");
        LogUtils.i("regPushToken pushToken : "+pushToken);
        if (pushToken != null) {
            OkGo.get(UrlManager.regPushToken(pushToken)).connTimeOut(5000).cacheMode(CacheMode.NO_CACHE).execute(new StringCallback() {
                @Override
                public void onSuccess(String s, Call call, Response response) {
                    Gson gson = new Gson();
                    VcodeBean vcodeBean = gson.fromJson(s, VcodeBean.class);
                    if (vcodeBean != null) {
                        if (vcodeBean.getRet() == 100) {
                            Logger.e("pushToken保存成功");
                        } else {
                            Logger.e(vcodeBean.getMsg());
                        }
                    }
                }
            });
        } else {
            Logger.e("激光注册id没有获取到");
        }
    }
}
