package com.deti.brand.demand.progress.generate

import android.widget.TextView
import com.deti.brand.BR
import com.deti.brand.R
import com.deti.brand.databinding.BrandActivitySampleClothesProgressBinding
import com.deti.brand.demand.progress.generate.entity.SapmleClothesLogisticsEntity
import com.safmvvm.ext.ui.progressview.PorgressStepView
import com.safmvvm.mvvm.view.BaseActivity


class SampleClothesProgressActivity: BaseActivity<BrandActivitySampleClothesProgressBinding, SampleClothesProgressViewModel>(
    R.layout.brand_activity_sample_clothes_progress,
    BR.viewModel
) {
    val stepsList = arrayListOf(
        "样衣发货",
        "样衣运输",
        "样衣签收"
    )

    override fun initData() {
        super.initData()

        mBinding.stepView.apply {
            setSteps(stepsList)
            setOnStepClickListener {
                go(it, true)
            }
            go(2, true)
        }

        mBinding.psvLogisticsProgress.apply {
            setDatas(testData())
            setBindViewListener(object : PorgressStepView.BindViewListener{
                override fun onBindView(itemMsg: TextView?, itemDate: TextView?, data: Any?) {
                    var entity = data as SapmleClothesLogisticsEntity
                    itemMsg?.text = entity.msg
                    itemDate?.text = entity.date
                }

            })
        }
    }

    fun testData():List<SapmleClothesLogisticsEntity> {
        var datas = arrayListOf<SapmleClothesLogisticsEntity>()
        for (i in 0..29) {
            val data = SapmleClothesLogisticsEntity()
            if (i % 2 == 0) {
                data.msg = "[北京市] 包裹已到达,北京市朝阳区 \n 联系电话:15912345678 "
            } else {
                data.msg = "[杭州市] 包裹已派发至转运中心,转运中心已发出。"
            }
            data.date = "2016年08月03日"
            datas.add(data)
        }
        return datas
    }

}