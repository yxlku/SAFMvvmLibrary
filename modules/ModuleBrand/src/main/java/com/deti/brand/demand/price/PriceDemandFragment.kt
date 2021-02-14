package com.deti.brand.demand.price

import android.content.Context
import android.graphics.Color
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandFragmentDemandPriceBinding
import com.deti.brand.demand.price.list.PriceListAllFragment
import com.safmvvm.ext.ui.tab.ITabTop
import com.safmvvm.ext.ui.viewpager.createViewPager
import com.safmvvm.mvvm.view.BaseLazyFragment
import com.safmvvm.ui.autosize.setTextSizeAuto
import me.jessyan.autosize.utils.AutoSizeUtils
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator.MODE_EXACTLY
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

class PriceDemandFragment(
    var pageIndex: Int = 0,
): BaseLazyFragment<BrandFragmentDemandPriceBinding, PriceDemandViewModel>(
    R.layout.brand_fragment_demand_price,
    BR.viewModel
), ITabTop{
    var titles = arrayListOf(
        "全部",
        "未确认",
        "已确认",
        "已关闭",
    )
    var fragments = arrayListOf<Fragment>(
        PriceListAllFragment(PriceListAllFragment.PRICE_LIST_ALL), // 全部列表
        PriceListAllFragment(PriceListAllFragment.PRICE_LIST_UNCONFIRMED), // 未确认列表
        PriceListAllFragment(PriceListAllFragment.PRICE_LIST_CONFIRMED), // 已确认列表
        PriceListAllFragment(PriceListAllFragment.PRICE_LIST_CLOSED), // 已关闭列表
    )
    override fun initData() {
        super.initData()

        initViewPager()
        initTab()

        //初始化显示页面
        switchPageIndex(pageIndex)
    }

    fun switchPageIndex(pIndex: Int = 0){
        //选择页面
        switchPage(mBinding.miTab, mBinding.vpContent, pIndex)
    }

    private fun initTab() {
        initTabTop(
            context,
            mBinding.miTab,
            mBinding.vpContent,
            titles,
            true
        )
    }

    private fun initViewPager() {
        fragments.createViewPager(
            childFragmentManager,
            mBinding.vpContent,
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
        viewPager: ViewPager?,
        index: Int,
        titles: ArrayList<String>,
        tab: Int
    ): IPagerTitleView = SimplePagerTitleView(context).apply {
        setTextSizeAuto(14F)
        text = titles[index]
        normalColor = Color.parseColor("#333333")
        selectedColor = Color.parseColor("#333333")
        setOnClickListener {
            switchPage(magicIndicator, viewPager, index)
        }
    }

    /**
     * 3、实现此方法，来自定义指示器样式
     *
     * 不写就是方法内默认样式
     */
    override fun createIndicator(context: Context?, tag: Int): IPagerIndicator? {
        return LinePagerIndicator(context).apply {
            mode = MODE_EXACTLY
            setColors(Color.parseColor("#FCCE48"))
            startInterpolator = AccelerateInterpolator()
            endInterpolator = DecelerateInterpolator(1.6f)
            lineHeight = AutoSizeUtils.mm2px(context, 2.0f).toFloat()
            lineWidth = AutoSizeUtils.mm2px(context, 24.0f).toFloat()
        }
    }
}