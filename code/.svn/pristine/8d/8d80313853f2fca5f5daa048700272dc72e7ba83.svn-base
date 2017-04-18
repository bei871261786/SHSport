package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-19-0019 15:26
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class HomeKJPagerAdapter extends PagerAdapter {
    private Context context;
    private List<String> articles;
    private List<View> mViews ;

    private TextView[] mBottomTextViews;//开奖数据集合


    public HomeKJPagerAdapter(Activity context, List<View> mViews) {
        this.context = context;
        this.mViews = mViews;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViews.get(position));
        return mViews.get(position);

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    @Override
    public int getCount() {
        Logger.e( mViews.size()+"开奖viewpager长度");
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }
}
