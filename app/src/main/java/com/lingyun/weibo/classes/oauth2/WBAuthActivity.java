package com.lingyun.weibo.classes.oauth2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.lingyun.weibo.R;
import com.lingyun.weibo.base.BaseActivity;
import com.lingyun.weibo.classes.oauth2.model.WBAuthModel;
import com.lingyun.weibo.constant.Constants;
import com.lingyun.weibo.helper.LoadingDialog;
import com.lingyun.weibo.helper.TokenHelper;
import com.lingyun.weibo.http.WeiBoHttp;
import com.lingyun.weibo.utils.LogUtil;
import com.lingyun.weibo.utils.ToastUtil;
import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class WBAuthActivity extends BaseActivity {

    @BindView(R.id.wbauth_webview)
    WebView mWebView;

    private String mWebUrl = "https://api.weibo.com/oauth2/authorize?client_id=" + Constants.APP_KEY + "&redirect_uri=" + Constants.REDIRECT_URL;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wbauth;
    }

    @Override
    protected void setupData() {
        mWebView.loadUrl(mWebUrl);
        mWebView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (url.equals(mWebUrl)){
                    return;
                }
                LoadingDialog.start();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (url.equals(mWebUrl)) {
                   return;
                }
                //授权完成获取重定向的key
                LogUtil.e(url);
                if (url != null) {
                    String[] strings = url.split("=");
                    if (strings.length != 2) {
                        return;
                    }
                    String code =  strings[1];
                    mWebView.setVisibility(View.GONE);
                    LogUtil.e(code);
                    setupAceessToken(code);
                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                LoadingDialog.hidden();
            }
        });
    }

    @Override
    protected void setupView() {
        //声明WebSettings子类
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

    }


    private void setupAceessToken(String code) {
        WeiBoHttp.oauth2AccessToken(code).subscribe(new Consumer<WBAuthModel>() {
            @Override
            public void accept(WBAuthModel model) throws Exception {
             LogUtil.e(model.toString());
             //token保存到本地
                TokenHelper.setToken(model.getAccess_token());
                WBAuthActivity.this.finish();
                LoadingDialog.hidden();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                LoadingDialog.hidden();
                LogUtil.e(throwable.getMessage());
                ToastUtil.show(mContext,"授权失败,请重新操作");
                mWebView.setVisibility(View.VISIBLE);
                mWebView.loadUrl(mWebUrl);
            }
        });
    }


}
