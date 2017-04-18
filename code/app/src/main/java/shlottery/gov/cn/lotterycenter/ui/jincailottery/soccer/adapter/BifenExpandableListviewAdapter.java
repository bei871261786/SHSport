package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;
import shlottery.gov.cn.lotterycenter.ui.activity.MatchAnalyticsActivity;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.JcDataUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.SoccerDialogUtils;


/**
 * Created by booob on 2016-06-21-0021.
 */
public class BifenExpandableListviewAdapter extends BaseExpandableAdapter {
    private Context context;
    private LayoutInflater mInflater = null;
    private Handler handler;

    private List<List<MatchsBean>> mDatas;
    private MatchsBean mSelectedMatchsBean = new MatchsBean();//已选择的比赛集合

    private List<MatchsBean> mSelectedMatchsBeans = new ArrayList<MatchsBean>();
    //private CheckedChangeListener mCheckedChangeListener;
    private int mGroupPosition;
    private int mChildPosition;


    public BifenExpandableListviewAdapter(Context context, List<List<MatchsBean>> mDatas, Handler handler) {
        this.context = context;
        this.handler = handler;
        this.mDatas = mDatas;

        this.mInflater = LayoutInflater.from(context);
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
            String week = mDatas.get(groupPosition).get(0).getWeekNo().replaceAll("\\d", "");
            Log.i(mDatas.get(groupPosition).get(0).getMatchDay() + "", "getGroupView: ");
            String matchDay = mDatas.get(groupPosition).get(0).getMatchDay();
            String year = matchDay.substring(0, 4);
            String month = matchDay.substring(4, 6);
            String day = matchDay.substring(6, 8);
            groupHolder.item_date.setText(year + "-" + month + "-" + day);
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
            LogUtils.e("ShengPingfuExpandableAdapter" + "----" + e);
        }

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        mGroupPosition = groupPosition;
        mChildPosition = childPosition;
        ChildHolder childHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.football_base_fragment_ex_dialog_item, null);
            childHolder = new ChildHolder();
            childHolder.mMatchName = (TextView) convertView
                    .findViewById(R.id.baseChild_name_tv);
            childHolder.mMatchTime = (TextView) convertView
                    .findViewById(R.id.baseChild_time_tv);
            childHolder.mMatchStopTime = (TextView) convertView
                    .findViewById(R.id.baseChild_stop_tv);
            childHolder.mMatchHostName = (TextView) convertView
                    .findViewById(R.id.baseChild_host_name_tv);
            childHolder.mMatchHostRank = (TextView) convertView
                    .findViewById(R.id.baseChild_host_rank_tv);
            childHolder.mMatchVsName = (TextView) convertView
                    .findViewById(R.id.baseChild_ping_name_tv);
            childHolder.mMatchVisitName = (TextView) convertView
                    .findViewById(R.id.baseChild_visit_name_tv);
            childHolder.mMatchVisitRank = (TextView) convertView
                    .findViewById(R.id.baseChild_visit_rank_tv);
            childHolder.mMatchSptSelect = (TextView) convertView
                    .findViewById(R.id.spt_score_selecttip_tv);
            childHolder.bqc_analysis_img = (ImageView) convertView.findViewById(R.id.bqc_analysis_img);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }
//        try {
        MatchsBean matchsBean = mDatas.get(groupPosition).get(childPosition);
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
        childHolder.mMatchName.setText(matchsBean.getLeagueName());
        childHolder.mMatchStopTime.setText(stopTime + "截止");
        childHolder.mMatchTime.setText(matchsBean.getWeekNo() + "");
        childHolder.mMatchHostName.setText(matchsBean.getHostName());
        childHolder.mMatchHostRank.setText(mHostRank);
        childHolder.mMatchVisitRank.setText(mVisitRank);
        childHolder.mMatchVisitName.setText(matchsBean.getVisitName());
        childHolder.mMatchVsName.setText("VS");
        childHolder.mMatchName.setBackgroundColor(Color.parseColor(matchsBean.getLeagueColor()));
        String[] itemInfo = getItemInfo(mDatas.get(groupPosition).get(childPosition), Configure_JC.FB_DIALOG_BF);
        childHolder.mMatchSptSelect.setText(itemInfo[0]);


        childHolder.mMatchSptSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new SoccerDialogUtils().showBfDialog(context, mDatas.get(groupPosition).get(childPosition), handler);
                } catch (Exception e) {
                }
            }
        });
//        } catch (Exception e) {
//
//        }
        childHolder.bqc_analysis_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MatchAnalyticsActivity.class);
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

    static class GroupHolder {
        TextView item_date;//日期
        TextView item_week;//周
        TextView item_total;//总共场次
        ImageView mImageView;//展开收缩图片
    }

    static class ChildHolder {
        TextView mMatchName;
        TextView mMatchTime;
        TextView mMatchStopTime;
        TextView mMatchHostRank;
        TextView mMatchHostName;
        TextView mMatchVsName;
        TextView mMatchVisitName;
        TextView mMatchVisitRank;
        TextView mMatchSptSelect;
        ImageView bqc_analysis_img;
    }


}


