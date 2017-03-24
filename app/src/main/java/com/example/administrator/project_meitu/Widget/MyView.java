package com.example.administrator.project_meitu.Widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ethank on 16/9/26.
 */

public class MyView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private SurfaceHolder surfaceHolder;
    private int currentColor = Color.RED;
    private int textSize = 1;
    private Paint paint = null;
    private Path path = null;
    private Bitmap bitmap;

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        initPamras();
    }

    public void setCurrentColor(int currentColor) {
        this.currentColor = currentColor;
        initPamras();
    }

    public MyView(Context context) {
        super(context);
        if (surfaceHolder == null) {
            surfaceHolder = getHolder();
        }
        surfaceHolder.addCallback(this);
        initPamras();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (surfaceHolder == null) {
            surfaceHolder = getHolder();
        }
        surfaceHolder.addCallback(this);
        initPamras();
    }

    private void initPamras() {
        paint = new Paint();
        path = new Path();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(textSize);
        paint.setTextSize(20);
        paint.setColor(currentColor);

        setOnTouchListener(this);
    }

    public void setBackground(int id) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(id);
        Bitmap bm = bitmapDrawable.getBitmap();
        Matrix matrix = new Matrix();
        matrix.setScale(0.665f, 0.66f);
        bitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initPamras();
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                Action action = new Action();
                action.setPath(path);
                action.setPaint(paint);
                action.setDelete(true);
                list.add(action);
                draw(true);
                break;
            case MotionEvent.ACTION_UP:
                if (!list.isEmpty())
                    list.getLast().setDelete(false);
                draw(false);
                break;
        }
        return true;
    }

    public void draw(boolean isTogether) {
        Canvas canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(Color.GRAY);
        canvas.drawBitmap(bitmap, 0, 0, new Paint());

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Action a = list.get(i);
                if (!isTogether) {
                    if (a.isDelete) {
                        list.remove(i);
                        i--;
                    }
                }
                canvas.drawPath(a.getPath(), a.getPaint());
            }
        }
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    LinkedList<Action> list = new LinkedList<>();

    class Action {
        Path path;
        Paint paint;
        boolean isDelete;

        public boolean isDelete() {
            return isDelete;
        }

        public void setDelete(boolean delete) {
            isDelete = delete;
        }

        public void setPath(Path path) {
            this.path = path;
        }

        public void setPaint(Paint paint) {
            this.paint = paint;
        }

        public Path getPath() {
            return path;
        }

        public Paint getPaint() {
            return paint;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        draw(false);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    //撤销
    public void back() {
        if (!list.isEmpty()) {
            list.removeLast();
        }
        draw(false);
    }

    //清空
    public void clear() {
        list = new LinkedList<>();
        draw(false);
    }

    //保存
    public boolean savePicToSdcard(String path) {
        try {
            File file = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            Bitmap bitmap1 = Bitmap.createBitmap(bitmap);
            Canvas canvas = new Canvas(bitmap1);
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Action a = list.get(i);
                    canvas.drawPath(a.getPath(), a.getPaint());
                }
            }
            bitmap1.compress(Bitmap.CompressFormat.WEBP, 100, fileOutputStream);
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
