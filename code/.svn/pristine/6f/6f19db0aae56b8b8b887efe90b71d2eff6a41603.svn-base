package shlottery.gov.cn.lotterycenter.Base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mobstat.StatService;

import java.util.List;

import shlottery.gov.cn.lotterycenter.ui.widget.LoadingPager;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;
import shlottery.gov.cn.lotterycenter.utils.ViewUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-12-0012 上午 11:55
 * 描    述：
 * 修订历史：
 * ================================================
 */

public abstract class BaseFragment extends DialogFragment {

    protected LoadingPager mContentView;
    private Activity mActivity;
    protected LayoutInflater layoutInflater;
    private String baiduTitle = "";


    @Override
    final public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        layoutInflater = LayoutInflater.from(mActivity);
        if (baiduTitle != null && !baiduTitle.isEmpty()) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mContentView) {
            mContentView = new LoadingPager(UIUtils.getContext()) {

                @Override
                protected LoadResult load() {
                    return BaseFragment.this.Load();
                }

                @Override
                protected View createLoadedView() {
                    return BaseFragment.this.createLoadedView();
                }
            };
        } else {
            ViewUtils.removeSelfFromParent(mContentView);
        }

        baiduTitle = getTitle();
        baiduTitle = getTitle();
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

    //刷新数据，可供子类复写
    public void refreshData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (baiduTitle != null && !baiduTitle.isEmpty()) {
            StatService.onPageStart(getActivity(), baiduTitle);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (baiduTitle != null && !baiduTitle.isEmpty()) {
            StatService.onPageEnd(getActivity(), baiduTitle);
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (baiduTitle != null && !baiduTitle.isEmpty()) {

        }

    }

    protected abstract String getTitle();
}
