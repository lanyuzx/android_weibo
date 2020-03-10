package com.lingyun.weibo.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    private static Toast toast = null;

    public static final int SHOW = 0;
    public static final int LONG = 1;

    public static void show(Context context, String msg) {
        if (context != null) {
            if (toast == null) {
                toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            toast.show();
        }
    }

    public static void show(Context context, String msg, int time) {
        if (context != null) {
            if (toast == null) {
                toast = Toast.makeText(context, msg, time);
            } else {
                toast.setText(msg);
            }
            toast.show();
        }
    }
}
