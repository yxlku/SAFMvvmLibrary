package com.deti.brand.demand.create.item.express

import android.app.Activity
import android.view.View
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.brand.demand.create.item.service.ItemServiceEntity
import com.test.common.ui.dialog.single.BaseSingleChoiceEntity
import com.test.common.ui.dialog.single.createDialogSelectedSingle
import com.test.common.ui.dialog.tip.createDialogTip

class ItemExpressViewModel(
    var mActivity: Activity?,
    var mAdapter: BaseBinderAdapter,
) {
    fun clickChooseExpressDialog(view: View, entity: ItemExpressEntity){
        mActivity?.let {
            arrayListOf(
                BaseSingleChoiceEntity("顺丰快递"),
                BaseSingleChoiceEntity("啥都行快递")
            ).createDialogSelectedSingle(it, "请选择快递", callback = {
                entity.selectedExpressText = it.text
                mAdapter.notifyDataSetChanged()
            }).show()
        }
    }

    fun clickAddress(view: View){
        mActivity?.let {
            "浙江省杭州市余杭区临平区余杭商会大厦C座 联系电话：123 4567 8910".createDialogTip(it, view).show()
        }
    }
}