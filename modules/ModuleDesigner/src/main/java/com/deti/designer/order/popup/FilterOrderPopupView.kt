package com.deti.designer.order.popup

import android.app.Activity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deti.designer.R
import com.deti.designer.order.popup.adapter.FilterOrderPopupAdapter
import com.lxj.xpopup.core.BottomPopupView
import com.test.common.ui.popup.base.BaseDialogSingleEntity

/**
 * 筛选弹窗
 */
class FilterOrderPopupView(
    var mActivity: Activity,
) : BottomPopupView(mActivity) {


    override fun getImplLayoutId(): Int = R.layout.designer_popup_order_filter

    var typeAdapter = FilterOrderPopupAdapter(0)
    var costAdapter = FilterOrderPopupAdapter(0)
    override fun onCreate() {
        super.onCreate()

        var rv_type = findViewById<RecyclerView>(R.id.rv_type)
        var rv_cost = findViewById<RecyclerView>(R.id.rv_cost)

        rv_type.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = typeAdapter
        }
        rv_cost.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = costAdapter
        }

        typeAdapter.setList(typeData())
        costAdapter.setList(costData())

        typeAdapter.setOnItemClickListener { adapter, view, position ->
            typeAdapter.selectedPosition = position
            typeAdapter.notifyDataSetChanged()
        }

        costAdapter.setOnItemClickListener { adapter, view, position ->
            costAdapter.selectedPosition = position
            costAdapter.notifyDataSetChanged()
        }
    }

    fun typeData(): ArrayList<BaseDialogSingleEntity> {
        var list = arrayListOf<BaseDialogSingleEntity>(
            BaseDialogSingleEntity("0", "女装"),
            BaseDialogSingleEntity("1", "男装"),
            BaseDialogSingleEntity("2", "女童"),
            BaseDialogSingleEntity("3", "男童"),
        )
        return list
    }

    fun costData(): ArrayList<BaseDialogSingleEntity> {
        var list = arrayListOf<BaseDialogSingleEntity>(
            BaseDialogSingleEntity("0", "0-50"),
            BaseDialogSingleEntity("1", "50-100"),
            BaseDialogSingleEntity("2", "100-200"),
            BaseDialogSingleEntity("3", "200以上"),
        )
        return list
    }

}