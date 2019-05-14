package com.example.mhmdreza_j.xproject.views.wheel_of_furtune;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment;

import java.util.Random;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wheel_of_fortune, container, false);
        final TextView spin = view.findViewById(R.id.spin);
        final View wheelImageView = view.findViewById(R.id.wheel);
        final Random random = new Random();
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

}
