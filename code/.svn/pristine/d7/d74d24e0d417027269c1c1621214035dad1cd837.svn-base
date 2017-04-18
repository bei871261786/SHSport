package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.MatchFtDetailBean;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;


/**
 * Created by booob on 2016-09-18-0018.
 */
public class JcZqInjuryAdapter extends BaseAdapter {

    //    private Context mContext;
    private LayoutInflater mInflater = null;
    private MatchFtDetailBean mMatchFtDetailBean;
    private int count;
    private int m = 0;//主队人数
    private int n = 0;//客队人数

    public JcZqInjuryAdapter(MatchFtDetailBean mMatchFtDetailBean) {
        this.mInflater = LayoutInflater.from(UIUtils.getContext());
        this.mMatchFtDetailBean = mMatchFtDetailBean;
        init();
    }

    private void init() {
        if (null != mMatchFtDetailBean && (mMatchFtDetailBean.getData().getHostInjury().size() > 0||mMatchFtDetailBean.getData().getVisitInjury().size() > 0)) {
            m = mMatchFtDetailBean.getData().getHostInjury().size();//主场人数
            n = mMatchFtDetailBean.getData().getVisitInjury().size();//客场人数

        } else {
            m = 0;
            n = 0;
        }
        count = m >= n ? m : n;
    }

