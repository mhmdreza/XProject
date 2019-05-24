package ir.sharif.vamdeh.webservices.base.requestProcess

import com.google.gson.annotations.Expose

open class BaseResponse {
    @Expose
    var messageBody: String? = null
}
