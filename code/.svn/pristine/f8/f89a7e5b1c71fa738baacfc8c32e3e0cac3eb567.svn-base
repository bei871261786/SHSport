package shlottery.gov.cn.lotterycenter.utils;

import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;

/**
 * Created by booob on 2016-07-07-0007.
 */
public class DingDanStringUtils {

    public static String[] s1 = new String[]{"2串1", "3串1", "4串1", "5串1", "6串1", "7串1", "8串1"};
    public static String[] s0 = new String[]{};
    String[] s2 = new String[]{"单关", "2串1", "3串1", "4串1", "5串1", "6串1", "7串1", "8串1"};
    public static String[][] s3 = new String[][]{{"3串3", "3串4"}, {"4串4", "4串5", "4串6", "4串11"}, {"5串5", "5串6", "5串10", "5串16", "5串20", "5串26"}, {"6串6", "6串7", "6串15", "6串20", "6串22", "6串35", "6串42", "6串50", "6串57"}, {"7串7", "7串8", "7串21", "7串35", "7串120"}, {"8串8", "8串9", "8串28", "8串56", "8串70", "8串247"}};

    /**
     * @param position gridview点击的position
     * @return
     */
    public static String getChuanLianStringText(int position) {
        return s1[position];
    }

    /**
     * @param position 更多gridviewitem点击的position
     * @return
     */

    public static String getChuanlianStringText(int position) {
        String s = s1[position];
        return s;
    }

    public static String getChuanLianMoreStringText(int parentPosition, int position) {
        parentPosition = parentPosition > 5 ? 5 : parentPosition;
        if (parentPosition < 0)
            return "";
        else {
            return getString(parentPosition, position);
        }
    }

    private static String getString(int parentPosition, int position) {
        String s = null;
        for (int j = 0; j < s3[parentPosition].length; j++) {
            if (position == j) {
                s = s3[parentPosition][position];
            }
        }
        return s;
    }

    //根据mDingdanLength来获得串联方式状态集合的长度并且在这里进行判断,限制不同类型的串法上线
    public static int calculateCounts(int i, int mJingcaiType) {
        LogUtils.i("calculate by type" + mJingcaiType+":::"+i+":::"+BaseApplication.getCurrentLotId());
        int count;
        if (i > 0) {
            if (BaseApplication.getCurrentLotId().equals("jczq") && (mJingcaiType == Configure_JC.TAB_BF || mJingcaiType == Configure_JC.TAB_BQC) && i >= 4) {
                count = 3;
            } else if (BaseApplication.getCurrentLotId().equals("jclq") && (mJingcaiType == Configure_JC.TAB_SFC) && i >= 4) {
                count = 3;
            } else if (BaseApplication.getCurrentLotId().equals("jczq") && (mJingcaiType == Configure_JC.TAB_ZJQ) && i >= 6) {
                count = 5;
            } else if (i >= 8) {
                count = 7;
            } else {
                count = i - 1;
            }
        } else {
            count = 0;
        }
        return count;
    }

    //根据mDingDanSelectBean中的mMoreParentPosition来获得串联方式状态集合的长度.这里不需要判断,因为传进来的mMoreParentPosition已经限制过了
    public static int calculateMoreCounts(int i) {
        int count;
        if (i >= 0) {
            count = s3[i].length;
        } else {
            count = 0;
        }
        return count;
    }

    //得到sps值转化的代号
    public static String getStakeNumber(String optionValue) {
        String stakeNumber = "";
        String tempStr = null;
        if (!optionValue.isEmpty()) {
            LogUtils.i("payment getstakeNumber before:" + optionValue);
            optionValue = optionValue.replace("胜其他", "90");
            optionValue = optionValue.replace("平其他", "99");
            optionValue = optionValue.replace("负其他", "09");
            optionValue = optionValue.replace("胜", "3");
            optionValue = optionValue.replace("平", "1");
            optionValue = optionValue.replace("负", "0");

            String[] stakeNumbers = optionValue.split(",");
            for (int i = 0; i < stakeNumbers.length; i++) {
                tempStr = "";
                for (int j = 0; j < stakeNumbers[i].length(); j++) {
                    if (Character.isDigit(stakeNumbers[i].charAt(j))) {
                        tempStr += stakeNumbers[i].charAt(j);
                    }
                }
                if (!tempStr.isEmpty()) {
                    LogUtils.i("payment getstakeNumber middle          :" + tempStr);
                    stakeNumber += tempStr + ",";
                    LogUtils.i("payment getstakeNumber middle            :" + stakeNumber);
                }
            }
            stakeNumber = stakeNumber.substring(0, stakeNumber.lastIndexOf(","));

            LogUtils.i("payment getstakeNumber after       :" + stakeNumber);
        }
        return stakeNumber;
    }


    //得到sps值转化的代号
    public static String getBkStakeNumber(String optionValue) {
        String stakeNumber = "";
        String tempStr = null;
        LogUtils.i("payment getstakeNumber before 3         :" + optionValue);
        if (!optionValue.isEmpty()) {
            LogUtils.i("payment getstakeNumber before:" + optionValue);
            optionValue = optionValue.replace("主胜 1-5", "01");
            optionValue = optionValue.replace("主胜 6-10", "02");
            optionValue = optionValue.replace("主胜 11-15", "03");
            optionValue = optionValue.replace("主胜 16-20", "04");
            optionValue = optionValue.replace("主胜 21-25", "05");
            optionValue = optionValue.replace("主胜 26+", "06");
            optionValue = optionValue.replace("客胜 1-5", "11");
            optionValue = optionValue.replace("客胜 6-10", "12");
            optionValue = optionValue.replace("客胜 11-15", "13");
            optionValue = optionValue.replace("客胜 16-20", "14");
            optionValue = optionValue.replace("客胜 21-25", "15");
            optionValue = optionValue.replace("客胜 26+", "16");

            optionValue = optionValue.replace("主负", "0");

            optionValue = optionValue.replace("主胜", "3");
            optionValue = optionValue.replace("大分", "1");
            optionValue = optionValue.replace("小分", "2");

            String[] stakeNumbers = optionValue.split(",");
            LogUtils.i("payment getstakeNumber before 2         :" + optionValue);
            for (int i = 0; i < stakeNumbers.length; i++) {
                tempStr = "";
                for (int j = 0; j < stakeNumbers[i].length(); j++) {
                    if (Character.isDigit(stakeNumbers[i].charAt(j))) {
                        tempStr += stakeNumbers[i].charAt(j);
                    }
                }
                LogUtils.i("payment getstakeNumber before          :" + tempStr);
                if (!tempStr.isEmpty()) {
                    LogUtils.i("payment getstakeNumber middle          :" + tempStr);
                    stakeNumber += tempStr + ",";
                    LogUtils.i("payment getstakeNumber middle            :" + stakeNumber);
                }
            }
            stakeNumber = stakeNumber.substring(0, stakeNumber.lastIndexOf(","));

            LogUtils.i("payment getstakeNumber after       :" + stakeNumber);
        }
        return stakeNumber;
    }

}
