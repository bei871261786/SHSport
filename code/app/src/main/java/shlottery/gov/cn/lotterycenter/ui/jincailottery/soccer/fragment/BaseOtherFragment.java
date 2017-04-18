package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.wiget.LoadingPager;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;
import shlottery.gov.cn.lotterycenter.utils.ViewUtils;

public abstract class BaseOtherFragment extends Fragment {
    private LoadingPager mContentView;

    @Override
    public void onDestroy() {
        if (mContentView != null) {
            mContentView.stopprotol();
        }
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (null == mContentView) {
            LogUtils.i("page oncreateview ==null");
            mContentView = new LoadingPager(UIUtils.getContext()) {
                @Override
                protected LoadResult load() {
                    LogUtils.i("page load");
                    return BaseOtherFragment.this.Load();
                }

                @Override
                protected void refresh() {
                    ReTryAsyncTask mReTryAsyncTask = new ReTryAsyncTask();
                    LogUtils.i("BaseOtherFragment ReTryAsyncTask");
                    mReTryAsyncTask.execute();
                }

                @Override
                protected View createLoadedView() {
                    LogUtils.i("fragment oncreateview");
                    LogUtils.i("page createview");

                    return BaseOtherFragment.this.createLoadedView();
                }
            };
        } else {
            LogUtils.i("page oncreateview !=null");
            ViewUtils.removeSelfFromParent(mContentView);
        }
        return mContentView;
    }

    protected abstract View createLoadedView();

    protected abstract LoadingPager.LoadResult Load();


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
        protected LoadingPager.LoadResult doInBackground(Object[] params) {

            return Load();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (o.equals(LoadingPager.LoadResult.SUCCESS)) {
                show();
            }
        }
    }
}
