package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by booob on 2016-05-31-0031.
 */
public class MatchViewPagerAdapter extends PagerAdapter {
    private List<View> mViewList;

    public MatchViewPagerAdapter(List<View> mViewList) {
        this.mViewList = mViewList;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) 	{
        container.removeView(mViewList.get(position));//删除页卡
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {	//这个方法用来实例化页卡
        container.addView(mViewList.get(position), 0);//添加页卡
        return mViewList.get(position);
    }

    @Override
    public int getCount() {
        return  mViewList.size();//返回页卡的数量
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0==arg1;//官方提示这样写
    }

}
