//package shlottery.gov.cn.lotterycenter.utils;
//
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
///**
// * ************************************************
// * 作    者：yongchao.bei
// * 版    本：1.0
// * 创建日期：2016/10/18 17:32
// * 描    述：
// * 修订历史：
// * ************************************************
// */
//
//public class CalculateBonu {
//    /**
//     * 计算奖金要依赖计算注数的算法，在计算注数时已经把所有情况（如果定胆，已经被删去）的item集合列出来了，
//     * 所以只需要从每个item集合中找到最大的sp值，计算每个item组合中最大的中奖金额，把所有item组合的最大中
//     * 奖金额相加就是最大中奖金额
//     */
//
//    private static double getMaxBonus(DingdanItemBean dingdanBean) {
//        ArrayList<String> spList = dingdanBean.getSpsList();
//        ArrayList<Double> spValueList = new_sign ArrayList<>();
//        for (String sp : spList) {
//            spValueList.add(Double.parseDouble(sp));
//        }
//        //double数组进行排序
//        Collections.sort(spValueList);
//        double maxSp = spValueList.get(spValueList.size() - 1);
////        LogUtils.i("calculateBonus after sort:" + maxSp);
//        return maxSp;
//    }
//
//    //过关最高奖金
//    public static String getMaxCalculateBouns(List<DingdanItemBean> dingdanList, List<String> chuanlianList) {
//        if (null != dingdanList && null != chuanlianList && !chuanlianList.isEmpty()) {
//            List<List<DingdanItemBean>> result = CalculateStage222.getAllItemCaculateStage(dingdanList, chuanlianList);
//            if (null != chuanlianList && null != dingdanList && null != result) {
//                double price = DataPool.getPrice();
//                int multiple = DataPool.getMultiple();
//                double sum = 0;
//                double temp = 1;
//                for (int i = 0; i < result.size(); i++) {
//                    temp = 1;
//                    for (int j = 0; j < result.get(i).size(); j++) {
//                        temp *= getMaxBonus(result.get(i).get(j));
//                    }
//                    sum += temp;
//                }
//                return get2Double(sum * price*multiple);
//            }
//            return 0.00 + "";
//        } else
//            return 0.00 + "";
//    }
//
//    //单关最高奖金
//    public static String getDanMaxCalculateBonus(List<DingdanItemBean> dingdanList) {
//        double bonus = 0.00;
//        double sum = 0.00;
//        for (int i = 0; i < dingdanList.size(); i++) {
//            bonus = 0.00;
//            for (int j = 0; j < dingdanList.get(i).getSpsList().size(); j++) {
//                bonus += Double.valueOf(dingdanList.get(i).getSpsList().get(j));
//            }
//            sum += bonus;
//        }
//        return get2Double(sum);
//    }
//
//    //得到两位小数的double
//    private static String get2Double(double number) {
//        LogUtils.i("asyntask count getresult calculate" + number);
//        DecimalFormat df = new_sign DecimalFormat("#.00");//保留两位小数
//        BigDecimal bigDecimal = new_sign BigDecimal(number);
//        String str = df.format(bigDecimal);
//        LogUtils.i("asyntask count getresult calculate::::" + str);
//        str = str.substring(0, str.lastIndexOf(".") + 3);
//
//        LogUtils.i("asyntask count getresult calculate:::::::" + str);
//        return str;
//    }
//
//}
//
