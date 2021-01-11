package com.test.common.ui.dialog.multiple.adapter

import android.view.View
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.safmvvm.bus.SingleLiveEvent
import com.safmvvm.bus.putValue

class MultipleChoiceViewModel {

    var selectedPosition = SingleLiveEvent<Int>()

    init {
        selectedPosition.putValue(0)
    }


}