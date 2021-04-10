package com.deti.brand

import android.graphics.Rect
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.ViewTreeObserver
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.deti.brand.databinding.BrandActivityIndexBinding
import com.deti.brand.demand.create.CreateDemandFragment
import com.deti.brand.main.MainFragment
import com.deti.brand.mine.MineFragment
import com.deti.brand.msg.MsgFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.safmvvm.app.AppActivityManager
import com.safmvvm.ext.ui.tab.ITabBottomHideShow
import com.safmvvm.ext.ui.viewpager.createViewPager
import com.safmvvm.mvvm.view.BaseActivity
import com.safmvvm.ui.toast.ToastUtil
import com.test.common.RouterActivityPath

@Route(path = RouterActivityPath.ModuleBrand.PAGE_INDEX)
class BrandIndexActivity : BaseActivity<BrandActivityIndexBinding, BrandIndexViewModel>(
    R.layout.brand_activity_index,
    BR.viewModel
), ITabBottomCommon {

    override fun initData() {
        //取消侧滑返回
        cleanSwipeback()

        arrayListOf<Fragment>(
            MainFragment(),
//            MsgFragment(),
//            MineFragment(),
//            TestFragment()
        ).createViewPager(supportFragmentManager, mBinding.vpContent)

        createBottomTab(
            context = this,
            pageNavigationView = mBinding.pnvTab,
            viewPager = mBinding.vpContent,
        )
    }



    private var mExitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                AppActivityManager.finishAllActivity()
            } else {
                mExitTime = System.currentTimeMillis()
                ToastUtil.showShortToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}