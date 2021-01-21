package com.test.common.base

import com.safmvvm.http.entity.IBaseResponse
import java.io.Serializable

data class BaseNetEntity<T: Serializable?>(
    /**
     * 请求返回信息
     */
    @JvmField
    var message: String,
    /**
     * 请求返回码
     */
    @JvmField
    var code: String,
    /**
     * 请求返回数据，可空
     */
    var data: T? = null,
    /**
     * 返回结果状态
     */
    var result: Boolean = true
) :Serializable, IBaseResponse<T>{
    override fun code(): String = code

    override fun msg(): String = message

    override fun data(): T? = data

}