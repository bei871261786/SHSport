package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;
import shlottery.gov.cn.lotterycenter.ui.activity.BasketBallShujufenxiActivity;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.JcDataUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.SoccerDialogUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;

/**
 * Created by yongchao.bei on 2016/6/28.
 */
public class ShenFenChaExpandableListViewAdapter extends BaseExpandableAdapter {
    private ArrayList<CheckedTextView> mCheckTextViews;
    private Context context;
    private LayoutInflater mInflater = null;
    private Handler handler;
    private ArrayList<String> mChildDatas = new ArrayList<>();
    private List<List<MatchsBean>> mDatas;
    private MatchsBean mSelectedMatchsBean = new MatchsBean();//已选择的比赛集合
    private List<MatchsBean> mSelectedMatchsBeans = new ArrayList<MatchsBean>();
    private MyOnCheckedChangeListener myOnCheckedChangeListener;
    private ArrayList<Boolean> mChildViewState = new ArrayList<>();
    private StringBuilder mChildSb;

    public ShenFenChaExpandableListViewAdapter(Context context, List<List<MatchsBean>> mDatas, Handler handler) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
        this.handler = handler;
        init(mDatas);
    }

    public void refresh() {
        init(mDatas);
        this.notifyDataSetChanged();
    }

    private void init(List<List<MatchsBean>> mDatas) {
        JcDataUtils.removeAllSelected(mDatas);
    }

    @Override
    public void setData(List<List<MatchsBean>> mDatas) {

    }

    @Override
    public int getGroupCount() {
        return mDatas.size();

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (mDatas.isEmpty() || groupPosition >= mDatas.size()) {
            return 0;
        } else {
            return mDatas.get(groupPosition).size();
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mDatas.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mDatas.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.match_expanablelistview_group_item, null);
            groupHolder = new GroupHolder();
            groupHolder.item_date = (TextView) convertView
                    .findViewById(R.id.item_date);
            groupHolder.item_week = (TextView) convertView
                    .findViewById(R.id.item_week);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        try {
            Log.i(mDatas.get(groupPosition).get(0).getMatchDay() + "", "getGroupView: ");
            String week = mDatas.get(groupPosition).get(0).getWeekNo().replaceAll("\\d", "");//去掉多余的数字,显示星期
            String matchDay=mDatas.get(groupPosition).get(0).getMatchDay();
            String year=matchDay.substring(0,4);
            String month=matchDay.substring(4,6);
            String day=matchDay.substring(6,8);
            groupHolder.item_date.setText(year+"-"+month+"-"+day);
            groupHolder.item_week.setText(week);

            groupHolder.item_total = (TextView) convertView
                    .findViewById(R.id.item_total);
            groupHolder.item_total.setText("总共" + getChildrenCount(groupPosition) + "场比赛可投注");
            groupHolder.mImageView = (ImageView) convertView.findViewById(R.id.expandable_image);
            if (isExpanded) {
                groupHolder.mImageView.setBackgroundResource(R.mipmap.expandablelistview_up);
            } else {
                groupHolder.mImageView.setBackgroundResource(R.mipmap.expandablelistview_down);
            }
        } catch (Exception e) {
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.basketball_base_fragment_ex_dialog_item, null);
            mCheckTextViews = new ArrayList<>();
            childHolder = new ChildHolder();
            childHolder.match_zhu_name = (TextView) convertView
                    .findViewById(R.id.baseChild_host_name_tv);
            childHolder.match_zhu_paiming = (TextView) convertView
                    .findViewById(R.id.baseChild_host_rank_tv);
            childHolder.match_ping_name = (TextView) convertView
                    .findViewById(R.id.baseChild_ping_name_tv);
            childHolder.match_fu_name = (TextView) convertView
                    .findViewById(R.id.baseChild_visit_name_tv);
            childHolder.match_fu_paiming = (TextView) convertView
                    .findViewById(R.id.baseChild_visit_rank_tv);
            childHolder.match_score_selecttip = (TextView) convertView.findViewById(R.id.spt_score_selecttip_tv);
            childHolder.match_stop = (TextView) convertView
                    .findViewById(R.id.baseChild_stop_tv);
            childHolder.match_time = (TextView) convertView
                    .findViewById(R.id.baseChild_time_tv);
            childHolder.match_saiming = (TextView) convertView
                    .findViewById(R.id.baseChild_name_tv);
            childHolder. bqc_analysis_img= (ImageView) convertView.findViewById(R.id.bqc_analysis_img);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        try {
            long time = mDatas.get(groupPosition).get(childPosition).getStopTime();
            String mHostRank;
            String mVisitRank;
            if (null == mDatas.get(groupPosition).get(childPosition).getHostRank() || mDatas.get(groupPosition).get(childPosition).getHostRank().equals("")) {
                mHostRank = "";
            } else {
                mHostRank = "[" + mDatas.get(groupPosition).get(childPosition).getHostRank() + "]";
            }
            if (null == mDatas.get(groupPosition).get(childPosition).getVisitRank() || mDatas.get(groupPosition).get(childPosition).getVisitRank().equals("")) {
                mVisitRank = "";
            } else {
                mVisitRank = "[" + mDatas.get(groupPosition).get(childPosition).getVisitRank() + "]";
            }

            String stopTime = DateUtils.formatTimeSimple(time * 1000);
            childHolder.match_saiming.setText(mDatas.get(groupPosition).get(childPosition).getLeagueName());
            childHolder.match_stop.setText(stopTime + "截止");
            childHolder.match_time.setText(mDatas.get(groupPosition).get(childPosition).getWeekNo() + "");

            childHolder.match_zhu_name.setText(mDatas.get(groupPosition).get(childPosition).getHostName());
            childHolder.match_zhu_paiming.setText(mHostRank);
            childHolder.match_ping_name.setText("VS");
            childHolder.match_fu_name.setText(mDatas.get(groupPosition).get(childPosition).getVisitName());

            childHolder.match_fu_paiming.setText(mVisitRank);
            childHolder.match_saiming.setBackgroundColor(Color.parseColor(mDatas.get(groupPosition).get(childPosition).getLeagueColor()));
            String[] itemInfo = getItemInfo(mDatas.get(groupPosition).get(childPosition), Configure_JC.FB_DIALOG_SFC);
            TextUtils.setTextSpan(childHolder.match_score_selecttip, itemInfo[0]);
            childHolder.match_score_selecttip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.i("bqc scoreLitener");
                    new SoccerDialogUtils().showSfcDialog(context, mDatas.get(groupPosition).get(childPosition), handler);
                    LogUtils.i("bqc 传递过去:" + groupPosition + ":::" + childPosition + "   " + mDatas.get(groupPosition).get(childPosition).getHostName());
                }
            });

            mChildDatas.clear();
            mChildDatas.addAll(mDatas.get(groupPosition).get(childPosition).getSps());
        } catch (Exception e) {

        }

        childHolder. bqc_analysis_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BasketBallShujufenxiActivity.class);
                intent.putExtra("matchId", mDatas.get(groupPosition).get(childPosition).getMatchId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class ChildHolder {
        TextView match_saiming;
        TextView match_time;
        TextView match_stop;

        TextView match_zhu_name;
        TextView match_zhu_paiming;
        TextView match_fu_name;
        TextView match_ping_name;
        TextView match_fu_paiming;
        TextView match_score_selecttip;
        ImageView bqc_analysis_img;

    }

    static class GroupHolder {
        TextView item_date;//日期
        TextView item_week;//周
        TextView item_total;//总共场次
        ImageView mImageView;//展开收缩图片
    }

    public List<List<MatchsBean>> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<List<MatchsBean>> mDatas) {
        notifyDataSetChanged();
        this.mDatas = mDatas;
    }


    private class MyOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putBoolean("isChecked", isChecked);
            message.setData(bundle);
            handler.sendMessage(message);
        }

    }
}


