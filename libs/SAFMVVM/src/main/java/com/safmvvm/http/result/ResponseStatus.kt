package com.safmvvm.http.result

/**
 *    请求状态
 */
enum class ResponseStatus {
    /**
     * 请求等待中
     */
    LOADING,
    /**
     * 请求成功
     */
    SUCCESS,

    /**
     * 请求失败 - 网络问题
     */
    ERROR,

    /**
     * 请求完成
     */
    COMPLETE


}