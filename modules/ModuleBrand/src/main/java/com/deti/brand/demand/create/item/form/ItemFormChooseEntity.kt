package com.deti.brand.demand.create.item.form

import androidx.databinding.ObservableField
import com.safmvvm.ext.ui.typesview.TypesViewDataBean
import com.test.common.entity.CommonColorEntity
import com.test.common.entity.CommonFindSizeDataBean
import com.test.common.ui.popup.custom.color.DemandColorDataBean

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


    /** 款式分类一*/
    var mStyleList: ArrayList<TypesViewDataBean?> = arrayListOf(),

    /** 选中的尺码类型 -- 从当前字段获取选中的尺码组Id -- sizeId*/
    var mSizeTypeData: CommonFindSizeDataBean? = null,

    /** 选择的颜色*/
    var mSelectColorDatas: ArrayList<DemandColorDataBean> = arrayListOf(),

    /** 颜色-尺码-数量*/
    var mColorSizeCountDatas: ArrayList<CommonColorEntity> = arrayListOf(),

    /** 交期*/
    var mTime: String = ""

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