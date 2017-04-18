package shlottery.gov.cn.lotterycenter.utils;

import java.util.HashSet;
import java.util.Random;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-20-0020 16:57
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class RandomUtils {

    private final static Random rd = new Random();
    private static final String INT = "0123456789";
    private static final String STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String ALL = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String randomStr(int length) {
        return random(length, RndType.STRING);
    }

    public static String randomInt(int length) {
        return random(length, RndType.INT);
    }

    /**
     * 随机指定范围内N个不重复的数
     * 在初始化的无重复待选数组中随机产生一个数放入结果中，
     * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换
     * 然后从len-2里随机产生下一个随机数，如此类推
     * @param max  指定范围最大值
     * @param min  指定范围最小值
     * @param n  随机数个数
     * @return int[] 随机数结果集
     */
    public static int[] randomCommon(int min,int max,int n){
        int len = max-min+1;

        if(max < min || n > len){
            return null;
        }

        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = min; i < min+len; i++){
            source[i-min] = i;
        }

        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            //待选数组0到(len-2)随机一个下标
            index = Math.abs(rd.nextInt() % len--);
            //将随机到的数放入结果集
            result[i] = source[index];
            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
            source[index] = source[len];
        }
        return result;
    }

    public static String randomAll(int length) {
        return random(length, RndType.ALL);
    }

    private static String random(int length, RndType rndType) {
        StringBuilder s = new StringBuilder();
        char num = 0;
        for (int i = 0; i < length; i++) {
            if (rndType.equals(RndType.INT))
                num = INT.charAt(rd.nextInt(INT.length()));//产生数字0-9的随机数
            else if (rndType.equals(RndType.STRING))
                num = STR.charAt(rd.nextInt(STR.length()));//产生字母a-z的随机数
            else {
                num = ALL.charAt(rd.nextInt(ALL.length()));
            }
            s.append(num);
        }
        return s.toString();
    }

    public static enum RndType {
        INT,
        STRING,
        ALL
    }
}