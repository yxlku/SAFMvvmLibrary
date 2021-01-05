package com.deti.basis.login

import android.content.Context
import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.deti.basis.R
import com.deti.basis.databinding.BasisActivityLoginBinding
import com.safmvvm.mvvm.view.BaseActivity
import com.deti.basis.BR
import com.deti.basis.login.password.PasswordFragment
import com.deti.basis.login.verification.VerificationFragment
import com.safmvvm.app.AppActivityManager
import com.safmvvm.ext.ui.ViewPager2FragmentAdapter
import com.safmvvm.ext.ui.ViewPager2Helper
import com.test.common.RouterActivityPath
import net.lucode.hackware.magicindicator.FragmentContainerHelper
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView


@Route(path = RouterActivityPath.ModuleBasis.PAGE_LOGIN)
class LoginActivity: BaseActivity<BasisActivityLoginBinding, LoginViewModel>(
    R.layout.basis_activity_login,
    BR.viewModel
) {
    var titles = arrayOf("手机快捷登录", "账号密码登录")
    var mFagments = arrayListOf<Fragment>()
    var mFragmentContainerHelper = FragmentContainerHelper()

    override fun initData() {
        //此页面去除侧滑关闭
        cleanSwipeback()

        //初始化Fragment
        initFragment()
        //初始化指示器
        initMagicIndicator()
        //默认选中页面
        switchPages(0)
    }

    private fun initFragment() {
        //验证码登录
        var verificationFragment = VerificationFragment()
        //手机号登录
        var passwordFragment = PasswordFragment()
        mFagments.add(verificationFragment)
        mFagments.add(passwordFragment)

        var fragmentAdapter = ViewPager2FragmentAdapter(this, mFagments)
        mBinding.basisVpContent.run {
            isUserInputEnabled = true
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = fragmentAdapter
        }
    }

    private fun initMagicIndicator() {
        var commonNavigator = CommonNavigator(this)
        commonNavigator.adapter = object : CommonNavigatorAdapter(){
            override fun getCount(): Int = titles.size

            override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
                return ClipPagerTitleView(context).apply {
                    text = titles[index]
                    textColor = Color.parseColor("#333333")
                    clipColor = Color.parseColor("#1B2643")
                    setOnClickListener {
                        switchPages(index)
                    }
                }
            }

            override fun getIndicator(context: Context?): IPagerIndicator {
                return LinePagerIndicator(context).apply {
                    mode = LinePagerIndicator.MODE_WRAP_CONTENT
                    setColors(Color.parseColor("#FCCE48"))
                }
            }

            override fun getTitleWeight(context: Context?, index: Int): Float {
                return 1.0f
            }
        }
        var magicIndicator = mBinding.basisMiTab.apply {
            navigator = commonNavigator
        }
        ViewPager2Helper.bind(magicIndicator, mBinding.basisVpContent)
    }

    private fun switchPages(index: Int){
        mFragmentContainerHelper.handlePageSelected(index, false)
        mBinding.basisVpContent.currentItem = index
    }

    override fun onBackPressed() {
        //B端App必须登录，如果不登录，则直接退出，如果弹出此页面也就意味着不登录进不了其他页面，返回理应退出app
        AppActivityManager.finishAllActivity()
    }

}