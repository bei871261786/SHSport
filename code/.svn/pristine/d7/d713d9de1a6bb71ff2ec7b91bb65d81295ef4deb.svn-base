package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.LocationBean;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-17-0017 15:53
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class DituAdapter extends BaseAdapter {

    private Context context;
    private LocationBean locationBean;

    public DituAdapter(Context context, LocationBean locationBean) {
        this.context = context;
        this.locationBean = locationBean;
    }

    @Override
    public int getCount() {
        return locationBean.getData().getList().size();
    }

    @Override
    public Object getItem(int position) {
        return locationBean.getData().getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ditulsv_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.locationTv.setText(locationBean.getData().getList().get(position).getAddress());


        String[] arr = locationBean.getData().getList().get(position).getSaleType().split("\\,");
        List<String> list = new ArrayList<>();
        if (!StringUtils.isEmpty(locationBean.getData().getList().get(position).getSitePic())) {
            Picasso.with(context).load(locationBean.getData().getList().get(position).getSitePic()).into(viewHolder.dituitemIm);
        } else {
            viewHolder.dituitemIm.setImageResource(R.mipmap.ditu);
        }
        list.clear();
        if (!StringUtils.isEmpty(locationBean.getData().getList().get(position).getDistrict())) {
            list.add(locationBean.getData().getList().get(position).getDistrict());
        }
        for (int m = 0; m < arr.length; m++) {
            list.add(arr[m]);
            Logger.e(arr[m] + "_______切割后的字符串");
        }
        if (list.size() == 1) {
            viewHolder.diquTv.setVisibility(View.VISIBLE);
            viewHolder.typeTv.setVisibility(View.GONE);
            viewHolder.type2Tv.setVisibility(View.GONE);
            viewHolder.diquTv.setText(list.get(0));
        }
        if (list.size() == 2) {
            viewHolder.diquTv.setVisibility(View.VISIBLE);
            viewHolder.typeTv.setVisibility(View.VISIBLE);
            viewHolder.type2Tv.setVisibility(View.GONE);
            viewHolder.diquTv.setText(list.get(0));
            viewHolder.typeTv.setText(list.get(1));

        }
        if (list.size() >= 3) {
            viewHolder.diquTv.setVisibility(View.VISIBLE);
            viewHolder.typeTv.setVisibility(View.VISIBLE);
            viewHolder.type2Tv.setVisibility(View.VISIBLE);
            viewHolder.diquTv.setText(list.get(0));
            viewHolder.typeTv.setText(list.get(1));
            viewHolder.type2Tv.setText(list.get(2));

        }
        if (StringUtils.isEmpty(locationBean.getData().getList().get(position).getDistance())) {
            viewHolder.juliTv.setText("");
        } else {
            viewHolder.juliTv.setText("距离" + locationBean.getData().getList().get(position).getDistance());
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.dituitem_im)
        ImageView dituitemIm;
        @BindView(R.id.location_tv)
        TextView locationTv;
        @BindView(R.id.diqu_tv)
        TextView diquTv;
        @BindView(R.id.type_tv)
        TextView typeTv;
        @BindView(R.id.type2_tv)
        TextView type2Tv;
        @BindView(R.id.dituitem_ll)
        LinearLayout dituitemLl;
        @BindView(R.id.dituitem_selected_im)
        ImageView dituitemSelectedIm;
        @BindView(R.id.juli_tv)
        TextView juliTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
