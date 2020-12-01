package com.safmvvm.ui.load

/**
 * 所有等待模式的接口，方便统一调用
 */
interface ILoad {

    /**
     * 初始化Load
     */
    fun initLoad()

    /**
     * 显示Load
     */
    fun showLoad()

    /**
     * 关闭Load
     */
    fun dissLoad()

    /**
     * 点击重新加载
     */
    fun reLoad()

    /**
     * 销毁Load
     */
    fun destroyLoad()

    /**
     * 获取 LoadSir 的目标，通常是 Activity，或者是某个 view，LoadSir 的页面会挂在该 view 上
     */
    fun getLoadSirTarget(): Any? = null


}