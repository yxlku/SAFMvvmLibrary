package com.deti.designer.materiel.popup.craft

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.designer.R
import com.deti.designer.databinding.DesignerItemAddCraftItemBinding
import com.deti.designer.materiel.popup.craft.item.choose.ItemChooseCraft
import com.deti.designer.materiel.popup.craft.item.choose.ItemChooseCraftEntity
import com.deti.designer.materiel.popup.craft.item.del.ItemDelCraft
import com.deti.designer.materiel.popup.craft.item.del.ItemDelCraftEntity
import com.deti.designer.materiel.popup.craft.item.input.ItemInputCraft
import com.deti.designer.materiel.popup.craft.item.input.ItemInputCraftEntity
import com.deti.designer.materiel.popup.craft.item.title.ItemTitleCraft
import com.deti.designer.materiel.popup.craft.item.title.ItemTitleCraftEntity

class AddCraftAdapter(
    var mViewModel: AddCraftViewModel
): BaseQuickAdapter<AddCraftItemEntity, BaseDataBindingHolder<DesignerItemAddCraftItemBinding>>(
    R.layout.designer_item_add_craft_item
) {

    override fun convert(
        holder: BaseDataBindingHolder<DesignerItemAddCraftItemBinding>,
        item: AddCraftItemEntity,
    ) {
        var databinding = holder.dataBinding
        databinding?.apply {
            viewModel = mViewModel
            var mAdapter = BaseBinderAdapter()
            var itemList = arrayListOf(
                ItemTitleCraftEntity("特殊工艺"),
                ItemChooseCraftEntity("0", "工艺供应商", "请选择工艺供应商", "", true),
                ItemChooseCraftEntity("1", "处理阶段", "请选择编号", "", true),
                ItemInputCraftEntity("0", "价格", "请输入价格", "", "元", true),
                ItemChooseCraftEntity("2", "提供方", "请选择品名", "", true),
                ItemDelCraftEntity()
            )
            mAdapter.apply {
                addItemBinder(ItemTitleCraftEntity::class.java, ItemTitleCraft(mViewModel))
                addItemBinder(ItemChooseCraftEntity::class.java, ItemChooseCraft(mViewModel))
                addItemBinder(ItemInputCraftEntity::class.java, ItemInputCraft(mViewModel))
                addItemBinder(ItemDelCraftEntity::class.java, ItemDelCraft(mAdapter))
            }
            rvContent.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
            }
            mAdapter.setList(itemList)
            executePendingBindings()
        }
    }
}