package com.deti.designer.materiel.popup.push.order

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.deti.designer.R
import com.deti.designer.BR
import com.deti.designer.databinding.DesignerItemPopupDistributeOrderBinding
import com.deti.designer.materiel.popup.push.adapter.DistributeOrderAdapter
import com.deti.designer.materiel.popup.push.entity.DistributeOrderEntity
import com.safmvvm.mvvm.view.BaseLazyFragment

/**
 * 派单、抢单
 */
class DistributeOrderFragment(
    /** 页面类型*/
    var type: String = ORDER_DISPATCH
): BaseLazyFragment<DesignerItemPopupDistributeOrderBinding, DistributeOrderViewModel>(
    R.layout.designer_item_popup_distribute_order,
    BR.viewModel
) {
    var mAdapter = DistributeOrderAdapter()
    var listData = arrayListOf(
        DistributeOrderEntity(),
        DistributeOrderEntity(),
        DistributeOrderEntity(),
        DistributeOrderEntity(),
    )
    companion object{
        /** 派单*/
        const val ORDER_DISPATCH = "order_Dispatch"
        /** 抢单*/
        const val ORDER_GRAB = "order_Grab"
    }

    override fun initData() {
        super.initData()
        var footerView = LayoutInflater.from(context).inflate(R.layout.designer_footer_distribute_order, null, false)
        mAdapter.addFooterView(footerView)

        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        mAdapter.setList(listData)
        mAdapter.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                var data = adapter.data[position] as DistributeOrderEntity

                data.isChecked = !data.isChecked
                adapter.notifyDataSetChanged()
            }
        })
    }



}