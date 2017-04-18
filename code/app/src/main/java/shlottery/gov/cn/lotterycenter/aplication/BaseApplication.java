package shlottery.gov.cn.lotterycenter.aplication;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.Properties;

import cn.jpush.android.api.JPushInterface;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.SharedPreferencesUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-12-0012 上午 11:54
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class BaseApplication extends Application {

    // 获取到主线程的上下文
    private static BaseApplication mContext           = null;
    // 获取到主线程的handler
    private static Handler         mMainThreadHandler = null;

    // 获取到主线程
    private static Thread mMainThread = null;
    // 获取到主线程的id
    private static int mMainThreadId;
    // 获取到主线程的looper
    private static Looper             mMainThreadLooper  = null;
    public static  WalkingRouteResult walkingRouteResult = null;

    public static String  registrationId      = "";
    public static boolean isMainActivityStart = false;//主activity是否启动

    @Override
    public void onCreate() {
        super.onCreate();
        //        PlatformConfig.setWeixin("wx216b846a8adfe057", "da1558a8e27444c6ebf737960b7374f6");
        PlatformConfig.setWeixin("wx216b846a8adfe057", "eb49f2afb30e06aea0f8661a07131df9");
        UMShareAPI.get(this);
        this.mContext = this;
        this.mMainThreadHandler = new Handler();
        this.mMainThread = Thread.currentThread();
        this.mMainThreadId = android.os.Process.myTid();
        this.mMainThreadLooper = getMainLooper();
        //        Stetho.initializeWithDefaults(this);

        //初始化极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        registrationId = JPushInterface.getRegistrationID(this);
        SharedPreferencesUtils.put(BaseApplication.getApplication(), "registrationId", registrationId);
        Logger.e("1099" + "run:--------->registrationId： " + registrationId);
        //初始化百度地图  也可以在activity中初始化
        SDKInitializer.initialize(getApplicationContext());
      /*  new LocationFaceUtil(getApplicationContext(), new LocationFace() {
            @Override
            public void locationResult(BDLocation location) {
            }
        });*/
        //必须调用初始化okgo
        OkGo.init(this);
        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //好处是全局参数统一,特定请求可以特别定制参数
        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()
                    //打开该调试开关,控制台会使用 红色error 级别打印log,并不是错误,是为了显眼,不需要就不要加入该行
                    .debug("OkGo")
                    //如果使用默认的 60秒,以下三行也不需要传
                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时
                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                    .setCacheMode(CacheMode.NO_CACHE)
                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

                    //如果不想让框架管理cookie,以下不需要
                    //                .setCookieStore(new_sign MemoryCookieStore())
                    // cookie使用内存缓存（app退出后，cookie消失）
                    .setCookieStore(new PersistentCookieStore());          //cookie持久化存储，如果cookie不过期，则一直有效

            //可以设置https的证书,以下几种方案根据需要自己设置,不需要不用设置
            //                    .setCertificates()                                  //方法一：信任所有证书
            //                    .setCertificates(getAssets().open("srca.cer"))      //方法二：也可以自己设置https证书
            //                    .setCertificates(getAssets().open("aaaa.bks"), "123456", getAssets().open("srca
            // .cer"))//方法三：传入bks证书,密码,和cer证书,支持双向加密

            //可以添加全局拦截器,不会用的千万不要传,错误写法直接导致任何回调不执行
            //                .addInterceptor(new_sign Interceptor() {
            //                    @Override
            //                    public Response intercept(Chain chain) throws IOException {
            //                        return chain.proceed(chain.request());
            //                    }
            //                })
                    /*//这两行同上,不需要就不要传
                    .addCommonHeaders(headers)                                         //设置全局公共头
					.addCommonParams(params);                                          //设置全局公共参数*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 对外暴露上下文
    public static BaseApplication getApplication() {
        return mContext;
    }

    // 对外暴露主线程的handler
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    // 对外暴露主线程
    public static Thread getMainThread() {
        return mMainThread;
    }

    // 对外暴露主线程id
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    // 对外暴露主线程的looper
    public static Looper getMainThreadLooper() {
        return mMainThreadLooper;
    }


    private String currenttime;//请求token时的时间(格式化时间)
    private static String DFAULT_LOTID = "jczq";//默认lotid
    private static String currentLotId = DFAULT_LOTID;//当前彩票种类，竞彩足球，竞彩篮球等
    //    private static ArrayList<LoginObserverCallback> mLoginObserList;//登陆观察者，注册的用户会根据登陆状态改变进行操作

    public static String getCurrentLotId() {
        return currentLotId;
    }

    public static void setCurrentLotId(String currentLotId) {
        BaseApplication.currentLotId = currentLotId;
    }

    private             String  mToken       = "";//访问网络需要的token
    public final static String  signKey      = "shgo12al";//app密匙
    protected static    boolean mLoginStatus = false;//登陆状态
    private static      String  mSecret      = "";//注册时获取的secret
    private static int    mUserId;//用户id
    private static String mUserPhoneNumber;//用户手机号
    private static String mUserPayPwd = "";//用户支付密码

    public static String getmUserUrl() {
        return mUserUrl;
    }

    private static String mUserUrl = "";//用户头像地址

    public static String getmSecret() {
        return mSecret;
    }

    public static void setmSecret(String mSecret) {
        BaseApplication.mSecret = mSecret;
    }

    public static boolean ismLoginStatus() {
        return mLoginStatus;
    }

    public static void setmLoginStatus(boolean mLoginStatus) {
        BaseApplication.mLoginStatus = mLoginStatus;

        LogUtils.i("setmLoginStatus :" + ismLoginStatus());

        //执行观察者操作
        //        if (mLoginStatus) {
        //            LogUtils.i("observer beginLogin");
        //            if (mLoginObserList != null) {
        //                for (LoginObserverCallback observerCallback : mLoginObserList) {
        //                    observerCallback.onLogin();
        //                }
        //            }
        //
        //        } else {
        //            LogUtils.i("observer exitLogin");
        //            for (LoginObserverCallback observerCallback : mLoginObserList) {
        //                observerCallback.onExitLogin();
        ////                clearUserInfo();
        //            }
        //        }
    }

    public String getmToken() {
        return mToken;
    }


    public void AppExit(Context context) {
        try {
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
        }
    }


    /**
     * 获取App安装包信息
     *
     * @return
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }


    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return version.substring(0, version.indexOf("."));//获取版本号的整数位
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.i(e + "exception");
            return "";
        }
    }

    //
    public boolean containsProperty(String key) {
        Properties props = getProperties();
        return props.containsKey(key);
    }

    //设置properties
    public void setProperties(Properties ps) {
        AppConfig.getAppConfig(this).set(ps);
    }

    public Properties getProperties() {
        return AppConfig.getAppConfig(this).get();
    }

    public void setProperty(String key, String value) {
        AppConfig.getAppConfig(this).set(key, value);
    }

    /**
     * 获取Token时传AppConfig.CONF_TOKEN
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        String res = AppConfig.getAppConfig(this).get(key);
        return res;
    }

    public void removeProperty(String... key) {
        AppConfig.getAppConfig(this).remove(key);
    }


    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static int getVersionCode() {
        try {
            PackageManager manager = getApplication().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getApplication().getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
}

