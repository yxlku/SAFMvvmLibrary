package com.deti.brand.demand.progress.logistics.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemLogisticsProgressBinding
import com.deti.brand.demand.progress.generate.entity.SapmleClothesLogisticsEntity
import com.safmvvm.ext.ui.progressview.PorgressStepView

class ItemLogisticsProgress: QuickDataBindingItemBinder<ItemLogisticsProgressEntity, BrandItemLogisticsProgressBinding>(){
    override fun convert(
        holder: BinderDataBindingHolder<BrandItemLogisticsProgressBinding>,
        data: ItemLogisticsProgressEntity
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = data

            binding.psvProgress.apply {
                setDatas(testData())
                setBindViewListener(object : PorgressStepView.BindViewListener {
                    override fun onBindView(itemMsg: TextView?, itemDate: TextView?, data: Any?) {
                        var entity = data as SapmleClothesLogisticsEntity
                        itemMsg?.text = entity.msg
                        itemDate?.text = entity.date
                    }
                })
            }

            executePendingBindings()
        }
    }

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BrandItemLogisticsProgressBinding = BrandItemLogisticsProgressBinding.inflate(layoutInflater, parent, false)

    fun testData():List<SapmleClothesLogisticsEntity> {
        var datas = arrayListOf<SapmleClothesLogisticsEntity>()
        datas.add(SapmleClothesLogisticsEntity("[报价已完成/关闭]", "2020-09-11 17:20:00"))
        datas.add(SapmleClothesLogisticsEntity("[需求待确认]", "2020-09-11 17:20:00"))
        datas.add(SapmleClothesLogisticsEntity("[得体报价中]", "2020-09-11 17:20:00"))
        datas.add(SapmleClothesLogisticsEntity("[样衣已寄送得体]", "2020-09-11 17:20:00"))
        datas.add(SapmleClothesLogisticsEntity("[需求已提交]", "2020-09-11 17:20:00"))
        return datas
    }
}