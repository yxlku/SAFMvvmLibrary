package com.deti.basis.personal

import androidx.fragment.app.Fragment
import com.deti.basis.BR
import com.deti.basis.R
import com.deti.basis.databinding.BasisActivityPersonalBinding
import com.safmvvm.ext.ui.tab.ITabTop
import com.safmvvm.ext.ui.viewpager.createViewPager
import com.safmvvm.mvvm.view.BaseActivity

class PersonalActivity : BaseActivity<BasisActivityPersonalBinding, PersonalViewModel>(
    R.layout.basis_activity_personal,
    BR.viewModel
), ITabTop {
    var titles = arrayListOf<String>(
        "完善注册信息",
        "添加子账号",
        "添加收货地址"
    )
    var fragments = arrayListOf<Fragment>(

    )

    override fun initData() {
        super.initData()

        //初始化viewpage
        fragments.createViewPager(supportFragmentManager, mBinding.vpContent)
        //初始化tab
        initTabTop(
            this,
            mBinding.miTab,
            mBinding.vpContent,
            titles,
            true
        )

    }
}