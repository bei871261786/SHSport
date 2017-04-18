package shlottery.gov.cn.lotterycenter.ui.nublottery.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.BaseBean;
import shlottery.gov.cn.lotterycenter.bean.OrderBean;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/24 11:09
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class LottoDingdanAdapter extends BaseAdapter {
    private ArrayList<OrderBean> data;
    private Context context;
    private Handler handler;

    public LottoDingdanAdapter(Context context, ArrayList<OrderBean> data, Handler handler) {
        this.data = data;
        this.context = context;
        this.handler = handler;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (null == view) {
            holder = new Holder();
            view = View.inflate(context, R.layout.adapter_lotto_dingdan, null);
            holder.txt = (TextView) view.findViewById(R.id.lotto_order_txt);
            holder.count = (TextView) view.findViewById(R.id.lotto_order_count);
            holder.delete = (ImageView) view.findViewById(R.id.lotto_order_delete);
            holder.touzhuType = (TextView) view.findViewById(R.id.lotto_order_touzhuType);
            holder.isDan = (TextView) view.findViewById(R.id.lotto_order_isDan);
            holder.money = (TextView) view.findViewById(R.id.lotto_order_money);
            view.setTag(holder);
        }
        holder = (Holder) view.getTag();
        StringBuilder redSb = new StringBuilder();
        StringBuilder blueSb = new StringBuilder();
        long count = 0;
        String type;
        count = data.get(i).getCount();
        type = data.get(i).getOrderType();
        List<BaseBean> beanListRed = data.get(i).getData().get("redData");
        List<BaseBean> beanListBlue = data.get(i).getData().get("blueData");
        final int position = i;
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.i("LottoDingdanAdapter remove:" + position + "::::" + data.size());
                if (data.size() > position) {
                    data.remove(position);
                    handler.sendEmptyMessage(0);
                }
            }
        });
        boolean isDanStart = false;
        boolean isDan = false;
        String startStr = "";
        String endStr = "";
        for (int k = 0; k < beanListRed.size(); k++) {
            BaseBean bean = beanListRed.get(k);
            if (bean.isSelected()) {
                startStr = "";
                endStr = "  ";
                if (bean.isDan() && !isDanStart) {
                    isDan = true;
                    startStr = "(";
                    isDanStart = true;
                } else if (!bean.isDan() && isDanStart) {

                    redSb = new StringBuilder(redSb.substring(0, redSb.length() - 2));
                    startStr = ")";
                    isDanStart = false;
                } else {
                    if (k != 0) {
                        endStr = "   ";
                    }
                }

                redSb.append(startStr + bean.getMsg() + endStr);
            }
        }
        for (int k = 0; k < beanListBlue.size(); k++) {
            BaseBean bean = beanListBlue.get(k);
            if (bean.isSelected()) {
                startStr = "";
                endStr = "  ";
                if (bean.isDan() && !isDanStart) {
                    isDan = true;
                    startStr = "(";
                    isDanStart = true;
                } else if (!bean.isDan() && isDanStart) {
                    LogUtils.i("redSbLenth :" + blueSb.length());
                    blueSb = new StringBuilder(blueSb.substring(0, blueSb.length() - 2));
                    startStr = ")";
                    isDanStart = false;
                } else {
                    if (k != 0) {
                        endStr = "   ";
                    }
                }
                blueSb.append(startStr + bean.getMsg() + endStr);
            }
        }
        TextUtils.setStrColor(holder.txt, redSb.toString() + "   " + blueSb.toString(), "   " + blueSb.toString(), context.getResources().getColor(R.color.dantuo_zhushi_blue));

        holder.count.setText(count + "注");
        holder.money.setText(count * 2 + "元");
        if (isDan) {
            holder.touzhuType.setText("胆码投注");
            holder.isDan.setText("胆");
        } else {
            holder.touzhuType.setText(type);
            holder.isDan.setText("");
        }
        return view;
    }

    private class Holder {
        TextView txt;
        TextView touzhuType;
        TextView isDan;
        TextView money;
        TextView count;
        ImageView delete;
    }
}