package com.deti.brand.demand.progress.logistics

import android.app.Activity
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.brand.R
import com.deti.brand.BR
import com.deti.brand.databinding.BrandActivityLogisticsBinding
import com.deti.brand.demand.progress.logistics.adapter.logistics.ItemLogisticsInfo
import com.deti.brand.demand.progress.logistics.adapter.logistics.ItemLogisticsInfoEntity
import com.deti.brand.demand.progress.logistics.adapter.logistics.ItemLogisticsProgress
import com.deti.brand.demand.progress.logistics.adapter.logistics.ItemLogisticsProgressEntity
import com.safmvvm.mvvm.view.BaseActivity
import com.test.common.ui.item.line.ItemGrayLine
import com.test.common.ui.item.line.ItemGrayLineEntity
import com.test.common.ui.item.line.ItemTransparentLine
import com.test.common.ui.item.line.ItemTransparentLineEntity

/**
 * 查看进度-订单详情
 */
class LogisticsActivity: BaseActivity<BrandActivityLogisticsBinding, LogisticsViewModel>(
    R.layout.brand_activity_logistics,
    BR.viewModel
) {

    companion object{
        fun startAction(context: Activity?){
            context?.apply {
                var intent = Intent(this, LogisticsActivity::class.java)
                this.startActivity(intent)
            }
        }
    }

    var mAdapter = BaseBinderAdapter()

    override fun initData() {
        super.initData()

        mAdapter.apply {
            addItemBinder(ItemTransparentLineEntity::class.java, ItemTransparentLine())
            addItemBinder(ItemGrayLineEntity::class.java, ItemGrayLine())
            addItemBinder(ItemLogisticsProgressEntity::class.java, ItemLogisticsProgress())
            addItemBinder(ItemLogisticsInfoEntity::class.java, ItemLogisticsInfo())
        }

        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        var listData = arrayListOf(
            ItemTransparentLineEntity(16F),
            ItemLogisticsProgressEntity(),
            ItemTransparentLineEntity(16F),
            ItemLogisticsInfoEntity("需求单号", "X2020111314391496"),
            ItemGrayLineEntity(),
            ItemLogisticsInfoEntity("服务", "样衣-打版+生产"),
            ItemGrayLineEntity(),
            ItemLogisticsInfoEntity("款式", "女生-服装-上装-棉服"),
            ItemGrayLineEntity(),
            ItemLogisticsInfoEntity("颜色", "藏青&#91300],橙色&#91100],白色&#91200]"),
            ItemGrayLineEntity(),
            ItemLogisticsInfoEntity("预算", "¥720.00"),
            ItemGrayLineEntity(),
            ItemLogisticsInfoEntity("货期", "2020年")
        )
        mAdapter.setList(listData)
    }
}