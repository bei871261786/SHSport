package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.ZhudanDetailBean;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-24-0024 17:31
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class JcZDDetailAdapter extends BaseAdapter {
    private ZhudanDetailBean zhuDanDetailBean;
    private Context context;
    private int typeCount;

    public JcZDDetailAdapter(ZhudanDetailBean zhuDanDetailBean, Context context) {
        this.zhuDanDetailBean = zhuDanDetailBean;
        this.context = context;
        intTypeCount();
    }

    private void intTypeCount() {
        int m = 0;
        int n = 0;
        int p = 0;
        for (int i = 0; i < zhuDanDetailBean.getData().getMatchList().size(); i++) {
            if (zhuDanDetailBean.getData().getMatchList().get(i).getStakeSp().size() <= 5) {
                m = 1;
            } else if (zhuDanDetailBean.getData().getMatchList().get(i).getStakeSp().size() > 5 && zhuDanDetailBean.getData().getMatchList().get(i).getStakeOption().size() <= 10) {
                n = 1;
            } else {
                p = 1;
            }

        }
        typeCount = m + n + p;
        Logger.e(typeCount + "typeCount");
    }

    @Override
    public int getCount() {
        if (zhuDanDetailBean == null) {
            return 0;
        }
        Logger.e(typeCount + "typeCount");
        return zhuDanDetailBean.getData().getMatchList().size();
    }

    @Override
    public Object getItem(int position) {
        return zhuDanDetailBean.getData().getMatchList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        Logger.e(typeCount + "typeCount");
        return typeCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().size() <= 5) {
            Logger.e(typeCount + "typeCount");
            return 0;
        } else if (zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().size() > 5 && zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().size() <= 10) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (getItemViewType(position) == 0) {//长度小于五条时
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.zhudandetail_jc_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.zhudanWeekTv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getWeekNo());
            viewHolder.zhudanLeagueTv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getLeagueName());
            viewHolder.zhudanHostnameTv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getHostName());
            viewHolder.zhudanVisitTv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getVisitName());
            for (int i = 0; i < zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().size(); i++) {
                if (i == 0) {
                    viewHolder.spsLl1.setVisibility(View.VISIBLE);
                    viewHolder.zhudanVictory1Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(0));
                    viewHolder.zhudanSps1Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(0));
                } else if (i == 1) {
                    viewHolder.spsLl2.setVisibility(View.VISIBLE);
                    viewHolder.zhudanVictory2Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(1));
                    viewHolder.zhudanSps2Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(1));
                } else if (i == 2) {
                    viewHolder.spsLl3.setVisibility(View.VISIBLE);
                    viewHolder.zhudanVictory3Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(2));
                    viewHolder.zhudanSps3Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(2));
                } else if (i == 3) {
                    viewHolder.spsLl4.setVisibility(View.VISIBLE);
                    viewHolder.zhudanVictory4Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(3));
                    viewHolder.zhudanSps4Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(3));
                } else if (i == 4) {
                    viewHolder.spsLl5.setVisibility(View.VISIBLE);
                    viewHolder.zhudanVictory5Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(4));
                    viewHolder.zhudanSps5Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(4));
                }
            }
            int time = zhuDanDetailBean.getData().getMatchList().get(position).getMatchTime();
            long time1 = (long) time * 1000;
            viewHolder.zhudanStoptimeTv.setText(DateUtils.formatDateAndTime(time1));
        } else if (getItemViewType(position) == 1) {//长度大于5小于10
            ViewHolder2 viewHolder2 = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.zhudandetail_jcbig_item, null);
                viewHolder2 = new ViewHolder2(convertView);
                convertView.setTag(viewHolder2);
            } else {
                viewHolder2 = (ViewHolder2) convertView.getTag();
            }
            viewHolder2.zhudanWeekTv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getWeekNo());
            viewHolder2.zhudanLeagueTv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getLeagueName());
            viewHolder2.zhudanHostnameTv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getHostName());
            viewHolder2.zhudanVisitTv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getVisitName());
            for (int i = 0; i < zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().size(); i++) {
                if (i == 0) {
                    viewHolder2.spsLl1.setVisibility(View.VISIBLE);
                    viewHolder2.zhudanVictory1Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder2.zhudanSps1Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                } else if (i == 1) {
                    viewHolder2.spsLl2.setVisibility(View.VISIBLE);
                    viewHolder2.zhudanVictory2Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder2.zhudanSps2Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                } else if (i == 2) {
                    viewHolder2.spsLl3.setVisibility(View.VISIBLE);
                    viewHolder2.zhudanVictory3Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder2.zhudanSps3Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                } else if (i == 3) {
                    viewHolder2.spsLl4.setVisibility(View.VISIBLE);
                    viewHolder2.zhudanVictory4Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder2.zhudanSps4Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                } else if (i == 4) {
                    viewHolder2.spsLl5.setVisibility(View.VISIBLE);
                    viewHolder2.zhudanVictory5Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder2.zhudanSps5Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                }
                if (i == 5) {
                    viewHolder2.spsLl21.setVisibility(View.VISIBLE);
                    viewHolder2.zhudanVictory1Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder2.zhudanSps1Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                } else if (i == 6) {
                    viewHolder2.spsLl22.setVisibility(View.VISIBLE);
                    viewHolder2.zhudanVictory2Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder2.zhudanSps2Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                } else if (i == 7) {
                    viewHolder2.spsLl23.setVisibility(View.VISIBLE);
                    viewHolder2.zhudanVictory23Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder2.zhudanSps23Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                } else if (i == 8) {
                    viewHolder2.spsLl24.setVisibility(View.VISIBLE);
                    viewHolder2.zhudanVictory24Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder2.zhudanSps24Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                } else if (i == 9) {
                    viewHolder2.spsLl25.setVisibility(View.VISIBLE);
                    viewHolder2.zhudanVictory25Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder2.zhudanSps25Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                }
            }

            int time = zhuDanDetailBean.getData().getMatchList().get(position).getMatchTime();
            long time1 = (long) time * 1000;
            viewHolder2.zhudanStoptimeTv.setText(DateUtils.formatDateAndTime(time1));
        } else {//十条以上时
            ViewHolder3 viewHolder3 = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.zhudandetail_jcbiger_item, null);
                viewHolder3 = new ViewHolder3(convertView);
                convertView.setTag(viewHolder3);
            } else {
                viewHolder3 = (ViewHolder3) convertView.getTag();
            }
            viewHolder3.zhudanWeekTv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getWeekNo());
            viewHolder3.zhudanLeagueTv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getLeagueName());
            viewHolder3.zhudanHostnameTv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getHostName());
            viewHolder3.zhudanVisitTv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getVisitName());
            for (int i = 0; i < zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().size(); i++) {
                if (i == 0) {
                    viewHolder3.spsLl1.setVisibility(View.VISIBLE);
                    viewHolder3.zhudanVictory1Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder3.zhudanSps1Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                } else if (i == 1) {
                    viewHolder3.spsLl2.setVisibility(View.VISIBLE);
                    viewHolder3.zhudanVictory2Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder3.zhudanSps2Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                } else if (i == 2) {
                    viewHolder3.spsLl3.setVisibility(View.VISIBLE);
                    viewHolder3.zhudanVictory3Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder3.zhudanSps3Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                } else if (i == 3) {
                    viewHolder3.spsLl4.setVisibility(View.VISIBLE);
                    viewHolder3.zhudanVictory4Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder3.zhudanSps4Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                } else if (i == 4) {
                    viewHolder3.spsLl5.setVisibility(View.VISIBLE);
                    viewHolder3.zhudanVictory5Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder3.zhudanSps5Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                }
                if (i == 5) {
                    viewHolder3.spsLl21.setVisibility(View.VISIBLE);
                    viewHolder3.zhudanVictory1Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder3.zhudanSps1Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                } else if (i == 6) {
                    viewHolder3.spsLl22.setVisibility(View.VISIBLE);
                    viewHolder3.zhudanVictory2Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder3.zhudanSps2Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                } else if (i == 7) {
                    viewHolder3.spsLl23.setVisibility(View.VISIBLE);
                    viewHolder3.zhudanVictory23Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder3.zhudanSps23Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                } else if (i == 8) {
                    viewHolder3.spsLl24.setVisibility(View.VISIBLE);
                    viewHolder3.zhudanVictory24Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder3.zhudanSps24Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                } else if (i == 9) {
                    viewHolder3.spsLl25.setVisibility(View.VISIBLE);
                    viewHolder3.zhudanVictory25Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder3.zhudanSps25Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                } else if (i == 10) {
                    viewHolder3.spsLl31.setVisibility(View.VISIBLE);
                    viewHolder3.zhudanVictory31Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder3.zhudanSps31Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                } else if (i == 11) {
                    viewHolder3.spsLl32.setVisibility(View.VISIBLE);
                    viewHolder3.zhudanVictory32Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder3.zhudanSps32Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                } else if (i == 12) {
                    viewHolder3.spsLl33.setVisibility(View.VISIBLE);
                    viewHolder3.zhudanVictory33Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().get(i));
                    viewHolder3.zhudanSps33Tv.setText(zhuDanDetailBean.getData().getMatchList().get(position).getStakeSp().get(i));
                }
            }

            int time = zhuDanDetailBean.getData().getMatchList().get(position).getMatchTime();
            long time1 = (long) time * 1000;
            viewHolder3.zhudanStoptimeTv.setText(DateUtils.formatDateAndTime(time1));
        }

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.zhudan_week_tv)
        TextView zhudanWeekTv;
        @BindView(R.id.zhudan_league_tv)
        TextView zhudanLeagueTv;
        @BindView(R.id.zhudan_stoptime_tv)
        TextView zhudanStoptimeTv;
        @BindView(R.id.zhudan_hostname_tv)
        TextView zhudanHostnameTv;
        @BindView(R.id.zhudan_vs_tv)
        TextView zhudanVsTv;
        @BindView(R.id.zhudan_visit_tv)
        TextView zhudanVisitTv;
        @BindView(R.id.zhudan_victory1_tv)
        TextView zhudanVictory1Tv;
        @BindView(R.id.zhudan_sps1_tv)
        TextView zhudanSps1Tv;
        @BindView(R.id.sps_ll1)
        LinearLayout spsLl1;
        @BindView(R.id.zhudan_victory2_tv)
        TextView zhudanVictory2Tv;
        @BindView(R.id.zhudan_sps2_tv)
        TextView zhudanSps2Tv;
        @BindView(R.id.sps_ll2)
        LinearLayout spsLl2;
        @BindView(R.id.zhudan_victory3_tv)
        TextView zhudanVictory3Tv;
        @BindView(R.id.zhudan_sps3_tv)
        TextView zhudanSps3Tv;
        @BindView(R.id.sps_ll3)
        LinearLayout spsLl3;
        @BindView(R.id.zhudan_victory4_tv)
        TextView zhudanVictory4Tv;
        @BindView(R.id.zhudan_sps4_tv)
        TextView zhudanSps4Tv;
        @BindView(R.id.sps_ll4)
        LinearLayout spsLl4;
        @BindView(R.id.zhudan_victory5_tv)
        TextView zhudanVictory5Tv;
        @BindView(R.id.zhudan_sps5_tv)
        TextView zhudanSps5Tv;
        @BindView(R.id.sps_ll5)
        LinearLayout spsLl5;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder2 {
        @BindView(R.id.zhudan_week_tv)
        TextView zhudanWeekTv;
        @BindView(R.id.zhudan_league_tv)
        TextView zhudanLeagueTv;
        @BindView(R.id.zhudan_stoptime_tv)
        TextView zhudanStoptimeTv;
        @BindView(R.id.zhudan_hostname_tv)
        TextView zhudanHostnameTv;
        @BindView(R.id.zhudan_vs_tv)
        TextView zhudanVsTv;
        @BindView(R.id.zhudan_visit_tv)
        TextView zhudanVisitTv;
        @BindView(R.id.zhudan_victory1_tv)
        TextView zhudanVictory1Tv;
        @BindView(R.id.zhudan_sps1_tv)
        TextView zhudanSps1Tv;
        @BindView(R.id.sps_ll1)
        LinearLayout spsLl1;
        @BindView(R.id.zhudan_victory2_tv)
        TextView zhudanVictory2Tv;
        @BindView(R.id.zhudan_sps2_tv)
        TextView zhudanSps2Tv;
        @BindView(R.id.sps_ll2)
        LinearLayout spsLl2;
        @BindView(R.id.zhudan_victory3_tv)
        TextView zhudanVictory3Tv;
        @BindView(R.id.zhudan_sps3_tv)
        TextView zhudanSps3Tv;
        @BindView(R.id.sps_ll3)
        LinearLayout spsLl3;
        @BindView(R.id.zhudan_victory4_tv)
        TextView zhudanVictory4Tv;
        @BindView(R.id.zhudan_sps4_tv)
        TextView zhudanSps4Tv;
        @BindView(R.id.sps_ll4)
        LinearLayout spsLl4;
        @BindView(R.id.zhudan_victory5_tv)
        TextView zhudanVictory5Tv;
        @BindView(R.id.zhudan_sps5_tv)
        TextView zhudanSps5Tv;
        @BindView(R.id.sps_ll5)
        LinearLayout spsLl5;
        @BindView(R.id.zhudan_victory21_tv)
        TextView zhudanVictory21Tv;
        @BindView(R.id.zhudan_sps21_tv)
        TextView zhudanSps21Tv;
        @BindView(R.id.sps_ll21)
        LinearLayout spsLl21;
        @BindView(R.id.zhudan_victory22_tv)
        TextView zhudanVictory22Tv;
        @BindView(R.id.zhudan_sps22_tv)
        TextView zhudanSps22Tv;
        @BindView(R.id.sps_ll22)
        LinearLayout spsLl22;
        @BindView(R.id.zhudan_victory23_tv)
        TextView zhudanVictory23Tv;
        @BindView(R.id.zhudan_sps23_tv)
        TextView zhudanSps23Tv;
        @BindView(R.id.sps_ll23)
        LinearLayout spsLl23;
        @BindView(R.id.zhudan_victory24_tv)
        TextView zhudanVictory24Tv;
        @BindView(R.id.zhudan_sps24_tv)
        TextView zhudanSps24Tv;
        @BindView(R.id.sps_ll24)
        LinearLayout spsLl24;
        @BindView(R.id.zhudan_victory25_tv)
        TextView zhudanVictory25Tv;
        @BindView(R.id.zhudan_sps25_tv)
        TextView zhudanSps25Tv;
        @BindView(R.id.sps_ll25)
        LinearLayout spsLl25;

        ViewHolder2(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder3 {
        @BindView(R.id.zhudan_week_tv)
        TextView zhudanWeekTv;
        @BindView(R.id.zhudan_league_tv)
        TextView zhudanLeagueTv;
        @BindView(R.id.zhudan_stoptime_tv)
        TextView zhudanStoptimeTv;
        @BindView(R.id.zhudan_hostname_tv)
        TextView zhudanHostnameTv;
        @BindView(R.id.zhudan_vs_tv)
        TextView zhudanVsTv;
        @BindView(R.id.zhudan_visit_tv)
        TextView zhudanVisitTv;
        @BindView(R.id.zhudan_victory1_tv)
        TextView zhudanVictory1Tv;
        @BindView(R.id.zhudan_sps1_tv)
        TextView zhudanSps1Tv;
        @BindView(R.id.sps_ll1)
        LinearLayout spsLl1;
        @BindView(R.id.zhudan_victory2_tv)
        TextView zhudanVictory2Tv;
        @BindView(R.id.zhudan_sps2_tv)
        TextView zhudanSps2Tv;
        @BindView(R.id.sps_ll2)
        LinearLayout spsLl2;
        @BindView(R.id.zhudan_victory3_tv)
        TextView zhudanVictory3Tv;
        @BindView(R.id.zhudan_sps3_tv)
        TextView zhudanSps3Tv;
        @BindView(R.id.sps_ll3)
        LinearLayout spsLl3;
        @BindView(R.id.zhudan_victory4_tv)
        TextView zhudanVictory4Tv;
        @BindView(R.id.zhudan_sps4_tv)
        TextView zhudanSps4Tv;
        @BindView(R.id.sps_ll4)
        LinearLayout spsLl4;
        @BindView(R.id.zhudan_victory5_tv)
        TextView zhudanVictory5Tv;
        @BindView(R.id.zhudan_sps5_tv)
        TextView zhudanSps5Tv;
        @BindView(R.id.sps_ll5)
        LinearLayout spsLl5;
        @BindView(R.id.zhudan_victory21_tv)
        TextView zhudanVictory21Tv;
        @BindView(R.id.zhudan_sps21_tv)
        TextView zhudanSps21Tv;
        @BindView(R.id.sps_ll21)
        LinearLayout spsLl21;
        @BindView(R.id.zhudan_victory22_tv)
        TextView zhudanVictory22Tv;
        @BindView(R.id.zhudan_sps22_tv)
        TextView zhudanSps22Tv;
        @BindView(R.id.sps_ll22)
        LinearLayout spsLl22;
        @BindView(R.id.zhudan_victory23_tv)
        TextView zhudanVictory23Tv;
        @BindView(R.id.zhudan_sps23_tv)
        TextView zhudanSps23Tv;
        @BindView(R.id.sps_ll23)
        LinearLayout spsLl23;
        @BindView(R.id.zhudan_victory24_tv)
        TextView zhudanVictory24Tv;
        @BindView(R.id.zhudan_sps24_tv)
        TextView zhudanSps24Tv;
        @BindView(R.id.sps_ll24)
        LinearLayout spsLl24;
        @BindView(R.id.zhudan_victory25_tv)
        TextView zhudanVictory25Tv;
        @BindView(R.id.zhudan_sps25_tv)
        TextView zhudanSps25Tv;
        @BindView(R.id.sps_ll25)
        LinearLayout spsLl25;
        @BindView(R.id.zhudan_victory31_tv)
        TextView zhudanVictory31Tv;
        @BindView(R.id.zhudan_sps31_tv)
        TextView zhudanSps31Tv;
        @BindView(R.id.sps_ll31)
        LinearLayout spsLl31;
        @BindView(R.id.zhudan_victory32_tv)
        TextView zhudanVictory32Tv;
        @BindView(R.id.zhudan_sps32_tv)
        TextView zhudanSps32Tv;
        @BindView(R.id.sps_ll32)
        LinearLayout spsLl32;
        @BindView(R.id.zhudan_victory33_tv)
        TextView zhudanVictory33Tv;
        @BindView(R.id.zhudan_sps33_tv)
        TextView zhudanSps33Tv;
        @BindView(R.id.sps_ll33)
        LinearLayout spsLl33;
        @BindView(R.id.zhudan_victory34_tv)
        TextView zhudanVictory34Tv;
        @BindView(R.id.zhudan_sps34_tv)
        TextView zhudanSps34Tv;
        @BindView(R.id.sps_ll34)
        LinearLayout spsLl34;
        @BindView(R.id.zhudan_victory35_tv)
        TextView zhudanVictory35Tv;
        @BindView(R.id.zhudan_sps35_tv)
        TextView zhudanSps35Tv;
        @BindView(R.id.sps_ll35)
        LinearLayout spsLl35;

        ViewHolder3(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
