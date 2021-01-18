package com.deti.brand.demand.create.item.personinfo

import android.app.Activity
import android.view.View
import com.safmvvm.app.BaseApp
import com.safmvvm.component.RouterUtil
import com.safmvvm.mvvm.model.BaseModel
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.test.common.RouterActivityPath
import com.test.common.ui.dialog.goods.createDialogGoodsDetail

class ItemPersonalViewModel(
    var activty: Activity?
): BaseViewModel<BaseModel>(BaseApp.getInstance()) {


    fun clickToPerfectPersonalPage(view: View){
//        RouterUtil.startActivity(RouterActivityPath.ModuleBasis.PAGE_PERFECT_PERSONAL)

        activty?.apply {
            createDialogGoodsDetail(this).show()
        }
    }

}