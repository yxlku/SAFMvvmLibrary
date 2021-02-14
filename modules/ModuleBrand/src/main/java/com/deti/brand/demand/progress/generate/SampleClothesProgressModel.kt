package com.deti.brand.demand.progress.generate

import com.deti.brand.BrandApiService
import com.deti.brand.demand.progress.generate.entity.SapmleClothesLogisticsEntity
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.utils.coroutines.flowOnIO
import com.test.common.base.BaseNetEntity
import kotlinx.coroutines.flow.Flow

class SampleClothesProgressModel: BaseModel(){

    var mHttpDataSource = generateHttpDataSource(BrandApiService::class.java)

    /**
     * 快递进度信息
     */
    fun requestFindExpressInfo(
        demandId: String = "",
    ): Flow<BaseNetEntity<SapmleClothesLogisticsEntity?>?> {
        return flowOnIO {
            var body = hashMapOf<String, Any?>()
            body.put("entity.id", demandId)
            return@flowOnIO mHttpDataSource?.findExpressInfo(body)
        }
    }


}