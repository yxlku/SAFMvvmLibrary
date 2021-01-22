package com.test.common.ui.popup.color

import android.app.Activity
import androidx.collection.ArraySet
import com.lxj.xpopup.core.BasePopupView
import com.test.common.ui.popup.createDialogBase

/**
 * 弹窗：选择颜色
 */
fun dialogChooseColors(
    activity: Activity,
    title: String,
    datas: DemandColorListEntity,
    mHeightMultiple: Float = 0.8F,
    /** 点击标题确定返回的结果*/
    resultBlock: (selectDatas: ArraySet<DemandColorDataBean>) -> Unit = {}
): BasePopupView {
    return createDialogBase(
        ColorsPopupView(activity, title, datas, mHeightMultiple, resultBlock)
    ){

    }
}