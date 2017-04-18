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
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;


/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-12-0012 下午 02:19
 * 描    述：竞彩足球战绩的适配器
 * 修订历史：
 * ================================================
 */
public class JcZqVsAdapter extends BaseAdapter {

    //    private Context mContext;
    private LayoutInflater mInflater = null;
    private MatchFtDetailBean mMatchFtDetailBean;
    private int mType;//战绩的类型,分 主客0,主1,客2

    public JcZqVsAdapter(MatchFtDetailBean mMatchFtDetailBean, int mTye) {
        this.mInflater = LayoutInflater.from(UIUtils.getContext());
        this.mType = mTye;
        LogUtils.i("JcZqAdapter mType :" + this.mType);
        this.mMatchFtDetailBean = mMatchFtDetailBean;
        LogUtils.i("JcZqAdapter data 1 :" + mMatchFtDetailBean);
        LogUtils.i("JcZqAdapter data 2:" + mMatchFtDetailBean.getData().getHostList() + ":::" + mMatchFtDetailBean.getData().getHostList().size());
    }

    @Override
    public int getCount() {
        if (null != mMatchFtDetailBean) {
            if (mType == 0) {
                if (null != mMatchFtDetailBean.getData().getVsList() && mMatchFtDetailBean.getData().getVsList().size() > 0) {
                    return mMatchFtDetailBean.getData().getVsList().size();
                }
            } else if (mType == 1) {
                if (null != mMatchFtDetailBean.getData().getHostList() && mMatchFtDetailBean.getData().getHostList().size() > 0) {
                    return mMatchFtDetailBean.getData().getHostList().size();
                }

            } else {
                if (null != mMatchFtDetailBean.getData().getVisitList() && mMatchFtDetailBean.getData().getVisitList().size() > 0) {
                    return mMatchFtDetailBean.getData().getVisitList().size();
                }
            }
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mType == 0) {
            return mMatchFtDetailBean.getData().getVsList().get(position);
        } else if (mType == 1) {
            return mMatchFtDetailBean.getData().getHostList().get(position);
        } else if (mType == 2) {
            return mMatchFtDetailBean.getData().getVisitList().get(position);
        } else {
            return 0;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder = null;
        if (null == convertView) {
            convertView = UIUtils.inflate(R.layout.match_vs_item);
            viewholder = new ViewHolder(convertView);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        if (mType == 0) {

            long baseTime = Long.parseLong(mMatchFtDetailBean.getData().getVsList().get(position).getMatchTime()) * 1000;
            viewholder.vsTimeTv.setText(DateUtils.formatDate2(baseTime));
            viewholder.vsLeaguageTv.setText(mMatchFtDetailBean.getData().getVsList().get(position).getLeagueName());
            viewholder.vsHostnameTv.setText(mMatchFtDetailBean.getData().getVsList().get(position).getHostName());
            viewholder.vsVisitnameTv.setText(mMatchFtDetailBean.getData().getVsList().get(position).getVisitName());
            if (!StringUtils.isEmpty(mMatchFtDetailBean.getData().getVsList().get(position).getHostRedCard())) {
                viewholder.vsHostredTv.setText(mMatchFtDetailBean.getData().getVsList().get(position).getHostRedCard());
                viewholder.vsHostredTv.setVisibility(View.VISIBLE);
            } else {
                viewholder.vsHostredTv.setVisibility(View.INVISIBLE);
            }
            if (!StringUtils.isEmpty(mMatchFtDetailBean.getData().getVsList().get(position).getVisitRedCard())) {
                viewholder.vsVisitredTv.setText(mMatchFtDetailBean.getData().getVsList().get(position).getVisitRedCard());
                viewholder.vsVisitredTv.setVisibility(View.VISIBLE);
            } else {
                viewholder.vsVisitredTv.setVisibility(View.INVISIBLE);
            }
            viewholder.vsBifenTv.setText(mMatchFtDetailBean.getData().getVsList().get(position).getHostGoal() + ":" + mMatchFtDetailBean.getData().getVsList().get(position).getVisitGoal());
        } else if (mType == 1) {
            LogUtils.i("JcZqAdapter getView 1:" + mMatchFtDetailBean.getData().getHostList().get(position).getMatchTime());
            long baseTime = Long.parseLong(mMatchFtDetailBean.getData().getHostList().get(position).getMatchTime()) * 1000;
            viewholder.vsTimeTv.setText(DateUtils.formatDate2(baseTime));
            viewholder.vsLeaguageTv.setText(mMatchFtDetailBean.getData().getHostList().get(position).getLeagueName());
            viewholder.vsHostnameTv.setText(mMatchFtDetailBean.getData().getHostList().get(position).getHostName());
            viewholder.vsVisitnameTv.setText(mMatchFtDetailBean.getData().getHostList().get(position).getVisitName());
            viewholder.vsBifenTv.setText(mMatchFtDetailBean.getData().getHostList().get(position).getHostGoal() + ":" + mMatchFtDetailBean.getData().getHostList().get(position).getVisitGoal());

            if (!StringUtils.isEmpty(mMatchFtDetailBean.getData().getHostList().get(position).getHostRedCard())) {
                viewholder.vsHostredTv.setText(mMatchFtDetailBean.getData().getHostList().get(position).getHostRedCard());
                viewholder.vsHostredTv.setVisibility(View.VISIBLE);
            } else {
                viewholder.vsHostredTv.setVisibility(View.INVISIBLE);
            }
            if (!StringUtils.isEmpty(mMatchFtDetailBean.getData().getHostList().get(position).getVisitRedCard())) {
                viewholder.vsVisitredTv.setText(mMatchFtDetailBean.getData().getHostList().get(position).getVisitRedCard());
                viewholder.vsVisitredTv.setVisibility(View.VISIBLE);
            } else {
                viewholder.vsVisitredTv.setVisibility(View.INVISIBLE);
            }
        } else {
            long baseTime = Long.parseLong(mMatchFtDetailBean.getData().getVisitList().get(position).getMatchTime()) * 1000;
            viewholder.vsTimeTv.setText(DateUtils.formatDate2(baseTime));
            viewholder.vsLeaguageTv.setText(mMatchFtDetailBean.getData().getVisitList().get(position).getLeagueName());
            viewholder.vsHostnameTv.setText(mMatchFtDetailBean.getData().getVisitList().get(position).getHostName());
            viewholder.vsVisitnameTv.setText(mMatchFtDetailBean.getData().getVisitList().get(position).getVisitName());
            viewholder.vsBifenTv.setText(mMatchFtDetailBean.getData().getVisitList().get(position).getHostGoal() + ":" + mMatchFtDetailBean.getData().getVisitList().get(position).getVisitGoal());
            if (!StringUtils.isEmpty(mMatchFtDetailBean.getData().getVisitList().get(position).getHostRedCard())) {
                viewholder.vsHostredTv.setText(mMatchFtDetailBean.getData().getVisitList().get(position).getHostRedCard());
                viewholder.vsHostredTv.setVisibility(View.VISIBLE);
            } else {
                viewholder.vsHostredTv.setVisibility(View.INVISIBLE);
            }
            if (!StringUtils.isEmpty(mMatchFtDetailBean.getData().getVisitList().get(position).getVisitRedCard())) {
                viewholder.vsVisitredTv.setText(mMatchFtDetailBean.getData().getVisitList().get(position).getVisitRedCard());
                viewholder.vsVisitredTv.setVisibility(View.VISIBLE);
            } else {
                viewholder.vsVisitredTv.setVisibility(View.INVISIBLE);
            }
        }
        if (position % 2 == 0) {
            convertView.setBackgroundResource(R.color.white);
        } else {
            convertView.setBackgroundResource(R.color.gray_bglight);
        }
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.vs_time_tv)
        TextView vsTimeTv;
        @BindView(R.id.vs_leaguage_tv)
        TextView vsLeaguageTv;
        @BindView(R.id.vs_bifen_tv)
        TextView vsBifenTv;
        @BindView(R.id.vs_hostname_tv)
        TextView vsHostnameTv;
        @BindView(R.id.vs_visitname_tv)
        TextView vsVisitnameTv;
        @BindView(R.id.vs_hostred_tv)
        TextView vsHostredTv;
        @BindView(R.id.vs_visitred_tv)
        TextView vsVisitredTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
