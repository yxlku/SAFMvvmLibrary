package com.deti.brand.demand.create.item.service

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemDemandServiceBinding
import com.deti.brand.demand.create.CreateDemandViewModel
import com.test.common.ui.popup.base.BaseSingleChoiceEntity
import com.test.common.ui.popup.dialogBottomSingle

/**
 * 服务
 */
class ItemService(
    var activity: Activity?,
    var mViewModel: CreateDemandViewModel? = null
): QuickDataBindingItemBinder<ItemServiceEntity, BrandItemDemandServiceBinding>() {

    /** 服务类型*/
    var serviceTypeData = arrayListOf(
        BaseSingleChoiceEntity("fob", "包工包料"),
        BaseSingleChoiceEntity("cmt", "纯加工"),
    )
    /** 对应服务*/
    var serviceProduceData = arrayListOf(
        BaseSingleChoiceEntity("sample_bulk", "打版 + 生产"),
        BaseSingleChoiceEntity("bulk", "仅生产"),
    )
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemDemandServiceBinding>,
        data: ItemServiceEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            viewModel = mViewModel
            entity = data
            //服务类型
            clServiceType.setOnClickListener {
                activity?.apply {
                    serviceTypeData.dialogBottomSingle(this, "请选择服务类型", callback = { selectData, position ->
                        data.mServiceType.set(selectData)
                    }).show()
                }
            }
            //对应服务
            clServiceProduce.setOnClickListener {
                activity?.apply {
                    serviceProduceData.dialogBottomSingle(this, "请选择对应服务", callback = { selectData, position->
                        data.mServiceProduce.set(selectData)
                    }).show()
                }
            }

            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemDemandServiceBinding = BrandItemDemandServiceBinding.inflate(layoutInflater, parent, false)
}