package com.deti.designer.materiel.popup.push

import androidx.fragment.app.Fragment
import com.deti.designer.R
import com.deti.designer.BR
import com.deti.designer.databinding.DesignerPopupPushMaterielBinding
import com.deti.designer.materiel.popup.push.order.DistributeOrderFragment
import com.safmvvm.ext.ui.tab.ITabTop
import com.safmvvm.ext.ui.viewpager.createViewPager
import com.safmvvm.mvvm.view.bottom.BaseBottomFragment

/**
 * 物料推送
 */
class PushMaterielFragment: BaseBottomFragment<DesignerPopupPushMaterielBinding, PushMaterielViewModel>(
    R.layout.designer_popup_push_materiel,
    BR.viewModel
), ITabTop {
    var titles = arrayListOf("派单", "抢单")
    var fragments = arrayListOf<Fragment>(
        DistributeOrderFragment(DistributeOrderFragment.ORDER_DISPATCH), //派单
        DistributeOrderFragment(DistributeOrderFragment.ORDER_GRAB),    //抢单
    )
    override fun initData() {
        super.initData()

        fragments.createViewPager(childFragmentManager, mBinding.vpContent)


        initTabTop(context, mBinding.miTab, mBinding.vpContent, titles)

    }
}