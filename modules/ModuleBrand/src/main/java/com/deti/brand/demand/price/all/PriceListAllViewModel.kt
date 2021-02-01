package com.deti.brand.demand.price.all

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.load.LoadingModel
import com.safmvvm.utils.LogUtil

/**
 * 报价列表
 */
class PriceListAllViewModel(app: Application): BaseViewModel<PriceListAllModel>(app){

    /**
     * 获取报价列表
     */
    fun requestFindDemandIndentListAPP(){
        launchRequest {
            mModel.findDemandIndentListAPP()
                .flowDataDeal(
                    onSuccess = {
                        LogUtil.d("it.data.toString()${it.data.toString()}")
                    }
                )
        }
    }
}