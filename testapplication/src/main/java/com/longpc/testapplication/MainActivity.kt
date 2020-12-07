package com.longpc.testapplication

import com.longpc.testapplication.databinding.MainActivityMainBinding
import com.safmvvm.mvvm.view.BaseActivity

class MainActivity : BaseActivity<MainActivityMainBinding, MainViewModel>(
    R.layout.main_activity_main, BR.viewModel
){

    override fun initViewObservable() {
        //设置自定义弹窗
//        setCustomDialog(R.layout.main_dialog_cus_test, "")
        setCustomDialog()
    }

    override fun onLoadSirReload() {
        mViewModel.testPostFlow()
    }
}