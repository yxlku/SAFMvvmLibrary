package com.deti.brand.demand.price.pricelist

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.ui.toast.ToastUtil
import com.test.common.ui.toast.ToastDrawableEnum

class PriceListViewModel(
    app: Application
): BaseViewModel<PriceListModel>(app)