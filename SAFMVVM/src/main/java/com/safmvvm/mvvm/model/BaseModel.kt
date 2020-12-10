package com.safmvvm.mvvm.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.safmvvm.app.globalconfig.GlobalConfig
import com.safmvvm.db.RoomUtil
import com.safmvvm.http.RetrofitClient
import com.safmvvm.http.entity.IBaseResponse
import com.safmvvm.mvvm.model.datasource.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

/**
 * 所有Model的基类
 */
abstract class BaseModel : IModel {

    /**
     * 配置http请求数据源
     */
    fun <T> generateHttpDataSource(api: Class<T>): T? {
        return RetrofitClient.instance.getService(api)
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