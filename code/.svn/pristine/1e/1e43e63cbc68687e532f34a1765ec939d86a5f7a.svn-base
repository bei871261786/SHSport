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

public class JLScoreMatchAdapter extends BaseExpandableListAdapter {
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


    public JLScoreMatchAdapter(JzScoreMatchBean mJzScoreMatchBean) {
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
                    return ManageJCScoreUtils.sortAsTime(ManageJCScoreUtils.jLfilterAsNoStart(mJzScoreMatchBean));
                case 2:
                    return ManageJCScoreUtils.sortAsTime(ManageJCScoreUtils.jLfilterAsStarting(mJzScoreMatchBean));
                case 3:
                    return ManageJCScoreUtils.sortAsTime(ManageJCScoreUtils.jLfilterAsFinsh(mJzScoreMatchBean));
                default:
                    return ManageJCScoreUtils.sortAsTime(mJzScoreMatchBean);
            }
        } else {
            switch (i) {
                case 0:
                    return ManageJCScoreUtils.sortAsTime(ManageJCScoreUtils.filterAsLeagues(mJzScoreMatchBean,
                            mLeagueList));
                case 1:
                    return ManageJCScoreUtils.sortAsTime(ManageJCScoreUtils.jLfilterAsNoStart(ManageJCScoreUtils
                            .filterAsLeagues(mJzScoreMatchBean, mLeagueList)));
                case 2:
                    return ManageJCScoreUtils.sortAsTime(ManageJCScoreUtils.jLfilterAsStarting(ManageJCScoreUtils
                            .filterAsLeagues(mJzScoreMatchBean, mLeagueList)));
                case 3:
                    return ManageJCScoreUtils.sortAsTime(ManageJCScoreUtils.jLfilterAsFinsh(ManageJCScoreUtils
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
            LogUtils.i("JLSoccerAdapter groupTime:" + time);
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
        ViewHolder holder2 = null;
        if (convertView == null) {
            convertView = mInflater.inflate(
                    R.layout.matchbasketball_child_item, null);
            holder2 = new ViewHolder(convertView);
            convertView.setTag(holder2);
        } else {
            holder2 = (ViewHolder) convertView.getTag();
        }

        holder2.hostnameTv.setText(mMatchDataBean.get(groupPosition).get(childPosition).getHostName());
        holder2.visitnameTv.setText(mMatchDataBean.get(groupPosition).get(childPosition).getVisitName());
        holder2.matchLeaguage1Tv.setText(mMatchDataBean.get(groupPosition).get(childPosition).getLeagueName());
        //holder2.matchLeaguage1Tv.setTextColor(Color.parseColor(mMatchDataBean.get(groupPosition).get(childPosition)
        // .getLeagueColor()));
        holder2.hostnameTv.setText(mMatchDataBean.get(groupPosition).get(childPosition).getHostName());
        holder2.week1Tv.setText(mMatchDataBean.get(groupPosition).get(childPosition).getWeekNo());
        //        StringBuffer sub = new StringBuffer(mMatchDataBean.get(groupPosition).get(childPosition).getWeekNo());
        //        String s = sub.replace(0, 2, "").toString();//替换周几   得到number
        //        holder2.week1Tv.setText(s);
        LogUtils.i("statusId :" + Integer.parseInt(mMatchDataBean.get(groupPosition).get(childPosition).getStatusId()));
        Long matchTime = Long.parseLong(mMatchDataBean.get(groupPosition).get(childPosition).getMatchTime());
        matchTime = matchTime * 1000;
        String matchDay = DateUtils.formatSimpleDateAndTime(matchTime);
        holder2.mmDd1Tv.setText(matchDay);
        //        holder2.hhMm1Tv.setText(DateUtils.formatTimeSimple(Long.parseLong(mMatchDataBean.get(groupPosition)
        // .get(childPosition).getMatchTime()) * 1000));
        String longTime = mMatchDataBean.get(groupPosition).get(childPosition).getSectionTime();
        LogUtils.i("JLSoccerAdapter longTime:" + longTime);
        String remainTime = "";
        if (longTime != null && !longTime.isEmpty()) {
            int minus = Integer.parseInt(longTime) / 60;
            int second = Integer.parseInt(longTime) % 60;
            LogUtils.i(" JLSoccerAdapter time :" + minus + ":::" + second);
            String mStr = "";
            String sStr = "";
            if (minus < 10) {
                mStr = "0" + minus;
            } else {
                mStr = minus + "";
            }
            if (second < 10) {
                sStr = "0" + second;
            } else {
                sStr = second + "";
            }

            remainTime = mStr + "'" + sStr;
            LogUtils.i(" JLSoccerAdapter time :" + remainTime);
        }

        LogUtils.i("JLScoreMatchAdapter statusid:" + (mMatchDataBean.get(groupPosition).get(childPosition)
                .getHostName() + "vs" + mMatchDataBean.get(groupPosition).get(childPosition).getVisitName() + "::::"
                + mMatchDataBean.get(groupPosition).get(childPosition).getStatusId()));
        //修改仅在全场结束时比分设置为红色, 其他状态为绿色
        int statusId = Integer.parseInt(mMatchDataBean.get(groupPosition).get(childPosition).getStatusId());
        int red = BaseApplication.getApplication().getResources().getColor(R.color.match_vs_red);
        int green = BaseApplication.getApplication().getResources().getColor(R.color.green);
        holder2.allBifen1Tv.setTextColor(statusId == 6 ? red : green);
        switch (statusId) {
            case 1://未开赛
                holder2.match_up.setText("未开");
                holder2.allBifen1Tv.setVisibility(View.GONE);
                holder2.halfBifen1Tv.setVisibility(View.VISIBLE);
                holder2.halfBifen1Tv.setText("vs");
                holder2.match_down.setVisibility(View.GONE);
                holder2.match_up.setVisibility(View.VISIBLE);
                holder2.halfBifen1Tv.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color
                        .match_vs_red));
                holder2.match_up.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color
                        .black_gray));
                break;
            case 2://待定
                holder2.match_up.setText("待定");
                holder2.match_up.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.blue));
                //                TextUtils.setStrColor(holder2.match_up, "腰斩", "腰斩", BaseApplication.getApplication
                // ().getResources().getColor(R.color.blue));
                holder2.match_down.setVisibility(View.GONE);
                holder2.match_up.setVisibility(View.VISIBLE);
                break;
            case 3:
                // 上半场
                holder2.match_down.setText("上半场");
                if (remainTime == null || remainTime.isEmpty()) {
                    holder2.match_up.setVisibility(View.GONE);
                } else {
                    holder2.match_up.setVisibility(View.VISIBLE);
                    holder2.match_up.setText(remainTime);
                }
                holder2.match_up.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.green));
                holder2.match_down.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color
                        .green));
                holder2.match_down.setVisibility(View.VISIBLE);
                break;
            case 4:
                // 下半场
                holder2.match_down.setText("下半场");
                if (remainTime == null || remainTime.isEmpty()) {
                    holder2.match_up.setVisibility(View.GONE);
                } else {
                    holder2.match_up.setVisibility(View.VISIBLE);
                    holder2.match_up.setText(remainTime);
                }
                holder2.match_up.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.green));
                holder2.match_down.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color
                        .green));
                holder2.match_down.setVisibility(View.VISIBLE);
                break;
            case 5:
                // 半场休息
                holder2.match_up.setText("半场");
                TextUtils.setStrColor(holder2.match_up, "半场", "半场", BaseApplication.getApplication().getResources()
                        .getColor(R.color.green));
                holder2.match_down.setVisibility(View.GONE);
                holder2.match_up.setVisibility(View.VISIBLE);
                break;
            case 6:
                // 全场结束
                holder2.match_up.setText("完");
                TextUtils.setStrColor(holder2.match_up, "完", "完", BaseApplication.getApplication().getResources()
                        .getColor(R.color.red));
                holder2.match_down.setVisibility(View.GONE);
                holder2.match_up.setVisibility(View.VISIBLE);
                break;
            case 7:
                //下半场完
                holder2.match_up.setText("下半场完");
                TextUtils.setStrColor(holder2.match_up, "下半场完", "下半场完", BaseApplication.getApplication().getResources
                        ().getColor(R.color.green));
                holder2.match_down.setVisibility(View.GONE);
                holder2.match_up.setVisibility(View.VISIBLE);
                break;
            case 12:
                // 中断
                holder2.match_up.setText("中断");
                TextUtils.setStrColor(holder2.match_up, "中断", "中断", BaseApplication.getApplication().getResources()
                        .getColor(R.color.blue));
                holder2.match_down.setVisibility(View.GONE);
                holder2.match_up.setVisibility(View.VISIBLE);
                break;
            case 13:
                // 腰斩
                holder2.match_up.setText("腰斩");
                TextUtils.setStrColor(holder2.match_up, "腰斩", "腰斩", BaseApplication.getApplication().getResources()
                        .getColor(R.color.blue));
                holder2.match_down.setVisibility(View.GONE);
                holder2.match_up.setVisibility(View.VISIBLE);
                break;
            case 14:
                // 取消
                holder2.match_up.setText("取消");
                TextUtils.setStrColor(holder2.match_up, "取消", "取消", BaseApplication.getApplication().getResources()
                        .getColor(R.color.blue));
                holder2.match_down.setVisibility(View.GONE);
                holder2.match_up.setVisibility(View.VISIBLE);
                break;
            case 15:
                // 延期
                holder2.match_up.setText("延期");
                TextUtils.setStrColor(holder2.match_up, "延期", "延期", BaseApplication.getApplication().getResources()
                        .getColor(R.color.blue));
                holder2.match_down.setVisibility(View.GONE);
                break;
            case 16:
                // 延时
                holder2.match_up.setText("延时");
                TextUtils.setStrColor(holder2.match_up, "延时", "延时", BaseApplication.getApplication().getResources()
                        .getColor(R.color.blue));
                holder2.match_down.setVisibility(View.GONE);
                holder2.match_up.setVisibility(View.VISIBLE);
                break;
            case 17:
                // 第一节
                holder2.match_down.setText("第1节");
                if (remainTime == null || remainTime.isEmpty()) {
                    holder2.match_up.setVisibility(View.GONE);
                } else {
                    holder2.match_up.setVisibility(View.VISIBLE);
                    holder2.match_up.setText(remainTime);
                }
                holder2.match_up.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.green));
                holder2.match_down.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color
                        .green));
                holder2.match_down.setVisibility(View.VISIBLE);
                break;
            case 18:
                // 第二节
                holder2.match_down.setText("第2节");
                if (remainTime == null || remainTime.isEmpty()) {
                    holder2.match_up.setVisibility(View.GONE);
                } else {
                    holder2.match_up.setVisibility(View.VISIBLE);
                    holder2.match_up.setText(remainTime);
                }

                holder2.match_up.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.green));
                holder2.match_down.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color
                        .green));
                holder2.match_down.setVisibility(View.VISIBLE);
                break;
            case 19:
                // 第三节
                holder2.match_down.setText("第3节");
                if (remainTime == null || remainTime.isEmpty()) {
                    holder2.match_up.setVisibility(View.GONE);
                } else {
                    holder2.match_up.setVisibility(View.VISIBLE);
                    holder2.match_up.setText(remainTime);
                }
                holder2.match_up.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.green));
                holder2.match_down.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color
                        .green));
                holder2.match_down.setVisibility(View.VISIBLE);
                break;
            case 20:
                // 第四节
                holder2.match_down.setText("第4节");
                if (remainTime == null || remainTime.isEmpty()) {
                    holder2.match_up.setVisibility(View.GONE);
                } else {
                    holder2.match_up.setVisibility(View.VISIBLE);
                    holder2.match_up.setText(remainTime);
                }
                holder2.match_up.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.green));
                holder2.match_down.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color
                        .green));
                holder2.match_down.setVisibility(View.VISIBLE);
                break;
            case 21:
                // 加时1
                holder2.match_down.setText("加时 1");
                if (remainTime == null || remainTime.isEmpty()) {
                    holder2.match_up.setVisibility(View.GONE);
                } else {
                    holder2.match_up.setVisibility(View.VISIBLE);
                    holder2.match_up.setText(remainTime);
                }
                holder2.match_up.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.green));
                holder2.match_down.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color
                        .green));
                holder2.match_down.setVisibility(View.VISIBLE);
                break;
            case 22:
                // 加时 2
                holder2.match_down.setText("加时 2");
                if (remainTime == null || remainTime.isEmpty()) {
                    holder2.match_up.setVisibility(View.GONE);
                } else {
                    holder2.match_up.setVisibility(View.VISIBLE);
                    holder2.match_up.setText(remainTime);
                }
                holder2.match_up.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.green));
                holder2.match_down.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color
                        .green));
                holder2.match_down.setVisibility(View.VISIBLE);
                break;
            case 23:
                // 加时 3
                holder2.match_down.setText("加时 3");
                if (remainTime == null || remainTime.isEmpty()) {
                    holder2.match_up.setVisibility(View.GONE);
                } else {
                    holder2.match_up.setVisibility(View.VISIBLE);
                    holder2.match_up.setText(remainTime);
                }
                holder2.match_up.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.green));
                holder2.match_down.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color
                        .green));
                holder2.match_down.setVisibility(View.VISIBLE);
                break;
            case 24:
                // 加时 4
                holder2.match_down.setText("加时 4");
                if (remainTime == null || remainTime.isEmpty()) {
                    holder2.match_up.setVisibility(View.GONE);
                } else {
                    holder2.match_up.setVisibility(View.VISIBLE);
                    holder2.match_up.setText(remainTime);
                }
                holder2.match_up.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.green));
                holder2.match_down.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color
                        .green));
                holder2.match_down.setVisibility(View.VISIBLE);
                break;
            case 26:
                // 第一节完
                holder2.match_up.setText("第1节完");
                TextUtils.setStrColor(holder2.match_up, "第1节完", "第1节完", BaseApplication.getApplication().getResources
                        ().getColor(R.color.green));
                holder2.match_down.setVisibility(View.GONE);
                holder2.match_up.setVisibility(View.VISIBLE);
                break;
            case 27:
                // 第二节完
                holder2.match_up.setText("第2节完");
                TextUtils.setStrColor(holder2.match_up, "第2节完", "第2节完", BaseApplication.getApplication().getResources
                        ().getColor(R.color.green));
                holder2.match_down.setVisibility(View.GONE);
                holder2.match_up.setVisibility(View.VISIBLE);
                break;
            case 28:
                // 第三节完
                holder2.match_up.setText("第3节完");
                TextUtils.setStrColor(holder2.match_up, "第3节完", "第3节完", BaseApplication.getApplication().getResources
                        ().getColor(R.color.green));
                holder2.match_down.setVisibility(View.GONE);
                holder2.match_up.setVisibility(View.VISIBLE);
                break;
            case 29:
                // 第四节完
                holder2.match_up.setText("第4节完");
                TextUtils.setStrColor(holder2.match_up, "第4节完", "第4节完", BaseApplication.getApplication().getResources
                        ().getColor(R.color.green));
                holder2.match_down.setVisibility(View.GONE);
                holder2.match_up.setVisibility(View.VISIBLE);
                break;
            case 30:
                // 加时1完
                holder2.match_up.setText("加时1完");
                TextUtils.setStrColor(holder2.match_up, "加时1完", "加时1完", BaseApplication.getApplication().getResources
                        ().getColor(R.color.green));
                holder2.match_down.setVisibility(View.GONE);
                holder2.match_up.setVisibility(View.VISIBLE);
                break;
            case 31:
                // 加时2完
                holder2.match_up.setText("加时2完");
                TextUtils.setStrColor(holder2.match_up, "加时2完", "加时2完", BaseApplication.getApplication().getResources
                        ().getColor(R.color.green));
                holder2.match_down.setVisibility(View.GONE);
                holder2.match_up.setVisibility(View.VISIBLE);
                break;
            case 32:
                // 加时3完
                holder2.match_up.setText("加时3完");
                TextUtils.setStrColor(holder2.match_up, "加时3完", "加时3完", BaseApplication.getApplication().getResources
                        ().getColor(R.color.green));
                holder2.match_down.setVisibility(View.GONE);
                holder2.match_up.setVisibility(View.VISIBLE);
                break;
            case 33:
                // 加时4完
                holder2.match_up.setText("加时4完");
                TextUtils.setStrColor(holder2.match_up, "加时4完", "加时4完", BaseApplication.getApplication().getResources
                        ().getColor(R.color.green));
                holder2.match_down.setVisibility(View.GONE);
                holder2.match_up.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }

        //设置比分 根据返回数据判断 全为空时显示vs  只有一个时显示一个
        JzScoreMatchBean.DataBean.MatchsBean matchsBean = mMatchDataBean.get(groupPosition).get(childPosition);
        if (StringUtils.isEmpty(matchsBean.getHostGoal()) && StringUtils.isEmpty(matchsBean.getHostHalfGoal())) {//
            holder2.allBifen1Tv.setVisibility(View.GONE);
            holder2.halfBifen1Tv.setVisibility(View.VISIBLE);
            holder2.halfBifen1Tv.setText("VS");
            holder2.halfBifen1Tv.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color
                    .match_vs_red));
        } else if (!StringUtils.isEmpty(matchsBean.getHostHalfGoal())) {
            //半场比分有,全场比分肯定有
            int m = Integer.parseInt(matchsBean.getStatusId());
            if (m == 1 || m == 2 || m == 3 || m == 12 || m == 13 || m == 14 || m == 15 || m == 16 || m == 17 || m ==
                    18) {//上半场
                holder2.allBifen1Tv.setVisibility(View.VISIBLE);
                holder2.allBifen1Tv.setText(matchsBean.getVisitGoal() + "-" + matchsBean.getHostGoal());
                holder2.halfBifen1Tv.setVisibility(View.GONE);
            } else {
                holder2.allBifen1Tv.setVisibility(View.VISIBLE);
                holder2.halfBifen1Tv.setVisibility(View.VISIBLE);
                holder2.allBifen1Tv.setText(matchsBean.getVisitGoal() + "-" + matchsBean.getHostGoal());
                holder2.halfBifen1Tv.setText("(" + matchsBean.getVisitHalfGoal() + "-" + matchsBean.getHostHalfGoal()
                        + ")");
                holder2.halfBifen1Tv.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color
                        .black_gray));
            }
        } else if (!StringUtils.isEmpty(matchsBean.getHostGoal())) {
            holder2.allBifen1Tv.setVisibility(View.VISIBLE);
            holder2.allBifen1Tv.setText(matchsBean.getVisitGoal() + "-" + matchsBean.getHostGoal());
            holder2.halfBifen1Tv.setVisibility(View.GONE);
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
        @BindView(R.id.match_up)
        TextView     match_up;
        @BindView(R.id.match_down)
        TextView     match_down;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
