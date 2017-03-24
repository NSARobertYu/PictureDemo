package com.example.administrator.project_meitu.Activitys;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.project_meitu.R;
import com.example.administrator.project_meitu.Widget.CropImageView;

/**
 * Created by ethank on 16/9/12.
 */
public class CaijianActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caijian);

        final CropImageView cropimageview_id = (CropImageView) findViewById(R.id.cropimageview_id);
//        ViewGroup.LayoutParams layoutParams = cropimageview_id.getLayoutParams();
//        layoutParams.width = 600;
//        layoutParams.height = 600;
//        cropimageview_id.setLayoutParams(layoutParams);

        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();

        cropimageview_id.setX_image((width - 400) / 2);
        cropimageview_id.setY_image((height - 400) / 2);

        Button caijian_but = (Button) findViewById(R.id.caijian_but);
        final ImageView showview = (ImageView) findViewById(R.id.showview_iv);

        //设置默认资源的大小
        cropimageview_id.setDrawable(getResources().getDrawable(R.drawable.img_3), 300, 300);

        caijian_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap cropImage = cropimageview_id.getCropImage();
                showview.setImageBitmap(cropImage);
            }
        });

    }
}
