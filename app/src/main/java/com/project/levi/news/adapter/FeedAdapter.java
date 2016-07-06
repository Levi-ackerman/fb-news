package com.project.levi.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.levi.news.R;
import com.project.levi.news.adapter.viewholder.FeedViewHolder;

/**
 * Created by Levi on 7/6/2016.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {
    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_list_item,parent,false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
