package com.deti.brand.demand.create.item.pic

import androidx.databinding.ObservableField

data class ItemPicChooseEntity(
    var pics: ArrayList<ItemPicChooseItemEntity> = arrayListOf(),
    /** 是否显示此布局*/
    var isShow: ObservableField<Boolean> = ObservableField(true)
)

data class ItemPicChooseItemEntity(
    var tipText: String = "",
    var picPath: String = "",

)