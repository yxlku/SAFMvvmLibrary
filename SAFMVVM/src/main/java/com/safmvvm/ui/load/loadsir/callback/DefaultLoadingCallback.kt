package com.safmvvm.ui.load.loadsir.callback

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.safmvvm.R

/**
 * 默认等待效果
 */
class DefaultLoadingCallback: Callback() {

    override fun onCreateView(): Int =  R.layout.layout_loadsir_default_loading

}