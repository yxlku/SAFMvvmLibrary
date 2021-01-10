package com.safmvvm.ext.ui.tab

import androidx.annotation.DrawableRes
import androidx.viewpager2.widget.ViewPager2
import com.safmvvm.ext.ui.viewpager2.ViewPager2Helper
import me.majiajie.pagerbottomtabstrip.NavigationController
import me.majiajie.pagerbottomtabstrip.PageNavigationView
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem



interface ITabBottom2{

    /**
     * 2、tabItem样式
     */
    fun newTabBottomItem(
        @DrawableRes uncheckIcon: Int,
        @DrawableRes checkedIcon: Int,
        text: String,
    ): BaseTabItem

    /**
     * 3、创建顶部Tab
     */
    fun createBottomTab(
        pageNavigationView: PageNavigationView,
        viewPager2: ViewPager2? = null,
        tabItems: ArrayList<out BaseTabItem>,
        defSelected: Int = 0,
        block: (navigationController: NavigationController) -> Unit = {},
    ) {
        pageNavigationView.custom().apply {
            tabItems.forEach {
                addItem(it)
            }
        }.build().run {
            block(this)
            viewPager2?.let {
                //默认选择
                setSelect(defSelected)
                //可以不使用ViewPager
                ViewPager2Helper.bind(this, it)
                addSimpleTabItemSelectedListener { index, old ->
                    //选择
                    switchPage(this, it, index)
                }
            }
        }

    }

    /**
     * 选择页面
     */
    fun switchPage(
        navigationController: NavigationController,
        viewPager2: ViewPager2,
        index: Int
    ) {
        navigationController.setSelect(index)
        viewPager2.setCurrentItem(index, true)
    }


}