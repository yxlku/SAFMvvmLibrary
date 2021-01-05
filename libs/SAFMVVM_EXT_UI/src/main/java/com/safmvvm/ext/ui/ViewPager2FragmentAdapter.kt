package com.safmvvm.ext.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPager2FragmentAdapter(
    mAcitivity: AppCompatActivity,
    var mFragments: ArrayList<Fragment>
): FragmentStateAdapter(mAcitivity) {

    override fun getItemCount(): Int = mFragments.size

    override fun createFragment(position: Int): Fragment = mFragments[position]
}