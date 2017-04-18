package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;
import shlottery.gov.cn.lotterycenter.utils.JcDataUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.SoccerDialogUtils;


/**
 * Created by booob on 2016-06-01-0001.
 */
public class DingDanMixListViewBasketAdapter extends DingdanBaseAdapter {
    private Context context;
    private LayoutInflater mInflater = null;
    private List<HashMap<Integer, MatchsBean>> mDatas;
    private Handler handler;
    private int mType;


    public DingDanMixListViewBasketAdapter(Context context, List<HashMap<Integer, MatchsBean>> mDatas, Handler handler, int mType) {
        super(context);
        this.context = context;
        this.mDatas = mDatas;
        this.handler = handler;
        this.mType = mType;
        this.mInflater = LayoutInflater.from(context);
        for (int i = 0; i < mDatas.size(); i++) {
            mDanList.add(false);
        }
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
        LogUtils.i("dingdangetViewhaha" + mDatas.size());
        Holder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.basketball_base_dingdanitem_1, null);
            holder = new Holder();
            holder.match_zhu_name = (TextView) convertView
                    .findViewById(R.id.baseChild_host_name_tv);
            holder.match_ping_name = (TextView) convertView
                    .findViewById(R.id.baseChild_ping_name_tv);
            holder.mMatchHostRank = (TextView) convertView
                    .findViewById(R.id.baseChild_host_rank_tv);
            holder.mMatchVisitRank = (TextView) convertView
                    .findViewById(R.id.baseChild_visit_rank_tv);
            holder.match_fu_name = (TextView) convertView
                    .findViewById(R.id.baseChild_visit_name_tv);
            holder.match_dantuo = (CheckBox) convertView
                    .findViewById(R.id.match_dantuo);
            holder.match_clear = (ImageView) convertView.findViewById(R.id.match_clear);
            holder.match_msg = (Button) convertView.findViewById(R.id.dingdan_base_button);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        final int mixId = JcDataUtils.getIndicator(mDatas.get(position));

        String mHostRank;
        String mVisitRank;
        if (null == mDatas.get(position).get(mixId).getHostRank() || mDatas.get(position).get(mixId).getHostRank().equals("")) {
            mHostRank = "";
        } else {
            mHostRank = "[" + mDatas.get(position).get(mixId).getHostRank() + "]";
        }
        if (null == mDatas.get(position).get(mixId).getVisitRank() || mDatas.get(position).get(mixId).getVisitRank().equals("")) {
            mVisitRank = "";
        } else {
            mVisitRank = "[" + mDatas.get(position).get(mixId).getVisitRank() + "]";
        }

        holder.mMatchHostRank.setText(mHostRank);
        holder.mMatchVisitRank.setText(mVisitRank);

        //如果选择更小串法，下达清除命令，清除所有定胆状态
        if (mIsCleanDan) {
            mDanList.clear();
            for (int i = 0; i < mDatas.size(); i++) {
                mDanList.add(false);
            }
            holder.match_dantuo.setEnabled(true);
            mDanCunt = 0;
        }
        LogUtils.i("adapter spf dan1:::::" + "mDanCunt:" + mDanCunt + "::" + "mDanShouldCunt:" + mDanShouldCunt + "::dan Enable?" + holder.match_dantuo.isEnabled() + "::" + mIsCleanDan);
        //如果达到定胆数量，剩余胆变为不可用
        if (mDanCunt >= mDanShouldCunt && !mDanList.get(position)) {
            holder.match_dantuo.setEnabled(false);
        } else {//如果没有达到定胆数量，则胆可用，并且清除命令取消
            holder.match_dantuo.setEnabled(true);
            mIsCleanDan = false;
        }
        LogUtils.i("adapter spf dan2:" + "mDanCunt:" + mDanCunt + "::" + "mDanShouldCunt:" + mDanShouldCunt + "::dan Enable?" + holder.match_dantuo.isEnabled() + "::" + mIsCleanDan);
        holder.match_dantuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                boolean isChecked = cb.isChecked();
                if (isChecked) {
                    mDanList.remove(position);
                    mDanList.add(position, true);
                    mDanCunt++;
                    LogUtils.i("adapter spf dan" + mDatas.get(position).get(mixId).isDan() + "::" + mDanCunt);
                } else {
                    mDanList.remove(position);
                    mDanList.add(position, false);
                    mDanCunt--;
                    LogUtils.i("adapter spf dan " + mDatas.get(position).get(mixId).isDan() + "::" + mDanCunt);
                }
                handler.sendEmptyMessage(1);
            }
        });

        holder.match_msg.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    if (mType == Configure_JC.TYPE_BASKETBALL) {
                                                        new SoccerDialogUtils().showMixBasketDialog(context, mDatas.get(position), handler);
                                                        // DialogUtils.ShowXieYiDialog(context);
                                                    } else {
                                                        new SoccerDialogUtils().showMixDialog(context, mDatas.get(position), handler);
                                                    }
                                                }
                                            }
        );
        LogUtils.i("bqc  show state" + (mDatas == null) + ":::" + (mDatas.get(position) == null));
        holder.match_zhu_name.setText(mDatas.get(position).
                get(Configure_JC.TAB_BF)
                .
                        getHostName()

        );
        holder.match_ping_name.setText("VS");
        holder.match_fu_name.setText(mDatas.get(position).
                get(Configure_JC.TAB_BF)
                .
                        getVisitName()

        );
        StringBuilder sb = new StringBuilder();
        LogUtils.i("dingdan mix DingdanSize:" +
                getDingdanInfo()
                        .
                                size()

                + "::::");
        LogUtils.i("dingdan count是：" + position + ":::" +
                getDingdanInfo()
                        .
                                size()
        );
        LogUtils.i("dingdan  循环 over" + sb.toString());
        LogUtils.i("DingdanMixAdapter info:" +
                getDingdanInfo(position)
                        .
                                getItemInfo()
        );
        holder.match_msg.setText(
                getDingdanInfo(position)
                        .getItemInfo()
        );
        holder.match_clear.setOnClickListener(new View.OnClickListener()

                                              {
                                                  @Override
                                                  public void onClick(View v) {
                                                      Message message = new Message();
                                                      Bundle bundle = new Bundle();
                                                      message.setData(bundle);
                                                      handler.sendMessage(message);
                                                      if (mDanList.get(position)) {
                                                          mDanCunt--;
                                                      }
                                                      mDatas.remove(position);
                                                      mDanList.remove(position);
                                                      notifyDataSetChanged();
                                                  }
                                              }

        );
        holder.match_dantuo.setChecked(mDanList.get(position));
        return convertView;
    }

    static class Holder {
        CheckBox match_dantuo;
        TextView match_zhu_name;
        TextView match_ping_name;
        TextView match_fu_name;
        TextView mMatchHostRank;
        TextView mMatchVisitRank;
        ImageView match_clear;
        Button match_msg;
    }
}