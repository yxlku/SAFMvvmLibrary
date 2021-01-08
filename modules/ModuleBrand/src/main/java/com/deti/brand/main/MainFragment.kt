package com.deti.brand.main

import android.content.Context
import android.graphics.Color
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.deti.brand.BR
import com.deti.brand.R
import com.deti.brand.databinding.BrandFragmentMainBinding
import com.deti.brand.main.odm.demand.ODMFragment
import com.deti.brand.main.oem.OEMFragment
import com.safmvvm.ext.ui.tab.ITabTop
import com.safmvvm.ext.ui.viewpager2.createViewPager2
import com.safmvvm.mvvm.view.BaseFragment
import me.jessyan.autosize.utils.AutoSizeUtils
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

class MainFragment: BaseFragment<BrandFragmentMainBinding, MainViewModel>(
    R.layout.brand_fragment_main,
    BR.viewModel
), ITabTop {
//    var titles = arrayListOf("OEM", "ODM")
//    var fragments = arrayListOf<Fragment>(OEMFragment(), ODMFragment())
    var titles = arrayListOf("ODM")
    var fragments = arrayListOf<Fragment>(ODMFragment())

    override fun initData() {
        super.initData()

        initViewPager()
        initTab()
    }

    private fun initViewPager() {
        fragments.createViewPager2(
            activity as AppCompatActivity,
            mBinding.vpContent,
            false
        )
    }
    private fun initTab() {
        initTabTop(
            context,
            mBinding.miTab,
            mBinding.vpContent,
            titles,
        )
    }

    /**
     * 2、实现此方法，来自定义titleItemView
     *
     * 不写就是方法内默认样式
     */
    override fun createTitleItemView(
        context: Context,
        magicIndicator: MagicIndicator,
        viewPager2: ViewPager2?,
        index: Int,
        titles: ArrayList<String>,
        tag: Int
    ): IPagerTitleView = SimplePagerTitleView(context).apply {
        setPadding(AutoSizeUtils.mm2px(context, 30f), 0, AutoSizeUtils.mm2px(context, 30f), 0)
        textSize = 16F
        text = titles[index]
        normalColor = Color.parseColor("#99FFFFFF")
        selectedColor = Color.parseColor("#FFFFFF")
        setOnClickListener {
            switchPage(magicIndicator, viewPager2, index)
        }
    }


    /**
     * 3、实现此方法，来自定义指示器样式
     *
     * 不写就是方法内默认样式
     */
    override fun createIndicator(context: Context?, tag: Int): IPagerIndicator? = null
}