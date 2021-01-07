package com.safmvvm.ext.ui.tab

import android.content.Context
import android.graphics.Color
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.viewpager2.widget.ViewPager2
import com.safmvvm.ext.ui.viewpager2.ViewPager2Helper
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
        viewPager2: ViewPager2? = null,
        titles: ArrayList<String>,
        isAdjust: Boolean = false,
    ) {
        context?.let {
            var commonNavigator = CommonNavigator(context).apply {
                isAdjustMode = isAdjust
                adapter = object : CommonNavigatorAdapter() {
                    override fun getCount(): Int = titles.size

                    override fun getTitleView(context: Context, index: Int): IPagerTitleView? =
                        createTitleItemView(context, magicIndicator, viewPager2, index, titles)

                    override fun getIndicator(context: Context): IPagerIndicator? = createIndicator(context)

                    override fun getTitleWeight(context: Context?, index: Int): Float = createTitleWeight(context, index)
                }
            }
            magicIndicator.navigator = commonNavigator
            viewPager2?.let {
                ViewPager2Helper.bind(magicIndicator, it)
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
        viewPager2: ViewPager2? = null,
        index: Int,
        titles: ArrayList<String>,
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
    fun createIndicator(context: Context?): IPagerIndicator? {
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
        viewPager2: ViewPager2?,
        index: Int,
    ) {
        viewPager2?.let {
            viewPager2?.setCurrentItem(index, true)
        } ?: run{
            magicIndicator.onPageScrolled(index, 0f, 0)
            magicIndicator.onPageSelected(index)
        }
    }


}