package com.longpc.testapplication.datasource

import com.longpc.testapplication.MainDataEntity
import com.longpc.testapplication.base.BaseNetEntity
import com.safmvvm.mvvm.model.datasource.IDataSource
import retrofit2.http.GET

interface TestApiService: IDataSource {

    @GET("article/list/0/json")
    suspend fun testApiService(): BaseNetEntity<MainDataEntity?>



}