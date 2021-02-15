package com.deti.brand.demand.price.list

import com.deti.brand.BrandApiService
import com.deti.brand.demand.price.list.entity.DemandIndentListApp
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.utils.coroutines.flowOnIO
import com.test.common.base.BaseNetEntity
import com.test.common.entity.CommoneEmpty
import kotlinx.coroutines.flow.Flow

class PriceListAllModel: BaseModel(){

    var mHttpDataSource = generateHttpDataSource(BrandApiService::class.java)


    /**
     * 报价列表
     */
    fun findDemandIndentListAPP(
        status: String = "",
        pageIndex: Int = 1,
    ): Flow<BaseNetEntity<DemandIndentListApp?>>{
        return flowOnIO {
            var body = hashMapOf<String, Any?>()
            body.apply {
                put("pageSize", "10")
                put("pageIndex", pageIndex.toString())
                put("status", status)
            }
            return@flowOnIO mHttpDataSource?.findDemandIndentListAPP(body)
        } as Flow<BaseNetEntity<DemandIndentListApp?>>
    }


    /**
     * 拒绝报价
     */
    fun refuseQuote(
        quoteId: String = "",
        replyMessage: String = "",
        isClose: Boolean = false,
    ): Flow<BaseNetEntity<CommoneEmpty?>?>{
        return flowOnIO {
            var body = hashMapOf<String, Any?>()
            body.put("quoteId", quoteId) //报价id
            body.put("replyMessage", replyMessage) //拒绝原因
            body.put("isClose", isClose)//关闭需求
            return@flowOnIO mHttpDataSource?.refuseQuote(body)
        }
    }


}