package com.test.common.ui.popup.custom.type

import android.app.Activity
import com.lxj.xpopup.core.BasePopupView
import com.safmvvm.ext.ui.typesview.TypesTreeViewEntity
import com.safmvvm.ext.ui.typesview.TypesViewDataBean
import com.test.common.ui.popup.createDialogBase

/**
 * 多级类型弹窗
 */
fun createDialogLevelTypes(
    activity: Activity,
    title: String,
    datas: TypesTreeViewEntity,
    defSelectedCodes: ArrayList<TypesViewDataBean?> = arrayListOf(),
    /** 目录层级*/
    levelCount: Int = 2,
    autoDismiss: Boolean = true,
    /** 选择的结果*/
    selectResultBlock: (result: ArrayList<TypesViewDataBean?>, resultTextList: ArrayList<String>) -> Unit = {result: ArrayList<TypesViewDataBean?>, resultTextList: ArrayList<String>->}
): BasePopupView {
    return createDialogBase(
        TypesPopupView(activity, title, datas, defSelectedCodes, levelCount,autoDismiss, selectResultBlock)
    ){

    }
}

