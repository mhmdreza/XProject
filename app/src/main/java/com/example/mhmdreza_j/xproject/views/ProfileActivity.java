package com.example.mhmdreza_j.xproject.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.blurry.Blurry;
import com.example.mhmdreza_j.xproject.utils.BlurryUtil;
import com.example.mhmdreza_j.xproject.views.base_class.BaseActivity;


public class ProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Blurry.ImageComposer composer = BlurryUtil.getComposer();

        LinearLayout rootLayout = findViewById(R.id.rootLayout);
        if (composer != null) {
            composer.into(rootLayout);
        } else {
            rootLayout.setBackgroundResource(R.mipmap.background);
        }

        TextView closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
