package com.example.administrator.project_meitu.Activitys;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.administrator.project_meitu.R;
import com.example.administrator.project_meitu.Utils.Configutils;
import com.example.administrator.project_meitu.Widget.MyView;

/**
 * Created by ethank on 16/9/26.
 * 博客地址：http://blog.csdn.net/qq_21983189/article/details/52606813
 */

public class HuaBanActivity extends Activity {

    public MyView myView = null;
    private View red_view, blue_view, black_view, green_view;
    private TextView sizeprogress_tv;
    private SeekBar seekbar;
    private ImageView showview_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huaban);

        myView = (MyView) findViewById(R.id.myview);
        myView.setBackground(R.drawable.img_3);
        red_view = findViewById(R.id.red_view);
        blue_view = findViewById(R.id.blue_view);
        black_view = findViewById(R.id.black_view);
        green_view = findViewById(R.id.green_view);
        sizeprogress_tv = (TextView) findViewById(R.id.sizeprogress_tv);
        seekbar = (SeekBar) findViewById(R.id.seekbar);
        showview_iv = (ImageView) findViewById(R.id.showview_iv);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sizeprogress_tv.setText(i + "");
                myView.setTextSize(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        red_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myView.setCurrentColor(Color.RED);
            }
        });
        black_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myView.setCurrentColor(Color.BLACK);
            }
        });
        blue_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myView.setCurrentColor(Color.BLUE);
            }
        });
        green_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myView.setCurrentColor(Color.GREEN);
            }
        });
    }

    public void back(View view) {
        myView.back();
    }

    public void clear(View view) {
        myView.clear();
    }

    public void save(View view) {
        String path = Configutils.GalleryPath + "test.webp";
        boolean b = myView.savePicToSdcard(path);
        if (b) {
            showview_iv.setImageBitmap(BitmapFactory.decodeFile(path));
        }
    }
}
