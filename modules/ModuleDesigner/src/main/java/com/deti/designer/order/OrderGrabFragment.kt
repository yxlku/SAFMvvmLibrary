package com.deti.designer.order

import androidx.recyclerview.widget.LinearLayoutManager
import com.deti.designer.R
import com.deti.designer.BR
import com.deti.designer.databinding.DesignerFragmentOrderGrabBinding
import com.deti.designer.order.adapter.OrderGrabAdapter
import com.deti.designer.order.entity.OrderGrabEntity
import com.deti.designer.order.popup.FilterOrderPopupView
import com.safmvvm.mvvm.view.BaseFragment
import com.safmvvm.mvvm.view.BaseLazyFragment
import com.test.common.ui.popup.createDialogBase

/**
 * 抢单
 */
class OrderGrabFragment(
    var mState: Int = STATE_GRAB
): BaseLazyFragment<DesignerFragmentOrderGrabBinding, OrderGrabViewModel>(
    R.layout.designer_fragment_order_grab,
    BR.viewModel
) {
    companion object{
        /** 抢单*/
        const val STATE_GRAB: Int = 0
        /** 派单*/
        const val STATE_DISPATCH: Int = 1
    }

    override fun initData() {
        super.initData()

        mBinding.tvListTitleFilter.setOnClickListener{
            activity?.apply {
                createDialogBase(FilterOrderPopupView(this)).show()
            }
        }


        var mAdapter = OrderGrabAdapter(activity, mState)
        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        mAdapter.setList(testData())
    }

    fun testData(): ArrayList<OrderGrabEntity>{
        var list = arrayListOf<OrderGrabEntity>(
            OrderGrabEntity("https://pics3.baidu.com/feed/b151f8198618367a23025a8c30846dd3b11ce5d3.jpeg?token=876c16a00d1d12186cda81073a641e00&s=AF325F85834B2F4D20DC6D000300F0C3"),
            OrderGrabEntity("https://pics3.baidu.com/feed/b151f8198618367a23025a8c30846dd3b11ce5d3.jpeg?token=876c16a00d1d12186cda81073a641e00&s=AF325F85834B2F4D20DC6D000300F0C3"),
            OrderGrabEntity("https://pics3.baidu.com/feed/b151f8198618367a23025a8c30846dd3b11ce5d3.jpeg?token=876c16a00d1d12186cda81073a641e00&s=AF325F85834B2F4D20DC6D000300F0C3"),
            OrderGrabEntity("https://pics3.baidu.com/feed/b151f8198618367a23025a8c30846dd3b11ce5d3.jpeg?token=876c16a00d1d12186cda81073a641e00&s=AF325F85834B2F4D20DC6D000300F0C3"),
            OrderGrabEntity("https://pics3.baidu.com/feed/b151f8198618367a23025a8c30846dd3b11ce5d3.jpeg?token=876c16a00d1d12186cda81073a641e00&s=AF325F85834B2F4D20DC6D000300F0C3"),
            OrderGrabEntity("https://pics3.baidu.com/feed/b151f8198618367a23025a8c30846dd3b11ce5d3.jpeg?token=876c16a00d1d12186cda81073a641e00&s=AF325F85834B2F4D20DC6D000300F0C3"),
            OrderGrabEntity("https://pics3.baidu.com/feed/b151f8198618367a23025a8c30846dd3b11ce5d3.jpeg?token=876c16a00d1d12186cda81073a641e00&s=AF325F85834B2F4D20DC6D000300F0C3"),
        )
        return list
    }
}