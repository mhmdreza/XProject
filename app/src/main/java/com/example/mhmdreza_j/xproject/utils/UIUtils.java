package com.example.mhmdreza_j.xproject.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;

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
}
