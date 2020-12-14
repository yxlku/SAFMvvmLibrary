package com.safmvvm.utils.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

/**
 * IO操作封装 - 在Model层中请求网络、数据库访问可以调用
 */
fun <T> flowOnIO(block: suspend () -> T?): Flow<T?> =
    flow {
        var result: T? = block()
        //发射操作后的数据
        emit(result)
    }.flowOn(Dispatchers.IO) // 通过 flowOn 切换到 io 线程

