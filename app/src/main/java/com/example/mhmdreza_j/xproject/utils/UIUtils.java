package com.example.mhmdreza_j.xproject.utils;

import android.graphics.Bitmap;
import android.view.ViewGroup;

public class UIUtils {
    public static Bitmap getDrawingCache(ViewGroup viewGroup){
        viewGroup.setDrawingCacheEnabled(true);
        viewGroup.buildDrawingCache();
        return viewGroup.getDrawingCache();
    }
}
