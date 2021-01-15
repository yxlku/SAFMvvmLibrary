package com.deti.brand.demand.sampleclothes

import android.content.Context
import android.graphics.Color
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandFragmentSimpleClothesBinding
import com.deti.brand.demand.sampleclothes.all.SimpleClothesListAllFragment
import com.safmvvm.ext.ui.tab.ITabTop
import com.safmvvm.ext.ui.viewpager.createViewPager
import com.safmvvm.mvvm.view.BaseFragment
import com.safmvvm.ui.autosize.setTextSizeAuto
import me.jessyan.autosize.utils.AutoSizeUtils
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

class SimpleClothesFragment: BaseFragment<BrandFragmentSimpleClothesBinding, SimpleClothesViewModel>(
    R.layout.brand_fragment_simple_clothes,
    BR.viewModel
), ITabTop {
    var titles = arrayListOf(
        "全部",
        "待发货",
        "待收货",
        "待评价",
        "待付款",
    )
    var fragments = arrayListOf<Fragment>(
        SimpleClothesListAllFragment(),
        SimpleClothesListAllFragment(),
        SimpleClothesListAllFragment(),
        SimpleClothesListAllFragment(),
        SimpleClothesListAllFragment(),
    )
    override fun initData() {
        super.initData()

        fragments.createViewPager(childFragmentManager, mBinding.vpContent)
        initTabTop(
            context,
            mBinding.miTab,
            mBinding.vpContent,
            titles,
            true
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
        tab: Int,
    ): IPagerTitleView = SimplePagerTitleView(context).apply {
        //setTextSize默认使用的是SP单位的，如果再进行转换将被二次转换，
//        textSize = AutoSizeUtils.mm2px(context, 14F).toFloat()
        setTextSizeAuto(14F)
        text = titles[index]
        normalColor = Color.parseColor("#88252C43")
        selectedColor = Color.parseColor("#252C43")
        setOnClickListener {
            switchPage(magicIndicator, viewPager, index)
        }
    }

}