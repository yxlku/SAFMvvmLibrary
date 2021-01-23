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
import com.test.common.entity.CommonFindSizeEntity
import com.test.common.ui.popup.color.DemandColorListEntity
import kotlinx.coroutines.flow.Flow

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
                    put("size.gender", data[0]?.id)
                    put("size.category", data[1]?.id)
                    put("size.suitType", data[2]?.id)
                }
            } catch (e: Exception) {
                LogUtil.exception("", e)
            }
            return@flowOnIO mHttpDataSource?.requestFindSize(body) as BaseNetEntity<CommonFindSizeEntity>
        }
    }


}