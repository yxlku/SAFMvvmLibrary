package com.deti.brand
import android.content.Context
import androidx.annotation.DrawableRes
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.lxj.xpopup.util.KeyboardUtils
import com.safmvvm.ext.ui.tab.bottom.NewNormalItemView
import com.safmvvm.ext.ui.viewpager.ViewPagerHelper
import com.safmvvm.ext.ui.viewpager2.ViewPager2Helper
import com.safmvvm.utils.ResUtil
import me.jessyan.autosize.utils.AutoSizeUtils
import me.majiajie.pagerbottomtabstrip.NavigationController
import me.majiajie.pagerbottomtabstrip.PageNavigationView
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem
interface ITabBottomCommon{

    fun initTabBottomItem(context: Context): ArrayList<NewNormalItemView> = arrayListOf(
        newTabBottomItem(context, R.mipmap.base_index_tab_main_uncheck, R.mipmap.base_index_tab_main_checked, "首页"),
//        newTabBottomItem(context, R.mipmap.base_index_tab_msg_uncheck, R.mipmap.base_index_tab_msg_checked, "消息"),
        newTabBottomItem(context, R.mipmap.base_index_tab_mine_uncheck, R.mipmap.base_index_tab_mine_checked, "我的"),
    )

    /**
     * 2、tabItem样式
     */
    fun newTabBottomItem(
        context: Context,
        @DrawableRes uncheckIcon: Int,
        @DrawableRes checkedIcon: Int,
        text: String,
    ): NewNormalItemView = NewNormalItemView(context).apply {
        title = text
        setDefaultDrawable(ResUtil.getDrawable(uncheckIcon))
        setSelectedDrawable(ResUtil.getDrawable(checkedIcon))
        setTextDefaultColor(ResUtil.getColor(R.color.colorPrimary))
        setTextCheckedColor(ResUtil.getColor(R.color.colorAccent))
        setIconSize(20, 20)
    }

    /**
     * 3、创建顶部Tab
     */
    fun createBottomTab(
        context: Context,
        pageNavigationView: PageNavigationView,
        viewPager: ViewPager? = null,
        defSelected: Int = 0,
        block: (navigationController: NavigationController) -> Unit = {},
    ) {
        pageNavigationView.custom().apply {
            initTabBottomItem(context).forEach {
                addItem(it)
            }
        }.build().run {
            block(this)
            viewPager?.let {
                //默认选择
                setSelect(defSelected)
                //可以不使用ViewPager
                ViewPagerHelper.bind(this, it)
                addSimpleTabItemSelectedListener { index, old ->
                    //选择
                    switchPage(this, it, index)
                    //隐藏键盘
                    KeyboardUtils.hideSoftInput(it)
                }
            }
        }

    }

    /**
     * 选择页面
     */
    fun switchPage(
        navigationController: NavigationController,
        viewPager: ViewPager,
        index: Int
    ) {
        navigationController.setSelect(index)
        viewPager.setCurrentItem(index, true)
    }


}