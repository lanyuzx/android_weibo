package com.lingyun.weibo.utils;

import android.util.Log;

import com.lingyun.weibo.BuildConfig;


/**
 * 日志打印类
 */
public class LogUtil {

    /**
     * 是否打印日志
     */
    private static boolean isDebug = BuildConfig.DEBUG;
    private static String TAG = "jlc_tag";

    public static void v(String tag, String msg) {
        if (isDebug) {
            Log.v(tag, msg != null ? msg : "");
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg != null ? msg : "");
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg != null ? msg : "");
        }
    }

    public static void w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, msg != null ? msg : "");
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg != null ? msg : "");
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            Log.v(TAG, msg != null ? msg : "");
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, msg != null ? msg : "");
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg != null ? msg : "");
        }
    }

    public static void w(String msg) {
        if (isDebug) {
            Log.w(TAG, msg != null ? msg : "");
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg != null ? msg : "");
        }
    }
}
