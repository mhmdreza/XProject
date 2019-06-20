package com.example.mhmdreza_j.xproject.views.base_class;

import com.example.mhmdreza_j.xproject.views.main_page.MainActivity;

import org.greenrobot.eventbus.EventBus;

public class EventListenerFragment extends BaseFragment {

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    public void showLoading() {
        if (getActivity() == null) return;
        ((MainActivity) getActivity()).showLoading();
    }

    public void hideLoading() {
        if (getActivity() == null) return;
        ((MainActivity) getActivity()).hideLoading();
    }
}
