package com.safmvvm.mvvm.model

import com.safmvvm.http.RetrofitClient
import com.safmvvm.http.entity.IBaseResponse
import com.safmvvm.mvvm.model.datasource.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

/**
 * 所有Model的基类
 *     val mIApiService: IApiService,   //http请求数据源
 *     val mCacheDataSource: BaseCacheDataSource, //缓存数据源
 *     val mSqlDataSource: BaseSqlDataSource      //数据库数据源
 */
abstract class BaseModel : IModel {
    var mCacheDataSource: BaseCacheDataSource? = null //缓存数据源
    var mSqlDataSource: BaseSqlDataSource? = null      //数据库数据源

    /**
     * 配置http请求数据源
     */
    fun <T> generateHttpDataSource(api: Class<T>): T? {
        return RetrofitClient.instance.getService(api)
    }

    /**
     * 配置缓存初始化数据源
     */
    fun configCacheDataSouce() {

    }

    /**
     * 配置数据库初始化数据源
     */
    fun configSqlDataSource() {

    }

    /** 销毁*/
    override fun onCleared() {}
}