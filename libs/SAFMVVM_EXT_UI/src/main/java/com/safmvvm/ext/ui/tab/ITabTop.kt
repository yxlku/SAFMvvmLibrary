package com.safmvvm.ext.ui.tab

import android.content.Context
import android.graphics.Color
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.viewpager.widget.ViewPager
import com.safmvvm.ext.ui.viewpager.ViewPagerHelper
import com.safmvvm.ui.autosize.setTextSizeAuto
import me.jessyan.autosize.utils.AutoSizeUtils
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView


interface ITabTop {

    /**
     * 1、初始调用位置
     */
    fun initTabTop(
        context: Context?,
        magicIndicator: MagicIndicator,
        viewPager: ViewPager? = null,
        titles: ArrayList<String>,
        isAdjust: Boolean = false,
        tag: Int = 0,
    ) {
        context?.let {
            var commonNavigator = CommonNavigator(context).apply {
                isAdjustMode = isAdjust
                adapter = object : CommonNavigatorAdapter() {
                    override fun getCount(): Int = titles.size

                    override fun getTitleView(context: Context, index: Int): IPagerTitleView? =
                        createTitleItemView(context, magicIndicator, viewPager, index, titles, tag)

                    override fun getIndicator(context: Context): IPagerIndicator? =
                        createIndicator(context, tag)

                    override fun getTitleWeight(context: Context?, index: Int): Float =
                        createTitleWeight(context, index)

                }

            }
            magicIndicator.navigator = commonNavigator
            viewPager?.let {
                it.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int,
                    ) {
                        magicIndicator.onPageScrolled(position,
                            positionOffset,
                            positionOffsetPixels)
                    }

                    override fun onPageSelected(position: Int) {
                        magicIndicator.onPageSelected(position)
                        switchPage(magicIndicator, it, position)
                    }

                    override fun onPageScrollStateChanged(state: Int) {
                        magicIndicator.onPageScrollStateChanged(state)
                    }

                })
//                ViewPagerHelper.bind(magicIndicator, it)
            }
        }
    }

    /**
     * 2、实现此方法，来自定义titleItemView
     *
     * 不写就是方法内默认样式
     */
    fun createTitleItemView(
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
        normalColor = Color.parseColor("#999999")
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
    fun createIndicator(context: Context?, tag: Int = 0): IPagerIndicator? {
        return LinePagerIndicator(context).apply {
            mode = LinePagerIndicator.MODE_WRAP_CONTENT
            setColors(Color.parseColor("#FCCE48"))
            startInterpolator = AccelerateInterpolator()
            endInterpolator = DecelerateInterpolator(1.6f)
            lineHeight = AutoSizeUtils.mm2px(context, 2.0f).toFloat()
        }
    }

    /**
     * 4、实现此方法、自定义各个Item占的比例
     *
     * 不写就是方法内默认样式
     */
    fun createTitleWeight(context: Context?, index: Int): Float = 1.0F

    /**
     * 选择页面
     */
    fun switchPage(
        magicIndicator: MagicIndicator,
        viewPager: ViewPager?,
        index: Int,
    ) {
        viewPager?.let {
            viewPager.setCurrentItem(index, false)
        } ?: run {
            magicIndicator.onPageScrolled(index, 0f, 0)
            magicIndicator.onPageSelected(index)
        }
    }


}