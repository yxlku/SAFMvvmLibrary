package com.deti.brand.demand.detail.material

import com.deti.brand.BrandApiService
import com.deti.brand.demand.detail.entity.MaterialCostEntity
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.utils.coroutines.flowOnIO
import com.test.common.base.BaseNetEntity
import kotlinx.coroutines.flow.Flow

class MaterialCostModel: BaseModel() {


    var mHttpDataSource = generateHttpDataSource(BrandApiService::class.java)

    /**
     * 物料成本
     */
    fun findFabricList(
        quoteId: String = "",
        indentId: String = "",
    ): Flow<BaseNetEntity<MaterialCostEntity?>> {
        return flowOnIO {
            var body = hashMapOf<String, Any?>().apply {
                put("quoteId", quoteId)
                put("indentId", indentId)
            }
            return@flowOnIO mHttpDataSource?.findFabricList(body)
        } as Flow<BaseNetEntity<MaterialCostEntity?>>
    }
}