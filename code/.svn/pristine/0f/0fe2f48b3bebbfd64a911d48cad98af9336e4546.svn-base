package shlottery.gov.cn.lotterycenter.ui.calculatebouns.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

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

public class DiaglogJiangJinZhuijiaAdapter extends BaseAdapter {

    private Context context;
    private BounsMoneyBean bounsMoneyBean;
    private List<List<String>> bouns = new ArrayList<>();

    public DiaglogJiangJinZhuijiaAdapter(Context context, BounsMoneyBean bounsMoneyBean) {
        this.context = context;
        this.bounsMoneyBean = bounsMoneyBean;
        initBounsList(bounsMoneyBean);
    }

    private void initBounsList(BounsMoneyBean bounsMoneyBean) {
        List<String> bouns1 = null;
        List<String> bouns2 = null;
        List<String> bouns3 = null;
        List<String> bouns4 = null;
        List<String> bouns5 = null;
        List<String> bouns6 = null;
        for (int i = 0; i < bounsMoneyBean.getData().getBonusList().size(); i++) {
            if (bounsMoneyBean.getData().getBonusList().get(i).getBonusLevel() == 1 || bounsMoneyBean.getData().getBonusList().get(i).getBonusLevel() == 11) {//一等奖
                if (bouns1 == null) {
                    bouns1 = new ArrayList<>();
                }
                Logger.e(bounsMoneyBean.getData().getBonusList().get(i).getBonusMoney() + "长度");
                bouns1.add(bounsMoneyBean.getData().getBonusList().get(i).getBonusMoney());
            } else if (bounsMoneyBean.getData().getBonusList().get(i).getBonusLevel() == 2 || bounsMoneyBean.getData().getBonusList().get(i).getBonusLevel() == 12) {
                if (bouns2 == null) {
                    bouns2 = new ArrayList<>();
                }
                bouns2.add(bounsMoneyBean.getData().getBonusList().get(i).getBonusMoney());
            } else if (bounsMoneyBean.getData().getBonusList().get(i).getBonusLevel() == 3 || bounsMoneyBean.getData().getBonusList().get(i).getBonusLevel() == 13) {
                if (bouns3 == null) {
                    bouns3 = new ArrayList<>();
                }
                bouns3.add(bounsMoneyBean.getData().getBonusList().get(i).getBonusMoney());
            } else if (bounsMoneyBean.getData().getBonusList().get(i).getBonusLevel() == 4 || bounsMoneyBean.getData().getBonusList().get(i).getBonusLevel() == 14) {
                if (bouns4 == null) {
                    bouns4 = new ArrayList<>();
                }
                bouns4.add(bounsMoneyBean.getData().getBonusList().get(i).getBonusMoney());
            } else if (bounsMoneyBean.getData().getBonusList().get(i).getBonusLevel() == 5 || bounsMoneyBean.getData().getBonusList().get(i).getBonusLevel() == 15) {
                if (bouns5 == null) {
                    bouns5 = new ArrayList<>();
                }
                bouns5.add(bounsMoneyBean.getData().getBonusList().get(i).getBonusMoney());
            } else if (bounsMoneyBean.getData().getBonusList().get(i).getBonusLevel() == 6) {//六等奖
                if (bouns6 == null) {
                    bouns6 = new ArrayList<>();
                }
                bouns6.add(bounsMoneyBean.getData().getBonusList().get(i).getBonusMoney());
            }

        }
        bouns.clear();
        if (bouns1 != null) {
            bouns.add(bouns1);
        }
        if (bouns2 != null) {
            bouns.add(bouns2);
        }
        if (bouns3 != null) {
            bouns.add(bouns3);
        }
        if (bouns4 != null) {
            bouns.add(bouns4);
        }
        if (bouns5 != null) {
            bouns.add(bouns5);
        }
        if (bouns6 != null) {
            bouns.add(bouns6);
        }
    }

    @Override
    public int getCount() {
        if (bounsMoneyBean == null || bounsMoneyBean.getData().getBonusList() == null) {
            return 0;
        }
        return bouns.size();
    }

    @Override
    public Object getItem(int position) {
        return bouns.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.zhongjiang_zhuitou_lsv_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.jiangjiTv.setText(bounsMoneyBean.getData().getBonusList().get(position).getBonusName());
        if (bouns.get(position).size() > 1) {

            Logger.e(bouns.get(position).size() + "----" + position);
            viewHolder.jiangjinItem1Tv.setText(bouns.get(position).get(0));
            viewHolder.zhongjiangZhushu1Tv.setText(bounsMoneyBean.getData().getBonusList().get(position).getBonusAmount() + "");
            viewHolder.jiangjinItem2Tv.setText(bouns.get(position).get(1));
            viewHolder.zhongjiangZhushu2Tv.setText(bounsMoneyBean.getData().getBonusList().get(position).getBonusAmount() + "");
            viewHolder.jiangjinItem2Tv.setVisibility(View.VISIBLE);
            viewHolder.zuijiaTv.setVisibility(View.VISIBLE);
            viewHolder.zhongjiangZhushu2Tv.setVisibility(View.VISIBLE);
        } else {
            Logger.e(bouns.get(position).size() + "----" + position);
            viewHolder.jiangjinItem1Tv.setText(bouns.get(position).get(0));
            viewHolder.zhongjiangZhushu1Tv.setText(bounsMoneyBean.getData().getBonusList().get(position).getBonusAmount() + "");
            viewHolder.jiangjinItem2Tv.setVisibility(View.GONE);
            viewHolder.zhongjiangZhushu2Tv.setVisibility(View.GONE);
            viewHolder.zuijiaTv.setVisibility(View.GONE);
        }

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.jiangji_tv)
        TextView jiangjiTv;
        @BindView(R.id.zhongjiang_zhushu1_tv)
        TextView zhongjiangZhushu1Tv;
        @BindView(R.id.zhongjiang_zhushu2_tv)
        TextView zhongjiangZhushu2Tv;
        @BindView(R.id.jiangjin_item1_tv)
        TextView jiangjinItem1Tv;
        @BindView(R.id.jiangjin_item2_tv)
        TextView jiangjinItem2Tv;
        @BindView(R.id.zuijia_tv)
        TextView zuijiaTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
