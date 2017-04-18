package shlottery.gov.cn.lotterycenter.ui.nublottery.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.Sf14Bean;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-12-0012 上午 11:53
 * 描    述：homefragment 中的开奖等gridview的适配器
 * 修订历史：
 * ================================================
 */


public class SfcTitleGdvAdapter extends BaseAdapter {

    private List<String> mNames;
    private Context context;
    private int selectedPosition;

    public SfcTitleGdvAdapter(Context context, List<String> mNames) {
        this.context = context;
        this.mNames = mNames;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mNames.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view;
        if (convertView == null) {
            view = View.inflate(context, R.layout.syx5gridview_item, null);
        } else {
            view = convertView;
        }
        TextView mtitle = (TextView) view.findViewById(R.id.gridview_title);
        LinearLayout mLinearlayout = (LinearLayout) view.findViewById(R.id.syx5_title_ll);
        mtitle.setText(mNames.get(position));
        if (position == selectedPosition) {
            mtitle.setTextColor(context.getResources().getColor(R.color.white));
            mLinearlayout.setBackgroundResource(R.drawable.shape_nocorner_whiteblue);
        } else {
            mtitle.setTextColor(context.getResources().getColor(R.color.frame_gray));
            mLinearlayout.setBackgroundResource(R.drawable.shape_nocorner_framegrayblue);
        }

        return view;
    }
}
