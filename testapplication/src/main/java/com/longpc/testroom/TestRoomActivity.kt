package com.longpc.testroom

import android.widget.ScrollView
import androidx.lifecycle.Observer
import com.jeremyliao.liveeventbus.LiveEventBus
import com.longpc.testapplication.R
import com.longpc.testapplication.BR
import com.longpc.testapplication.databinding.MainActivityTestRoomBinding
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.view.BaseActivity

class TestRoomActivity: BaseActivity<MainActivityTestRoomBinding, TestRoomViewModel>(
    R.layout.main_activity_test_room, BR.viewModel
) {
    override fun initViewObservable() {
        LiveDataBus.observe<Unit>(this, "scd", Observer {
            mBinding.mainScrollview.fullScroll(ScrollView.FOCUS_DOWN) //滑到底部
        })
    }

}