package com.project.levi.news.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.project.levi.news.R;
import com.project.levi.news.common.Utils;

/**
 * Created by VyTK1 on 7/4/2016.
 */

public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private int mSpace;

    public SimpleDividerItemDecoration(Context context, int space) {
        if (context != null) {
            mDivider = ContextCompat.getDrawable(context, R.drawable.divider);
        }
        mSpace = space;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mDivider != null) {
            int left = (int) Utils.convertDpToPixel(72);
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        } else {
            super.onDrawOver(c, parent, state);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mSpace < 0) {
            super.getItemOffsets(outRect, view, parent, state);
        } else {
            int position = parent.getChildLayoutPosition(view);
            if(position == 0){
                outRect.top = mSpace;
                outRect.bottom = mSpace;
            }else {
                outRect.bottom = mSpace;
            }
        }
    }


}
