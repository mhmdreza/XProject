package com.example.mhmdreza_j.xproject.views.game;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

import static com.example.mhmdreza_j.xproject.utils.UIUtils.dp;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinishGameFragment extends BaseFragment {


    private ArrayList<View> views = new ArrayList<>();

    public FinishGameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_finish_game, container, false);
        LinearLayout questionLayout = view.findViewById(R.id.questionLayout);
        if (views.size() == 0){
            for (int i = 0; i < 10; i++) {
                View v = new View(getContext());
                v.setBackgroundResource(i % 2 == 0 ? R.drawable.question_yellow : R.drawable.question_green);
                views.add(v);
                questionLayout.addView(v, dp(30 , getContext()), dp(30, getContext()));
            }
        }
        return view;
    }

}
