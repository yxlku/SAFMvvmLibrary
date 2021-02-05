package com.deti.brand.demand.detail.total

import android.app.Application
import com.deti.brand.demand.detail.other.OtherCostFragment
import com.deti.brand.demand.detail.total.TotalCostFragment.Companion.LIVE_DATA_TOTAL_COST
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.viewmodel.BaseViewModel

class TotalCostViewModel(app: Application): BaseViewModel<TotalCostModel>(app) {

    fun requestLastQuoteInfo(){
        launchRequest {
            mModel.lastQuoteInfo(
                "1", "lixtext"
            ).flowDataDeal(
                onSuccess = {
                    it.data?.apply {
                        LiveDataBus.send(LIVE_DATA_TOTAL_COST, this.result)
                    }
                }
            )
        }
    }
}