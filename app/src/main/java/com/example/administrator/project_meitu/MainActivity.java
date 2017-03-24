package com.example.administrator.project_meitu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.project_meitu.Activitys.BiankuangActivity;
import com.example.administrator.project_meitu.Activitys.CaijianActivity;
import com.example.administrator.project_meitu.Activitys.CameraActivity;
import com.example.administrator.project_meitu.Activitys.DuodianchukongActivity;
import com.example.administrator.project_meitu.Activitys.FanzhuanActivity;
import com.example.administrator.project_meitu.Activitys.GalleryActivity;
import com.example.administrator.project_meitu.Activitys.HuaBanActivity;
import com.example.administrator.project_meitu.Activitys.PintuActivity;
import com.example.administrator.project_meitu.Activitys.RecPictrueActivity;
import com.example.administrator.project_meitu.Activitys.RotationActivity;
import com.example.administrator.project_meitu.Activitys.SavePicActivity;
import com.example.administrator.project_meitu.Activitys.ShuiYinActivity;

public class MainActivity extends AppCompatActivity {
    private Button camera_btn,gallery_btn,duodian_btn,caijian_but
            ,rotate_btn,fanzhuan_btn,biankuang_btn,tuya_btn
            ,pintu_btn,shuiyin_but,sediao_but,savepic_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera_btn = (Button) findViewById(R.id.camera_btn);
        gallery_btn = (Button) findViewById(R.id.gallery_btn);
        duodian_btn = (Button) findViewById(R.id.duodian_btn);
        caijian_but = (Button) findViewById(R.id.caijian_but);
        rotate_btn = (Button) findViewById(R.id.rotate_btn);
        fanzhuan_btn = (Button) findViewById(R.id.fanzhuan_btn);
        biankuang_btn = (Button) findViewById(R.id.biankuang_btn);
        tuya_btn = (Button) findViewById(R.id.tuya_btn);
        pintu_btn = (Button) findViewById(R.id.pintu_btn);
        shuiyin_but = (Button) findViewById(R.id.shuiyin_but);
        sediao_but = (Button) findViewById(R.id.sediao_but);
        savepic_but = (Button) findViewById(R.id.savepic_but);

        //相机拍照
        camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CameraActivity.class));
            }
        });
        //相册
        gallery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,GalleryActivity.class));
            }
        });
        //缩放移动
        duodian_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DuodianchukongActivity.class));
            }
        });
        //图片裁剪
        caijian_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CaijianActivity.class));
            }
        });
        //图片旋转
        rotate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RotationActivity.class));
            }
        });
        //图片反转
        fanzhuan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FanzhuanActivity.class));
            }
        });
        //设置图片边框
        biankuang_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BiankuangActivity.class));
            }
        });
        //图片的涂鸦
        tuya_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HuaBanActivity.class));
            }
        });
        //拼图
        pintu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PintuActivity.class));
            }
        });
        //水印
        shuiyin_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShuiYinActivity.class));
            }
        });
        //色调
        sediao_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RecPictrueActivity.class));
            }
        });
        //保存
        savepic_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SavePicActivity.class));
            }
        });

    }
}
