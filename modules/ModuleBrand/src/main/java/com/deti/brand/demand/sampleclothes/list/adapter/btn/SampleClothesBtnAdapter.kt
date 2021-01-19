package com.deti.brand.demand.sampleclothes.list.adapter.btn

import android.app.Activity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.brand.R
import com.deti.brand.databinding.BrandItemSampleClothesListBtnBinding
import com.deti.brand.demand.sampleclothes.list.entity.SampleClothesListAllBtnEntity

class SampleClothesBtnAdapter(
    var mActivity: Activity?
): BaseQuickAdapter<SampleClothesListAllBtnEntity, BaseDataBindingHolder<BrandItemSampleClothesListBtnBinding>>(
    R.layout.brand_item_sample_clothes_list_btn
) {
    override fun getItemCount(): Int {
        if(data.size >= 3){
            return 3
        }else{
            return data.size
        }
    }

    override fun convert(
        holder: BaseDataBindingHolder<BrandItemSampleClothesListBtnBinding>,
        item: SampleClothesListAllBtnEntity,
    ) {
        var mViewMode = SampleClothesBtnViewModel(mActivity, this)
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            viewModel = mViewMode
            executePendingBindings()
        }
    }

}