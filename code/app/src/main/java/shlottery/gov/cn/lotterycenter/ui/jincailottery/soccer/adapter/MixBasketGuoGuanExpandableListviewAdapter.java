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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;
import shlottery.gov.cn.lotterycenter.ui.activity.BasketBallShujufenxiActivity;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.JcDataUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.SoccerDialogUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;


/**
 * Created by booob on 2016-06-28-0028.
 */
public class MixBasketGuoGuanExpandableListviewAdapter extends BaseExpandableAdapter {
    private Context context;
    private LayoutInflater mInflater = null;
    private Handler handler;
    private ArrayList<String> mChildSpfDatas = null;
    private ArrayList<String> mChildRqspfDatas = null;
    private List<List<HashMap<Integer, MatchsBean>>> mDatas;
    //mListDatas是根据原始数据mDatas处理后用于混合过关使用的数据
    //private List<List<HashMap<Integer, MatchsBean>>> mListDatas = new ArrayList<>();
    private MatchsBean mSelectedMatchsBean = new MatchsBean();//已选择的比赛集合
    private List<MatchsBean> mSelectedMatchsBeans = new ArrayList<MatchsBean>();
    private MyOnCheckedChangeListener myOnCheckedChangeListener;
    private ChildHolder childHolder = null;
    //    private int mixId;

    public MixBasketGuoGuanExpandableListviewAdapter(Context context, List<List<HashMap<Integer, MatchsBean>>> mDatas, Handler handler) {
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

    private void init(List<List<HashMap<Integer, MatchsBean>>> mDatas) {
        for (int i = 0; i < mDatas.size(); i++) {
            for (int j = 0; j < mDatas.get(i).size(); j++) {
                JcDataUtils.removeAllSelected(mDatas.get(i).get(j).get(Configure_JC.TAB_SF));
                JcDataUtils.removeAllSelected(mDatas.get(i).get(j).get(Configure_JC.TAB_XSF));
                JcDataUtils.removeAllSelected(mDatas.get(i).get(j).get(Configure_JC.TAB_DXF));
                JcDataUtils.removeAllSelected(mDatas.get(i).get(j).get(Configure_JC.TAB_SFC));
            }
        }
    }

    @Override
    public void setData(List<List<MatchsBean>> mDatas) {

    }

    @Override
    public int getGroupCount() {
        LogUtils.i("mix group Size" + mDatas.size());
        return mDatas.size();

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        LogUtils.i("mix child Size" + mDatas.get(groupPosition).size());
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
        LogUtils.i("mix group  getview" + convertView);
        GroupHolder groupHolder;
        if (null == convertView) {
            // convertView = UIUtils.inflate(R.layout.match_expanablelistview_group_item);
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
            Log.i(mDatas.get(groupPosition).get(0).get(Configure_JC.TAB_BF).getMatchDay() + "", "getGroupView: ");
            groupHolder.item_date.setText(mDatas.get(groupPosition).get(0).get(Configure_JC.TAB_BF).getMatchDay());
            String week = mDatas.get(groupPosition).get(0).get(Configure_JC.TAB_BF).getWeekNo().replaceAll("\\d", "");//去掉多余的数字,显示星期
            String matchDay = mDatas.get(groupPosition).get(0).get(Configure_JC.TAB_BF).getMatchDay();
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
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LogUtils.i("mix getChildview changdu:" + mDatas.size() + ":::" + mDatas.get(groupPosition).size() + "");
        if (null == convertView) {
            convertView = UIUtils.inflate(R.layout.basketball_base_fragment_ex_dialog_item);
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
        MatchsBean msgData = null;
        MatchsBean spfData = null;
        MatchsBean rqspfData = null;

        if (null != mDatas.get(groupPosition).get(childPosition).get(Configure_JC.TAB_SPF)) {
            msgData = mDatas.get(groupPosition).get(childPosition).get(Configure_JC.TAB_SPF);
            spfData = mDatas.get(groupPosition).get(childPosition).get(Configure_JC.TAB_SPF);
        }
        if (null != mDatas.get(groupPosition).get(childPosition).get(Configure_JC.TAB_BF)) {
            msgData = mDatas.get(groupPosition).get(childPosition).get(Configure_JC.TAB_BF);
        }
        if (null != mDatas.get(groupPosition).get(childPosition).get(Configure_JC.TAB_BQC)) {
            msgData = mDatas.get(groupPosition).get(childPosition).get(Configure_JC.TAB_BQC);
        }
        if (null != mDatas.get(groupPosition).get(childPosition).get(Configure_JC.TAB_RQSFP)) {
            rqspfData = mDatas.get(groupPosition).get(childPosition).get(Configure_JC.TAB_RQSFP);
            msgData = mDatas.get(groupPosition).get(childPosition).get(Configure_JC.TAB_RQSFP);
        }
        if (null != mDatas.get(groupPosition).get(childPosition).get(Configure_JC.TAB_ZJQ)) {
            msgData = mDatas.get(groupPosition).get(childPosition).get(Configure_JC.TAB_ZJQ);
        }

        LogUtils.i("mix 布尔：" + groupPosition + "::" + childPosition + ":::" + spfData + ":::" + rqspfData + ":::");
        long time = msgData.getStopTime();
        String mHostRank;
        String mVisitRank;
        if (null == msgData.getHostRank() || msgData.getHostRank().equals("")) {
            mHostRank = "";
        } else {
            mHostRank = "[" + msgData.getHostRank() + "]";
        }

        if (null == msgData.getVisitRank() || msgData.getVisitRank().equals("")) {
            mVisitRank = "";
        } else {
            mVisitRank = "[" + msgData.getVisitRank() + "]";
        }

        String stopTime = DateUtils.formatTimeSimple(time * 1000);
        childHolder.mMatchName.setText(msgData.getLeagueName());
        childHolder.mMatchStopTime.setText(stopTime + "截止");
        childHolder.mMatchTime.setText(msgData.getWeekNo() + "");
        childHolder.mMatchHostName.setText(msgData.getHostName());
        childHolder.mMatchHostRank.setText(mHostRank);
        childHolder.mMatchVisitRank.setText(mVisitRank);
        childHolder.mMatchVisitName.setText(msgData.getVisitName());
        childHolder.mMatchVsName.setText("VS");
        childHolder.mMatchName.setBackgroundColor(Color.parseColor(msgData.getLeagueColor()));
        String[] itemInfo = getBaskMixItemInfo(mDatas.get(groupPosition).get(childPosition));
        childHolder.mMatchSptSelect.setText(itemInfo[0]);
        if (null != spfData) {
            mChildSpfDatas = (ArrayList<String>) spfData.getSps();
        }
        if (null != rqspfData) {
            mChildRqspfDatas = (ArrayList<String>) rqspfData.getSps();
        }

        childHolder.mMatchSptSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SoccerDialogUtils().showMixBasketDialog(context, mDatas.get(groupPosition).get(childPosition), handler);
            }
        });

        childHolder.bqc_analysis_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BasketBallShujufenxiActivity.class);
                intent.putExtra("matchId", mDatas.get(groupPosition).get(childPosition).get(0).getMatchId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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

    static class GroupHolder {
        TextView item_date;//日期
        TextView item_week;//周
        TextView item_total;//总共场次
        ImageView mImageView;//展开收缩图片
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


