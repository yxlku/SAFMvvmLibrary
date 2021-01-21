package com.deti.brand.demand.create

import com.deti.brand.BrandApiService
import com.deti.brand.demand.create.entity.DemandExpressListEntity
import com.deti.debug.TestBaseNetEntityPost
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.utils.coroutines.flowOnIO
import com.test.common.base.BaseNetEntity
import com.test.common.common.userInfoToken
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
}