package com.project.levi.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.levi.news.R;
import com.project.levi.news.adapter.listener.OnItemSelectListener;
import com.project.levi.news.adapter.viewholder.PageViewHolder;
import com.project.levi.news.data.models.Page;

import java.util.List;

/**
 * Created by VyTK1 on 7/4/2016.
 */

public class PageAdapter extends RecyclerView.Adapter<PageViewHolder> {

    private List<Page> mPageList;
    private OnItemSelectListener<Page> mOnItemSelectListener;

    public PageAdapter(OnItemSelectListener<Page> onItemSelectListener) {
        mOnItemSelectListener = onItemSelectListener;
    }

    @Override
    public PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.page_list_item,
                parent, false);
        return new PageViewHolder(view, mOnItemSelectListener);
    }

    @Override
    public void onBindViewHolder(PageViewHolder holder, int position) {
        Page page = mPageList.get(position);
        holder.bind(page);
    }

    @Override
    public int getItemCount() {
        return mPageList == null ? 0 : mPageList.size();
    }

    public void setPageList(List<Page> pageList) {
        mPageList = pageList;
    }
}
