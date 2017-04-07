package com.kazekim.techcrunch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.kazekim.techcrunch.R;
import com.kazekim.techcrunch.datahelper.NewsDataHelper;
import com.kazekim.techcrunch.datatype.MutableInteger;
import com.kazekim.techcrunch.model.NewsDao;
import com.kazekim.techcrunch.viewholder.NewsViewHolder;

import java.util.ArrayList;

/**
 * Created by Jirawat Kim Harnsiriwatanakit on 4/7/2017 AD.
 * Contact : jirawat.h@kazekim.com
 */

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    /**
     * Variables
     */

    MutableInteger lastPositionInteger;
    private NewsDataHelper dataHelper;
    private Context mContext;

    private OnRecyclerViewItemClickedListener onRecyclerViewItemClickedListener;

    public interface OnRecyclerViewItemClickedListener {
        void onRecyclerViewItemClicked(NewsViewHolder viewHolder, int position);
    }

    /**
     * Methods
     */

    public NewsRecyclerAdapter(Context context) {
        this.mContext = context;
        lastPositionInteger = new MutableInteger(0);


    }

    public void setOnRecyclerViewItemClickedListener(OnRecyclerViewItemClickedListener listener) {
        this.onRecyclerViewItemClickedListener = listener;
    }

    public ArrayList<NewsDao> getNewsList()
    {
        return (ArrayList<NewsDao>)dataHelper.getNewsList();
    }

    public void setNewsDataHelper(NewsDataHelper dataHelper) {
        this.dataHelper = dataHelper;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_news, viewGroup, false);
        NewsViewHolder pvh = new NewsViewHolder(v);

        return pvh;
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, final int position) {

        holder.setTitle(dataHelper.getNewsAtPosition(position).getTitle());
        holder.setDetail(dataHelper.getNewsAtPosition(position).getDescription());
//        holder.setImageUrl(mContext, dataHelper.getNewsAtPosition(position).getUrlToImage());
        holder.setDate(dataHelper.getNewsAtPosition(position).getPublishedAt());
        holder.getListItemWrap().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onRecyclerViewItemClickedListener != null)
                    onRecyclerViewItemClickedListener.onRecyclerViewItemClicked(holder, position);
            }
        });

        if(position > lastPositionInteger.getValue()) {
            Animation anim = AnimationUtils.loadAnimation(holder.getListItemWrap().getContext(),
                    R.anim.up_from_bottom);
            holder.getListItemWrap().startAnimation(anim);
            lastPositionInteger.setValue(position);
        }


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return getNewsList().size();
    }

}