    @Override
    public int getCount() {

        if (null != mMatchFtDetailBean) {
            if (null != mMatchFtDetailBean.getData().getHostInjury() || mMatchFtDetailBean.getData().getHostInjury() != null) {
                return count;
            }
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (m >= n) {
            return mMatchFtDetailBean.getData().getHostInjury().get(position);
        } else {
            return mMatchFtDetailBean.getData().getVisitInjury().get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.injurylistv_item, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        if (m >= n) {//主队伤病人数多
            if (position >= n) {//人数超过客队人数
                if (mMatchFtDetailBean.getData().getHostInjury() != null && mMatchFtDetailBean.getData().getHostInjury().size() > 0) {
                    mViewHolder.hostnameTv.setText(mMatchFtDetailBean.getData().getHostInjury().get(position).getName());
                    mViewHolder.hostmemberTv.setText(mMatchFtDetailBean.getData().getHostInjury().get(position).getPos());
                    mViewHolder.hostpaimingTv.setText(mMatchFtDetailBean.getData().getHostInjury().get(position).getNo());
//                    mViewHolder.hostNumTv.setText(mMatchFtDetailBean.getData().getHostInjury().get(position).getType());
                    setMember(mMatchFtDetailBean.getData().getHostInjury().get(position).getPos(), mViewHolder.hostmemberTv);
                    setBackgroud(mMatchFtDetailBean.getData().getHostInjury().get(position).getType(), mViewHolder.hostNumTv);
                }
            } else {
                if (mMatchFtDetailBean.getData().getHostInjury() != null && mMatchFtDetailBean.getData().getHostInjury().size() > 0) {
                    mViewHolder.hostnameTv.setText(mMatchFtDetailBean.getData().getHostInjury().get(position).getName());
                    mViewHolder.hostmemberTv.setText(mMatchFtDetailBean.getData().getHostInjury().get(position).getPos());
                    mViewHolder.hostpaimingTv.setText(mMatchFtDetailBean.getData().getHostInjury().get(position).getNo());
//                    mViewHolder.hostNumTv.setText(mMatchFtDetailBean.getData().getHostInjury().get(position).getType());
                    setBackgroud(mMatchFtDetailBean.getData().getHostInjury().get(position).getType(), mViewHolder.hostNumTv);
                    setMember(mMatchFtDetailBean.getData().getHostInjury().get(position).getPos(), mViewHolder.hostmemberTv);
                }

                if (mMatchFtDetailBean.getData().getVisitInjury() != null && mMatchFtDetailBean.getData().getVisitInjury().size() > 0) {
                    mViewHolder.visitnameTv.setText(mMatchFtDetailBean.getData().getVisitInjury().get(position).getName());
                    mViewHolder.visitmemberTv.setText(mMatchFtDetailBean.getData().getVisitInjury().get(position).getPos());
                    mViewHolder.visitpaimingTv.setText(mMatchFtDetailBean.getData().getVisitInjury().get(position).getNo());
//                    mViewHolder.visitNumTv.setText(mMatchFtDetailBean.getData().getVisitInjury().get(position).getType());
                    setBackgroud(mMatchFtDetailBean.getData().getVisitInjury().get(position).getType(), mViewHolder.visitNumTv);
                    setMember(mMatchFtDetailBean.getData().getVisitInjury().get(position).getPos(), mViewHolder.visitmemberTv);
                }
            }
        } else {//客队人数多
            if (position >= m) {//人数超过主队人数
                if (mMatchFtDetailBean.getData().getVisitInjury() != null && mMatchFtDetailBean.getData().getVisitInjury().size() > 0) {
                    mViewHolder.visitnameTv.setText(mMatchFtDetailBean.getData().getVisitInjury().get(position).getName());
                    mViewHolder.visitmemberTv.setText(mMatchFtDetailBean.getData().getVisitInjury().get(position).getPos());
                    mViewHolder.visitpaimingTv.setText(mMatchFtDetailBean.getData().getVisitInjury().get(position).getNo());
//                    mViewHolder.visitNumTv.setText(mMatchFtDetailBean.getData().getVisitInjury().get(position).getType());
                    setBackgroud(mMatchFtDetailBean.getData().getVisitInjury().get(position).getType(), mViewHolder.visitNumTv);
                    setMember(mMatchFtDetailBean.getData().getVisitInjury().get(position).getPos(), mViewHolder.visitmemberTv);
                }
            } else {
                if (mMatchFtDetailBean.getData().getHostInjury() != null && mMatchFtDetailBean.getData().getHostInjury().size() > 0) {
                    mViewHolder.hostnameTv.setText(mMatchFtDetailBean.getData().getHostInjury().get(position).getName());
                    mViewHolder.hostmemberTv.setText(mMatchFtDetailBean.getData().getHostInjury().get(position).getPos());
                    mViewHolder.hostpaimingTv.setText(mMatchFtDetailBean.getData().getHostInjury().get(position).getNo());
//                    mViewHolder.hostNumTv.setText(mMatchFtDetailBean.getData().getHostInjury().get(position).getType());
                    setBackgroud(mMatchFtDetailBean.getData().getHostInjury().get(position).getType(), mViewHolder.hostNumTv);
                    setMember(mMatchFtDetailBean.getData().getHostInjury().get(position).getPos(), mViewHolder.hostmemberTv);
                }
                if (mMatchFtDetailBean.getData().getVisitInjury() != null && mMatchFtDetailBean.getData().getVisitInjury().size() > 0) {
                    mViewHolder.visitnameTv.setText(mMatchFtDetailBean.getData().getVisitInjury().get(position).getName());
                    mViewHolder.visitmemberTv.setText(mMatchFtDetailBean.getData().getVisitInjury().get(position).getPos());
                    mViewHolder.visitpaimingTv.setText(mMatchFtDetailBean.getData().getVisitInjury().get(position).getNo());
//                    mViewHolder.visitNumTv.setText(mMatchFtDetailBean.getData().getVisitInjury().get(position).getType());
                    setBackgroud(mMatchFtDetailBean.getData().getVisitInjury().get(position).getType(), mViewHolder.visitNumTv);
                    setMember(mMatchFtDetailBean.getData().getVisitInjury().get(position).getPos(), mViewHolder.visitmemberTv);
                }
            }
        }
        return convertView;
    }

    //根据返回状态显示背景颜色 伤病的type
    private void setBackgroud(String s, TextView textView) {
        switch (s) {
            case "1":
                textView.setBackgroundResource(R.mipmap.icon_shangting);
                break;
            case "2":
                textView.setBackgroundResource(R.mipmap.icon_tingsai);
                break;
            default:
                textView.setBackgroundResource(0);
                break;
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
        @BindView(R.id.centerline)
        View centerline;
        @BindView(R.id.host_num_tv)
        TextView hostNumTv;
        @BindView(R.id.visit_num_tv)
        TextView visitNumTv;
        @BindView(R.id.hostname_tv)
        TextView hostnameTv;
        @BindView(R.id.hostmember_tv)
        TextView hostmemberTv;
        @BindView(R.id.visitname_tv)
        TextView visitnameTv;
        @BindView(R.id.visitmember_tv)
        TextView visitmemberTv;
        @BindView(R.id.hostpaiming_tv)
        TextView hostpaimingTv;
        @BindView(R.id.visitpaiming_tv)
        TextView visitpaimingTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
