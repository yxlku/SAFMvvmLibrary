package com.example.moduletesta

import com.example.moduletesta.databinding.TestaActivityMainBinding
import com.safmvvm.mvvm.view.BaseActivity

class TestAMainActivity: BaseActivity<TestaActivityMainBinding, TestAViewModel>(
    R.layout.testa_activity_main,
    BR.viewModel
) {
    override fun initViewObservable() {
    }

    override fun initData() {
    }
}