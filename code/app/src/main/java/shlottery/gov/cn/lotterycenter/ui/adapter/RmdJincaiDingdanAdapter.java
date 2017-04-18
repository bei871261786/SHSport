package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.Sf14Bean;
import shlottery.gov.cn.lotterycenter.bean.SfcDingdanBean;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-08-0008 17:05
 * 描    述：任选九订单的adapter
 * 修订历史：
 * ================================================
 */

public class RmdJincaiDingdanAdapter extends BaseAdapter {
    private Context                                             mContext;
    private SfcDingdanBean                                      mSfcDingdanBean;
    private int                                                 mActivityType;//activity类型
    private List<Sf14Bean.DataBean.IssueListBean.MatchListBean> mMatchListBeans;
    private boolean isInit = false;
    //是否是竞彩篮球类型
    private final boolean mIsBasketballMatch;

    public RmdJincaiDingdanAdapter(Context mContext, SfcDingdanBean mSfcDingdanBean, int ActivityType) {
        this.mContext = mContext;
        this.mSfcDingdanBean = mSfcDingdanBean;
        this.mActivityType = ActivityType;
        mIsBasketballMatch = mActivityType == Configs.ACTIVITYTYPE_JCDX ||
                mActivityType == Configs.ACTIVITYTYPE_JLRSF ||
                mActivityType == Configs.ACTIVITYTYPE_JLSF;
    }

    private void initMatchListBean() {
        if (mSfcDingdanBean.getMatchListBeans() != null && !isInit) {
            mMatchListBeans = new ArrayList<>();
            for (int i = 0; i < mSfcDingdanBean.getMatchListBeans().size(); i++) {
                mMatchListBeans.add(mSfcDingdanBean.getMatchListBeans().get(i));
            }
            isInit = true;
        }

    }

    @Override
    public int getCount() {
        if (mSfcDingdanBean == null || mSfcDingdanBean.getMatchListBeans() == null) {
            return 0;
        }
        return mSfcDingdanBean.getMatchListBeans().size();
    }

