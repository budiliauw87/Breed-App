package com.liaudev.catbreedsapp.ui.banner;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.liaudev.catbreedsapp.R;
import com.zhpan.bannerview.BaseViewHolder;

/**
 * Created by Budiliauw87 on 2020-11-20.
 * liautech.com
 * Budiliauw87@gmail.com
 */
public class NetViewHolder extends BaseViewHolder<BannerData> {
    public NetViewHolder(@NonNull View itemView) {
        super(itemView);
        ImageView imageView = findView(R.id.banner_image);
    }
    @Override
    public void bindData(BannerData data, int position, int pageSize) {
        ImageView imageView = findView(R.id.banner_image);
        Glide.with(imageView).load(data.getUrlimg()).placeholder(R.drawable.ic_launcher_foreground).into(imageView);
    }
}
