package com.test.common.ui.item.listinfo

import android.graphics.Color
import com.safmvvm.app.BaseApp
import me.jessyan.autosize.utils.AutoSizeUtils

/**
 * 列表信息
 */
data class ItemListInfoEntity(
    var id: String = "",
    var title: String = "",
    var content: String = "",
    /** 间隔*/
    var interval: Int = AutoSizeUtils.mm2px(BaseApp.getInstance(), 8F),
    var titleColor: Int = Color.parseColor("#AAAAAA"),
    var contentColor: Int = Color.parseColor("#666666"),
    var titleSize: Int = AutoSizeUtils.mm2px(BaseApp.getInstance(), 12F),
    var contentSize: Int = AutoSizeUtils.mm2px(BaseApp.getInstance(), 12F)
)