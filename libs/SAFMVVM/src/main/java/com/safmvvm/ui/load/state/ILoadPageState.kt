package com.safmvvm.ui.load.state

import androidx.annotation.DrawableRes

/**
 * 所有自定义状态实现此接口的通用方法
 */
interface ILoadPageState {

    fun setMsg(msg: String?)

    fun setSubMsg(subMsg: String?)

    fun setIcon(@DrawableRes icon: Int)

}