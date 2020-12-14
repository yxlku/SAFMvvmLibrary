package com.safmvvm.http.entity

/**
 * 实体类必须实现这个接口并复写其中的方法，这样才可以使用 BaseViewModel 中的协程方法
 */
interface IBaseResponse<T> {
    fun code(): String = ""
    fun msg(): String = ""
    fun data(): T?
}