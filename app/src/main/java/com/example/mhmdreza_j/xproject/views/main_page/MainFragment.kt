package com.example.mhmdreza_j.xproject.views.main_page


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.lib.ah_bottom.AHBottomNavigation
import com.example.mhmdreza_j.xproject.lib.ah_bottom.AHBottomNavigationItem
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment
import com.example.mhmdreza_j.xproject.views.coin.CoinFragment
import com.example.mhmdreza_j.xproject.views.market.MarketFragment
import com.example.mhmdreza_j.xproject.views.wheel_of_furtune.WheelOfFortuneFragment

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : BaseFragment() {

    var selectedMenuItemPosition = RANKING_POSITION
    lateinit var navigationView: AHBottomNavigation
    var isBottomNavigationEnable = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        initBottomNavigation(view)
        startInnerFragment(selectedMenuItemPosition)
        return view
    }


    private fun initBottomNavigation(view: View) {
        navigationView = view.findViewById(R.id.navigation)

        navigationView.accentColor = R.color.titleTextColor
        navigationView.inactiveColor = R.color.beige

        navigationView.addItem(AHBottomNavigationItem(0, R.drawable.bn_info, R.color.titleTextColor))
        navigationView.addItem(AHBottomNavigationItem(0, R.drawable.bn_spinner, R.color.titleTextColor))
        navigationView.addItem(AHBottomNavigationItem(0, R.drawable.bn_ranking, R.color.titleTextColor))
        navigationView.addItem(AHBottomNavigationItem(0, R.drawable.bn_market, R.color.titleTextColor))
        navigationView.addItem(AHBottomNavigationItem(0, R.drawable.bn_coin, R.color.titleTextColor))

        navigationView.titleState = AHBottomNavigation.TitleState.ALWAYS_HIDE
        navigationView.currentItem = RANKING_POSITION
        navigationView.setDefaultBackgroundResource(R.mipmap.bottom_navigation_background)

        navigationView.setOnTabSelectedListener { position, _ ->
            if (selectedMenuItemPosition != position) {
                onBottomNavigationItemClicked(position)
            }
            false
        }
    }

    private fun onBottomNavigationItemClicked(position: Int) {
        selectedMenuItemPosition = position
        if (isBottomNavigationEnable)
            startInnerFragment(selectedMenuItemPosition)
    }

    private fun startInnerFragment(selectedMenuItemId: Int) {
        val fragment: BaseFragment
        when (selectedMenuItemId) {
            COIN_POSITION -> fragment = CoinFragment()
            SPINNER_POSITION -> fragment = WheelOfFortuneFragment()
            MARKET_POSITION -> fragment = MarketFragment()
            INFO_POSITION, RANKING_POSITION -> fragment = RankingFragment()
            else -> fragment = RankingFragment()
        }
        fragment.parentFragment = this
        openInnerFragment(fragment)
    }

    private fun openInnerFragment(fragment: BaseFragment) {
        if (activity == null) return
        val manager = activity!!.supportFragmentManager
        val fragmentTransaction = manager.beginTransaction()
        fragmentTransaction.replace(R.id.mainInnerFragment, fragment)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        if (selectedMenuItemPosition == R.id.navigation_ranking) {
            if (activity == null) return
            activity!!.finish()
        } else {
            navigationView.performOnClick(RANKING_POSITION)
        }
    }

}// Required empty public constructor

const val INFO_POSITION = 0
const val SPINNER_POSITION = 1
const val RANKING_POSITION = 2
const val MARKET_POSITION = 3
const val COIN_POSITION = 4
