package com.deti.brand

import android.graphics.Color
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.deti.brand.databinding.BrandActivityIndexBinding
import com.deti.brand.main.MainFragment
import com.deti.brand.mine.MineFragment
import com.deti.brand.msg.MsgFragment
import com.safmvvm.ext.ui.tab.ITabBottom
import com.safmvvm.ext.ui.tab.bottom.NewNormalItemView
import com.safmvvm.ext.ui.viewpager2.createViewPager2
import com.safmvvm.ext.ui.viewpager2.transformations.CubeInRotationTransformation
import com.safmvvm.mvvm.view.BaseActivity
import com.test.common.RouterActivityPath

@Route(path = RouterActivityPath.ModuleBrand.PAGE_INDEX)
class BrandIndexActivity: BaseActivity<BrandActivityIndexBinding, BrandIndexViewModel>(
    R.layout.brand_activity_index,
    BR.viewModel
), ITabBottom {

    override fun initData() {
        //取消侧滑返回
        cleanSwipeback()

        initViewPager()
        initTab()
    }

    private fun initViewPager() {
        var fragments = arrayListOf<Fragment>().apply {
            add(MainFragment())
            add(MsgFragment())
            add(MineFragment())
        }
        fragments.createViewPager2(this, mBinding.vpContent, false){
            it.setPageTransformer(CubeInRotationTransformation())
        }
    }

    private fun initTab() {
        var tabMain = newTabBottomItem(R.mipmap.base_index_tab_main_uncheck, R.mipmap.base_index_tab_main_checked, "首页")
        var tabMsg = newTabBottomItem(R.mipmap.base_index_tab_msg_uncheck, R.mipmap.base_index_tab_msg_checked, "消息")
        var tabMine = newTabBottomItem(R.mipmap.base_index_tab_mine_uncheck, R.mipmap.base_index_tab_mine_checked, "我的")
        //数字气泡
        tabMsg.tintMessageBackground(Color.parseColor("#FCCE48"))
        tabMsg.setMessageNumberColor(Color.parseColor("#333333"))
        var tabItems = arrayListOf<NewNormalItemView>().apply {
            add(tabMain)
            add(tabMsg)
            add(tabMine)
        }
        createBottomTab(mBinding.pnvTab, mBinding.vpContent,  tabItems)
        tabMsg.setMessageNumber(100)
    }

    override fun newTabBottomItem(
        uncheckIcon: Int,
        checkedIcon: Int,
        text: String,
    ): NewNormalItemView = NewNormalItemView(this).apply {
        initialize(uncheckIcon, checkedIcon, text)
        setTextDefaultColor(Color.parseColor("#999999"))
        setTextCheckedColor(Color.parseColor("#FCCE48"))
        setIconSize(24, 24)
        setTitleSize(12)
    }

}