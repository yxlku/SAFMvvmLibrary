package com.test.common.ui.dialog.color.adapter

import com.safmvvm.bus.SingleLiveEvent
import com.safmvvm.bus.putValue

class ColorsViewModel {

    var selectedPosition = SingleLiveEvent<Int>()

    init {
        selectedPosition.putValue(0)
    }


}