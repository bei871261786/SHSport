package shlottery.gov.cn.lotterycenter.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.SuperGDetailBean;
import shlottery.gov.cn.lotterycenter.bean.SuperGListBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.SuperGDetaiProtocol;
import shlottery.gov.cn.lotterycenter.protool.SuperGListProtocol;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.ScreenUtils;


public class SuperGActivity extends BaseActivity implements LoadCallback {

    private RadioGroup mRadioGroup;
    private MyListener mListener;
    private TextView mHeadissueno;
    private TextView mHeadTitle;
    private TextView mHeadSummary;
    private LinearLayout mVideioviewLayout;
    private ImageView mVideoCover;//视屏播放封面图片
    private RelativeLayout mCoverLayout;//视屏播放封面布局
    private TextView mDetailContent;//节目详情
    private ImageView mCaidanContent;//终极彩单
    private ImageView mBonusListContent;//中奖名单
    private ScrollView mScrollView;
    private TextView mGuest;
    private TextView mHost;
    private String mGueststr = "";
    private int mPageNo = 1;
    private int mPageSize = 20;
    private String mDetailId = "";
    //视频地址
    private String path = "";
    private Uri uri;
    private boolean isCanPlay = false;
    private boolean isFromList = false;//是否是从列表进来
    private View mPlaceholder;
    private boolean isOnPause;
    private FrameLayout mVideoContainer;
    private WebView mWebView;
    private LinearLayout mWebviewParent;
    private RelativeLayout mTitleBar;
    private boolean isLimit = true;//如果在全屏状态，那返回键就不退出，而是缩小
    private LinearLayout.LayoutParams mParams;

