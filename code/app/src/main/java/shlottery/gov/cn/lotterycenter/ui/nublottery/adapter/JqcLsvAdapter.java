package shlottery.gov.cn.lotterycenter.ui.nublottery.adapter;

import android.content.Context;
import android.content.Intent;
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

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.Sf14Bean;
import shlottery.gov.cn.lotterycenter.callback.SfcEventbus;
import shlottery.gov.cn.lotterycenter.ui.activity.MatchAnalyticsActivity;
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

public class JqcLsvAdapter extends BaseAdapter {

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

    public JqcLsvAdapter(List<Sf14Bean.DataBean.IssueListBean.MatchListBean> mSf14Beans, Context context, Handler mHandler) {
        this.mSf14Beans = mSf14Beans;
        this.context = context;
        this.mHandler = mHandler;
        this.mInflater = LayoutInflater.from(context);
//        this.ActivityType = ActivityType;
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
            map.put(6, false);
            map.put(7, false);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.sfc_jqclsv_item, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.jqcPosition1Tv.setText(2 * position + 1 + "");
        mViewHolder.jqcPosition2Tv.setText(2 * position + 2 + "");
        mViewHolder.jqcbaseNameTv.setText(mSf14Beans.get(position).getLeagueName());
        mViewHolder.jqcbaseNameTv.setBackgroundColor(Color.parseColor(mSf14Beans.get(position).getLeagueColor()));
        long time = mSf14Beans.get(position).getMatchTime();

        mViewHolder.jqcbaseStophourTv.setText(DateUtils.formatMonthDay(time * 1000));
        mViewHolder.jqcbaseStopminTv.setText(DateUtils.formatTimeSimple(time * 1000));

        if (!StringUtils.isEmpty(mSf14Beans.get(position).getHostRank())) {
            mViewHolder.baseChildHostRankTv.setText("[" + mSf14Beans.get(position).getHostRank() + "]");
            mViewHolder.baseChildVisitRankTv.setText("[" + mSf14Beans.get(position).getVisitRank() + "]");
        }
        mViewHolder.baseChildHostNameTv.setText(mSf14Beans.get(position).getHostName());
        mViewHolder.baseChildVisitNameTv.setText(mSf14Beans.get(position).getVisitName());
        MyOnclicLisener myOnclicLisener = new MyOnclicLisener();
        mViewHolder.jqcHostCbx0.setOnClickListener(myOnclicLisener);
        mViewHolder.jqcHostCbx1.setOnClickListener(myOnclicLisener);
        mViewHolder.jqcHostCbx2.setOnClickListener(myOnclicLisener);
        mViewHolder.jqcHostCbx3.setOnClickListener(myOnclicLisener);
        mViewHolder.jqcVisitCbx0.setOnClickListener(myOnclicLisener);
        mViewHolder.jqcVisitCbx1.setOnClickListener(myOnclicLisener);
        mViewHolder.jqcVisitCbx2.setOnClickListener(myOnclicLisener);
        mViewHolder.jqcVisitCbx3.setOnClickListener(myOnclicLisener);
        final ViewHolder finalMViewHolder = mViewHolder;
       /* if (ActivityType == Configs.ACTIVITYTYPE_R9) {
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
        }*/

        checkBoxListener(mViewHolder.jqcHostCbx0, mViewHolder.jqcHostTv0, position, 0);
        checkBoxListener(mViewHolder.jqcHostCbx1, mViewHolder.jqcHostTv1, position, 1);
        checkBoxListener(mViewHolder.jqcHostCbx2, mViewHolder.jqcHostTv2, position, 2);
        checkBoxListener(mViewHolder.jqcHostCbx3, mViewHolder.jqcHostTv3, position, 3);
        checkBoxListener(mViewHolder.jqcVisitCbx0, mViewHolder.jqcVisitTv0, position, 4);
        checkBoxListener(mViewHolder.jqcVisitCbx1, mViewHolder.jqcVisitTv1, position, 5);
        checkBoxListener(mViewHolder.jqcVisitCbx2, mViewHolder.jqcVisitTv2, position, 6);
        checkBoxListener(mViewHolder.jqcVisitCbx3, mViewHolder.jqcVisitTv3, position, 7);
       /* mViewHolder.sptCbxD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
//                changeDan(position, finalMViewHolder.sfcdanTv);
            }
        });
//        changeDan(position, mViewHolder.sfcdanTv);
        mViewHolder.sfcdanTv.setChecked(mSf14Beans.get(position).isChecked());
        Logger.e(countNum + "---注数---" + danNum + "--胆的数量");
*/
        mViewHolder.jqcHostCbx0.setChecked(mSf14Beans.get(position).getSpsState(0));
        mViewHolder.jqcHostCbx1.setChecked(mSf14Beans.get(position).getSpsState(1));
        mViewHolder.jqcHostCbx2.setChecked(mSf14Beans.get(position).getSpsState(2));
        mViewHolder.jqcHostCbx3.setChecked(mSf14Beans.get(position).getSpsState(3));
        mViewHolder.jqcVisitCbx0.setChecked(mSf14Beans.get(position).getSpsState(4));
        mViewHolder.jqcVisitCbx1.setChecked(mSf14Beans.get(position).getSpsState(5));
        mViewHolder.jqcVisitCbx2.setChecked(mSf14Beans.get(position).getSpsState(6));
        mViewHolder.jqcVisitCbx3.setChecked(mSf14Beans.get(position).getSpsState(7));

        mViewHolder.sfcfenxiTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MatchAnalyticsActivity.class);
                intent.putExtra("matchId",mSf14Beans.get(position).getMatchId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }


