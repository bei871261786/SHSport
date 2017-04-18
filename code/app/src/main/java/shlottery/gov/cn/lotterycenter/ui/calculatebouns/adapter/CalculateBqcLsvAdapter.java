package shlottery.gov.cn.lotterycenter.ui.calculatebouns.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.Sf14Bean;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-07-0007 11:28
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class CalculateBqcLsvAdapter extends BaseAdapter {

    private List<Sf14Bean.DataBean.IssueListBean.MatchListBean> mSf14Beans;
    private Context context;
    private Handler mHandler;
    private int danNum = 0;
    private int countNum = 1;

    public int getDanNum() {
        return danNum;
    }

    public void setDanNum(int danNum) {
        this.danNum = danNum;
    }

    public int getCountNum() {
        return countNum;
    }

    public void setCountNum(int countNum) {
        this.countNum = countNum;
    }

    public CalculateBqcLsvAdapter(List<Sf14Bean.DataBean.IssueListBean.MatchListBean> mSf14Beans, Context context, Handler mHandler) {
        this.mSf14Beans = mSf14Beans;
        this.context = context;
        this.mHandler = mHandler;
        this.mInflater = LayoutInflater.from(context);
        intData(mSf14Beans);
    }

    public void intData(List<Sf14Bean.DataBean.IssueListBean.MatchListBean> mSf14Beans) {
        for (int i = 0; i < mSf14Beans.size(); i++) {
            HashMap<Integer, Boolean> map = new HashMap<>();
            map.put(0, false);
            map.put(1, false);
            map.put(2, false);
            map.put(3, false);
            map.put(4, false);
            map.put(5, false);
            mSf14Beans.get(i).setCheckedHashMap(map);
            mSf14Beans.get(i).setCanChecked(false);
            mSf14Beans.get(i).setChecked(false);
        }
    }

    public List<Sf14Bean.DataBean.IssueListBean.MatchListBean> getmSf14Beans() {
        return mSf14Beans;
    }

    public void setmSf14Beans(List<Sf14Bean.DataBean.IssueListBean.MatchListBean> mSf14Beans) {
        this.mSf14Beans = mSf14Beans;
    }


    private LayoutInflater mInflater = null;

    @Override
    public int getCount() {
        return mSf14Beans.size();
    }

    @Override
    public Object getItem(int position) {
        return mSf14Beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.sfccal_bqclsv_item, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
            mViewHolder.hostList.add(mViewHolder.jqcHostCbx0);
            mViewHolder.hostList.add(mViewHolder.jqcHostCbx1);
            mViewHolder.hostList.add(mViewHolder.jqcHostCbx2);
            mViewHolder.visitList.add(mViewHolder.jqcVisitCbx0);
            mViewHolder.visitList.add(mViewHolder.jqcVisitCbx1);
            mViewHolder.visitList.add(mViewHolder.jqcVisitCbx2);
            mViewHolder.hostTxts.add(mViewHolder.bqcHostWin);
            mViewHolder.hostTxts.add(mViewHolder.bqcHostPing);
            mViewHolder.hostTxts.add(mViewHolder.bqcHostLost);
            mViewHolder.visitTxts.add(mViewHolder.bqcVisitWin);
            mViewHolder.visitTxts.add(mViewHolder.bqcVisitPing);
            mViewHolder.visitTxts.add(mViewHolder.bqcVisitLost);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.jqcPosition1Tv.setText((position + 1) * 2 - 1 + "");
        mViewHolder.jqcPosition2Tv.setText((position + 1) * 2 + "");
        mViewHolder.jqcbaseNameTv.setText(mSf14Beans.get(position).getLeagueName());
        mViewHolder.jqcbaseNameTv.setBackgroundColor(Color.parseColor(mSf14Beans.get(position).getLeagueColor()));
        long time = mSf14Beans.get(position).getMatchTime();
        mViewHolder.jqcbaseStophourTv.setText(DateUtils.formatMonthDay(time * 1000));
        mViewHolder.jqcbaseStopminTv.setText(DateUtils.formatTimeSimple(time * 1000));

        if (!StringUtils.isEmpty(mSf14Beans.get(position).getHostRank())) {
            mViewHolder.baseChildHostRankTv.setText("[" + mSf14Beans.get(position).getHostRank() + "]");
            mViewHolder.baseChildVisitRankTv.setText("[" + mSf14Beans.get(position).getVisitRank() + "]");
        }
        else
        {
            mViewHolder.baseChildHostRankTv.setText("");
            mViewHolder.baseChildVisitRankTv.setText("");
        }
        mViewHolder.baseChildHostNameTv.setText(mSf14Beans.get(position).getHostName());
        mViewHolder.baseChildVisitNameTv.setText(mSf14Beans.get(position).getVisitName());
        final ViewHolder finalMViewHolder = mViewHolder;
        for (int i = 0; i < mViewHolder.hostList.size(); i++) {
            final int k = i;
            mViewHolder.hostList.get(i).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (!b) {
                        LogUtils.i("bqcAdapter host no checked" + position);
                        for (int i = 0; i < mSf14Beans.size(); i++) {
                            if (position == i) {
                                Message message = new Message();
                                mHandler.sendMessage(message);
                                mSf14Beans.get(position).getCheckedHashMap().put(k, false);
                                LogUtils.i("bqcAdapter isChecked:" + mSf14Beans.get(position).getCheckedHashMap().get(k));
                                finalMViewHolder.hostTxts.get(k).setTextColor(context.getResources().getColor(R.color.select_red));
                            }
                        }
                    } else {
                        LogUtils.i("bqcAdapter host  checked" + position);
                        for (int i = 0; i < mSf14Beans.size(); i++) {
                            if (position == i) {
                                Message message = new Message();
                                mHandler.sendMessage(message);
                                mSf14Beans.get(position).getCheckedHashMap().put(k, true);
                                LogUtils.i("bqcAdapter isChecked:" + mSf14Beans.get(position).getCheckedHashMap().get(k));
                                finalMViewHolder.hostTxts.get(k).setTextColor(context.getResources().getColor(R.color.white));
                            }
                        }
                    }
                }
            });
        }

        for (int i = 0; i < mViewHolder.visitList.size(); i++) {
            final int k = i;
            mViewHolder.visitList.get(i).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (!b) {
                        LogUtils.i("bqcAdapter visit  no checked" + position);
                        for (int i = 0; i < mSf14Beans.size(); i++) {
                            if (position == i) {
                                Message message = new Message();
                                mHandler.sendMessage(message);
                                mSf14Beans.get(position).getCheckedHashMap().put(3 + k, false);
                                finalMViewHolder.visitTxts.get(k).setTextColor(context.getResources().getColor(R.color.select_red));
                            }
                        }
                    } else {
                        LogUtils.i("bqcAdapter visit  checked" + position);
                        for (int i = 0; i < mSf14Beans.size(); i++) {
                            if (position == i) {
                                Message message = new Message();
                                mHandler.sendMessage(message);
                                mSf14Beans.get(position).getCheckedHashMap().put(3 + k, true);
                                finalMViewHolder.visitTxts.get(k).setTextColor(context.getResources().getColor(R.color.white));
                            }
                        }
                    }
                }
            });
        }


        for (int i = 0; i < mViewHolder.hostList.size(); i++) {
            LogUtils.i("bqcAdapter host getChecked state:" + mSf14Beans.get(position).getSpsState(i));
            mViewHolder.hostList.get(i).setChecked(mSf14Beans.get(position).getSpsState(i));
        }
        for (int i = 0; i < mViewHolder.visitList.size(); i++) {
            LogUtils.i("bqcAdapter visit getChecked state:" + mSf14Beans.get(position).getSpsState(3 + i));
            mViewHolder.visitList.get(i).setChecked(mSf14Beans.get(position).getSpsState(3 + i));
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.jqc_position1_tv)
        TextView jqcPosition1Tv;
        @BindView(R.id.jqc_position2_tv)
        TextView jqcPosition2Tv;
        @BindView(R.id.jqcbase_name_tv)
        TextView jqcbaseNameTv;
        @BindView(R.id.jqcbase_stophour_tv)
        TextView jqcbaseStophourTv;
        @BindView(R.id.jqcbase_stopmin_tv)
        TextView jqcbaseStopminTv;
        @BindView(R.id.baseChild_host_rank_tv)
        TextView baseChildHostRankTv;
        @BindView(R.id.baseChild_host_name_tv)
        TextView baseChildHostNameTv;
        @BindView(R.id.baseChild_ping_name_tv)
        TextView baseChildPingNameTv;
        @BindView(R.id.baseChild_visit_name_tv)
        TextView baseChildVisitNameTv;
        @BindView(R.id.baseChild_visit_rank_tv)
        TextView baseChildVisitRankTv;
        @BindView(R.id.jqc_host_cbx0)
        CheckBox jqcHostCbx0;
        @BindView(R.id.bqc_host_win)
        TextView bqcHostWin;
        @BindView(R.id.jqc_host_cbx1)
        CheckBox jqcHostCbx1;
        @BindView(R.id.bqc_host_ping)
        TextView bqcHostPing;
        @BindView(R.id.jqc_host_cbx2)
        CheckBox jqcHostCbx2;
        @BindView(R.id.bqc_host_lost)
        TextView bqcHostLost;
        @BindView(R.id.jqc_visit_cbx0)
        CheckBox jqcVisitCbx0;
        @BindView(R.id.bqc_visit_win)
        TextView bqcVisitWin;
        @BindView(R.id.jqc_visit_cbx1)
        CheckBox jqcVisitCbx1;
        @BindView(R.id.bqc_visit_ping)
        TextView bqcVisitPing;
        @BindView(R.id.jqc_visit_cbx2)
        CheckBox jqcVisitCbx2;
        @BindView(R.id.bqc_visit_lost)
        TextView bqcVisitLost;
        @BindView(R.id.sfcfenxi_tv)
        TextView sfcfenxiTv;
        ArrayList<CheckBox> hostList;
        ArrayList<CheckBox> visitList;
        ArrayList<TextView> hostTxts;
        ArrayList<TextView> visitTxts;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            hostList = new ArrayList<>();
            visitList = new ArrayList<>();
            hostTxts = new ArrayList<>();
            visitTxts = new ArrayList<>();
        }
    }
}
