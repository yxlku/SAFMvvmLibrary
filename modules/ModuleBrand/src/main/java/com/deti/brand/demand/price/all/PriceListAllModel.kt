package com.deti.brand.demand.price.all

import com.deti.brand.BrandApiService
import com.deti.brand.demand.price.all.entity.DemandIndentListApp
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.utils.coroutines.flowOnIO
import com.test.common.base.BaseNetEntity
import kotlinx.coroutines.flow.Flow

class PriceListAllModel: BaseModel(){

    var mHttpDataSource = generateHttpDataSource(BrandApiService::class.java)


    /**
     * 报价列表
     */
    fun findDemandIndentListAPP(): Flow<BaseNetEntity<DemandIndentListApp?>>{
        return flowOnIO {
            var body = hashMapOf<String, Any?>()
            return@flowOnIO mHttpDataSource?.findDemandIndentListAPP(body)
        } as Flow<BaseNetEntity<DemandIndentListApp?>>
    }

}