    private void checkBoxListener(final CheckBox mCheckBox, final TextView mTextView, final int position, final int m) {
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for (int i = 0; i < mSf14Beans.size(); i++) {
                        if (position == i) {
//                            Message message = new Message();
//                            mHandler.sendMessage(message);
//                            mSf14Beans.get(position).setSpsState(0, true);
                            Logger.e("选中");
                            mSf14Beans.get(position).getCheckedHashMap().put(m, true);
                            mTextView.setTextColor(Color.parseColor("#FFFFFF"));
                        }
                    }
                } else {
                    for (int i = 0; i < mSf14Beans.size(); i++) {
                        if (position == i) {
//                            Message message = new Message();
//                            mHandler.sendMessage(message);
//                            mSf14Beans.get(position).setSpsState(0, false);
                            Logger.e("未选中");
                            mSf14Beans.get(position).getCheckedHashMap().put(m, false);
                            mTextView.setTextColor(Color.parseColor("#cf0100"));
                        }
                    }
                }
//                changeDan(position, finalMViewHolder.sfcdanTv);
            }
        });
    }


    static class ViewHolder {
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
        @BindView(R.id.jqc_host_tv0)
        TextView jqcHostTv0;
        @BindView(R.id.jqc_host_cbx1)
        CheckBox jqcHostCbx1;
        @BindView(R.id.jqc_host_tv1)
        TextView jqcHostTv1;
        @BindView(R.id.jqc_host_cbx2)
        CheckBox jqcHostCbx2;
        @BindView(R.id.jqc_host_tv2)
        TextView jqcHostTv2;
        @BindView(R.id.jqc_host_cbx3)
        CheckBox jqcHostCbx3;
        @BindView(R.id.jqc_host_tv3)
        TextView jqcHostTv3;
        @BindView(R.id.jqc_visit_cbx0)
        CheckBox jqcVisitCbx0;
        @BindView(R.id.jqc_visit_tv0)
        TextView jqcVisitTv0;
        @BindView(R.id.jqc_visit_cbx1)
        CheckBox jqcVisitCbx1;
        @BindView(R.id.jqc_visit_tv1)
        TextView jqcVisitTv1;
        @BindView(R.id.jqc_visit_cbx2)
        CheckBox jqcVisitCbx2;
        @BindView(R.id.jqc_visit_tv2)
        TextView jqcVisitTv2;
        @BindView(R.id.jqc_visit_cbx3)
        CheckBox jqcVisitCbx3;
        @BindView(R.id.jqc_visit_tv3)
        TextView jqcVisitTv3;
        @BindView(R.id.spt_lost3_ll)
        LinearLayout sptLost3Ll;
        @BindView(R.id.sfcdan_tv)
        CheckBox sfcdanTv;
        @BindView(R.id.sfcfenxi_tv)
        TextView sfcfenxiTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private class MyOnclicLisener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            mHandler.sendEmptyMessage(0);
        }
    }
}
