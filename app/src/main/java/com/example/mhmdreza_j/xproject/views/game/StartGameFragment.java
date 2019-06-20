package com.example.mhmdreza_j.xproject.views.game;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment;
import com.example.mhmdreza_j.xproject.views.main_page.MainActivity;
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment;

import ir.tapsell.sdk.bannerads.TapsellBannerType;
import ir.tapsell.sdk.bannerads.TapsellBannerView;
import ir.tapsell.sdk.bannerads.TapsellBannerViewEventListener;

import static com.example.mhmdreza_j.xproject.utils.UIUtils.dp;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartGameFragment extends BaseFragment{


    public StartGameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start_game, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        view.findViewById(R.id.returnView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        view.findViewById(R.id.prizeTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextFragment = new GameFragment();
                onNextPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (getActivity() == null) return;
        ((MainActivity) getActivity()).startFragment(new MainFragment());
    }
}
