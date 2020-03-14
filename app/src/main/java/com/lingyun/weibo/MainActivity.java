package com.lingyun.weibo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lingyun.weibo.base.BaseActivity;
import com.lingyun.weibo.classes.home.HomeFragement;
import com.lingyun.weibo.classes.home.model.HomeModel;
import com.lingyun.weibo.classes.message.MessageFragement;
import com.lingyun.weibo.classes.mine.MineFragement;
import com.lingyun.weibo.classes.oauth2.WBAuthActivity;
import com.lingyun.weibo.discover.DiscoverFragement;
import com.lingyun.weibo.helper.TokenHelper;
import com.lingyun.weibo.http.WeiBoHttp;
import com.lingyun.weibo.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_bottomNavigationBar)
    BottomNavigationBar mBottomNavigationBar;

    private static List<Fragment> mFragmentList = new ArrayList<>();

    private static  List<BottomNavigationItem> mNavigationItemList = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupData() {

    }

    @Override
    protected void setupView() {
        setupBottomNavBar();
    }

    private void setupBottomNavBar() {
        mFragmentList.add(new HomeFragement());
        mFragmentList.add(new MessageFragement());
        mFragmentList.add(new DiscoverFragement());
        mFragmentList.add(new MineFragement());

        mNavigationItemList.add(new BottomNavigationItem(R.mipmap.ic_tabbar_home_highlighted,"首页").setInactiveIconResource(R.mipmap.ic_tabbar_home));
        mNavigationItemList.add(new BottomNavigationItem(R.mipmap.ic_tabbar_message_center_highlighted,"消息").setInactiveIconResource(R.mipmap.ic_tabbar_message_center));
        mNavigationItemList.add(new BottomNavigationItem(R.mipmap.ic_tabbar_discover_highlighted,"发现").setInactiveIconResource(R.mipmap.ic_tabbar_discover));
        mNavigationItemList.add(new BottomNavigationItem(R.mipmap.ic_tabbar_profile_highlighted,"我的").setInactiveIconResource(R.mipmap.ic_tabbar_profile));

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container, mFragmentList.get(0), "home")
                .add(R.id.main_container, mFragmentList.get(1), "message")
                .add(R.id.main_container, mFragmentList.get(2), "discover")
                .add(R.id.main_container, mFragmentList.get(3), "mine")
                .hide(mFragmentList.get(1))
                .hide(mFragmentList.get(2))
                .hide(mFragmentList.get(3))
                .show(mFragmentList.get(0))
                .commit();

        mBottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED) // 设置mode
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)  // 背景样式
                .setBarBackgroundColor(R.color.AppColor) // 背景颜色
                .setInActiveColor("#999999") // 未选中状态颜色
                .setActiveColor("#ffffff") // 选中状态颜色
                .addItem(mNavigationItemList.get(0)) // 添加Item
                .addItem(mNavigationItemList.get(1))
                .addItem(mNavigationItemList.get(2))
                .addItem(mNavigationItemList.get(3))
                .setFirstSelectedPosition(0) //设置默认选中位置
                .setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(int position) {
                        for (int i = 0 ;i< mFragmentList.size();i++) {
                            Fragment fragment =  mFragmentList.get(i);
                            if (position == i) {
                                getSupportFragmentManager().beginTransaction().show(fragment).commit();
                            }else  {
                                getSupportFragmentManager().beginTransaction().hide(fragment).commit();
                            }
                        }
                    }

                    @Override
                    public void onTabUnselected(int position) {

                    }

                    @Override
                    public void onTabReselected(int position) {

                    }
                })
                .initialise();  // 提交初始化（完成配置）
    }



}
