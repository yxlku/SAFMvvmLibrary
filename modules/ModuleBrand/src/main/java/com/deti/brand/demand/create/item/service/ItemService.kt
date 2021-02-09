package com.deti.brand.demand.create.item.service

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemDemandServiceBinding
import com.deti.brand.demand.create.CreateDemandViewModel
import com.test.common.dictionary.DictionaryServiceCorresponde
import com.test.common.dictionary.DictionaryServiceType
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
        BaseSingleChoiceEntity(DictionaryServiceType.PRODUCE.key, DictionaryServiceType.PRODUCE.value),
        BaseSingleChoiceEntity(DictionaryServiceType.PRODUCE_PATTERN.key, DictionaryServiceType.PRODUCE_PATTERN.value),
    )
    /** 对应服务*/
    var serviceProduceData = arrayListOf(
        BaseSingleChoiceEntity(DictionaryServiceCorresponde.PRODUCE_PATTERN.key, DictionaryServiceCorresponde.PRODUCE_PATTERN.value),
        BaseSingleChoiceEntity(DictionaryServiceCorresponde.PRODUCE.key, DictionaryServiceCorresponde.PRODUCE.value),
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