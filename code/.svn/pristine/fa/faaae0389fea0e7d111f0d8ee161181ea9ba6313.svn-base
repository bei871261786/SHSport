package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;
import shlottery.gov.cn.lotterycenter.utils.JcDataUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * Created by booob on 2016-06-01-0001.
 */
public class DingDanDxListViewAdapter extends DingdanBaseAdapter {
    private Context context;
    private LayoutInflater mInflater = null;
    private List<MatchsBean> mDatas;
    private Handler handler;
    private boolean isDanguan;

    public DingDanDxListViewAdapter(Context context, List<MatchsBean> mDatas, Handler handler, boolean isDanguan) {
        super(context);
        this.context = context;
        this.mDatas = mDatas;
        this.handler = handler;
        this.isDanguan = isDanguan;
        this.mInflater = LayoutInflater.from(context);
        initDanCount(mDatas);
    }

    @Override
    public int getCount() {
        LogUtils.i("dingdan  spf count:" + mDatas.size());
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
        Holder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.basketball_base_dingdanitem_dx, null);
            holder = new Holder();
            holder.match_zhu_name = (TextView) convertView
                    .findViewById(R.id.baseChild_host_name_tv);
            holder.match_zhu_paiming = (TextView) convertView
                    .findViewById(R.id.baseChild_host_rank_tv);
            holder.match_zhu_spf = (TextView) convertView
                    .findViewById(R.id.dingdan_zhu_spf);
            holder.match_ping_name = (TextView) convertView
                    .findViewById(R.id.baseChild_ping_name_tv);
            holder.match_fu_name = (TextView) convertView
                    .findViewById(R.id.baseChild_visit_name_tv);
            holder.match_fu_paiming = (TextView) convertView
                    .findViewById(R.id.baseChild_visit_rank_tv);
            holder.match_fu_spf = (TextView) convertView
                    .findViewById(R.id.dingdan_fu_spf);
            holder.match_dantuo = (CheckBox) convertView
                    .findViewById(R.id.match_dantuo);
            holder.match_clear = (ImageView) convertView.findViewById(R.id.match_clear);
            holder.spt_cbx_w = (CheckBox) convertView.findViewById(R.id.dingdan_cbx_w);
            holder.spt_cbx_l = (CheckBox) convertView.findViewById(R.id.dingdan_cbx_l);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        if (isDanguan) {
            holder.match_dantuo.setVisibility(View.GONE);
        }
        double hand = mDatas.get(position).getHand();
        holder.match_zhu_name.setText(mDatas.get(position).getHostName());

        holder.match_zhu_spf.setText("大分" + mDatas.get(position).getSps().get(0));
        holder.match_ping_name.setTextColor(BaseApplication.getApplication().getResources().getColor(R.color.select_red));
        holder.match_ping_name.setText("(" + hand + ")");
        holder.match_fu_name.setText(mDatas.get(position).getVisitName());

        holder.match_fu_spf.setText("小分" + mDatas.get(position).getSps().get(1));
        holder.match_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDatas.get(position).isDan()) {
                    mDanCunt--;
                }
                mDatas.remove(position);
                handler.sendEmptyMessage(1);
            }
        });

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

        holder.match_zhu_paiming.setText(mHostRank);
        holder.match_fu_paiming.setText(mVisitRank);
        final Holder finalChildHolder = holder;
        LogUtils.i("adapter spf dan:" + "mDanCunt:" + mDanCunt + "::" + "mDanShouldCunt:" + mDanShouldCunt + "::dan Enable?" + holder.match_dantuo.isEnabled());
        LogUtils.i("adapter spf dan1:::::" + "mDanCunt:" + mDanCunt + "::" + "mDanShouldCunt:" + mDanShouldCunt + "::dan Enable?" + holder.match_dantuo.isEnabled() + "::" + mIsCleanDan);
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
        holder.spt_cbx_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(1);
            }
        });
        holder.spt_cbx_w.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for (int i = 0; i < mDatas.size(); i++) {
                        if (position == i) {
                            JcDataUtils.setSpsState(mDatas.get(i), 0, true);
                            finalChildHolder.match_zhu_spf.setTextColor(Color.WHITE);
                        }
                    }
                } else {
                    for (int i = 0; i < mDatas.size(); i++) {
                        if (position == i) {
                            JcDataUtils.setSpsState(mDatas.get(i), 0, false);
                            finalChildHolder.match_zhu_spf.setTextColor(Color.parseColor("#ff787878"));
                        }
                    }
                }
            }
        });

        final Holder finalChildHolder2 = holder;
        holder.spt_cbx_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(1);
            }
        });
        holder.spt_cbx_l.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for (int i = 0; i < mDatas.size(); i++) {
                        if (position == i) {
                            JcDataUtils.setSpsState(mDatas.get(i), 1, true);
                            finalChildHolder2.match_fu_spf.setTextColor(Color.WHITE);
                        }
                    }
                } else {
                    for (int i = 0; i < mDatas.size(); i++) {
                        if (position == i) {
                            JcDataUtils.setSpsState(mDatas.get(i), 1, false);
                            finalChildHolder2.match_fu_spf.setTextColor(Color.parseColor("#ff787878"));
                        }
                    }
                }

            }
        });
        //必须放在setOnCheckedChangeListener之后,否则checkbox的状态会随着listview的滑动而改变
        holder.spt_cbx_w.setChecked(JcDataUtils.getSpsState(mDatas.get(position), 0));
        holder.spt_cbx_l.setChecked(JcDataUtils.getSpsState(mDatas.get(position), 1));
        if (!mDatas.get(position).getSpsStateMap().get(0) && !mDatas.get(position).getSpsStateMap().get(1)) {
            if (mDatas.get(position).isDan()) {
                mDatas.get(position).setDan(false);
                mDanCunt--;
            }
            holder.match_dantuo.setEnabled(false);
        } else {
            if (mDanCunt < mDanShouldCunt) {
                holder.match_dantuo.setEnabled(true);
            }
        }
        holder.match_dantuo.setChecked(mDatas.get(position).isDan());

        return convertView;
    }

    static class Holder {
        CheckBox match_dantuo;
        TextView match_zhu_name;
        TextView match_zhu_paiming;
        TextView match_zhu_spf;
        TextView match_ping_name;
        TextView match_fu_name;
        TextView match_fu_paiming;
        TextView match_fu_spf;
        CheckBox spt_cbx_w;
        CheckBox spt_cbx_l;
        ImageView match_clear;
    }
}