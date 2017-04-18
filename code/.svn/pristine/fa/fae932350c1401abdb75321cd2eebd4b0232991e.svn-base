package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.BaseFragment;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-02-0002 13:59
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class Syx5VpgAdapter extends FragmentPagerAdapter {

    public Syx5VpgAdapter(FragmentManager fm, List<BaseFragment> baseFragments) {
        super(fm);
        this.baseFragments = baseFragments;
    }

    private List<BaseFragment> baseFragments;


    @Override
    public Fragment getItem(int position) {
        return baseFragments.get(position);
    }

    @Override
    public int getCount() {
        return baseFragments.size();
    }
}
