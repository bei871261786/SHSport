package shlottery.gov.cn.lotterycenter.utils;

import android.app.Application;
import android.graphics.Color;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.Sf14Bean;
import shlottery.gov.cn.lotterycenter.bean.SfcDingdanBean;
import shlottery.gov.cn.lotterycenter.bean.ZhudanDetailBean;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-25-0025 10:47
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class ZhuDanUtils {

    //注单详情中过关方式以及注数 以及金额
    public static String getZhuDanDetail(int position, ZhudanDetailBean zhudanDetailBean) {
        if (null != zhudanDetailBean) {
            String s = "";
            switch (zhudanDetailBean.getData().getLotId()) {
                case "pl3":
                    if (zhudanDetailBean.getData().getCodeList().get(position).getPlayType().equals("组三包号")) {
                        s = "组三复式  " + zhudanDetailBean.getData().getCodeList().get(position).getAmount() + "注  " + zhudanDetailBean.getData().getCodeList().get(position).getMoney() + "元";
                    } else if (zhudanDetailBean.getData().getCodeList().get(position).getPlayType().equals("组六包号")) {
                        if (zhudanDetailBean.getData().getCodeList().get(position).getStakeCode().length() == 5) {
                            s = "组六单式  " + zhudanDetailBean.getData().getCodeList().get(position).getAmount() + "注  " + zhudanDetailBean.getData().getCodeList().get(position).getMoney() + "元";
                        } else {
                            s = "组六复式  " + zhudanDetailBean.getData().getCodeList().get(position).getAmount() + "注  " + zhudanDetailBean.getData().getCodeList().get(position).getMoney() + "元";
                        }
                    } else {
                        if (zhudanDetailBean.getData().getCodeList().get(position).getStakeCode().length() == 5) {
                            s = "直选单式  " + zhudanDetailBean.getData().getCodeList().get(position).getAmount() + "注  " + zhudanDetailBean.getData().getCodeList().get(position).getMoney() + "元";
                        } else {
                            s = "直选复式  " + zhudanDetailBean.getData().getCodeList().get(position).getAmount() + "注  " + zhudanDetailBean.getData().getCodeList().get(position).getMoney() + "元";
                        }
                    }
                    break;
                case "pl5":
                    s = zhudanDetailBean.getData().getCodeList().get(position).getPlayType() + "  " + zhudanDetailBean.getData().getCodeList().get(position).getAmount() + "注  " + zhudanDetailBean.getData().getCodeList().get(position).getMoney() + "元";
                    break;
                case "sh115":
                    s = zhudanDetailBean.getData().getCodeList().get(position).getPlayType() + "  " + zhudanDetailBean.getData().getCodeList().get(position).getAmount() + "注  " + zhudanDetailBean.getData().getCodeList().get(position).getMoney() + "元";
                    break;
                case "qxc":
                    s = zhudanDetailBean.getData().getCodeList().get(position).getPlayType() + "  " + zhudanDetailBean.getData().getCodeList().get(position).getAmount() + "注  " + zhudanDetailBean.getData().getCodeList().get(position).getMoney() + "元";
                    break;
                case "dlt":
                    s = zhudanDetailBean.getData().getCodeList().get(position).getPlayType() + "  " + zhudanDetailBean.getData().getCodeList().get(position).getAmount() + "注  " + zhudanDetailBean.getData().getCodeList().get(position).getMoney() + "元";
                    break;
                default:
                    break;
            }
            return s;
        } else {
            return "";
        }
    }

    //获取数字彩注单详情中数字 正确显示的String
    public static void setNumDetail(int position, TextView textView, ZhudanDetailBean zhudanDetailBean) {
        if (null != zhudanDetailBean) {
            String s = "";
            switch (zhudanDetailBean.getData().getLotId()) {
                case "pl3":
                    textView.setText(zhudanDetailBean.getData().getCodeList().get(position).getStakeCode());
                    break;
                case "pl5":
                    textView.setText(zhudanDetailBean.getData().getCodeList().get(position).getStakeCode());
                    break;
                case "sh115":

                    if (zhudanDetailBean.getData().getCodeList().get(position).getPlayType().contains("胆拖")) {
                        String s1 = zhudanDetailBean.getData().getCodeList().get(position).getStakeCode();
                        s1 = s1.replaceAll(",", "  ");
                        String[] arr = s1.split("\\$");
                        String s2 = arr[0];
                        String s3 = arr[1];
                        s = "(" + s2 + ")  " + s3;
                        textView.setText(s);
                    } else if (zhudanDetailBean.getData().getCodeList().get(position).getPlayType().contains("任选") || zhudanDetailBean.getData().getCodeList().get(position).getPlayType().contains("前三组选") || zhudanDetailBean.getData().getCodeList().get(position).getPlayType().contains("前二组选") || zhudanDetailBean.getData().getCodeList().get(position).getPlayType().contains("前一直选")) {
                        String s1 = zhudanDetailBean.getData().getCodeList().get(position).getStakeCode();
                        s = s1.replaceAll(",", "  ");
                        textView.setText(s);
                    } else if (zhudanDetailBean.getData().getCodeList().get(position).getPlayType().contains("前二直选")) {
                        String s1 = zhudanDetailBean.getData().getCodeList().get(position).getStakeCode();
                        s = s1.replaceAll(",", "  ");
                        s = s.replaceAll("#", " | ");
                        textView.setText(s);
                    } else if (zhudanDetailBean.getData().getCodeList().get(position).getPlayType().contains("前三直选")) {
                        String s1 = zhudanDetailBean.getData().getCodeList().get(position).getStakeCode();
                        s = s1.replaceAll(",", "  ");
                        s = s.replaceAll("#", " | ");
                        textView.setText(s);
                    }
//                    textView.setText(zhudanDetailBean.getData().getCodeList().get(position).getStakeCode());
                    break;
                case "qxc":
                    textView.setText(zhudanDetailBean.getData().getCodeList().get(position).getStakeCode());
                    break;
                case "dlt":
                    if (zhudanDetailBean.getData().getCodeList().get(position).getPlayType().equals("胆拖")) {
                        String s1 = zhudanDetailBean.getData().getCodeList().get(position).getStakeCode();
                        s1 = s1.replaceAll(",", "  ");
                        String[] arr = s1.split("\\$");
                        if (arr.length == 3) {
                            String s2 = arr[0];
//                        s2.replace(",", "  ");
                            s2 = "(" + s2 + ")  ";
                            String[] arr2 = arr[1].split("#");
                            String s3 = arr2[0];
//                        s3.replace(",", "  ");
                            String s4 = arr2[1];
//                        s4.replace(",", "  ");
                            s4 = "  (" + s4 + ")  ";
                            String s5 = arr[2];
//                        s5.replace(",", "  ");
                            s = s2 + s3 + s4 + s5;
                            textView.setText(s);
                            TextUtils.setStrColor(textView, s, s2 + s3, BaseApplication.getApplication().getResources().getColor(R.color.select_red));
                            TextUtils.setStrColor(textView, s, s4 + s5, BaseApplication.getApplication().getResources().getColor(R.color.homeblue));
                        }else {
                            String s2 = arr[0];
//                        s2.replace(",", "  ");
                            s2 = "(" + s2 + ")  ";
                            String[] arr2 = arr[1].split("#");
                            String s3 = arr2[0]+" ";
//                        s3.replace(",", "  ");
                            String s4 = arr2[1];
//
                            s = s2 + s3 + s4 ;
                            textView.setText(s);
                            TextUtils.setStrColor(textView, s, s2 + s3, BaseApplication.getApplication().getResources().getColor(R.color.select_red));
                            TextUtils.setStrColor(textView, s, s4 , BaseApplication.getApplication().getResources().getColor(R.color.homeblue));

                        }
                    } else {
                        String s1 = zhudanDetailBean.getData().getCodeList().get(position).getStakeCode();
                        s1 = s1.replaceAll(",", "  ");
                        String[] arr = s1.split("#");
                        String s3 = arr[0].replace(",", "  ");
                        s3 += "  ";
                        String s4 = arr[1].replace(",", "  ");
                        s = s3 + s4;
                        TextUtils.setStrColor(textView, s, s3, BaseApplication.getApplication().getResources().getColor(R.color.select_red));
                        TextUtils.setStrColor(textView, s, s4, BaseApplication.getApplication().getResources().getColor(R.color.homeblue));
                    }

                    break;
                default:
                    break;

            }
        }
    }

    //初始化注单beans
    public static SfcDingdanBean initZhuDanBean(ZhudanDetailBean zhudanDetailBean, SfcDingdanBean sfcDingdanBean) {
        List<Sf14Bean.DataBean.IssueListBean.MatchListBean> MatchListBeans = new ArrayList<>();//赛事bean的集合
        sfcDingdanBean = new SfcDingdanBean();
        sfcDingdanBean.setIssueNo(zhudanDetailBean.getData().getIssueNo());
        sfcDingdanBean.setLotId(zhudanDetailBean.getData().getLotId());
        String arr[] = zhudanDetailBean.getData().getCodeList().get(0).getStakeCode().split(",");

        for (int i = 0; i < zhudanDetailBean.getData().getMatchList().size(); i++) {
            Sf14Bean.DataBean.IssueListBean.MatchListBean matchListBean = new Sf14Bean.DataBean.IssueListBean.MatchListBean();
            HashMap<Integer, Boolean> hashMap = new HashMap();
            List<String> StakeOptions = zhudanDetailBean.getData().getMatchList().get(i).getStakeOption();
            //初始化hashmap  默认为没选中
            hashMap.put(0, false);
            hashMap.put(1, false);
            hashMap.put(2, false);
            hashMap.put(3, false);
            hashMap.put(4, false);
            hashMap.put(5, false);
            hashMap.put(6, false);
            hashMap.put(7, false);

            matchListBean.setHostName(zhudanDetailBean.getData().getMatchList().get(i).getHostName());
            matchListBean.setLeagueName(zhudanDetailBean.getData().getMatchList().get(i).getLeagueName());
            matchListBean.setVisitName(zhudanDetailBean.getData().getMatchList().get(i).getVisitName());
            matchListBean.setMatchTime(zhudanDetailBean.getData().getMatchList().get(i).getMatchTime());
            if (zhudanDetailBean.getData().getMatchList().get(i).getDan() == 0) {//设置是否为胆 0不是单 1是胆
                matchListBean.setChecked(false);
            } else {
                matchListBean.setChecked(true);
            }
            if (zhudanDetailBean.getData().getLotId().equals("jqc")) {//进球彩
                for (int m = 0; m < StakeOptions.size(); m++) {
                    if (StakeOptions.get(0).contains("0")) {
                        hashMap.put(0, true);
                    }
                    if (StakeOptions.get(0).contains("1")) {
                        hashMap.put(1, true);
                    }
                    if (StakeOptions.get(0).contains("2")) {
                        hashMap.put(2, true);
                    }
                    if (StakeOptions.get(0).contains("3")) {
                        hashMap.put(3, true);
                    }
                    if (StakeOptions.get(1).contains("0")) {
                        hashMap.put(4, true);
                    }
                    if (StakeOptions.get(1).contains("1")) {
                        hashMap.put(5, true);
                    }
                    if (StakeOptions.get(1).contains("2")) {
                        hashMap.put(6, true);
                    }
                    if (StakeOptions.get(1).contains("3")) {
                        hashMap.put(7, true);
                    }
                }

            } else if (zhudanDetailBean.getData().getLotId().equals("bqc")) {//半全场

                if (StakeOptions.get(0).contains("3")) {
                    hashMap.put(0, true);
                }
                if (StakeOptions.get(0).contains("1")) {
                    hashMap.put(1, true);
                }
                if (StakeOptions.get(0).contains("0")) {
                    hashMap.put(2, true);
                }
                if (StakeOptions.get(1).contains("3")) {
                    hashMap.put(3, true);
                }
                if (StakeOptions.get(1).contains("1")) {
                    hashMap.put(4, true);
                }
                if (StakeOptions.get(1).contains("0")) {
                    hashMap.put(5, true);
                }
            } else {
                if (StakeOptions != null && StakeOptions.size() > 0) {
                    if (StakeOptions.get(0).contains("3")) {
                        hashMap.put(0, true);
                    }
                    if (StakeOptions.get(0).contains("1")) {
                        hashMap.put(1, true);
                    }
                    if (StakeOptions.get(0).contains("0")) {
                        hashMap.put(2, true);
                    }
                }
            }
            matchListBean.setCheckedHashMap(hashMap);
            MatchListBeans.add(matchListBean);
            sfcDingdanBean.setMatchListBeans(MatchListBeans);
        }
        return sfcDingdanBean;
    }
}
