package com.deti.brand

import com.deti.brand.demand.create.entity.DemandExpressListEntity
import com.deti.debug.TestBaseNetEntityPost
import com.test.common.base.BaseNetEntity
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BrandApiService {

    /**
     * 获取创建需求快递列表
     */
    @POST("client/system/findAvailableDataDictionaryList")
    suspend fun requestExpressList(
        @Body body: HashMap<String, String?>,
    ): BaseNetEntity<DemandExpressListEntity?>
}