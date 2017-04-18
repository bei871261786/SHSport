package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import shlottery.gov.cn.lotterycenter.R;


public class GuoGuanGridViewAdapter extends BaseAdapter {
    private final String[] mString;
    private Context context;
    private int clickTemp;

    public GuoGuanGridViewAdapter(Context context, String[] string, int clickTemp) {
        this.context = context;
        this.mString = string;
        this.clickTemp = clickTemp;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mString.length;
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


    public void setSeclection(int position) {
        clickTemp = position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view;
        if (convertView == null) {
            view = View.inflate(context, R.layout.dingdan_girdviewitem_guoguan, null);
        } else {
            view = convertView;
        }
        TextView textView = (TextView) view.findViewById(R.id.gg_fangshi);
        textView.setText(mString[position]);
        ImageView imageview = (ImageView) view.findViewById(R.id.chocice);
        if (clickTemp == position) {
            imageview.setVisibility(View.VISIBLE);
            textView.setTextColor(context.getResources().getColor(R.color.dantuo_zhushi_blue));
        } else {
            imageview.setVisibility(View.INVISIBLE);
            textView.setTextColor(context.getResources().getColor(R.color.black));
        }
        return view;
    }
}
