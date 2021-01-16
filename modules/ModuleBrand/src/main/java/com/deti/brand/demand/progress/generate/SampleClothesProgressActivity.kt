package com.deti.brand.demand.progress.generate

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.baoyachi.stepview.bean.StepBean
import com.deti.brand.BR
import com.deti.brand.R
import com.deti.brand.databinding.BrandActivitySampleClothesProgressBinding
import com.deti.brand.demand.progress.OrderStatus
import com.deti.brand.demand.progress.logistics.adapter.LogisticsStateEntity
import com.safmvvm.mvvm.view.BaseActivity


class SampleClothesProgressActivity: BaseActivity<BrandActivitySampleClothesProgressBinding, SampleClothesProgressViewModel>(
    R.layout.brand_activity_sample_clothes_progress,
    BR.viewModel
) {
    val stepsList = arrayListOf<String>(
        "样衣发货",
        "样衣运输",
        "样衣签收"
    )

    override fun initData() {
        super.initData()

        mBinding.stepView.apply {
            setSteps(stepsList)
            setStepsNumber(stepsList.size)
            go(2, true)
        }
    }

}