package com.example.customlibrary.Wedgit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.example.customlibrary.R;

/**
 * Created by Administrator on 2017/2/25.
 */

public class CustomButton extends Button {
    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);


        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.CustomButton);
        String text = typedArray.getString(R.styleable.CustomButton_Text);
        int color = typedArray.getColor(R.styleable.CustomButton_TextColor,0x000000);

        setText(text);
        setTextColor(color);

    }
}
