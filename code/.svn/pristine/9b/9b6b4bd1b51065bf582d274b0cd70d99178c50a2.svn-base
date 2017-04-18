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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.Sf14Bean;
import shlottery.gov.cn.lotterycenter.callback.SfcEventbus;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
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

public class Sf14CalculateLsvAdapter extends BaseAdapter {

    private List<Sf14Bean.DataBean.IssueListBean.MatchListBean> mSf14Beans;
    private Context context;
    private Handler mHandler;
    private int ActivityType;//Activity的类型
    private SfcEventbus sfcEventbus;
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

    public Sf14CalculateLsvAdapter(List<Sf14Bean.DataBean.IssueListBean.MatchListBean> mSf14Beans, Context context, Handler mHandler, int ActivityType) {
        this.mSf14Beans = mSf14Beans;
        this.context = context;
        this.mHandler = mHandler;
        this.mInflater = LayoutInflater.from(context);
        this.ActivityType = ActivityType;
        intData(mSf14Beans);
    }

    public void intData(List<Sf14Bean.DataBean.IssueListBean.MatchListBean> mSf14Beans) {
        for (int i = 0; i < mSf14Beans.size(); i++) {
            HashMap<Integer, Boolean> map = new HashMap<>();
            map.put(0, false);
            map.put(1, false);
            map.put(2, false);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.sf14cal_lsv_item, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.sfcPositionTv.setText(position + 1 + "");
        mViewHolder.sfbaseNameTv.setText(mSf14Beans.get(position).getLeagueName());
        mViewHolder.sfbaseNameTv.setBackgroundColor(Color.parseColor(mSf14Beans.get(position).getLeagueColor()));
        long time = mSf14Beans.get(position).getMatchTime();
        mViewHolder.sfbaseStoptimeTv.setText(DateUtils.getPl5DateAndTime(time * 1000));
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
        if (ActivityType == Configs.ACTIVITYTYPE_R9) {
            mViewHolder.sfcdanTv.setVisibility(View.VISIBLE);
            mViewHolder.sfcdanTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new SfcEventbus());
                }
            });
            mViewHolder.sptCbxD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new SfcEventbus());
                }
            });
            mViewHolder.sptCbxW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new SfcEventbus());
                }
            });
            mViewHolder.sptCbxL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new SfcEventbus());
                }
            });
            mViewHolder.sfcdanTv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        for (int i = 0; i < mSf14Beans.size(); i++) {
                            if (position == i) {
                                Message message = new Message();
                                mHandler.sendMessage(message);
                                mSf14Beans.get(position).setChecked(true);
//                                changeDan(position, finalMViewHolder.sfcdanTv);
                                danNum++;
                            }
                        }
                    } else {
                        for (int i = 0; i < mSf14Beans.size(); i++) {
                            if (position == i) {
                                Message message = new Message();
                                mHandler.sendMessage(message);
                                mSf14Beans.get(position).setChecked(false);
//                                changeDan(position, finalMViewHolder.sfcdanTv);
                                danNum--;
                            }
                        }
                    }
                }
            });
//            changeDan(position, finalMViewHolder.sfcdanTv);
        }

        mViewHolder.sptCbxW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    for (int i = 0; i < mSf14Beans.size(); i++) {
                        if (position == i) {
                            Message message = new Message();
                            mHandler.sendMessage(message);
//                            mSf14Beans.get(position).setSpsState(0, true);
                            mSf14Beans.get(position).getCheckedHashMap().put(0, true);
                            finalMViewHolder.matchZhuSpf.setTextColor(Color.parseColor("#FFFFFF"));
                        }
                    }
                } else {
                    for (int i = 0; i < mSf14Beans.size(); i++) {
                        if (position == i) {
                            Message message = new Message();
                            mHandler.sendMessage(message);
//                            mSf14Beans.get(position).setSpsState(0, false);
                            mSf14Beans.get(position).getCheckedHashMap().put(0, false);
                            finalMViewHolder.matchZhuSpf.setTextColor(Color.parseColor("#cf0100"));
                        }
                    }
                }
            }
        });

        mViewHolder.sptCbxD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for (int i = 0; i < mSf14Beans.size(); i++) {
                        if (position == i) {
                            Message message = new Message();
                            mHandler.sendMessage(message);
//                            mSf14Beans.get(position).setSpsState(1, true);
                            mSf14Beans.get(position).getCheckedHashMap().put(1, true);
                            finalMViewHolder.matchPingSpf.setTextColor(Color.parseColor("#FFFFFF"));
                        }
                    }
                } else {
                    for (int i = 0; i < mSf14Beans.size(); i++) {
                        if (position == i) {
                            Message message = new Message();
                            mHandler.sendMessage(message);
//                            mSf14Beans.get(position).setSpsState(1, false);
                            mSf14Beans.get(position).getCheckedHashMap().put(1, false);
                            finalMViewHolder.matchPingSpf.setTextColor(Color.parseColor("#cf0100"));
                        }
                    }
                }
