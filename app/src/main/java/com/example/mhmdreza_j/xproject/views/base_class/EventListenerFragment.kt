package com.example.mhmdreza_j.xproject.views.base_class

import com.example.mhmdreza_j.xproject.views.main_page.MainActivity

import org.greenrobot.eventbus.EventBus

open class EventListenerFragment : BaseFragment() {

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}
