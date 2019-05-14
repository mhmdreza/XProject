package com.example.mhmdreza_j.xproject.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.mhmdreza_j.xproject.views.main_page.MainActivity;
import com.example.mhmdreza_j.xproject.views.ProfileActivity;

public class ActivityHelper {
    public static void startMainActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);

    }

    public static void startProfileActivity(Context context){
        context.startActivity(new Intent(context, ProfileActivity.class));
    }
}
