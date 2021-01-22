package com.deti.brand

import com.deti.brand.demand.create.entity.DemandExpressListEntity
import com.deti.brand.demand.create.entity.DemandStyleTypeEntity
import com.deti.debug.TestBaseNetEntityPost
import com.test.common.base.BaseNetEntity
import kotlinx.coroutines.flow.Flow
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

    /**
     * 获取款式列表
     */
    @POST("client/classify/findClassifyFourthTree")
    suspend fun requestStyleInfo(
        @Body body: HashMap<String, String?>,
    ):BaseNetEntity<DemandStyleTypeEntity?>


}