package com.deti.basis.search

import com.alibaba.android.arouter.facade.annotation.Route
import com.deti.basis.R
import com.deti.basis.BR
import com.deti.basis.databinding.BasisActivitySearchBinding
import com.safmvvm.mvvm.view.BaseActivity
import com.test.common.RouterActivityPath

@Route(path = RouterActivityPath.ModuleBasis.PAGE_SEARCH)
class SearchActivity: BaseActivity<BasisActivitySearchBinding, SearchViewModel>(
    R.layout.basis_activity_search,
    BR.viewModel
) {

    override fun initData() {
        super.initData()

        var chacheTagList = arrayListOf<String>(
            "tag5",
            "tag2",
            "KFC",
            "KFC",
            "KFC",
            "KFC",
            "KFC",
            "KFC",
            "KFC",
            "KFC",
            "KFC",
            "KFC",
            "麦当劳"
        )
        mBinding.tagHistory.tags = chacheTagList
    }
}