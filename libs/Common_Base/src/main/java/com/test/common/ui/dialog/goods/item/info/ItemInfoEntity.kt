package com.test.common.ui.dialog.goods.item.info

import androidx.annotation.DrawableRes

data class ItemInfoEntity(
    var tagId: Int = -1,
    var title: String = "",
    var content: String = "",
    @DrawableRes var icon: Int = 0
)

object ItemInfoTagIds{
    /** 其他*/
    var OTHER = 1
    /** 货期*/
    var ID_GOODS_TIME = 2
}