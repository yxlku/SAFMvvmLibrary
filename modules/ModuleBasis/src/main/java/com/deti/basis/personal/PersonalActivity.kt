package com.deti.basis.personal

import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.deti.basis.BR
import com.deti.basis.R
import com.deti.basis.databinding.BasisActivityPersonalBinding
import com.deti.basis.personal.address.AddAddressFragment
import com.deti.basis.personal.perfect.PerfectPersonalFragment
import com.deti.basis.personal.subaccount.AddSubAccountFragment
import com.safmvvm.ext.ui.tab.ITabTop
import com.safmvvm.ext.ui.viewpager.createViewPager
import com.safmvvm.mvvm.view.BaseActivity
import com.test.common.RouterActivityPath
import net.lucode.hackware.magicindicator.MagicIndicator

@Route(path = RouterActivityPath.ModuleBasis.PAGE_PERFECT_PERSONAL)
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
        PerfectPersonalFragment(),
        AddSubAccountFragment(),
        AddAddressFragment()
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
    /**
     * 选择页面
     */
    override fun switchPage(
        magicIndicator: MagicIndicator,
        viewPager: ViewPager?,
        index: Int,
    ) {
        viewPager?.let {
            viewPager.setCurrentItem(index, true)
        } ?: run {
            magicIndicator.onPageScrolled(index, 0f, 0)
            magicIndicator.onPageSelected(index)
        }
    }

}