package com.example.mhmdreza_j.xproject.webservice.webservices.battleEntrance

import ir.sharif.vamdeh.webservices.base.Constants.Category
import ir.sharif.vamdeh.webservices.base.MyRetrofit
import ir.sharif.vamdeh.webservices.base.requestProcess.BaseProcess

class EntranceProcess(category: Category): BaseProcess() {
    private val request: EntranceRequest = EntranceRequest(category)

    override fun process(): EntranceResponse = send(MyRetrofit.webserviceUrls.enterBattle(request))
}