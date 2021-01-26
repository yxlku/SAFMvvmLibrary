package com.deti.designer.msg

import com.deti.designer.R
import com.deti.designer.BR
import com.deti.designer.databinding.DesignerFragmentMsgBinding
import com.safmvvm.mvvm.view.BaseFragment
import com.safmvvm.mvvm.view.BaseLazyFragment
import com.safmvvm.utils.LogUtil

class MsgFragment: BaseLazyFragment<DesignerFragmentMsgBinding, MsgViewModel>(
    R.layout.designer_fragment_msg,
    BR.viewModel
) {

    override fun initData() {
        super.initData()
        LogUtil.d("初始化：msgFragment1")
    }

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()

        LogUtil.d("初始化：msgFragment")
    }
}