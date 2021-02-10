package com.deti.designer.style.popup.item.radio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.designer.databinding.DesignerItemStyleEditRadioBinding

class ItemRadio: QuickDataBindingItemBinder<ItemRadioEntity, DesignerItemStyleEditRadioBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<DesignerItemStyleEditRadioBinding>,
        data: ItemRadioEntity,
    ) {
        var binding = holder.dataBinding
        binding.apply {
            entity = data
            //radio选项
            var radioAdapter = ItemRadioAdapter()
            rvContent.apply {
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
                adapter = radioAdapter
            }
            radioAdapter.setList(data.radioDatas)
            radioAdapter.setOnItemClickListener { adapter, view, position ->
                radioAdapter.checkPosition = position
                radioAdapter.notifyDataSetChanged()
            }
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): DesignerItemStyleEditRadioBinding = DesignerItemStyleEditRadioBinding.inflate(layoutInflater, parent, false)
}