package com.safmvvm.ui.swipebacklayout

import android.app.Application
import com.billy.android.swipe.SmartSwipeBack
import com.safmvvm.app.globalconfig.GlobalConfig


object SwipebacklayoutUtil {


    fun init(app: Application) {
        //add relative moving slide back
        if (GlobalConfig.App.gIsOpenSwipeback) {
            SmartSwipeBack.activitySlidingBack(app, null)
        }
    }


}