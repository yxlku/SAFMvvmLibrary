package com.deti.brand.main.odm

import android.content.Context
import android.graphics.Color
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandFragmentIndexOdmBinding
import com.deti.brand.demand.create.CreateDemandFragment
import com.deti.brand.demand.price.PriceDemandFragment
import com.safmvvm.ext.ui.NewSimplePagerTitleView
import com.safmvvm.ext.ui.tab.ITabTop
import com.safmvvm.ext.ui.viewpager.createViewPager
import com.safmvvm.mvvm.view.BaseFragment
import com.safmvvm.utils.ResUtil
import me.jessyan.autosize.utils.AutoSizeUtils
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.TriangularPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.DummyPagerTitleView

class ODMFragment : BaseFragment<BrandFragmentIndexOdmBinding, ODMViewModel>(
    R.layout.brand_fragment_index_odm,
    BR.viewModel
), ITabTop {
    /** tab指示器 圆角矩形*/
    val INDICATOR_ROUNDED_RECTANGLE: Int = 0
    /** tab指示器 底部三角*/
    val INDICATOR_TRIANGLE: Int = 1
    var titles = arrayListOf(
        "新建需求",
        "需求报价",
        "样衣打版",
        "大货订单"
    )
    var fragments = arrayListOf<Fragment>(
        CreateDemandFragment(),
        PriceDemandFragment(),
        PriceDemandFragment(),
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
        initTabTop(context, mBinding.miTab, mBinding.vpContent, titles, true, INDICATOR_ROUNDED_RECTANGLE)
        initTabTop(context, mBinding.miTabTriangle, mBinding.vpContent, titles, true, INDICATOR_TRIANGLE)
    }

    override fun createTitleItemView(
        context: Context,
        magicIndicator: MagicIndicator,
        viewPager: ViewPager?,
        index: Int,
        titles: ArrayList<String>,
        tag: Int
    ): IPagerTitleView {
        return if (tag == INDICATOR_ROUNDED_RECTANGLE) {
            NewSimplePagerTitleView(context).apply {
                textSize = 14F
                text = titles[index]
                selectedColor = Color.parseColor("#1F2438")
                normalColor = if (index >= 2) {
                    Color.parseColor("#CCFFFFFF")
                } else {
                    Color.parseColor("#1F2438")
                }
                setOnClickListener {
                    switchPage(magicIndicator, viewPager, index)
                }
            }
        } else {
            DummyPagerTitleView(context)
        }
    }

    override fun createIndicator(context: Context?, tag: Int): IPagerIndicator?{
        return if (tag == INDICATOR_ROUNDED_RECTANGLE) {
            WrapPagerIndicator(context).apply {
                fillColor = Color.parseColor("#FCCE48")
                startInterpolator = OvershootInterpolator(1.0f)
                endInterpolator = OvershootInterpolator(1.0f)
            }
        }else{
            TriangularPagerIndicator(context).apply {
                yOffset = AutoSizeUtils.mm2px(context, -6.0F).toFloat()
                triangleWidth = AutoSizeUtils.mm2px(context, 16F)
                triangleHeight = AutoSizeUtils.mm2px(context, 12F)
                lineColor = Color.parseColor("#FFFFFF")
                lineHeight = 0
            }
        }
    }

    override fun switchPage(
        magicIndicator: MagicIndicator,
        viewPager: ViewPager?,
        index: Int
    ) {
        //tab颜色状态
        tabNormalColor(magicIndicator, index)
        //前两个tab背景图片在点后两个的时候隐藏
        mBinding.vTabBgWhite.background = if (index > 1) {
            ResUtil.getDrawable(R.drawable.brand_odm_tab_gray_bg)
        } else {
            ResUtil.getDrawable(R.drawable.brand_odm_tab_white_bg)
        }
        //滑动动画
        viewPager?.setCurrentItem(index, false)
    }

    fun tabNormalColor(magicIndicator: MagicIndicator, index: Int){
        var navigator = magicIndicator.navigator as CommonNavigator
        var tab1 = (navigator.getPagerTitleView(0) as NewSimplePagerTitleView)
        var tab2 = (navigator.getPagerTitleView(1) as NewSimplePagerTitleView)
        if (index > 1) {
            tab1.normalColor = Color.parseColor("#FFFFFF")
            tab2.normalColor = Color.parseColor("#FFFFFF")
        } else {
            tab1.normalColor = Color.parseColor("#1F2438")
            tab2.normalColor = Color.parseColor("#1F2438")
        }
    }

}