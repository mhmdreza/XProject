package com.example.mhmdreza_j.xproject.logic.job.battle

import android.util.Log
import com.example.mhmdreza_j.xproject.logic.MyJobCreator.Companion.ENTER_BATTLE
import com.example.mhmdreza_j.xproject.logic.job.BaseJob
import com.example.mhmdreza_j.xproject.webservice.WebserviceHelper
import com.example.mhmdreza_j.xproject.webservice.base.constants.Category
import org.greenrobot.eventbus.EventBus

class EnterBattleJob : BaseJob() {

    override fun onRunJob(params: Params): Result {
        val entranceResponse = WebserviceHelper.enterBattle(category)
        EventBus.getDefault().post(OnEnterBattleSuccessEvent(entranceResponse.opponentID))
        Log.d("APP APP", "Success login")
        return Result.SUCCESS
    }

    companion object {

        private lateinit var category: Category
        fun schedule(category: Category) {
            Companion.category = category
            scheduleImmediateJob(ENTER_BATTLE)
        }
    }
}
