package com.example.mhmdreza_j.xproject.views.game;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment;
import com.example.mhmdreza_j.xproject.views.main_page.MainActivity;
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment;
import com.facebook.FacebookActivity;

import java.util.ArrayList;

import static com.example.mhmdreza_j.xproject.utils.UIUtils.dp;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends BaseFragment {
    private ArrayList<View> views = new ArrayList<>();

    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        initViews(view);
        return view;
    }

    private void initViews(View view) {
        final TextView progressView = view.findViewById(R.id.progressView)
                ;
        final SeekBar seekBar = view.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressView.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ImageView rightArrow = view.findViewById(R.id.rightArrow);
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int progress = seekBar.getProgress();
                if (progress == seekBar.getMax()) return;
                seekBar.setProgress(progress + 1);
            }
        });

        ImageView leftArrow = view.findViewById(R.id.leftArrow);
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int progress = seekBar.getProgress();
                seekBar.setProgress(progress > 0 ? progress - 1: progress);
            }
        });

        ImageView homeButton = view.findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        TextView submit = view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextFragment = new FinishGameFragment();
                onNextPressed();
            }
        });

        LinearLayout questionLayout = view.findViewById(R.id.questionLayout);
        if (views.size() == 0){
            for (int i = 0; i < 10; i++) {
                View v = new View(getContext());
                v.setBackgroundResource(R.drawable.question_transparent);
                views.add(v);
                questionLayout.addView(v, dp(20 , getContext()), dp(20, getContext()));
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (getActivity() == null) return;
        ((MainActivity) getActivity()).startFragment(new MainFragment());
    }
}
