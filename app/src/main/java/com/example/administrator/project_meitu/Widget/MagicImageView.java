package com.example.administrator.project_meitu.Widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/2/26.
 */

public class MagicImageView extends ImageView {

    float mScale = 1;
    float dis_x = 0;
    float dis_y = 0;

    public float getDis_x() {
        return dis_x;
    }

    public void setDis_x(float dis_x) {
        this.dis_x = dis_x;
    }

    public float getDis_y() {
        return dis_y;
    }

    public void setDis_y(float dis_y) {
        this.dis_y = dis_y;
    }

    public float getmScale() {
        return mScale;
    }

    public void setmScale(float mScale) {
        this.mScale = mScale;
    }

    public MagicImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Matrix cm = new Matrix();
        float[] array = {1 * mScale, 0, dis_x, 0, 1 * mScale, dis_y, 0, 0, 1};
        cm.setValues(array);
        canvas.setMatrix(cm);
        super.onDraw(canvas);
    }

    float last_x = -1;
    float last_y = -1;
    float baseValue;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            baseValue = 0;
            float x = last_x = event.getRawX();
            float y = last_y = event.getRawY();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (event.getPointerCount() == 2) {
                float x = event.getX(0) - event.getX(1);
                float y = event.getY(0) - event.getY(1);
                float value = (float) Math.sqrt(x * x + y * y);// 计算两点的距离
                if (baseValue == 0) {
                    baseValue = value;
                } else {
                    if (value - baseValue >= 10 || value - baseValue <= -10) {
                        float scale = value / baseValue;// 当前两点间的距离除以手指落下时两点间的距离就是需要缩放的比例。
                        img_scale(scale);
                    }
                }
            } else if (event.getPointerCount() == 1) {

                float x = event.getRawX();
                float y = event.getRawY();

                x -= last_x;
                y -= last_y;

                if (x >= 10 || y >= 10 || x <= -10 || x <= -10) {
                    img_transport(x, y);
                }
                last_x = event.getRawX();
                last_y = event.getRawY();
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
        }
        return true;
    }

    private void img_scale(float scale) {
        mScale *= scale;
        if (mScale >= 2)
            mScale = 2;
        if (mScale <= 0.5)
            mScale = 0.5f;

        this.invalidate();
    }

    private void img_transport(float x, float y) {
        dis_x += x;
        dis_y += y;
        if (dis_x >= 400) {
            dis_x = 400;
        } else if (dis_x <= -400) {
            dis_x = -400;
        }

        if (dis_y >= 200) {
            dis_y = 200;
        } else if (dis_y <= -200) {
            dis_y = -200;
        }
        this.invalidate();
    }

}
