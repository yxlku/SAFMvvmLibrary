package com.safmvvm.ext.ui.typesview.adapter.two

import com.safmvvm.bus.SingleLiveEvent
import com.safmvvm.bus.putValue

class TypesTwoViewModel {

    var selectedPosition = SingleLiveEvent<Int>()

    init {
        selectedPosition.putValue(0)
    }

}