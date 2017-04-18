package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.ZhudanDetailBean;
import shlottery.gov.cn.lotterycenter.utils.ZhuDanUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-24-0024 17:31
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class NumZDDetailAdapter extends BaseAdapter {
    private ZhudanDetailBean zhuDanDetailBean;
    private Context context;

    public NumZDDetailAdapter(ZhudanDetailBean zhuDanDetailBean, Context context) {
        this.zhuDanDetailBean = zhuDanDetailBean;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (zhuDanDetailBean == null) {
            return 0;
        }
        return zhuDanDetailBean.getData().getCodeList().size();
    }

    @Override
    public Object getItem(int position) {
        return zhuDanDetailBean.getData().getCodeList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.zhudandetail_num_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        viewHolder.danMoneyTv.setText(zhuDanDetailBean.getData().getCodeList().get(position).getPlayType() + "  " + zhuDanDetailBean.getData().getCodeList().get(position).getAmount() + "注" + "  " + zhuDanDetailBean.getData().getCodeList().get(position).getMoney() + "元");
//        viewHolder.numDetailTv.setText(zhuDanDetailBean.getData().getCodeList().get(position).getStakeCode());
        viewHolder.danMoneyTv.setText(ZhuDanUtils.getZhuDanDetail(position,zhuDanDetailBean));
        ZhuDanUtils.setNumDetail(position,viewHolder.numDetailTv,zhuDanDetailBean);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.num_detail_tv)
        TextView numDetailTv;
        @BindView(R.id.dan_money_tv)
        TextView danMoneyTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
