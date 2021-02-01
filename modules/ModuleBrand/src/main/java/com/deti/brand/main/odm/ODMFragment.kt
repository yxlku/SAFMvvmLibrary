package com.deti.brand.main.odm

import android.content.Context
import android.graphics.Color
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.deti.brand.BR
import com.deti.brand.R
import com.deti.brand.databinding.BrandFragmentIndexOdmBinding
import com.deti.brand.demand.create.CreateDemandFragment
import com.deti.brand.demand.price.PriceDemandFragment
import com.deti.brand.demand.sampleclothes.SimpleClothesFragment
import com.safmvvm.ext.ui.NewSimplePagerTitleView
import com.safmvvm.ext.ui.tab.ITabTop
import com.safmvvm.ext.ui.viewpager.createViewPager
import com.safmvvm.mvvm.view.BaseFragment
import com.safmvvm.mvvm.view.BaseLazyFragment
import com.safmvvm.ui.autosize.setTextSizeAuto
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator

class ODMFragment : BaseLazyFragment<BrandFragmentIndexOdmBinding, ODMViewModel>(
    R.layout.brand_fragment_index_odm,
    BR.viewModel
), ITabTop {
    var titles = arrayListOf(
        "新建需求",
        "需求报价",
        "样衣打版",
        "大货订单"
    )
    var fragments = arrayListOf<Fragment>(
        CreateDemandFragment(),
        PriceDemandFragment(),
        SimpleClothesFragment(),
        PriceDemandFragment()
    )

    override fun initData() {
        super.initData()

        initViewPager()
        initTab()
    }

    private fun initViewPager() {
        fragments.createViewPager(
            childFragmentManager,
            mBinding.vpContent
        )
    }

    private fun initTab() {
        initTabTop(context,
            mBinding.miTab,
            mBinding.vpContent,
            titles,
            true
        )
    }

    override fun createTitleItemView(
        context: Context,
        magicIndicator: MagicIndicator,
        viewPager: ViewPager?,
        index: Int,
        titles: ArrayList<String>,
        tag: Int,
    ): IPagerTitleView = NewSimplePagerTitleView(context).apply {
        setTextSizeAuto(14F)
        text = titles[index]
        selectedColor = Color.parseColor("#1F2438")
        normalColor = Color.parseColor("#CCFFFFFF")
        setOnClickListener {
            switchPage(magicIndicator, viewPager, index)
        }
    }


    override fun createIndicator(context: Context?, tag: Int): IPagerIndicator? =
        WrapPagerIndicator(context).apply {
            fillColor = Color.parseColor("#FCCE48")
            startInterpolator = OvershootInterpolator(1.0f)
            endInterpolator = OvershootInterpolator(1.0f)
        }

    override fun switchPage(
        magicIndicator: MagicIndicator,
        viewPager: ViewPager?,
        index: Int,
    ) {
        //滑动动画
        viewPager?.setCurrentItem(index, false)
    }

}