package com.deti.brand.demand.price.all

import android.app.Application
import com.deti.brand.demand.price.all.adapter.PriceListAllAdapter.Companion.STATE_CONFIRMED
import com.deti.brand.demand.price.all.adapter.PriceListAllAdapter.Companion.STATE_CONFIRMED_WAIT
import com.deti.brand.demand.price.all.adapter.PriceListAllAdapter.Companion.STATE_DEMAND_CLOSE
import com.deti.brand.demand.price.all.adapter.PriceListAllAdapter.Companion.STATE_OFFER_WAIT
import com.deti.brand.demand.price.all.adapter.PriceListAllAdapter.Companion.STATE_OFFER_WAIT_NO_SAMPLE
import com.deti.brand.demand.price.all.adapter.PriceListAllAdapter.Companion.STATE_OFFER_WAIT_SECOND
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
                            this[0].apply {
                                this.status = STATE_OFFER_WAIT  //待报价 - 样衣未签收
                                this.statusName = "待报价"
                                this.quotePrice = "12366"
                                this.receiveFlag = "20"
                            }
                            this[1].apply {
                                this.status = STATE_OFFER_WAIT  //待报价 - 样衣已签收
                                this.statusName = "待报价"
                                this.quotePrice = "12366"
                                this.receiveFlag = "30"
                                this.receiveTime = "2021-01-31 11:26:52签收"
                                this.receiveTimestamp = 1612418750
                            }
                            this[2].apply {
                                this.status = STATE_OFFER_WAIT_NO_SAMPLE  //待报价 - 无样衣
                                this.statusName = "待报价"
                                this.quotePrice = "12366"
                                this.receiveFlag = "10"
                            }
                            this[3].apply {
                                this.status = STATE_OFFER_WAIT_SECOND  //待报价 - 二次
                                this.statusName = "二次待报价"
                                this.quotePrice = "12366"
                                this.orderTimestamp =  0
                            }
                            this[4].apply {
                                this.status = STATE_CONFIRMED_WAIT  //待确认
                                this.statusName = "待确认"
                                this.quotePrice = "12366"
                            }
                            this[5].apply {
                                this.status = STATE_CONFIRMED //已确认
                                this.statusName = "已确认"
                                this.quotePrice = "12366"
                            }
                            this[6].apply {
                                this.status = STATE_DEMAND_CLOSE //已关闭
                                this.statusName = "已关闭"
                            }
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