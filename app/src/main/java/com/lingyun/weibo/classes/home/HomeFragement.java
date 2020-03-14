package com.lingyun.weibo.classes.home;

import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lingyun.weibo.R;
import com.lingyun.weibo.base.BaseFragement;
import com.lingyun.weibo.classes.home.adpater.HomeAdpater;
import com.lingyun.weibo.classes.home.model.HomeModel;
import com.lingyun.weibo.http.WeiBoHttp;
import com.lingyun.weibo.utils.LogUtil;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class HomeFragement extends BaseFragement {

    @BindView(R.id.nav_title)
    TextView mTitleView;
    @BindView(R.id.home_recyclerView)
    RecyclerView mRecyclerView;
    private HomeAdpater mHomeAdpater;
    @Override
    protected int getLayoutId() {
        return R.layout.fragement_home;
    }

    @Override
    protected void setupData() {
        WeiBoHttp.homeTimeLine().subscribe(new Consumer<HomeModel>() {
            @Override
            public void accept(HomeModel homeModel) throws Exception {
                mHomeAdpater.setNewData(homeModel.getStatuses());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                LogUtil.e(throwable.toString());
            }
        });
    }

    @Override
    protected void setupView() {
        mTitleView.setText("首页");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mHomeAdpater =  new HomeAdpater(null);
        mRecyclerView.setAdapter(mHomeAdpater);
    }
}
