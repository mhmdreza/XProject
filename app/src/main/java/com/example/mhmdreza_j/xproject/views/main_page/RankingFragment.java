package com.example.mhmdreza_j.xproject.views.main_page;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.lib.blurry.Blurry;
import com.example.mhmdreza_j.xproject.utils.BlurryUtil;
import com.example.mhmdreza_j.xproject.views.game.StartGameFragment;
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment;
import com.example.mhmdreza_j.xproject.views.profile.ProfileFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class RankingFragment extends BaseFragment {

    public static final int LAYOUT_RESOURCE_ID = R.layout.fragment_ranking;

    public RankingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate the layout for this fragment
        final View view = inflater.inflate(LAYOUT_RESOURCE_ID, container, false);
        ImageView profileImageView = view.findViewById(R.id.profileImageView);

        TextView dice = view.findViewById(R.id.dicesGamePrice);
        dice.setText("20$ ");
        dice.setShadowLayer(10, 10, 10, R.color.darkBlue);

        ImageView dices = view.findViewById(R.id.rabbits);
        if (dices == null) {
            Toast.makeText(view.getContext(), "asded", Toast.LENGTH_SHORT).show();
        } else {
            dices.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view1) {
                    if (getActivity() == null) return;

                    nextFragment = new StartGameFragment();
                    onNextPressed();
                }
            });
        }

        profileImageView.bringToFront();
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Blurry.ImageComposer composer = Blurry.with(view.getContext()).capture(parentFragment.getView());
                BlurryUtil.saveCompser(composer);
                nextFragment = new ProfileFragment();
                onNextPressed();
            }
        });
        return view;
    }

}
