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
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
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

public class IBDetaiJqcMatchAdapter extends BaseAdapter {

    private Context context;
    private List<IssueBonusSoccerDetailBean.DataBean.MatchListBean> mDatas;


    public IBDetaiJqcMatchAdapter(Context context, ArrayList<IssueBonusSoccerDetailBean.DataBean.MatchListBean> mDatas) {
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
            convertView = UIUtils.inflate(R.layout.adapter_ib_soccerresult_jqc);
            holder = new Holder();
            holder.hostname = (TextView) convertView.findViewById(R.id.issueDetail_soccer_hostName);
            holder.visitname = (TextView) convertView.findViewById(R.id.issueDetail_soccer_visitName);
            holder.leaguname = (TextView) convertView.findViewById(R.id.issueDetail_soccer_leagueName);
            holder.ordinal_1 = (TextView) convertView.findViewById(R.id.issueDetail_ordinal_1);
            holder.ordinal_2 = (TextView) convertView.findViewById(R.id.issueDetail_ordinal_2);
            holder.bcresult = (TextView) convertView.findViewById(R.id.issueDetail_soccer_bcScore);
            holder.qcresult = (TextView) convertView.findViewById(R.id.issueDetail_soccer_qcScore);
            holder.hostgoal = (TextView) convertView.findViewById(R.id.issueDetail_soccer_hostGoal);
            holder.visitgoal = (TextView) convertView.findViewById(R.id.issueDetail_soccer_visitGoal);
            convertView.setTag(holder);
        }
        holder = (Holder) convertView.getTag();
        IssueBonusSoccerDetailBean.DataBean.MatchListBean bean = mDatas.get(position);
        holder.hostname.setText(bean.getHostName());
        holder.visitname.setText(bean.getVisitName());
        holder.hostgoal.setText(bean.getHostGoal()+"");
        holder.visitgoal.setText(bean.getVisitGoal()+"");
        holder.leaguname.setText(bean.getLeagueName());
        holder.ordinal_1.setText(2 * position + 1 + "");
        holder.ordinal_2.setText(2 * (position + 1) + "");
        LogUtils.i("result size:" + bean.getResult().size());
        holder.bcresult.setText(bean.getResult().get(0) + "");
        holder.qcresult.setText(bean.getResult().get(1) + "");
        return convertView;
    }

    private class Holder {
        TextView hostname;
        TextView hostgoal;
        TextView visitgoal;
        TextView visitname;
        TextView leaguname;
        TextView bcresult;
        TextView qcresult;
        TextView ordinal_1;
        TextView ordinal_2;
    }
}

