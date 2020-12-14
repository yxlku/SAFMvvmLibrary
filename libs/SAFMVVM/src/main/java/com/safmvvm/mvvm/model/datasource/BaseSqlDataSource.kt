package com.safmvvm.mvvm.model.datasource

/**
 * 数据库数据源基类
 */
abstract class BaseSqlDataSource: IDataSource {

    init {
        initDataSource()
    }

    override fun initDataSource() {

    }

}