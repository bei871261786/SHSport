package shlottery.gov.cn.lotterycenter.ui.activity;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.PrefUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;

public class ZoushiActivity extends BaseActivity {

    private WebView mWebView;
    private String mUrl;
    private String mTitle = "";

    @Override
    protected void initView() {
        setContentView(R.layout.activity_zoushi);
        mWebView = (WebView) findViewById(R.id.zoushi_webview);
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
    }

    @Override
    protected void init() {
        String url = getIntent().getStringExtra("url");
        mUrl = getTotalUrl(url);
        LogUtils.i("zoushi url:" + mUrl);
        mTitle = getIntent().getStringExtra("title");
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
        titlebarTv.setText(mTitle + "   走势图");
        ImageView mSelfQuestion = (ImageView) findViewById(R.id.titlebar_indicator);
        mSelfQuestion.setVisibility(View.GONE);
    }

    private String getTotalUrl(String url) {
        String client = PrefUtils.getString(BaseApplication.getApplication(), "Client", "");
        String signKey = BaseApplication.signKey;
        String sign = StringUtils.getMD5(client + signKey);
        return url + "?client=" + client + "&sign=" + sign;
    }

    @Override
    protected String getBaidutitle() {
        return "走势";
    }
}
