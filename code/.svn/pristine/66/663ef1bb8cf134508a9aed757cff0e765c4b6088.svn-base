package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.MatchFtDetailBean;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * Created by booob on 2016-09-18-0018.
 * <p>
 * id	事件
 * 1	进球得分
 * 2	进乌龙球
 * 3	罚中点球
 * 102	进行换人
 * 202	得黄牌
 * 203	得红牌
 * 204	累计红牌
 * 205	罚失点球
 */
public class JcLiveEventAdapter extends BaseAdapter {

    //    private Context mContext;
    private LayoutInflater mInflater = null;
    private MatchFtDetailBean mMatchFtDetailBean;
    private int mCurrentType;
    private int mHosthrtype;//主场换人
    private int mHostballtype;//主场ball类型
    private int mVisithrtype;//客场首发人数
    private int mVisitballtype;//客场替补人数
    private int mHalfType;//半场类型
    private int mHalfPosition = 0;//半场所在位置
    private int mTypeCount;//赛事直播总的type数
    private static int HOSTHRTYPE = 0;//主队换人类型
    private static int VISITHRTYPE = 1;//客队换人类型
    private static int HOSTBALLTYPE = 2;//主队进失球,红黄牌类型
    private static int VISITBALLTYPE = 3;//客队进失球,红黄牌类型
    private List<MatchFtDetailBean.DataBean.LogListBean> mEventData = new ArrayList<>();

    public JcLiveEventAdapter(MatchFtDetailBean mMatchFtDetailBean) {
//        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(UIUtils.getContext());
        this.mMatchFtDetailBean = mMatchFtDetailBean;
        init();
    }

    private void init() {
        if (null != mMatchFtDetailBean && null != mMatchFtDetailBean.getData().getLogList() && mMatchFtDetailBean.getData().getLogList().size() > 0) {
            mEventData.clear();
            for (int i = 0; i < mMatchFtDetailBean.getData().getLogList().size(); i++) {
                if (mMatchFtDetailBean.getData().getLogList().get(i).getType().equals("102")) {//换人
                    if (mMatchFtDetailBean.getData().getLogList().get(i).getTeam().equals("1")) {//主队换人
                        mHosthrtype = 1;
                    } else if (mMatchFtDetailBean.getData().getLogList().get(i).getTeam().equals("2")) {//客队换人
                        mVisithrtype = 1;
                    }
                } else {//球类事件以及红黄牌
                    if (mMatchFtDetailBean.getData().getLogList().get(i).getTeam().equals("1")) {//主队进失球或红黄牌
                        mHostballtype = 1;
                    } else if (mMatchFtDetailBean.getData().getLogList().get(i).getTeam().equals("0")) {//客队进失球或红黄牌
                        mVisitballtype = 1;
                    }
                }
               /* if (i < mMatchFtDetailBean.getData().getLogList().size() - 1) {
                    if (Integer.parseInt(StringUtils.splitString(mMatchFtDetailBean.getData().getLogList().get(i).getLt(), "//'", 0)) > 45 && Integer.parseInt(StringUtils.splitString(mMatchFtDetailBean.getData().getLogList().get(i + 1).getLt(), "//'", 0)) <= 45) {
                        mHalfPosition = mMatchFtDetailBean.getData().getLogList().size() + 1 - (mMatchFtDetailBean.getData().getLogList().size() - i);
                    }
                }*/
                mEventData.add(mMatchFtDetailBean.getData().getLogList().get(mMatchFtDetailBean.getData().getLogList().size() - 1 - i));
            }
           /* if (Integer.parseInt(StringUtils.splitString(mMatchFtDetailBean.getLogData().get(mMatchFtDetailBean.getLogData().size() - 1).getLt(), "//'", 0)) > 45) {
                mHalfType = 1;
            }*/
        } else {
            mHosthrtype = 0;
            mHostballtype = 0;
            mVisithrtype = 0;
            mVisitballtype = 0;
//            mHalfType = 0;
        }
        mTypeCount = mHosthrtype + mHostballtype + mVisithrtype + mVisitballtype;
    }

