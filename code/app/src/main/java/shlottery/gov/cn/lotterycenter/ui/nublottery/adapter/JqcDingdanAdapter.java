package shlottery.gov.cn.lotterycenter.ui.nublottery.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.SfcDingdanBean;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-08-0008 17:05
 * 描    述：任选九以及胜负14共用的adapter
 * 修订历史：
 * ================================================
 */

public class JqcDingdanAdapter extends BaseAdapter {
    private Context mContext;
    private SfcDingdanBean mSfcDingdanBean;

    public JqcDingdanAdapter(Context mContext, SfcDingdanBean mSfcDingdanBean) {
        this.mContext = mContext;
        this.mSfcDingdanBean = mSfcDingdanBean;
    }

    @Override
    public int getCount() {
        return mSfcDingdanBean.getMatchListBeans().size();
    }

    @Override
    public Object getItem(int position) {
        if (mSfcDingdanBean == null || mSfcDingdanBean.getMatchListBeans() == null) {
            return 0;
        }
        return mSfcDingdanBean.getMatchListBeans().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.jqc_dingdan_item, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        long time = mSfcDingdanBean.getMatchListBeans().get(position).getMatchTime();
        mViewHolder.sfcddPosition1Tv.setText(2 * position + 1 + "");
        mViewHolder.sfcddPosition2Tv.setText(2 * position + 2 + "");
        mViewHolder.sfcDdLeaguenameTv.setText(mSfcDingdanBean.getMatchListBeans().get(position).getLeagueName());
        mViewHolder.ddStoptimeTv.setText(DateUtils.getPl5DateAndTime(time * 1000));
        mViewHolder.jqcddHostnameTv.setText(mSfcDingdanBean.getMatchListBeans().get(position).getHostName());
        mViewHolder.jqcddVisitnameTv.setText(mSfcDingdanBean.getMatchListBeans().get(position).getVisitName());
        if (mSfcDingdanBean.getMatchListBeans().get(position).getCheckedHashMap().get(0)) {
            mViewHolder.jqchostDd0Tv.setBackgroundResource(R.mipmap.dingdanred_bg);
            mViewHolder.jqchostDd0Tv.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            mViewHolder.jqchostDd0Tv.setBackgroundResource(R.mipmap.dingdanwhite_bg);
            mViewHolder.jqchostDd0Tv.setTextColor(Color.parseColor("#cf0100"));
        }

        if (mSfcDingdanBean.getMatchListBeans().get(position).getCheckedHashMap().get(1)) {
            mViewHolder.jqchostDd1Tv.setBackgroundResource(R.mipmap.dingdanred_bg);
            mViewHolder.jqchostDd1Tv.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            mViewHolder.jqchostDd1Tv.setBackgroundResource(R.mipmap.dingdanwhite_bg);
            mViewHolder.jqchostDd1Tv.setTextColor(Color.parseColor("#cf0100"));
        }

        if (mSfcDingdanBean.getMatchListBeans().get(position).getCheckedHashMap().get(2)) {
            mViewHolder.jqchostDd2Tv.setBackgroundResource(R.mipmap.dingdanred_bg);
            mViewHolder.jqchostDd2Tv.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            mViewHolder.jqchostDd2Tv.setBackgroundResource(R.mipmap.dingdanwhite_bg);
            mViewHolder.jqchostDd2Tv.setTextColor(Color.parseColor("#cf0100"));
        }
        if (mSfcDingdanBean.getMatchListBeans().get(position).getCheckedHashMap().get(3)) {
            mViewHolder.jqchostDd3Tv.setBackgroundResource(R.mipmap.dingdanred_bg);
            mViewHolder.jqchostDd3Tv.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            mViewHolder.jqchostDd3Tv.setBackgroundResource(R.mipmap.dingdanwhite_bg);
            mViewHolder.jqchostDd3Tv.setTextColor(Color.parseColor("#cf0100"));
        }


        if (mSfcDingdanBean.getMatchListBeans().get(position).getCheckedHashMap().get(4)) {
            mViewHolder.jqcvisitDd0Tv.setBackgroundResource(R.mipmap.dingdanred_bg);
            mViewHolder.jqcvisitDd0Tv.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            mViewHolder.jqcvisitDd0Tv.setBackgroundResource(R.mipmap.dingdanwhite_bg);
            mViewHolder.jqcvisitDd0Tv.setTextColor(Color.parseColor("#cf0100"));
        }

        if (mSfcDingdanBean.getMatchListBeans().get(position).getCheckedHashMap().get(5)) {
            mViewHolder.jqcvisitDd1Tv.setBackgroundResource(R.mipmap.dingdanred_bg);
            mViewHolder.jqcvisitDd1Tv.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            mViewHolder.jqcvisitDd1Tv.setBackgroundResource(R.mipmap.dingdanwhite_bg);
            mViewHolder.jqcvisitDd1Tv.setTextColor(Color.parseColor("#cf0100"));
        }

        if (mSfcDingdanBean.getMatchListBeans().get(position).getCheckedHashMap().get(6)) {
            mViewHolder.jqcvisitDd2Tv.setBackgroundResource(R.mipmap.dingdanred_bg);
            mViewHolder.jqcvisitDd2Tv.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            mViewHolder.jqcvisitDd2Tv.setBackgroundResource(R.mipmap.dingdanwhite_bg);
            mViewHolder.jqcvisitDd2Tv.setTextColor(Color.parseColor("#cf0100"));
        }
        if (mSfcDingdanBean.getMatchListBeans().get(position).getCheckedHashMap().get(7)) {
            mViewHolder.jqcvisitDd3Tv.setBackgroundResource(R.mipmap.dingdanred_bg);
            mViewHolder.jqcvisitDd3Tv.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            mViewHolder.jqcvisitDd3Tv.setBackgroundResource(R.mipmap.dingdanwhite_bg);
            mViewHolder.jqcvisitDd3Tv.setTextColor(Color.parseColor("#cf0100"));
        }

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.sfcdd_position1_tv)
        TextView sfcddPosition1Tv;
        @BindView(R.id.sfcdd_position2_tv)
        TextView sfcddPosition2Tv;
        @BindView(R.id.sfc_dd_leaguename_tv)
        TextView sfcDdLeaguenameTv;
        @BindView(R.id.dd_stoptime_tv)
        TextView ddStoptimeTv;
        @BindView(R.id.jqcdd_hostname_tv)
        TextView jqcddHostnameTv;
        @BindView(R.id.jqcddvs_tv)
        TextView jqcddvsTv;
        @BindView(R.id.jqcdd_visitname_tv)
        TextView jqcddVisitnameTv;
        @BindView(R.id.jqchost_zhu_tv)
        TextView jqchostZhuTv;
        @BindView(R.id.jqchost_dd0_tv)
        TextView jqchostDd0Tv;
        @BindView(R.id.jqchost_dd1_tv)
        TextView jqchostDd1Tv;
        @BindView(R.id.jqchost_dd2_tv)
        TextView jqchostDd2Tv;
        @BindView(R.id.jqchost_dd3_tv)
        TextView jqchostDd3Tv;
        @BindView(R.id.jqcvisit_ke_tv)
        TextView jqcvisitKeTv;
        @BindView(R.id.jqcvisit_dd0_tv)
        TextView jqcvisitDd0Tv;
        @BindView(R.id.jqcvisit_dd1_tv)
        TextView jqcvisitDd1Tv;
        @BindView(R.id.jqcvisit_dd2_tv)
        TextView jqcvisitDd2Tv;
        @BindView(R.id.jqcvisit_dd3_tv)
        TextView jqcvisitDd3Tv;
        @BindView(R.id.dd_dan_ll)
        LinearLayout ddDanLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