    private Handler expandHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isLimit = false;
            if (mWebView != null) {
                mScrollView.setVisibility(View.GONE);
                mVideoContainer.setVisibility(View.VISIBLE);
                mWebviewParent.removeView(mWebView);
                mVideoContainer.addView(mWebView);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                mWebView.setLayoutParams(params);
                mTitleBar.setVisibility(View.GONE);
            }
        }
    };
    private Handler shrinkHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isLimit = true;
            mScrollView.setVisibility(View.VISIBLE);
            if (mWebView != null) {
                mScrollView.setVisibility(View.VISIBLE);
                mVideoContainer.setVisibility(View.GONE);
                mVideoContainer.removeView(mWebView);
                mWebviewParent.addView(mWebView, 3);
                mWebView.setLayoutParams(mParams);
                mTitleBar.setVisibility(View.VISIBLE);
            }
            LogUtils.i("webview url   2:" + mWebView.getUrl() + "::::" + path);
        }
    };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_super_g);
        mRadioGroup = (RadioGroup) findViewById(R.id.superG_radiogrpoup);
        mHeadissueno = (TextView) findViewById(R.id.superG_head_issueno);
        mVideoContainer = (FrameLayout) findViewById(R.id.video_fullView);
        mHeadTitle = (TextView) findViewById(R.id.superG_head_title);
        mHost = (TextView) findViewById(R.id.superG_host);
        mGuest = (TextView) findViewById(R.id.superG_guest);
        mHeadSummary = (TextView) findViewById(R.id.superG_head_summary);
        mDetailContent = (TextView) findViewById(R.id.superG_detail_content);
        mCaidanContent = (ImageView) findViewById(R.id.superG_caidan_content);
        mBonusListContent = (ImageView) findViewById(R.id.superG_bonuslist_content);
        mWebviewParent = (LinearLayout) findViewById(R.id.SuperG_sco_linearlayout);
        mScrollView = (ScrollView) findViewById(R.id.superG_scro);
        mWebView = (WebView) findViewById(R.id.SuperG_webView);
        mTitleBar = (RelativeLayout) findViewById(R.id.base_titleBar);
        mRadioGroup.setOnCheckedChangeListener(mListener);
        ViewGroup.LayoutParams params = mCaidanContent.getLayoutParams();
        mParams = (LinearLayout.LayoutParams) mWebView.getLayoutParams();
        int screenWidth = ScreenUtils.getScreenWidth(this);
        params.width = screenWidth;
        params.height = (int) (screenWidth / 1.77);
        mCaidanContent.setLayoutParams(params);
        mBonusListContent.setLayoutParams(params);
        initWebview(mWebView);
        initTitleBar();
        if (mDetailId == null || mDetailId.equals("")) {
            loadList();
        } else {
            loadDetailByList();
        }
    }

    class MyChromeClient extends WebChromeClient {
        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            LogUtils.i("webview onShowCustomView");
            super.onShowCustomView(view, callback);
        }

        @Override
        public void onHideCustomView() {
            LogUtils.i("webview onHideCustomView");
            super.onHideCustomView();
        }
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            LogUtils.i("webview onPageFinished");
            super.onPageFinished(view, url);
            String js = getJs(url);
            view.loadUrl(js);
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void fullScreen() {
        LogUtils.i("fullScreen " + Thread.currentThread().getName() + ":::" + isLimit);
        LogUtils.i("current Thread  fullScreen:" + Thread.currentThread() + ":::" + Thread.currentThread().getName());
//        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        } else {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        }
        if (isLimit) {
            expandHandler.sendEmptyMessage(0);
        } else {
            shrinkHandler.sendEmptyMessage(0);
        }
    }

    private class JsObject {
        @JavascriptInterface
        public void fullscreen() {
            //监听到用户点击全屏按钮
            fullScreen();
        }
    }

    //获得用于注入的javascript代码
    private String getJs(String url) {
        String js = "javascript:document.getElementsByClassName('" + getTagByUrl(url) + "')[0].addEventListener('click',function(){onClick.fullscreen();return false;});";
        return js;
    }

    //视频网站的全屏按钮Class标识
    public static String getTagByUrl(String url) {
        if (url.contains("qq")) {
            return "tvp_fullscreen_button"; // http://m.v.qq.com
        } else if (url.contains("youku")) {
            return "x-zoomin";              // http://www.youku.com
        } else if (url.contains("bilibili")) {
            return "icon-widescreen";       // http://www.bilibili.com/mobile/index.html
        } else if (url.contains("acfun")) {
            return "controller-btn-fullscreen"; //http://m.acfun.tv   无效
        } else if (url.contains("le")) {
            return "hv_ico_screen";         // http://m.le.com  无效
        }
        return "";
    }

    private void initWebview(WebView webview) {
        LogUtils.i("current Thread  initWebview:" + Thread.currentThread() + ":::" + Thread.currentThread().getName());
        WebSettings settings = webview.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //启用支持javascript
        settings.setJavaScriptEnabled(true);
        // 启动缓存
        webview.getSettings().setAppCacheEnabled(true);
        // 设置缓存模式
        webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        //使用自定义的WebViewClient
        webview.setWebViewClient(new MyWebViewClient());
        webview.setWebChromeClient(new MyChromeClient());
        webview.addJavascriptInterface(new JsObject(), "onClick");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void updateWebViewUrl(WebView webView) {
        // 设置WebView属性，能够执行Javascript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setVisibility(View.VISIBLE);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl(path);
//        LogUtils.i("webview url   4:" + webView.getUrl() + "::::" + path);
        LogUtils.i("updateWebViewUrl over");
    }

    private void initTitleBar() {
        LinearLayout mBack = (LinearLayout) findViewById(R.id.titlebar_back_ll);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLimit) {
                    finish();
                } else {
                    shrinkHandler.sendEmptyMessage(0);
                }
            }
        });
        titlebarTv = (TextView) findViewById(R.id.titlebar_tv);
        titlebarTv.setText("超G竞彩");
        ImageView mSelfQuestion = (ImageView) findViewById(R.id.titlebar_indicator);
        mSelfQuestion.setVisibility(View.GONE);
    }


    @Override
    protected void init() {
        mListener = new MyListener();
        Intent intent = getIntent();
        mDetailId = intent.getStringExtra("id");
    }

    private void loadDetailByList() {
        LogUtils.i("superG loadDetailByList");
        isCanPlay = true;//从列表点击过来直接可以播放视频
        loadDetail();
    }

    private void loadList() {
        LogUtils.i("superG loadList");
        SuperGListProtocol protocol = new SuperGListProtocol(this);
        protocol.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                LinkedHashMap<String, String> params = new LinkedHashMap();
                params.put("guest", mGueststr);
                params.put("pageNo", mPageNo + "");
                params.put("pageSize", mPageSize + "");
                return params;
            }
        }, this);
    }

    private void loadDetail() {
        SuperGDetaiProtocol protocol = new SuperGDetaiProtocol(this);
        protocol.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                LinkedHashMap<String, String> params = new LinkedHashMap();
                params.put("id", mDetailId);
                return params;
            }
        }, this);
    }

    @Override
    public void onSucess(Object o) {
        LogUtils.i(" superG onsuccess");
        if (o != null) {
            if (o instanceof SuperGListBean) {
                SuperGListBean bean = (SuperGListBean) o;
                if (bean.getRet() == 100) {
                    LogUtils.i(" superG onsuccess ret100");
                    ArrayList<SuperGListBean.DataBean.ListBean> listBean = (ArrayList<SuperGListBean.DataBean.ListBean>) bean.getData().getList();
                    initListInfo(listBean);
                }
            }

            if (o instanceof SuperGDetailBean) {
                SuperGDetailBean bean = (SuperGDetailBean) o;
                if (bean.getRet() == 100) {
                    SuperGDetailBean.DataBean dataBean = bean.getData();
                    initDetailInfo(dataBean);
                }
            }

        }
    }

    @Override
    public void onError() {
    }

    private void initDetailInfo(SuperGDetailBean.DataBean dataBean) {
        if (dataBean != null) {
            mDetailContent.setText(dataBean.getContent());
            mHeadTitle.setText(dataBean.getTitle());
            mHeadSummary.setText(dataBean.getSummary());
            mGuest.setText(dataBean.getGuest());
            mHost.setText(dataBean.getPresenter());
            path = dataBean.getVideoUrl();
            if (path.contains("https")) {
                path = path.replace("https", "http");
            }
            if (dataBean.getPicUrl() != null && !dataBean.getPicUrl().isEmpty()) {
                updateWebViewUrl(mWebView);
            }
            if (dataBean.getSummary() == null || dataBean.getSummary().equals("")) {
                mHeadSummary.setVisibility(View.GONE);
            } else {
                mHeadSummary.setText(dataBean.getSummary());
            }
            String matchUrl = dataBean.getMatchPic();
            if (matchUrl != null && !matchUrl.isEmpty()) {
                Picasso.with(this).load(matchUrl).into(mCaidanContent);
            }
            String bonusUrl = dataBean.getBonusUrl();
            if (bonusUrl != null && !bonusUrl.isEmpty()) {
                Picasso.with(this).load(bonusUrl).into(mBonusListContent);
            }
        }
    }

    private void initListInfo(ArrayList<SuperGListBean.DataBean.ListBean> listBean) {
        Logger.i("superG initinfo ");
        if (listBean != null && listBean.size() > 0) {
            SuperGListBean.DataBean.ListBean bean = listBean.get(0);
            Logger.i("superG initinfo :" + bean.getTitle() + ":::" + bean.getGuest() + ":::" + bean.getPresenter());
            mDetailId = bean.getId() + "";
            loadDetail();
        }
    }

    private void toggleRadioContent(int type) {
        switch (type) {
            case 0:
                mDetailContent.setVisibility(View.VISIBLE);
                mCaidanContent.setVisibility(View.GONE);
                mBonusListContent.setVisibility(View.GONE);

                break;
            case 1:
                mDetailContent.setVisibility(View.GONE);
                mCaidanContent.setVisibility(View.VISIBLE);
                mBonusListContent.setVisibility(View.GONE);
                break;
            case 2:
                mDetailContent.setVisibility(View.GONE);
                mCaidanContent.setVisibility(View.GONE);
                mBonusListContent.setVisibility(View.VISIBLE);
                break;

        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    private class MyListener implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.superG_detail:
                    toggleRadioContent(0);
                    break;
                case R.id.superG_caidan:
                    toggleRadioContent(1);
                    break;
                case R.id.superG_bonuslist:
                    toggleRadioContent(2);
                    break;
            }
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
            }
        }
    }


    /**
     * 当Activity执行onPause()时让WebView执行pause
     */
    @Override
    protected void onPause() {
        super.onPause();
        try {
            if (mWebView != null) {
                mWebView.getClass().getMethod("onPause").invoke(mWebView, (Object[]) null);
                isOnPause = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 当Activity执行onResume()时让WebView执行resume
     */
    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (isOnPause) {
                if (mWebView != null) {
                    mWebView.getClass().getMethod("onResume").invoke(mWebView, (Object[]) null);
                }
                isOnPause = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 该处的处理尤为重要:
     * 应该在内置缩放控件消失以后,再执行mWebView.destroy()
     * 否则报错WindowLeaked
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.getSettings().setBuiltInZoomControls(true);
            mWebView.setVisibility(View.GONE);
            mWebView.destroy();
            mWebView = null;
        }
        isOnPause = false;
    }

    @Override
    public void onBackPressed() {
        if (isLimit) {
            finish();
        } else {
            shrinkHandler.sendEmptyMessage(0);
        }

    }

    @Override
    protected String getBaidutitle() {
        return "超G竞彩";
    }
}
