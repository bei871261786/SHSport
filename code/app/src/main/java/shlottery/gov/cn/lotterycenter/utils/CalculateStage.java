package shlottery.gov.cn.lotterycenter.utils;

import com.orhanobut.logger.Logger;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import shlottery.gov.cn.lotterycenter.bean.BaseBean;
import shlottery.gov.cn.lotterycenter.utils.SfcUtils.Combination;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/18 17:33
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class CalculateStage {
  /* private String mCaiZhong;
     private int mGuoGuan;//过关方式,单关或过关,0为过关,1为单关
     private boolean isDan;//是否胆拖
     private List<String> mChuanLian;//串联方式,仅过关有
     private List<MatchsBean> mMatchsBeans;//订单datas*/
    // private String[] mGuoGuanChuanLianArr = new_sign String[]{"2串1", "3串1", "4串1", "5串1", "6串1", "7串1", "8串1"};

    public synchronized static BigInteger getCaculateCount(List<BaseBean> baseBeen) {
        /**
         * 从n个数字中选择m个数字,只计算组合个数
         * @param a
         * @param m
         * @return
         */
        long m = 0;
        long n = 0;
        long m1 = 0;
        long n1 = 0;
        BigInteger result = new BigInteger("0");
        int danCount = 0;

        List<BaseBean> data = new ArrayList<>();
        if (baseBeen != null && baseBeen.get(0) != null) {
            m = baseBeen.get(0).getMinCount();
        }

        //得到数据列表定胆数量和总共参与计算个数
        for (int i = 0; i < baseBeen.size(); i++) {
            if (baseBeen.get(i).isSelected()) {
                data.add(baseBeen.get(i));
                n++;
            }
            if (baseBeen.get(i).isDan()) {
                danCount++;
            }
        }
        n1 = n - danCount;
        m1 = m - danCount;
        if (m1 > n1) {
            LogUtils.i("错误！数组a中只有" + n + "个元素。" + m + "大于" + n + "!!!");
        } else if (m1 == n1) {
            return new BigInteger("1");
        } else {
            result = getFactorial(n1).divide(getFactorial(m1)).divide(getFactorial(n1 - m1));
            LogUtils.i("getCaculateCount-----------:" + "m:" + m + "\tn:" + n + "\tf(n):" + getFactorial(n1) + "\tf(m):" + getFactorial(m1) + "\tf(n-m):" + getFactorial(n1 - m1) + "\tresult:" + result);

        }
        return result;
    }

    public static BigInteger getFactorial(long n) {
        if (n < 0) {
            LogUtils.i("n必须大于等于0！");
            return new BigInteger("-1");
        } else if (n == 0) {
            return new BigInteger("0");
        }
        //将数组换成字符串后构造BigInteger
        BigInteger result = new BigInteger("1");
        for (; n > 0; n--) {
            //将数字n转换成字符串后，再构造一个BigInteger对象，与现有结果做乘法
            result = result.multiply(new BigInteger(new Long(n).toString()));
        }
        return result;
    }

    public synchronized static List<List<BaseBean>> getCaculateStare(List<BaseBean> baseBeen) {

        /**
         * 从n个数字中选择m个数字
         * @param a
         * @param m
         * @return
         */
        int m = 0;
        int n = 0;
        int danCount = 0;

        List<BaseBean> data = new ArrayList<>();
        if (baseBeen != null && baseBeen.get(0) != null) {
            m = baseBeen.get(0).getMinCount();
        }

        //得到数据列表定胆数量和总共参与计算个数
        for (int i = 0; i < baseBeen.size(); i++) {
            if (baseBeen.get(i).isSelected()) {
                data.add(baseBeen.get(i));
                n++;
            }
            if (baseBeen.get(i).isDan()) {
                danCount++;
            }
        }

        if (m > n) {
            LogUtils.i("错误！数组a中只有" + n + "个元素。" + m + "大于" + n + "!!!");

        } else {
            List<List<BaseBean>> result = new ArrayList();
            int[] bs = new int[n];
            for (int i = 0; i < n; i++) {
                bs[i] = 0;
            }
            //初始化
            for (int i = 0; i < m; i++) {
                bs[i] = 1;
            }
            boolean flag = true;
            boolean tempFlag = false;
            int pos = 0;
            int sum = 0;
            boolean isChange = false;
            //首先找到第一个10组合，然后变成01，同时将左边所有的1移动到数组的最左边
            do {
                printDan(data, "begin---");
                sum = 0;
                pos = 0;
                tempFlag = true;
                result.add(print(bs, data, m));

                for (int i = 0; i < n - 1; i++) {
                    if (bs[i] == 1 && bs[i + 1] == 0) {
                        bs[i] = 0;
                        bs[i + 1] = 1;
                        pos = i;
                        break;
                    }
                }
                //将左边的1全部移动到数组的最左边
                for (int i = 0; i < pos; i++) {
                    if (bs[i] == 1) {
                        sum++;
                    }
                }
                for (int i = 0; i < pos; i++) {
                    if (i < sum) {
                        bs[i] = 1;
                    } else {
                        bs[i] = 0;
                    }
                }

                //检查是否所有的1都移动到了最右边
                for (int i = n - m; i < n; i++) {
                    if (bs[i] == 0) {
                        tempFlag = false;
                        break;
                    }
                }
                if (tempFlag == false) {
                    isChange = true;
                    flag = true;
                } else {
                    flag = false;
                }
            }
            while (flag);
            if (isChange) {
                result.add(print(bs, data, m));
            }
            LogUtils.i("calculate control::danCount:" + danCount + ":::result:" + result.size() + "::::" + result.toString());
            //得到定胆的结果
            for (int i = 0; i < result.size(); i++) {
                LogUtils.i("calculate control2" + result.get(i).toString());
                printDan(result.get(i), "_-_-_-_-calculate control2");
                int count = 0;
                for (int j = 0; j < result.get(i).size(); j++) {
                    if (result.get(i).get(j).isDan()) {
                        count++;
                    }
                }
                LogUtils.i("calculate control::count--------:" + count + "::::" + danCount);
                if (count != danCount) {
                    result.remove(i);
                    i--;
                }
            }
//            LogUtils.i("caluclateStage result prepare size" + result.size());
            return result;
        }
        return null;

    }

    private static List<BaseBean> print(int[] bs, List<BaseBean> data, int m) {
        printDan(data, "print");
        List<BaseBean> result = new ArrayList<>();
        int pos = 0;
        for (int i = 0; i < bs.length; i++) {
            if (bs[i] == 1) {
                result.add(data.get(i));
                pos++;
            }
        }
        return result;
    }

    private static void printDan(List<BaseBean> data, String str) {
        int danCount = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isDan()) {
                danCount++;
            }
        }
        LogUtils.i("calculate printDan:" + str + ":" + danCount + ":::" + data.toString());
    }

    /**
     * 计算十一选五中直选3的注数
     *
     * @param map1 第一行选中的集合
     * @param map2 第二行选中的集合
     * @param map3 第三行选中的集合
     * @return
     */
    public static int calculateZhix3Stage(Map<Integer, Boolean> map1, Map<Integer, Boolean> map2, Map<Integer, Boolean> map3) {
        int line1Length = 0;//第一行的长度
        int line2Length = 0;
        int line3Length = 0;
        int same13 = 0;//13两行中持有的相同数字个数
        int same23 = 0;
        int same12 = 0;
        int same123 = 0;//123三行持有相同数字的个数
        int stage;//总串数
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        for (int m = 0; m < 11; m++) {
            if (map1.get(m)) {
                line1Length++;
                list1.add(m);
            }
            if (map2.get(m)) {
                line2Length++;
                list2.add(m);
            }
            if (map3.get(m)) {
                line3Length++;
                list3.add(m);
            }
        }

        for (int i = 0; i < list1.size(); i++) {
            for (int m = 0; m < list2.size(); m++) {
                if (list1.get(i) == list2.get(m)) {
                    same12++;
                    for (int n = 0; n < list3.size(); n++) {
                        if (list1.get(i) == list3.get(n)) {
                            same123++;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < list2.size(); i++) {
            for (int m = 0; m < list3.size(); m++) {
                if (list2.get(i) == list3.get(m)) {
                    same23++;
                }
            }
        }
        for (int i = 0; i < list1.size(); i++) {
            for (int m = 0; m < list3.size(); m++) {
                if (list1.get(i) == list3.get(m)) {
                    same13++;
                }
            }
        }

        stage = line1Length * line2Length * line3Length - line1Length * same23 - line2Length * same13 - line3Length * same12 + 2 * same123;
        return stage;
    }

    /**
     * 十一选五中普通玩法直选2串数的计算
     *
     * @param map1
     * @param map2
     * @return
     */
    public static int calculateZhix2Stage(Map<Integer, Boolean> map1, Map<Integer, Boolean> map2) {
        int line1Length = 0;//第一行的长度
        int line2Length = 0;
        int same12 = 0;
        int stage;//总串数
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int m = 0; m < 11; m++) {
            if (map1.get(m)) {
                line1Length++;
                list1.add(m);
            }
            if (map2.get(m)) {
                line2Length++;
                list2.add(m);
            }
        }

        for (int i = 0; i < list1.size(); i++) {
            for (int m = 0; m < list2.size(); m++) {
                if (list1.get(i) == list2.get(m)) {
                    same12++;
                }
            }
        }

        stage = line1Length * line2Length - same12;
        return stage;
    }


    /**
     * 从a个数字中选择m个数字
     *
     * @param a
     * @param m
     * @return
     */
    public static List<List<Integer>> combine(List<Integer> a, int m) {
        int n = a.size();
        if (m > n) {
            return null;
//            throw new OurException("错误！数组a中只有"+n+"个元素。"+m+"大于"+2+"!!!");
        }

        List<List<Integer>> result = new ArrayList();

        int[] bs = new int[n];
        for (int i = 0; i < n; i++) {
            bs[i] = 0;
        }
        //初始化
        for (int i = 0; i < m; i++) {
            bs[i] = 1;
        }
        boolean flag = true;
        boolean tempFlag = false;
        int pos = 0;
        int sum = 0;
        //首先找到第一个10组合，然后变成01，同时将左边所有的1移动到数组的最左边
        do {
            sum = 0;
            pos = 0;
            tempFlag = true;
            result.add(printSfc(bs, a, m));

            for (int i = 0; i < n - 1; i++) {
                if (bs[i] == 1 && bs[i + 1] == 0) {
                    bs[i] = 0;
                    bs[i + 1] = 1;
                    pos = i;
                    break;
                }
            }
            //将左边的1全部移动到数组的最左边
            for (int i = 0; i < pos; i++) {
                if (bs[i] == 1) {
                    sum++;
                }
            }
            for (int i = 0; i < pos; i++) {
                if (i < sum) {
                    bs[i] = 1;
                } else {
                    bs[i] = 0;
                }
            }

            //检查是否所有的1都移动到了最右边
            for (int i = n - m; i < n; i++) {
                if (bs[i] == 0) {
                    tempFlag = false;
                    break;
                }
            }
            if (tempFlag == false) {
                flag = true;
            } else {
                flag = false;
            }

        } while (flag);
        result.add(printSfc(bs, a, m));

        return result;
    }

    private static List<Integer> printSfc(int[] bs, List<Integer> a, int m) {
        List result = new ArrayList();
        int pos = 0;
        for (int i = 0; i < bs.length; i++) {
            if (bs[i] == 1) {
                result.add(pos, a.get(i));
                pos++;
            }
        }
        return result;
    }

    /**
     * 计算任选九注数
     *
     * @param unDanList 非胆且已选中的所在行长度集合
     * @param danList   胆所在行长度集合
     * @return
     */
    public static int getRenxuan9Count(List<Integer> unDanList, List<Integer> danList) {
        int sum = 0;
        int total;
        int danTotal;
        if (null != danList) {
            danTotal = 1;
            for (int m = 0; m < danList.size(); m++) {
                danTotal = danTotal * danList.get(m);
            }
        } else {
            danTotal = 1;
        }
        List<List<Integer>> list = combine(unDanList, 9 - danList.size());

        if (null != list) {
            Logger.e(list.size() + "list的长度___total");
            sum = 0;
            for (int i = 0; i < list.size(); i++) {
                total = 1;
                for (int j = 0; j < list.get(i).size(); j++) {
                    total = total * list.get(i).get(j);
                    Logger.e(total + "____total");
                }
                sum += total;
                Logger.e(sum + "____ sum  total");
            }
            return sum * danTotal;
        }
        return 0;
    }

    public static int getRenxuan9Count1(List<Integer> unDanList, List<Integer> danList) {
        int sum = 0;
        int total;
        int danTotal;
        if (null != danList) {
            danTotal = 1;
            for (int m = 0; m < danList.size(); m++) {
                danTotal = danTotal * danList.get(m);
            }
        } else {
            danTotal = 1;
        }
        if (9 - danList.size() <= unDanList.size()) {
            for (List<Integer> list : Combination.of(unDanList, 9 - danList.size())) {
                total = 1;
                for (int i = 0; i < list.size(); i++) {
                    total *= list.get(i);
                }
                sum += total;
            }
            return sum * danTotal;
        }


      /*  List<List<Integer>> list = combine(unDanList, 9 - danList.size());

        if (null != list) {
            Logger.e(list.size() + "list的长度___total");
            sum = 0;
            for (int i = 0; i < list.size(); i++) {
                total = 1;
                for (int j = 0; j < list.get(i).size(); j++) {
                    total = total * list.get(i).get(j);
                    Logger.e(total + "____total");
                }
                sum += total;
                Logger.e(sum + "____ sum  total");
            }
            return sum * danTotal;
        }*/
        return 0;
    }
}


