package com.example.mhmdreza_j.xproject.logic;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;
import com.example.mhmdreza_j.xproject.logic.job.login.LoginJob;
import com.example.mhmdreza_j.xproject.logic.job.profile.GetProfileJob;
import com.example.mhmdreza_j.xproject.logic.job.record.GetRecordJob;

public class MyJobCreator implements JobCreator {
    public static final String PROFILE = "profile";
    public static final String LOGIN = "login";
    public static final String MAIN_RECORD= "main record";

    @Nullable
    @Override
    public Job create(@NonNull String s) {
        switch (s) {
            case PROFILE:
                return new GetProfileJob();
            case LOGIN:
                return new LoginJob();
            case MAIN_RECORD:
                return new GetRecordJob();
            default:
                return null;
        }
    }
}
