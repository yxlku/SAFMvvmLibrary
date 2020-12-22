package com.longpc.testfragment

import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import com.longpc.testapplication.R
import com.longpc.testapplication.databinding.MainFragmentTestBinding
import com.safmvvm.mvvm.view.BaseFragment
import com.longpc.testapplication.BR
import com.safmvvm.utils.jetpack.putValue

class TestFragment : BaseFragment<MainFragmentTestBinding, TestViewModel>(
    R.layout.main_fragment_test,
    BR.viewModel2
) {
//    override fun onActivityResultOk(intent: Intent) {
//        super.onActivityResultOk(intent)
//        var s = intent.getStringExtra("ca")
//        mViewModel.text.putValue(s)
//    }
}