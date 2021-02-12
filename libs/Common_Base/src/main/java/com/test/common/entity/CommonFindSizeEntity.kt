package com.test.common.entity

import com.test.common.ui.popup.base.BaseSingleChoiceEntity
import java.io.Serializable

/**
 * 通用尺码组实体类
 */
data class CommonFindSizeEntity(
    var list: List<CommonFindSizeDataBean> = arrayListOf()
): Serializable

data class CommonFindSizeDataBean(
//    var id: String = "",
    /** 一级类别*/
    var gender: String = "",
    /** 二级类别*/
    var category: String = "",
    /** 三级类别*/
    var suitType: String = "",

    /** 尺码组名称*/
    var label: String = "",
    /** 尺码组*/
    var sizeRangeList: List<String> = arrayListOf(),
): BaseSingleChoiceEntity(), Serializable{
    override var text: String
        get() = label
        set(value) {}
}