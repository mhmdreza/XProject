package com.example.mhmdreza_j.xproject.utils

import android.content.Context
import android.util.Base64
import com.example.mhmdreza_j.xproject.webservice.pref.WebservicePrefSetting
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

@Throws(Exception::class)
fun decoded(JWTEncoded: String, key: String): String {
    return try {
        val split = JWTEncoded.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val json = getJson(split[1])
        val jsonObject = JSONObject(json)
        jsonObject[key] as String
    } catch (e: UnsupportedEncodingException) {
        ""
    }
}

fun getUsername(context: Context?): String {
    return when {
        context == null -> ""
        WebservicePrefSetting.getInstance(context).token.isNotEmpty() ->
            decoded(WebservicePrefSetting.getInstance(context).token, "username")
        else -> ""
    }
}

@Throws(UnsupportedEncodingException::class)
private fun getJson(strEncoded: String): String {
    val decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE)
    return String(decodedBytes, Charset.forName("UTF-8"))
}

