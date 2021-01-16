package com.deti.brand.demand.sampleclothes.all

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.node.BaseNode
import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandFragmentSimpleClothesListAllBinding
import com.deti.brand.demand.sampleclothes.all.adapter.SimpleClothesListAllAdapter
import com.deti.brand.demand.sampleclothes.all.entity.FirstNodeEntity
import com.deti.brand.demand.sampleclothes.all.entity.SecondNodeEntity
import com.safmvvm.mvvm.view.BaseFragment

class SimpleClothesListAllFragment: BaseFragment<BrandFragmentSimpleClothesListAllBinding, SimpleClothesListAllViewModel>(
    R.layout.brand_fragment_simple_clothes_list_all,
    BR.viewModel
) {


    override fun initData() {
        super.initData()
        var mAdapter = SimpleClothesListAllAdapter(activity)
        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        mAdapter.setList(testData())
    }

    fun testData(): List<FirstNodeEntity> {
        var firstNode = arrayListOf<FirstNodeEntity>()
        for (i in 0 until 3) {
            var secondNodes = arrayListOf<BaseNode>()
            for (j in 0 until 5) {
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