package com.example.mhmdreza_j.xproject.webservice.webservices.battleEntrance

import com.example.mhmdreza_j.xproject.webservice.base.constants.Category
import com.example.mhmdreza_j.xproject.webservice.base.MyRetrofit
import com.example.mhmdreza_j.xproject.webservice.base.requestProcess.BaseProcess

class EntranceProcess(category: Category): BaseProcess() {
    private val request: EntranceRequest = EntranceRequest(category)

    override fun process(): EntranceResponse = send(MyRetrofit.webserviceUrls.enterBattle(request))
}