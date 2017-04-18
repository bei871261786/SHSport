package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Date;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.JCScoreToCompareBean;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;

/**
 * Created by booob on 2016-09-08-0008.
 */
public class JcScorePopupWindowAdapter extends BaseAdapter {
    private JCScoreToCompareBean mJCScoreToCompareBean;
    private Context mContext;
    private LayoutInflater mInflater;

    public JcScorePopupWindowAdapter(JCScoreToCompareBean mJCScoreToCompareBean, Context mContext) {
        this.mJCScoreToCompareBean = mJCScoreToCompareBean;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mJCScoreToCompareBean.getMatchData().size();
    }

    @Override
    public Object getItem(int position) {
        return mJCScoreToCompareBean.getMatchData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (null == convertView) {
            mViewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.popupwindow_jcscore, null);
            mViewHolder.eventtimeTv = (TextView) convertView.findViewById(R.id.eventtime_tv);
            mViewHolder.jcscoreleaguenameTv = (TextView) convertView.findViewById(R.id.jcscoreleaguename_tv);
            mViewHolder.matchtimeTv = (TextView) convertView.findViewById(R.id.matchtime_tv);
            mViewHolder.eventhomeleaguenameTv = (TextView) convertView.findViewById(R.id.eventhomeleaguename_tv);
            mViewHolder.eventvisitleaguenameTv = (TextView) convertView.findViewById(R.id.eventvisitleaguename_tv);
            mViewHolder.homegoalIm = (ImageView) convertView.findViewById(R.id.homegoal_im);
            mViewHolder.visitredcardIm = (ImageView) convertView.findViewById(R.id.visitredcard_im);
            mViewHolder.visitgoalIm = (ImageView) convertView.findViewById(R.id.visitgoal_im);
            mViewHolder.homeredcardIm = (ImageView) convertView.findViewById(R.id.homeredcard_im);
            mViewHolder.homesocrenumberTv = (TextView) convertView.findViewById(R.id.homesocrenumber_tv);
            mViewHolder.visitsocrenumberTv = (TextView) convertView.findViewById(R.id.visitsocrenumber_tv);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        String matchStatus = mJCScoreToCompareBean.getMatchData().get(position).getStatusId();//比赛状态
        long l = Long.parseLong(mJCScoreToCompareBean.getMatchData().get(position).getMatchTime()) * 1000;//开赛时间
        long l1 = 0;
        if (null != mJCScoreToCompareBean.getMatchData().get(position).getHalfTime() && !mJCScoreToCompareBean.getMatchData().get(position).getHalfTime().equals("")) {
            l1 = Long.parseLong(mJCScoreToCompareBean.getMatchData().get(position).getHalfTime()) * 1000;//半场时间
        }
        mViewHolder.jcscoreleaguenameTv.setText(mJCScoreToCompareBean.getMatchData().get(position).getLeagueName());
//        mViewHolder.eventtimeTv.setText(mJCScoreToCompareBean.getMatchData().get(position).getMt());
        mViewHolder.matchtimeTv.setText(DateUtils.formatTimeSimple(l));
        mViewHolder.eventhomeleaguenameTv.setText(mJCScoreToCompareBean.getMatchData().get(position).getHostName());
        mViewHolder.eventvisitleaguenameTv.setText(mJCScoreToCompareBean.getMatchData().get(position).getVisitName());
        mViewHolder.homesocrenumberTv.setText(mJCScoreToCompareBean.getMatchData().get(position).getHostGoal());
        mViewHolder.visitsocrenumberTv.setText(mJCScoreToCompareBean.getMatchData().get(position).getVisitGoal());
        if (mJCScoreToCompareBean.getMatchData().get(position).ishomegoal()) {
            mViewHolder.homegoalIm.setVisibility(View.VISIBLE);
            mViewHolder.homesocrenumberTv.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.select_red));
        }else {
            mViewHolder.homegoalIm.setVisibility(View.INVISIBLE);
            mViewHolder.homesocrenumberTv.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.black));
        }
        if (mJCScoreToCompareBean.getMatchData().get(position).isvisitgoal()) {
            mViewHolder.visitgoalIm.setVisibility(View.VISIBLE);
            mViewHolder.visitsocrenumberTv.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.select_red));
        }else {
            mViewHolder.visitgoalIm.setVisibility(View.INVISIBLE);
            mViewHolder.visitsocrenumberTv.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.black));
        }
        if (mJCScoreToCompareBean.getMatchData().get(position).ishomeredcard()) {
            mViewHolder.homeredcardIm.setVisibility(View.VISIBLE);
        }else {
            mViewHolder.homeredcardIm.setVisibility(View.INVISIBLE);
        }
        if (mJCScoreToCompareBean.getMatchData().get(position).isvisitredcard()) {
            mViewHolder.visitredcardIm.setVisibility(View.VISIBLE);
        }else {
            mViewHolder.visitredcardIm.setVisibility(View.INVISIBLE);
        }
        switch (Integer.parseInt(matchStatus)) {
            case 3:
                // 上半场
                int mtf = DateUtils.formatFirstHalfLiveScoreMatchStatus(
                        new Date(), l);// 当前比赛时间差值
                if (mtf > 45) {
                    mViewHolder.eventtimeTv.setText("45+'");
                } else if (mtf < 0) {
                    mViewHolder.eventtimeTv.setText("上");
                } else {
                    mViewHolder.eventtimeTv.setText("上" + mtf + "'");
                }
                break;
            case 4:
                // 下半场
                int mts = DateUtils.formatFirstHalfLiveScoreMatchStatus(
                        new Date(), l1);// 当前比赛时间差值
                int n = mts + 46;
                if ((mts + 46) > 90) {
                    mViewHolder.eventtimeTv.setText("90+'");
                } else if (mts + 46 < 0) {
                    mViewHolder.eventtimeTv.setText("下");
                } else {
                    mViewHolder.eventtimeTv.setText("下" + n + "'");
                }
                break;
            case 7:
                //加时
            case 8:
                //加时上
            case 9:
                //加时下
                mViewHolder.eventtimeTv.setText("90+'");
                break;
            case 11://点球
                mViewHolder.eventtimeTv.setText("点球");
        }
        return convertView;
    }


    static class ViewHolder {
        TextView eventtimeTv;
        TextView jcscoreleaguenameTv;
        TextView matchtimeTv;
        TextView eventhomeleaguenameTv;
        TextView eventvisitleaguenameTv;
        ImageView visitgoalIm;
        ImageView homeredcardIm;
        ImageView homegoalIm;
        ImageView visitredcardIm;
        TextView homesocrenumberTv;
        TextView visitsocrenumberTv;

    }
}
