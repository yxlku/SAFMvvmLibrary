package com.deti.brand.demand.detail.material

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.deti.brand.BR
import com.deti.brand.R
import com.deti.brand.databinding.BrandFragmentCostMaterialBinding
import com.deti.brand.demand.detail.entity.MaterialCostDetailEntity
import com.deti.brand.demand.detail.entity.MaterialCostItemEntity
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.mvvm.view.BaseLazyFragment
import com.test.common.ui.adapter.tab.IAdapterTabEntity
import com.test.common.ui.adapter.tab.TabAdapter
import com.test.common.ui.item.infodetail.ItemChoose
import com.test.common.ui.item.infodetail.ItemChooseEntity

/**
 * 物料成本
 */
class MaterialCostFragment :
    BaseLazyFragment<BrandFragmentCostMaterialBinding, MaterialCostViewModel>(
        R.layout.brand_fragment_cost_material,
        BR.viewModel
    ) {

    companion object {
        /** 刷新tab信息*/
        const val LIVE_TAB_INFO = "live_tab_info"
    }

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
        //懒加载数据
        mViewModel.requestFindFabricList()

        //物料信息适配器
        var infoAdapter = BaseBinderAdapter().apply {
            addItemBinder(ItemChooseEntity::class.java, ItemChoose())
        }
        //tab 点击事件
        var tabAdapter = TabAdapter { adapter: BaseQuickAdapter<*, *>, view: View, position: Int, data: IAdapterTabEntity ->
            var data = data as MaterialCostItemEntity
            switchMaterialInfo(infoAdapter, data)
        }

        mBinding?.apply {
            //tab
            rvTab.apply {
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
                adapter = tabAdapter
            }
            //详情列表
            rvContent.apply {
                layoutManager = GridLayoutManager(context, 2).apply {
                    spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return if(position <= 11){
                                1
                            }else{
                                2
                            }
                        }
                    }
                }
                adapter = infoAdapter
            }
        }

        LiveDataBus.observe<MaterialCostDetailEntity>(this, LIVE_TAB_INFO, {
            tabAdapter.setList(it.fabricList)
        }, false)

    }

    /**
     * 切换信息
     */
    private fun switchMaterialInfo(infoAdapter: BaseBinderAdapter, data: MaterialCostItemEntity) {
        var infoList = arrayListOf<Any>(
            ItemChooseEntity("", "品名",data.name),
            ItemChooseEntity("", "幅宽",data.breadth, "cm"),
            ItemChooseEntity("", "成份",data.ingredient),
            ItemChooseEntity("", "损耗",data.lossPercent, "%"),
            ItemChooseEntity("", "颜色",data.color),
            ItemChooseEntity("", "色号",data.colorNumber),
            ItemChooseEntity("", "用量",data.singleQuantity),
            ItemChooseEntity("", "单位",data.unit),
            ItemChooseEntity("", "经缩",data.shrinkLongPercent, "%"),
            ItemChooseEntity("", "纬缩",data.shrinkLatPercent, "%"),
            ItemChooseEntity("", "总用量",data.totalQuantity),
            ItemChooseEntity("", "单价",data.unitPrice, "元"),
            ItemChooseEntity("", "总金额",data.totalPrice, "元"),
            ItemChooseEntity("", "物料金额合计","待定。。", "元"),
        )
        infoAdapter.setList(infoList)
    }
}