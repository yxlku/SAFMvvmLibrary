package com.safmvvm.ui.load

import com.zy.multistatepage.MultiState


/**
 * 所有等待模式的接口，方便统一调用
 */
interface ILoad {

    /**
     * 加载失败，显示 LoadSir 的页面
     */
    fun onLoadSirShowed(it: Class<out MultiState>) {}

    /**
     * 加载成功，LoadSir 消失，显示结果页
     */
    fun onLoadSirSuccess() {}

    /**
     * 如果出现错误页等需要重新加载的页面，可在此方法中接收回调
     */
    fun onLoadSirReload() {}


}