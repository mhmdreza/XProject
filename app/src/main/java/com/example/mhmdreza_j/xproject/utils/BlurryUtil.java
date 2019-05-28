package com.example.mhmdreza_j.xproject.utils;


import com.example.mhmdreza_j.xproject.lib.blurry.Blurry;

public class BlurryUtil {
    private static Blurry.ImageComposer composer = null;
    public static void saveCompser(Blurry.ImageComposer composer) {
        BlurryUtil.composer = composer;
    }


    public static Blurry.ImageComposer getComposer() {
        return composer;
    }
}
