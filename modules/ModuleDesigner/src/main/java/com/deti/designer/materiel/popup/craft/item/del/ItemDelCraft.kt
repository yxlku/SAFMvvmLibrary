package com.deti.designer.materiel.popup.craft.item.del

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseBinderAdapter
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.designer.databinding.DesignerItemAddCraftDelBinding

class ItemDelCraft(
    var mAdapter: BaseBinderAdapter,
) : QuickDataBindingItemBinder<ItemDelCraftEntity, DesignerItemAddCraftDelBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<DesignerItemAddCraftDelBinding>,
        data: ItemDelCraftEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = data
            tvDel.setOnClickListener {
                mAdapter.setList(arrayListOf())
            }
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): DesignerItemAddCraftDelBinding =
        DesignerItemAddCraftDelBinding.inflate(layoutInflater, parent, false)
}