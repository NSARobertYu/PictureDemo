package com.example.administrator.project_meitu.Activitys;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.project_meitu.R;
import com.example.administrator.project_meitu.Utils.Configutils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ethank on 16/9/27.
 * 博客地址:http://blog.csdn.net/qq_21983189
 * qq:1456001152
 */

public class SavePicActivity extends Activity {

    private String picPath = "http://img2.imgtn.bdimg.com/it/u=3914947943,3919334977&fm=11&gp=0.jpg";
    private ImageView local_iv, newwork_iv;
    private Button local_but, network_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savepictolocal_layout);

        local_iv = (ImageView) findViewById(R.id.local_iv);
        newwork_iv = (ImageView) findViewById(R.id.newwork_iv);
        local_but = (Button) findViewById(R.id.local_but);
        network_but = (Button) findViewById(R.id.network_but);

        local_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                fetchLocalBitmap();
                fetchLocalPicToGallery();
            }
        });
        network_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchNetWorkPic();
            }
        });
    }

    public void fetchLocalBitmap() {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.img_3);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        File file = new File(Configutils.GalleryPath + System.currentTimeMillis() + ".webp");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.WEBP, 100, fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        local_iv.setImageBitmap(bitmap);
    }

    public void fetchLocalPicToGallery() {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.img_3);
        Bitmap bitmap = bitmapDrawable.getBitmap();

        //保存本地图片到系统相册
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null);

        //从系统相册取出图片
        try {
            InputStream inputStream = getContentResolver().openInputStream(Uri.parse(path));
            Bitmap b = BitmapFactory.decodeStream(inputStream);

            local_iv.setImageBitmap(b);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void fetchNetWorkPic() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL u = new URL(picPath);
                    HttpURLConnection h = (HttpURLConnection) u.openConnection();
                    h.setConnectTimeout(20 * 1000);
                    if (h.getResponseCode() != 200) {
                        Log.d(getPackageName(), "请求失败");
                        return;
                    }

                    InputStream inputStream = h.getInputStream();
                    Bitmap b = BitmapFactory.decodeStream(inputStream);

                    Message m = Message.obtain();
                    m.obj = b;
                    handler.sendMessage(m);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            Bitmap b = (Bitmap) message.obj;
            if (b != null) {
                newwork_iv.setImageBitmap(b);
            }
            return false;
        }
    });


}
