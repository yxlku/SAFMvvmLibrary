package com.deti.brand.demand.detail.other

import com.deti.brand.BrandApiService
import com.deti.brand.demand.detail.entity.MaterialCostEntity
import com.deti.brand.demand.detail.entity.OtherCostEntity
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.utils.coroutines.flowOnIO
import com.test.common.base.BaseNetEntity
import kotlinx.coroutines.flow.Flow

class OtherCostModel: BaseModel() {

    var mHttpDataSource = generateHttpDataSource(BrandApiService::class.java)



    /**
     * 其他费用
     */
    fun findFabricList(
        quoteId: String = "",
        indentId: String = "",
    ): Flow<BaseNetEntity<OtherCostEntity?>> {
        return flowOnIO {
            var body = hashMapOf<String, Any?>().apply {
                put("quoteId", quoteId)
                put("indentId", indentId)
            }
            return@flowOnIO mHttpDataSource?.otherQuoteInfo(body)
        } as Flow<BaseNetEntity<OtherCostEntity?>>
    }

}