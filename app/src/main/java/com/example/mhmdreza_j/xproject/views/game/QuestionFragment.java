package com.example.mhmdreza_j.xproject.views.game;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.lib.blurry.Blurry;
import com.example.mhmdreza_j.xproject.utils.BlurryUtil;
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment;
import com.example.mhmdreza_j.xproject.views.main_page.MainActivity;
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment;
import com.facebook.FacebookActivity;

import static com.example.mhmdreza_j.xproject.utils.UIUtils.dp;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends BaseFragment {

    private int left = 0;
    private int top = 0;
    private Mode mode;

    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        FrameLayout rootLayout = view.findViewById(R.id.rootLayout);

        FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams(dp(30, getContext()), dp(30, getContext()));
        layout.setMargins(left, top, 0, 0);
        View v = new View(getContext());
        v.setBackgroundResource(mode == Mode.YELLOW ? R.drawable.question_yellow : R.drawable.question_green);
        rootLayout.addView(v, layout);

        Blurry.ImageComposer questionComposer = BlurryUtil.getQuestionComposer();
        if (questionComposer != null) {
            questionComposer.into(rootLayout);
        }


        ImageView homeButton = view.findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() == null)return;
                ((MainActivity) getActivity()).startFragment(new MainFragment());
            }
        });

        return view;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public void setTop(int top) {
        this.top = top;
    }

    @Override
    public void onBackPressed() {
        if (getActivity() == null)return;
        ((MainActivity) getActivity()).startFragment(new FinishGameFragment());
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }
    public static enum Mode{
        YELLOW, GREEN
    }
}
