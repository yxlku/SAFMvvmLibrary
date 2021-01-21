package com.deti.brand.demand.create.item.file

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemUploadFileBinding
import com.deti.brand.demand.create.CreateDemandViewModel

/**
 * 上传文件
 */
class ItemUploadFile(
    var mViewModel: CreateDemandViewModel? = null
): QuickDataBindingItemBinder<ItemUploadFileEntity, BrandItemUploadFileBinding>() {

    override fun convert(
        holder: BinderDataBindingHolder<BrandItemUploadFileBinding>,
        data: ItemUploadFileEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = data
            viewModel = mViewModel
            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemUploadFileBinding = BrandItemUploadFileBinding.inflate(layoutInflater, parent, false)
}
