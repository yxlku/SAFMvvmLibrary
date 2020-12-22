package com.longpc.testfragment

import android.content.Intent
import com.longpc.testapplication.R
import com.longpc.testapplication.databinding.MainActivityTestFragmentBinding
import com.safmvvm.mvvm.view.BaseActivity
import com.longpc.testapplication.BR

class TestFragmentActivity: BaseActivity<MainActivityTestFragmentBinding, TestFragmentViewModel>(
        R.layout.main_activity_test_fragment,
        BR.viewModel3
) {
    override fun initViewObservable() {
    }
    var fragment = TestFragment()
    override fun initData() {

        supportFragmentManager
            .beginTransaction()
            .replace(mBinding.clLayout.id, fragment)
            .commit()
    }

}