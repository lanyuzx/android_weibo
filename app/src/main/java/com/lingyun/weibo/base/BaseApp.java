package com.lingyun.weibo.base;

import android.app.Application;

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        WbSdk.install(this,new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE));
    }

}


