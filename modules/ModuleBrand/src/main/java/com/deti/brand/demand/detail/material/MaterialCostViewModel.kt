package com.deti.brand.demand.detail.material

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import com.deti.brand.demand.detail.material.MaterialCostFragment.Companion.LIVE_TAB_INFO
import com.jeremyliao.liveeventbus.LiveEventBus
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.http.result.state.success
import com.safmvvm.mvvm.viewmodel.BaseViewModel

class MaterialCostViewModel(app: Application): BaseViewModel<MaterialCostModel>(app) {


    fun requestFindFabricList(){
        launchRequest {
            mModel.findFabricList(
                "1", "lixtext"
            ).flowDataDeal(
                onSuccess = {
                    it.data?.apply {
                        LiveDataBus.send(LIVE_TAB_INFO, this.list)
                    }
                }
            )
        }
    }
}