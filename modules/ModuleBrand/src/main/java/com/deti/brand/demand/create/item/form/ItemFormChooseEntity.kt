package com.deti.brand.demand.create.item.form

import androidx.databinding.ObservableField
import com.safmvvm.ext.ui.typesview.TypesViewDataBean

/**
 * form选择类型
 */
class ItemFormChooseEntity(
    /** ItemFormChooseType*/
    var tag: Int = -1,
    /** item标题*/
    var title: String = "",
    /** 必填 * 是否显示*/
    var tipXShow: Boolean = false,
    /** 提示文字和选中*/
    var hintText: String = "请选择",
    /** 选择后的文字*/
    var contentText: ObservableField<String> = ObservableField(),
)

/**
 * form选择类型
 */
object ItemFormChooseType{
    /** 款式分类*/
    var CHOOSE_STYLE = 0
    /** 尺码类型*/
    var CHOOSE_SIZE_TYPE = 1
    /** 颜色选择*/
    var CHOOSE_COLOR = 2
    /** 尺码数量*/
    var CHOOSE_SIZE_COUNT = 3
    /** 设置交期*/
    var CHOOSE_TIME = 4
}