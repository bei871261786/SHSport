package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.IssueBonusNumberDetailBean;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/27 16:29
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class IBDetaiNumlInfoAdapter extends BaseAdapter {
    private ArrayList<IssueBonusNumberDetailBean.DataBean.BonusListBean> data;
    private Context context;

    public IBDetaiNumlInfoAdapter(Context context, ArrayList<IssueBonusNumberDetailBean.DataBean.BonusListBean> data) {
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
            view = View.inflate(context, R.layout.adapter_ib_numberiinfo, null);
            holder.addIssueCount = (TextView) view.findViewById(R.id.issueDetail_addIssueCount);
            holder.addIssueMoney = (TextView) view.findViewById(R.id.issueDetail_addIssueMoney);
            holder.baseIssueCount = (TextView) view.findViewById(R.id.issueDetail_baseIssueCount);
            holder.baseIssueMoney = (TextView) view.findViewById(R.id.issueDetail_baseIssueMoney);
            holder.issueName = (TextView) view.findViewById(R.id.issueDetail_IssueName);
            holder.addDes = (TextView) view.findViewById(R.id.issueDetail_addDes);
            holder.baseDes = (TextView) view.findViewById(R.id.issueDetail_baseDes);
            holder.expandLayout = (LinearLayout) view.findViewById(R.id.issueDetail_expandLayout);
            view.setTag(holder);
        }
        holder = (Holder) view.getTag();
        IssueBonusNumberDetailBean.DataBean.BonusListBean bean = data.get(i);
        String lotid = bean.getLotid();
        if (lotid.equals("dlt")) {
            holder.addIssueCount.setVisibility(View.VISIBLE);
            holder.addIssueMoney.setVisibility(View.VISIBLE);
            holder.addDes.setVisibility(View.VISIBLE);
            holder.baseDes.setVisibility(View.VISIBLE);
            if ("六等奖".equals(bean.getBonusName())) {
                holder.addIssueCount.setVisibility(View.GONE);
                holder.addIssueMoney.setVisibility(View.GONE);
                holder.addDes.setVisibility(View.GONE);
            }
        } else {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.issueName.getLayoutParams();
            params.weight = 2;
            holder.issueName.setLayoutParams(params);
            holder.expandLayout.setVisibility(View.GONE);
        }
        String bonusMoney= TextUtils.checkIsNull(bean.getBonusMoney());
        holder.baseIssueMoney.setText(bonusMoney);
        holder.addIssueCount.setText(bean.getAddbonusAmount() + "");
        String addMoney= TextUtils.checkIsNull(bean.getAddbonusMoney());
        holder.addIssueMoney.setText( addMoney);
        holder.baseIssueCount.setText(bean.getBonusAmount() + "");
        holder.issueName.setText(bean.getBonusName());

        return view;
    }

    private class Holder {
        TextView issueName;
        TextView addIssueCount;
        TextView baseIssueCount;
        TextView addIssueMoney;
        TextView baseIssueMoney;
        TextView addDes;
        TextView baseDes;
        LinearLayout expandLayout;
    }
}
