package com.example.xyzreader.ui;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;


public class DynamicImageView extends AppCompatImageView {

private float aspectRatio = 1f;
    public DynamicImageView(Context context) {
        super(context);
    }

    public DynamicImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DynamicImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAspectRatio(float value){
        this.aspectRatio=value;
        requestLayout();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int newHeight= (int) (MeasureSpec.getSize(widthMeasureSpec)/aspectRatio);
        int value = MeasureSpec.makeMeasureSpec(newHeight,MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, value);
    }
}
