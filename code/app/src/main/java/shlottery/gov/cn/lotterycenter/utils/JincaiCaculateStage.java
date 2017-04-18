package shlottery.gov.cn.lotterycenter.utils;

import java.util.ArrayList;
import java.util.List;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.Jincai.DingdanItemBean;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/1 14:24
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class JincaiCaculateStage {
    //得到过关总注数
    public static int getCalculateStage(List<DingdanItemBean> dingdanList, List<String> chuanlianList) {
        if (null != dingdanList && null != chuanlianList && !chuanlianList.isEmpty()) {
            List<List<DingdanItemBean>> result = getAllItemCaculateStage(dingdanList, chuanlianList);
            if (null != result) {
                int sum = 0;
//            LogUtils.i("caluclateStage result  size" + result.size());
                for (int i = 0; i < result.size(); i++) {
                    int number = 1;
                    for (int j = 0; j < result.get(i).size(); j++) {
                        number *= result.get(i).get(j).getSpsList().size();
//                    LogUtils.i("caluclateStage result get size" + result.get(i).get(j).getSpsList().size());
                    }
                    sum += number;
                }
                return sum;
            }
            return 0;
        } else {
            return 0;
        }
    }

    //得到单关总注数
    public static int getDanCalculateStage(List<DingdanItemBean> dingdanList) {
        int count = 0;
        for (int i = 0; i < dingdanList.size(); i++) {
            count += dingdanList.get(i).getSpsList().size();
        }
        return count;
    }

    //得到所有串法的item排列集合
    public static List<List<DingdanItemBean>> getAllItemCaculateStage(List<DingdanItemBean> dingdanList, List<String> chuanlianList) {
        LogUtils.i("caluclateStage chuanlian size:" + dingdanList.size() + ":::" + chuanlianList.size() + ":::" + chuanlianList);
        if (null != chuanlianList && null != dingdanList) {
            List<List<DingdanItemBean>> result = new ArrayList<>();
            //从资源文件获得串联方式数组
            String[] chuanlianArr = BaseApplication.getApplication().getResources().getStringArray(R.array.dingdan_chuanlian);
            //每种串法的串联方式集合，用于计算3串3,5串10等
            for (int i = 0; i < chuanlianList.size(); i++) {
                List<List<DingdanItemBean>> list = new ArrayList<>();
                if (null != chuanlianList.get(i)) {
                    switch (chuanlianList.get(i)) {
                        case "3串3":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[0]));
                            break;
                        case "3串4":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[0]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[1]));
                            break;
                        case "4串4":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[1]));
                            break;
                        case "4串5":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[1]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[2]));
                            break;
                        case "4串6":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[0]));
                            break;
                        case "4串11":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[0]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[1]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[2]));
                            break;
                        case "5串5":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[2]));
                            break;
                        case "5串6":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[2]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[3]));
                            break;
                        case "5串10":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[0]));
                            break;
                        case "5串16":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[1]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[2]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[3]));
                            break;
                        case "5串20":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[0]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[1]));
                            break;
                        case "5串26":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[0]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[1]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[2]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[3]));
                            break;
                        case "6串6":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[3]));
                            break;
                        case "6串7":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[3]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[4]));
                            break;
                        case "6串15":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[0]));
                            break;
                        case "6串20":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[1]));
                            break;
                        case "6串22":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[2]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[3]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[4]));
                            break;
                        case "6串35":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[0]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[1]));
                            break;
                        case "6串42":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[1]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[2]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[3]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[4]));
                            break;
                        case "6串50":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[0]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[1]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[2]));
                            break;
                        case "6串57":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[0]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[1]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[2]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[3]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[4]));
                            break;
                        case "7串7":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[4]));
                            break;
                        case "7串8":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[4]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[5]));
                            break;
                        case "7串21":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[3]));
                            break;
                        case "7串35":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[2]));
                            break;
                        case "7串120":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[0]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[1]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[2]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[3]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[4]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[5]));
                            break;
                        case "8串8":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[5]));
                            break;
                        case "8串9":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[5]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[6]));
                            break;
                        case "8串28":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[4]));
                            break;
                        case "8串56":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[3]));
                            break;
                        case "8串70":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[2]));
                            break;
                        case "8串247":
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[0]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[1]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[2]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[3]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[4]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[5]));
                            list.addAll(getCalculateItemStare(dingdanList, chuanlianArr[6]));
                            break;
                        default:
                            list = getCalculateItemStare(dingdanList, chuanlianList.get(i));
                            break;
                    }
                }
                if (null != list) {
                    result.addAll(list);
                }
            }
            return result;
        } else {
            return null;
        }
    }

    //计算单独一种串法的得到的item排列集合,并且进行同步,防止同时遍历数据导致异常
    private synchronized static List<List<DingdanItemBean>> getCalculateItemStare(List<DingdanItemBean> dingdanList, String chuanlian) {
        /**
         * 从n个数字中选择m个数字
         * @param a
         * @param m
         * @return
         */
        int m = 0;
        int danCount = 0;
        //得到订单列表定胆数量
        for (DingdanItemBean bean : dingdanList) {
            if (bean.isDan()) {
                danCount++;
            }
        }
        switch (chuanlian) {
            case "2串1":
                m = 2;
                break;
            case "3串1":
                m = 3;
                break;
            case "4串1":
                m = 4;
                break;
            case "5串1":
                m = 5;
                break;
            case "6串1":
                m = 6;
                break;
            case "7串1":
                m = 7;
                break;
            case "8串1":
                m = 8;
                break;
            default:
                break;
        }
        int n = dingdanList.size();
        if (m > n) {
            LogUtils.i("错误！数组a中只有" + n + "个元素。" + m + "大于" + n + "!!!");

        } else {
            List<List<DingdanItemBean>> result = new ArrayList();
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
                sum = 0;
                pos = 0;
                tempFlag = true;
                result.add(print(bs, dingdanList, m));

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
            } while (flag);
            if (isChange) {
                result.add(print(bs, dingdanList, m));
            }

            //得到定胆的结果
            for (int i = 0; i < result.size(); i++) {
                int count = 0;
                for (int j = 0; j < result.get(i).size(); j++) {
                    if (result.get(i).get(j).isDan()) {
                        count++;
                    }
                }
                LogUtils.i("caluclateStage result 得到定胆数量:" + count + ":::" + i+"::::"+result.get(i));
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

    private static List<DingdanItemBean> print(int[] bs, List<DingdanItemBean> dingdanList, int m) {
        List<DingdanItemBean> result = new ArrayList<>();
        int pos = 0;
        for (int i = 0; i < bs.length; i++) {
            if (bs[i] == 1) {
                result.add(dingdanList.get(i));
                pos++;
            }
        }
        return result;
    }


}
