package com.example.app.wigdet.player;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.R;

/**
 * TODO: document your custom view class.
 */
public class VideoContainer extends ViewGroup {
    public VideoContainer(Context context) {
        super(context);
        init(null, 0);
    }

    public VideoContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public VideoContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    private void init(AttributeSet attrs, int defStyle) {

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }


}