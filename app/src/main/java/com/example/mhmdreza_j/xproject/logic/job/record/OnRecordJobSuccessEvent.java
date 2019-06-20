package com.example.mhmdreza_j.xproject.logic.job.record;

import com.example.mhmdreza_j.xproject.webservice.webservices.userRecord.RecordResponse;

public class OnRecordJobSuccessEvent {
    private RecordResponse record;

    public OnRecordJobSuccessEvent(RecordResponse record) {
        this.record = record;
    }

    public RecordResponse getRecord() {
        return record;
    }
}
