package com.deti.designer.materiel.popup.detaile.item.type

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.designer.R
import com.deti.designer.databinding.DesignerItemMaterielTabBinding
import com.deti.designer.materiel.popup.detaile.MaterielDeatilTypeEntity
import com.deti.designer.materiel.popup.detaile.TabEntity

class TabAdapter: BaseQuickAdapter<TabEntity, BaseDataBindingHolder<DesignerItemMaterielTabBinding>>(
    R.layout.designer_item_materiel_tab
) {
    /** 是否显示删除*/
    var isShowDel: Boolean = false

    /** 选中位置*/
    var isSelectedPosition: Int = 0

    override fun convert(
        holder: BaseDataBindingHolder<DesignerItemMaterielTabBinding>,
        item: TabEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            //删除按钮
            ivDel.visibility = if (isShowDel) {
                View.VISIBLE
            }else{
                View.GONE
            }
            ivDel.setOnClickListener{
                remove(item)
            }

            //选中状态
            vLine.visibility = if (isSelectedPosition == holder.adapterPosition) {
                View.VISIBLE
            }else{
                View.GONE
            }
            executePendingBindings()
        }
    }
}