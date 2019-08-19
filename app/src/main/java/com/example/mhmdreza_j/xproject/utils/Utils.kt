package com.example.mhmdreza_j.xproject.utils

fun getStringOfInt(integer: Int?): String {
    return "" + getInt(integer)
}

fun getInt(integer: Int?): Int {
    return integer ?: 0
}