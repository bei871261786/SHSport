package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

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

public class HomeTopPagerAdapter extends PagerAdapter {
    private Context context;
    private List<String> urls;
    private List<ImageView> images = new ArrayList<ImageView>();


    public HomeTopPagerAdapter(Activity context, List<String> urls) {
        Logger.e(images.size() + "images的长度");
        this.context = context;

        if (urls == null || urls.size() == 0) {
            this.urls = new ArrayList<>();
        } else {
            this.urls = urls;
        }
        if (urls.size() == 2) {
            for (int i = 0; i < urls.size(); i++) {
                ImageView image = new ImageView(context);
                image.setScaleType(ImageView.ScaleType.FIT_XY);
                Picasso.with(context).load(urls.get(i)).into(image);
                images.add(image);
            }
            for (int i = 0; i < urls.size(); i++) {
                ImageView image = new ImageView(context);
                image.setScaleType(ImageView.ScaleType.FIT_XY);
                Picasso.with(context).load(urls.get(i)).into(image);
                images.add(image);
            }
        } else {
            for (int i = 0; i < urls.size(); i++) {
                ImageView image = new ImageView(context);
                image.setScaleType(ImageView.ScaleType.FIT_XY);
                Picasso.with(context).load(urls.get(i)).into(image);
                images.add(image);
            }
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        container.addView(images.get(position));
//        return images.get(position);

        //对ViewPager页号求模取出View列表中要显示的项
        position %= images.size();
        if (position < 0) {
            position = images.size() + position;
        }
        ImageView view = images.get(position);
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }
        container.addView(view, 0);
        //add listeners here if necessary
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(images.get(position));
    }

    @Override
    public int getCount() {
//        return articles.size();
        if (urls.size() == 1) {
            return 1;
        }
        //设置成最大，使用户看不到边界

        return 5000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }
}
