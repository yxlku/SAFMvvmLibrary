package com.longpc.testapplication

import com.test.common.base.BaseNetEntity
import com.deti.debug.TestBaseNetEntityPost
import com.longpc.testapplication.datasource.TestApiService
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.utils.coroutines.flowOnIO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainModel: BaseModel() {
    //网络请求数据源
    var mHttpDataSource: TestApiService? = generateHttpDataSource(TestApiService::class.java)

    suspend fun testMainNet(): BaseNetEntity<MainDataEntity?>?{
        var d :BaseNetEntity<MainDataEntity?>? = mHttpDataSource?.testApiService("", "")
        d?.data?.text = "咋都是空的啊"
        return d
    }

    suspend fun testMainNetFolw(): Flow<BaseNetEntity<MainDataEntity?>?>{
        return flow {
            var d :BaseNetEntity<MainDataEntity?>? = mHttpDataSource?.testApiService("", "")
            d?.data?.text = "Flow就是牛逼！！！"
            delay(3000)
            emit(d)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun testInLineFolw(): Flow<BaseNetEntity<MainDataEntity?>?>{
        return flowOnIO{
//            mHttpDataSource?.testApiService()
            var d :BaseNetEntity<MainDataEntity?>? = mHttpDataSource?.testApiService("one", "two2")
            d?.data?.text = "Flow就是牛逼！！！"
//            delay(5000)
            return@flowOnIO d
        }
    }

    /**
     * 测试post请求
     */
    suspend fun testPostFlow(): Flow<TestBaseNetEntityPost<ArrayList<MainPostEntity?>?>?>{
        return flowOnIO {
            var body = HashMap<String, String>()
            body.put("page", "1")
            body.put("count", "5")
            var d: TestBaseNetEntityPost<ArrayList<MainPostEntity?>?>? = mHttpDataSource?.testPost("one", "two")
            return@flowOnIO d
        }
    }
}