package com.project.levi.news.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.project.levi.news.R;
import com.project.levi.news.data.models.Page;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by VyTK1 on 7/4/2016.
 */

public class PageViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.textName)
    TextView mTextName;
    @BindView(R.id.textAbout)
    TextView mTextAbout;

    public PageViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Page page){
        mTextName.setText(page.getName());
        mTextAbout.setText(page.getAbout());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Position : " + page.getId(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
