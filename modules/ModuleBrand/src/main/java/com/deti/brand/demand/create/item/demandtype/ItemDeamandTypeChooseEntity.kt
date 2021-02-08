package com.deti.brand.demand.create.item.demandtype

import androidx.databinding.ObservableField
import com.test.common.ui.popup.multiple.BaseMultipleChoiceEntity

/**
 * 类型选择
 */
data class ItemDeamandTypeChooseEntity(
    /** 选中后显示的文字*/
    var showText: ObservableField<String> = ObservableField(),
    /** 类型选择*/
    var mChooseTypes: ArrayList<BaseMultipleChoiceEntity> = arrayListOf<BaseMultipleChoiceEntity>()
)

