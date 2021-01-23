package com.deti.brand.demand.create

import com.deti.brand.BrandApiService
import com.deti.brand.demand.create.entity.DemandExpressListEntity
import com.deti.brand.demand.create.entity.DemandStyleTypeEntity
import com.safmvvm.ext.ui.typesview.TypesViewDataBean
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.utils.LogUtil
import com.safmvvm.utils.coroutines.flowOnIO
import com.test.common.base.BaseNetEntity
import com.test.common.common.userInfoToken
import com.test.common.entity.CommonColorEntity
import com.test.common.entity.CommonFindSizeEntity
import com.test.common.entity.CommoneEmpty
import com.test.common.ui.popup.color.DemandColorListEntity
import kotlinx.coroutines.flow.Flow
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * 创建需求
 */
class CreateDemandModel: BaseModel(){
    //网络请求数据源
    var mHttpDataSource: BrandApiService? = generateHttpDataSource(BrandApiService::class.java)
    /**
     * 获取快递列表
     */
    fun requestExpressList(): Flow<BaseNetEntity<DemandExpressListEntity>?> {
        return flowOnIO {
            var body = HashMap<String, String?>()
            body.put("token", userInfoToken())
            body.put("sign","2147483647") //暂时这么写
            body.put("key", "expressType")
            return@flowOnIO  mHttpDataSource?.requestExpressList(body) as BaseNetEntity<DemandExpressListEntity>
        }
    }

    /**
     * 款式分类
     */
    fun requestStyleInfo(): Flow<BaseNetEntity<DemandStyleTypeEntity>?>{
        return flowOnIO {
            var body = hashMapOf<String, String?>()
            return@flowOnIO mHttpDataSource?.requestStyleInfo(body) as BaseNetEntity<DemandStyleTypeEntity>
        }
    }

    /**
     * 颜色列表
     */
    fun requestColorsList(): Flow<BaseNetEntity<DemandColorListEntity>?>{
        return flowOnIO {
            var body = hashMapOf<String, String?>()
            return@flowOnIO mHttpDataSource?.requestColorsList(body) as BaseNetEntity<DemandColorListEntity>
        }
    }
    /**
     * 根据类别找尺码组
     */
    fun requestFindSize(data: ArrayList<TypesViewDataBean?>): Flow<BaseNetEntity<CommonFindSizeEntity>?>{
        return flowOnIO {
            var body = hashMapOf<String, String?>()
            try {
                body.apply {
                    put("size.gender", data[0]?.code)
                    put("size.category", data[1]?.code)
                    put("size.suitType", data[2]?.code)
                }
            } catch (e: Exception) {
                LogUtil.exception("", e)
            }
            return@flowOnIO mHttpDataSource?.requestFindSize(body) as BaseNetEntity<CommonFindSizeEntity>
        }
    }

    /**
     * 提交需求
     */
    fun requestDemandSubmit(
        /** 可提供信息*/
        provideList: List<String> = arrayListOf(),
        /** 服务类型：包工包料:fob、纯加工 cmt*/
        serviceType: String? = "",
        /** 生产类型：打板+生产sample_bulk、生产bulk */
        productionType: String? = "",
        /** 面料信息*/
        fabricInfo: String = "",
        /** 样衣快递类型 DataDictionary - code*/
        sampleDressExpressType: String? = "",
        /** 快递单单号*/
        sampleDressExpressId: String? = "",
        /** 制版文件*/
        makeFilePath: String = "",
        /** 正面图片*/
        frontImage: String = "",
        /** 背面图片*/
        backImage: String = "",
        /** 详细图片列表*/
        detailsImageList: List<String> = arrayListOf(),
        /** 性别-款式分类一级*/
        gender: String? = "",
        /** 品类-款式分类二级*/
        category: String? = "",
        /** 套装类型-款式分类三级*/
        suitType: String? = "",
        /** 款式分类*/
        classify: String? = "",
        /** 颜色列表*/
        colorList: List<CommonColorEntity> = arrayListOf(),
        /** 单价*/
        unitPrice: Double? = 0.0,
        /** 交期*/
        deliveryDate: String?,
        /** 备注*/
        comment: String = ""
    ): Flow<BaseNetEntity<CommoneEmpty>?>{
        return flowOnIO {
            var body = hashMapOf<String, Any?>()
            body.apply {
                put("sign", "2147483647")
                put("token", userInfoToken())
                put("timestamp", "2147483647")
                put("demandIndent.provideList", provideList)
                put("demandIndent.serviceType", serviceType)
                put("demandIndent.productionType", productionType)
                put("demandIndent.fabricInfo", fabricInfo)
                put("demandIndent.sampleDressExpressType", sampleDressExpressType)
                put("demandIndent.sampleDressExpressId", sampleDressExpressId)
                put("demandIndent.makeFilePath", makeFilePath)
                put("demandIndent.frontImage", frontImage)
                put("demandIndent.backImage", backImage)
                put("demandIndent.detailsImageList", detailsImageList)
                put("demandIndent.gender", gender)
                put("demandIndent.category", category)
                put("demandIndent.suitType", suitType)
                put("demandIndent.classify", classify)
                put("demandIndent.unitPrice", unitPrice)
                put("demandIndent.deliveryDate", deliveryDate)
                put("demandIndent.comment", comment)
                put("demandIndent.colorList", colorList)
            }
            return@flowOnIO mHttpDataSource?.requestDemandSubmit(body) as BaseNetEntity<CommoneEmpty>
        }
    }


}