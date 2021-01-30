package com.deti.designer.materiel.popup.detaile.item.type

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.designer.databinding.DesignerItemMaterielTypeDataBinding
import com.deti.designer.materiel.popup.detaile.MaterielDeatilEntity

class ItemMaterielType: QuickDataBindingItemBinder<MaterielDeatilEntity, DesignerItemMaterielTypeDataBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<DesignerItemMaterielTypeDataBinding>,
        data: MaterielDeatilEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = data

            var tabAdapter = TabAdapter()
            rvTab.apply {
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
                adapter = tabAdapter
            }
            tabAdapter.setList(data.typeList)
            tabAdapter.setOnItemClickListener { adapter, view, position ->
                //选中，切换列表
                tabAdapter.isSelectedPosition = position
                tabAdapter.notifyDataSetChanged()
            }
            tvDel.setOnClickListener {
                var isDel = !tabAdapter.isShowDel
                tabAdapter.isShowDel = isDel
                if (isDel) {
                    tvDel.text = "完成"
                }else{
                    tvDel.text = "删除"
                }
                tabAdapter.notifyDataSetChanged()
            }
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): DesignerItemMaterielTypeDataBinding = DesignerItemMaterielTypeDataBinding.inflate(layoutInflater, parent, false)
}