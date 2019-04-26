package com.example.mhmdreza_j.xproject.views.main_page;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.mhmdreza_j.xproject.utils.ActivityHelper;
import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.views.base_class.BaseActivity;
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment;
import com.example.mhmdreza_j.xproject.views.market.MarketFragment;

import ir.sharif.vamdeh.webservices.WebserviceHelper;
import ir.sharif.vamdeh.webservices.webservices.buy.BuyResponse;
import ir.sharif.vamdeh.webservices.webservices.profile.ProfileResponse;

public class MainActivity extends BaseActivity {

    private static final String MAIN_FRAGMENT = "MAIN_FRAGMENT";
    private int selectedMenuItemId = R.id.navigation_ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBottomNavigation();
        startInnerFragment();
    }

    private void initBottomNavigation() {
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                clearColorFilter();
                selectedMenuItemId = menuItem.getItemId();
                Drawable icon = menuItem.getIcon();
                icon.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                menuItem.setIcon(icon);
                startInnerFragment();
                return false;
            }
        });
//        navigationView.
    }

    private void clearColorFilter() {
        Drawable drawable;
        switch (selectedMenuItemId) {
            case R.id.navigation_coin:
                drawable = getResources().getDrawable(R.drawable.bn_coin);
                break;
            case R.id.navigation_info:
                drawable = getResources().getDrawable(R.drawable.bn_info);
                break;
            case R.id.navigation_market:
                drawable = getResources().getDrawable(R.drawable.bn_market);
                break;
            case R.id.navigation_ranking:
                drawable = getResources().getDrawable(R.drawable.bn_ranking);
                break;
            case R.id.navigation_spinner:
                drawable = getResources().getDrawable(R.drawable.bn_spinner);
                break;
            default:
                return;
        }
        drawable.clearColorFilter();
    }

    private void startInnerFragment() {
        switch (selectedMenuItemId) {
            case R.id.navigation_coin:
                startFragment(new MarketFragment());
                break;
            case R.id.navigation_info:
            case R.id.navigation_market:
            case R.id.navigation_ranking:
            case R.id.navigation_spinner:
            default:
                startFragment(new MainFragment());
        }
    }

    private void startFragment(BaseFragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFragment, fragment);
        fragmentTransaction.commit();
    }
}
