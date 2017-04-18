package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.JzScoreMatchBean;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.ManageJCScoreUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;


/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-06-0006 上午 09:30
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class JZScoreMatchAdapter extends BaseExpandableListAdapter {
    private Context          mContext;
    private JzScoreMatchBean mJzScoreMatchBean;//访问服务器返回的json数据,生成的最原始的bean
    private LayoutInflater mInflater = null;

    private boolean mSortAsTime  = true; //默认按照时间排序
    private int     mMatchStatus = Configs.MATCHALL;   //比赛状态默认为全部
    public String mMatchTime;   //比赛期号

    public List<List<JzScoreMatchBean.DataBean.MatchsBean>> getmMatchDataBean() {
        return mMatchDataBean;
    }

    public void setmMatchDataBean(List<List<JzScoreMatchBean.DataBean.MatchsBean>> mMatchDataBean) {
        this.mMatchDataBean = mMatchDataBean;
    }

    private List<List<JzScoreMatchBean.DataBean.MatchsBean>> mMatchDataBean = new ArrayList<>();

    public JzScoreMatchBean getmJzScoreMatchBean() {
        return mJzScoreMatchBean;
    }

    public void setmJzScoreMatchBean(JzScoreMatchBean mJzScoreMatchBean) {
        this.mJzScoreMatchBean = mJzScoreMatchBean;
    }

    public void setmSortAsTime(boolean mSortAsTime) {
        this.mSortAsTime = mSortAsTime;
    }

    public JZScoreMatchAdapter(JzScoreMatchBean mJzScoreMatchBean) {
        //        EventBus.getDefault().register(this);
        this.mContext = UIUtils.getContext();
        this.mJzScoreMatchBean = mJzScoreMatchBean;
        this.mInflater = LayoutInflater.from(mContext);
        //        mMatchDataBean = ManageJCScoreUtils.sortAsMatch(ManageJCScoreUtils.filterAsNoStart
        // (mJzScoreMatchBean));
        if (null != mMatchDataBean) {
            mMatchDataBean.clear();
        }
        mMatchDataBean = getMatchDataBean(mMatchStatus, null);
    }

    /**
     * 清空列表数据 并刷新列表
     */
    public void clearData() {
        if (mMatchDataBean != null) {
            mMatchDataBean.clear();
        }
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        if (null == mMatchDataBean || mMatchDataBean.size() == 0) {
            return 0;
        }
        return mMatchDataBean.size();
    }

    /**
     * @param i i即为type ,根据type处理比赛数据,0 全部,1未开赛,2位进行中,3已完场
     * @return
     */
    public List<List<JzScoreMatchBean.DataBean.MatchsBean>> getMatchDataBean(int i, List<String> mLeagueList) {
        if (null == mLeagueList || mLeagueList.size() == 0) {
            switch (i) {
                case 0:
                    return ManageJCScoreUtils.sortAsTime(mJzScoreMatchBean);
                case 1:
                    return ManageJCScoreUtils.sortAsTime(ManageJCScoreUtils.filterAsNoStart(mJzScoreMatchBean));
                case 2:
                    return ManageJCScoreUtils.sortAsTime(ManageJCScoreUtils.filterAsStarting(mJzScoreMatchBean));
                case 3:
                    return ManageJCScoreUtils.sortAsTime(ManageJCScoreUtils.filterAsFinsh(mJzScoreMatchBean));
                default:
                    return ManageJCScoreUtils.sortAsTime(mJzScoreMatchBean);
            }
        } else {
            switch (i) {
                case 0:
                    return ManageJCScoreUtils.sortAsTime(ManageJCScoreUtils.filterAsLeagues(mJzScoreMatchBean,
                            mLeagueList));
                case 1:
                    return ManageJCScoreUtils.sortAsTime(ManageJCScoreUtils.filterAsNoStart(ManageJCScoreUtils
                            .filterAsLeagues(mJzScoreMatchBean, mLeagueList)));
                case 2:
                    return ManageJCScoreUtils.sortAsTime(ManageJCScoreUtils.filterAsStarting(ManageJCScoreUtils
                            .filterAsLeagues(mJzScoreMatchBean, mLeagueList)));
                case 3:
                    return ManageJCScoreUtils.sortAsTime(ManageJCScoreUtils.filterAsFinsh(ManageJCScoreUtils
                            .filterAsLeagues(mJzScoreMatchBean, mLeagueList)));
                default:
                    return ManageJCScoreUtils.sortAsTime(ManageJCScoreUtils.filterAsLeagues(mJzScoreMatchBean,
                            mLeagueList));
            }
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (null == mMatchDataBean.get(groupPosition) || mMatchDataBean.get(groupPosition).size() == 0) {
            return 0;
        }
        return mMatchDataBean.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        if (null == mMatchDataBean.get(groupPosition) || mMatchDataBean.get(groupPosition).size() == 0) {
            return null;
        }
        return mMatchDataBean.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mMatchDataBean.get(groupPosition).get(childPosition);
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
            groupHolder.item_total = (TextView) convertView
                    .findViewById(R.id.item_total);
            groupHolder.mImageView = (ImageView) convertView.findViewById(R.id.expandable_image);
            convertView.setTag(R.id.group_tag, groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag(R.id.group_tag);
        }

        if (!mSortAsTime) {
            groupHolder.item_total.setText("共" + mMatchDataBean.get(groupPosition).size() + "场比赛");
            groupHolder.item_week.setVisibility(View.INVISIBLE);
        } else {

            groupHolder.item_total.setText("共" + mMatchDataBean.get(groupPosition).size() + "场比赛");
            String time = "";
            if (StringUtils.isEmpty(mMatchDataBean.get(groupPosition).get(0).getMatchDay())) {

            } else {
                time = DateUtils.formatDate(mMatchDataBean.get(groupPosition).get(0).getMatchDay());
            }
            //            groupHolder.item_date.setText(mMatchDataBean.get(groupPosition).get(0).getMatchDay());
            groupHolder.item_date.setText(time);
            groupHolder.item_week.setText(StringUtils.getWeek(mMatchDataBean.get(groupPosition).get(0).getMatchNo(),
                    false));
            groupHolder.item_week.setVisibility(View.VISIBLE);
        }
        if (isExpanded) {
            groupHolder.mImageView.setBackgroundResource(R.mipmap.expandablelistview_up);
        } else {
            groupHolder.mImageView.setBackgroundResource(R.mipmap.expandablelistview_down);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup
            parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(
                    R.layout.matchfootball_child_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        long l = Long.parseLong(mMatchDataBean.get(groupPosition).get(childPosition).getMatchTime()) * 1000;//开赛时间
        long l1 = 0;
        if (null != mMatchDataBean.get(groupPosition).get(childPosition).getHalfTime() && !mMatchDataBean.get
                (groupPosition).get(childPosition).getHalfTime().equals("")) {
            l1 = Long.parseLong(mMatchDataBean.get(groupPosition).get(childPosition).getHalfTime()) * 1000;//半场时间
        }
        holder.hostnameTv.setText(mMatchDataBean.get(groupPosition).get(childPosition).getHostName());
        holder.visitnameTv.setText(mMatchDataBean.get(groupPosition).get(childPosition).getVisitName());
        holder.matchLeaguage1Tv.setText(mMatchDataBean.get(groupPosition).get(childPosition).getLeagueName());
        //  holder.matchLeaguage1Tv.setBackgroundColor(Color.parseColor(mMatchDataBean.get(groupPosition).get
        // (childPosition).getLeagueColor()));
        //2.0版本，赛事颜色统一用灰色
        //holder.matchLeaguage1Tv.setTextColor(Color.parseColor(mMatchDataBean.get(groupPosition).get(childPosition)
        // .getLeagueColor()));
        holder.hostnameTv.setText(mMatchDataBean.get(groupPosition).get(childPosition).getHostName());
        holder.week1Tv.setText(mMatchDataBean.get(groupPosition).get(childPosition).getWeekNo());
        StringBuffer sub = new StringBuffer(mMatchDataBean.get(groupPosition).get(childPosition).getWeekNo());
        String s = sub.replace(0, 2, "").toString();//替换周几   得到number
        LogUtils.i("JZSoccerMatchAdapter week:" + mMatchDataBean.get(groupPosition).get(childPosition).getWeekNo() +
                ":::" + s);
        Long matchTime = Long.parseLong(mMatchDataBean.get(groupPosition).get(childPosition).getMatchTime());
        matchTime = matchTime * 1000;
        String matchDay = DateUtils.formatSimpleDateAndTime(matchTime);
        holder.mmDd1Tv.setText(matchDay);
        //        holder.hhMm1Tv.setText(DateUtils.formatTimeSimple(Long.parseLong(mMatchDataBean.get(groupPosition)
        // .get(childPosition).getMatchTime()) * 1000));
        LogUtils.i("statusId :" + Integer.parseInt(mMatchDataBean.get(groupPosition).get(childPosition).getStatusId()));
        //修改只在完场状态下把比分设为红色,其他状态都为绿色
        int statusId = Integer.parseInt(mMatchDataBean.get(groupPosition).get(childPosition).getStatusId());
        int red = BaseApplication.getApplication().getResources().getColor(R.color.match_vs_red);
        int green = BaseApplication.getApplication().getResources().getColor(R.color.green);
        holder.allBifen1Tv.setTextColor(statusId == 6 ? red : green);
        switch (statusId) {
            case 1://未开赛
                LogUtils.i("JZSoccerAdapter 未开");
                holder.match_up.setText("未开");
                holder.match_up.setVisibility(View.VISIBLE);
                holder.allBifen1Tv.setVisibility(View.GONE);
                holder.halfBifen1Tv.setVisibility(View.VISIBLE);
                holder.halfBifen1Tv.setText("vs");
                holder.halfBifen1Tv.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color
                        .match_vs_red));
                holder.match_down.setVisibility(View.GONE);
                holder.match_up.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color
                        .black_gray));
                break;
            case 2://待定
                holder.match_up.setText("待定");
                holder.match_up.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.blue));
                //                TextUtils.setStrColor(holder.match_up, "腰斩", "腰斩", BaseApplication.getApplication()
                // .getResources().getColor(R.color.blue));
                holder.match_down.setVisibility(View.GONE);
                holder.match_up.setVisibility(View.VISIBLE);
                break;
            case 3:
                // 上半场
                int mtf = DateUtils.formatFirstHalfLiveScoreMatchStatus(
                        new Date(), l);// 当前比赛时间差值
                if (mtf > 45) {
                    holder.match_up.setText("45'+");
                    holder.match_down.setText("上");
                } else if (mtf < 0) {
                    holder.match_up.setText("00'");
                    holder.match_down.setText("上");
                } else {
                    holder.match_up.setText(mtf + "'");
                    holder.match_down.setText("上");
                }
                holder.match_up.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.green));
                holder.match_down.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.green));
                holder.match_down.setVisibility(View.VISIBLE);
                holder.match_up.setVisibility(View.VISIBLE);
                break;
            case 4:
                // 下半场
                int mts = DateUtils.formatFirstHalfLiveScoreMatchStatus(
                        new Date(), l1);// 当前比赛时间差值
                int n = mts + 46;
                if ((mts + 46) > 90) {
                    holder.match_up.setText("90'+");
                    holder.match_down.setText("下");
                } else if (mts + 46 < 0) {
                    holder.match_up.setText("00'");
                    holder.match_down.setText("下");
                } else {
                    holder.match_up.setText(n + "'");
                    holder.match_down.setText("下");
                }
                holder.match_up.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.green));
                holder.match_down.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.green));
                holder.match_down.setVisibility(View.VISIBLE);
                holder.match_up.setVisibility(View.VISIBLE);
                break;
            case 5:
                // 半场休息
                holder.match_up.setText("半场");
                TextUtils.setStrColor(holder.match_up, "半场", "半场", BaseApplication.getApplication().getResources()
                        .getColor(R.color.green));
                holder.match_down.setVisibility(View.GONE);
                holder.match_up.setVisibility(View.VISIBLE);
                break;
            case 6:
                // 全场结束
                holder.match_up.setText("完");
                TextUtils.setStrColor(holder.match_up, "完", "完", BaseApplication.getApplication().getResources()
                        .getColor(R.color.red));
                holder.match_down.setVisibility(View.GONE);
                holder.match_up.setVisibility(View.VISIBLE);
                break;
            case 7:
                //加时
            case 8:
                //加时上
            case 9:
                //加时下
            case 10:
                //加时完
                // 设置比分颜色
                // 隐藏vs
                holder.match_up.setText("加时");
                TextUtils.setStrColor(holder.match_up, "加时", "加时", BaseApplication.getApplication().getResources()
                        .getColor(R.color.green));
                holder.match_down.setVisibility(View.GONE);
                holder.match_up.setVisibility(View.VISIBLE);
                break;
            case 11:
                // 点球
                holder.match_up.setText("点球");
                TextUtils.setStrColor(holder.match_up, "点球", "点球", BaseApplication.getApplication().getResources()
                        .getColor(R.color.green));
                holder.match_down.setVisibility(View.GONE);
                holder.match_up.setVisibility(View.VISIBLE);
                break;
            case 12:
                // 暂停
                holder.match_up.setText("暂停");
                TextUtils.setStrColor(holder.match_up, "暂停", "暂停", BaseApplication.getApplication().getResources()
                        .getColor(R.color.blue));
                holder.match_down.setVisibility(View.GONE);
                holder.match_up.setVisibility(View.VISIBLE);
                break;
            case 13:
                // 腰斩
                holder.match_up.setText("腰斩");
                TextUtils.setStrColor(holder.match_up, "腰斩", "腰斩", BaseApplication.getApplication().getResources()
                        .getColor(R.color.blue));
                holder.match_down.setVisibility(View.GONE);
                holder.match_up.setVisibility(View.VISIBLE);
                break;
            case 14:
                // 取消
                holder.match_up.setText("取消");
                TextUtils.setStrColor(holder.match_up, "取消", "取消", BaseApplication.getApplication().getResources()
                        .getColor(R.color.blue));
                holder.match_down.setVisibility(View.GONE);
                holder.match_up.setVisibility(View.VISIBLE);
                break;
            case 15:
                // 改期
                holder.match_up.setText("改期");
                TextUtils.setStrColor(holder.match_up, "改期", "改期", BaseApplication.getApplication().getResources()
                        .getColor(R.color.blue));
                holder.match_down.setVisibility(View.GONE);
                holder.match_up.setVisibility(View.VISIBLE);
                break;
            case 16:
                // 延期
                holder.match_up.setText("延期");
                TextUtils.setStrColor(holder.match_up, "延期", "延期", BaseApplication.getApplication().getResources()
                        .getColor(R.color.blue));
                holder.match_down.setVisibility(View.GONE);
                holder.match_up.setVisibility(View.VISIBLE);
                break;
            case 17:
                // 90分钟完场
                holder.match_up.setText("完");
                holder.match_down.setVisibility(View.GONE);
                holder.match_up.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }

        //设置比分 根据返回数据判断 全为空时显示vs  只有一个时显示一个
        if (StringUtils.isEmpty(mMatchDataBean.get(groupPosition).get(childPosition).getHostGoal()) && StringUtils
                .isEmpty(mMatchDataBean.get(groupPosition).get(childPosition).getHostHalfGoal())) {//
            holder.allBifen1Tv.setVisibility(View.GONE);
            holder.halfBifen1Tv.setVisibility(View.VISIBLE);
            holder.halfBifen1Tv.setText("VS");
            holder.halfBifen1Tv.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color
                    .match_vs_red));
        } else if (!StringUtils.isEmpty(mMatchDataBean.get(groupPosition).get(childPosition).getHostHalfGoal()))
        {//半场比分有,全场比分肯定有
            int m = Integer.parseInt(mMatchDataBean.get(groupPosition).get(childPosition).getStatusId());
            if (m == 4 || m == 5 || m == 6 || m == 7 || m == 9 || m == 10 || m == 11 || m == 17) {//只有比赛状态>在下半场以后才能显示
                holder.allBifen1Tv.setVisibility(View.VISIBLE);
                holder.halfBifen1Tv.setVisibility(View.VISIBLE);
                holder.allBifen1Tv.setText(mMatchDataBean.get(groupPosition).get(childPosition).getHostGoal() + "-" +
                        mMatchDataBean.get(groupPosition).get(childPosition).getVisitGoal());
                holder.halfBifen1Tv.setText("(" + mMatchDataBean.get(groupPosition).get(childPosition)
                        .getHostHalfGoal() + "-" + mMatchDataBean.get(groupPosition).get(childPosition)
                        .getVisitHalfGoal() + ")");
                holder.halfBifen1Tv.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color
                        .black_gray));
            } else {
                holder.allBifen1Tv.setVisibility(View.VISIBLE);
                holder.allBifen1Tv.setText(mMatchDataBean.get(groupPosition).get(childPosition).getHostGoal() + "-" +
                        mMatchDataBean.get(groupPosition).get(childPosition).getVisitGoal());
                holder.halfBifen1Tv.setVisibility(View.GONE);
            }
        } else if (!StringUtils.isEmpty(mMatchDataBean.get(groupPosition).get(childPosition).getHostGoal())) {
            holder.allBifen1Tv.setVisibility(View.VISIBLE);
            holder.allBifen1Tv.setText(mMatchDataBean.get(groupPosition).get(childPosition).getHostGoal() + "-" +
                    mMatchDataBean.get(groupPosition).get(childPosition).getVisitGoal());
            holder.halfBifen1Tv.setVisibility(View.GONE);
        }

        //主队红牌
        if (!StringUtils.isEmpty(mMatchDataBean.get(groupPosition).get(childPosition).getHostRedCard()) && Integer
                .parseInt(mMatchDataBean.get(groupPosition).get(childPosition).getHostRedCard()) > 0) {
            holder.hostHongpaiTv.setText(mMatchDataBean.get(groupPosition).get(childPosition).getHostRedCard());
            holder.hostHongpaiTv.setVisibility(View.VISIBLE);
        } else {
            holder.hostHongpaiTv.setVisibility(View.GONE);
        }


        //客队红牌
        if (!StringUtils.isEmpty(mMatchDataBean.get(groupPosition).get(childPosition).getVisitRedCard()) && Integer
                .parseInt(mMatchDataBean.get(groupPosition).get(childPosition).getVisitRedCard()) > 0) {

            holder.visitHongpaiTv.setText(mMatchDataBean.get(groupPosition).get(childPosition).getVisitRedCard());
            holder.visitHongpaiTv.setVisibility(View.VISIBLE);
        } else {
            holder.visitHongpaiTv.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    static class GroupHolder {
        TextView  item_date;//日期
        TextView  item_week;//周
        TextView  item_total;//总共场次
        ImageView mImageView;//展开收缩图片
    }


    static class ViewHolder {
        @BindView(R.id.week_tv)
        TextView     week1Tv;
        @BindView(R.id.matchweek_ll)
        LinearLayout matchweekLl;
        @BindView(R.id.mm_dd_tv)
        TextView     mmDd1Tv;
        @BindView(R.id.hh_mm_tv)
        TextView     hhMm1Tv;
        @BindView(R.id.matchtime_ll)
        LinearLayout matchtimeLl;
        @BindView(R.id.match_leaguage_tv)
        TextView     matchLeaguage1Tv;
        @BindView(R.id.host_hongpai_tv)
        TextView     hostHongpaiTv;
        @BindView(R.id.hostname_tv)
        TextView     hostnameTv;
        @BindView(R.id.all_bifen_tv)
        TextView     allBifen1Tv;
        @BindView(R.id.half_bifen_tv)
        TextView     halfBifen1Tv;
        @BindView(R.id.matchbifen_ll)
        LinearLayout matchbifenLl;
        @BindView(R.id.visitname_tv)
        TextView     visitnameTv;
        @BindView(R.id.visit_hongpai_tv)
        TextView     visitHongpaiTv;
        @BindView(R.id.match_up)
        TextView     match_up;
        @BindView(R.id.match_down)
        TextView     match_down;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    //
    //    static class ViewHolder2 {
    //        @BindView(R.id.week1_tv)
    //        TextView week1Tv;
    //        @BindView(R.id.weeknum1_tv)
    //        TextView weeknum1Tv;
    //        @BindView(R.id.matchweek1_ll)
    //        LinearLayout matchweek1Ll;
    //        @BindView(R.id.mm_dd1_tv)
    //        TextView mmDd1Tv;
    //        @BindView(R.id.hh_mm1_tv)
    //        TextView hhMm1Tv;
    //        @BindView(R.id.matchtime1_ll)
    //        LinearLayout matchtime1Ll;
    //        @BindView(R.id.match_leaguage1_tv)
    //        TextView matchLeaguage1Tv;
    //        @BindView(R.id.hostname_tv)
    //        TextView hostnameTv;
    //        @BindView(R.id.all_bifen1_tv)
    //        TextView allBifen1Tv;
    //        @BindView(R.id.half_bifen1_tv)
    //        TextView halfBifen1Tv;
    //        @BindView(R.id.matchbifen1_ll)
    //        LinearLayout matchbifen1Ll;
    //        @BindView(R.id.visitname1_tv)
    //        TextView visitname1Tv;
    //
    //
    //        ViewHolder2(View view) {
    //            ButterKnife.bind(this, view);
    //        }
    //    }
}
