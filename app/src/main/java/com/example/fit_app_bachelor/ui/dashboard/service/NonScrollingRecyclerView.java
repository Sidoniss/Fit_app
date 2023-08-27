package com.example.fit_app_bachelor.ui.dashboard.service;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class NonScrollingRecyclerView extends RecyclerView {
    public NonScrollingRecyclerView(Context context) {
        super(context);
    }

    public NonScrollingRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NonScrollingRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST));
    }
}
