package com.example.mhmdreza_j.xproject.logic.job.profile;

import com.example.mhmdreza_j.xproject.webservice.webservices.profile.ProfileResponse;

public class OnProfileJobSuccessEvent {

    private ProfileResponse profile;

    public OnProfileJobSuccessEvent(ProfileResponse profile) {
        this.profile = profile;
    }

    public ProfileResponse getProfile() {
        return profile;
    }
}
