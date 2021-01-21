package com.deti.brand.demand.create.item.service

import android.app.Activity
import android.view.View
import com.chad.library.adapter.base.BaseBinderAdapter
import com.test.common.ui.popup.base.BaseSingleChoiceEntity
import com.test.common.ui.dialog.single.createDialogSelectedSingle

class ItemServiceViewModel(
    var mActivity: Activity?,
    var mAdapter: BaseBinderAdapter,
) {

    fun clickChooseTypeDialog(view: View, entity: ItemServiceEntity){
        mActivity?.let {
            arrayListOf(
                BaseSingleChoiceEntity("0", "包工包料"),
                BaseSingleChoiceEntity("0", "纯加工")
            ).createDialogSelectedSingle(it, "请选择服务类型", callback = {
                entity.selectedTypeText = it.text
                mAdapter.notifyDataSetChanged()
            }).show()
        }
    }

    fun clickChooseServiceDialog(view: View, entity: ItemServiceEntity){
        mActivity?.let {
            arrayListOf(
                BaseSingleChoiceEntity("0", "打版+生产"),
                BaseSingleChoiceEntity("0", "仅生产")
            ).createDialogSelectedSingle(it, "请选择服务类型", callback = {
                entity.seletedServiceText = it.text
                mAdapter.notifyDataSetChanged()
            }).show()
        }
    }
}