package com.safmvvm.ext.ui.viewpager

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.safmvvm.mvvm.view.BaseLazyFragment
import com.safmvvm.ui.viewpager.CommonFragmentPagerAdapter
import com.safmvvm.ui.viewpager.FragmentViewPagerAdapter

/**
 * 1、创建ViewPager
 */
fun ArrayList<Fragment>.createViewPager(
    fragmentManager: FragmentManager,
    viewPager: ViewPager,
    block: (viewPager: ViewPager) -> Unit = {},
): ViewPager {
    var viewPagerFragmentAdapter = CommonFragmentPagerAdapter(fragmentManager,
        FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, this)
    return viewPager.apply {
//        offscreenPageLimit = 5
        adapter = viewPagerFragmentAdapter
        block(this)
    }
}


