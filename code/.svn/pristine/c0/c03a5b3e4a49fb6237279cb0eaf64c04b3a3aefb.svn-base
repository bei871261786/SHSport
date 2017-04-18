package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;
import shlottery.gov.cn.lotterycenter.ui.activity.BasketBallShujufenxiActivity;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.JcDataUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * Created by booob on 2016-06-01-0001.
 */
public class RangShengFuExpandableListviewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private LayoutInflater mInflater = null;
    private Handler   handler;
    private Resources mResource;

    private List<List<MatchsBean>> mDatas;
    private MatchsBean mSelectedMatchsBean = new MatchsBean();//已选择的比赛集合

    private List<MatchsBean> mSelectedMatchsBeans = new ArrayList<MatchsBean>();

    public RangShengFuExpandableListviewAdapter(List<List<MatchsBean>> mDatas, Handler handler) {

        this.context = UIUtils.getContext();
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
        this.handler = handler;
        mResource = context.getResources();
        LogUtils.e(mDatas.size() + "" + "shengpingfulistview");
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
    public int getGroupCount() {
        LogUtils.i(mDatas.size() + "getGroupCount");
        if (mDatas.isEmpty()) {
            return 0;
        } else {
            return mDatas.size();
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        LogUtils.i(mDatas.get(groupPosition).size() + "getChildCount");
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
            convertView.setTag(R.id.group_tag, groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag(R.id.group_tag);
        }
        try {
            String week = mDatas.get(groupPosition).get(0).getWeekNo().replaceAll("\\d", "");//去掉多余的数字,显示星期
            String matchDay = mDatas.get(groupPosition).get(0).getMatchDay();
            String year = matchDay.substring(0, 4);
            String month = matchDay.substring(4, 6);
            String day = matchDay.substring(6, 8);
            groupHolder.item_date.setText(year + "-" + month + "-" + day);
            groupHolder.item_week.setText((mDatas.get(groupPosition).get(0).getWeekNo()).replace("\\d+", ""));
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
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View
            convertViews, ViewGroup parent) {
        ChildHolder childHolder;
        if (convertViews == null) {
            convertViews = mInflater.inflate(R.layout.xsf_match_expandablelistview_child_item, null);
            childHolder = new ChildHolder();
            childHolder.match_zhu_name = (TextView) convertViews
                    .findViewById(R.id.baseChild_host_name_tv);
            childHolder.match_zhu_paiming = (TextView) convertViews
                    .findViewById(R.id.baseChild_host_rank_tv);
            childHolder.match_zhu_spf = (TextView) convertViews
                    .findViewById(R.id.match_zhu_spf);
            childHolder.match_ping_name = (TextView) convertViews
                    .findViewById(R.id.baseChild_ping_name_tv);
            childHolder.match_fu_name = (TextView) convertViews
                    .findViewById(R.id.baseChild_visit_name_tv);
            childHolder.match_fu_paiming = (TextView) convertViews
                    .findViewById(R.id.baseChild_visit_rank_tv);
            childHolder.match_fu_spf = (TextView) convertViews
                    .findViewById(R.id.match_fu_spf);
            childHolder.match_stop = (TextView) convertViews
                    .findViewById(R.id.baseChild_stop_tv);
            childHolder.match_time = (TextView) convertViews
                    .findViewById(R.id.baseChild_time_tv);
            childHolder.match_saiming = (TextView) convertViews
                    .findViewById(R.id.baseChild_name_tv);
            childHolder.spt_cbx_w = (CheckBox) convertViews.findViewById(R.id.spt_cbx_w);
            childHolder.spt_cbx_l = (CheckBox) convertViews.findViewById(R.id.spt_cbx_l);
            childHolder.bqc_analysis_img = (ImageView) convertViews.findViewById(R.id.bqc_analysis_img);
            convertViews.setTag(R.id.child_tag, childHolder);
        } else {
            childHolder = (ChildHolder) convertViews.getTag(R.id.child_tag);
        }

        long time = mDatas.get(groupPosition).get(childPosition).getStopTime();
        String mHostRank;
        String mVisitRank;
        if (null == mDatas.get(groupPosition).get(childPosition).getHostRank() || mDatas.get(groupPosition).get
                (childPosition).getHostRank().equals("")) {
            mHostRank = "";
        } else {
            mHostRank = "[" + mDatas.get(groupPosition).get(childPosition).getHostRank() + "]";
        }
        if (null == mDatas.get(groupPosition).get(childPosition).getVisitRank() || mDatas.get(groupPosition).get
                (childPosition).getVisitRank().equals("")) {
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
        childHolder.match_zhu_spf.setText("主胜" + " " + mDatas.get(groupPosition).get(childPosition).getSps().get(0));

        double hand = mDatas.get(groupPosition).get(childPosition).getHand();
        if (hand > 0) {
            childHolder.match_ping_name.setTextColor(context.getResources().getColor(R.color.select_red));
        } else {
            childHolder.match_ping_name.setTextColor(context.getResources().getColor(R.color.standard_textcolor_c9));
        }
        LogUtils.i("soccer handle" + hand);
        childHolder.match_ping_name.setText(hand > 0 ? "(+" + hand + ")" : "(" + hand + ")");
        childHolder.match_fu_name.setText(mDatas.get(groupPosition).get(childPosition).getVisitName());
        childHolder.match_fu_paiming.setText(mVisitRank);
        childHolder.match_fu_spf.setText("主负" + " " + mDatas.get(groupPosition).get(childPosition).getSps().get(1));
        childHolder.match_saiming.setBackgroundColor(Color.parseColor(mDatas.get(groupPosition).get(childPosition)
                .getLeagueColor()));

        final ChildHolder finalChildHolder = childHolder;
        childHolder.spt_cbx_w.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LogUtils.i("onEventMainThread sbw");
                Message message = new Message();
                Bundle bundle = new Bundle();
                message.setData(bundle);
                handler.sendMessage(message);
                if (isChecked) {
                    for (int i = 0; i < mDatas.size(); i++) {
                        for (int j = 0; j < mDatas.get(i).size(); j++) {
                            if (i == groupPosition && j == childPosition) {
                                JcDataUtils.setSpsState(mDatas.get(i).get(j), 0, true);
                                finalChildHolder.match_zhu_spf.setTextColor(Color.WHITE);
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < mDatas.size(); i++) {
                        for (int j = 0; j < mDatas.get(i).size(); j++) {
                            if (i == groupPosition && j == childPosition) {
                                JcDataUtils.setSpsState(mDatas.get(i).get(j), 0, false);
                                finalChildHolder.match_zhu_spf.setTextColor(context.getResources().getColor(R.color
                                        .fb_color_text_matchname));
                            }
                        }
                    }
                }
            }
        });

        final ChildHolder finalChildHolder2 = childHolder;
        childHolder.spt_cbx_l.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //notifyDataSetChanged();
                LogUtils.i("onEventMainThread cb l");
                Message message = new Message();
                Bundle bundle = new Bundle();
                message.setData(bundle);
                handler.sendMessage(message);
                if (isChecked) {
                    for (int i = 0; i < mDatas.size(); i++) {
                        for (int j = 0; j < mDatas.get(i).size(); j++) {
                            if (i == groupPosition && j == childPosition) {
                                //                                mDatas.get(i).get(j).setSpsLostChecked(true);
                                JcDataUtils.setSpsState(mDatas.get(i).get(j), 1, true);
                                finalChildHolder2.match_fu_spf.setTextColor(Color.WHITE);
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < mDatas.size(); i++) {
                        for (int j = 0; j < mDatas.get(i).size(); j++) {
                            if (i == groupPosition && j == childPosition) {
                                //                                mDatas.get(i).get(j).setSpsLostChecked(false);
                                JcDataUtils.setSpsState(mDatas.get(i).get(j), 1, false);
                                finalChildHolder2.match_fu_spf.setTextColor(context.getResources().getColor(R.color
                                        .fb_color_text_matchname));
                            }
                        }
                    }
                }
            }
        });

        //必须放在setOnCheckedChangeListener之后,否则checkbox的状态会随着listview的滑动而改变
        try {
            LogUtils.i("adapter spf:" + mDatas.get(groupPosition).get(childPosition).getHostName() + "::" +
                    JcDataUtils.getSpsState(mDatas.get(groupPosition).get(childPosition), 0) + "::" + JcDataUtils
                    .getSpsState(mDatas.get(groupPosition).get(childPosition), 1) + "::" + JcDataUtils.getSpsState
                    (mDatas.get(groupPosition).get(childPosition), 2));
            childHolder.spt_cbx_w.setChecked(JcDataUtils.getSpsState(mDatas.get(groupPosition).get(childPosition), 0));
            childHolder.spt_cbx_l.setChecked(JcDataUtils.getSpsState(mDatas.get(groupPosition).get(childPosition), 1));
        } catch (Exception e) {
            LogUtils.e("ShengPingfuExpandableAdapter" + "----" + e);
        }

        childHolder.bqc_analysis_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BasketBallShujufenxiActivity.class);
                intent.putExtra("matchId", mDatas.get(groupPosition).get(childPosition).getMatchId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return convertViews;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class ChildHolder {
        TextView  match_saiming;
        TextView  match_time;
        TextView  match_zhu_name;
        TextView  match_stop;
        TextView  match_zhu_spf;
        TextView  match_ping_name;
        TextView  match_fu_name;
        TextView  match_zhu_paiming;
        TextView  match_fu_paiming;
        TextView  match_fu_spf;
        CheckBox  spt_cbx_w;
        CheckBox  spt_cbx_l;
        ImageView bqc_analysis_img;
    }

    static class GroupHolder {
        TextView  item_date;//日期
        TextView  item_week;//周
        TextView  item_total;//总共场次
        ImageView mImageView;//展开收缩图片

    }

    public List<List<MatchsBean>> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<List<MatchsBean>> mDatas) {
        notifyDataSetChanged();
        this.mDatas = mDatas;
    }

}
