package shlottery.gov.cn.lotterycenter.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingPager;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;
import shlottery.gov.cn.lotterycenter.utils.ViewUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/25 17:04
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public abstract class BaseMainFragment extends BaseFragment {
    protected ImageView mCalendar;
    protected ImageView mFiltrate;
    protected ImageView mSetting;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mContentView) {
            mContentView = new LoadingPager(UIUtils.getContext()) {

                @Override
                protected LoadResult load() {
                    return BaseMainFragment.this.Load();
                }

                @Override
                protected View createLoadedView() {
                    return BaseMainFragment.this.createLoadedView();
                }
            };
        } else {
            ViewUtils.removeSelfFromParent(mContentView);
        }
        mCalendar = (ImageView) getActivity().findViewById(R.id.main_calendar);
        mFiltrate = (ImageView) getActivity().findViewById(R.id.filtrate_tv);
        mSetting = (ImageView) getActivity().findViewById(R.id.main_setting);
//        initTitlebar();
        return mContentView;
    }

    protected abstract void initTitlebar();

}

