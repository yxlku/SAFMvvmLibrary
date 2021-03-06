package com.safmvvm.mvvm.model

import androidx.room.RoomDatabase
import com.safmvvm.db.RoomUtil
import com.safmvvm.http.RetrofitClient

/**
 * 所有Model的基类
 */
abstract class BaseModel : IModel {

    /**
     * 配置http请求数据源
     */
    fun <T> generateHttpDataSource(api: Class<T>): T? {
        return RetrofitClient.getService(api)
    }

    /**
     * 配置数据库初始化数据源
     */
    fun <T: RoomDatabase> generateDBDataSource(database: Class<T>): T{
        return RoomUtil.getDB(database)
    }

    /** 销毁*/
    override fun onCleared() {}
}