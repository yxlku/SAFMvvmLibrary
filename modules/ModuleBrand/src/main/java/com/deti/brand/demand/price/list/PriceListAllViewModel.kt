package com.deti.brand.demand.price.list

import android.app.Application
import com.deti.brand.demand.price.list.adapter.PriceListAllAdapter.Companion.STATE_CONFIRMED
import com.deti.brand.demand.price.list.adapter.PriceListAllAdapter.Companion.STATE_CONFIRMED_WAIT
import com.deti.brand.demand.price.list.adapter.PriceListAllAdapter.Companion.STATE_DEMAND_CLOSE
import com.deti.brand.demand.price.list.adapter.PriceListAllAdapter.Companion.STATE_OFFER_WAIT
import com.deti.brand.demand.price.list.adapter.PriceListAllAdapter.Companion.STATE_OFFER_WAIT_NO_SAMPLE
import com.deti.brand.demand.price.list.adapter.PriceListAllAdapter.Companion.STATE_OFFER_WAIT_SECOND
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.load.LoadingModel
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.LogUtil

/**
 * 报价列表
 */
class PriceListAllViewModel(app: Application): BaseViewModel<PriceListAllModel>(app){
    var pageIndex = 1

    /**
     * 拒绝报价
     */
    fun requestRefuseQuote(
        quoteId: String = "",
        replyMessage: String = "",
        isClose: Boolean = false,
    ){
        launchRequest {
            mModel.refuseQuote(
                quoteId, replyMessage, isClose
            ).flowDataDeal(
                loadingModel = LoadingModel.LOADING,
                onSuccess = {
                    it?.apply {
                        //TODO 刷新列表要不然多次拒绝
                        ToastUtil.showShortToast(message)
                    }
                },
                onFaile = {code: String, msg: String ->
                    ToastUtil.showShortToast(msg)
                }
            )
        }
    }

    /**
     * 同意报价
     */
    fun requestAcceptQuote(
        quoteId: String = "",
    ){
        launchRequest {
            mModel.acceptQuote(
                quoteId
            ).flowDataDeal(
                loadingModel = LoadingModel.LOADING,
                onSuccess = {
                    it?.apply {
                        //TODO 刷新列表更新状态
                        ToastUtil.showShortToast(message)
                    }
                },
                onFaile = {code: String, msg: String ->
                    ToastUtil.showShortToast(msg)
                }
            )
        }
    }

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
                                this.receiveTimestamp = 1612437360
                            }
                            this[2].apply {
                                this.status = STATE_OFFER_WAIT_NO_SAMPLE  //待报价 - 无样衣
                                this.statusName = "待报价"
                                this.quotePrice = "12366"
                                this.receiveFlag = "10"
                                this.orderTimestamp = 1612435620
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
                    }
                )
        }
    }
}