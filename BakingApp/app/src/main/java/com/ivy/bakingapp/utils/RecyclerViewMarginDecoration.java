package com.ivy.bakingapp.utils;

import android.graphics.Rect;
import android.support.annotation.IntRange;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RecyclerViewMarginDecoration extends RecyclerView.ItemDecoration {
    public static final int ORIENTATION_VERTICAL = 1;
    private final int columns;
    private int margin;
    private int orientation;

    public RecyclerViewMarginDecoration(int orientation,
                                        @IntRange(from = 0) int margin,
                                        @IntRange(from = 0) int columns) {
        this.margin = margin;
        this.columns = columns;
        this.orientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        if (orientation == ORIENTATION_VERTICAL) {
            outRect.bottom = margin;
            outRect.right = margin;
            if (position < columns) {
                outRect.top = margin;
            }
            if (position % columns == 0) {
                outRect.left = margin;
            }
        }
    }
}