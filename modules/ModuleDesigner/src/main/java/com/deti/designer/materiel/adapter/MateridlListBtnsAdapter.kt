package com.deti.designer.materiel.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.designer.R
import com.deti.designer.databinding.DesignerItemMaterielListBtnsBinding
import com.deti.designer.materiel.entity.MaterielListBtnsEntity

/**
 * 列表按钮
 */
class MateridlListBtnsAdapter: BaseQuickAdapter<MaterielListBtnsEntity, BaseDataBindingHolder<DesignerItemMaterielListBtnsBinding>>(
    R.layout.designer_item_materiel_list_btns
) {
    override fun convert(
        holder: BaseDataBindingHolder<DesignerItemMaterielListBtnsBinding>,
        item: MaterielListBtnsEntity,
    ) {
        var databind = holder.dataBinding
        databind?.apply {
            entity = item
            executePendingBindings()
        }
    }
}