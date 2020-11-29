package com.longpc.testapplication

import com.longpc.testapplication.base.BaseNetEntity
import com.longpc.testapplication.datasource.TestApiService
import com.safmvvm.mvvm.model.BaseModel

class MainModel: BaseModel() {
    //网络请求数据源
    var mHttpDataSource: TestApiService? = generateHttpDataSource(TestApiService::class.java)

    suspend fun testMainNet(): BaseNetEntity<MainDataEntity?>?{
        var d :BaseNetEntity<MainDataEntity?>? = mHttpDataSource?.testApiService()
        d?.data?.text = "咋都是空的啊"
        return d
    }

}