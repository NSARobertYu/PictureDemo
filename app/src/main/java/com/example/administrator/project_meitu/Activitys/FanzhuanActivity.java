package com.example.administrator.project_meitu.Activitys;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.project_meitu.R;

/**
 * Created by Administrator on 2017/2/27.
 */
public class FanzhuanActivity extends Activity {

    private Button zuoyou_btn,shangxia_btn;
    private ImageView old_iv, new_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fanzhuan);

        zuoyou_btn = (Button) findViewById(R.id.fanzhuan_zuoyou);
        shangxia_btn = (Button) findViewById(R.id.fanzhuan_shangxia);
        old_iv = (ImageView) findViewById(R.id.old_iv);
        new_iv = (ImageView) findViewById(R.id.new_iv);

        //左右反转
        zuoyou_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = drawableToBitmap(old_iv.getDrawable());

                bitmap = reverView(bitmap, 1);
                new_iv.setImageBitmap(bitmap);
            }
        });
        //上下反转
        shangxia_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = drawableToBitmap(old_iv.getDrawable());

                bitmap = reverView(bitmap, 2);
                new_iv.setImageBitmap(bitmap);
            }
        });

    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight,
                drawable.getOpacity() != PixelFormat.OPAQUE
                        ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);

        return bitmap;
    }

    //反转
    public Bitmap reverView(Bitmap bitmap, int sign) {
        float[] floats = null;
        switch (sign) {
            case 1://左右
                floats = new float[]{-1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f};
                break;
            case 2://上下
                floats = new float[]{1f, 0f, 0f, 0f, -1f, 0f, 0f, 0f, 1f};
                break;
        }

        Matrix matrix = new Matrix();
        matrix.setValues(floats);

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

    }

}
