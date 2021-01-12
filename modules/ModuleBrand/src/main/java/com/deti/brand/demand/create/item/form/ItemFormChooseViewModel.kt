package com.deti.brand.demand.create.item.form

import android.app.Activity
import android.view.View
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.brand.demand.create.item.service.ItemServiceEntity
import com.test.common.ui.dialog.single.BaseSingleChoiceEntity
import com.test.common.ui.dialog.single.createDialogSelectedSingle

class ItemFormChooseViewModel(
    var mActivity: Activity?,
    var mAdapter: BaseBinderAdapter,
) {
    fun clickItem(view: View, entity: ItemFormChooseEntity){
        when (entity.title) {
            "尺码类型" -> clickChooseSizeTypeDialog(view, entity)
            else -> {
            }
        }
    }


    fun clickChooseSizeTypeDialog(view: View, entity: ItemFormChooseEntity){
        mActivity?.let {
            arrayListOf(
                BaseSingleChoiceEntity("数字码"),
                BaseSingleChoiceEntity("字母码")
            ).createDialogSelectedSingle(it, "请选择尺码类型", callback = {
                entity.contentText = it.text
                mAdapter.notifyDataSetChanged()
            }).show()
        }
    }
}