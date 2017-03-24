package com.example.administrator.project_meitu.Activitys;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.administrator.project_meitu.R;

/**
 * Created by ethank on 16/9/26.
 * 博客地址:http://blog.csdn.net/qq_21983189/article/details/52638270
 */

public class RecPictrueActivity extends Activity {

    private ImageView showview_iv;
    private TextView progress_tv;
    private SeekBar baohedu_seekbar, sexiang_seekbar, liangdu_seekbar;
    //用于颜色变幻的矩阵,android位图颜色变幻主要的控制器
    private ColorMatrix colorMatrix_Bhd = new ColorMatrix();
    private ColorMatrix colorMatrix_Sx = new ColorMatrix();
    private ColorMatrix colorMatrix_Ld = new ColorMatrix();

    private ColorMatrix allColorMatrix = new ColorMatrix();

    //饱和度
    private float bhdStatusValue = 0f;
    //色相
    private float sxStatusValue = 0f;
    //亮度
    private float ldStatusValue = 1f;

    //运算像素和颜色的比例,是最大值的1/2
    private float Maxmiddle_Value = 50;

    Bitmap srcBitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sediao_layout);

        showview_iv = (ImageView) findViewById(R.id.showview_iv);
        srcBitmap = ((BitmapDrawable) showview_iv.getDrawable()).getBitmap();
        progress_tv = (TextView) findViewById(R.id.progress_tv);
        baohedu_seekbar = (SeekBar) findViewById(R.id.baohedu_seekbar);
        sexiang_seekbar = (SeekBar) findViewById(R.id.sexiang_seekbar);
        liangdu_seekbar = (SeekBar) findViewById(R.id.liangdu_seekbar);

        addOnSeekBarChanheListener(baohedu_seekbar, sexiang_seekbar, liangdu_seekbar);
    }

    public void addOnSeekBarChanheListener(SeekBar... seekbars) {
        for (SeekBar seekbar : seekbars) {
            seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    switch (seekBar.getId()) {
                        case R.id.baohedu_seekbar:
                            bhdStatusValue = (float) (i * 1.0D / Maxmiddle_Value);
                            colorMatrix_Bhd.reset();
                            colorMatrix_Bhd.setSaturation(bhdStatusValue);
                            break;
                        case R.id.sexiang_seekbar:

                            //色相表现为色轮的旋转角度,正值表示顺时针旋转,反之逆时针旋转,范围是-180到+180
                            sxStatusValue = (float) ((i - Maxmiddle_Value) * 1.0D / Maxmiddle_Value * 180);
                            colorMatrix_Sx.reset();
                            colorMatrix_Sx.setRotate(0, sxStatusValue);//让红色区在色轮上旋转葛角度
                            colorMatrix_Sx.setRotate(1, sxStatusValue);//让绿色区在色轮上旋转葛角度
                            colorMatrix_Sx.setRotate(2, sxStatusValue);//让蓝色区在色轮上旋转葛角度
                            break;
                        case R.id.liangdu_seekbar:
                            ldStatusValue = (float) (i * 1.0D / Maxmiddle_Value);
                            colorMatrix_Ld.reset();
                            colorMatrix_Ld.setScale(ldStatusValue, ldStatusValue, ldStatusValue, 1);
                            break;
                    }

                    progress_tv.setText(i + "");
                    Bitmap bitmap = resetBitmap();
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
    }

    public Bitmap resetBitmap() {
        allColorMatrix.reset();
        //效果叠加
        allColorMatrix.postConcat(colorMatrix_Bhd);
        allColorMatrix.postConcat(colorMatrix_Sx);
        allColorMatrix.postConcat(colorMatrix_Ld);

        Bitmap b = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(b);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        paint.setColorFilter(new ColorMatrixColorFilter(allColorMatrix));
        canvas.drawBitmap(srcBitmap, 0, 0, paint);
        return b;
    }

}
