package com.lingyun.weibo.classes.home.adpater;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.lingyun.weibo.R;

import androidx.core.widget.PopupWindowCompat;

public class RetweetPopWindow extends PopupWindow {
    private Context mContext;
    public RetweetPopWindow(Context context) {
        super(context);
        mContext = context;
        setWindowBackgroundAlpha();
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(getDrawable());
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_retweet_popwindow,
                null, false);
        setContentView(contentView);
//        showBackgroundAnimator();
    }

    /**
     * 控制窗口背景的不透明度
     */
    private void setWindowBackgroundAlpha() {
        if (mContext == null) return;
        if (mContext instanceof Activity) {
            Window window = ((Activity) mContext).getWindow();

            WindowManager.LayoutParams lp = window
                    .getAttributes();
            lp.alpha = 1f;
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setAttributes(lp);

            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.alpha = 0.8f;
            window.setAttributes(layoutParams);
            setBackgroundDrawable(new BitmapDrawable());
            setOnDismissListener(new PopupWindow.OnDismissListener() {
                // 在dismiss中恢复透明度
                public void onDismiss() {
                    WindowManager.LayoutParams lp = window
                            .getAttributes();
                    lp.alpha = 1f;
                    window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    window.setAttributes(lp);
                }
            });
        }
    }


    public static RetweetPopWindow show(Context mContext ,View view) {
        RetweetPopWindow popWindow = new RetweetPopWindow(mContext);
        View contentView = popWindow.getContentView();
        //需要先测量，PopupWindow还未弹出时，宽高为0
        contentView.measure(makeDropDownMeasureSpec(popWindow.getWidth()),
                makeDropDownMeasureSpec(popWindow.getHeight()));
        int offsetX =  Math.abs(popWindow.getContentView().getMeasuredWidth()-view.getWidth()) / 2;
        int offsetY = -(popWindow.getContentView().getMeasuredHeight()+view.getHeight()) - 10;
        PopupWindowCompat.showAsDropDown(popWindow, view, offsetX, offsetY, Gravity.START);
        return popWindow;
    }


    @SuppressWarnings("ResourceType")
    private static int makeDropDownMeasureSpec(int measureSpec) {
        int mode;
        if (measureSpec == ViewGroup.LayoutParams.WRAP_CONTENT) {
            mode = View.MeasureSpec.UNSPECIFIED;
        } else {
            mode = View.MeasureSpec.EXACTLY;
        }
        return View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(measureSpec), mode);
    }

    private Drawable getDrawable(){
        ShapeDrawable bgdrawable =new ShapeDrawable(new OvalShape());
        bgdrawable.getPaint().setColor(0x666666);
        return   bgdrawable;
    }


}
