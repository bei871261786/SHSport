package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.MatchFtDetailBean;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;


/**
 * Created by booob on 2016-09-18-0018.
 * 足球赛事数据统计阵容列表适配器
 */
public class JcLiveZrAdapter extends BaseAdapter {

    //    private Context mContext;
    private LayoutInflater mInflater = null;
    private MatchFtDetailBean mMatchFtDetailBean;
    private int               mCurrentType;
    private int               mHostLineUp;//主场首发人数
    private int               mHostSubstitute;//主场替补人数
    private int               mVisitLineUp;//客场首发人数
    private int               mVisitSubstitute;//客场替补人数
    private int               m;//首发最大人数
    private int               n;//替补最大人数
    //阵型
    private int hostFirstD  = 0;
    private int hostFirstM  = 0;
    private int hostFirstF  = 0;
    private int visitFirstD = 0;
    private int visitFirstM = 0;
    private int visitFirstF = 0;
    private final List<MatchFtDetailBean.DataBean.HostFirstBean>  mHostFirst;
    private final List<MatchFtDetailBean.DataBean.VisitFirstBean> mVisitFirst;

    public JcLiveZrAdapter(MatchFtDetailBean mMatchFtDetailBean) {
        // this.mContext = mContext;
        this.mInflater = LayoutInflater.from(UIUtils.getContext());
        this.mMatchFtDetailBean = mMatchFtDetailBean;
        mHostFirst = mMatchFtDetailBean.getData().getHostFirst();
        mVisitFirst = mMatchFtDetailBean.getData().getVisitFirst();
        init();
    }

