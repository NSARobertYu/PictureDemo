package com.example.administrator.project_meitu.Activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.administrator.project_meitu.Adapter.PictureAdapter;
import com.example.administrator.project_meitu.R;
import com.example.administrator.project_meitu.Utils.Configutils;

import java.io.File;

/**
 * Created by Administrator on 2017/2/26.
 */
public class GalleryActivity extends Activity {

    private GridView gridView;
    private PictureAdapter pictureAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pictrue_layout);

        gridView = (GridView) findViewById(R.id.grid_view);

        final File[] listFiles = FetchFile();
        pictureAdapter = new PictureAdapter(this, listFiles);
        gridView.setAdapter(pictureAdapter);

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(GalleryActivity.this);

                builder.setMessage("确定要删除吗？");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        File file = listFiles[position];
                        if (file.exists()) {
                            file.delete();
                        }
                        if (pictureAdapter != null) {
                            File[] listFiles = FetchFile();
                            pictureAdapter.setFiles(listFiles);
                        }
                    }
                });
                builder.setNegativeButton("cancel", null);
                builder.show();
                return false;
            }
        });
    }

    private File[] FetchFile() {
        File files = new File(Configutils.GalleryPath);
        return files.listFiles();
    }
}
