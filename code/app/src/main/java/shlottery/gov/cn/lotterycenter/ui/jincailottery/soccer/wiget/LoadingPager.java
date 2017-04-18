package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.wiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.manager.ThreadManager;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;


public abstract class LoadingPager extends FrameLayout {

    // 加载默认的状态
    private static final int START_UNLOADING = 0;
    // 加载的状态
    private static final int START_LOADING = 1;
    // 失败的状态
    private static final int START_ERROR = 3;
    // 加载空的状态
    private static final int START_EMPTY = 4;
    // 加载成功的状态
    private static final int START_SUCCESS = 5;

    public int getmState() {
        return mState;
    }

    public void setmState(int mState) {
        this.mState = mState;
    }

    private int mState;
    private View loadingView;
    private View emptyView;
    private View errorView;
    private View successView;
    private TextView mReTryButton;
    public ThreadManager manager;

    public LoadingPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public LoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingPager(Context context) {
        super(context);
        init();
    }

    private void init() {
        // 初始化 默认的状态
        LogUtils.i("page lpinit");
        mState = START_UNLOADING;
        loadingView = createLoadingView();
        if (null != loadingView) {
            addView(loadingView, new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));
        }
        errorView = createErrorView();

        if (null != errorView) {
            addView(errorView, new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));
            mReTryButton = (TextView) errorView.findViewById(R.id.page_bt);
            mReTryButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadingView = createLoadingView();
                    refresh();
                }
            });
        }

        emptyView = createEmptyView();
        if (null != emptyView) {
            addView(emptyView, new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));
        }
        // 显示出界面
        //  showSafePagerView();
        show();
    }

    protected abstract void refresh();

    private void showSafePagerView() {
        UIUtils.runInMainThread(new Runnable() {
            @Override
            public void run() {
                showPageView();
            }
        });
    }

    protected void showPageView() {
        LogUtils.i("page lp showpageview State:" + mState);
        LogUtils.i("page view State:" + (null == loadingView) + ":" + (null != errorView) + ":" + (null != emptyView) + ":" + (null == successView));
        if (null != loadingView) {
            loadingView.setVisibility(mState == START_LOADING
                    || mState == START_UNLOADING ? View.VISIBLE
                    : View.INVISIBLE);
        }
        if (null != errorView) {
            errorView.setVisibility(mState == START_ERROR ? View.VISIBLE
                    : View.INVISIBLE);
        }
        if (null != emptyView) {
            emptyView.setVisibility(mState == START_EMPTY ? View.VISIBLE
                    : View.INVISIBLE);
        }

        // 成功的界面
        if (null == successView && mState == START_SUCCESS) {
            successView = createLoadedView();
            addView(successView, new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));
        }

        if (null != successView) {
            successView.setVisibility(mState == START_SUCCESS ? View.VISIBLE
                    : View.INVISIBLE);
        }
    }

    public void show() {
        if (mState == START_ERROR || mState == START_EMPTY) {
            mState = START_UNLOADING;
        }
        if (mState == START_UNLOADING) {
            mState = START_LOADING;

            manager = new ThreadManager();
            manager.getLongPool().execute(new TaskRunable());
        }
        showSafePagerView();
    }

    /**
     * 获取数据的接口
     *
     * @author xml_tech
     */
    private class TaskRunable implements Runnable {
        @Override
        public void run() {
            LogUtils.i("page TaskRunnable");
            final LoadResult result = load();
            UIUtils.runInMainThread(new Runnable() {

                @Override
                public void run() {
                    if (null != result) {
                        mState = result.getValue();
                    }
                    showPageView();
                }
            });
        }

    }

    public enum LoadResult {
        ERROR(3), EMPTY(4), SUCCESS(5);

        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    protected abstract View createLoadedView();

    protected abstract LoadResult load();

    // 空的界面
    private View createEmptyView() {
        return UIUtils.inflate(R.layout.loading_page_empty);
    }

    // 错误界面
    private View createErrorView() {
        return UIUtils.inflate(R.layout.loading_page_error);
    }

    // 加载界面
    private View createLoadingView() {
        return UIUtils.inflate(R.layout.loading_page_loading);
    }

    public void stopprotol( ) {
        manager.mLongPool.stop();
    }
}
