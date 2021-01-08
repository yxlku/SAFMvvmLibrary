package com.safmvvm.ext.ui.viewpager2

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

/**
 * 1、创建ViewPager2
 */
fun ArrayList<Fragment>.createViewPager2(
    activity: AppCompatActivity,
    viewPager2: ViewPager2,
    isScroll: Boolean = true,
    block: (viewPager2: ViewPager2) -> Unit = {},
): ViewPager2 {
    var viewPager2FragmentAdapter = ViewPager2FragmentAdapter(activity, this)
    return viewPager2.apply {
        adapter = viewPager2FragmentAdapter
        //禁止滑动
        isUserInputEnabled = isScroll
        block(this)
    }
}

class ViewPager2FragmentAdapter(
    mAcitivity: AppCompatActivity,
    var mFragments: ArrayList<Fragment>
): FragmentStateAdapter(mAcitivity) {

    override fun getItemCount(): Int = mFragments.size

    override fun createFragment(position: Int): Fragment = mFragments[position]
}