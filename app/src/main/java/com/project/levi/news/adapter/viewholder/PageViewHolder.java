package com.project.levi.news.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.project.levi.news.R;
import com.project.levi.news.adapter.listener.OnItemSelectListener;
import com.project.levi.news.data.models.Page;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by VyTK1 on 7/4/2016.
 */

public class PageViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text_name)
    TextView mTextName;
    @BindView(R.id.text_about)
    TextView mTextAbout;
    @BindView(R.id.img_avatar)
    ImageView imgAvatar;

    private OnItemSelectListener<Page> mOnItemSelectListener;

    public PageViewHolder(View itemView, OnItemSelectListener<Page> onItemSelectListener) {
        super(itemView);
        this.mOnItemSelectListener = onItemSelectListener;
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Page page){
        mTextName.setText(page.getName());
        mTextAbout.setText(page.getAbout());

        Glide.with(itemView.getContext()).load(page.getPicture().getUrl())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgAvatar);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemSelectListener.onItemClick(page);
            }
        });
    }
}
