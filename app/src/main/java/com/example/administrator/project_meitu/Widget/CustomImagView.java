package com.example.administrator.project_meitu.Widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by ethank on 16/9/12.
 */
public class CustomImagView extends ImageView {

    int color;
    int boradWidth;

    public void setColor(int color) {
        this.color = color;
    }

    public void setBoradWidth(int boradWidth) {
        this.boradWidth = boradWidth;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect rect = canvas.getClipBounds();
        rect.bottom--;
        rect.right--;
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(boradWidth);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect, paint);
    }

    public CustomImagView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
