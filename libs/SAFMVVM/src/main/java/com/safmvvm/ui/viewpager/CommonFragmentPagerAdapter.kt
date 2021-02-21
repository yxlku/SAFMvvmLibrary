package com.safmvvm.ui.viewpager

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class CommonFragmentPagerAdapter(manager: FragmentManager, behavior: Int, var fragments: ArrayList<Fragment>): FragmentStatePagerAdapter(manager,behavior) {

    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    }
}