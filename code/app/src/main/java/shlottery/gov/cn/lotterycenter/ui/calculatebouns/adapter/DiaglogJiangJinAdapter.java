package shlottery.gov.cn.lotterycenter.ui.calculatebouns.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.BounsMoneyBean;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-01-0001 15:59
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class DiaglogJiangJinAdapter extends BaseAdapter {

    private Context context;
    private BounsMoneyBean bounsMoneyBean;

    public DiaglogJiangJinAdapter(Context context, BounsMoneyBean bounsMoneyBean) {
        this.context = context;
        this.bounsMoneyBean = bounsMoneyBean;
    }

    @Override
    public int getCount() {
        if (bounsMoneyBean == null || bounsMoneyBean.getData().getBonusList() == null) {
            return 0;
        }
        return bounsMoneyBean.getData().getBonusList().size();
    }

    @Override
    public Object getItem(int position) {
        return bounsMoneyBean.getData().getBonusList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.zhongjiang_lsv_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.jiangjinItemTv.setText(bounsMoneyBean.getData().getBonusList().get(position).getBonusMoney());
        viewHolder.jiangjiTv.setText(bounsMoneyBean.getData().getBonusList().get(position).getBonusName());
        viewHolder.zhongjiangZhushuTv.setText(bounsMoneyBean.getData().getBonusList().get(position).getBonusAmount()+"");
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.jiangji_tv)
        TextView jiangjiTv;
        @BindView(R.id.zhongjiang_zhushu_tv)
        TextView zhongjiangZhushuTv;
        @BindView(R.id.jiangjin_item_tv)
        TextView jiangjinItemTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
