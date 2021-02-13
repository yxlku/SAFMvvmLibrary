package com.test.common.ui.popup.custom.color

import android.app.Activity
import com.lxj.xpopup.core.BasePopupView
import com.test.common.ui.popup.createDialogBase

/**
 * 弹窗：选择颜色
 */
fun dialogChooseColors(
    activity: Activity,
    title: String,
    datas: DemandColorListEntity,
    /** 选中的颜色*/
    selectColors: List<DemandColorDataBean> = arrayListOf(),
    mHeightMultiple: Float = 0.8F,
    /** 点击标题确定返回的结果*/
    resultBlock: (selectDatas: ArrayList<DemandColorDataBean>, selectDatasText: ArrayList<String>, basePopupView: BasePopupView) -> Unit = {selectDatas: ArrayList<DemandColorDataBean>, selectDatasText: ArrayList<String>, basePopupView: BasePopupView->}
): BasePopupView {
    return createDialogBase(
        ColorsPopupView(activity, title, datas, selectColors, mHeightMultiple, resultBlock)
    ){

    }
}