package com.example.mhmdreza_j.xproject.views.market


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment

import com.example.mhmdreza_j.xproject.views.main_page.RANKING_POSITION
import kotlinx.android.synthetic.main.score_view.*


/**
 * A simple [Fragment] subclass.
 */
class MarketFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_market, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeButton.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        if (activity == null) return
        this.parentFragment!!.navigationView.performOnClick(RANKING_POSITION)
    }
}// Required empty public constructor
