package com.deti.designer.materiel.popup.detaile.item.type

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.designer.databinding.DesignerItemCraftTypeDataBinding
import com.deti.designer.materiel.popup.detaile.CraftDeatilTypeData
import com.deti.designer.materiel.popup.detaile.CraftDeatileTypeEntity
import com.deti.designer.materiel.popup.detaile.item.choose.ItemChoose
import com.deti.designer.materiel.popup.detaile.item.choose.ItemChooseEntity
import com.test.common.ui.line.ItemGrayLine
import com.test.common.ui.line.ItemGrayLineEntity

/**
 * 工艺详情信息
 */
class ItemCraftType() :
    QuickDataBindingItemBinder<ItemCraftEntity, DesignerItemCraftTypeDataBinding>() {
    override fun convert(
        holder: BinderDataBindingHolder<DesignerItemCraftTypeDataBinding>,
        data: ItemCraftEntity,
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
            tabAdapter.setList(data.craftList)

            //信息适配器
            var infoAdapter = BaseBinderAdapter()
            infoAdapter.apply {
                addItemBinder(ItemChooseEntity::class.java, ItemChoose())
                addItemBinder(ItemGrayLineEntity::class.java, ItemGrayLine())
            }
            rvContent.apply {
                layoutManager = GridLayoutManager(context, 1)
                adapter = infoAdapter
            }
            infoAdapter.setOnItemClickListener { adapter, view, position ->
                //弹窗
            }
            switchInfoPage(tabAdapter, infoAdapter, 0)

            tabAdapter.setOnItemClickListener { adapter, view, position ->
                switchInfoPage(tabAdapter, infoAdapter, position)
            }


            tvDel.setOnClickListener {
                //tab删除
                var isDel = !tabAdapter.isShowDel
                tabAdapter.isShowDel = isDel
                if (isDel) {
                    tvDel.text = "完成"
                } else {
                    tvDel.text = "删除"
                }
                tabAdapter.notifyDataSetChanged()
                switchInfoPage(tabAdapter, infoAdapter, tabAdapter.isSelectedPosition)
            }
            executePendingBindings()
        }
    }

    fun switchInfoPage(tabAdapter: TabAdapter, infoAdapter: BaseBinderAdapter, position: Int) {
        //刷新列表
        tabAdapter.isSelectedPosition = position
        tabAdapter.notifyDataSetChanged()

        //更新信息数据
        var info = tabAdapter.data[position] as CraftDeatileTypeEntity
        infoAdapter.setList(typeInfo(info.craftTypeData))
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): DesignerItemCraftTypeDataBinding =
        DesignerItemCraftTypeDataBinding.inflate(layoutInflater, parent, false)

    fun typeInfo(info: CraftDeatilTypeData): ArrayList<Any> {
        var infos = arrayListOf<Any>()
        infos.apply {
            add(ItemChooseEntity("0", "提供方", info.craftTGF))
            add(ItemChooseEntity("0", "品名", "234"))
            add(ItemChooseEntity("0", "总金额", "234"))
            add(ItemChooseEntity("0", "处理阶段", "234"))
            add(ItemChooseEntity("0", "工艺单位", "234"))
            add(ItemChooseEntity("0", "工艺供应商", "234"))
        }
        return infos
    }
}