package shlottery.gov.cn.lotterycenter.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.UserInfoBean;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.PrefUtils;
import shlottery.gov.cn.lotterycenter.utils.SharedPreferencesUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;

public class WdRegistActivity extends BaseActivity {
    private WebView mWebView;
    private String  mUrl;
    private String secret = "";

    @SuppressLint("JavascriptInterface")
    @Override
    protected void initView() {
        setContentView(R.layout.activity_wd_regist);
        mWebView = (WebView) findViewById(R.id.wdRegWebview);
        WebSettings settings = mWebView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //启用支持javascript
        settings.setJavaScriptEnabled(true); // 启动缓存
        mWebView.getSettings().setAppCacheEnabled(true);
        // 设置缓存模式
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        //使用自定义的WebViewClient
        mWebView.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        initTitleBar();

        mWebView.loadUrl(mUrl);
        mWebView.setWebViewClient(new MyWebViewClient());
    }

    @Override
    protected void init() {
        UserInfoBean infoBean = (UserInfoBean) SharedPreferencesUtils.getObject(Configure.SPKEY_USERINFO);
        String url = "";
        if (infoBean != null && infoBean.getSecret() != null) {
            secret = infoBean.getSecret();
            url = infoBean.getSiteRegUrl();
        }
        mUrl = getTotalUrl(url);
        LogUtils.i("zoushi url:" + mUrl);
    }

    private void initTitleBar() {
        LinearLayout mBack = (LinearLayout) findViewById(R.id.titlebar_back_ll);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titlebarTv = (TextView) findViewById(R.id.titlebar_tv);
        // titlebarTv.setText("网点登记");
        ImageView mSelfQuestion = (ImageView) findViewById(R.id.titlebar_indicator);
        mSelfQuestion.setVisibility(View.GONE);
    }

    private String getTotalUrl(String url) {
        String client = PrefUtils.getString(BaseApplication.getApplication(), "Client", "");
        String signKey = BaseApplication.signKey;
        String sign = StringUtils.getMD5(client + secret + signKey);
        return url + "?client=" + client + "&sign=" + sign;
    }


    // 监听
    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            view.getSettings().setJavaScriptEnabled(true);

            super.onPageFinished(view, url);
            // html加载完成之后，添加监听图片的点击js函数
            titlebarTv.setText(mWebView.getTitle());
            LogUtils.i("webview title:" + mWebView.getTitle());
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            view.getSettings().setJavaScriptEnabled(true);

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            super.onReceivedError(view, errorCode, description, failingUrl);

        }

    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected String getBaidutitle() {
        return "代销资格申请";
    }
}