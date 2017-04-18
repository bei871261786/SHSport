package shlottery.gov.cn.lotterycenter.ui.nublottery.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.SfcDingdanBean;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-08-0008 17:05
 * 描    述：胜负14订单的adapter
 * 修订历史：
 * ================================================
 */

public class SfcDingdanAdapter extends BaseAdapter {
    private Context mContext;
    private SfcDingdanBean mSfcDingdanBean;
    private int ActivityType;//activity类型

    public SfcDingdanAdapter(Context mContext, SfcDingdanBean mSfcDingdanBean, int ActivityType) {
        this.mContext = mContext;
        this.mSfcDingdanBean = mSfcDingdanBean;
        this.ActivityType = ActivityType;
    }

    @Override
    public int getCount() {
        if (mSfcDingdanBean==null||mSfcDingdanBean.getMatchListBeans()==null){
            return 0;
        }
        return mSfcDingdanBean.getMatchListBeans().size();
    }

    @Override
    public Object getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.sfc_dingdan_item, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        long time = mSfcDingdanBean.getMatchListBeans().get(position).getMatchTime();
        mViewHolder.sfcddPositionTv.setText(position + 1 + "");
        mViewHolder.sfcDdLeaguenameTv.setText(mSfcDingdanBean.getMatchListBeans().get(position).getLeagueName());
        mViewHolder.ddStoptimeTv.setText(DateUtils.getPl5DateAndTime(time * 1000));
        mViewHolder.sfcddHostnameTv.setText(mSfcDingdanBean.getMatchListBeans().get(position).getHostName());
        mViewHolder.sfcddVisitnameTv.setText(mSfcDingdanBean.getMatchListBeans().get(position).getVisitName());
        if (ActivityType == Configs.ACTIVITYTYPE_R9) {
            mViewHolder.ddDanLl.setVisibility(View.VISIBLE);
            if (mSfcDingdanBean.getMatchListBeans().get(position).isChecked()){
                mViewHolder.ddDanTv.setBackgroundResource(R.drawable.sfcdan_checkeddingdan);
                mViewHolder.ddDanTv.setTextColor(Color.parseColor("#cf0100"));
            }else {
                mViewHolder.ddDanTv.setBackgroundResource(R.drawable.sfcdan_unchecked);
                mViewHolder.ddDanTv.setTextColor(Color.parseColor("#333333"));
            }

            mViewHolder.ddDanTv.setChecked(mSfcDingdanBean.getMatchListBeans().get(position).isChecked());
        } else {
            mViewHolder.ddDanLl.setVisibility(View.GONE);
        }
        if (mSfcDingdanBean.getMatchListBeans().get(position).getCheckedHashMap().get(0)) {
            mViewHolder.ddWinTv.setBackgroundResource(R.mipmap.dingdanred_bg);
            mViewHolder.ddWinTv.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            mViewHolder.ddWinTv.setBackgroundResource(R.mipmap.dingdanwhite_bg);
            mViewHolder.ddWinTv.setTextColor(Color.parseColor("#cf0100"));
        }
        if (mSfcDingdanBean.getMatchListBeans().get(position).getCheckedHashMap().get(1)) {
            mViewHolder.ddPingTv.setBackgroundResource(R.mipmap.dingdanred_bg);
            mViewHolder.ddPingTv.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            mViewHolder.ddPingTv.setBackgroundResource(R.mipmap.dingdanwhite_bg);
            mViewHolder.ddPingTv.setTextColor(Color.parseColor("#cf0100"));
        }
        if (mSfcDingdanBean.getMatchListBeans().get(position).getCheckedHashMap().get(2)) {
            mViewHolder.ddLostTv.setBackgroundResource(R.mipmap.dingdanred_bg);
            mViewHolder.ddLostTv.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            mViewHolder.ddLostTv.setBackgroundResource(R.mipmap.dingdanwhite_bg);
            mViewHolder.ddLostTv.setTextColor(Color.parseColor("#cf0100"));
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.sfcdd_position_tv)
        TextView sfcddPositionTv;
        @BindView(R.id.sfc_dd_leaguename_tv)
        TextView sfcDdLeaguenameTv;
        @BindView(R.id.dd_stoptime_tv)
        TextView ddStoptimeTv;
        @BindView(R.id.sfddvs_tv)
        TextView sfddvsTv;
        @BindView(R.id.sfcdd_hostname_tv)
        TextView sfcddHostnameTv;
        @BindView(R.id.sfcdd_visitname_tv)
        TextView sfcddVisitnameTv;
        @BindView(R.id.dd_win_tv)
        TextView ddWinTv;
        @BindView(R.id.dd_ping_tv)
        TextView ddPingTv;
        @BindView(R.id.dd_lost_tv)
        TextView ddLostTv;
        @BindView(R.id.dd_dan_tv)
        CheckBox ddDanTv;
        @BindView(R.id.dd_dan_ll)
        LinearLayout ddDanLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
