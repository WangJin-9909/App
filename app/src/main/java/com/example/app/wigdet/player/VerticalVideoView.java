package com.example.app.wigdet.player;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class VerticalVideoView extends VideoView {
    public VerticalVideoView(@NonNull Context context) {
        super(context);
    }

    public VerticalVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalVideoView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /*
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 处理
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }*/


}
