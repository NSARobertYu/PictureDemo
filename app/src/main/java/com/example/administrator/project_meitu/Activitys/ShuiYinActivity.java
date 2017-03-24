package com.example.administrator.project_meitu.Activitys;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.project_meitu.R;

/**
 * Created by Administrator on 2017/2/28.
 */
public class ShuiYinActivity extends Activity {

    private ImageView showview_iv, newpic_iv;
    private EditText edit;
    private Button writeText_but, writePic_but;
    private Bitmap srcBitmap;
    private boolean isText = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuiyin);

        showview_iv = (ImageView) findViewById(R.id.showview_iv);
        srcBitmap = ((BitmapDrawable) (showview_iv.getDrawable())).getBitmap();

        newpic_iv = (ImageView) findViewById(R.id.newpic_iv);
        edit = (EditText) findViewById(R.id.edit);
        writePic_but = (Button) findViewById(R.id.writePic_but);
        writeText_but = (Button) findViewById(R.id.writeText_but);

        //生成水印文字
        writeText_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isText = true;
                writeTextAndPicToBitmap(v.getLeft(), v.getTop());
            }
        });
        //生成水印图片
        writePic_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isText = false;
                writeTextAndPicToBitmap(v.getLeft(), v.getTop());
            }
        });
        //可移动水印
        newpic_iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                if (event.getAction() == event.ACTION_DOWN) {
                    writeTextAndPicToBitmap(x, y);
                }

                return false;
            }
        });
    }

    public void writeTextAndPicToBitmap(float left, float top) {

        int width = showview_iv.getMeasuredWidth();
        int height = showview_iv.getMeasuredHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();

        if (isText) {
            String type = "宋体";
            Typeface typeface = Typeface.create(type, Typeface.BOLD);
            paint.setColor(Color.RED);
            paint.setTypeface(typeface);
            canvas.drawBitmap(srcBitmap, 0, 0, null);
            String text = edit.getText().toString().trim();
            canvas.drawText(text, left, top, paint);
        } else {
            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
            //将水印图片设置到canvas上
            canvas.drawBitmap(b, left, top, null);
        }

        newpic_iv.setImageBitmap(bitmap);
    }

}
