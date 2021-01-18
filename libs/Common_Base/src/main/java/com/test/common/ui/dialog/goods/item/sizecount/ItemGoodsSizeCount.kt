package com.test.common.ui.dialog.goods.item.sizecount

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.binder.QuickViewBindingItemBinder
import com.chad.library.adapter.base.entity.node.BaseNode
import com.test.common.R
import com.test.common.databinding.BaseDialogItemGoodsSizeCountBinding
import com.test.common.ui.dialog.sizecount.adapter.SizeCountAdapter
import com.test.common.ui.dialog.sizecount.adapter.entity.FirstNodeEntity
import com.test.common.ui.dialog.sizecount.adapter.entity.SecondNodeEntity

class ItemGoodsSizeCount: QuickViewBindingItemBinder<ItemGoodsSizeCountEntity, BaseDialogItemGoodsSizeCountBinding>() {

    override fun onCreateViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BaseDialogItemGoodsSizeCountBinding = BaseDialogItemGoodsSizeCountBinding.inflate(layoutInflater, parent, false)

    override fun convert(
        holder: BinderVBHolder<BaseDialogItemGoodsSizeCountBinding>,
        data: ItemGoodsSizeCountEntity,
    ) {
        var binding = holder.viewBinding
        binding?.apply {
            var mAdapter = SizeCountAdapter(R.layout.base_dialog_item_goods_sizecount_first)
            rvContent.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
            }
            //模拟数据
            mAdapter.setList(testData())
            executePendingBindings()
        }
    }


    fun testData(): List<FirstNodeEntity> {
        var firstNode = arrayListOf<FirstNodeEntity>()
        for (i in 0 until 3) {
            var secondNodes = arrayListOf<BaseNode>()
            for (j in 0 until 5) {
                var secondNodeEntity = SecondNodeEntity(
                    j,
                    "${j}xs",
                    0,
                    "屎黄$i"
                )
                secondNodes.add(secondNodeEntity)
            }
            firstNode.add(FirstNodeEntity(i, "屎黄$i", 0, secondNodes))
        }
        return firstNode
    }
}