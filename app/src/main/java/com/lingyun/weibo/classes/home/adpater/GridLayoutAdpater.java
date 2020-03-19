package com.lingyun.weibo.classes.home.adpater;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lingyun.weibo.R;

import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class GridLayoutAdpater extends BaseQuickAdapter<String, BaseViewHolder> {
    private boolean  mIsOneImage;
    public GridLayoutAdpater( @Nullable List<String> data ,Boolean isOneImage) {
        super( isOneImage ? R.layout.layout_adpater_gridview_largeimage :R.layout.layout_adpater_gridview,data);
        mIsOneImage =  isOneImage;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (mIsOneImage) {
            ImageView imageView =  helper.getView(R.id.grid_item_large_imageView);
            Glide.with(this.mContext).load(item).dontAnimate().into(imageView);
            return;
        }
       ImageView imageView =  helper.getView(R.id.grid_item_imageView);
        Glide.with(mContext)
                .load(item)
                .dontAnimate()
                .into(imageView);
    }
}
