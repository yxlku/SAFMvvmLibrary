package com.deti.designer.materiel.popup.revoke

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.deti.designer.R
import com.deti.designer.BR
import com.deti.designer.databinding.DesignerItemRemarkBinding
import com.deti.designer.databinding.DesignerPopupRevokeBinding
import com.deti.designer.materiel.popup.revoke.adapter.RevokeInfoAdapter
import com.deti.designer.materiel.popup.revoke.adapter.RevokeInfoEntity
import com.safmvvm.mvvm.view.bottom.BaseBottomFragment

/**
 * 撤回
 */
class DialogRevokeFragment: BaseBottomFragment<DesignerPopupRevokeBinding, DialogRevokeViewModel>(
    R.layout.designer_popup_revoke,
    BR.viewModel
) {

    var mAdapter = RevokeInfoAdapter()

    override fun initData() {
        super.initData()
//        var footerView = View.inflate(context, R.layout.designer_footer_revoke, null)
        var footerView = LayoutInflater.from(context).inflate(R.layout.designer_footer_distribute_order, null, false)
        var listData = arrayListOf(
            RevokeInfoEntity(),
            RevokeInfoEntity(),
            RevokeInfoEntity(),
            RevokeInfoEntity(),
            RevokeInfoEntity(),
        )
        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        mAdapter.setList(listData)
        mAdapter.addFooterView(footerView)
    }
}