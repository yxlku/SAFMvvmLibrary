package com.safmvvm.mvvm.model.datasource

/**
 * MMKV、SP数据源基类
 */
abstract class BaseCacheDataSource: IDataSource {
    init {
        initDataSource()
    }

    override fun initDataSource() {

    }
}