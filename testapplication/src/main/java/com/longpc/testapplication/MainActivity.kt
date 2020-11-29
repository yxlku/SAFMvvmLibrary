package com.longpc.testapplication

import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import com.longpc.testapplication.databinding.MainActivityMainBinding
import com.safmvvm.mvvm.view.BaseSuperActivity
import com.safmvvm.utils.*

class MainActivity : BaseSuperActivity<MainActivityMainBinding, MainViewModel>(
    R.layout.main_activity_main, BR.viewModel
){

    override fun initUiChangeLiveData() {
    }

    override fun initViewObservable() {
    }
}