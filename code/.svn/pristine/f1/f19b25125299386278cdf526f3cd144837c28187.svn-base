package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusSh115DetailBean;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/27 16:29
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class IBDetaiSh115lInfoAdapter extends BaseAdapter {
    private ArrayList<IssueBonusSh115DetailBean.DataBean.IssueListBean> data;
    private Context context;

    public IBDetaiSh115lInfoAdapter(Context context, ArrayList<IssueBonusSh115DetailBean.DataBean.IssueListBean> data) {
        this.context = context;
        this.data = data;
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
        if (view == null) {
            holder = new Holder();
            view = View.inflate(context, R.layout.adapter_ib_sh115beriinfo, null);
            holder.issueno = (TextView) view.findViewById(R.id.issueDetail_IssueNo);
            holder.date = (TextView) view.findViewById(R.id.issueDetail_IssueDate);
            holder.number = (TextView) view.findViewById(R.id.issueDetail_IssueNumber);

            view.setTag(holder);
        }
        holder = (Holder) view.getTag();
        IssueBonusSh115DetailBean.DataBean.IssueListBean bean = data.get(i);

        holder.issueno.setText(bean.getIssueNo() + "");
        holder.date.setText(bean.getBonusTime() + "");
        holder.number.setText(bean.getBonusCode() + "");
        return view;
    }

    private class Holder {
        TextView issueno;
        TextView date;
        TextView number;

    }
}
