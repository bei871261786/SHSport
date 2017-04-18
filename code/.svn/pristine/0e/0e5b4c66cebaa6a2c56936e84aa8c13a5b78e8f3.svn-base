package shlottery.gov.cn.lotterycenter.utils;

import android.content.Context;
import android.content.SharedPreferences;

import shlottery.gov.cn.lotterycenter.Base.Configure_JC;

/**
 * SharePreference封装
 *
 * @author bob
 * @date 2016-8-4
 */
public class PrefUtils {

    public static boolean getBoolean(Context ctx, String key, boolean defValue) {
        SharedPreferences sp = ctx.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void setBoolean(Context ctx, String key, boolean value) {
        SharedPreferences sp = ctx.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static void setString(Context ctx, String key, String value) {
        SharedPreferences sp = ctx.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context ctx, String key, String defValue) {
        SharedPreferences sp = ctx.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void setInt(Context ctx, String key, int value) {
        SharedPreferences sp = ctx.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();
    }

    public static int getInt(Context ctx, String key, int defValue) {
        SharedPreferences sp = ctx.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    //清除shredperference数据
    public static void clearShredData(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        sp.edit().remove(Configure_JC.HASPAYPWD_KEY).commit();
        sp.edit().remove(Configure_JC.AVAILMONEY_KEY).commit();
        sp.edit().remove(Configure_JC.VOUCHERNUM_KEY).commit();
        sp.edit().remove(Configure_JC.MUSERIMAGEURL_KEY).commit();
        sp.edit().remove(Configure_JC.USERNICKNAME_KEY).commit();
        sp.edit().remove(Configure_JC.REALNAME_KEY).commit();

    }

}
