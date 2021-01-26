package com.deti.designer.materiel.popup.addmateriel

import android.app.Activity
import com.deti.designer.materiel.MaterielListViewModel
import com.lxj.xpopup.core.BasePopupView
import com.test.common.ui.popup.createDialogBase

/**
 * 添加物料
 */
fun dialogAddMateriel(
    mActivity: Activity,
    mViewModel: MaterielListViewModel,
    mTitle: String = "",
): BasePopupView{
    return createDialogBase(
        AddMaterielPopupView(mActivity, mViewModel, mTitle)
    )
}