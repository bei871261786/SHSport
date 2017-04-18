package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.DingYueBean;
import shlottery.gov.cn.lotterycenter.ui.view.CircleImageView;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-26-0026 15:23
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class SubscriberAdapter extends BaseAdapter {

    private DingYueBean dingYueBean;

    private int typeCount;//item的种类

    public SubscriberAdapter(DingYueBean dingYueBean) {
        this.dingYueBean = dingYueBean;
        typeCount = initTypeCount(dingYueBean);
    }

    //判断有几种类型item   类型(0-文字通知 1-资讯通知 2-专家推荐)
    private int initTypeCount(DingYueBean dingYueBean) {
        int m = 0, n = 0, p = 0;
        for (int i = 0; i < dingYueBean.getData().getList().size(); i++) {
            if (dingYueBean.getData().getList().get(i).getType() == 0) {
                m = 1;
            }
            if (dingYueBean.getData().getList().get(i).getType() == 1) {
                m = 1;
            }
            if (dingYueBean.getData().getList().get(i).getType() == 2) {
                p = 1;
            }
        }
        return m + n + p;
    }

    @Override
    public int getCount() {
        return dingYueBean.getData().getList().size();
    }

    @Override
    public Object getItem(int position) {
        return dingYueBean.getData().getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (dingYueBean.getData().getList().get(position).getType() == 0) {//文字通知
            return 0;
        } else if (dingYueBean.getData().getList().get(position).getType() == 1) {//资讯通知
            return 0;
        } else {//推荐通知
            return 1;
        }

    }

    @Override
    public int getViewTypeCount() {
        return typeCount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder0 viewholder0 = null;
        ViewHolder1 viewholder1 = null;
        if (convertView == null) {
            if (getItemViewType(position) == 0) {
                convertView = UIUtils.inflate(R.layout.dingyue_text_item);
                viewholder0 = new ViewHolder0();
                convertView.setTag(viewholder0);
                viewholder0.adapterNewlistDate = (TextView) convertView.findViewById(R.id.adapter_newlist_date);
                viewholder0.adapterNewlistTitle = (TextView) convertView.findViewById(R.id.adapter_newlist_title);
                viewholder0.adapterNewlistTab1 = (TextView) convertView.findViewById(R.id.adapter_newlist_tab1);
                viewholder0.adapterNewlistTab2 = (TextView) convertView.findViewById(R.id.adapter_newlist_tab2);
                viewholder0.adapterNewlistTab3 = (TextView) convertView.findViewById(R.id.adapter_newlist_tab3);
            } else {
                convertView = UIUtils.inflate(R.layout.adapter_famouslist);
                viewholder1 = new ViewHolder1();
                viewholder1.famousIcon = (CircleImageView) convertView.findViewById(R.id.famous_icon);
                viewholder1.famousWanfa = (TextView) convertView.findViewById(R.id.famous_wanfa);
                viewholder1.famousType = (TextView) convertView.findViewById(R.id.famous_type);
                viewholder1.famousSchemeMoney = (TextView) convertView.findViewById(R.id.famous_schemeMoney);
                viewholder1.famousForecastMoney = (TextView) convertView.findViewById(R.id.famous_forecastMoney);
                viewholder1.famousName = (TextView) convertView.findViewById(R.id.famous_name);
                viewholder1.famousDate = (TextView) convertView.findViewById(R.id.famous_date);
                convertView.setTag(viewholder1);
            }
        } else {
            if (getItemViewType(position) == 0) {
                viewholder0 = (ViewHolder0) convertView.getTag();
            } else {
                viewholder1 = (ViewHolder1) convertView.getTag();
            }
        }
        if (getItemViewType(position) == 0) {//新闻 资讯
            if (!StringUtils.isEmpty(dingYueBean.getData().getList().get(position).getTitle())) {
                viewholder0.adapterNewlistTitle.setText(dingYueBean.getData().getList().get(position).getTitle());
                viewholder0.adapterNewlistDate.setText(dingYueBean.getData().getList().get(position).getSendTime());
            }
            if (!StringUtils.isEmpty(dingYueBean.getData().getList().get(position).getTags())) {
                String arr[] = dingYueBean.getData().getList().get(position).getTags().split("\\,");
                if (arr.length == 1) {
                    viewholder0.adapterNewlistTab1.setVisibility(View.VISIBLE);
                    viewholder0.adapterNewlistTab1.setText(arr[0]);
                    viewholder0.adapterNewlistTab2.setVisibility(View.INVISIBLE);
                    viewholder0.adapterNewlistTab3.setVisibility(View.INVISIBLE);
                }
                if (arr.length == 2) {
                    viewholder0.adapterNewlistTab1.setVisibility(View.VISIBLE);
                    viewholder0.adapterNewlistTab1.setText(arr[0]);
                    viewholder0.adapterNewlistTab2.setVisibility(View.VISIBLE);
                    viewholder0.adapterNewlistTab2.setText(arr[1]);
                    viewholder0.adapterNewlistTab3.setVisibility(View.INVISIBLE);
                }
                if (arr.length >= 3) {
                    viewholder0.adapterNewlistTab1.setVisibility(View.VISIBLE);
                    viewholder0.adapterNewlistTab1.setText(arr[0]);
                    viewholder0.adapterNewlistTab2.setVisibility(View.VISIBLE);
                    viewholder0.adapterNewlistTab2.setText(arr[1]);
                    viewholder0.adapterNewlistTab3.setVisibility(View.VISIBLE);
                    viewholder0.adapterNewlistTab3.setText(arr[2]);
                }

            } else {
                viewholder0.adapterNewlistTab1.setVisibility(View.INVISIBLE);
                viewholder0.adapterNewlistTab2.setVisibility(View.INVISIBLE);
                viewholder0.adapterNewlistTab3.setVisibility(View.INVISIBLE);
            }
        } else {
            if (!StringUtils.isEmpty(dingYueBean.getData().getList().get(position).getTitle())) {
                viewholder1.famousWanfa.setText(dingYueBean.getData().getList().get(position).getLotName()+" "+dingYueBean.getData().getList().get(position).getPlayType());
                viewholder1.famousDate.setText(dingYueBean.getData().getList().get(position).getSendTime());
                viewholder1.famousType.setVisibility(View.INVISIBLE);
            }
            if (!StringUtils.isEmpty(dingYueBean.getData().getList().get(position).getTotalMoney())) {
                viewholder1.famousSchemeMoney.setText("方案金额 " + dingYueBean.getData().getList().get(position).getTotalMoney() + " 元");
                TextUtils.setStrColor(viewholder1.famousSchemeMoney, "方案金额 " + dingYueBean.getData().getList().get(position).getTotalMoney() + " 元", dingYueBean.getData().getList().get(position).getTotalMoney(), BaseApplication.getApplication().getResources().getColor(R.color.select_red));
                viewholder1.famousSchemeMoney.setVisibility(View.VISIBLE);
            } else {
                viewholder1.famousSchemeMoney.setVisibility(View.INVISIBLE);
            }
            if (!StringUtils.isEmpty(dingYueBean.getData().getList().get(position).getBonusEstimate())) {
                viewholder1.famousForecastMoney.setText("最高奖金预测 " + dingYueBean.getData().getList().get(position).getBonusEstimate() + " 元");
                TextUtils.setStrColor(viewholder1.famousForecastMoney, "最高奖金预测 " + dingYueBean.getData().getList().get(position).getBonusEstimate() + " 元", dingYueBean.getData().getList().get(position).getBonusEstimate(), BaseApplication.getApplication().getResources().getColor(R.color.select_red));
                viewholder1.famousForecastMoney.setVisibility(View.VISIBLE);
            } else {
                viewholder1.famousForecastMoney.setVisibility(View.INVISIBLE);
            }
            if (!StringUtils.isEmpty(dingYueBean.getData().getList().get(position).getName())) {
                viewholder1.famousName.setText(dingYueBean.getData().getList().get(position).getName());
                viewholder1.famousName.setVisibility(View.VISIBLE);
            } else {
                viewholder1.famousName.setVisibility(View.INVISIBLE);
            }
            if (!StringUtils.isEmpty(dingYueBean.getData().getList().get(position).getLogo())) {
                Picasso.with(BaseApplication.getApplication()).load(dingYueBean.getData().getList().get(position).getLogo()).into(viewholder1.famousIcon);
                viewholder1.famousIcon.setVisibility(View.VISIBLE);
            } else {
                viewholder1.famousIcon.setVisibility(View.INVISIBLE);
            }
        }
        return convertView;
    }


    static class ViewHolder0 {
        TextView adapterNewlistTitle;
        TextView adapterNewlistTab1;
        TextView adapterNewlistTab2;
        TextView adapterNewlistTab3;
        TextView adapterNewlistDate;

    }

    static class ViewHolder1 {
        CircleImageView famousIcon;
        TextView famousWanfa;
        TextView famousType;
        TextView famousSchemeMoney;
        TextView famousForecastMoney;
        TextView famousName;
        TextView famousDate;
    }

}
