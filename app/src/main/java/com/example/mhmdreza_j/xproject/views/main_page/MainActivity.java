package com.example.mhmdreza_j.xproject.views.main_page;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.mhmdreza_j.xproject.utils.ActivityHelper;
import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.views.base_class.BaseActivity;
import com.example.mhmdreza_j.xproject.views.market.MarketFragment;

public class MainActivity extends BaseActivity {

    private static final String MAIN_FRAGMENT = "MAIN_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startFragment();
    }

    private void startFragment() {
//        MainFragment fragment = new MainFragment();
        MarketFragment fragment = new MarketFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFragment, fragment);
        fragmentTransaction.commit();
    }
}
