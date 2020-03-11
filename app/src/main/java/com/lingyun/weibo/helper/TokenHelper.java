package com.lingyun.weibo.helper;

import com.lingyun.weibo.base.BaseActivity;
import com.lingyun.weibo.utils.SharedPreferencesUtil;
import com.lingyun.weibo.utils.StringUtil;

public class TokenHelper {
    final static String mTokenKey = "mAccessTokenKey";

    public static String getToken() {
        return SharedPreferencesUtil.getPreferencesByKey(BaseActivity.mContext, mTokenKey);
    }

    public static  void setToken(String token) {
        if (StringUtil.isBlank(token)){
            return;
        }
        SharedPreferencesUtil.setPreferencesByKey(BaseActivity.mContext,mTokenKey,token);
    }
}


