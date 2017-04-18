package shlottery.gov.cn.lotterycenter.utils;

import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/25 10:11
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class TextUtils {
    public static void setTextSpan(TextView textView, String str1) {
        int start;
        int end;
        SpannableStringBuilder word = new SpannableStringBuilder();
        word.append(str1);
        start = 0;
        end = str1.length();
        word.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), start, end, Spanned
                .SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(word);

    }


    //在字符串中指定某一部分的颜色
    public static void setStrColor(TextView textView, String str, String colorStr, int color) {
        //        if(textView!=null&&str!=null&&colorStr!=null)
        //        {
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        ForegroundColorSpan yellowSpan = new ForegroundColorSpan(color);
        int start = str.indexOf(colorStr);
        int end = start + colorStr.length();
        if (start != -1 && end <= str.length()) {
            LogUtils.i("textutil 有匹配项:" + start + "::" + colorStr + ":::" + end);
            builder.setSpan(yellowSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(builder);
        } else {
            LogUtils.i("textutil 没有匹配:");
            textView.setText(str);
        }
        //        }
    }

    //在字符串中指定某一部分的颜色和大小
    public static void setStrColorSize(TextView textView, String str, String colorStr, int color) {
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        ForegroundColorSpan yellowSpan = new ForegroundColorSpan(color);
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(16);

        int start = str.indexOf(colorStr);
        int end = start + colorStr.length();
        if (start != -1 && end <= str.length()) {
            LogUtils.i("textutil 有匹配项:" + start + "::" + colorStr + ":::" + end);
            builder.setSpan(yellowSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(span, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            textView.setText(builder);
        } else {
            LogUtils.i("textutil 没有匹配:");
            textView.setText(str);
        }
    }

    //在字符串中指定某一部分的颜色和大小
    public static void setStrColorSizeCenter(TextView textView, String str, String colorStr, int color) {
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        ForegroundColorSpan yellowSpan = new ForegroundColorSpan(color);
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(35);

        int start = str.indexOf(colorStr);
        int end = start + colorStr.length();
        if (start != -1 && end <= str.length()) {
            LogUtils.i("textutil 有匹配项:" + start + "::" + colorStr + ":::" + end);
            builder.setSpan(yellowSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(span, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            builder.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, str.length(), Spanned
                    .SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(builder);
        } else {
            LogUtils.i("textutil 没有匹配:");
            textView.setText(str);
        }
    }

    //检查数字，每三位加一个逗号
    public static String checkNumber(String number) {
        if (number != null && !number.isEmpty()) {
            LogUtils.i("checkNumber data:" + number);
            if (number.equals("0") || number.isEmpty() || number.equals("-1")) {
                return "--";
            }
            ArrayList<String> list = new ArrayList<>();
            ArrayList<String> temp = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < number.length(); i++) {
                list.add(number.charAt(i) + "");
            }
            temp.addAll(list);
            LogUtils.i("checkNumber temp:" + temp);
            Collections.reverse(list);
            Collections.reverse(temp);
            LogUtils.i("checkNumber reverse temp:" + temp);
            int count = 0;
            int addCount = 0;
            for (int i = 0; i < list.size(); i++) {
                count++;
                if (count == 3 && i < list.size() - 1 && i != 0) {
                    LogUtils.i("checkNumber   listAdd:" + i + ":::" + count);
                    temp.add((i + 1 + addCount), ",");
                    addCount++;
                    LogUtils.i("checkNumber   listAdd currentTemp:" + i + ":::" + temp);
                    count = 0;
                }
            }
            Collections.reverse(temp);
            LogUtils.i("checkNumber  result:" + temp);
            if (temp != null && !temp.isEmpty()) {

                for (int i = 0; i < temp.size(); i++) {
                    sb.append(temp.get(i));
                }
            }
            return sb.toString();
        }
        return null;
    }

    //检查数字，每三位加一个逗号
    public static String checkNumber(int number) {
        String numberStr;
        numberStr = number + "";
        return checkNumber(numberStr);
    }

    //检查数字，每三位加一个逗号
    public static String checkNumber(long number) {
        String numberStr;
        numberStr = number + "";
        return checkNumber(numberStr);
    }

    //检查是否没有数据或者数据为0
    public static String checkIsNull(String number) {
        if (number.equals("0") || number.isEmpty() || number.equals("-1")) {
            return "--";
        }
        return number;
    }

    //检查是否没有数据或者数据为0
    public static String checkIsNull(long number) {
        return checkIsNull(number + "");
    }

    //检查是否没有数据或者数据为0
    public static String checkIsNull(int number) {
        return checkIsNull(number + "");
    }

    /**
     * 格式化金额, 把科学计数法的数字转化为正常显示  1.2343423E7 --->12343423
     *
     * @param money
     * @return
     */
    public static String formatMoney(double money) {
        return NumberFormat.getInstance().format(money);
    }
}