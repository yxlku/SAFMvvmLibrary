package com.safmvvm.utils

import android.os.SystemClock
import com.safmvvm.ui.toast.ToastUtil

/**
 * 双击事件
 */
object DoubleClickUtil {

    /** 数组的长度为2代表只记录双击操作 */
    private var TIME_ARRAY = LongArray(2)

    /**
     * 是否是双击
     * @param time 两次点击时间间隔（毫秒）
     */
    fun isOnDoubleClick(time: Int = 1500): Boolean {
        System.arraycopy(TIME_ARRAY, 1, TIME_ARRAY, 0, TIME_ARRAY.size - 1 )
        TIME_ARRAY[TIME_ARRAY.size - 1] = SystemClock.uptimeMillis()
        return TIME_ARRAY[0] >= (SystemClock.uptimeMillis() - time)
    }

    fun checkDoubleClick(
        time: Int = 1500,
        msg: String,
        block: () -> Unit = {},
        tipBlock: () -> Unit = { ToastUtil.showShortToast(msg) }
    ) {
        if (isOnDoubleClick(time)) {
            block()
        } else {
            tipBlock()
        }
    }
}