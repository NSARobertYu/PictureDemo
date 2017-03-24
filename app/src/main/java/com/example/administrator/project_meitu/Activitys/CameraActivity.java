package com.example.administrator.project_meitu.Activitys;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.project_meitu.R;
import com.example.administrator.project_meitu.Utils.Configutils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/2/25.
 */
public class CameraActivity extends Activity {
    private SurfaceView surfaceView;
    private Button camera_btn, light_btn, camera_change_btn;
    private ImageView iv;
    private SurfaceHolder holder;
    private Camera camera;
    private Camera.Parameters parameters;
    private List<Camera.Size> supportedPictureSizes;
    private boolean isOpenLightSign = false;
    private int position = 0;//1表示前置,0表示后置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_camera);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
        camera_btn = (Button) findViewById(R.id.camera_btn);
        light_btn = (Button) findViewById(R.id.light_btn);
        camera_change_btn = (Button) findViewById(R.id.camera_change_btn);
        iv = (ImageView) findViewById(R.id.iv);

        holder = surfaceView.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        //大小
        holder.setFixedSize(177, 144);
        //保持高亮
        holder.setKeepScreenOn(true);

        //添加回调函数
        holder.addCallback(new MySurfaceCallBack());

        //拍照点击事件
        camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture(null, null, new TakePic());
            }
        });
        //闪光灯点击事件
        light_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLight();
                isOpenLightSign = !isOpenLightSign;
            }
        });
        //切换摄像头
        camera_change_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCamera();
            }
        });

    }

    //切换摄像头
    private void changeCamera() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int count = Camera.getNumberOfCameras();
        for (int i = 0; i < count; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (position == 1) {
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    try {
                        camera.stopPreview();
                        camera.release();
                        camera = null;
                        camera = Camera.open(i);
                        camera.setDisplayOrientation(getRotation(CameraActivity.this));
                        camera.setPreviewDisplay(holder);
                        camera.startPreview();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    position = 0;
                    camera_change_btn.setText("打开前置摄像头");
                    break;
                }
            } else {
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                    try {
                        camera.stopPreview();
                        camera.release();
                        camera = null;
                        camera = Camera.open(i);
                        camera.setDisplayOrientation(getRotation(CameraActivity.this));
                        camera.setPreviewDisplay(holder);
                        camera.startPreview();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    camera_change_btn.setText("打开后置摄像头");
                    position = 1;
                    break;
                }
            }
        }
    }

    //闪光灯
    private void changeLight() {
        Camera.Parameters parameters = camera.getParameters();
        if (isOpenLightSign) {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            light_btn.setText("关闭闪光灯");
        } else {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            light_btn.setText("打开闪光灯");
        }
        camera.setParameters(parameters);
    }

    //拍照
    class TakePic implements Camera.PictureCallback {

        @Override
        public void onPictureTaken(byte[] bytes, Camera camera) {
            if (bytes.length > 0) {
//                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                iv.setImageBitmap(bitmap);
                saveSdcard(bytes);
            }

        }
    }

    //保存到相册
    private void saveSdcard(byte[] b) {
        File dir = new File(Configutils.GalleryPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String picName = System.currentTimeMillis() + ".jpg";
        try {
            File picFile = new File(dir, picName);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            FileOutputStream fo = new FileOutputStream(picFile);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fo);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, bufferedOutputStream);

            fo.flush();
            fo.close();
            bufferedOutputStream.flush();
            bufferedOutputStream.close();

            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Callback
    class MySurfaceCallBack implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera = Camera.open();
                camera.setDisplayOrientation(getRotation(CameraActivity.this));
                camera.setPreviewDisplay(holder);
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int i1, int i2) {
            parameters = camera.getParameters();
            supportedPictureSizes = parameters.getSupportedPictureSizes();
            if (supportedPictureSizes.isEmpty()) {
                parameters.setPreviewSize(i1, i2);
            } else {
                Camera.Size size = supportedPictureSizes.get(0);
                parameters.setPreviewSize(size.width, size.height);
            }
            parameters.setPictureFormat(PixelFormat.JPEG);
            parameters.setPictureSize(i1, i2);
            parameters.setJpegQuality(80);
            parameters.setPreviewFrameRate(5);

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (camera != null) {
                camera.release();
                camera = null;
            }

        }
    }

    //界面旋转
    private int getRotation(Activity activity) {

        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degree = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degree = 90;
                break;
            case Surface.ROTATION_90:
                degree = 0;
                break;
            case Surface.ROTATION_180:
                degree = 270;
                break;
            case Surface.ROTATION_270:
                degree = 180;
                break;
        }
        return degree;
    }

    //失去焦点时
    @Override
    protected void onPause() {
        super.onPause();
        if (camera != null) {
            camera.stopPreview();
        }
    }

    //恢复
    @Override
    protected void onRestart() {
        super.onRestart();
        if (camera != null) {
            camera.startPreview();
        } else {
            try {
                camera = Camera.open();
                camera.setDisplayOrientation(getRotation(CameraActivity.this));
                camera.setPreviewDisplay(holder);
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //摄像适配
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
