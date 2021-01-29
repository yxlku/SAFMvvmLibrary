package com.deti.designer.version

import androidx.fragment.app.Fragment
import com.deti.designer.R
import com.deti.designer.BR
import com.deti.designer.databinding.DesignerFragmentVersionListBinding
import com.deti.designer.version.datalist.VersionDataListFragment
import com.deti.designer.version.datalist.VersionDataListFragment.Companion.TYPE_ADD_UN
import com.deti.designer.version.datalist.VersionDataListFragment.Companion.TYPE_ALL
import com.deti.designer.version.datalist.VersionDataListFragment.Companion.TYPE_COMPLETE
import com.deti.designer.version.datalist.VersionDataListFragment.Companion.TYPE_COMPLETE_UN
import com.safmvvm.ext.ui.tab.ITabTop
import com.safmvvm.ext.ui.viewpager.createViewPager
import com.safmvvm.mvvm.view.BaseFragment
import com.safmvvm.mvvm.view.BaseLazyFragment

/**
 * 版单
 */
class VersionListFragment: BaseLazyFragment<DesignerFragmentVersionListBinding, VersionListViewModel>(
    R.layout.designer_fragment_version_list,
    BR.viewModel
), ITabTop{

    var titles = arrayListOf(
        "全部",
        "未添加",
        "未完成",
        "已完成",
    )
    var fragments = arrayListOf<Fragment>()

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
        fragments.apply {
            add(VersionDataListFragment(TYPE_ALL))
            add(VersionDataListFragment(TYPE_ADD_UN))
            add(VersionDataListFragment(TYPE_COMPLETE))
            add(VersionDataListFragment(TYPE_COMPLETE_UN))
        }

        fragments.createViewPager(childFragmentManager, mBinding.vpContent)
        initTabTop(context, mBinding.miTab, mBinding.vpContent, titles, true)
    }


}