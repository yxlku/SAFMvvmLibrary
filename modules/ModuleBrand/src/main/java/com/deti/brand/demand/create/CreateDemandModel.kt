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
        mViewModel: CreateDemandViewModel,
    ): Flow<BaseNetEntity<CommoneEmpty>?>{
        //选择类型
        var provideList = arrayListOf<String>()
        mViewModel.mChooseTypes.forEach {
            provideList.add(it.id)
        }
        //正面照
        var frontImage = ""
        //背面照
        var backImage = ""
        //其他图片列表
        var detailsImageList = arrayListOf<String>()

        return flowOnIO {
            var body = hashMapOf<String, Any?>()
            body.apply {
                put("sign", "2147483647")
                put("token", userInfoToken())
                put("timestamp", "2147483647")
                put("demandIndent.provideList", provideList)        //选择的类型
                put("demandIndent.serviceType", mViewModel.mServiceType)
                put("demandIndent.productionType", mViewModel.mServiceProduce)
                put("demandIndent.fabricInfo", mViewModel.mFilePathFabric)
                put("demandIndent.sampleDressExpressType", mViewModel.mExpressSingleChoiceEntity.get()?.id)
                put("demandIndent.sampleDressExpressId", mViewModel.mExpressNum)
                put("demandIndent.makeFilePath", mViewModel.mFilePathPlate) //制版文件

                put("demandIndent.frontImage", frontImage)          //图片
                put("demandIndent.backImage", backImage)
                put("demandIndent.detailsImageList", detailsImageList)

                put("demandIndent.gender", mViewModel.mStyleList[0])        //款式分类
                put("demandIndent.category", mViewModel.mStyleList[1])
                put("demandIndent.suitType", mViewModel.mStyleList[2])
                put("demandIndent.classify", mViewModel.mStyleList[3])

                put("demandIndent.unitPrice", mViewModel.mPrice)    //单价
                put("demandIndent.deliveryDate", mViewModel.mTime)

                put("demandIndent.comment", mViewModel.mRemark) //备注

                put("demandIndent.colorList", mViewModel.mSelectColorDatas)
            }
            return@flowOnIO mHttpDataSource?.requestDemandSubmit(body) as BaseNetEntity<CommoneEmpty>
        }
    }


}