package com.lingyun.weibo;

import android.content.Intent;

import com.lingyun.weibo.base.BaseActivity;
import com.lingyun.weibo.classes.oauth2.WBAuthActivity;
import com.lingyun.weibo.helper.TokenHelper;
import com.lingyun.weibo.utils.StringUtil;

import static java.lang.Thread.sleep;

public class LauncherActivity  extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_launcher;
    }

    @Override
    protected void setupData() {

        new Thread( new Runnable( ) {
            @Override
            public void run() {
                //耗时任务，比如加载网络数据
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 这里可以睡几秒钟，如果要放广告的话
                        try {
                            sleep(4000);
                            if (StringUtil.isBlank(TokenHelper.getToken())){
                                Intent intent = new Intent(mContext, WBAuthActivity.class);
                                startActivity(intent);
                                LauncherActivity.this.finish();
                                return;
                            }
                            Intent intent = new Intent(mContext,MainActivity.class);
                            startActivity(intent);
                            LauncherActivity.this.finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } ).start();
    }

    @Override
    protected void setupView() {

    }
}
