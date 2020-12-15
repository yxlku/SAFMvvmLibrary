package com.test.common.base

import com.safmvvm.http.entity.IBaseResponse
import java.io.Serializable

data class BaseNetEntity<T: Serializable?>(
    /**
     * 请求返回信息
     */
    @JvmField
    var errorMsg: String,
    /**
     * 请求返回码
     */
    @JvmField
    var errorCode: String,
    /**
     * 请求返回数据，可空
     */
    var data: T? = null

) :Serializable, IBaseResponse<T>{
    override fun code(): String = errorCode

    override fun msg(): String = errorMsg

    override fun data(): T? = data

}