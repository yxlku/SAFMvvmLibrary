package com.safmvvm.app

import com.safmvvm.R


/**
 * 检查工具，未通过会抛出异常，一定是异常，强制使用框架内容
 */
internal object CheckUtil {
    /**
     * 检查使用LoadSir时是否使用了getLoadSirTarget方法，控制显示位置
     */
    fun checkLoadSirEvent(event: Any?) {
        if (event == null) {
            throw RuntimeException(BaseApp.getInstance().getString(R.string.load_sir_tips))
        }
    }
    /**
     * 检查使用LoadSir时是否使用了getLoadSirTarget方法，控制显示位置
     */
    fun checkLoadDialogEvent(event: Any?) {
        if (event == null) {
            throw RuntimeException(BaseApp.getInstance().getString(R.string.load_sir_tips))
        }
    }
}