package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-19-0019 15:26
 * 描    述：欢迎页的viewpager适配器
 * 修订历史：
 * ================================================
 */

public class WelcomePagerAdapter extends PagerAdapter {
    private List<ImageView> imageViews ;



    public WelcomePagerAdapter( List<ImageView> imageViews) {
        this.imageViews = imageViews;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageViews.get(position));
        return imageViews.get(position);

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViews.get(position));
    }

    @Override
    public int getCount() {
        Logger.e( imageViews.size()+"开奖viewpager长度");
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }
}
