package com.deti.brand.demand.create.item.demandtype

import androidx.databinding.ObservableField

/**
 * 类型选择
 */
data class ItemDeamandTypeChooseEntity(
    var showText: ObservableField<String> = ObservableField(),
    /** 选择输入信息类型*/
    var types: ArrayList<String> = arrayListOf()
)

