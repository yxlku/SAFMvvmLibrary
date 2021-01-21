package com.deti.debug

import com.safmvvm.http.entity.IBaseResponse
import java.io.Serializable

data class TestBaseNetEntityPost<T: Serializable?>(
    /**
     * 请求返回信息
     */
    var message: String,
    /**
     * 请求返回码
     */
    var code: String,
    /**
     * 请求返回数据，可空
     */
    var result: T? = null

) :Serializable, IBaseResponse<T>{
    override fun code(): String = code

    override fun msg(): String = message

    override fun data(): T? = result

}