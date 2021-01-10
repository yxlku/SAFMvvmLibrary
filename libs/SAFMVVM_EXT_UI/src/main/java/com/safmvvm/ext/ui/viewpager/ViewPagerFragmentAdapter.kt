package com.safmvvm.ext.ui.viewpager

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

/**
 * 1、创建ViewPager2
 */
fun ArrayList<Fragment>.createViewPager(
    fragmentManager: FragmentManager,
    viewPager: ViewPager,
    block: (viewPager: ViewPager) -> Unit = {},
): ViewPager {
    var viewPagerFragmentAdapter = ViewPagerFragmentAdapter(fragmentManager, this)
    return viewPager.apply {
        adapter = viewPagerFragmentAdapter
        block(this)
    }
}

class ViewPagerFragmentAdapter(
    fragmentManager: FragmentManager,
    var mFragments: ArrayList<Fragment>
): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int = mFragments.size

    override fun getItem(position: Int): Fragment = mFragments[position]
}



