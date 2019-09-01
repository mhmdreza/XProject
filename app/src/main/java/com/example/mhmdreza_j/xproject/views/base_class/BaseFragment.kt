package com.example.mhmdreza_j.xproject.views.base_class

import androidx.fragment.app.Fragment
import android.widget.Toast
import com.example.mhmdreza_j.xproject.views.main_page.MainActivity
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment

open class BaseFragment : Fragment() {
    var parentFragment: MainFragment? = null
    var nextFragment: BaseFragment? = null
    val mainActivity: MainActivity?
        get() {
            if (activity == null || activity !is MainActivity) {
                return null
            }
            return activity as MainActivity
        }

    open fun onBackPressed() {
        if (activity == null) return
        activity!!.finish()
    }

    fun onNextPressed() {
        if (activity == null || nextFragment == null) return
        mainActivity!!.startFragment(nextFragment!!)
    }


    fun showLoading() {
        if (activity == null) return
        mainActivity!!.showLoading()
    }

    fun hideLoading() {
        if (activity == null) return
        mainActivity!!.hideLoading()
    }

    protected fun toastMessage(message: String) {
        activity?.runOnUiThread {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
    }
}
