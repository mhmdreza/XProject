package com.example.mhmdreza_j.xproject.utils;


import com.example.mhmdreza_j.xproject.lib.blurry.Blurry;

public class BlurryUtil {
    private static Blurry.ImageComposer profileComposer = null;
    private static Blurry.ImageComposer questionComposer = null;


    public static void saveProfileComposer(Blurry.ImageComposer composer) {
        BlurryUtil.profileComposer = composer;
    }


    public static Blurry.ImageComposer getProfileComposer() {
        return profileComposer;
    }

    public static void saveQuestionComposer(Blurry.ImageComposer composer) {
        BlurryUtil.questionComposer = composer;
    }


    public static Blurry.ImageComposer getQuestionComposer() {
        return questionComposer;
    }
}
