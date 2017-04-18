package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.BasketBallDetailBean;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
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
public class JcLqVsAdapter extends BaseAdapter {

    //    private Context mContext;
    private LayoutInflater mInflater = null;
    private BasketBallDetailBean mBasketBallDetailBean;
    private int mType;//战绩的类型,分 主客0,主1,客2

    public JcLqVsAdapter(BasketBallDetailBean mBasketBallDetailBean, int mTye) {
        this.mInflater = LayoutInflater.from(UIUtils.getContext());
        this.mType = mTye;
        this.mBasketBallDetailBean = mBasketBallDetailBean;
    }

    @Override
    public int getCount() {
        if (mBasketBallDetailBean == null) {
            return 0;
        }
        if (mType == 0) {
            if (null != mBasketBallDetailBean.getData().getVsList() ) {
                Logger.e(mBasketBallDetailBean.getData().getVsList().size()+"mBasketBallDetailBean.getData().getVsList().size()");
                return mBasketBallDetailBean.getData().getVsList().size();
            }
        }
        if (mType == 1) {
            if (null != mBasketBallDetailBean.getData().getHostList() ) {
                return mBasketBallDetailBean.getData().getHostList().size();

            }
        }
        if (mType == 2) {
            if (null != mBasketBallDetailBean.getData().getVisitList() ) {
                return mBasketBallDetailBean.getData().getVisitList().size();
            }
        }

        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mType == 0) {
            return mBasketBallDetailBean.getData().getVsList().get(position);
        } else if (mType == 1) {
            return mBasketBallDetailBean.getData().getHostList().get(position);
        } else if (mType == 2) {
            return mBasketBallDetailBean.getData().getVisitList().get(position);
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
            long baseTime = mBasketBallDetailBean.getData().getVsList().get(position).getMatchTime() * 1000;
            viewholder.vsTimeTv.setText(DateUtils.formatDate2(baseTime));
            viewholder.vsLeaguageTv.setText(mBasketBallDetailBean.getData().getVsList().get(position).getLeagueName());
            viewholder.vsHostnameTv.setText(mBasketBallDetailBean.getData().getVsList().get(position).getHostName());
            viewholder.vsVisitnameTv.setText(mBasketBallDetailBean.getData().getVsList().get(position).getVisitName());
            viewholder.vsBifenTv.setText(mBasketBallDetailBean.getData().getVsList().get(position).getHostGoal() + ":" + mBasketBallDetailBean.getData().getVsList().get(position).getVisitGoal());
        } else if (mType == 1) {
            long baseTime = mBasketBallDetailBean.getData().getHostList().get(position).getMatchTime() * 1000;
            viewholder.vsTimeTv.setText(DateUtils.formatDate2(baseTime));
            viewholder.vsLeaguageTv.setText(mBasketBallDetailBean.getData().getHostList().get(position).getLeagueName());
            viewholder.vsHostnameTv.setText(mBasketBallDetailBean.getData().getHostList().get(position).getHostName());
            viewholder.vsVisitnameTv.setText(mBasketBallDetailBean.getData().getHostList().get(position).getVisitName());
            viewholder.vsBifenTv.setText(mBasketBallDetailBean.getData().getHostList().get(position).getHostGoal() + ":" + mBasketBallDetailBean.getData().getHostList().get(position).getVisitGoal());

        } else {
            long baseTime = mBasketBallDetailBean.getData().getVisitList().get(position).getMatchTime() * 1000;
            viewholder.vsTimeTv.setText(DateUtils.formatDate2(baseTime));
            viewholder.vsLeaguageTv.setText(mBasketBallDetailBean.getData().getVisitList().get(position).getLeagueName());
            viewholder.vsHostnameTv.setText(mBasketBallDetailBean.getData().getVisitList().get(position).getHostName());
            viewholder.vsVisitnameTv.setText(mBasketBallDetailBean.getData().getVisitList().get(position).getVisitName());
            viewholder.vsBifenTv.setText(mBasketBallDetailBean.getData().getVisitList().get(position).getHostGoal() + ":" + mBasketBallDetailBean.getData().getVisitList().get(position).getVisitGoal());
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