//                changeDan(position, finalMViewHolder.sfcdanTv);
            }
        });
        mViewHolder.sptCbxL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for (int i = 0; i < mSf14Beans.size(); i++) {
                        if (position == i) {
                            Message message = new Message();
                            mHandler.sendMessage(message);
//                            mSf14Beans.get(position).setSpsState(2, true);
                            mSf14Beans.get(position).getCheckedHashMap().put(2, true);
                            finalMViewHolder.matchFuSpf.setTextColor(Color.parseColor("#FFFFFF"));
                        }
                    }
                } else {
                    for (int i = 0; i < mSf14Beans.size(); i++) {
                        if (position == i) {
                            Message message = new Message();
                            mHandler.sendMessage(message);
//                            mSf14Beans.get(position).setSpsState(2, false);
                            mSf14Beans.get(position).getCheckedHashMap().put(2, false);
                            finalMViewHolder.matchFuSpf.setTextColor(Color.parseColor("#cf0100"));
                        }
                    }
                }
            }
        });
        mViewHolder.sfcdanTv.setChecked(mSf14Beans.get(position).isChecked());
        Logger.e(countNum + "---注数---" + danNum + "--胆的数量");

        mViewHolder.sfcdanTv.setEnabled(mSf14Beans.get(position).isCanChecked());
        mViewHolder.sptCbxW.setChecked(mSf14Beans.get(position).getSpsState(0));
        mViewHolder.sptCbxD.setChecked(mSf14Beans.get(position).getSpsState(1));
        mViewHolder.sptCbxL.setChecked(mSf14Beans.get(position).getSpsState(2));
        return convertView;
    }

    private void changeDan(int position, CheckBox checkBox) {
        for (int i = 0; i < mSf14Beans.size(); i++) {
            if (position == i) {
                if (mSf14Beans.get(position).getCheckedHashMap().get(0) || mSf14Beans.get(position).getCheckedHashMap().get(1) || mSf14Beans.get(position).getCheckedHashMap().get(2)) {
                    Logger.e(countNum + "---注数---" + danNum + "--胆的数量");
                    if (danNum <= countNum - 1 && danNum <= 8) {
                        mSf14Beans.get(position).setCanChecked(true);
                    } else {
                        mSf14Beans.get(position).setCanChecked(false);
                    }
                } else {
                    mSf14Beans.get(position).setCanChecked(false);
                    // checkBox.setChecked(false);
                    mSf14Beans.get(position).setChecked(false);
                    countNum--;
                }
            }
        }
        checkBox.setEnabled(mSf14Beans.get(position).isCanChecked());
    }

    static class ViewHolder {
        @BindView(R.id.sfc_position_tv)
        TextView sfcPositionTv;
        @BindView(R.id.sfbase_name_tv)
        TextView sfbaseNameTv;
        @BindView(R.id.sfbase_stoptime_tv)
        TextView sfbaseStoptimeTv;
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
        @BindView(R.id.spt_cbx_w)
        CheckBox sptCbxW;
        @BindView(R.id.match_zhu_spf)
        TextView matchZhuSpf;
        @BindView(R.id.spt_win_ll)
        LinearLayout sptWinLl;
        @BindView(R.id.spt_cbx_d)
        CheckBox sptCbxD;
        @BindView(R.id.match_ping_spf)
        TextView matchPingSpf;
        @BindView(R.id.spt_draw_ll)
        LinearLayout sptDrawLl;
        @BindView(R.id.spt_cbx_l)
        CheckBox sptCbxL;
        @BindView(R.id.match_fu_spf)
        TextView matchFuSpf;
        @BindView(R.id.spt_lost_ll)
        LinearLayout sptLostLl;
        @BindView(R.id.sf14win_tv)
        TextView sf14winTv;
        @BindView(R.id.sf14ping_tv)
        TextView sf14pingTv;
        @BindView(R.id.sf14fu_tv)
        TextView sf14fuTv;
        @BindView(R.id.sfcdan_tv)
        CheckBox sfcdanTv;
        @BindView(R.id.sfcfenxi_tv)
        TextView sfcfenxiTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
