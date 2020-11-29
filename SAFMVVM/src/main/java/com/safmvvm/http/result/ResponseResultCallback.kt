package com.safmvvm.http.result

import android.os.Parcelable

/**
 * 请求返回结果回调
 */
interface ResponseResultCallback<T: Parcelable> {
    /**
     * 请求等待状态 等待窗。。
     */
    fun onLoading(lodingText: String)

    /**
     * 请求返回数据
     */
    fun onSuccess(data: T?)

    /**
     * 请求错误
     * @param code 状态码
     * @param msg 错误文字信息
     * @param exception 错误异常信息
     */
    fun onFailed(code: String, msg: String)

    /**
     * 请求完成，可以做一些等待窗体取消。。。
     */
    fun onComplete()
}