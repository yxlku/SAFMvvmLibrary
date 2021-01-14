package com.safmvvm.ext.ui.typesview.adapter.first

import com.safmvvm.bus.SingleLiveEvent
import com.safmvvm.bus.putValue

class TypesFirstViewModel {

    var selectedPosition = SingleLiveEvent<Int>()

    init {
        selectedPosition.putValue(0)
    }

}