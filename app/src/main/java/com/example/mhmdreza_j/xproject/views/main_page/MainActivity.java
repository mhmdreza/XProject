package com.example.mhmdreza_j.xproject.views.main_page;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.views.base_class.BaseActivity;
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment;
import com.example.mhmdreza_j.xproject.views.coin.CoinFragment;
import com.example.mhmdreza_j.xproject.views.market.MarketFragment;
import com.example.mhmdreza_j.xproject.views.wheel_of_furtune.WheelOfFortuneFragment;

public class MainActivity extends BaseActivity {

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
        BaseFragment fragment;
        switch (selectedMenuItemId) {
            case R.id.navigation_coin:
                fragment = new CoinFragment();
                break;
            case R.id.navigation_spinner:
                fragment = new WheelOfFortuneFragment();
                break;
            case R.id.navigation_market:
                fragment = new MarketFragment();
                break;
            case R.id.navigation_info:
            case R.id.navigation_ranking:
            default:
                fragment = new MainFragment();
        }
        startFragment(fragment);
    }

    private void startFragment(BaseFragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFragment, fragment);
        fragmentTransaction.commit();
    }
}
