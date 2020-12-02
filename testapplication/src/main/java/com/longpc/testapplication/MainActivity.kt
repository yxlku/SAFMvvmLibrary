package com.longpc.testapplication

import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import com.longpc.testapplication.databinding.MainActivityMainBinding
import com.safmvvm.mvvm.view.BaseActivity
import com.safmvvm.mvvm.view.BaseSuperActivity
import com.safmvvm.utils.*

class MainActivity : BaseActivity<MainActivityMainBinding, MainViewModel>(
    R.layout.main_activity_main, BR.viewModel
){

    override fun initViewObservable() {
        //设置自定义弹窗
//        setCustomDialog(R.layout.main_dialog_cus_test, "")
        setCustomDialog()
    }

    override fun getLoadSirTarget(): Any? {
        return mBinding.layoutRoot2
    }
}