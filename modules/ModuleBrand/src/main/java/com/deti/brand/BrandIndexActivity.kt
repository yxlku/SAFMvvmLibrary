package com.deti.brand

import android.graphics.Color
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.deti.brand.databinding.BrandActivityIndexBinding
import com.deti.brand.main.MainFragment
import com.deti.brand.mine.MineFragment
import com.deti.brand.msg.MsgFragment
import com.safmvvm.ext.ui.viewpager2.ViewPager2FragmentAdapter
import com.safmvvm.ext.ui.viewpager2.ViewPager2Helper
import com.safmvvm.ext.ui.tab.bottom.NewNormalItemView
import com.safmvvm.ext.ui.viewpager2.transformations.CubeInRotationTransformation
import com.safmvvm.ext.ui.viewpager2.transformations.FadeOutTransformation
import com.safmvvm.ext.ui.viewpager2.transformations.ZoomOutTransformation
import com.safmvvm.mvvm.view.BaseActivity
import com.test.common.RouterActivityPath

@Route(path = RouterActivityPath.ModuleBrand.PAGE_INDEX)
class BrandIndexActivity: BaseActivity<BrandActivityIndexBinding, BrandIndexViewModel>(
    R.layout.brand_activity_index,
    BR.viewModel
) {

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
        var viewPager2FragmentAdapter = ViewPager2FragmentAdapter(this, fragments)
        mBinding.vpContent.apply {
            adapter = viewPager2FragmentAdapter
            //禁止滑动
            isUserInputEnabled = true
            setPageTransformer(CubeInRotationTransformation())
        }
    }

    private fun initTab() {
        var tabMain = newTabItem(R.mipmap.base_index_tab_main_uncheck, R.mipmap.base_index_tab_main_checked, "首页")
        var tabMsg = newTabItem(R.mipmap.base_index_tab_msg_uncheck, R.mipmap.base_index_tab_msg_checked, "消息")
        var tabMine = newTabItem(R.mipmap.base_index_tab_mine_uncheck, R.mipmap.base_index_tab_mine_checked, "我的")
        tabMsg.tintMessageBackground(Color.parseColor("#FCCE48"))
        tabMsg.setMessageNumberColor(Color.parseColor("#333333"))
        mBinding.pnvTab.custom().apply {
            addItem(tabMain)
            addItem(tabMsg)
            addItem(tabMine)
        }.build().run {
            setSelect(0)
            ViewPager2Helper.bind(this, mBinding.vpContent)
            addSimpleTabItemSelectedListener{index, old ->
                //选择
                switchPage(index)
            }
        }
        tabMsg.setMessageNumber(100)
    }

    /**
     * 选择页面
     */
    fun switchPage(index: Int){
        mBinding.vpContent.setCurrentItem(index, true)
    }

    fun newTabItem(
        @DrawableRes uncheckIcon: Int,
        @DrawableRes checkedIcon: Int,
        text: String
    ): NewNormalItemView = NewNormalItemView(this).apply {
        initialize(uncheckIcon, checkedIcon, text)
        setTextDefaultColor(Color.parseColor("#999999"))
        setTextCheckedColor(Color.parseColor("#FCCE48"))
        setIconSize(24, 24)
        setTitleSize(12)
    }

}