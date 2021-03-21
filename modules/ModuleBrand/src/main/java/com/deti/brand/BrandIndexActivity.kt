package com.deti.brand

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.deti.brand.databinding.BrandActivityIndexBinding
import com.deti.brand.demand.create.CreateDemandFragment
import com.deti.brand.main.MainFragment
import com.deti.brand.mine.MineFragment
import com.deti.brand.msg.MsgFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.safmvvm.ext.ui.tab.ITabBottomHideShow
import com.safmvvm.mvvm.view.BaseActivity
import com.test.common.RouterActivityPath

@Route(path = RouterActivityPath.ModuleBrand.PAGE_INDEX)
class BrandIndexActivity : BaseActivity<BrandActivityIndexBinding, BrandIndexViewModel>(
    R.layout.brand_activity_index,
    BR.viewModel
), ITabBottomHideShow {

    override fun initFragments(): ArrayList<Fragment> = arrayListOf(
        MainFragment(),
        MsgFragment(),
        MineFragment()
    )

    override fun frameLayout(): Int = mBinding.flContent.id


    override fun initData() {
        //取消侧滑返回
        cleanSwipeback()
        supportFragmentManager.initBottomNavigation(mBinding.bnvTab) {
                bottomNavigationView: BottomNavigationView, menuItemViews: ArrayList<View> ->
        }

    }

}