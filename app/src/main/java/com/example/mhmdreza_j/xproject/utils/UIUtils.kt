package com.example.mhmdreza_j.xproject.utils

import android.content.Context
import android.graphics.Bitmap
import android.view.ViewGroup
import android.widget.ImageView

import com.example.mhmdreza_j.xproject.R
import kotlin.math.ceil

fun dp(value: Float, context: Context): Int {
    if (value == 0f) {
        return 0
    }
    val density = context.resources.displayMetrics.density
    return ceil((density * value).toDouble()).toInt()
}

fun setProfileImage(imageView: ImageView, i: Int?) {
    val drawableResId: Int = when (i) {
        0 -> R.mipmap.face0
        1 -> R.mipmap.face1
        2 -> R.mipmap.face2
        3 -> R.mipmap.face3
        4 -> R.mipmap.face4
        5 -> R.mipmap.face5
        else -> R.mipmap.face0
    }
    imageView.setImageResource(drawableResId)
}
