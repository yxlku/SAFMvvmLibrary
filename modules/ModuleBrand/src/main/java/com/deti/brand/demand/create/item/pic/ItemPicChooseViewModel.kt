package com.deti.brand.demand.create.item.pic

import android.app.Activity
import android.view.View
import com.safmvvm.app.BaseApp
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.test.common.ui.dialog.multiple.BaseMultipleChoiceEntity
import com.test.common.ui.dialog.multiple.createDialogSelectedMultiple

class ItemPicChooseViewModel(
    var mActivity: Activity?
): BaseViewModel<BaseModel>(BaseApp.getInstance()) {
    fun btnShowMultipeDialog(v: View){
    }
}