    @Override
    public Object getItem(int position) {
        return mSfcDingdanBean.getMatchListBeans().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (convertView == null) {
            //篮球 足球使用不同布局
            if (mIsBasketballMatch) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.jincai_dingdan_item_basketball, null);
            } else {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.jincai_dingdan_item, null);
            }
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        initMatchListBean();
        Resources resources = BaseApplication.getApplication().getResources();
        Sf14Bean.DataBean.IssueListBean.MatchListBean matchBean = null;
        matchBean = mSfcDingdanBean.getMatchListBeans().get(position);
        double hand = matchBean.getHand();
        //竞彩篮球 大小分
        if (mActivityType == Configs.ACTIVITYTYPE_JCDX) {
            mViewHolder.ddWinTv.setText("小分");
            mViewHolder.ddPingTv.setBackground(new ColorDrawable(0));
            mViewHolder.ddPingTv.setTextColor(resources.getColor(R.color.select_red));
            mViewHolder.hand.setVisibility(View.GONE);
            mViewHolder.ddLostTv.setText("大分");
            mViewHolder.ddPingTv.setText("(" + hand + ")");
        }
        if (mActivityType == Configs.ACTIVITYTYPE_JLSF || mActivityType == Configs.ACTIVITYTYPE_JZSPF) {
            mViewHolder.hand.setVisibility(View.GONE);
        }
        //竞彩篮球 胜负
        if (mActivityType == Configs.ACTIVITYTYPE_JLSF) {
            //            mViewHolder.ddPingTv.setBackground(new ColorDrawable(0));
            //            mViewHolder.ddPingTv.setTextColor(resources.getColor(R.color.select_red));
            mViewHolder.ddPingTv.setVisibility(View.INVISIBLE);
            mViewHolder.hand.setVisibility(View.GONE);
        }
        if (mActivityType == Configs.ACTIVITYTYPE_JLRSF) {
            mViewHolder.ddPingTv.setBackground(new ColorDrawable(0));
            mViewHolder.ddPingTv.setTextColor(resources.getColor(R.color.select_red));
            mViewHolder.hand.setVisibility(View.GONE);
            mViewHolder.ddPingTv.setVisibility(View.INVISIBLE);
        }
        long time = matchBean.getValidTime();
        mViewHolder.sfcDdLeaguenameTv.setText(matchBean.getLeagueName());
        mViewHolder.ddStoptimeTv.setText(DateUtils.formatTimeSimple(time * 1000) + "截止");
        mViewHolder.sfcddHostnameTv.setText(mIsBasketballMatch ? matchBean.getHostName() + "(主)" : matchBean
                .getHostName());
        mViewHolder.sfcddVisitnameTv.setText(matchBean.getVisitName());
        mViewHolder.weekNo.setText(matchBean.getWeekNo());


        if (mActivityType == Configs.ACTIVITYTYPE_JZRSPF) {
            LogUtils.i("RmdJincaidingdan hand jinzu" + hand);
            int temp = (int) hand;
            if (temp >= 0) {
                mViewHolder.hand.setText("+" + temp);
                mViewHolder.hand.setTextColor(resources.getColor(R.color.select_red));
            } else {
                mViewHolder.hand.setText(temp + "");
                mViewHolder.hand.setTextColor(resources.getColor(R.color.standard_textcolor_c9));
            }
        }
        int unSelectedColor = BaseApplication.getApplication().getResources().getColor(R.color.black);
        if (mMatchListBeans.get(position).getCheckedHashMap().get(0)) {
            mViewHolder.ddWinTv.setBackgroundResource(R.mipmap.dingdanred_bg);
            mViewHolder.ddWinTv.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            mViewHolder.ddWinTv.setBackgroundResource(R.mipmap.dingdanwhite_bg);
            mViewHolder.ddWinTv.setTextColor(unSelectedColor);
        }
        if (mMatchListBeans.get(position).getCheckedHashMap().get(1)) {
            if (mActivityType != Configs.ACTIVITYTYPE_JCDX && mActivityType != Configs.ACTIVITYTYPE_JLSF &&
                    mActivityType != Configs.ACTIVITYTYPE_JLRSF) {
                mViewHolder.ddPingTv.setBackgroundResource(R.mipmap.dingdanred_bg);
                mViewHolder.ddPingTv.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                if (hand > 0) {
                    LogUtils.i("RmdJincaidingdan hand+++");
                    if (mActivityType != Configs.ACTIVITYTYPE_JCDX) {
                        mViewHolder.tvBasketBallHand.setText("(+" + hand + ")");
                        mViewHolder.tvBasketBallHand.setTextColor(resources.getColor(R.color.select_red));
                    } else {
                        //竞彩篮球 大小分
                        mViewHolder.ddPingTv.setText("(" + hand + ")");
                    }

                    mViewHolder.ddPingTv.setTextColor(resources.getColor(R.color.select_red));
                } else if (hand < 0) {
                    LogUtils.i("RmdJincaidingdan hand---");
                    mViewHolder.tvBasketBallHand.setText("(" + hand + ")");
                    mViewHolder.tvBasketBallHand.setTextColor(resources.getColor(R.color.standard_textcolor_c9));
                    mViewHolder.ddPingTv.setTextColor(resources.getColor(R.color.standard_textcolor_c9));
                }
            }
        } else {
            if (mActivityType != Configs.ACTIVITYTYPE_JCDX && mActivityType != Configs.ACTIVITYTYPE_JLSF &&
                    mActivityType != Configs.ACTIVITYTYPE_JLRSF) {
                mViewHolder.ddPingTv.setBackgroundResource(R.mipmap.dingdanwhite_bg);
                mViewHolder.ddPingTv.setTextColor(unSelectedColor);
            } else {
                if (hand > 0) {
                    LogUtils.i("RmdJincaidingdan hand+++");
                    if (mActivityType != Configs.ACTIVITYTYPE_JCDX) {
                        mViewHolder.tvBasketBallHand.setText("(+" + hand + ")");
                        mViewHolder.tvBasketBallHand.setTextColor(resources.getColor(R.color.select_red));
                    } else {
                        //竞彩篮球 大小分
                        mViewHolder.ddPingTv.setText("(" + hand + ")");
                    }
                    mViewHolder.ddPingTv.setTextColor(resources.getColor(R.color.select_red));
                } else if (hand < 0) {
                    LogUtils.i("RmdJincaidingdan hand---");
                    mViewHolder.tvBasketBallHand.setText("(" + hand + ")");
                    mViewHolder.tvBasketBallHand.setTextColor(resources.getColor(R.color.standard_textcolor_c9));
                    mViewHolder.ddPingTv.setTextColor(resources.getColor(R.color.standard_textcolor_c9));
                }
            }
        }
        if (mMatchListBeans.get(position).getCheckedHashMap().get(2)) {
            mViewHolder.ddLostTv.setBackgroundResource(R.mipmap.dingdanred_bg);
            mViewHolder.ddLostTv.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            mViewHolder.ddLostTv.setBackgroundResource(R.mipmap.dingdanwhite_bg);
            mViewHolder.ddLostTv.setTextColor(unSelectedColor);
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.sfc_dd_leaguename_tv)
        TextView sfcDdLeaguenameTv;
        @BindView(R.id.dd_stoptime_tv)
        TextView ddStoptimeTv;
        @BindView(R.id.sfddvs_tv)
        TextView sfddvsTv;
        @BindView(R.id.sfcdd_hostname_tv)
        TextView sfcddHostnameTv;
        @BindView(R.id.sfcdd_visitname_tv)
        TextView sfcddVisitnameTv;
        @BindView(R.id.dd_win_tv)
        TextView ddWinTv;
        @BindView(R.id.dd_ping_tv)
        TextView ddPingTv;
        @BindView(R.id.dd_lost_tv)
        TextView ddLostTv;
        @BindView(R.id.sfc_dd_weekNo_tv)
        TextView weekNo;
        @BindView(R.id.sfc_dd_hand_tv)
        TextView hand;
        @BindView(R.id.tv_item_jincai_dingdan_hand)
        TextView tvBasketBallHand;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
