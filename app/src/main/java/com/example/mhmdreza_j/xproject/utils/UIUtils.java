package com.example.mhmdreza_j.xproject.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mhmdreza_j.xproject.R;

public class UIUtils {
    public static Bitmap getDrawingCache(ViewGroup viewGroup){
        viewGroup.setDrawingCacheEnabled(true);
        viewGroup.buildDrawingCache();
        return viewGroup.getDrawingCache();
    }

    public static int dp(float value, Context context) {
        if (value == 0) {
            return 0;
        }
        float density = context.getResources().getDisplayMetrics().density;
        return (int) Math.ceil(density * value);
    }

    public static void setProfileImage(ImageView imageView, int i) {
        int drawableResId;
        switch (i){
            default:
            case 0:
                drawableResId = R.mipmap.face0;
                break;
            case 1:
                drawableResId = R.mipmap.face1;
                break;
            case 2:
                drawableResId = R.mipmap.face2;
                break;
            case 3:
                drawableResId = R.mipmap.face3;
                break;
            case 4:
                drawableResId = R.mipmap.face4;
                break;
            case 5:
                drawableResId = R.mipmap.face5;
                break;
        }
        imageView.setImageResource(drawableResId);
    }
}
