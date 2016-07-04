package com.project.levi.news.ui;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by VyTK1 on 7/4/2016.
 */

public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;

    public SimpleDividerItemDecoration(int space) {
        mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        int position = parent.getChildLayoutPosition(view);
        // Add top margin only for the first item to avoid double space between items
        if (position == 0 || position == 1) {
            outRect.top = mSpace;
        } else {
            outRect.top = 0;
        }

        outRect.left = mSpace;
        outRect.right = mSpace;

        outRect.bottom = mSpace;
    }
}
