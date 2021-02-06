package com.deti.brand.demand.create.item.pic

import androidx.databinding.ObservableField
import com.deti.brand.demand.create.item.IItemIsShow

data class ItemPicChooseEntity(
    var pics: ArrayList<ItemPicChooseItemEntity> = arrayListOf(),
): IItemIsShow()

data class ItemPicChooseItemEntity(
    var tipText: String = "",
    var picPath: ObservableField<String> = ObservableField(),

)