package com.longpc.testapplication

import com.longpc.testapplication.base.BaseNetEntity
import com.longpc.testapplication.datasource.TestApiService
import com.safmvvm.mvvm.model.BaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher

class MainModel: BaseModel() {
    //网络请求数据源
    var mHttpDataSource: TestApiService? = generateHttpDataSource(TestApiService::class.java)

    suspend fun testMainNet(): BaseNetEntity<MainDataEntity?>?{
        var d :BaseNetEntity<MainDataEntity?>? = mHttpDataSource?.testApiService()
        d?.data?.text = "咋都是空的啊"
        return d
    }

    suspend fun testMainNetFolw(): Flow<BaseNetEntity<MainDataEntity?>?>{
        return flow {
            var d :BaseNetEntity<MainDataEntity?>? = mHttpDataSource?.testApiService()
            d?.data?.text = "Flow就是牛逼！！！"
            emit(d)
        }.flowOn(Dispatchers.IO)
    }
}