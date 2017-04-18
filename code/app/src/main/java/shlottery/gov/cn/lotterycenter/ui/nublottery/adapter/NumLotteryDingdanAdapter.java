package shlottery.gov.cn.lotterycenter.ui.nublottery.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.BaseBean;
import shlottery.gov.cn.lotterycenter.bean.NumLotOrderBean;
import shlottery.gov.cn.lotterycenter.bean.NumLotteryBean;
import shlottery.gov.cn.lotterycenter.bean.OrderBean;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-05-0005 下午 08:56
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class NumLotteryDingdanAdapter extends BaseAdapter {
    private List<NumLotOrderBean> mNumLotOrderBeans;
    private Context context;
    private Handler handler;

    public NumLotteryDingdanAdapter(Context context, List<NumLotOrderBean> mNumLotOrderBeans, Handler handler) {
        this.mNumLotOrderBeans = mNumLotOrderBeans;
        this.context = context;
        this.handler = handler;
    }

    @Override
    public int getCount() {
        return mNumLotOrderBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mNumLotOrderBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        Holder holder;
        if (null == view) {
            holder = new Holder();
            view = View.inflate(context, R.layout.adapter_lotto_dingdan, null);
            holder.txt = (TextView) view.findViewById(R.id.lotto_order_txt);

            holder.count = (TextView) view.findViewById(R.id.lotto_order_count);
            holder.delete = (ImageView) view.findViewById(R.id.lotto_order_delete);
            holder.touzhuType = (TextView) view.findViewById(R.id.lotto_order_touzhuType);

            view.setTag(holder);
        }
        holder = (Holder) view.getTag();
        int count = mNumLotOrderBeans.get(position).getCount();
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        String s5 = "";
        String s6 = "";
        String s7 = "";

        String s11 = "";
        String s12 = "";
        String s13 = "";
        String s14 = "";
        String s15 = "";
        String s16 = "";
        String s17 = "";
        for (int i = 0; i < mNumLotOrderBeans.get(position).getLotteryData().size(); i++) {
            if (i == 0) {
                for (int m = 0; m <= 9; m++) {
                    if (mNumLotOrderBeans.get(position).getLotteryData().get(i).get(m)) {
                        if (StringUtils.isEmpty(s1)) {
                            s1 += m;
                        } else {
                            s1 += "," + m;
                        }
                    }
                }
            } else if (i == 1) {
                for (int m = 0; m <= 9; m++) {
                    if (mNumLotOrderBeans.get(position).getLotteryData().get(i).get(m)) {
                        if (StringUtils.isEmpty(s2)) {
                            s2 += m;
                        } else {
                            s2 += "," + m;
                        }
                    }
                }
            } else if (i == 2) {
                for (int m = 0; m <= 9; m++) {
                    if (mNumLotOrderBeans.get(position).getLotteryData().get(i).get(m)) {
                        if (StringUtils.isEmpty(s3)) {
                            s3 += m;
                        } else {
                            s3 += "," + m;
                        }
                    }
                }
            } else if (i == 3) {
                for (int m = 0; m <= 9; m++) {
                    if (mNumLotOrderBeans.get(position).getLotteryData().get(i).get(m)) {
                        if (StringUtils.isEmpty(s4)) {
                            s4 += m;
                        } else {
                            s4 += "," + m;
                        }
                    }
                }
            } else if (i == 4) {
                for (int m = 0; m <= 9; m++) {
                    if (mNumLotOrderBeans.get(position).getLotteryData().get(i).get(m)) {
                        if (StringUtils.isEmpty(s5)) {
                            s5 += m;
                        } else {
                            s5 += "," + m;
                        }
                    }
                }
            } else if (i == 5) {
                for (int m = 0; m <= 9; m++) {
                    if (mNumLotOrderBeans.get(position).getLotteryData().get(i).get(m)) {
                        if (StringUtils.isEmpty(s6)) {
                            s6 += m;
                        } else {
                            s6 += "," + m;
                        }
                    }
                }
            } else if (i == 6) {
                for (int m = 0; m <= 9; m++) {
                    if (mNumLotOrderBeans.get(position).getLotteryData().get(i).get(m)) {
                        if (StringUtils.isEmpty(s7)) {
                            s7 += m;
                        } else {
                            s7 += "," + m;
                        }
                    }
                }
            }
        }


        for (int i = 0; i < mNumLotOrderBeans.get(position).getLotteryData().size(); i++) {
            if (i == 0) {
                for (int m = 0; m <= 9; m++) {
                    if (mNumLotOrderBeans.get(position).getLotteryData().get(i).get(m)) {
                        if (StringUtils.isEmpty(s11)) {
                            s11 += m;
                        } else {
                            s11 += "" + m;
                        }
                    }
                }
            } else if (i == 1) {
                for (int m = 0; m <= 9; m++) {
                    if (mNumLotOrderBeans.get(position).getLotteryData().get(i).get(m)) {
                        if (StringUtils.isEmpty(s12)) {
                            s12 += m;
                        } else {
                            s12 += "" + m;
                        }
                    }
                }
            } else if (i == 2) {
                for (int m = 0; m <= 9; m++) {
                    if (mNumLotOrderBeans.get(position).getLotteryData().get(i).get(m)) {
                        if (StringUtils.isEmpty(s13)) {
                            s13 += m;
                        } else {
                            s13 += "" + m;
                        }
                    }
                }
            } else if (i == 3) {
                for (int m = 0; m <= 9; m++) {
                    if (mNumLotOrderBeans.get(position).getLotteryData().get(i).get(m)) {
                        if (StringUtils.isEmpty(s14)) {
                            s14 += m;
                        } else {
                            s14 += "" + m;
                        }
                    }
                }
            } else if (i == 4) {
                for (int m = 0; m <= 9; m++) {
                    if (mNumLotOrderBeans.get(position).getLotteryData().get(i).get(m)) {
                        if (StringUtils.isEmpty(s15)) {
                            s15 += m;
                        } else {
                            s15 += "" + m;
                        }
                    }
                }
            } else if (i == 5) {
                for (int m = 0; m <= 9; m++) {
                    if (mNumLotOrderBeans.get(position).getLotteryData().get(i).get(m)) {
                        if (StringUtils.isEmpty(s16)) {
                            s16 += m;
                        } else {
                            s16 += "" + m;
                        }
                    }
                }
            } else if (i == 6) {
                for (int m = 0; m <= 9; m++) {
                    if (mNumLotOrderBeans.get(position).getLotteryData().get(i).get(m)) {
                        if (StringUtils.isEmpty(s17)) {
                            s17 += m;
                        } else {
                            s17 += "" + m;
                        }
                    }
                }
            }
        }

        if (mNumLotOrderBeans.get(position).getLotId().equals("pl5")) {
            holder.txt.setText(s11 + "," + s12 + "," + s13 + "," + s14 + "," + s15);
//            if ((s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6 + "," + s7).indexOf("  ") != -1) {//如果String中包含",",说明为复式
//                holder.touzhuType.setText("复式投注");
//            } else {
//                holder.touzhuType.setText("单式投注");
//            }
            if ((s11.length() > 1 || s12.length() > 1 || s13.length() > 1 || s14.length() > 1 || s15.length() > 1)) {//如果String中包含",",说明为复式
                holder.touzhuType.setText("复式投注");
            } else {
                holder.touzhuType.setText("单式投注");
            }
        } else if (mNumLotOrderBeans.get(position).getLotId().equals("qxc")) {
            holder.txt.setText(s11 + "," + s12 + "," + s13 + "," + s14 + "," + s15 + "," + s16 + "," + s17);
            if ((s11.length() > 1 || s12.length() > 1 || s13.length() > 1 || s14.length() > 1 || s15.length() > 1) || s16.length() > 1 || s17.length() > 1) {//如果String中包含",",说明为复式
                holder.touzhuType.setText("复式投注");
            } else {
                holder.touzhuType.setText("单式投注");
            }
            /*if ((s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6 + "," + s7).indexOf("  ") != -1) {//如果String中包含",",说明为复式
                holder.touzhuType.setText("复式投注");
            } else {
                holder.touzhuType.setText("单式投注");
            }*/
        } else {
            if (mNumLotOrderBeans.get(position).getmType() == Configs.PL3_ZU3) {
                holder.txt.setText(s1);
//                if ((s1 ).split("  ").length>2) {//如果String中包含"  ",说明为复式
                holder.touzhuType.setText("组三复式");
//                } else {
//                    holder.touzhuType.setText("组三单式");
//                }
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.PL3_ZU6) {
                holder.txt.setText(s1);
                if ((s1).split(",").length > 3) {//s1报长度大于3说明为复式
                    holder.touzhuType.setText("组六复式");
                } else {
                    holder.touzhuType.setText("组六单式");
                }
              /*  if ((s1 ).split("  ").length>3) {//s1报长度大于3说明为复式
                    holder.touzhuType.setText("组六复式");
                } else {
                    holder.touzhuType.setText("组六单式");
                }*/
            } else {
                holder.txt.setText(s11 + "," + s12 + "," + s13);
                if ((s11.length() > 1 || s12.length() > 1 || s13.length() > 1)) {//如果String中包含"  ",说明为复式
                    holder.touzhuType.setText("直选复式");
                } else {
                    holder.touzhuType.setText("直选单式");
                }
               /* if ((s1 + "," + s2 + "," + s3).indexOf("  ") != -1) {//如果String中包含"  ",说明为复式
                    holder.touzhuType.setText("直选复式");
                } else {
                    holder.touzhuType.setText("直选单式");
                }*/
            }
        }
        holder.count.setText(count + "注" + "  " + 2 * count + "元");
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumLotOrderBeans.remove(position);
                handler.sendEmptyMessage(position);
                notifyDataSetChanged();
            }
        });
        return view;
    }

    private class Holder {
        TextView txt;
        TextView touzhuType;
        TextView count;
        ImageView delete;
    }
}
