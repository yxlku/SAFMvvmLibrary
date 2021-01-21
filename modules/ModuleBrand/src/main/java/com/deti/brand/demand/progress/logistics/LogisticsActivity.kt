package com.deti.brand.demand.progress.logistics

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandActivityLogisticsBinding
import com.deti.brand.demand.progress.logistics.adapter.ItemLogisticsInfo
import com.deti.brand.demand.progress.logistics.adapter.ItemLogisticsInfoEntity
import com.deti.brand.demand.progress.logistics.adapter.ItemLogisticsProgress
import com.deti.brand.demand.progress.logistics.adapter.ItemLogisticsProgressEntity
import com.safmvvm.mvvm.view.BaseActivity
import com.test.common.ui.line.ItemTransparentLine
import com.test.common.ui.line.ItemTransparentLineEntity

/**
 * 订单详情
 */
class LogisticsActivity: BaseActivity<BrandActivityLogisticsBinding, LogisticsViewModel>(
    R.layout.brand_activity_logistics,
    BR.viewModel
) {

    var mAdapter = BaseBinderAdapter()

    override fun initData() {
        super.initData()

        mAdapter.apply {
            addItemBinder(ItemTransparentLineEntity::class.java, ItemTransparentLine())
//            addItemBinder(ItemGrayLineEntity::class.java, ItemGrayLine())
            addItemBinder(ItemLogisticsProgressEntity::class.java, ItemLogisticsProgress())
            addItemBinder(ItemLogisticsInfoEntity::class.java, ItemLogisticsInfo())
        }

        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        var listData = arrayListOf(
            ItemTransparentLineEntity(this, 16F),
            ItemLogisticsProgressEntity(),
            ItemTransparentLineEntity(this, 16F),
            ItemLogisticsInfoEntity("需求单号", "X2020111314391496"),
            ItemLogisticsInfoEntity("服务", "样衣-打版+生产"),
            ItemLogisticsInfoEntity("款式", "女生-服装-上装-棉服"),
            ItemLogisticsInfoEntity("颜色", "藏青&#91300],橙色&#91100],白色&#91200]"),
            ItemLogisticsInfoEntity("预算", "¥720.00"),
            ItemLogisticsInfoEntity("货期", "2020年")
        )
        mAdapter.setList(listData)
    }
}