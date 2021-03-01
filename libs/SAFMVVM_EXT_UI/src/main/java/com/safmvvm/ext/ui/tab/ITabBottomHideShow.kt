package com.safmvvm.ext.ui.tab

import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.safmvvm.mvvm.viewmodel.BaseViewModel
import com.safmvvm.utils.LogUtil


interface ITabBottomHideShow {

    abstract fun initFragments(): ArrayList<Fragment>

    abstract fun frameLayout(): Int

    fun FragmentManager.initBottomNavigation(
        bottomNavigationView: BottomNavigationView,
        /** 默认系那是页面*/
        defPage: Int = 0,
        /** 点击某个Item -- 返回true 执行选择页面功能*/
        clickBlock: (position: Int, item: MenuItem, menuItemViews: ArrayList<View>)-> Boolean = { position: Int, item: MenuItem, menuItemViews: ArrayList<View>-> true},
        /** 初始化操作*/
        initBlock: (bottomNavigationView: BottomNavigationView, menuItemViews: ArrayList<View>) -> Unit = {bottomNavigationView: BottomNavigationView, menuItemViews: ArrayList<View>->},
    ){
        var menuItemViews: ArrayList<View> = arrayListOf()
        bottomNavigationView.children.forEach {
            menuItemViews.add(it)
        }
        initBlock(bottomNavigationView, menuItemViews)

        //默认选中的页面
        switchPage(this, defPage)

        bottomNavigationView.itemIconTintList = null
        bottomNavigationView.setOnNavigationItemSelectedListener {
            bottomNavigationView.menu.forEachIndexed { index, item ->
                if (item.itemId == it.itemId) {
                    if (clickBlock(index, it, menuItemViews)) {
                        switchPage(this, index)
                    }
                }
            }
            true
        }

    }

    fun switchPage(fragmentManager: FragmentManager, position: Int){
        val transaction = fragmentManager.beginTransaction()
        fragmentManager.executePendingTransactions()
        fragmentManager.hideFragments(transaction)
        if (position >= 0 && position < initFragments().size) {
            var currFragment = initFragments()[position]
            fragmentManager.findFragmentByTag(currFragment::class.java.simpleName)?.apply {
                transaction.show(this)
            } ?: transaction.add(frameLayout(), currFragment, currFragment::class.java.simpleName)
        }
        transaction.commitNowAllowingStateLoss()
    }




    /**
     *  隐藏所有的Fragment
     */
    fun FragmentManager.hideFragments(transaction: FragmentTransaction) {
        this.fragments.forEach {
            transaction.hide(it)
        }
    }
}