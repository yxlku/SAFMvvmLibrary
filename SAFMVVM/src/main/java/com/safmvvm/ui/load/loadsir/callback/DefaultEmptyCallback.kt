package com.safmvvm.ui.load.loadsir.callback

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.safmvvm.R

/**
 * 默认空布局
 */
class DefaultEmptyCallback: Callback() {

    override fun onCreateView(): Int = R.layout.layout_loadsir_default_empty

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }

}