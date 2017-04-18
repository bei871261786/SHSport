package shlottery.gov.cn.lotterycenter.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.orhanobut.logger.Logger;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;

import static android.content.Context.MODE_PRIVATE;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-25-0025 15:19
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class SharedPreferencesUtils {

    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "share_data";

    /**
     * 通过对象输出流和Base64编码来存储序列化对象
     */
    public static void putObject(String key, Object object) {
        LogUtils.i("SharedPreferencesUtils putObject" + key + "1:::" + object);
        SharedPreferences preferences = BaseApplication.getApplication().getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        // 创建字节输出流
        LogUtils.i("SharedPreferencesUtils putObject" + key + "2:::" + preferences);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            // 创建对象输出流，并封装字节流
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            LogUtils.i("SharedPreferencesUtils putObject" + key + "3:::" + oos);
            // 将对象写入字节流
            oos.writeObject(object);
            LogUtils.i("SharedPreferencesUtils putObject" + key + "4::::");
            // 将字节流编码成base64的字符窜
            String oAuth_Base64 = new String(Base64.encodeBase64(baos.toByteArray()));
            LogUtils.i("haredPreferencesUtils putObject" + key + "5:::" + oAuth_Base64);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, oAuth_Base64);
            editor.commit();
            LogUtils.i("SharedPreferencesUtils 保存成功" + key + "::::" + oAuth_Base64);
        } catch (IOException e) {
            // TODO Auto-generated
            LogUtils.e("haredPreferencesUtils putObject error" + key+":::"+e.getStackTrace()+":::"+e.toString());
        }
    }

    public static Object getObject(String key) {
        Object oAuth_1 = null;
        SharedPreferences preferences = BaseApplication.getApplication().getSharedPreferences(FILE_NAME,
                MODE_PRIVATE);
        String productBase64 = preferences.getString(key, "");

        //读取字节
        byte[] base64 = Base64.decodeBase64(productBase64.getBytes());

        //封装到字节流
        ByteArrayInputStream bais = new ByteArrayInputStream(base64);
        try {
            Logger.i("SharedPreferencesUtils getObject   try1");
            //再次封装
            ObjectInputStream bis = new ObjectInputStream(bais);
            Logger.i("SharedPreferencesUtils getObject   try2");
            try {
                //读取对象
                oAuth_1 = bis.readObject();
                Logger.i("SharedPreferencesUtils getObject   try3");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Logger.i("SharedPreferencesUtils 读取成功" + key + "::::" + oAuth_1 + ":::" + base64 + "::::" + bis);
        } catch (StreamCorruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return oAuth_1;
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object
     */
    public static void put(Context context, String key, Object object) {

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }

        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get(Context context, String key, Object defaultObject) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                MODE_PRIVATE);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }

}


