package shlottery.gov.cn.lotterycenter.Base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.Jincai.RequestBean;
import shlottery.gov.cn.lotterycenter.ui.MainActivity;


/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-12-0012 上午 11:54
 * 描    述：
 * 修订历史：
 * ================================================
 */

public abstract class BaseActivity extends ActionBarActivity {

    // 获取到前台进程的Activity
    private static Activity mForegroundActivity = null;
    protected TextView titlebarTv;
    String baiduTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (baiduTitle != null && !baiduTitle.isEmpty()) {
        }
        init();
        initView();
        perpare();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.home_backcolor);//通知栏所需颜色
        baiduTitle = getBaidutitle();
    }

    private void perpare() {

    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        OkGo.getInstance().cancelTag(this);
    }

    //解析请求结果的数据
    protected RequestBean parseRequest(String json) {
        Gson gson = new Gson();
        RequestBean resultData = gson.fromJson(json, RequestBean.class);
        return resultData;
    }

    /**
     * 初始化界面
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void init();

    //获取百度统计页面名称
    protected abstract String getBaidutitle();

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (baiduTitle != null && !baiduTitle.isEmpty()) {

            StatService.onPageStart(this, baiduTitle);
        }

        this.mForegroundActivity = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (baiduTitle != null && !baiduTitle.isEmpty()) {

        }

    }

    protected void setLotid(String lotId) {
        BaseApplication.setCurrentLotId(lotId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (baiduTitle != null && !baiduTitle.isEmpty()) {

            StatService.onPageEnd(this, baiduTitle);
        }

        this.mForegroundActivity = null;
    }

    public static Activity getForegroundActivity() {
        return mForegroundActivity;
    }

    /**
     * 如果不是从主界面进入, 回退到主界面
     */
    protected void closeThisActivity() {
        if (BaseApplication.isMainActivityStart) {
            finish();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}

