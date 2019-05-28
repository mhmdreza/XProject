package com.example.mhmdreza_j.xproject.views.wheel_of_furtune;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment;
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment;

import java.util.Random;

import static com.example.mhmdreza_j.xproject.views.main_page.MainFragment.RANKING_POSITION;

/**
 * A simple {@link Fragment} subclass.
 */
public class WheelOfFortuneFragment extends BaseFragment {


    public WheelOfFortuneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wheel_of_fortune, container, false);
        final TextView spin = view.findViewById(R.id.spin);
        final View wheelImageView = view.findViewById(R.id.wheel);
        final Random random = new Random();

        ImageView homeButton = view.findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        int spinAmount = random.nextInt() % 720 + 720;
        final RotateAnimation animRotate = new RotateAnimation(0.0f, -390.0f,
                RotateAnimation.RELATIVE_TO_SELF, 0.504f,
                RotateAnimation.RELATIVE_TO_SELF, 0.442f);
        animRotate.setDuration(5000);
        animRotate.setInterpolator(new DecelerateInterpolator());

        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wheelImageView.startAnimation(animRotate);
            }
        });
        return view;
    }


    @Override
    public void onBackPressed() {
        if (getActivity() == null) return;
        MainFragment parentFragment = (MainFragment) this.parentFragment;
        parentFragment.navigationView.performOnClick(RANKING_POSITION);
    }
}
