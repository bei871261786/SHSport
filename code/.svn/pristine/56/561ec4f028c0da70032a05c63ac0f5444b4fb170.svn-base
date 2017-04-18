package shlottery.gov.cn.lotterycenter.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.ClientBean;
import shlottery.gov.cn.lotterycenter.bean.UserInfoBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.service.UpdateLoginStatusService;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.PrefUtils;
import shlottery.gov.cn.lotterycenter.utils.SharedPreferencesUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.ToastUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-25-0025 14:27
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class SplashActivity extends Activity implements LoadCallback {
    @BindView(R.id.splash_im)
    ImageView splashIm;
    @BindView(R.id.copyright)
    TextView copyright;
    @BindView(R.id.lottery_version_tv)
    TextView lotteryVersionTv;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    private Handler mHandler;
    private Intent mIntent;
    private static final int TOWELCOME = 0;//跳转到引导页
    private static final int TOMAIN = 1;//跳转到mainactivity
    private static final int TOADVERT = 2;//跳转到广告页
    //    private MyCountDownTimer myCountDownTimer;
    public ClientBean mClientBean;
    private String mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        //百度统计
        StatService.start(this);
        init();
        if (StringUtils.isEmpty(mClient)) {
            startGetClient();
        } else {
            ChooseActivity();
        }
        initLoginStatus();
        //  UpdateUserinfoService service=new UpdateUserinfoService();
        //启动服务，在后台三十秒一次核对登录状态
        Intent intent = new Intent(this, UpdateLoginStatusService.class);
        startService(intent);
        // JumptoMainActivity();
    }

    private void initLoginStatus() {
        UserInfoBean mUserInfoBean = (UserInfoBean) SharedPreferencesUtils.getObject(Configure.SPKEY_USERINFO);
        if (mUserInfoBean != null) {
            if (mUserInfoBean.getSecret() == null) {
                BaseApplication.setmLoginStatus(false);
            } else {
                LogUtils.i("initLoginStatus :" + mUserInfoBean.getSecret() + "__");
                if (mUserInfoBean.getSecret() == null || "".equals(mUserInfoBean.getSecret())) {
                    BaseApplication.setmLoginStatus(false);
                } else {
                    BaseApplication.setmLoginStatus(true);
                }
            }
        }
    }

    private void ChooseActivity() {
        boolean isFirst = (boolean) SharedPreferencesUtils.get(SplashActivity.this, "is_first", true);
        LogUtils.i("splashActivity chooseActivity");
        if (isFirst) {
            Message message = new Message();
            message.what = 0;
            mHandler.sendMessage(message);
            SharedPreferencesUtils.put(SplashActivity.this, "is_first", false);
        } else {
            Message message = new Message();
            message.what = 1;
            mHandler.sendMessageDelayed(message, 0);//一秒后
        }
    }

    //异步获取Client
    private void startGetClient() {
        LogUtils.i("splashActivity startGetClient");
        OkGo.get(UrlManager.url + "?" + UrlManager.getParames()).connTimeOut(5000).cacheMode(CacheMode.NO_CACHE).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                mClientBean = gson.fromJson(s, ClientBean.class);
                if (null != mClientBean) {
                    if (mClientBean.getRet() == 100) {
                        PrefUtils.setString(SplashActivity.this, "Client", mClientBean.getData().getClient());
                        Logger.e(PrefUtils.getString(SplashActivity.this, "Client", "1111") + "Client");
                    } else {
                        ToastUtils.showShort(SplashActivity.this, mClientBean.getMsg());
                        JumptoAdvertActivity();
                    }
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                ToastUtils.showShort(SplashActivity.this, "网络异常,请检查网络");
                JumptoAdvertActivity();
            }

            @Override
            public void onAfter(@Nullable String s, @Nullable Exception e) {
                super.onAfter(s, e);
                if (null != mClientBean) {
                    if (mClientBean.getData().isNewVersion()) {
                        ToastUtils.showShort(SplashActivity.this, "有新版本");
                    }
                    ChooseActivity();
                }
            }
        });

    }

    protected void init() {
        mClient = PrefUtils.getString(SplashActivity.this, "Client", "");
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == TOWELCOME) {
                    mIntent = new Intent(SplashActivity.this, WelcomeActivity.class);
                    startActivity(mIntent);
                    finish();
                } else {
                    JumptoAdvertActivity();
                }
            }
        };
    }




    private void JumptoAdvertActivity() {
        mIntent = new Intent(SplashActivity.this, AdvertActivity.class);
        startActivity(mIntent);
        finish();
    }

    //禁用返回键
    @Override
    public void onBackPressed() {
        return;
    }


    @Override
    public void onSucess(Object o) {
    }

    @Override
    public void onError() {

    }
}
