package com.deti.designer.main

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.deti.designer.BR
import com.deti.designer.R
import com.deti.designer.databinding.DesignerFragmentMainBinding
import com.deti.designer.materiel.MaterielListFragment
import com.deti.designer.order.OrderGrabFragment
import com.safmvvm.ext.ui.tab.ITabTop
import com.safmvvm.ext.ui.tab.top.ScaleTransitionPagerTitleView
import com.safmvvm.ext.ui.viewpager.createViewPager
import com.safmvvm.mvvm.view.BaseLazyFragment
import com.safmvvm.mvvm.view.bottom.BaseBottomFragment
import com.safmvvm.ui.autosize.setTextSizeAuto
import com.safmvvm.utils.LogUtil
import me.jessyan.autosize.utils.AutoSizeUtils
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.TriangularPagerIndicator


class MainFragment: BaseLazyFragment<DesignerFragmentMainBinding, MainViewModel>(
    R.layout.designer_fragment_main,
    BR.viewModel
), ITabTop {

    var titles = arrayListOf(
        "抢单",
        "派单",
        "物料",
        "版单",
        "款式",
    )
    var fragments = arrayListOf<Fragment>()

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
        fragments.apply {
            /** 抢单*/
            add(OrderGrabFragment(OrderGrabFragment.STATE_GRAB))
            /** 派单*/
            add(OrderGrabFragment(OrderGrabFragment.STATE_DISPATCH))
            /** 物料列表*/
            add(MaterielListFragment())

            add(OrderGrabFragment())
            add(OrderGrabFragment())
        }
        fragments.createViewPager(childFragmentManager, mBinding.vpContent)

        initTabTop(context, mBinding.miTab, mBinding.vpContent, titles, true)
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
    ): IPagerTitleView = ScaleTransitionPagerTitleView(context).apply {
        //setTextSize默认使用的是SP单位的，如果再进行转换将被二次转换，
//        textSize = AutoSizeUtils.mm2px(context, 14F).toFloat()
        setTextSizeAuto(18F)
        text = titles[index]
        normalColor = Color.parseColor("#CCFFFFFF")
        selectedColor = Color.parseColor("#FEC217")
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
        return TriangularPagerIndicator(context).apply {
            lineHeight = 0
            triangleWidth = AutoSizeUtils.mm2px(context, 15F)
            triangleHeight = AutoSizeUtils.mm2px(context, 6F)
            lineColor = Color.parseColor("#FFFFFF")
        }
    }

}