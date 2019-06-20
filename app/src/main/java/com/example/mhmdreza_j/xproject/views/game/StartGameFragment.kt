package com.example.mhmdreza_j.xproject.views.game


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment
import com.example.mhmdreza_j.xproject.views.main_page.MainActivity
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment

import ir.tapsell.sdk.bannerads.TapsellBannerType
import ir.tapsell.sdk.bannerads.TapsellBannerView
import ir.tapsell.sdk.bannerads.TapsellBannerViewEventListener

import com.example.mhmdreza_j.xproject.utils.UIUtils.dp


/**
 * A simple [Fragment] subclass.
 */
class StartGameFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_start_game, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        view.findViewById<View>(R.id.returnView).setOnClickListener { onBackPressed() }
        view.findViewById<View>(R.id.prizeTextView).setOnClickListener {
            nextFragment = GameFragment()
            onNextPressed()
        }
    }

    override fun onBackPressed() {
        if (activity == null) return
        (activity as MainActivity).startFragment(MainFragment())
    }
}// Required empty public constructor