    private void init() {
        if (null != mMatchFtDetailBean && mMatchFtDetailBean.getData().getHostFirst().size() > 0) {
            mHostLineUp = mMatchFtDetailBean.getData().getHostFirst().size();//主场首发人数
            mHostSubstitute = mMatchFtDetailBean.getData().getHostSub().size();//主场替补人数
            mVisitLineUp = mMatchFtDetailBean.getData().getVisitFirst().size();//客场首发人数
            mVisitSubstitute = mMatchFtDetailBean.getData().getVisitSub().size();//客场替补人
        } else {
            mHostLineUp = 0;
            mHostSubstitute = 0;
            mVisitLineUp = 0;
            mVisitSubstitute = 0;
        }
        m = mHostLineUp > mVisitLineUp ? mHostLineUp : mVisitLineUp;
        n = mHostSubstitute > mVisitSubstitute ? mHostSubstitute : mVisitSubstitute;

        // 计算阵型
        for (MatchFtDetailBean.DataBean.HostFirstBean hostFirstBean : mHostFirst) {
            switch (hostFirstBean.getPos()) {
                case "D":
                    hostFirstD++;
                    break;
                case "M":
                    hostFirstM++;
                    break;
                case "F":
                    hostFirstF++;
                    break;
                default:
                    break;
            }
        }

        for (MatchFtDetailBean.DataBean.VisitFirstBean visitFirstBean : mVisitFirst) {
            switch (visitFirstBean.getPos()) {
                case "D":
                    visitFirstD++;
                    break;
                case "M":
                    visitFirstM++;
                    break;
                case "F":
                    visitFirstF++;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public int getCount() {

        if (null != mMatchFtDetailBean && null != mMatchFtDetailBean.getData().getHostFirst() && mMatchFtDetailBean
                .getData().getHostFirst().size() > 0) {
            return m + n + 2;

        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (position == 0 || position == mMatchFtDetailBean.getData().getHostFirst().size()) {
            return null;
        } else if (position < mMatchFtDetailBean.getData().getHostFirst().size()) {
            return mMatchFtDetailBean.getData().getHostFirst().get(position);
        } else {
            return mMatchFtDetailBean.getData().getHostSub().get(position - mMatchFtDetailBean.getData().getHostFirst
                    ().size() - 2);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == (mHostLineUp > mVisitLineUp ? mHostLineUp : mVisitLineUp) + 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int getViewTypeCount() {
        if (null != mMatchFtDetailBean && null != mMatchFtDetailBean.getData().getHostFirst() && mMatchFtDetailBean
                .getData().getHostFirst().size() > 0) {
            return 2;
        }
        return super.getViewTypeCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        ViewTitleHolder mViewTitleHolder = null;
        mCurrentType = getItemViewType(position);
        if (mCurrentType == 1) {//标题
            if (null == convertView) {
                convertView = mInflater.inflate(R.layout.zrtitle_listv_item, null);
                mViewTitleHolder = new ViewTitleHolder();
                mViewTitleHolder.zrtitlenameTv = (TextView) convertView.findViewById(R.id.zrtitle_name_tv);
                mViewTitleHolder.zhenxing1Tv = (TextView) convertView.findViewById(R.id.zhenxing1_tv);
                mViewTitleHolder.zhenxing2Tv = (TextView) convertView.findViewById(R.id.zhenxing2_tv);
                mViewTitleHolder.tvFormationHost = (TextView) convertView.findViewById(R.id.tv_formation_host);
                mViewTitleHolder.tvFormationVisit = (TextView) convertView.findViewById(R.id.tv_formation_visit);
                convertView.setTag(mViewTitleHolder);
            } else {
                mViewTitleHolder = (ViewTitleHolder) convertView.getTag();
            }
        } else {//阵容
            if (null == convertView) {
                convertView = mInflater.inflate(R.layout.zrlistv_item, null);
                mViewHolder = new ViewHolder();
                mViewHolder.hostZrpaimingTv = (TextView) convertView.findViewById(R.id.host_zrpaiming_tv);
                mViewHolder.visitZrpaimingTv = (TextView) convertView.findViewById(R.id.visit_zrpaiming_tv);
                mViewHolder.hostzrnameTv = (TextView) convertView.findViewById(R.id.hostzrname_tv);
                mViewHolder.hostmemberTv = (TextView) convertView.findViewById(R.id.hostmember_tv);
                mViewHolder.visitzrnameTv = (TextView) convertView.findViewById(R.id.visitzrname_tv);
                mViewHolder.visitmemberTv = (TextView) convertView.findViewById(R.id.visitmember_tv);
                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (ViewHolder) convertView.getTag();
            }
        }
        //当position为0 和m+1时,为标题栏
        if (position == 0) {
            mViewTitleHolder.zrtitlenameTv.setText("首发");
            mViewTitleHolder.zhenxing1Tv.setVisibility(View.INVISIBLE);
            mViewTitleHolder.zhenxing2Tv.setVisibility(View.INVISIBLE);
            mViewTitleHolder.tvFormationHost.setVisibility(View.INVISIBLE);
            mViewTitleHolder.tvFormationVisit.setVisibility(View.INVISIBLE);
            mViewTitleHolder.tvFormationHost.setText(hostFirstD + "-" + hostFirstM + "-" + hostFirstF);
            mViewTitleHolder.tvFormationVisit.setText(visitFirstD + "-" + visitFirstM + "-" + visitFirstF);
        } else if (position == m + 1) {
            mViewTitleHolder.zrtitlenameTv.setText("替补");
            mViewTitleHolder.zhenxing1Tv.setVisibility(View.INVISIBLE);
            mViewTitleHolder.zhenxing2Tv.setVisibility(View.INVISIBLE);
            mViewTitleHolder.tvFormationHost.setVisibility(View.INVISIBLE);
            mViewTitleHolder.tvFormationVisit.setVisibility(View.INVISIBLE);
        } else {//替补的人员数量可能不等,所以需要分情况
            if (position < m + 1) {
                if (position - 1 < mMatchFtDetailBean.getData().getHostFirst().size()) {
                    MatchFtDetailBean.DataBean.HostFirstBean hostFirstBean = mMatchFtDetailBean.getData()
                            .getHostFirst().get(position - 1);
                    setMember(hostFirstBean.getPos(), mViewHolder.hostmemberTv);
                    mViewHolder.hostzrnameTv.setText(hostFirstBean.getName());
                    mViewHolder.hostZrpaimingTv.setText(hostFirstBean.getNo());
                    mViewHolder.hostZrpaimingTv.setBackgroundResource(R.mipmap.zr_sortbg);
                    //                    setBackGround( mViewHolder.hostZrpaimingTv,mMatchFtDetailBean.getData()
                    // .getHostFirst().get(position - 1).getTn());
                }
                if (position - 1 < mMatchFtDetailBean.getData().getVisitFirst().size()) {
                    MatchFtDetailBean.DataBean.VisitFirstBean visitFirstBean = mMatchFtDetailBean.getData()
                            .getVisitFirst().get(position - 1);
                    setMember(visitFirstBean.getPos(), mViewHolder.visitmemberTv);
                    mViewHolder.visitzrnameTv.setText(visitFirstBean.getName());
                    mViewHolder.visitZrpaimingTv.setText(visitFirstBean.getNo());
                    mViewHolder.visitZrpaimingTv.setBackgroundResource(R.mipmap.zr_sortbg);
                    //                    setBackGround(mViewHolder.visitZrpaimingTv,mMatchFtDetailBean.getData()
                    // .getVisitFirst().get(position - 1).getTn());
                }
            } else {
                if (position - m - 2 < mMatchFtDetailBean.getData().getHostSub().size()) {
                    setMember(mMatchFtDetailBean.getData().getHostSub().get(position - m - 2).getPos(), mViewHolder
                            .hostmemberTv);
                    mViewHolder.hostzrnameTv.setText(mMatchFtDetailBean.getData().getHostSub().get(position - m - 2)
                            .getName());
                    mViewHolder.hostZrpaimingTv.setText(mMatchFtDetailBean.getData().getHostSub().get(position - m -
                            2).getNo());
                    mViewHolder.hostZrpaimingTv.setBackgroundResource(R.mipmap.zr_sortbg);
                    //                    setBackGround( mViewHolder.hostZrpaimingTv,mMatchFtDetailBean
                    // .getHostSubstituteData().get(position - m - 2).getTn());
                }
                if (position - m - 2 < mMatchFtDetailBean.getData().getVisitSub().size()) {
                    setMember(mMatchFtDetailBean.getData().getVisitSub().get(position - m - 2).getPos(), mViewHolder
                            .visitmemberTv);
                    mViewHolder.visitzrnameTv.setText(mMatchFtDetailBean.getData().getVisitSub().get(position - m -
                            2).getName());
                    mViewHolder.visitZrpaimingTv.setText(mMatchFtDetailBean.getData().getVisitSub().get(position - m
                            - 2).getNo());
                    mViewHolder.visitZrpaimingTv.setBackgroundResource(R.mipmap.zr_sortbg);
                    //                    setBackGround(mViewHolder.visitZrpaimingTv,mMatchFtDetailBean
                    // .getVisitSubstituteData().get(position - m - 2).getTn());
                }
            }
        }

        //        if (mMatchFtDetailBean.getData().getHostFirst().get(position - m - 2).getPp().equals())
        return convertView;
    }

    //给排名设置背景,因为有些排名为空 或者缺少数据 需要判断
    private void setBackGround(TextView textView, String s) {
        if (!StringUtils.isEmpty(s)) {
            textView.setBackgroundResource(R.mipmap.zr_sortbg);
        }
    }

    public void setMember(String mValue, TextView mTextView) {

        switch (mValue) {
            case "G":
                mTextView.setText("G");
                mTextView.setBackgroundResource(R.mipmap.goalkeeper);
                break;
            case "D":
                mTextView.setText("D");
                mTextView.setBackgroundResource(R.mipmap.defenders);
                break;
            case "M":
                mTextView.setText("M");
                mTextView.setBackgroundResource(R.mipmap.midfield);
                break;
            case "F":
                mTextView.setText("F");
                mTextView.setBackgroundResource(R.mipmap.forward);
                break;
        }
    }

    class ViewHolder {

        TextView hostZrpaimingTv;
        TextView visitZrpaimingTv;
        TextView hostzrnameTv;
        TextView hostmemberTv;
        TextView visitzrnameTv;
        TextView visitmemberTv;
    }

    class ViewTitleHolder {

        TextView zrtitlenameTv;
        TextView zhenxing1Tv;
        TextView zhenxing2Tv;
        //主队阵型
        TextView tvFormationHost;
        //客队阵型
        TextView tvFormationVisit;


    }
}
