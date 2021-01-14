package com.deti.brand.demand.create.item.file

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemUploadFileBinding

class ItemUploadFile(
    var mActivity: AppCompatActivity?
): QuickDataBindingItemBinder<ItemUploadFileEntity, BrandItemUploadFileBinding>() {


    override fun convert(
        holder: BinderDataBindingHolder<BrandItemUploadFileBinding>,
        data: ItemUploadFileEntity,
    ) {
        var mViewModel = ItemUploadFileViewModel(mActivity, adapter)
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