package com.lingyun.weibo.classes.home.adpater;

import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lingyun.weibo.R;
import com.lingyun.weibo.classes.home.model.HomeModel;
import com.lingyun.weibo.utils.DateUtil;
import com.lingyun.weibo.utils.LogUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class HomeAdpater extends BaseQuickAdapter<HomeModel.HomeStatusesModel, BaseViewHolder> {

    private GridLayoutAdpater mGridLayoutAdpater;
    private GridLayoutAdpater mLargeGridLayoutAdpater;
    public HomeAdpater( @Nullable List<HomeModel.HomeStatusesModel> data) {
        super(R.layout.layout_adpater_home, data);
        mGridLayoutAdpater  =  new  GridLayoutAdpater(null,false);
        mLargeGridLayoutAdpater =  new  GridLayoutAdpater(null,true);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeModel.HomeStatusesModel item) {

       ImageView iconImageView = helper.getView(R.id.home_adpater_icon);

        Glide.with(mContext)
                .load(item.getUser().getProfile_image_url())
                .bitmapTransform(new CropCircleTransformation(mContext))
                .into(iconImageView);

        ImageView vipImageView = helper.getView(R.id.home_adpater_vip);
        vipImageView.setVisibility(View.VISIBLE);
        vipImageView.setImageResource(R.mipmap.ic_avatar_enterprise_vip);

        ImageView levelImageView = helper.getView(R.id.home_adpater_level);
        Glide.with(mContext).load(R.mipmap.ic_common_icon_membership_level1).into(levelImageView);

        TextView titleView = helper.getView(R.id.home_adpater_title);
        titleView.setText(item.getUser().getScreen_name());


        TextView souceTextView = helper.getView(R.id.home_adpater_source);

        Document document = Jsoup.parse(item.getSource());
        String soure = document.select("a").text();
        try {
            Date date = new Date(item.getCreated_at());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String created_at = formatter.format(date);
            String formatText = DateUtil.getTimeFormatText(formatter.parse(created_at));
            souceTextView.setText(formatText + "  " + "来自" + soure );
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView contentView = helper.getView(R.id.home_adpater_content);
        contentView.setText(item.getText());

        LinearLayout linearLayout = helper.getView(R.id.home_adpater_retweeted);
        linearLayout.setVisibility(View.VISIBLE);
        TextView retweetedTextView = helper.getView(R.id.home_adpater_retweeted_title);
        RecyclerView picRecyclerView = helper.getView(R.id.home_adpater_retweeted_recyclerView);
        //是转发微博
        if (item.getRetweeted_status() != null) {
            linearLayout.setBackgroundColor(0xEFEFEF);
            retweetedTextView.setVisibility(View.VISIBLE);
            retweetedTextView.setText("@" + item.getRetweeted_status().getUser().getName() + ":" + item.getRetweeted_status().getText());
            if (item.getRetweeted_status().getPic_urls() == null){
                picRecyclerView.setVisibility(View.GONE);
                return;
            }
            picRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    outRect.set(10, 5,10,5);
                }
            });
            if (item.getRetweeted_status().getPic_urls().size() == 0){
                picRecyclerView.setVisibility(View.GONE);
                return;
            }
            List<String> picList = new ArrayList<>();
            for (Map map : item.getRetweeted_status().getPic_urls()) {
                picList.add((String) map.get("thumbnail_pic"));
            }
            if (picList.size() == 1) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 1);
                picRecyclerView.setLayoutManager(gridLayoutManager);
                picRecyclerView.setAdapter(mLargeGridLayoutAdpater);
                mLargeGridLayoutAdpater.setNewData(picList);
            }else  {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
                picRecyclerView.setLayoutManager(gridLayoutManager);
                picRecyclerView.setAdapter(mGridLayoutAdpater);
                mGridLayoutAdpater.setNewData(picList);
            }


        }else  { //不是转发微博
            linearLayout.setBackgroundColor(0xffffff);
            retweetedTextView.setVisibility(View.GONE);
            if (((List)item.getPic_urls()).size() == 0){
                picRecyclerView.setVisibility(View.GONE);
                return;
            }
            List<String> picList = new ArrayList<>();
            for (Map map : item.getPic_urls()) {
                picList.add((String) map.get("thumbnail_pic"));
            }
            picRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    outRect.set(10, 5,10,5);
                }
            });
            if (picList.size() == 1) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 1);
                picRecyclerView.setLayoutManager(gridLayoutManager);
                picRecyclerView.setAdapter(mLargeGridLayoutAdpater);
                mLargeGridLayoutAdpater.setNewData(picList);
            }else  {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
                picRecyclerView.setLayoutManager(gridLayoutManager);
                picRecyclerView.setAdapter(mGridLayoutAdpater);
                mGridLayoutAdpater.setNewData(picList);
            }
        }

    }
}
