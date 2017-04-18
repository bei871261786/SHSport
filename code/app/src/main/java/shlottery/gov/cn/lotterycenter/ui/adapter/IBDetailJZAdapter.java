package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusJCDetailBean;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
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
public class IBDetailJZAdapter extends BaseAdapter {

    private Context context;
    private List<IssueBonusJCDetailBean.DataBean.MatchListBean> mDatas;

    public IBDetailJZAdapter(Context context, ArrayList<IssueBonusJCDetailBean.DataBean.MatchListBean> mDatas) {
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
        LogUtils.i("jinzu getView");
        Holder holder = null;
        if (null == convertView) {
            convertView = UIUtils.inflate(R.layout.adapter_ib_jincaisoccer);
            holder = new Holder();
            holder.spf_result = (TextView) convertView.findViewById(R.id.issueDetail_spf_result);
            holder.bqc_result = (TextView) convertView.findViewById(R.id.issueDetail_bqc_result);
            holder.bf_result = (TextView) convertView.findViewById(R.id.issueDetail_bf_result);
            holder.zjq_result = (TextView) convertView.findViewById(R.id.issueDetail_zjq_result);
            holder.rqspf_result = (TextView) convertView.findViewById(R.id.issueDetail_rqspf_result);
            holder.hostname = (TextView) convertView.findViewById(R.id.issueDetail_hostname);
            holder.visitname = (TextView) convertView.findViewById(R.id.issueDetail_visitname);
            holder.ping = (TextView) convertView.findViewById(R.id.issueDetail_ping);
            holder.ping2 = (TextView) convertView.findViewById(R.id.issueDetail_ping2);
            holder.weekno = (TextView) convertView.findViewById(R.id.issueDetail_weekno);
            holder.leaguname = (TextView) convertView.findViewById(R.id.issueDetail_leagueName);
            convertView.setTag(holder);
        }
        holder = (Holder) convertView.getTag();
        LogUtils.i("issuebonus adapter:" + holder.hostname + ":::" + mDatas.get(position).getHostName() + ":::" + mDatas.get(position).getLeagueName());
        holder.hostname.setText(mDatas.get(position).getHostName());
        holder.visitname.setText(mDatas.get(position).getVisitName());
        holder.weekno.setText(mDatas.get(position).getWeekNo());
        holder.leaguname.setText(mDatas.get(position).getLeagueName());
        long time = mDatas.get(position).getMatchTime();
        String pingText = "";
        String pingHalfText = "";
        for (int i = 0; i < mDatas.get(position).getBonusList().size(); i++) {
            String result = (mDatas.get(position).getBonusList().get(i).getBonusResult() == null) ? "--" : mDatas.get(position).getBonusList().get(i).getBonusResult();
            LogUtils.i("results item total:" + position + "::::" + i + ":::::::" + result);
            if (mDatas.get(position).getBonusList().get(i).getLotId().equals("jzspf")) {
                int hand = (int) mDatas.get(position).getBonusList().get(i).getHand();
                if (hand >= 0) {
                    TextUtils.setStrColor(holder.spf_result, "[+" + hand + "]" + result, "[" + hand + "]", context.getResources().getColor(R.color.select_red));
                } else if (hand < 0) {
                    TextUtils.setStrColor(holder.spf_result, "[" + hand + "]" + result, "[" + hand + "]", context.getResources().getColor(R.color.standard_textcolor_c9));
                }
                holder.spf_result.setText(result);

            }
            if (mDatas.get(position).getBonusList().get(i).getLotId().equals("jzxspf")) {
                int hand = (int) mDatas.get(position).getBonusList().get(i).getHand();
                if (hand >= 0) {
                    TextUtils.setStrColor(holder.rqspf_result, "[+" + hand + "]" + result, "[+" + hand + "]", context.getResources().getColor(R.color.select_red));
                } else if (hand < 0) {
                    TextUtils.setStrColor(holder.rqspf_result, "[" + hand + "]" + result, "[" + hand + "]", context.getResources().getColor(R.color.standard_textcolor_c9));
                }
            }
            if (mDatas.get(position).getBonusList().get(i).getLotId().equals("jzbf")) {
                holder.bf_result.setText(result);
            }
            if (mDatas.get(position).getBonusList().get(i).getLotId().equals("jzbqc")) {
                holder.bqc_result.setText(result);
            }
            if (mDatas.get(position).getBonusList().get(i).getLotId().equals("jzjqs")) {
                holder.zjq_result.setText(result);
            }

            LogUtils.i("");
            if (holder.spf_result.getText() == null || holder.spf_result.getText().toString().isEmpty()) {
                holder.spf_result.setText("--");
            }
            if (holder.bf_result.getText() == null || holder.bf_result.getText().toString().isEmpty()) {
                holder.bf_result.setText("--");
            }
            if (holder.bqc_result.getText() == null || holder.bqc_result.getText().toString().isEmpty()) {
                holder.bqc_result.setText("--");
            }
            if (holder.rqspf_result.getText() == null || holder.rqspf_result.getText().toString().isEmpty()) {
                holder.rqspf_result.setText("--");
            }
            if (holder.zjq_result.getText() == null || holder.zjq_result.getText().toString().isEmpty()) {
                holder.zjq_result.setText("--");
            }
        }
        int hostGoal = mDatas.get(position).getHostGoal();
        int visitGoal = mDatas.get(position).getVisitGoal();
        int haltHostGoal = mDatas.get(position).getHostHalfGoal();
        int haltVisitGoal = mDatas.get(position).getVisitHalfGoal();
        pingText = hostGoal + "-" + visitGoal;
        pingHalfText = "(" + haltHostGoal + "-" + haltVisitGoal + ")";
        holder.ping2.setText(pingHalfText);
        holder.ping.setText(pingText);
        if (hostGoal > visitGoal) {
            holder.ping.setTextColor(context.getResources().getColor(R.color.select_red));
        } else if (hostGoal == visitGoal) {
            holder.ping.setTextColor(context.getResources().getColor(R.color.standard_textcolor_c7));
        } else if (hostGoal < visitGoal) {
            holder.ping.setTextColor(context.getResources().getColor(R.color.standard_textcolor_c9));
        }
        return convertView;
    }

    class Holder {
        TextView hostname;
        TextView ping;
        TextView ping2;
        TextView visitname;
        TextView weekno;
        TextView leaguname;
        TextView spf_result;
        TextView rqspf_result;
        TextView bqc_result;
        TextView bf_result;
        TextView zjq_result;
    }
}

