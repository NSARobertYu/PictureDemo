package com.example.customlibrary.Wedgit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/2/24.
 */

public class CustomTextView extends View {
    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        //设置无锯齿
        p.setStyle(Paint.Style.STROKE);
        //设置画笔颜色 = 字体颜色
        p.setColor(Color.BLUE);
        p.setTextSize(20);

        //画布颜色
        canvas.drawColor(Color.GREEN);

        String text = "I am from CustomTextView";
        //设置在什么地方
        canvas.drawText(text,10,30,p);
        canvas.save();
        canvas.restore();
        invalidate();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(260,100);


    }
}
