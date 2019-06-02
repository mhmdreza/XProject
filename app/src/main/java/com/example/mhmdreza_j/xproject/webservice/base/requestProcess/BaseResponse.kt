package com.example.mhmdreza_j.xproject.webservice.base.requestProcess

import com.google.gson.annotations.Expose

open class BaseResponse {
    @Expose
    var messageBody: String? = null
}
