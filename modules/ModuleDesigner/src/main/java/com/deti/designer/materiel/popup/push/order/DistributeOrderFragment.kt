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
import com.safmvvm.mvvm.view.BaseFragment
import com.safmvvm.mvvm.view.BaseLazyFragment
import com.safmvvm.utils.LogUtil
import java.util.ArrayList

/**
 * 派单、抢单
 */
class DistributeOrderFragment(
    /** 页面类型*/
    var type: String = ORDER_DISPATCH
): BaseFragment<DesignerItemPopupDistributeOrderBinding, DistributeOrderViewModel>(
    R.layout.designer_item_popup_distribute_order,
    BR.viewModel
) {
    var mAdapter = DistributeOrderAdapter(type)

    companion object{
        /** 派单*/
        const val ORDER_DISPATCH = "order_Dispatch"
        /** 抢单*/
        const val ORDER_GRAB = "order_Grab"
    }

    override fun initData() {
        super.initData()
        var footerView = LayoutInflater.from(context).inflate(R.layout.designer_footer_distribute_order, null, false)

        if (type != ORDER_GRAB) {
            mBinding.tvOrderNum.text = "需求单号：123"
        }else{
            mBinding.tvOrderNum.text = "需求单号：234"
        }
        var listData = arrayListOf(
            DistributeOrderEntity(),
            DistributeOrderEntity(),
            DistributeOrderEntity(),
            DistributeOrderEntity(),
        )

        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        mAdapter.setList(listData)
        mAdapter.addFooterView(footerView)

        mBinding.tvDel.setOnClickListener {
            var delItemData = arrayListOf<DistributeOrderEntity>()
            var sb = StringBuilder()
            mAdapter.data.forEach {
                if (it.isChecked) {
                    sb.append("删除的备注：${it.remark.get()}")
                    delItemData.add(it)
                }
            }
            LogUtil.d(sb.toString())
            delCheckData(delItemData)
        }
        mBinding.tvAdd.setOnClickListener {
            mAdapter.addData(DistributeOrderEntity())
        }
    }

    private fun delCheckData(delItemData: ArrayList<DistributeOrderEntity>) {
        mAdapter.apply {
            data.removeAll(delItemData)
            notifyDataSetChanged()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        LogUtil.d("test销毁了")
    }


}