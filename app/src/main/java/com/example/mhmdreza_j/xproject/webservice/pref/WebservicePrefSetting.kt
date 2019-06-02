package ir.sharif.vamdeh.webservices.pref

import android.content.Context
import android.content.SharedPreferences

class WebservicePrefSetting private constructor(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PrefConstants.SHARED_NAME, Context.MODE_PRIVATE)

    var isRegister: Boolean
        get() = sharedPreferences.getBoolean(PrefConstants.IS_REGISTER, false)
        set(register) = sharedPreferences.edit().putBoolean(PrefConstants.IS_REGISTER, register).apply()

    val token: String?
        get() = sharedPreferences.getString(PrefConstants.SHARED_KEY, "")

    fun saveToken(key: String) {
        sharedPreferences.edit().putString(PrefConstants.SHARED_KEY, key).apply()

    }

    companion object {

        private var instance: WebservicePrefSetting? = null

        fun getInstance(context: Context): WebservicePrefSetting {
            return instance?:WebservicePrefSetting(context)
        }

        val instanceWithoutContext: WebservicePrefSetting
            get() {
                return instance?:throw RuntimeException("Please set context first")
            }
    }
}
