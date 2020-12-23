package com.longpc.testfragment

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import com.longpc.testapplication.R
import com.longpc.testapplication.databinding.MainFragmentTestBinding
import com.safmvvm.mvvm.view.BaseFragment
import com.longpc.testapplication.BR
import com.safmvvm.ui.titlebar.OnTitleBarListener
import com.safmvvm.utils.ToastUtil

class TestFragment : BaseFragment<MainFragmentTestBinding, TestViewModel>(
    R.layout.main_fragment_test,
    BR.viewModel2
) {

    override fun titleBackFinish(): Boolean = false

    override fun initData() {
        super.initData()
        mTitleBar?.let {
            it.setOnTitleBarListener(object : OnTitleBarListener {
                override fun onLeftClick(v: View?) {
                    ToastUtil.showShortToast("我点击标题返回了！！")
                }

                override fun onTitleClick(v: View?) {
                }

                override fun onRightClick(v: View?) {
                }

            })
        }
    }
}