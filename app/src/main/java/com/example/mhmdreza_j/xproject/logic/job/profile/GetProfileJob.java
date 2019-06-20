package com.example.mhmdreza_j.xproject.logic.job.profile;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;
import com.example.mhmdreza_j.xproject.logic.MyJobCreator;
import com.example.mhmdreza_j.xproject.logic.job.BaseJob;
import com.example.mhmdreza_j.xproject.webservice.WebserviceHelper;
import com.example.mhmdreza_j.xproject.webservice.base.WebserviceException;
import com.example.mhmdreza_j.xproject.webservice.webservices.profile.ProfileResponse;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import static com.example.mhmdreza_j.xproject.logic.MyJobCreator.PROFILE;

public class GetProfileJob extends BaseJob {
    @NonNull
    @Override
    protected Result onRunJob(@NonNull Params params) {
        try {
            ProfileResponse profile = WebserviceHelper.INSTANCE.profile();
            EventBus.getDefault().post(new OnProfileJobSuccessEvent(profile));
            return Result.SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WebserviceException e) {
            e.printStackTrace();
        }
        return Result.FAILURE;
    }

    public static void schedule() {
        scheduleImmediateJob(PROFILE);
    }
}
