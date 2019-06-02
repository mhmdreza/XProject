package com.example.mhmdreza_j.xproject.views.base_class;

import android.app.job.JobScheduler;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.mhmdreza_j.xproject.views.main_page.MainActivity;

import org.greenrobot.eventbus.EventBus;

public class BaseFragment extends Fragment {
    public BaseFragment parentFragment = null;
    public BaseFragment nextFragment = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onBackPressed() {
        if (getActivity() == null) return;
        getActivity().finish();
    }

    public void onNextPressed() {
        if (getActivity() == null || nextFragment == null) return;

        ((MainActivity) getActivity()).startFragment(nextFragment);
    }
}
