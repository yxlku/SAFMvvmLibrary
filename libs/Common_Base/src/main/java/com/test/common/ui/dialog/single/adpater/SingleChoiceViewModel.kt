package com.test.common.ui.dialog.single.adpater

import android.view.View
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.safmvvm.bus.SingleLiveEvent
import com.safmvvm.bus.putValue

class SingleChoiceViewModel {

    var selectedPosition = SingleLiveEvent<Int>()

    init {
        selectedPosition.putValue(0)
    }


}