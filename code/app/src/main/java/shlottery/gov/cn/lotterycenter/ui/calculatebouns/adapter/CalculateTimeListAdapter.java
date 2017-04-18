package shlottery.gov.cn.lotterycenter.ui.calculatebouns.adapter;

import android.content.Context;


import java.util.List;

import shlottery.gov.cn.lotterycenter.ui.view.widget.adapters.AbstractWheelTextAdapter;

/**
 * Created by booob on 2016-08-08-0008.
 */
public class CalculateTimeListAdapter extends AbstractWheelTextAdapter {

    private List<String> TimeList;
    private Context context;

    public CalculateTimeListAdapter(Context context, List<String> TimeList) {
        super(context);
        this.context = context;
        this.TimeList = TimeList;
    }


    @Override
    protected CharSequence getItemText(int index) {
        return TimeList.get(index);
    }

    @Override
    public int getItemsCount() {
        return TimeList.size();
    }
}
