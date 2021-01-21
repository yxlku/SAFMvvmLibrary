package com.deti.brand.demand.create.item.pic

import android.app.Activity
import android.view.View
import com.safmvvm.app.BaseApp
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.utils.LogUtil
import com.test.common.ui.dialog.pic.createDialogPhotoSelect

class ItemPicChooseItemViewModel(
    var mActivity: Activity?,
    var mAdapter: ItemPicChooseItemAdapter,
) : BaseViewModel<BaseModel>(BaseApp.getInstance()) {
    fun clickDel(v: View, entity: ItemPicChooseItemEntity) {
        entity.picPath = ""
        mAdapter.notifyDataSetChanged()
    }



}