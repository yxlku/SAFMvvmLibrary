package com.deti.designer.mine

import com.deti.designer.R
import com.deti.designer.BR
import com.deti.designer.databinding.DesignerFragmentMineBinding
import com.safmvvm.mvvm.view.BaseFragment
import com.safmvvm.mvvm.view.BaseLazyFragment
import com.safmvvm.utils.LogUtil

class MineFragment: BaseLazyFragment<DesignerFragmentMineBinding, MineViewModel>(
    R.layout.designer_fragment_mine,
    BR.viewModel
) {

    override fun initData() {
        super.initData()
        LogUtil.d("初始化：mineFragment")

    }

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
        LogUtil.d("初始化：mineFragment1")
    }
}