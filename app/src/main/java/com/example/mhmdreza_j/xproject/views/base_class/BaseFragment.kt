package com.example.mhmdreza_j.xproject.views.base_class

import android.support.v4.app.Fragment

import com.example.mhmdreza_j.xproject.views.main_page.MainActivity
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment
import com.example.mhmdreza_j.xproject.webservice.WebserviceHelper
import io.socket.client.IO
import io.socket.client.Socket

import org.greenrobot.eventbus.EventBus
import java.net.URISyntaxException

open class BaseFragment : Fragment() {
    var parentFragment: MainFragment? = null
    var nextFragment: BaseFragment? = null

    open fun onBackPressed() {
        if (activity == null) return
        activity!!.finish()
    }

    fun onNextPressed() {
        if (activity == null || nextFragment == null) return

        (activity as MainActivity).startFragment(nextFragment!!)
    }

    fun getMainActivity(): MainActivity? {
        if (activity == null || activity !is MainActivity){
            return null
        }
        return activity as MainActivity
    }

    fun showLoading() {
        if (activity == null) return
        (activity as MainActivity).showLoading()
    }

    fun hideLoading() {
        if (activity == null) return
        (activity as MainActivity).hideLoading()
    }
}
