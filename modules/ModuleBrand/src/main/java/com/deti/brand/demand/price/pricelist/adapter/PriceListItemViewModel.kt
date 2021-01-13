package com.deti.brand.demand.price.pricelist.adapter

import android.view.View
import com.safmvvm.ui.toast.ToastUtil
import com.test.common.ui.toast.ToastDrawableEnum

class PriceListItemViewModel {

    fun clickTip(view: View){
        ToastUtil.showShortToast("需求提交成功！我们会在\n24小时内报价！", true, ToastDrawableEnum.TOP)
    }

}