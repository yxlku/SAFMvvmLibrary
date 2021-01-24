package com.deti.brand.msg

import com.deti.brand.BR
import com.deti.brand.R
import com.deti.brand.databinding.BrandFragmentMsgBinding
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.impl.LoadingPopupView
import com.safmvvm.mvvm.view.BaseFragment

class MsgFragment: BaseFragment<BrandFragmentMsgBinding, MsgViewModel>(
    R.layout.brand_fragment_msg,
    BR.viewModel
){
    override fun initData() {
        super.initData()
        mBinding.brandTextview3.setOnClickListener {
            val loadingPopup = XPopup.Builder(context)
                .dismissOnBackPressed(false)
                .asLoading("正在加载中", R.layout.saf_loading_layout)
                .show() as LoadingPopupView
        }
    }
}