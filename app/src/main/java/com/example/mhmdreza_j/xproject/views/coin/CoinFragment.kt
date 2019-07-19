package com.example.mhmdreza_j.xproject.views.coin


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment
import com.example.mhmdreza_j.xproject.views.main_page.RANKING_POSITION

class CoinFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_coin, container, false)
        val homeButton = view.findViewById<ImageView>(R.id.homeButton)
        homeButton.setOnClickListener { onBackPressed() }
        return view
    }

    override fun onBackPressed() {
        if (activity == null) return
        this.parentFragment!!.navigationView.performOnClick(RANKING_POSITION)
    }
}// Required empty public constructor
