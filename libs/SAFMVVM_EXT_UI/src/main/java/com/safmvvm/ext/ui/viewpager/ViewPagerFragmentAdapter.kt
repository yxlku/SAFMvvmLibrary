package com.safmvvm.ext.ui.viewpager

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.safmvvm.mvvm.view.BaseLazyFragment
import com.safmvvm.ui.viewpager.FragmentViewPagerAdapter

/**
 * 1、创建ViewPager2
 */
fun ArrayList<Fragment>.createViewPager(
    fragmentManager: FragmentManager,
    viewPager: ViewPager,
    block: (viewPager: ViewPager) -> Unit = {},
): ViewPager {
    var viewPagerFragmentAdapter = FragmentViewPagerAdapter(fragmentManager, viewPager, this)
    return viewPager.apply {
//        offscreenPageLimit = 5
        adapter = viewPagerFragmentAdapter
        block(this)
    }
}


