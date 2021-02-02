package com.deti.brand.demand.price.all

import android.app.Application
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.utils.LogUtil
import com.test.common.dictionary.dictionaryServiceTypeKeyToValue

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
                        var i = it.data?.result?.list?.get(0)!!
                        LogUtil.d("keyToValue${i.productionType.dictionaryServiceTypeKeyToValue()}")
                    }
                )
        }
    }
}