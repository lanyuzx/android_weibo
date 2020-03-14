package com.lingyun.weibo.classes.home.adpater;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lingyun.weibo.R;
import com.lingyun.weibo.classes.home.model.HomeModel;
import com.lingyun.weibo.utils.GlideRoundTransformUtil;

import java.util.List;

public class HomeAdpater extends BaseQuickAdapter<HomeModel.HomeStatusesModel, BaseViewHolder> {
    public HomeAdpater( @Nullable List<HomeModel.HomeStatusesModel> data) {
        super(R.layout.layout_adpater_home, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeModel.HomeStatusesModel item) {

       ImageView iconImageView = helper.getView(R.id.home_adpater_icon);
        Glide.with(mContext)
                .load(item.getUser().getProfile_image_url())
                .transform(new CenterCrop(mContext),new GlideRoundTransformUtil(mContext,22))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .dontAnimate()
                .crossFade()
                .into(iconImageView);

        ImageView vipImageView = helper.getView(R.id.home_adpater_vip);
        Glide.with(mContext)
                .load(item.getUser().getProfile_url())
                .transform(new CenterCrop(mContext),new GlideRoundTransformUtil(mContext,5))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .dontAnimate()
                .crossFade()
                .into(vipImageView);

        TextView titleView = helper.getView(R.id.home_adpater_title);
        titleView.setText(item.getUser().getScreen_name());
    }
}
