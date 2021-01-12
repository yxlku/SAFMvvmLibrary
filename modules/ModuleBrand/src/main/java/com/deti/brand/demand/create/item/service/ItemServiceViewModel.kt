package com.deti.brand.demand.create.item.service

import android.app.Activity
import android.view.View
import com.chad.library.adapter.base.BaseBinderAdapter
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemDemandServiceBinding
import com.safmvvm.bus.putValue
import com.test.common.ui.dialog.single.BaseSingleChoiceEntity
import com.test.common.ui.dialog.single.createDialogSelectedSingle

class ItemServiceViewModel(
    var mActivity: Activity?,
    var mAdapter: BaseBinderAdapter,
) {

    fun clickChooseTypeDialog(view: View, entity: ItemServiceEntity){
        mActivity?.let {
            arrayListOf(
                BaseSingleChoiceEntity("包工包料"),
                BaseSingleChoiceEntity("纯加工")
            ).createDialogSelectedSingle(it, "请选择服务类型", callback = {
                entity.selectedTypeText = it.text
                mAdapter.notifyDataSetChanged()
            }).show()
        }
    }

    fun clickChooseServiceDialog(view: View, entity: ItemServiceEntity){
        mActivity?.let {
            arrayListOf(
                BaseSingleChoiceEntity("打版+生产"),
                BaseSingleChoiceEntity("仅生产")
            ).createDialogSelectedSingle(it, "请选择服务类型", callback = {
                entity.seletedServiceText = it.text
                mAdapter.notifyDataSetChanged()
            }).show()
        }
    }
}