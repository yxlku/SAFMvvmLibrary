package com.deti.brand.demand.price.all

import android.app.Application
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.utils.LogUtil
import com.test.common.dictionary.dictionaryServiceTypeKeyToValue

/**
 * 报价列表
 */
class PriceListAllViewModel(app: Application): BaseViewModel<PriceListAllModel>(app){
    var pageIndex = 1
    /**
     * 获取报价列表
     */
    fun requestFindDemandIndentListAPP(status: String){
        launchRequest {
            mModel.findDemandIndentListAPP(status, pageIndex)
                .flowDataDeal(
                    onSuccess = {
                        var pageData = it.data?.pageData?.content
                        pageData?.apply {
//                            this[0].status = STATE_OFFER_WAIT_DETI //待得体报价
//                            this[1].status = STATE_CONFIRMED_WAIT  //待确认
                            LiveDataBus.send(PriceListAllFragment.DATA_ADD, this)
                        } ?: run {
                            LiveDataBus.send("noData", this)
                        }
                        LogUtil.d("it.data.toString()${it.data.toString()}")
                    }
                )
        }
    }
}