package com.example.mhmdreza_j.xproject.logic.job.record;

import android.support.annotation.NonNull;

import com.example.mhmdreza_j.xproject.logic.job.BaseJob;
import com.example.mhmdreza_j.xproject.webservice.WebserviceHelper;
import com.example.mhmdreza_j.xproject.webservice.base.WebserviceException;
import com.example.mhmdreza_j.xproject.webservice.webservices.userRecord.RecordResponse;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import static com.example.mhmdreza_j.xproject.logic.MyJobCreator.MAIN_RECORD;

public class GetRecordJob extends BaseJob {

    @NonNull
    @Override
    protected Result onRunJob(@NonNull Params params) {
        try {
            RecordResponse record = WebserviceHelper.INSTANCE.record();
            EventBus.getDefault().post(new OnRecordJobSuccessEvent(record));
            return Result.SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WebserviceException e) {
            e.printStackTrace();
        }
        return Result.FAILURE;
    }

    public static void schedule() {
        scheduleImmediateJob(MAIN_RECORD);
    }
}
