package com.safmvvm.ext.ui.typesview.adapter.four

import com.safmvvm.bus.SingleLiveEvent
import com.safmvvm.bus.putValue

class TypesFourViewModel {

    var selectedPosition = SingleLiveEvent<Int>()

    init {
        selectedPosition.putValue(0)
    }

}