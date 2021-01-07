package com.deti.brand.main

import android.content.Context
import android.graphics.Color
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.deti.brand.BR
import com.deti.brand.R
import com.deti.brand.databinding.BrandFragmentMainBinding
import com.deti.brand.main.odm.demand.ODMFragment
import com.deti.brand.main.oem.OEMFragment
import com.safmvvm.ext.ui.viewpager2.ViewPager2FragmentAdapter
import com.safmvvm.ext.ui.viewpager2.ViewPager2Helper
import com.safmvvm.ext.ui.viewpager2.transformations.CubeInRotationTransformation
import com.safmvvm.mvvm.view.BaseActivity
import com.safmvvm.mvvm.view.BaseFragment
import com.test.common.RouterActivityPath
import me.jessyan.autosize.utils.AutoSizeUtils
import net.lucode.hackware.magicindicator.FragmentContainerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

class MainFragment: BaseFragment<BrandFragmentMainBinding, MainViewModel>(
    R.layout.brand_fragment_main,
    BR.viewModel
){
//    var titles = arrayOf("OEM", "ODM")
    var titles = arrayOf("ODM")
    var mFagments = arrayListOf<Fragment>()
    var mFragmentContainerHelper = FragmentContainerHelper()

    override fun initData() {
        super.initData()

        initViewPager()
        initTab()
    }

    private fun initViewPager() {
        var fragments = arrayListOf<Fragment>().apply {
//            add(OEMFragment())
            add(ODMFragment())
        }
        var viewPager2FragmentAdapter = ViewPager2FragmentAdapter(activity as AppCompatActivity, fragments)
        mBinding.vpContent.apply {
            isUserInputEnabled = false
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = viewPager2FragmentAdapter
        }
    }
    private fun initTab() {
        var commonNavigator = CommonNavigator(context).apply {
            isAdjustMode = false
            adapter = object : CommonNavigatorAdapter() {
                override fun getCount(): Int = titles.size

                override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
                    return SimplePagerTitleView(context).apply {
                        setPadding(AutoSizeUtils.mm2px(context, 30f), 0, AutoSizeUtils.mm2px(context, 30f), 0)
                        textSize = 16F
                        text = titles[index]
                        normalColor = Color.parseColor("#99FFFFFF")
                        selectedColor = Color.parseColor("#FFFFFF")
                        setOnClickListener {
                            switchPages(index)
                        }
                    }
                }

                override fun getIndicator(context: Context?): IPagerIndicator? {
                    return null
                }

                override fun getTitleWeight(context: Context?, index: Int): Float {
                    return 1.0f
                }
            }
        }
        var magicIndicator = mBinding.miTab.apply {
            navigator = commonNavigator
        }
        ViewPager2Helper.bind(magicIndicator, mBinding.vpContent)
    }
    private fun switchPages(index: Int){
        mFragmentContainerHelper.handlePageSelected(index, false)
        mBinding.vpContent.setCurrentItem(index, true)
    }

}