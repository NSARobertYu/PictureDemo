package com.example.administrator.project_meitu.Activitys;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.project_meitu.R;
import com.example.administrator.project_meitu.Utils.Configutils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2017/2/28.
 */

public class PintuActivity extends Activity {

    ImageView imageView1, imageView2, imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingtu);

        imageView1 = (ImageView) findViewById(R.id.imageview1);
        imageView2 = (ImageView) findViewById(R.id.imageview2);
        imageView3 = (ImageView) findViewById(R.id.imageview3);

        Bitmap bitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.img_3)).getBitmap();

        Bitmap bitmap1 = AddBitmap(bitmap,bitmap);
        imageView1.setImageBitmap(bitmap1);
        bitmap1 = AddBitmap2(bitmap,bitmap);
        imageView2.setImageBitmap(bitmap1);
        bitmap1 = AddBitmap3(bitmap,bitmap,bitmap,bitmap,bitmap);
        imageView3.setImageBitmap(bitmap1);

    }

    //横向拼接
    public Bitmap AddBitmap(Bitmap firstBitmap, Bitmap secondBitmap) {
        int width = firstBitmap.getWidth() + secondBitmap.getWidth();
        int height = Math.max(firstBitmap.getHeight(), secondBitmap.getHeight());

        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(firstBitmap, 0, 0, null);
        canvas.drawBitmap(secondBitmap, firstBitmap.getWidth(), 0, null);

        File file = new File(Configutils.GalleryPath + System.currentTimeMillis() + ".webp");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            result.compress(Bitmap.CompressFormat.WEBP, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //纵向拼接
    public Bitmap AddBitmap2(Bitmap firstBitmap, Bitmap secondBitmap) {
        int width = Math.max(firstBitmap.getWidth(), secondBitmap.getWidth());
        int height = firstBitmap.getHeight() + secondBitmap.getHeight();

        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(firstBitmap, 0, 0, null);
        canvas.drawBitmap(secondBitmap, 0, firstBitmap.getHeight(), null);

        File file = new File(Configutils.GalleryPath + System.currentTimeMillis() + ".webp");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            result.compress(Bitmap.CompressFormat.WEBP, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //多张图片
    public Bitmap AddBitmap3(Bitmap bitmap1,Bitmap bitmap2
            ,Bitmap bitmap3,Bitmap bitmap4,Bitmap bitmap5){
        int width = Math.max(bitmap1.getWidth()+bitmap2.getWidth()
                ,bitmap3.getWidth()+bitmap4.getWidth());
        int height = Math.max(bitmap1.getHeight()+bitmap3.getHeight()
                ,bitmap2.getHeight()+bitmap4.getHeight());
        Bitmap result = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(bitmap1,0,0,null);
        canvas.drawBitmap(bitmap2,bitmap1.getWidth(),0,null);
        canvas.drawBitmap(bitmap3,0,bitmap1.getHeight(),null);
        canvas.drawBitmap(bitmap4,bitmap3.getWidth(),bitmap2.getHeight(),null);
        canvas.drawBitmap(bitmap5,bitmap1.getWidth()/2,bitmap1.getHeight()/2,null);

        File file = new File(Configutils.GalleryPath + System.currentTimeMillis() + ".webp");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            result.compress(Bitmap.CompressFormat.WEBP, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
