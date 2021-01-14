package com.safmvvm.ext.ui.typesview.adapter.three

import com.safmvvm.bus.SingleLiveEvent
import com.safmvvm.bus.putValue

class TypesThreeViewModel {

    var selectedPosition = SingleLiveEvent<Int>()

    init {
        selectedPosition.putValue(0)
    }

}