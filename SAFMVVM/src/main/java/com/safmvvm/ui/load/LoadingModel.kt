package com.safmvvm.ui.load

/**
 * 异步请求等待时的效果
 */
enum class LoadingModel {
    /** 无等待效果*/
    NULL,
    /** 弹窗模式等待效果*/
    LOADING,
    /** 页面覆盖形式 */
    LOAD_PAGESATE
}