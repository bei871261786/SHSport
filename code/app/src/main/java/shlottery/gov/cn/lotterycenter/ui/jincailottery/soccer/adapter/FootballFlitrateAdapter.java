package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.Jincai.LeaguesBean;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;


/**
 * Created by yongchao.bei on 2016/7/13.
 */
public class FootballFlitrateAdapter extends BaseAdapter {

    private ArrayList<LeaguesBean> mDatas;
    private Context context;
    private Handler handler;

    public FootballFlitrateAdapter(ArrayList<LeaguesBean> mDatas, Context context, Handler handler) {
        LogUtils.i("flitrate adapter in data:" + mDatas.size());
        this.mDatas = mDatas;
        this.context = context;
        this.handler = handler;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
//        LogUtils.i("flitrate adapter in getview:" + mDatas.get(position).getLeagueName() + "::" + mDatas.get(position).isChecked());
        View view = null;
        Holder holder;
        if (null == convertView) {
            view = LayoutInflater.from(context).inflate(R.layout.football_listview_item_flitrate, null);
            holder = new Holder();
            holder.matchName = (CheckBox) view.findViewById(R.id.flitrate_matchName_cb);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (Holder) view.getTag();
        }
        holder.matchName.setText(mDatas.get(position).getLeagueName());
        holder.matchName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mDatas.get(position).setChecked(true);
                } else {
                    mDatas.get(position).setChecked(false);
                }
                handler.sendEmptyMessage(0);

            }
        });
        holder.matchName.setChecked(mDatas.get(position).isChecked());

        return view;
    }

    class Holder {
        CheckBox matchName;
    }
}
