package com.example.mhmdreza_j.xproject.views.game;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.lib.blurry.Blurry;
import com.example.mhmdreza_j.xproject.utils.BlurryUtil;
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment;
import com.example.mhmdreza_j.xproject.views.main_page.MainActivity;
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment;
import com.facebook.FacebookActivity;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

import static com.example.mhmdreza_j.xproject.utils.UIUtils.dp;
import static com.example.mhmdreza_j.xproject.views.game.QuestionFragment.Mode.GREEN;
import static com.example.mhmdreza_j.xproject.views.game.QuestionFragment.Mode.YELLOW;

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
        final View view = inflater.inflate(R.layout.fragment_finish_game, container, false);
        final LinearLayout questionLayout = view.findViewById(R.id.questionLayout);
        if (views.size() == 0){
            for (int i = 0; i < 10; i++) {
                final View v = new View(getContext());
                v.setBackgroundResource(i % 2 == 0 ? R.drawable.question_yellow : R.drawable.question_green);
                views.add(v);
                final int finalI = i;
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v1) {
                        if (getActivity() == null) {
                            return;
                        }
                        Blurry.ImageComposer composer = Blurry.with(v1.getContext())
                                .color(R.color.dark)
                                .capture(view);
                        BlurryUtil.saveQuestionComposer(composer);
                        QuestionFragment fragment = new QuestionFragment();
                        fragment.setLeft(v1.getLeft() + questionLayout.getLeft());
                        fragment.setTop(v1.getTop()+ questionLayout.getTop());
                        fragment.setMode(finalI % 2 == 0 ? YELLOW: GREEN);

                        ((MainActivity) getActivity()).startFragment(fragment);
                    }
                });
                questionLayout.addView(v, dp(30 , getContext()), dp(30, getContext()));
            }
        }

        ImageView homeButton = view.findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        return view;
    }

    @Override
    public void onBackPressed() {
        if (getActivity() == null) return;
        ((MainActivity) getActivity()).startFragment(new MainFragment());
    }
}
