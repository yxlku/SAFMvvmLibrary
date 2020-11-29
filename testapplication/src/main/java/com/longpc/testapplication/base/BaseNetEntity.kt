package com.longpc.testapplication.base

import android.os.Parcelable
import com.safmvvm.http.entity.IBaseResponse
import kotlinx.android.parcel.Parcelize

//TODO 子Module可以动态更改 实体基类
@Parcelize
data class BaseNetEntity<T: Parcelable?>(
    /**
     * 请求返回信息
     */
    var errorMsg: String,
    /**
     * 请求返回码
     */
    var errorCode: String,
    /**
     * 请求返回数据，可空
     */
    var data: T? = null

) : Parcelable, IBaseResponse<T>{
    override fun code(): String = errorCode

    override fun msg(): String = errorMsg

    override fun data(): T? = data

}