package com.lingyun.weibo;

import android.content.Intent;
import android.widget.TextView;

import com.lingyun.weibo.base.BaseActivity;
import com.lingyun.weibo.utils.LogUtil;
import com.lingyun.weibo.utils.ToastUtil;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import butterknife.BindView;

public class WBAuthActivity extends BaseActivity {

    private  Oauth2AccessToken  mAccessToken;
    private SsoHandler  mSsoHandler;

    @BindView(R.id.wbauth_token)
    TextView mWbauthToken;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wbauth;
    }

    @Override
    protected void setupData() {
        mSsoHandler = new SsoHandler(WBAuthActivity.this);
        mSsoHandler.authorize(new SelfWbAuthListener());
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
                        mWbauthToken.setText(mAccessToken.getToken());
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
            ToastUtil.show(mContext,errorMessage.getErrorMessage());
        }
    }
}
