package com.safmvvm.ui.load

/**
 * 页面状态
 */
enum class LoadState {

    /** 等待中*/
    LOADING,
    /** 请求成功*/
    SUCCESS,
    /** 请求成功-但是服务器返回错误*/
    FAIL,
    /** 空数据*/
    EMPTY,
    /** 网络错误-例如没有网络*/
    ERROR,


}