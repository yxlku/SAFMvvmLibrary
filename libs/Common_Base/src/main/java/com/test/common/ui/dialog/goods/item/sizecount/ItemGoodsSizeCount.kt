package com.test.common.ui.dialog.goods.item.sizecount

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.binder.QuickViewBindingItemBinder
import com.test.common.R
import com.test.common.databinding.BaseDialogItemGoodsSizeCountBinding
import com.test.common.ui.popup.custom.sizecount.adapter.SizeCountAdapter

class ItemGoodsSizeCount(
    var mActivity: Activity,
): QuickViewBindingItemBinder<ItemGoodsSizeCountEntity, BaseDialogItemGoodsSizeCountBinding>() {

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
        binding.apply {
            var mAdapter = SizeCountAdapter(mActivity, R.layout.base_dialog_item_goods_sizecount_first)
            rvContent.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
            }
            //模拟数据
    //            mAdapter.setList(testData())
            executePendingBindings()
        }
    }


//    fun testData(): List<FirstNodeEntity> {
//        var firstNode = arrayListOf<FirstNodeEntity>()
//        for (i in 0 until 3) {
//            var secondNodes = arrayListOf<BaseNode>()
//            for (j in 0 until 5) {
//                var secondNodeEntity = SecondNodeEntity(
//                    j,
//                    "${j}xs",
//                    0,
//                    "屎黄$i"
//                )
//                secondNodes.add(secondNodeEntity)
//            }
//            firstNode.add(FirstNodeEntity("i", "屎黄$i", 0, secondNodes))
//        }
//        return firstNode
//    }
}