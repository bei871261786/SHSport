package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import shlottery.gov.cn.lotterycenter.R;

/**
 * @创建者 Tyler Wang.
 * @创建时间 2017/3/28  18:21.
 * @描述 ${混合过关推荐列表中下注的gridview适配器}.
 */
public class JzhhGridViewAdapter extends BaseAdapter {

    private List<String> mStakes;
    //是否是总进球数
    private boolean      mIsJQS;

    public JzhhGridViewAdapter(List<String> stakes, boolean isJQS) {
        this.mStakes = stakes;
        this.mIsJQS = isJQS;
    }

    @Override
    public int getCount() {
        return mStakes == null ? 0 : mStakes.size();
    }

    @Override
    public String getItem(int position) {
        return mStakes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String stake = getItem(position);
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rmd_jzhh_stake, parent, false);
        TextView tvItemJzhhStake = (TextView) itemView.findViewById(R.id.tv_item_jzhh_stake);
        tvItemJzhhStake.setText(mIsJQS ? stake + "球" : stake);
        return itemView;
    }
}
