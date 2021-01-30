package com.deti.designer.materiel.popup.detaile.item.type

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.designer.databinding.DesignerItemMaterielTypeDataBinding
import com.deti.designer.materiel.popup.detaile.MaterielDeatilEntity
import com.deti.designer.materiel.popup.detaile.MaterielDeatilTypeData
import com.deti.designer.materiel.popup.detaile.MaterielDeatilTypeEntity
import com.deti.designer.materiel.popup.detaile.item.choose.ItemChoose
import com.deti.designer.materiel.popup.detaile.item.choose.ItemChooseEntity

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

            //信息适配器
            var infoAdapter = BaseBinderAdapter()
            infoAdapter.apply {
                addItemBinder(ItemChooseEntity::class.java, ItemChoose())
            }
            rvContent.apply {
                layoutManager = GridLayoutManager(context, 1)
                adapter = infoAdapter
            }
            infoAdapter.setOnItemClickListener { adapter, view, position ->
                //弹窗
            }
            tabAdapter.setOnItemClickListener { adapter, view, position ->
                //更新信息数据
                var info = adapter.data[position] as MaterielDeatilTypeEntity
                infoAdapter.setList(typeInfo(info.materielTypeData))
                //选中，切换列表
                tabAdapter.isSelectedPosition = position
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

    fun typeInfo(info: MaterielDeatilTypeData): ArrayList<ItemChooseEntity>{
        var infos = arrayListOf<ItemChooseEntity>()
        infos.apply {
            add(ItemChooseEntity("0", "供应商", info.productName))
            add(ItemChooseEntity("0", "品名", info.supplierName))
            add(ItemChooseEntity("0", "编号", "123"))
        }
        return infos
    }
}