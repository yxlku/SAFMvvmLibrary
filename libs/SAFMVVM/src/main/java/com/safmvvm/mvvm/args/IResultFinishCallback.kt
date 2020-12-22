package com.safmvvm.mvvm.args

import android.content.Intent
import androidx.lifecycle.Observer
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.utils.jetpack.putValue

/**
 * 封装 ActivityResult 的回调接口，让回调在界面和 vm 中都可以收到。
 * 只有使用 V、View 中 start 开头的方法，此接口才保证有效
 */
interface IResultFinishCallback {

    fun onPageResult(tag: String, block:(resultCode: Int?, intent: Intent?)->Unit){
        LiveDataBus.observe<Pair<Int?, Intent?>>(
            this,
            tag,
            Observer {
                block(it.first, it.second)
            },
            true
        )
    }

}