package com.example.mhmdreza_j.xproject.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.Window;

import com.example.mhmdreza_j.xproject.R;

public class LoadingDialog extends Dialog {
    public LoadingDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loading_dialog);
        if (getWindow() == null) return;
        getWindow().setBackgroundDrawableResource(R.drawable.loading_background);
    }
}
