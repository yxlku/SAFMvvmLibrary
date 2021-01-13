package com.test.common.ui.dialog.multiple.adapter

import com.safmvvm.bus.SingleLiveEvent
import com.safmvvm.bus.putValue

class MultipleChoiceViewModel {

    var selectedPosition = SingleLiveEvent<Boolean>()

    init {
        selectedPosition.putValue(false)
    }

}