package com.example.mhmdreza_j.xproject.logic;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

public class MyJobCreator implements JobCreator {
    public static final String PROFILE = "profile";
    public static final String LOGIN = "login";

    @Nullable
    @Override
    public Job create(@NonNull String s) {
        switch (s) {
            case PROFILE:
            case LOGIN:
            default:
                return null;
        }
    }
}
