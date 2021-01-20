package com.deti.brand.demand.sampleclothes.list

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.node.BaseNode
import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandFragmentSimpleClothesListAllBinding
import com.deti.brand.demand.sampleclothes.list.adapter.list.SimpleClothesListAllAdapter
import com.deti.brand.demand.sampleclothes.list.entity.FirstNodeEntity
import com.deti.brand.demand.sampleclothes.list.entity.SecondNodeEntity
import com.safmvvm.mvvm.view.BaseFragment

class SimpleClothesListAllFragment(
    /** 列表状态*/
    var mStateList: Int = StateListSimpleClothesEnum.STATE_ALL
): BaseFragment<BrandFragmentSimpleClothesListAllBinding, SimpleClothesListAllViewModel>(
    R.layout.brand_fragment_simple_clothes_list_all,
    BR.viewModel
) {

    override fun initData() {
        super.initData()
        var mAdapter = SimpleClothesListAllAdapter(activity, mStateList)
        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        mAdapter.setList(testData())
    }

    fun testData(): List<FirstNodeEntity> {
        var firstNode = arrayListOf<FirstNodeEntity>()
        for (i in 0 until 1) {
            var secondNodes = arrayListOf<BaseNode>()
            for (j in 0 until 4) {
                var secondNodeEntity = SecondNodeEntity(
                    j,
                    j
                )
                secondNodes.add(secondNodeEntity)
            }
            firstNode.add(FirstNodeEntity(i, "屎黄$i", 0, secondNodes))
        }
        return firstNode
    }
}