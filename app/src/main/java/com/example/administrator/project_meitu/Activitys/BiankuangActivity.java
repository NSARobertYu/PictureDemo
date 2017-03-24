package com.example.administrator.project_meitu.Activitys;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.administrator.project_meitu.R;
import com.example.administrator.project_meitu.Widget.CustomImagView;

/**
 * Created by ethank on 16/9/12.
 */
public class BiankuangActivity extends Activity {

    CustomImagView custom_iv;
    Button yellow_but, blue_but, red_but, green_but;
    ImageView showview_iv;
    SeekBar seekbar;
    TextView progress_tv;

    int currentColor = 0;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biankuang);

        custom_iv = (CustomImagView) findViewById(R.id.custom_iv);
        yellow_but = (Button) findViewById(R.id.yellow_but);
        blue_but = (Button) findViewById(R.id.blue_but);
        red_but = (Button) findViewById(R.id.red_but);
        green_but = (Button) findViewById(R.id.green_but);
        showview_iv = (ImageView) findViewById(R.id.showview_iv);
        seekbar = (SeekBar) findViewById(R.id.seekbar);
        progress_tv = (TextView) findViewById(R.id.progress_tv);

        yellow_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yellow_but.setTextColor(Color.YELLOW);
                blue_but.setTextColor(Color.BLACK);
                red_but.setTextColor(Color.BLACK);
                green_but.setTextColor(Color.BLACK);

                currentColor = Color.YELLOW;

                custom_iv.setBoradWidth(seekbar.getProgress());
                custom_iv.setColor(currentColor);
            }
        });
        blue_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yellow_but.setTextColor(Color.BLACK);
                blue_but.setTextColor(Color.BLUE);
                red_but.setTextColor(Color.BLACK);
                green_but.setTextColor(Color.BLACK);

                currentColor = Color.BLUE;

                custom_iv.setBoradWidth(seekbar.getProgress());
                custom_iv.setColor(currentColor);
            }
        });
        red_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yellow_but.setTextColor(Color.BLACK);
                blue_but.setTextColor(Color.BLACK);
                red_but.setTextColor(Color.RED);
                green_but.setTextColor(Color.BLACK);

                currentColor = Color.RED;

                custom_iv.setBoradWidth(seekbar.getProgress());
                custom_iv.setColor(currentColor);
            }
        });
        green_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yellow_but.setTextColor(Color.BLACK);
                blue_but.setTextColor(Color.BLACK);
                red_but.setTextColor(Color.BLACK);
                green_but.setTextColor(Color.GREEN);

                currentColor = Color.GREEN;

                custom_iv.setBoradWidth(seekbar.getProgress());
                custom_iv.setColor(currentColor);
            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                custom_iv.setColor(currentColor);
                custom_iv.setBoradWidth(i);

                progress_tv.setText("边框宽度：" + i);

                Bitmap bitmap = drawableToBitmap(custom_iv.getDrawable());

                showview_iv.setImageBitmap(bitmap);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();

        Bitmap bitmap = Bitmap.createBitmap(width, height, drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);

        Rect rect = canvas.getClipBounds();
        Paint paint = new Paint();

        paint.setStrokeWidth(seekbar.getProgress());
        paint.setColor(currentColor);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect, paint);

        return bitmap;
    }


}
