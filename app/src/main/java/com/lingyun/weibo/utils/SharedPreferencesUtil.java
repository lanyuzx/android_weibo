package com.lingyun.weibo.utils;

import android.content.Context;
import android.content.SharedPreferences;


public  class SharedPreferencesUtil {

    private static final String FILE_NAME = "FILE_NAME";

    public static  String getPreferencesByKey(Context context ,String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String result = sp.getString(key,"null");
        return result;
    }

    public  static  void  setPreferencesByKey(Context context ,String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,value);
        editor.commit();

    }


}
