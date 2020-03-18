package com.lingyun.weibo.classes.home.adpater;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lingyun.weibo.R;

import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class GridLayoutAdpater extends BaseQuickAdapter<String, BaseViewHolder> {
    public GridLayoutAdpater( @Nullable List<String> data) {
        super(R.layout.layout_adpater_gridview, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

       ImageView imageView =  helper.getView(R.id.grid_item_imageView);
        Glide.with(mContext)
                .load(item)
                .into(imageView);
    }
}
