package com.deti.designer.version.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.designer.R
import com.deti.designer.databinding.DesignerItemDemandOrderBinding
import com.deti.designer.version.entity.DemandOrderEntity
import com.deti.designer.version.entity.GoodsDataEntity
import com.test.common.adapter.CommonListBtnsAdapter
import com.test.common.adapter.CommonListBtnsEntity

class DemandOrderAdapter :
    BaseQuickAdapter<DemandOrderEntity, BaseDataBindingHolder<DesignerItemDemandOrderBinding>>(
        R.layout.designer_item_demand_order
    ) {
    override fun convert(
        holder: BaseDataBindingHolder<DesignerItemDemandOrderBinding>,
        item: DemandOrderEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            var goodsAdapter = GoodsListAdapter()
            rvGoodsList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = goodsAdapter
            }
            goodsAdapter.setList(item.goosList)

            var btnAdapter = CommonListBtnsAdapter()
            rvBtns.apply {
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
                adapter = btnAdapter
            }
            btnAdapter.setList(btnsDatas(item.goosList))

            executePendingBindings()
        }
    }

    companion object {
        /** 添加版单*/
        const val BTN_TYPE_ADD = "btn_type_add"

        /** 版单详情*/
        const val BTN_TYPE_DETAIL = "btn_type_detail"
    }

    fun btnsDatas(goosList: List<GoodsDataEntity>): List<CommonListBtnsEntity> {
        var btnsList = arrayListOf<CommonListBtnsEntity>()
        if (goosList.size <= 1) {
            btnsList.add(
                CommonListBtnsEntity(BTN_TYPE_DETAIL, "版单详情", R.drawable.base_btn_yellow_bg)
            )
        } else {
            btnsList.add(
                CommonListBtnsEntity(BTN_TYPE_ADD, "添加版单", R.drawable.base_btn_yellow_bg)
            )
            btnsList.add(
                CommonListBtnsEntity(BTN_TYPE_DETAIL, "版单详情", R.drawable.base_btn_gray_bg)
            )
        }
        return btnsList
    }
}