package com.deti.designer.style.popup

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.designer.BR
import com.deti.designer.R
import com.deti.designer.databinding.DesignerPopupStyleEditBinding
import com.deti.designer.style.popup.item.choose.ItemChoose
import com.deti.designer.style.popup.item.choose.ItemChooseEntity
import com.deti.designer.style.popup.item.input.ItemInput
import com.deti.designer.style.popup.item.input.ItemInputEntity
import com.deti.designer.style.popup.item.other.ItemOther
import com.deti.designer.style.popup.item.other.ItemOtherEntity
import com.deti.designer.style.popup.item.radio.ItemRadio
import com.deti.designer.style.popup.item.radio.ItemRadioData
import com.deti.designer.style.popup.item.radio.ItemRadioEntity
import com.safmvvm.mvvm.view.bottom.BaseBottomFragment

/**
 * 编辑款式
 */
class StyleEditDialogFragment :
    BaseBottomFragment<DesignerPopupStyleEditBinding, StyleEditViewModel>(
        R.layout.designer_popup_style_edit,
        BR.viewModel
    ) {


    var mAdapter = BaseBinderAdapter()
    override fun initData() {
        super.initData()

        mAdapter.apply {
            addItemBinder(ItemInputEntity::class.java, ItemInput())
            addItemBinder(ItemChooseEntity::class.java, ItemChoose())
            addItemBinder(ItemRadioEntity::class.java, ItemRadio())
            addItemBinder(ItemOtherEntity::class.java, ItemOther())
        }

        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        var list = arrayListOf(
            ItemChooseEntity("0", "客户", "请选择客户"),
            ItemInputEntity("0", "款式名称", "请输入款式名称"),
            ItemChooseEntity("0", "版型", "请选择编号"),
            ItemChooseEntity("0", "款式颜色", "请选择编号"),
            ItemChooseEntity("0", "性别", "请选择编号"),
            ItemChooseEntity("0", "品类", "请选择编号"),
            ItemChooseEntity("0", "类型", "请选择品名"),
            ItemChooseEntity("0", "分类", "请选择供应商"),
            ItemChooseEntity("0", "季节", "请选择品名"),
            ItemChooseEntity("0", "版师", "请选择品名"),
            ItemChooseEntity("0", "工艺师", "请选择品名"),
            ItemChooseEntity("0", "面料特性", "请选择品名"),

            //指数
            itemRadioVertical(),
            itemRadioTransparent(),
            itemRadioElasticity(),
            itemRadioSkinFriendly(),
            itemRadioThickness(),

            //其他
            ItemOtherEntity()
        )
        mAdapter.setList(list)
    }

    /** 垂直指数*/
    fun itemRadioVertical(): ItemRadioEntity = ItemRadioEntity(
        id = "radio_vertical",
        title = "垂直指数",
        radioDatas = arrayListOf(
            ItemRadioData("0", "垂坠"),
            ItemRadioData("1", "微骨架"),
            ItemRadioData("2", "挺括"),
            ItemRadioData("3", "硬挺"),
        )
    )
    /** 透明指数*/
    fun itemRadioTransparent(): ItemRadioEntity = ItemRadioEntity(
        id = "radio_transparent",
        title = "透明指数",
        radioDatas = arrayListOf(
            ItemRadioData("0", "不透明 "),
            ItemRadioData("1", "微透"),
            ItemRadioData("2", "透明"),
        )
    )

    /** 弹力指数*/
    fun itemRadioElasticity(): ItemRadioEntity = ItemRadioEntity(
        id = "radio_elasticity",
        title = "弹力指数",
        radioDatas = arrayListOf(
            ItemRadioData("0", "无弹 "),
            ItemRadioData("1", "微弹"),
            ItemRadioData("2", "弹力"),
            ItemRadioData("3", "超弹"),
        )
    )

    /** 亲肤指数*/
    fun itemRadioSkinFriendly(): ItemRadioEntity = ItemRadioEntity(
        id = "radio_SkinFriendly",
        title = "亲肤指数",
        radioDatas = arrayListOf(
            ItemRadioData("0", "加衬 "),
            ItemRadioData("1", "不适合贴身"),
            ItemRadioData("2", "可贴身"),
        )
    )

    /** 厚度指数*/
    fun itemRadioThickness(): ItemRadioEntity = ItemRadioEntity(
        id = "radio_Thickness",
        title = "厚度指数",
        radioDatas = arrayListOf(
            ItemRadioData("0", "薄"),
            ItemRadioData("1", "偏薄"),
            ItemRadioData("2", "适中"),
            ItemRadioData("3", "偏厚"),
            ItemRadioData("4", "厚"),
        )
    )
}