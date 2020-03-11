package com.lingyun.weibo;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingyun.weibo.base.BaseActivity;
import com.lingyun.weibo.helper.TokenHelper;
import com.lingyun.weibo.utils.LogUtil;
import com.lingyun.weibo.utils.SharedPreferencesUtil;
import com.lingyun.weibo.utils.StringUtil;
import com.lingyun.weibo.utils.ToastUtil;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import butterknife.BindView;

public class WBAuthActivity extends BaseActivity {

    @BindView(R.id.wbauth_imageview)
    ImageView mLauncherView;

    private  Oauth2AccessToken  mAccessToken;
    private SsoHandler  mSsoHandler;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wbauth;
    }

    @Override
    protected void setupData() {
        if (StringUtil.isBlank(TokenHelper.getToken())){
            mLauncherView.setVisibility(View.GONE);
            mSsoHandler = new SsoHandler(WBAuthActivity.this);
            mSsoHandler.authorize(new SelfWbAuthListener());
            return;
        }
        LogUtil.d("token =========" + TokenHelper.getToken());
        mLauncherView.setVisibility(View.VISIBLE);
        //当做启动页
        Integer time = 2000;    //设置等待时间，单位为毫秒
        Handler handler = new Handler();
        //当计时结束时，跳转至主界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toActivity(MainActivity.class);
                WBAuthActivity.this.finish();
            }
        }, time);

    }

    @Override
    protected void setupView() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    private class SelfWbAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener{
        @Override
        public void onSuccess(final Oauth2AccessToken token) {
            WBAuthActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAccessToken = token;
                    if (mAccessToken.isSessionValid()) {
                        //保存本地token
                        TokenHelper.setToken(mAccessToken.getToken());
                        toActivity(MainActivity.class);
                        return;
                    }
                    ToastUtil.show(mContext,"token失效");
                }
            });
        }

        @Override
        public void cancel() {
            ToastUtil.show(mContext,"撤销授权");
        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
            ToastUtil.show(mContext,errorMessage.getErrorCode());
        }
    }
}
