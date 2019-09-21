package com.example.mhmdreza_j.xproject.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import kotlin.math.ceil
import android.graphics.BitmapFactory
import android.content.res.Resources
import kotlin.math.roundToInt


fun dp(value: Float, context: Context): Int {
    if (value == 0f) {
        return 0
    }
    val density = context.resources.displayMetrics.density
    return ceil((density * value).toDouble()).toInt()
}

fun setBackgroundResource(imageView: ImageView, resId: Int) {
    val bitmap = decodeSampledBitmapFromResource(imageView.context.resources, resId,
            imageView.width, imageView.height)
    imageView.setImageBitmap(bitmap)
}
fun decodeSampledBitmapFromResource(res: Resources, resId: Int,
                                    reqWidth: Int, reqHeight: Int): Bitmap {

    // First decode with inJustDecodeBounds=true to check dimensions
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(res, resId, options)

    // Calculate inSampleSize
    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

    // Decode bitmap with inSampleSize set
    options.inJustDecodeBounds = false
    return BitmapFactory.decodeResource(res, resId, options)
}

fun calculateInSampleSize(
        options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
    // Raw height and width of image
    val height = options.outHeight
    val width = options.outWidth
    var inSampleSize = 1

    if (height > reqHeight || width > reqWidth) {

        // Calculate ratios of height and width to requested height and width
        val heightRatio = (height.toFloat() / reqHeight.toFloat()).roundToInt()
        val widthRatio = (width.toFloat() / reqWidth.toFloat()).roundToInt()

        // Choose the smallest ratio as inSampleSize value, this will guarantee
        // a final image with both dimensions larger than or equal to the
        // requested height and width.
        inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
    }

    return inSampleSize
}

fun setProfileImage(imageView: ImageView, i: Int?) {
    val drawableResId: Int = when (i) {
        0 -> com.example.mhmdreza_j.xproject.R.drawable.face0
        1 -> com.example.mhmdreza_j.xproject.R.drawable.face1
        2 -> com.example.mhmdreza_j.xproject.R.drawable.face2
        3 -> com.example.mhmdreza_j.xproject.R.drawable.face3
        4 -> com.example.mhmdreza_j.xproject.R.drawable.face4
        5 -> com.example.mhmdreza_j.xproject.R.drawable.face5
        else -> com.example.mhmdreza_j.xproject.R.drawable.face0
    }
    imageView.setImageResource(drawableResId)
}
