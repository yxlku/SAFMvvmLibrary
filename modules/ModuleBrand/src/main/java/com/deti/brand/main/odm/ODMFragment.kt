package com.deti.brand.main.odm

import android.content.Context
import android.graphics.Color
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.deti.brand.BR
import com.deti.brand.R
import com.deti.brand.databinding.BrandFragmentIndexOdmBinding
import com.deti.brand.demand.create.CreateDemandFragment
import com.deti.brand.demand.price.PriceDemandFragment
import com.deti.brand.demand.sampleclothes.SimpleClothesFragment
import com.safmvvm.bus.SingleLiveEvent
import com.safmvvm.ext.ui.NewSimplePagerTitleView
import com.safmvvm.ext.ui.tab.ITabTop
import com.safmvvm.ext.ui.tab.ITabTopHideShow
import com.safmvvm.ext.ui.viewpager.createViewPager
import com.safmvvm.mvvm.view.BaseFragment
import com.safmvvm.mvvm.view.BaseLazyFragment
import com.safmvvm.ui.autosize.setTextSizeAuto
import com.test.common.ui.popup.base.BaseSingleChoiceEntity
import me.jessyan.autosize.utils.AutoSizeUtils
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator

class ODMFragment : BaseFragment<BrandFragmentIndexOdmBinding, ODMViewModel>(
    R.layout.brand_fragment_index_odm,
    BR.viewModel
), ITabTopHideShow {
    companion object {
        /** 跳转到需求报价列表*/
        val ODM_LIVE_TO_ORDER_LIST =  SingleLiveEvent<Unit>()
    }
    var titles = arrayListOf(
        "新建需求",
        "需求报价",
        "样衣打版",
        "大货订单"
    )
    var createDemandFragment = CreateDemandFragment()
    var priceDemandFragment = PriceDemandFragment()
    var simpleClothesFragment = SimpleClothesFragment()
    var priceDemandFragment2 = SimpleClothesFragment()

    override fun initFragments(): ArrayList<Fragment> = arrayListOf<Fragment>(
        createDemandFragment,
        priceDemandFragment,
        simpleClothesFragment,
        priceDemandFragment2
    )

    override fun frameLayout(): Int = mBinding.flContent.id

    override fun initData() {
        super.initData()

        initTab()

        ODM_LIVE_TO_ORDER_LIST.observe(this){
            //跳转到为确认页面
            childFragmentManager.switchPage(mBinding.miTab, 1)
            priceDemandFragment.switchPageIndex(1)
        }
    }

    private fun initTab() {
        childFragmentManager.initTabTop(context,
            mBinding.miTab,
            titles,
            true
        )
    }

    override fun FragmentManager.createTitleItemView(
        context: Context,
        magicIndicator: MagicIndicator,
        index: Int,
        titles: ArrayList<String>,
        tab: Int
    ): IPagerTitleView= NewSimplePagerTitleView(context).apply {
        setTextSizeAuto(14F)
        text = titles[index]
        selectedColor = Color.parseColor("#1F2438")
        normalColor = Color.parseColor("#CCFFFFFF")
        setOnClickListener {
            switchPage(magicIndicator, index)
        }
    }



    override fun createIndicator(context: Context?, tag: Int): IPagerIndicator? =
        WrapPagerIndicator(context).apply {
            verticalPadding = AutoSizeUtils.mm2px(context, 6F)
            horizontalPadding = AutoSizeUtils.mm2px(context, 10f)
            fillColor = Color.parseColor("#FCCE48")
            startInterpolator = OvershootInterpolator(1.0f)
            endInterpolator = OvershootInterpolator(1.0f)
        }

}