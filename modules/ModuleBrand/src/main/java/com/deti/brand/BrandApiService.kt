package com.deti.brand

import com.deti.brand.demand.create.entity.DemandExpressListEntity
import com.deti.brand.demand.create.entity.DemandInfoEntity
import com.deti.brand.demand.create.entity.DemandStyleTypeEntity
import com.deti.brand.demand.detail.entity.MaterialCostEntity
import com.deti.brand.demand.detail.entity.OtherCostEntity
import com.deti.brand.demand.detail.entity.TotalCostEntity
import com.deti.brand.demand.price.list.entity.DemandIndentListApp
import com.deti.brand.demand.progress.generate.entity.SapmleClothesLogisticsEntity
import com.test.common.base.BaseNetEntity
import com.test.common.entity.CommonFindSizeEntity
import com.test.common.entity.CommoneEmpty
import com.test.common.ui.popup.custom.color.DemandColorListEntity
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

    /**
     * 需求单修改提交
     */
    @POST("http://192.168.10.11:9002/DETI-Demand/client/demandIndent/updateDemandIndentAPP")
    suspend fun requestUpdateDemandIndentAPP(
        @Body body: HashMap<String, Any?>,
    ):BaseNetEntity<CommoneEmpty?>
    /**
     * 品牌商侧-需求单详情(APP)
     */
    @POST("http://192.168.10.11:9002/DETI-Demand/client/demandIndent/findDemandIndentInfo")
    suspend fun requestFindDemandIndentInfo(
        @Body body: HashMap<String, Any?>,
    ):BaseNetEntity<DemandInfoEntity?>

    /**
     * 品牌商侧-获取需求单列表(APP)
     */
    @POST("http://192.168.10.11:9002/DETI-Demand/client/quote/findQuotePageApp")
    suspend fun findDemandIndentListAPP(
        @Body body: HashMap<String, Any?>,
    ):BaseNetEntity<DemandIndentListApp?>



    /**
     * 品牌商侧-获取需求单列表(APP)
     */
    @POST("http://192.168.10.11:9002/DETI-Demand/client/quote/findFabricList")
    suspend fun findFabricList(
        @Body body: HashMap<String, Any?>,
    ):BaseNetEntity<MaterialCostEntity?>

    /**
     * 其他费用
     */
    @POST("http://192.168.10.11:9002/DETI-Demand/client/quote/otherQuoteInfo")
    suspend fun otherQuoteInfo(
        @Body body: HashMap<String, Any?>,
    ):BaseNetEntity<OtherCostEntity?>


    /**
     * 合计报价
     */
    @POST("http://192.168.10.11:9002/DETI-Demand/client/quote/lastQuoteInfo")
    suspend fun lastQuoteInfo(
        @Body body: HashMap<String, Any?>,
    ):BaseNetEntity<TotalCostEntity?>

    /**
     * 拒绝报价
     */
    @POST("http://192.168.10.11:9002/DETI-Demand/client/quote/refuseQuote")
    suspend fun refuseQuote(
        @Body body: HashMap<String, Any?>,
    ):BaseNetEntity<CommoneEmpty?>
    /**
     * 同意报价
     */
    @POST("http://192.168.10.11:9002/DETI-Demand/client/quote/acceptQuote")
    suspend fun acceptQuote(
        @Body body: HashMap<String, Any?>,
    ):BaseNetEntity<CommoneEmpty?>


    /**
     * 快递查询
     */
    @POST("http://192.168.10.11:9002/DETI-Demand/client/demandIndent/findExpressInfo")
    suspend fun findExpressInfo(
        @Body body: HashMap<String, Any?>,
    ):BaseNetEntity<SapmleClothesLogisticsEntity?>

}