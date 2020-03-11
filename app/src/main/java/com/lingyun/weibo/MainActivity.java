package com.lingyun.weibo;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lingyun.weibo.base.BaseActivity;
import com.lingyun.weibo.classes.home.model.HomeModel;
import com.lingyun.weibo.helper.TokenHelper;
import com.lingyun.weibo.http.WeiBoHttp;
import com.lingyun.weibo.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;

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

        Map map = new HashMap();
        map.put("access_token", TokenHelper.getToken());
        WeiBoHttp.userAttentionList(map).subscribe(new Consumer<HomeModel>() {
            @Override
            public void accept(HomeModel homeModel) throws Exception {

                LogUtil.e("1");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                LogUtil.e("2");
            }
        });
    }

    @Override
    protected void setupView() {

    }

    @OnClick(R.id.main_auth)
    public void viewClick(View view){
        switch (view.getId()){
            case R.id.main_auth:

                break;
        }
    }
}
