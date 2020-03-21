package com.lingyun.weibo.classes.home;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lingyun.weibo.R;
import com.lingyun.weibo.base.BaseFragement;
import com.lingyun.weibo.classes.home.adpater.HomeAdpater;
import com.lingyun.weibo.classes.home.model.HomeModel;
import com.lingyun.weibo.helper.TokenHelper;
import com.lingyun.weibo.http.WeiBoHttp;
import com.lingyun.weibo.utils.LogUtil;
import com.lingyun.weibo.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class HomeFragement extends BaseFragement implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.nav_title)
    TextView mTitleView;
    @BindView(R.id.home_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.home_refresh)
    SmartRefreshLayout mRefreshLayout;
    private HomeAdpater mHomeAdpater;
    @Override
    protected int getLayoutId() {
        return R.layout.fragement_home;
    }

    @Override
    protected void setupData() {
        Map map = new HashMap();
        map.put("access_token", TokenHelper.getToken());
        map.put("page",mPageIndex);
        WeiBoHttp.homeTimeLine(map).subscribe(new Consumer<HomeModel>() {
            @Override
            public void accept(HomeModel homeModel) throws Exception {
                if (mPageIndex == 1) {
                    mHomeAdpater.setNewData(homeModel.getStatuses());
                    mRefreshLayout.finishRefresh();
                    return;
                }
                mHomeAdpater.addData(homeModel.getStatuses());
                mRefreshLayout.finishLoadMore();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mRefreshLayout.finishRefresh();
                mRefreshLayout.finishLoadMore();
                LogUtil.e(throwable.toString());
                ToastUtil.show(mContext,throwable.getMessage());
            }
        });
    }

    @Override
    protected void setupView() {
        mTitleView.setText("首页");
        mHomeAdpater =  new HomeAdpater(null);
        mRecyclerView.setAdapter(mHomeAdpater);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPageIndex ++;
        setupData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPageIndex = 1;
        setupData();
    }
}

