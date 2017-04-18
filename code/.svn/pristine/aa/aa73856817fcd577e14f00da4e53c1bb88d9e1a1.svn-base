package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;
import shlottery.gov.cn.lotterycenter.utils.JcDataUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.SoccerDialogUtils;

/**
 * Created by yongchao.bei on 2016/7/7.
 */
public class DingdanBaseDialogAdapter extends DingdanBaseAdapter {
    private Context context;
    private LayoutInflater mInflater = null;
    private List<MatchsBean> mDatas;
    private Handler handler;
    private int mSubType;
    private int mType;
    private boolean isDanguan = false;

    public DingdanBaseDialogAdapter(Context context, List<MatchsBean> mDatas, Handler handler, int mType, int mSubType, boolean isDanguan) {
        super(context);
        this.context = context;
        this.mDatas = mDatas;
        this.handler = handler;
        this.mInflater = LayoutInflater.from(context);
        this.mSubType = mSubType;
        this.mType = mType;
        this.isDanguan = isDanguan;
        initDanCount(mDatas);
    }

    @Override
    public int getCount() {
        LogUtils.i("dingdanData:" + mDatas.size());
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LogUtils.i("dingdangetView" + mDatas.size());
        Holder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.football_base_dingdanitem_1, null);
            holder = new Holder();
            holder.match_zhu_name = (TextView) convertView
                    .findViewById(R.id.baseChild_host_name_tv);
            holder.match_ping_name = (TextView) convertView
                    .findViewById(R.id.baseChild_ping_name_tv);
            holder.match_fu_name = (TextView) convertView
                    .findViewById(R.id.baseChild_visit_name_tv);
            holder.match_zhu_paiming = (TextView) convertView
                    .findViewById(R.id.baseChild_host_rank_tv);
            holder.match_fu_paiming = (TextView) convertView
                    .findViewById(R.id.baseChild_visit_rank_tv);

            holder.match_dantuo = (CheckBox) convertView.findViewById(R.id.match_dantuo);
            holder.match_clear = (ImageView) convertView.findViewById(R.id.match_clear);
            holder.match_msg = (Button) convertView.findViewById(R.id.dingdan_base_button);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        if (isDanguan) {
            holder.match_dantuo.setVisibility(View.GONE);
        }

        //如果选择更小串法，下达清除命令，清除所有定胆状态
        if (mIsCleanDan) {
            JcDataUtils.removeAllDan(mDatas);
            holder.match_dantuo.setEnabled(true);
            mDanCunt = 0;
        }
        //如果达到定胆数量，剩余胆变为不可用
        if (mDanCunt >= mDanShouldCunt && !mDatas.get(position).isDan()) {
            holder.match_dantuo.setEnabled(false);
        } else {//如果没有达到定胆数量，则胆可用，并且清除命令取消
            holder.match_dantuo.setEnabled(true);
            mIsCleanDan = false;
        }


        holder.match_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mType == Configure_JC.TYPE_SOCCER) {
                    switch (mSubType) {
                        case Configure_JC.TAB_BF:
                            new SoccerDialogUtils().showBfDialog(context, mDatas.get(position), handler);
                            break;
                        case Configure_JC.TAB_BQC:
                            new SoccerDialogUtils().showBqcDialog(context, mDatas.get(position), handler);
                            break;
                        case Configure_JC.TAB_ZJQ:
                            new SoccerDialogUtils().showZjqDialog(context, mDatas.get(position), handler);
                            break;
                        case Configure_JC.TAB_HHGG:
//                        DialogUtil.showMixDialog(context, mDatas.get(position), handler);
                            break;
                    }
                } else {
                    switch (mSubType) {
                        case Configure_JC.TAB_SFC:
                            new SoccerDialogUtils().showSfcDialog(context, mDatas.get(position), handler);
                            break;
                    }
                }
            }
        });

        holder.match_dantuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                boolean isChecked = cb.isChecked();
                if (isChecked) {
                    mDatas.get(position).setDan(true);
                    mDanCunt++;
                    LogUtils.i("adapter spf dan" + mDatas.get(position).isDan() + "::" + mDanCunt);
                } else {
                    mDatas.get(position).setDan(false);
                    mDanCunt--;
                    LogUtils.i("adapter spf dan " + mDatas.get(position).isDan() + "::" + mDanCunt);
                }
                handler.sendEmptyMessage(1);
            }
        });
        LogUtils.i("bqc  show state" + (mDatas == null) + ":::" + (mDatas.get(position) == null));
        String mHostRank;
        String mVisitRank;
        if (null == mDatas.get(position).getHostRank() || mDatas.get(position).getHostRank().equals("")) {
            mHostRank = "";
        } else {
            mHostRank = "[" + mDatas.get(position).getHostRank() + "]";
        }
        if (null == mDatas.get(position).getVisitRank() || mDatas.get(position).getVisitRank().equals("")) {
            mVisitRank = "";
        } else {
            mVisitRank = "[" + mDatas.get(position).getVisitRank() + "]";
        }
        LogUtils.i("DingdanBaseadapter :" + mHostRank + "::::" + mVisitRank);
        holder.match_zhu_paiming.setText(mHostRank);
        holder.match_fu_paiming.setText(mVisitRank);
        holder.match_zhu_name.setText(mDatas.get(position).getHostName());
        holder.match_ping_name.setText("VS");
        holder.match_fu_name.setText(mDatas.get(position).getVisitName());
        holder.match_msg.setText(getDingdanInfo(position).getItemInfo()/**/);
        holder.match_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDatas.get(position).isDan()) {
                    mDanCunt--;
                }
                mDatas.remove(position);
                handler.sendEmptyMessage(0);
            }
        });

        holder.match_dantuo.setChecked(mDatas.get(position).isDan());
        return convertView;
    }


    static class Holder {
        CheckBox match_dantuo;
        TextView match_zhu_name;
        TextView match_ping_name;
        TextView match_fu_name;
        ImageView match_clear;
        TextView match_zhu_paiming;
        TextView match_fu_paiming;
        Button match_msg;
    }
}

