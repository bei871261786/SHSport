package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-12-0012 上午 11:53
 * 描    述：homefragment 中的开奖等gridview的适配器
 * 修订历史：
 * ================================================
 */
import shlottery.gov.cn.lotterycenter.R;


public class HomeGridViewAdapter extends BaseAdapter {

    private String[] mTitle;
    private int[] mIcon = {R.mipmap.kaijiang, R.mipmap.goucaitong, R.mipmap.jjjs_icon, R.mipmap.tuijian, R.mipmap.huodong, R.mipmap.chaojijincai, R.mipmap.wangdian, R.mipmap.kefu};
    private Context context;

    public HomeGridViewAdapter(Context context, String[] mTitle) {
        this.context = context;
        this.mTitle = mTitle;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mTitle.length;
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


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view;
        if (convertView == null) {
            view = View.inflate(context, R.layout.homegridview_item, null);
        } else {
            view = convertView;
        }
        TextView mtitle = (TextView) view.findViewById(R.id.gridview_title);
        mtitle.setText(mTitle[position]);
        ImageView imageview = (ImageView) view.findViewById(R.id.caizhong_im);
        imageview.setImageResource(mIcon[position]);
      /*  if (position == 0) {
            imageview.setImageResource(R.mipmap.kaijiang);
        } else if (position == 1) {
            imageview.setImageResource(R.mipmap.goucaitong);
        } else if (position == 2) {
            imageview.setImageResource(R.mipmap.bifen);
        } else if (position == 3) {
            imageview.setImageResource(R.mipmap.tuijian);
        }
        if (position == 4) {
            imageview.setImageResource(R.mipmap.huodong);
        } else if (position == 5) {
            imageview.setImageResource(R.mipmap.chaojijincai);
        } else if (position == 6) {
            imageview.setImageResource(R.mipmap.wangdian);
        }
        if (position == 7) {
            imageview.setImageResource(R.mipmap.kefu);
        }*/
        return view;
    }
}
