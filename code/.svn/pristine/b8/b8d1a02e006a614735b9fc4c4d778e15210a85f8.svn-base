package shlottery.gov.cn.lotterycenter.ui.nublottery.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.NumLotOrderBean;
import shlottery.gov.cn.lotterycenter.entity.Configs;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-05-0005 下午 08:56
 * 描    述：十一选5的适配器
 * 修订历史：
 * ================================================
 */

public class NumSh115DingdanAdapter extends BaseAdapter {
    private List<NumLotOrderBean> mNumLotOrderBeans;
    private Context context;
    private Handler handler;

    public NumSh115DingdanAdapter(Context context, List<NumLotOrderBean> mNumLotOrderBeans, Handler handler) {
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
        for (int i = 0; i < mNumLotOrderBeans.get(position).getLotteryData().size(); i++) {
            if (i == 0) {

                for (int m = 0; m <= 10; m++) {
                    if (mNumLotOrderBeans.get(position).getLotteryData().get(i).get(m)) {
                        if (StringUtils.isEmpty(s1)) {
                            if (m < 9) {
                                s1 += "0" + (m + 1);
                            } else {
                                s1 += (m + 1);
                            }
                        } else {
                            if (m < 9) {
                                s1 += "  " + "0" + (m + 1);
                            } else {
                                s1 += "  " + (m + 1);
                            }
                        }
                    }
                }
            } else if (i == 1) {
                for (int m = 0; m <= 10; m++) {
                    if (mNumLotOrderBeans.get(position).getLotteryData().get(i).get(m)) {
                        if (StringUtils.isEmpty(s2)) {
                            if (m < 9) {
                                s2 += "0" + (m + 1);
                            } else {
                                s2 += (m + 1);
                            }

                        } else {
                            if (m < 9) {
                                s2 += "  " + "0" + (m + 1);
                            } else {
                                s2 += "  " + (m + 1);
                            }

                        }
                    }
                }
            } else if (i == 2) {
                for (int m = 0; m <= 10; m++) {
                    if (mNumLotOrderBeans.get(position).getLotteryData().get(i).get(m)) {
                        if (StringUtils.isEmpty(s3)) {
                            if (m < 9) {
                                s3 += "0" + (m + 1);
                            } else {
                                s3 += (m + 1);
                            }
                        } else {
                            if (m < 9) {
                                s3 += "  " + "0" + (m + 1);
                            } else {
                                s3 += "  " + (m + 1);
                            }
                        }
                    }
                }
            }
        }
       /* if ((s1 + "    " + s2 + "    " + s3).indexOf(",") != -1) {//如果String中包含",",说明为复式
            holder.touzhuType.setText("复式投注");
        } else {
            holder.touzhuType.setText("单式投注");
        }*/
        if (mNumLotOrderBeans.get(position).isdan()) {//当为胆时
            if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_RXDAN2) {
                holder.touzhuType.setText("任选二 胆拖");
                holder.txt.setText("(" + s1 + ")" + "," + s2);
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_RXDAN3) {
                holder.touzhuType.setText("任选三 胆拖");
                holder.txt.setText("(" + s1 + ")" + "," + s2);
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_RXDAN4) {
                holder.touzhuType.setText("任选四 胆拖");
                holder.txt.setText("(" + s1 + ")" + "," + s2);
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_RXDAN5) {
                holder.touzhuType.setText("任选五 胆拖");
                holder.txt.setText("(" + s1 + ")" + "," + s2);
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_RXDAN6) {
                holder.touzhuType.setText("任选六 胆拖");
                holder.txt.setText("(" + s1 + ")" + "," + s2);
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_RXDAN7) {
                holder.touzhuType.setText("任选七 胆拖");
                holder.txt.setText("(" + s1 + ")" + "," + s2);
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_RXDAN8) {
                holder.touzhuType.setText("任选八 胆拖");
                holder.txt.setText("(" + s1 + ")" + "," + s2);
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_Q2ZUXDAN) {
                holder.touzhuType.setText("前二组选 胆拖");
                holder.txt.setText("(" + s1 + ")" + "," + s2);
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_Q3ZUXDAN) {
                holder.touzhuType.setText("前三组选 胆拖");
                holder.txt.setText("(" + s1 + ")" + "," + s2);
            }
        } else {
            if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_RX2) {
                if (mNumLotOrderBeans.get(position).getCount() == 1) {
                    holder.touzhuType.setText("任选二 单式");
                } else {
                    holder.touzhuType.setText("任选二 复式");
                }

                holder.txt.setText(s1);
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_RX3) {
                if (mNumLotOrderBeans.get(position).getCount() == 1) {
                    holder.touzhuType.setText("任选三 单式");
                } else {
                    holder.touzhuType.setText("任选三 复式");
                }
                holder.txt.setText(s1);
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_RX4) {
                if (mNumLotOrderBeans.get(position).getCount() == 1) {
                    holder.touzhuType.setText("任选四 单式");
                } else {
                    holder.touzhuType.setText("任选四 复式");
                }
                holder.txt.setText(s1);
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_RX5) {
                if (mNumLotOrderBeans.get(position).getCount() == 1) {
                    holder.touzhuType.setText("任选五 单式");
                } else {
                    holder.touzhuType.setText("任选五 复式");
                }
                holder.txt.setText(s1);
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_RX6) {
                if (mNumLotOrderBeans.get(position).getCount() == 1) {
                    holder.touzhuType.setText("任选六 单式");
                } else {
                    holder.touzhuType.setText("任选六 复式");
                }
                holder.txt.setText(s1);
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_RX7) {
                if (mNumLotOrderBeans.get(position).getCount() == 1) {
                    holder.touzhuType.setText("任选七 单式");
                } else {
                    holder.touzhuType.setText("任选七 复式");
                }
                holder.txt.setText(s1);
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_RX8) {
                if (mNumLotOrderBeans.get(position).getCount() == 1) {
                    holder.touzhuType.setText("任选八 单式");
                } else {
                    holder.touzhuType.setText("任选八 复式");
                }
                holder.txt.setText(s1);
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_QY) {
                if (mNumLotOrderBeans.get(position).getCount() == 1) {
                    holder.touzhuType.setText("前一直选 单式");
                } else {
                    holder.touzhuType.setText("前一直选 复式");
                }
                holder.txt.setText(s1);
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_Q2ZHIX) {
                if (mNumLotOrderBeans.get(position).getCount() == 1) {
                    holder.touzhuType.setText("前二直选 单式");
                } else {
                    holder.touzhuType.setText("前二直选 复式");
                }
                holder.txt.setText(s1 + " | " + s2);
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_Q3ZHIX) {
                if (mNumLotOrderBeans.get(position).getCount() == 1) {
                    holder.touzhuType.setText("前三直选 单式");
                } else {
                    holder.touzhuType.setText("前三直选 复式");
                }
                holder.txt.setText(s1 + " | " + s2 + " | " + s3);
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_Q2ZUX) {
                if (mNumLotOrderBeans.get(position).getCount() == 1) {
                    holder.touzhuType.setText("前二组选 单式");
                } else {
                    holder.touzhuType.setText("前二组选 复式");
                }
                holder.txt.setText(s1);
            } else if (mNumLotOrderBeans.get(position).getmType() == Configs.SYX5_Q3ZUX) {
                if (mNumLotOrderBeans.get(position).getCount() == 1) {
                    holder.touzhuType.setText("前三组选 单式");
                } else {
                    holder.touzhuType.setText("前三组选 复式");
                }
                holder.txt.setText(s1);
            }
        }

        holder.count.setText(count + "注" + "  " + 2 * count + "元");
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumLotOrderBeans.remove(position);
                handler.sendEmptyMessage(0);
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
