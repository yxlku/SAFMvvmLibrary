package com.safmvvm.utils.coroutines

import com.safmvvm.http.entity.IBaseResponse
import com.safmvvm.ui.load.LoadingState
import com.safmvvm.utils.LogUtil
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


/**
 * 通常用在ViewModel层进行处理请求后的统一操作
 * 异步操作
 * 1、异步前操作
 * 2、异步完成操作
 * 3、成功后操作
 * 4、失败后操作
 */
suspend fun <T> Flow<T>.flowDataDeal(
    /** 等待状态 */
    onLoading: LoadingState = LoadingState.LOADING,
    /** 成功状态 */
    onSuccess: (T)->Unit,
    /** 请求成功但是返回错误*/
    onFaile: (code: String, msg: String) -> Unit,
    /** 错误状态、不能成功请求（无网络） */
    onError: (ex: Throwable) -> Unit?
){
    //基类所有操作都是遵循：1、统一封装；2、调用回调函数到调用者返回去处理自定义操作
    this.onStart {
            //等待处理
            LogUtil.e("我是封装好的请求等待")
        }
        .onCompletion {
            //等待关闭
            LogUtil.e("我是封装好的请求完成")
        }
        .catch {
            //异常处理
            LogUtil.e("咋还出错了呢")
            LogUtil.exception("请求异常", it)
            onError(it)
        }
        .collect {
            //返回处理处理成功与失败结果
            if (it is IBaseResponse<*>) {
                onSuccess(it)
            }
        }

}