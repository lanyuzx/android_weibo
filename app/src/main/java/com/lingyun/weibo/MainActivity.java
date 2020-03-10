package com.lingyun.weibo;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lingyun.weibo.base.BaseActivity;
import com.lingyun.weibo.utils.LogUtil;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_auth)
    TextView textView;

    @Override
    protected int getLayoutId() {
        LogUtil.e("11111");
        return R.layout.activity_main;
    }

    @Override
    protected void setupData() {

    }

    @Override
    protected void setupView() {

    }

    @OnClick(R.id.main_auth)
    public void viewClick(View view){
        switch (view.getId()){
            case R.id.main_auth:
                this.toActivity(WBAuthActivity.class);
                break;
        }
    }
}