    @Override
    public int getCount() {

        if (mEventData.size() > 0) {
            return mMatchFtDetailBean.getData().getLogList().size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mEventData.size() > 0) {
            return mEventData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (mEventData.size() > 0) {
            if (mEventData.get(position).getType().equals("105")) {//换人
                if (mEventData.get(position).getTeam().equals("1")) {//主队换人
                    return HOSTHRTYPE;
                } else {//客队换人
                    return VISITHRTYPE;
                }
            } else {//球类事件以及红黄牌
                if (mEventData.get(position).getTeam().equals("1")) {//主队进失球或红黄牌
                    return HOSTBALLTYPE;
                } else {//客队进失球或红黄牌
                    return VISITBALLTYPE;
                }
            }
        } else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public int getViewTypeCount() {
        if (mTypeCount != 0) {
            return mTypeCount;
        } else {
            return super.getViewTypeCount();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HostHRHolder mHostHRHolder = null;
        VistiHRHolder mVistiHRHolder = null;
        HostBallHolder mHostBallHolder = null;
        VisitBallHolder mVisitBallHolder = null;
        mCurrentType = getItemViewType(position);
        if (mCurrentType == HOSTHRTYPE) {//主队换人
            if (null == convertView) {
                convertView = mInflater.inflate(R.layout.liveevent_host_huanren_item, null);
                mHostHRHolder = new HostHRHolder();
                mHostHRHolder.hosthuanrenTimeTv = (TextView) convertView.findViewById(R.id.hosthuanren_time_tv);
                mHostHRHolder.hostjoninnameTv = (TextView) convertView.findViewById(R.id.hostjoninname_tv);
                mHostHRHolder.hostjoininIm = (ImageView) convertView.findViewById(R.id.hostjoinin_im);
                mHostHRHolder.hostleavenameTv = (TextView) convertView.findViewById(R.id.hostleavename_tv);
                mHostHRHolder.hostleaveIm = (ImageView) convertView.findViewById(R.id.hostleave_im);
                convertView.setTag(mHostHRHolder);
            } else {
                mHostHRHolder = (HostHRHolder) convertView.getTag();
            }
        } else if (mCurrentType == VISITHRTYPE) {//客队换人
            if (null == convertView) {
                convertView = mInflater.inflate(R.layout.liveevent_visit_huanren_item, null);
                mVistiHRHolder = new VistiHRHolder();
                mVistiHRHolder.visithuanrenTimeTv = (TextView) convertView.findViewById(R.id.visithuanren_time_tv);
                mVistiHRHolder.visitjoninnameTv = (TextView) convertView.findViewById(R.id.visitjoninname_tv);
                mVistiHRHolder.visitleaveIm = (ImageView) convertView.findViewById(R.id.visitleave_im);
                mVistiHRHolder.visitleavenameTv = (TextView) convertView.findViewById(R.id.visitleavename_tv);
                mVistiHRHolder.visitjoininIm = (ImageView) convertView.findViewById(R.id.visitjoinin_im);
                convertView.setTag(mVistiHRHolder);
            } else {
                mVistiHRHolder = (VistiHRHolder) convertView.getTag();
            }
        } else if (mCurrentType == HOSTBALLTYPE) {//主队进失球 红黄牌
            if (null == convertView) {
                convertView = mInflater.inflate(R.layout.liveevent_host_ball_item, null);
                mHostBallHolder = new HostBallHolder();
                mHostBallHolder.hostballTimeTv = (TextView) convertView.findViewById(R.id.hostball_time_tv);
                mHostBallHolder.hostballnameTv = (TextView) convertView.findViewById(R.id.hostballname_tv);
                mHostBallHolder.hostgoal1Im = (ImageView) convertView.findViewById(R.id.hostgoal1_im);
                mHostBallHolder.hostgoal2Im = (ImageView) convertView.findViewById(R.id.hostgoal2_im);
                mHostBallHolder.hostassistnameTv = (TextView) convertView.findViewById(R.id.hostassistname_tv);
                convertView.setTag(mHostBallHolder);
            } else {
                mHostBallHolder = (HostBallHolder) convertView.getTag();
            }
        } else if (mCurrentType == VISITBALLTYPE) {//客队进失球 红黄牌
            if (null == convertView) {
                convertView = mInflater.inflate(R.layout.liveevent_visit_ball_item, null);
                mVisitBallHolder = new VisitBallHolder();
                mVisitBallHolder.visitballTimeTv = (TextView) convertView.findViewById(R.id.visitball_time_tv);
                mVisitBallHolder.visitballnameTv = (TextView) convertView.findViewById(R.id.visitballname_tv);
                mVisitBallHolder.visitassistnameTv = (TextView) convertView.findViewById(R.id.visitassistname_tv);
                mVisitBallHolder.visitgoal1Im = (ImageView) convertView.findViewById(R.id.visitgoal1_im);
                mVisitBallHolder.visitgoal2Im = (ImageView) convertView.findViewById(R.id.visitgoal2_im);
                convertView.setTag(mVisitBallHolder);
            } else {
                mVisitBallHolder = (VisitBallHolder) convertView.getTag();
            }
        }
        if (mCurrentType == HOSTHRTYPE) {
            mHostHRHolder.hosthuanrenTimeTv.setText("[" + mEventData.get(position).getTime() + "]");
            mHostHRHolder.hostjoninnameTv.setText(mEventData.get(position).getName());
            mHostHRHolder.hostleavenameTv.setText(mEventData.get(position).getName2());
        } else if (mCurrentType == VISITHRTYPE) {
            mVistiHRHolder.visithuanrenTimeTv.setText("[" + mEventData.get(position).getTime() + "]");
            mVistiHRHolder.visitjoninnameTv.setText(mEventData.get(position).getName());
            mVistiHRHolder.visitleavenameTv.setText(mEventData.get(position).getName2());
        } else if (mCurrentType == HOSTBALLTYPE) {
            mHostBallHolder.hostballTimeTv.setText("[" + mEventData.get(position).getTime() + "]");
            setBallMess(Integer.parseInt(mEventData.get(position).getType()), position, mHostBallHolder.hostgoal1Im, mHostBallHolder.hostballnameTv,mHostBallHolder.hostgoal2Im,mHostBallHolder.hostassistnameTv);
        } else if (mCurrentType == VISITBALLTYPE) {
            mVisitBallHolder.visitballTimeTv.setText("[" + mEventData.get(position).getTime() + "]");
            setBallMess(Integer.parseInt(mEventData.get(position).getType()), position, mVisitBallHolder.visitgoal1Im, mVisitBallHolder.visitballnameTv,mVisitBallHolder.visitgoal2Im,mVisitBallHolder.visitassistnameTv);

        }
        return convertView;
    }

    private void setBallMess(int id, int position, ImageView imageView, TextView textView, ImageView imageView1, TextView textView1) {
        switch (id) {
            case 1:
                imageView.setImageResource(R.mipmap.goal);
                textView.setText(mEventData.get(position).getName());
                setAssitMess(position, imageView1, textView1);
                break;
            case 2:
                imageView.setImageResource(R.mipmap.own_goal);
                textView.setText(mEventData.get(position).getName());
                setAssitMess(position, imageView1, textView1);
                break;
            case 3:
                imageView.setImageResource(R.mipmap.penalty);
                textView.setText(mEventData.get(position).getName());
                break;
            case 202:
                imageView.setImageResource(R.mipmap.yellow_card);
                textView.setText(mEventData.get(position).getName());
                break;
            case 203:
                imageView.setImageResource(R.mipmap.red_card);
                textView.setText(mEventData.get(position).getName());
                break;
            case 204:
                imageView.setImageResource(R.mipmap.yellow_red_card);
                textView.setText(mEventData.get(position).getName());
                break;
            case 205:
                imageView.setImageResource(R.mipmap.missgoal);
                textView.setText(mEventData.get(position).getName());
                break;
            default:
                break;

        }
    }

    private void setAssitMess(int position, ImageView imageView1, TextView textView1) {
        if (!StringUtils.isEmpty(mEventData.get(position).getName2())) {
            imageView1.setImageResource(R.mipmap.assist_person);
            textView1.setText(mEventData.get(position).getName2());
            imageView1.setVisibility(View.VISIBLE);
            textView1.setVisibility(View.VISIBLE);
        } else {
            imageView1.setVisibility(View.INVISIBLE);
            textView1.setVisibility(View.INVISIBLE);
        }
    }

    class HostHRHolder {
        TextView hosthuanrenTimeTv;
        TextView hostjoninnameTv;
        ImageView hostjoininIm;
        TextView hostleavenameTv;
        ImageView hostleaveIm;

    }

    class VistiHRHolder {
        TextView visithuanrenTimeTv;
        TextView visitjoninnameTv;
        ImageView visitleaveIm;
        TextView visitleavenameTv;
        ImageView visitjoininIm;

    }

    class HostBallHolder {
        TextView hostballTimeTv;
        TextView hostballnameTv;
        ImageView hostgoal1Im;
        @BindView(R.id.hostassistname_tv)
        TextView hostassistnameTv;
        @BindView(R.id.hostgoal2_im)
        ImageView hostgoal2Im;

    }

    class VisitBallHolder {
        @BindView(R.id.visitball_time_tv)
        TextView visitballTimeTv;
        @BindView(R.id.visitballname_tv)
        TextView visitballnameTv;
        @BindView(R.id.visitgoal1_im)
        ImageView visitgoal1Im;
        @BindView(R.id.visitassistname_tv)
        TextView visitassistnameTv;
        @BindView(R.id.visitgoal2_im)
        ImageView visitgoal2Im;
    }
}
