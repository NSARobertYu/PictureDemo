package com.example.customlibrary.Wedgit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.customlibrary.R;

/**
 * Created by Administrator on 2017/2/24.
 */

public class CustomImageView extends View {
    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        //设置画笔颜色 = 字体颜色
        p.setColor(Color.BLUE);
        p.setTextSize(20);
        //画布颜色
        canvas.drawColor(Color.GREEN);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        canvas.drawBitmap(bitmap,10,10,p);
        bitmap.recycle();

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
