package com.example.mhmdreza_j.xproject.views.base_class;

import android.support.v4.app.Fragment;

import com.example.mhmdreza_j.xproject.views.main_page.MainActivity;

public class BaseFragment extends Fragment {
    public BaseFragment parentFragment = null;
    public BaseFragment nextFragment = null;

    public void onBackPressed() {
        if (getActivity() == null) return;
        getActivity().finish();
    }

    public void onNextPressed() {
        if (getActivity() == null || nextFragment == null) return;

        ((MainActivity) getActivity()).startFragment(nextFragment);
    }
}
