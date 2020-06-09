package com.gzy.paymentlistener;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class SpUtils {

    private static final String APP_CONFIG = "app_config";

    static final String URL = "sp_url";

    static final String PARAM_NAME = "parameter_name";

    /**
     * 存字符串
     */
    public static void putString(Context mContext, String key, String values) {
        SharedPreferences sp = mContext.getSharedPreferences(APP_CONFIG, Context.MODE_PRIVATE);
        sp.edit().putString(key, values).apply();
    }

    /**
     * 取字符串
     */
    public static String getString(Context mContext, String key, String values) {
        SharedPreferences sp = mContext.getSharedPreferences(APP_CONFIG, Context.MODE_PRIVATE);
        return sp.getString(key, values);
    }

    /**
     * 存字符串集合
     */
    public static void putStrings(Context mContext, String key, Set<String> values) {
        SharedPreferences sp = mContext.getSharedPreferences(APP_CONFIG, Context.MODE_PRIVATE);
        sp.edit().putStringSet(key, values).apply();
    }

    /**
     * 取字符串集合
     */
    public static Set<String> getStrings(Context mContext, String key) {
        SharedPreferences sp = mContext.getSharedPreferences(APP_CONFIG, Context.MODE_PRIVATE);
        return sp.getStringSet(key, null);
    }


    /**
     * 存布尔值
     */
    public static void putBoolean(Context mContext, String key, boolean values) {
        SharedPreferences sp = mContext.getSharedPreferences(APP_CONFIG, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, values).apply();
    }


    /**
     * 取布尔值
     */
    public static boolean getBoolean(Context mContext, String key, boolean values) {
        SharedPreferences sp = mContext.getSharedPreferences(APP_CONFIG, Context.MODE_PRIVATE);
        return sp.getBoolean(key, values);
    }


    /**
     * 存int值
     */
    public static void putInt(Context mContext, String key, int values) {
        SharedPreferences sp = mContext.getSharedPreferences(APP_CONFIG, Context.MODE_PRIVATE);
        sp.edit().putInt(key, values).apply();
    }

    /**
     * 取int值
     */
    public static int getInt(Context mContext, String key, int values) {
        SharedPreferences sp = mContext.getSharedPreferences(APP_CONFIG, Context.MODE_PRIVATE);
        return sp.getInt(key, values);
    }

    /**
     * 删除一条字段
     */
    public static void deleShare(Context mContext, String key) {
        SharedPreferences sp = mContext.getSharedPreferences(APP_CONFIG, Context.MODE_PRIVATE);
        sp.edit().remove(key).apply();
    }

    /**
     * 删除全部数据
     */
    public static void deleShareAll(Context mContext) {
        SharedPreferences sp = mContext.getSharedPreferences(APP_CONFIG, Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }
}