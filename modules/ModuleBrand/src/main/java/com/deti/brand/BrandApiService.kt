package com.deti.brand

import com.deti.brand.demand.create.entity.DemandExpressListEntity
import com.deti.brand.demand.create.entity.DemandStyleTypeEntity
import com.test.common.base.BaseNetEntity
import com.test.common.entity.CommonFindSizeEntity
import com.test.common.entity.CommoneEmpty
import com.test.common.ui.popup.color.DemandColorListEntity
import retrofit2.http.Body
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

    /**
     * 获取颜色列表
     */
    @POST("client/color/findColorList")
    suspend fun requestColorsList(
        @Body body: HashMap<String, String?>,
    ):BaseNetEntity<DemandColorListEntity?>
    /**
     * 根据类别找尺码组
     */
    @POST("client/size/findSizes")
    suspend fun requestFindSize(
        @Body body: HashMap<String, String?>,
    ):BaseNetEntity<CommonFindSizeEntity?>

    /**
     * 需求单下单
     */
    @POST("http://192.168.10.11:9002/DETI-Demand/client/demandIndent/saveDemandIndentAPP")
    suspend fun requestDemandSubmit(
        @Body body: HashMap<String, Any?>,
    ):BaseNetEntity<CommoneEmpty?>






}