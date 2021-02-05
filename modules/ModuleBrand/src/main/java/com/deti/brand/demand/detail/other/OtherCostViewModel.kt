package com.deti.brand.demand.detail.other

import android.app.Application
import com.deti.brand.demand.detail.other.OtherCostFragment.Companion.LIVE_DATA_OTHER_COST
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.viewmodel.BaseViewModel

class OtherCostViewModel(app: Application): BaseViewModel<OtherCostModel>(app) {


    fun requestFindFabricList(){
        launchRequest {
            mModel.findFabricList(
                "1", "lixtext"
            ).flowDataDeal(
                onSuccess = {
                    it.data?.apply {
                        LiveDataBus.send(LIVE_DATA_OTHER_COST, this.result)
                    }
                }
            )
        }
    }

}