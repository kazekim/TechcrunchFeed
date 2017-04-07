package com.kazekim.techcrunch.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kazekim.techcrunch.R;

/**
 * Created by Jirawat Kim Harnsiriwatanakit on 4/7/2017 AD.
 * Contact : jirawat.h@kazekim.com
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {
    private TextView tvTitle;
    private TextView tvDetail;
    private TextView tvDate;
    private ImageView ivImg;
    private RelativeLayout listItemWrap;

    public NewsViewHolder(View v) {
        super(v);
        this.listItemWrap = (RelativeLayout) v
                .findViewById(R.id.listItemWrap);
        this.tvTitle = (TextView) v
                .findViewById(R.id.tvNewsTitle);
        this.tvDetail = (TextView) v
                .findViewById(R.id.tvNewsDetail);
        this.tvDate = (TextView) v
                .findViewById(R.id.tvNewsDate);
        this.ivImg = (ImageView) v
                .findViewById(R.id.ivImg);
    }

    public void setImageUrl(Context context, String url)
    {
        //Glide Use RGB565
        //Glide can play GIF
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.defaultimage_thumb)
                //.error(R.drawable.mock)
                //DiskCacheStrategy.ALL cache all size (Use when image use many places)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivImg);
    }

    public RelativeLayout getListItemWrap() {
        return listItemWrap;
    }

    public void setTextViewWrap(RelativeLayout listItemWrap) {
        this.listItemWrap = listItemWrap;
    }

    public void setTitle(String title) {
        this.tvTitle.setText(title);
    }

    public void setDetail(String detail) {
        this.tvDetail.setText(detail);
    }

    public void setDate(String date) {
        this.tvDate.setText(date);
    }
}
