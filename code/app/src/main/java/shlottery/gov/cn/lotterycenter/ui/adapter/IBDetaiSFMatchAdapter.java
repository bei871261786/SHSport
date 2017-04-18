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

public class IBDetaiSFMatchAdapter extends BaseAdapter {

    private Context context;
    private List<IssueBonusSoccerDetailBean.DataBean.MatchListBean> mDatas;


    public IBDetaiSFMatchAdapter(Context context, ArrayList<IssueBonusSoccerDetailBean.DataBean.MatchListBean> mDatas) {
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
            convertView = UIUtils.inflate(R.layout.adapter_ib_soccerresult_sf);
            holder = new Holder();
            holder.hostname = (TextView) convertView.findViewById(R.id.issueDetail_soccer_hostName);
            holder.visitname = (TextView) convertView.findViewById(R.id.issueDetail_soccer_visitName);
            holder.leaguname = (TextView) convertView.findViewById(R.id.issueDetail_soccer_leagueName);
            holder.ordinal = (TextView) convertView.findViewById(R.id.issueDetail_ordinal);
            holder.result = (TextView) convertView.findViewById(R.id.issueDetail_soccer_result);
            holder.goal = (TextView) convertView.findViewById(R.id.issueDetail_soccer_goal);
            convertView.setTag(holder);
        }
        holder = (Holder) convertView.getTag();
        IssueBonusSoccerDetailBean.DataBean.MatchListBean bean = mDatas.get(position);
        holder.hostname.setText(bean.getHostName());
        holder.visitname.setText(bean.getVisitName());
        holder.goal.setText(bean.getHostGoal() + ":" + bean.getVisitGoal());
        holder.leaguname.setText(bean.getLeagueName());
        holder.ordinal.setText(position + 1 + "");
        String result = bean.getResult() + "";
        result = result.substring(1, 2);
        holder.result.setText(result);
        return convertView;
    }

    private class Holder {
        TextView hostname;
        TextView goal;
        TextView visitname;
        TextView leaguname;
        TextView result;
        TextView ordinal;
    }
}

