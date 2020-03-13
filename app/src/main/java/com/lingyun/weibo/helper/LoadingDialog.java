package com.lingyun.weibo.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.lingyun.weibo.R;
import com.lingyun.weibo.base.BaseActivity;
import com.wang.avi.AVLoadingIndicatorView;

public class LoadingDialog extends AlertDialog {

    private static LoadingDialog loadingDialog;
    private AVLoadingIndicatorView mAviLoading;

    private static LoadingDialog getInstance() {
        if (loadingDialog == null){
            loadingDialog = new LoadingDialog(BaseActivity.mContext, R.style.TransparentDialog); //设置AlertDialog背景透明
            loadingDialog.setCancelable(false);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        return loadingDialog;
    }

    private LoadingDialog(Context context, int themeResId) {
        super(context,themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_loading);
        mAviLoading = (AVLoadingIndicatorView)this.findViewById(R.id.dialog_loading);
    }

    public static void   start() {
        LoadingDialog.getInstance().show();
    }

    public static void   hidden() {
        LoadingDialog.getInstance().dismiss();
    }

    @Override
    public void show() {
        super.show();
        mAviLoading.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mAviLoading.hide();
    }
}
