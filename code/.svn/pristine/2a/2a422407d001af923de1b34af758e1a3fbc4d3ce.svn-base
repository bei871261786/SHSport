package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.ZhudanDetailBean;
import shlottery.gov.cn.lotterycenter.utils.DateUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-24-0024 17:31
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class JcZDzqDetailAdapter extends BaseAdapter {
    private ZhudanDetailBean zhuDanDetailBean;
    private Context          context;
    private int              type;//0,代表足球;1代表篮球
    //是否篮球  篮球主客队位置调换 客队在前

    public JcZDzqDetailAdapter(ZhudanDetailBean zhuDanDetailBean, Context context, int type) {
        this.zhuDanDetailBean = zhuDanDetailBean;
        this.context = context;
        this.type = type;
        //        intTypeCount();
    }


    @Override
    public int getCount() {
        if (zhuDanDetailBean == null) {
            return 0;
        }
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

  /*  @Override
    public int getViewTypeCount() {
        Logger.e(typeCount + "typeCount");
        return typeCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().size() <= 5) {
            Logger.e(typeCount + "typeCount");
            return 0;
        } else if (zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().size() > 5 &&
        zhuDanDetailBean.getData().getMatchList().get(position).getStakeOption().size() <= 10) {
            return 1;
        } else {
            return 2;
        }
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            //篮球足球使用不同布局
            convertView = LayoutInflater.from(context).inflate(type == 0 ? R.layout.zhudandetail_jczuqiu_item : R
                    .layout.zhudandetail_jczuqiu_item_basketball, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ZhudanDetailBean.DataBean.MatchListBean matchListBean = zhuDanDetailBean.getData().getMatchList().get(position);
        Resources res = context.getResources();
        viewHolder.zhudanWeekTv.setText(matchListBean.getWeekNo());
        viewHolder.zhudanLeagueTv.setText(matchListBean.getLeagueName());
        if (matchListBean.getHand() == 0) {//如果有让球显示让球数
            //篮球主队队名后显示 主
            viewHolder.zhudanHostnameTv.setText(type == 1 ? matchListBean.getHostName() + "(主)" :
                    matchListBean.getHostName());
        } else {
            String hostName = matchListBean.getHostName();
            float hand = matchListBean.getHand();
            if (type == 0) {
                //足球
                int rangqiu = (int) hand;
                String score = "";
                if (rangqiu > 0) {
                    score = "(" + "+" + rangqiu + ")";
                    viewHolder.zhudanHostnameTv.setText(hostName + score);
                    TextUtils.setStrColor(viewHolder.zhudanHostnameTv, hostName + score, score, res.getColor(R.color
                            .select_red));
                } else {
                    score = "(" + rangqiu + ")";
                    viewHolder.zhudanHostnameTv.setText(hostName + score);
                    TextUtils.setStrColor(viewHolder.zhudanHostnameTv, hostName + score, score, res.getColor(R.color
                            .standard_textcolor_c9));
                }
            } else {
                //篮球
                String score = "";
                if (hand > 0) {
                    //是否竞篮大小分
                    boolean isJldx = "jldx".equals(matchListBean.getLotId());
                    score = isJldx ? "" : "(+" + hand + ")";
                    hostName = hostName + "(主)";
                    viewHolder.zhudanHostnameTv.setText(hostName + score);
                    TextUtils.setStrColor(viewHolder.zhudanHostnameTv, hostName + score, score,res.getColor(R.color.select_red));
                    //竞篮大小分 把比分放中间替换 vs
                    if (isJldx) {
                        viewHolder.zhudanVsTv.setText(hand + "");
                        viewHolder.zhudanVsTv.setTextColor(res.getColor(R.color.select_red));
                    }
                } else {
                    score = "(" + hand + ")";
                    hostName = hostName + "(主)";
                    viewHolder.zhudanHostnameTv.setText(hostName + score);
                    TextUtils.setStrColor(viewHolder.zhudanHostnameTv, hostName + score, score, res.getColor(R.color
                            .standard_textcolor_c9));
                }
            }
        }

        if (matchListBean.getDan() == 0) {//如果有胆需显示
            viewHolder.zhudanDanTv.setVisibility(View.GONE);
        } else {
            viewHolder.zhudanDanTv.setVisibility(View.VISIBLE);
        }
        viewHolder.zhudanVisitTv.setText(matchListBean.getVisitName());

        //根据注单中的数据长度确定item中需要显示的行数
        if (matchListBean.getStakeOption().size() > 0 && matchListBean.getStakeOption()
                .size() <= 3) {
            viewHolder.zhudanHangLl11.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl21.setVisibility(View.GONE);
            viewHolder.zhudanHangLl31.setVisibility(View.GONE);
            viewHolder.zhudanHangLl41.setVisibility(View.GONE);
            viewHolder.zhudanHangLl51.setVisibility(View.GONE);
            viewHolder.zhudanHangLl61.setVisibility(View.GONE);
            viewHolder.zhudanHangLl71.setVisibility(View.GONE);
            viewHolder.zhudanHangLl81.setVisibility(View.GONE);
            viewHolder.zhudanHangLl91.setVisibility(View.GONE);
            viewHolder.zhudanHangLl101.setVisibility(View.GONE);
            viewHolder.zhudanHangLl111.setVisibility(View.GONE);
        } else if (matchListBean.getStakeOption().size() > 3 && matchListBean
                .getStakeOption().size() <= 6) {
            viewHolder.zhudanHangLl11.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl21.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl31.setVisibility(View.GONE);
            viewHolder.zhudanHangLl41.setVisibility(View.GONE);
            viewHolder.zhudanHangLl51.setVisibility(View.GONE);
            viewHolder.zhudanHangLl61.setVisibility(View.GONE);
            viewHolder.zhudanHangLl71.setVisibility(View.GONE);
            viewHolder.zhudanHangLl81.setVisibility(View.GONE);
            viewHolder.zhudanHangLl91.setVisibility(View.GONE);
            viewHolder.zhudanHangLl101.setVisibility(View.GONE);
            viewHolder.zhudanHangLl111.setVisibility(View.GONE);
        } else if (matchListBean.getStakeOption().size() > 6 && matchListBean
                .getStakeOption().size() <= 9) {
            viewHolder.zhudanHangLl11.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl21.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl31.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl41.setVisibility(View.GONE);
            viewHolder.zhudanHangLl51.setVisibility(View.GONE);
            viewHolder.zhudanHangLl61.setVisibility(View.GONE);
            viewHolder.zhudanHangLl71.setVisibility(View.GONE);
            viewHolder.zhudanHangLl81.setVisibility(View.GONE);
            viewHolder.zhudanHangLl91.setVisibility(View.GONE);
            viewHolder.zhudanHangLl101.setVisibility(View.GONE);
            viewHolder.zhudanHangLl111.setVisibility(View.GONE);
        } else if (matchListBean.getStakeOption().size() > 9 && matchListBean
                .getStakeOption().size() <= 12) {
            viewHolder.zhudanHangLl11.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl21.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl31.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl41.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl51.setVisibility(View.GONE);
            viewHolder.zhudanHangLl61.setVisibility(View.GONE);
            viewHolder.zhudanHangLl71.setVisibility(View.GONE);
            viewHolder.zhudanHangLl81.setVisibility(View.GONE);
            viewHolder.zhudanHangLl91.setVisibility(View.GONE);
            viewHolder.zhudanHangLl101.setVisibility(View.GONE);
            viewHolder.zhudanHangLl111.setVisibility(View.GONE);
        } else if (matchListBean.getStakeOption().size() > 12 && matchListBean
                .getStakeOption().size() <= 15) {
            viewHolder.zhudanHangLl11.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl21.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl31.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl41.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl51.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl61.setVisibility(View.GONE);
            viewHolder.zhudanHangLl71.setVisibility(View.GONE);
            viewHolder.zhudanHangLl81.setVisibility(View.GONE);
            viewHolder.zhudanHangLl91.setVisibility(View.GONE);
            viewHolder.zhudanHangLl101.setVisibility(View.GONE);
            viewHolder.zhudanHangLl111.setVisibility(View.GONE);
        } else if (matchListBean.getStakeOption().size() > 15 && matchListBean
                .getStakeOption().size() <= 18) {
            viewHolder.zhudanHangLl11.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl21.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl31.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl41.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl51.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl61.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl71.setVisibility(View.GONE);
            viewHolder.zhudanHangLl81.setVisibility(View.GONE);
            viewHolder.zhudanHangLl91.setVisibility(View.GONE);
            viewHolder.zhudanHangLl101.setVisibility(View.GONE);
            viewHolder.zhudanHangLl111.setVisibility(View.GONE);
        } else if (matchListBean.getStakeOption().size() > 18 && matchListBean
                .getStakeOption().size() <= 21) {
            viewHolder.zhudanHangLl11.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl21.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl31.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl41.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl51.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl61.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl71.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl81.setVisibility(View.GONE);
            viewHolder.zhudanHangLl91.setVisibility(View.GONE);
            viewHolder.zhudanHangLl101.setVisibility(View.GONE);
            viewHolder.zhudanHangLl111.setVisibility(View.GONE);
        } else if (matchListBean.getStakeOption().size() > 21 && matchListBean
                .getStakeOption().size() <= 24) {
            viewHolder.zhudanHangLl11.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl21.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl31.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl41.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl51.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl61.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl71.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl81.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl91.setVisibility(View.GONE);
            viewHolder.zhudanHangLl101.setVisibility(View.GONE);
            viewHolder.zhudanHangLl111.setVisibility(View.GONE);
        } else if (matchListBean.getStakeOption().size() > 24 && matchListBean
                .getStakeOption().size() <= 27) {
            viewHolder.zhudanHangLl11.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl21.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl31.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl41.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl51.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl61.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl71.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl81.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl91.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl101.setVisibility(View.GONE);
            viewHolder.zhudanHangLl111.setVisibility(View.GONE);
        } else if (matchListBean.getStakeOption().size() > 27 && matchListBean
                .getStakeOption().size() <= 30) {
            viewHolder.zhudanHangLl11.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl21.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl31.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl41.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl51.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl61.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl71.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl81.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl91.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl101.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl111.setVisibility(View.GONE);
        } else if (matchListBean.getStakeOption().size() > 30 && matchListBean
                .getStakeOption().size() <= 33) {
            viewHolder.zhudanHangLl11.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl21.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl31.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl41.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl51.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl61.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl71.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl81.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl91.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl101.setVisibility(View.VISIBLE);
            viewHolder.zhudanHangLl111.setVisibility(View.VISIBLE);
        }
        initview(viewHolder, position);
        for (int i = 0; i < matchListBean.getStakeOption().size(); i++) {
            if (i == 0) {
                viewHolder.spsLl11.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory11Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps11Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 1) {
                viewHolder.spsLl12.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory12Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps12Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 2) {
                viewHolder.spsLl13.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory13Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps13Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 3) {
                viewHolder.spsLl21.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory21Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps21Tv.setText(matchListBean.getStakeSp().get(i));
            }
            if (i == 4) {
                viewHolder.spsLl22.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory22Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps22Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 5) {
                viewHolder.spsLl23.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory23Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps23Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 6) {
                viewHolder.spsLl31.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory31Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps31Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 7) {
                viewHolder.spsLl32.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory32Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps32Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 8) {
                viewHolder.spsLl33.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory33Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps33Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 9) {
                viewHolder.spsLl41.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory41Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps41Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 10) {
                viewHolder.spsLl42.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory42Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps42Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 11) {
                viewHolder.spsLl43.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory43Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps43Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 12) {
                viewHolder.spsLl51.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory51Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps51Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 13) {
                viewHolder.spsLl52.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory52Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps52Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 14) {
                viewHolder.spsLl53.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory53Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps53Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 15) {
                viewHolder.spsLl61.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory61Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps61Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 16) {
                viewHolder.spsLl62.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory62Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps62Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 17) {
                viewHolder.spsLl63.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory63Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps63Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 18) {
                viewHolder.spsLl71.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory71Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps71Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 19) {
                viewHolder.spsLl72.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory72Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps72Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 20) {
                viewHolder.spsLl73.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory73Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps73Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 21) {
                viewHolder.spsLl81.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory81Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps81Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 22) {
                viewHolder.spsLl82.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory82Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps82Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 23) {
                viewHolder.spsLl83.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory83Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps83Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 24) {
                viewHolder.spsLl91.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory91Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps91Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 25) {
                viewHolder.spsLl92.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory92Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps92Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 26) {
                viewHolder.spsLl93.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory93Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps93Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 27) {
                viewHolder.spsLl101.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory101Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps101Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 28) {
                viewHolder.spsLl102.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory102Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps102Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 29) {
                viewHolder.spsLl103.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory103Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps103Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 30) {
                viewHolder.spsLl101.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory111Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps111Tv.setText(matchListBean.getStakeSp().get(i));
            } else if (i == 31) {
                viewHolder.spsLl112.setVisibility(View.VISIBLE);
                viewHolder.zhudanVictory112Tv.setText(matchListBean.getStakeOption().get(i));
                viewHolder.zhudanSps112Tv.setText(matchListBean.getStakeSp().get(i));
            }


        }
        int time = matchListBean.getMatchTime();
        long time1 = (long) time * 1000;
        viewHolder.zhudanStoptimeTv.setText(DateUtils.formatDateAndTime(time1));


        return convertView;
    }

    private void initview(ViewHolder viewHolder, int position) {

      /*  viewHolder.zhudanVictory11Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps11Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory12Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps12Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory13Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps13Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory21Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps21Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory22Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps22Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory23Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps23Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory31Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps31Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory32Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps32Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory33Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps33Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory41Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps41Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory42Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps42Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory43Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps43Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory51Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps51Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory52Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps52Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory53Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps53Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory61Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps61Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory62Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps62Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory63Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps63Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory71Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps71Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory72Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps72Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory73Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps73Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory81Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps81Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory82Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps82Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory83Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps83Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory91Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps91Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory92Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps92Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory93Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps93Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory101Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps101Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory102Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps102Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory103Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps103Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory111Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps111Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanVictory112Tv.setVisibility(View.INVISIBLE);
        viewHolder.zhudanSps112Tv.setVisibility(View.INVISIBLE);*/

        viewHolder.spsLl11.setVisibility(View.INVISIBLE);
        viewHolder.spsLl12.setVisibility(View.INVISIBLE);
        viewHolder.spsLl13.setVisibility(View.INVISIBLE);
        viewHolder.spsLl21.setVisibility(View.INVISIBLE);
        viewHolder.spsLl22.setVisibility(View.INVISIBLE);
        viewHolder.spsLl23.setVisibility(View.INVISIBLE);
        viewHolder.spsLl31.setVisibility(View.INVISIBLE);
        viewHolder.spsLl32.setVisibility(View.INVISIBLE);
        viewHolder.spsLl33.setVisibility(View.INVISIBLE);
        viewHolder.spsLl41.setVisibility(View.INVISIBLE);
        viewHolder.spsLl42.setVisibility(View.INVISIBLE);
        viewHolder.spsLl43.setVisibility(View.INVISIBLE);
        viewHolder.spsLl51.setVisibility(View.INVISIBLE);
        viewHolder.spsLl52.setVisibility(View.INVISIBLE);
        viewHolder.spsLl53.setVisibility(View.INVISIBLE);
        viewHolder.spsLl61.setVisibility(View.INVISIBLE);
        viewHolder.spsLl62.setVisibility(View.INVISIBLE);
        viewHolder.spsLl63.setVisibility(View.INVISIBLE);
        viewHolder.spsLl71.setVisibility(View.INVISIBLE);
        viewHolder.spsLl72.setVisibility(View.INVISIBLE);
        viewHolder.spsLl73.setVisibility(View.INVISIBLE);
        viewHolder.spsLl81.setVisibility(View.INVISIBLE);
        viewHolder.spsLl82.setVisibility(View.INVISIBLE);
        viewHolder.spsLl83.setVisibility(View.INVISIBLE);
        viewHolder.spsLl91.setVisibility(View.INVISIBLE);
        viewHolder.spsLl92.setVisibility(View.INVISIBLE);
        viewHolder.spsLl93.setVisibility(View.INVISIBLE);
        viewHolder.spsLl101.setVisibility(View.INVISIBLE);
        viewHolder.spsLl102.setVisibility(View.INVISIBLE);
        viewHolder.spsLl103.setVisibility(View.INVISIBLE);
        viewHolder.spsLl101.setVisibility(View.INVISIBLE);
        viewHolder.spsLl112.setVisibility(View.INVISIBLE);
    }


    static class ViewHolder {
        @BindView(R.id.zhudan_week_tv)
        TextView     zhudanWeekTv;
        @BindView(R.id.zhudan_league_tv)
        TextView     zhudanLeagueTv;
        @BindView(R.id.zhudan_stoptime_tv)
        TextView     zhudanStoptimeTv;
        @BindView(R.id.zhudan_hostname_tv)
        TextView     zhudanHostnameTv;
        @BindView(R.id.zhudan_vs_tv)
        TextView     zhudanVsTv;
        @BindView(R.id.zhudan_visit_tv)
        TextView     zhudanVisitTv;
        @BindView(R.id.zhudan_Dan_tv)
        TextView     zhudanDanTv;
        @BindView(R.id.zhudan_victory11_tv)
        TextView     zhudanVictory11Tv;
        @BindView(R.id.zhudan_sps11_tv)
        TextView     zhudanSps11Tv;
        @BindView(R.id.sps_ll11)
        LinearLayout spsLl11;
        @BindView(R.id.zhudan_victory12_tv)
        TextView     zhudanVictory12Tv;
        @BindView(R.id.zhudan_sps12_tv)
        TextView     zhudanSps12Tv;
        @BindView(R.id.sps_ll12)
        LinearLayout spsLl12;
        @BindView(R.id.zhudan_victory13_tv)
        TextView     zhudanVictory13Tv;
        @BindView(R.id.zhudan_sps13_tv)
        TextView     zhudanSps13Tv;
        @BindView(R.id.sps_ll13)
        LinearLayout spsLl13;
        @BindView(R.id.zhudan_hang_ll11)
        LinearLayout zhudanHangLl11;
        @BindView(R.id.zhudan_victory21_tv)
        TextView     zhudanVictory21Tv;
        @BindView(R.id.zhudan_sps21_tv)
        TextView     zhudanSps21Tv;
        @BindView(R.id.sps_ll21)
        LinearLayout spsLl21;
        @BindView(R.id.zhudan_victory22_tv)
        TextView     zhudanVictory22Tv;
        @BindView(R.id.zhudan_sps22_tv)
        TextView     zhudanSps22Tv;
        @BindView(R.id.sps_ll22)
        LinearLayout spsLl22;
        @BindView(R.id.zhudan_victory23_tv)
        TextView     zhudanVictory23Tv;
        @BindView(R.id.zhudan_sps23_tv)
        TextView     zhudanSps23Tv;
        @BindView(R.id.sps_ll23)
        LinearLayout spsLl23;
        @BindView(R.id.zhudan_hang_ll21)
        LinearLayout zhudanHangLl21;
        @BindView(R.id.zhudan_victory31_tv)
        TextView     zhudanVictory31Tv;
        @BindView(R.id.zhudan_sps31_tv)
        TextView     zhudanSps31Tv;
        @BindView(R.id.sps_ll31)
        LinearLayout spsLl31;
        @BindView(R.id.zhudan_victory32_tv)
        TextView     zhudanVictory32Tv;
        @BindView(R.id.zhudan_sps32_tv)
        TextView     zhudanSps32Tv;
        @BindView(R.id.sps_ll32)
        LinearLayout spsLl32;
        @BindView(R.id.zhudan_victory33_tv)
        TextView     zhudanVictory33Tv;
        @BindView(R.id.zhudan_sps33_tv)
        TextView     zhudanSps33Tv;
        @BindView(R.id.sps_ll33)
        LinearLayout spsLl33;
        @BindView(R.id.zhudan_hang_ll31)
        LinearLayout zhudanHangLl31;
        @BindView(R.id.zhudan_victory41_tv)
        TextView     zhudanVictory41Tv;
        @BindView(R.id.zhudan_sps41_tv)
        TextView     zhudanSps41Tv;
        @BindView(R.id.sps_ll41)
        LinearLayout spsLl41;
        @BindView(R.id.zhudan_victory42_tv)
        TextView     zhudanVictory42Tv;
        @BindView(R.id.zhudan_sps42_tv)
        TextView     zhudanSps42Tv;
        @BindView(R.id.sps_ll42)
        LinearLayout spsLl42;
        @BindView(R.id.zhudan_victory43_tv)
        TextView     zhudanVictory43Tv;
        @BindView(R.id.zhudan_sps43_tv)
        TextView     zhudanSps43Tv;
        @BindView(R.id.sps_ll43)
        LinearLayout spsLl43;
        @BindView(R.id.zhudan_hang_ll41)
        LinearLayout zhudanHangLl41;
        @BindView(R.id.zhudan_victory51_tv)
        TextView     zhudanVictory51Tv;
        @BindView(R.id.zhudan_sps51_tv)
        TextView     zhudanSps51Tv;
        @BindView(R.id.sps_ll51)
        LinearLayout spsLl51;
        @BindView(R.id.zhudan_victory52_tv)
        TextView     zhudanVictory52Tv;
        @BindView(R.id.zhudan_sps52_tv)
        TextView     zhudanSps52Tv;
        @BindView(R.id.sps_ll52)
        LinearLayout spsLl52;
        @BindView(R.id.zhudan_victory53_tv)
        TextView     zhudanVictory53Tv;
        @BindView(R.id.zhudan_sps53_tv)
        TextView     zhudanSps53Tv;
        @BindView(R.id.sps_ll53)
        LinearLayout spsLl53;
        @BindView(R.id.zhudan_hang_ll51)
        LinearLayout zhudanHangLl51;
        @BindView(R.id.zhudan_victory61_tv)
        TextView     zhudanVictory61Tv;
        @BindView(R.id.zhudan_sps61_tv)
        TextView     zhudanSps61Tv;
        @BindView(R.id.sps_ll61)
        LinearLayout spsLl61;
        @BindView(R.id.zhudan_victory62_tv)
        TextView     zhudanVictory62Tv;
        @BindView(R.id.zhudan_sps62_tv)
        TextView     zhudanSps62Tv;
        @BindView(R.id.sps_ll62)
        LinearLayout spsLl62;
        @BindView(R.id.zhudan_victory63_tv)
        TextView     zhudanVictory63Tv;
        @BindView(R.id.zhudan_sps63_tv)
        TextView     zhudanSps63Tv;
        @BindView(R.id.sps_ll63)
        LinearLayout spsLl63;
        @BindView(R.id.zhudan_hang_ll61)
        LinearLayout zhudanHangLl61;
        @BindView(R.id.zhudan_victory71_tv)
        TextView     zhudanVictory71Tv;
        @BindView(R.id.zhudan_sps71_tv)
        TextView     zhudanSps71Tv;
        @BindView(R.id.sps_ll71)
        LinearLayout spsLl71;
        @BindView(R.id.zhudan_victory72_tv)
        TextView     zhudanVictory72Tv;
        @BindView(R.id.zhudan_sps72_tv)
        TextView     zhudanSps72Tv;
        @BindView(R.id.sps_ll72)
        LinearLayout spsLl72;
        @BindView(R.id.zhudan_victory73_tv)
        TextView     zhudanVictory73Tv;
        @BindView(R.id.zhudan_sps73_tv)
        TextView     zhudanSps73Tv;
        @BindView(R.id.sps_ll73)
        LinearLayout spsLl73;
        @BindView(R.id.zhudan_hang_ll71)
        LinearLayout zhudanHangLl71;
        @BindView(R.id.zhudan_victory81_tv)
        TextView     zhudanVictory81Tv;
        @BindView(R.id.zhudan_sps81_tv)
        TextView     zhudanSps81Tv;
        @BindView(R.id.sps_ll81)
        LinearLayout spsLl81;
        @BindView(R.id.zhudan_victory82_tv)
        TextView     zhudanVictory82Tv;
        @BindView(R.id.zhudan_sps82_tv)
        TextView     zhudanSps82Tv;
        @BindView(R.id.sps_ll82)
        LinearLayout spsLl82;
        @BindView(R.id.zhudan_victory83_tv)
        TextView     zhudanVictory83Tv;
        @BindView(R.id.zhudan_sps83_tv)
        TextView     zhudanSps83Tv;
        @BindView(R.id.sps_ll83)
        LinearLayout spsLl83;
        @BindView(R.id.zhudan_hang_ll81)
        LinearLayout zhudanHangLl81;
        @BindView(R.id.zhudan_victory91_tv)
        TextView     zhudanVictory91Tv;
        @BindView(R.id.zhudan_sps91_tv)
        TextView     zhudanSps91Tv;
        @BindView(R.id.sps_ll91)
        LinearLayout spsLl91;
        @BindView(R.id.zhudan_victory92_tv)
        TextView     zhudanVictory92Tv;
        @BindView(R.id.zhudan_sps92_tv)
        TextView     zhudanSps92Tv;
        @BindView(R.id.sps_ll92)
        LinearLayout spsLl92;
        @BindView(R.id.zhudan_victory93_tv)
        TextView     zhudanVictory93Tv;
        @BindView(R.id.zhudan_sps93_tv)
        TextView     zhudanSps93Tv;
        @BindView(R.id.sps_ll93)
        LinearLayout spsLl93;
        @BindView(R.id.zhudan_hang_ll91)
        LinearLayout zhudanHangLl91;
        @BindView(R.id.zhudan_victory101_tv)
        TextView     zhudanVictory101Tv;
        @BindView(R.id.zhudan_sps101_tv)
        TextView     zhudanSps101Tv;
        @BindView(R.id.sps_ll101)
        LinearLayout spsLl101;
        @BindView(R.id.zhudan_victory102_tv)
        TextView     zhudanVictory102Tv;
        @BindView(R.id.zhudan_sps102_tv)
        TextView     zhudanSps102Tv;
        @BindView(R.id.sps_ll102)
        LinearLayout spsLl102;
        @BindView(R.id.zhudan_victory103_tv)
        TextView     zhudanVictory103Tv;
        @BindView(R.id.zhudan_sps103_tv)
        TextView     zhudanSps103Tv;
        @BindView(R.id.sps_ll103)
        LinearLayout spsLl103;
        @BindView(R.id.zhudan_hang_ll101)
        LinearLayout zhudanHangLl101;
        @BindView(R.id.zhudan_victory111_tv)
        TextView     zhudanVictory111Tv;
        @BindView(R.id.zhudan_sps111_tv)
        TextView     zhudanSps111Tv;
        @BindView(R.id.sps_ll111)
        LinearLayout spsLl111;
        @BindView(R.id.zhudan_victory112_tv)
        TextView     zhudanVictory112Tv;
        @BindView(R.id.zhudan_sps112_tv)
        TextView     zhudanSps112Tv;
        @BindView(R.id.sps_ll112)
        LinearLayout spsLl112;
        @BindView(R.id.zhudan_victory113_tv)
        TextView     zhudanVictory113Tv;
        @BindView(R.id.zhudan_sps113_tv)
        TextView     zhudanSps113Tv;
        @BindView(R.id.sps_ll113)
        LinearLayout spsLl113;
        @BindView(R.id.zhudan_hang_ll111)
        LinearLayout zhudanHangLl111;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
