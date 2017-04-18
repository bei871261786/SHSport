package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusSoccerDetailBean;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/28 14:56
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class IBDetaiSoccerwinInfoAdapter extends BaseAdapter {

    private Context context;
    private List<IssueBonusSoccerDetailBean.DataBean.BonusListBean> mDatas;

    public IBDetaiSoccerwinInfoAdapter(Context context, ArrayList<IssueBonusSoccerDetailBean.DataBean.BonusListBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (null == convertView) {
            convertView = UIUtils.inflate(R.layout.adapter_ib_wininfo);
            holder = new Holder();
            holder.winLevel = (TextView) convertView.findViewById(R.id.issueDetail_soccer_winLevel);
            holder.winCount = (TextView) convertView.findViewById(R.id.issueDetail_soccer_winCount);
            holder.winMoney = (TextView) convertView.findViewById(R.id.issueDetail_soccer_winMoney);
            convertView.setTag(holder);
        }
        holder = (Holder) convertView.getTag();
        IssueBonusSoccerDetailBean.DataBean.BonusListBean bean = mDatas.get(position);

        holder.winLevel.setText(bean.getBonusName());
        holder.winCount.setText(bean.getBonusAmount() + "");
        int money = bean.getBonusMoney();
        if (money <= 0) {
            holder.winMoney.setText("--");
        } else {
            holder.winMoney.setText(money + "");
        }
        return convertView;
    }

    private class Holder {
        TextView winLevel;
        TextView winCount;
        TextView winMoney;


    }
}

