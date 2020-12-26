package com.safmvvm.utils.coroutines

import android.content.IntentSender
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * IO操作封装 - 在Model层中请求网络、数据库访问可以调用
 */
fun <T> flowOnIO(block: suspend () -> T?): Flow<T?> =
    flow {
        var result: T? = block()
        //发射操作后的数据
        emit(result)
    }.flowOn(Dispatchers.IO) // 通过 flowOn 切换到 io 线程

/**
 * 倒计时定义 -> 返回Flow，没有预处理，需要自行使用原生方式处理
 * @param countTime  倒计时时间
 * @param timeUnit   刷新的时间单位，默认以 "秒" 为单位，设置其他非秒的单位，此方法会将单位设置成 "毫秒"（换句话说，除了秒就是毫秒）
 */
fun flowCountdown(
    countTime: Long,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): Flow<Long> {
    var interval: Long = if(timeUnit == TimeUnit.SECONDS) 1000 else 10
    return (countTime downTo 0).asFlow()
        .onEach {
            delay(interval)
        }.flowOn(Dispatchers.IO) // 通过 flowOn 切换到 io 线程
}



