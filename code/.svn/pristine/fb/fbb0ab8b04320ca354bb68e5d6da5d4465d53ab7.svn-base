package shlottery.gov.cn.lotterycenter.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.UserInfoBean;
import shlottery.gov.cn.lotterycenter.manager.LoginManager;
import shlottery.gov.cn.lotterycenter.ui.MainActivity;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.PrefUtils;
import shlottery.gov.cn.lotterycenter.utils.SharedPreferencesUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;

/**
 * Created by booob on 2016-08-11-0011.
 */
public class WebViewActivity extends BaseActivity {
    @BindView(R.id.wv_news_detail)
    WebView wvNewsDetail;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    private String mUrl;
    private String mCode = "";
    private int mType;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_newsdetail);
        ButterKnife.bind(this);
        wvNewsDetail.loadUrl(mUrl);
        wvNewsDetail.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                titlebarTv.setText(title);//将网页的title设置到apptitle
            }
        });

        initTitleBar();
        WebSettings settings = wvNewsDetail.getSettings();
        settings.setBuiltInZoomControls(true);// 显示缩放按钮(wap网页不支持)
        settings.setUseWideViewPort(true);// 支持双击缩放(wap网页不支持)
        settings.setJavaScriptEnabled(true);// 支持js功能
        wvNewsDetail.addJavascriptInterface(new JsObject(), "AndroidFunction");

        wvNewsDetail.setWebViewClient(new WebViewClient() {
            // 开始加载网页
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pbLoading.setVisibility(View.VISIBLE);
            }

            // 网页加载结束
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pbLoading.setVisibility(View.INVISIBLE);
            }

            // 所有链接跳转会走此方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);// 在跳转链接时强制在当前webview中加载
                return true;
            }
        });
        // mWebView.goBack();//跳到上个页面
        // mWebView.goForward();//跳到下个页面
    }

    private void initTitleBar() {
        LinearLayout mBack = (LinearLayout) findViewById(R.id.titlebar_back_ll);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (BaseApplication.isMainActivityStart) {
                    finish();
                } else {
                    Intent intent = new Intent(WebViewActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        titlebarTv = (TextView) findViewById(R.id.titlebar_tv);
        ImageView mFlitrateImg = (ImageView) findViewById(R.id.titlebar_indicator);
        mFlitrateImg.setVisibility(View.GONE);
    }

    //返回(如果不是从主界面进入，那返回的时候需要启动主界面)
    @Override
    public void onBackPressed() {
        if (wvNewsDetail.canGoBack()){
            wvNewsDetail.goBack();
            return;
        }
        if (BaseApplication.isMainActivityStart) {
            finish();
        } else {
            Intent intent = new Intent(WebViewActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void init() {
        Intent mIntent = getIntent();
        mUrl = mIntent.getStringExtra("mUrl");
        mCode = mIntent.getStringExtra("mCode");
        mType = mIntent.getIntExtra("mType", 0);
        LogUtils.e(mUrl + "地址");
        mUrl = getTotalUrl(mUrl);
        LogUtils.i("WebViewactivity url:" + mUrl + "::type:" + mType);
        if (mUrl == null) {
            mUrl = "http://www.shlottery.gov.cn/";
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        pbLoading.setVisibility(View.GONE);
    }

    private class JsObject {
        @JavascriptInterface
        public void login() {
            LogUtils.i("webview checkLogin");
            LoginManager.getInstance().login(WebViewActivity.this);
        }
    }

    private String getTotalUrl(String url) {
        String client = PrefUtils.getString(BaseApplication.getApplication(), "Client", "");
        String signKey = BaseApplication.signKey;
        String sign;
        switch (mType) {
            //需要sign和client
            case 0:
                sign = StringUtils.getMD5(client + signKey);
                return url + "?client=" + client + "&sign=" + sign;
            //需要vouchercode，sign和client
            case 1:
                String secret = "";
                UserInfoBean infoBean = (UserInfoBean) SharedPreferencesUtils.getObject(Configure.SPKEY_USERINFO);
                if (infoBean != null && infoBean.getSecret() != null) {
                    secret = infoBean.getSecret();
                }
                sign = StringUtils.getMD5(client + secret + signKey+mCode);
                return url + "?code=" + mCode + "&client=" + client + "&sign=" + sign;
        }
        return null;
    }

    @Override
    protected String getBaidutitle() {
        return titlebarTv.getText().toString();
    }
}
