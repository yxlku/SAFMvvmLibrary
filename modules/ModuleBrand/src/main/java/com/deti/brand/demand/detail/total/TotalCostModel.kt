package com.deti.brand.demand.detail.total

import com.deti.brand.BrandApiService
import com.deti.brand.demand.detail.entity.MaterialCostEntity
import com.deti.brand.demand.detail.entity.TotalCostEntity
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.utils.coroutines.flowOnIO
import com.test.common.base.BaseNetEntity
import kotlinx.coroutines.flow.Flow

class TotalCostModel: BaseModel() {

    var mHttpDataSource = generateHttpDataSource(BrandApiService::class.java)

    /**
     * 物料成本
     */
    fun lastQuoteInfo(
        quoteId: String = "",
        indentId: String = "",
    ): Flow<BaseNetEntity<TotalCostEntity?>> {
        return flowOnIO {
            var body = hashMapOf<String, Any?>().apply {
                put("quoteId", quoteId)
                put("indentId", indentId)
            }
            return@flowOnIO mHttpDataSource?.lastQuoteInfo(body)
        } as Flow<BaseNetEntity<TotalCostEntity?>>
    }
}