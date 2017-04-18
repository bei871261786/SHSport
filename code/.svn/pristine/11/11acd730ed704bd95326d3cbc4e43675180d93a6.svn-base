package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.callback.JcActivityFragment_DataCallback;
import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.wiget.LoadingPager;
import shlottery.gov.cn.lotterycenter.utils.DialogUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;
import shlottery.gov.cn.lotterycenter.utils.ViewUtils;

public abstract class BaseFragment extends Fragment{
    protected List<List<MatchsBean>> mDanGuanList = new ArrayList<>();
    protected List<List<MatchsBean>> mGuoGuanList = new ArrayList<>();
    protected List<List<MatchsBean>> mFilterGuoGuanList = new ArrayList<>();
    protected List<List<MatchsBean>> mFilterDanGuanList = new ArrayList<>();
    protected JcActivityFragment_DataCallback callback;
    protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handleMessge(msg);
        }
    };

    @Override
    @Subscribe
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtils.i("fragment oncreate");
        super.onCreate(savedInstanceState);
        registEnevtBus();
        callback = (JcActivityFragment_DataCallback) getActivity();
        handler.sendEmptyMessage(Configure_JC.HANDLER_INIT);
    }

    @Override
    @Subscribe
    public void onDestroy() {
        super.onDestroy();
        unRegistEnevtBus();
    }

    private LoadingPager mContentView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (null == mContentView) {
            LogUtils.i("page oncreateview ==null");
            asyntaskLoad();
            mContentView = new LoadingPager(UIUtils.getContext()) {
                @Override
                protected LoadingPager.LoadResult load() {
                    LogUtils.i("page load");
                    return BaseFragment.this.Load();
                }

                @Override
                protected void refresh() {
                    ReTryAsyncTask mReTryAsyncTask = new ReTryAsyncTask();
                    mReTryAsyncTask.execute();
                }

                @Override
                protected View createLoadedView() {
                    LogUtils.i("fragment oncreateview");
                    LogUtils.i("page createview");
                    return BaseFragment.this.createLoadedView();
                }
            };
        } else {
            LogUtils.i("page oncreateview !=null");
            ViewUtils.removeSelfFromParent(mContentView);
        }
        return mContentView;
    }

    protected void asyntaskLoad() {

    }

    protected abstract View createLoadedView();

    protected abstract LoadingPager.LoadResult Load();

    protected abstract void registEnevtBus();

    protected abstract void unRegistEnevtBus();

    protected abstract void handleMessge(Message msg);

    // 展示具体的页面
    public void show() {
        if (null != mContentView) {
            mContentView.show();
        }
    }

    // 检查服务器返回的数据情况
    protected LoadingPager.LoadResult check(Object obj) {
        LogUtils.i("page check");
        if (null == obj) {
            return LoadingPager.LoadResult.ERROR;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            if (list.size() == 0) {
                return LoadingPager.LoadResult.EMPTY;
            }
        }
        return LoadingPager.LoadResult.SUCCESS;
    }

    class ReTryAsyncTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            asyntaskLoad();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            show();
        }
    }


}
