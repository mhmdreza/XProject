package com.example.mhmdreza_j.xproject.logic.job;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

public abstract class BaseJob extends Job {

    protected static void scheduleImmediateJob(String tag) {
        new JobRequest.Builder(tag)
                .startNow()
                .build()
                .schedule();
    }

}
