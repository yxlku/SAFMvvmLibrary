package com.deti.brand.demand.create.item.personinfo

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemPersonalInfoTipBinding
import com.deti.brand.demand.update.UpdateDemandActivity
import com.safmvvm.ext.rvIsShow
import com.test.common.ui.dialog.goods.createDialogGoodsDetail

/**
 * 完善信息
 */
class ItemPersonalInfoTip(
    var activty: Activity?
): QuickDataBindingItemBinder<ItemPersonalInfoEntity, BrandItemPersonalInfoTipBinding>() {

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemPersonalInfoTipBinding {
        return BrandItemPersonalInfoTipBinding.inflate(layoutInflater, parent, false)
    }

    override fun convert(
        holder: BinderDataBindingHolder<BrandItemPersonalInfoTipBinding>,
        data: ItemPersonalInfoEntity,
    ) {
        var binding = holder.dataBinding
        binding.apply {
            entity = data
            holder.itemView.rvIsShow(data.isShow)
            tvInfoBtn.setOnClickListener {
                clickToPerfectPersonalPage(it)
            }
            executePendingBindings()
        }

    }


    fun clickToPerfectPersonalPage(view: View){
//        RouterUtil.startActivity(RouterActivityPath.ModuleBasis.PAGE_PERFECT_PERSONAL)

        activty?.apply {
//            createDialogGoodsDetail(this).show()
            UpdateDemandActivity.startAction(this)
        }
    }

}