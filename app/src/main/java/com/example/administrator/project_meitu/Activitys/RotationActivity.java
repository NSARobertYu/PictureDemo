package com.example.administrator.project_meitu.Activitys;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.administrator.project_meitu.R;

/**
 * Created by Administrator on 2017/2/27.
 */
public class RotationActivity extends Activity {

    private SeekBar seekBar;
    private TextView progress;
    private ImageView iv, showview_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);

        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        progress = (TextView) findViewById(R.id.progress_tv);
        iv = (ImageView) findViewById(R.id.rotate_iv);
        showview_iv = (ImageView) findViewById(R.id.showview_iv);

        //进度以10递增（减）
        seekBar.incrementProgressBy(10);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                iv.setRotation(i);
                Bitmap bitmap = drawableToBitmap(iv.getDrawable(), i);
                showview_iv.setImageBitmap(bitmap);
                progress.setText("当前旋转的度数是：" + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public Bitmap drawableToBitmap(Drawable drawable,int degree) {

        int intrinsicWidh = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(intrinsicWidh,intrinsicHeight,
                drawable.getOpacity()!= PixelFormat.OPAQUE
                        ?Bitmap.Config.ARGB_8888:Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0,0,intrinsicWidh,intrinsicHeight);

        drawable.draw(canvas);

        Matrix matrix = new Matrix();
        matrix.postRotate(degree);

        Bitmap newBitmap = Bitmap.createBitmap(bitmap,0,0
                ,bitmap.getWidth(),bitmap.getHeight(),matrix,true);

        bitmap.recycle();


        return newBitmap;
    }

}
