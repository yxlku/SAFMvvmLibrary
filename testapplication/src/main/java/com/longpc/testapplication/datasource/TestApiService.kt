package com.longpc.testapplication.datasource

import com.longpc.testapplication.MainDataEntity
import com.longpc.testapplication.MainPostEntity
import com.longpc.testapplication.base.BaseNetEntity
import com.longpc.testapplication.base.BaseNetEntityPost
import com.safmvvm.mvvm.model.datasource.IDataSource
import retrofit2.http.*

interface TestApiService : IDataSource {

    @GET("article/list/0/json")
    suspend fun testApiService(): BaseNetEntity<MainDataEntity?>


    @FormUrlEncoded
    @POST("getWangYiNews")
    suspend fun testPost(
//        @Body body: HashMap<String, String>,
        @Field("testFiled1") one: String,
        @Field("testFiled2") two: String,
    ): BaseNetEntityPost<ArrayList<MainPostEntity?>?>
}