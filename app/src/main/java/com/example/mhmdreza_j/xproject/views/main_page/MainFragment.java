package com.example.mhmdreza_j.xproject.views.main_page;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.lib.ah_bottom.AHBottomNavigation;
import com.example.mhmdreza_j.xproject.lib.ah_bottom.AHBottomNavigationItem;
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment;
import com.example.mhmdreza_j.xproject.views.coin.CoinFragment;
import com.example.mhmdreza_j.xproject.views.market.MarketFragment;
import com.example.mhmdreza_j.xproject.views.wheel_of_furtune.WheelOfFortuneFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {
    public static final int INFO_POSITION = 0;
    public static final int SPINNER_POSITION = 1;
    public static final int RANKING_POSITION = 2;
    public static final int MARKET_POSITION = 3;
    public static final int COIN_POSITION = 4;

    public int selectedMenuItemPosition = RANKING_POSITION;
    public AHBottomNavigation navigationView;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initBottomNavigation(view);
        startInnerFragment(selectedMenuItemPosition);
        return view;
    }


    private void initBottomNavigation(View view) {
        navigationView = view.findViewById(R.id.navigation);

        navigationView.setAccentColor(R.color.titleTextColor);
        navigationView.setInactiveColor(R.color.beige);

        navigationView.addItem(new AHBottomNavigationItem(0, R.drawable.bn_info, R.color.titleTextColor));
        navigationView.addItem(new AHBottomNavigationItem(0, R.drawable.bn_spinner, R.color.titleTextColor));
        navigationView.addItem(new AHBottomNavigationItem(0, R.drawable.bn_ranking, R.color.titleTextColor));
        navigationView.addItem(new AHBottomNavigationItem(0, R.drawable.bn_market, R.color.titleTextColor));
        navigationView.addItem(new AHBottomNavigationItem(0, R.drawable.bn_coin, R.color.titleTextColor));

        navigationView.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
        navigationView.setCurrentItem(RANKING_POSITION);
        navigationView.setDefaultBackgroundResource(R.mipmap.bottom_navigation_background);

        navigationView.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (selectedMenuItemPosition != position) {
                    onBottomNavigationItemClicked(position);
                }
                return false;
            }
        });
    }

    public void onBottomNavigationItemClicked(int position) {
        selectedMenuItemPosition = position;
        startInnerFragment(selectedMenuItemPosition);
    }


    public void startInnerFragment(int selectedMenuItemId) {
        BaseFragment fragment;
        switch (selectedMenuItemId) {
            case COIN_POSITION:
                fragment = new CoinFragment();
                break;
            case SPINNER_POSITION:
                fragment = new WheelOfFortuneFragment();
                break;
            case MARKET_POSITION:
                fragment = new MarketFragment();
                break;
            case INFO_POSITION:
            case RANKING_POSITION:
            default:
                fragment = new RankingFragment();
        }
        fragment.parentFragment = this;
        openInnerFragment(fragment);
    }

    public void openInnerFragment(BaseFragment fragment) {
        if (getActivity() == null) return;
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.mainInnerFragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (selectedMenuItemPosition == R.id.navigation_ranking) {
            if (getActivity() == null) return;
            getActivity().finish();
        } else {
            navigationView.performOnClick(RANKING_POSITION);
        }
    }
}
