package com.test.common.ui.dialog.goods

import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseBinderAdapter
import com.lxj.xpopup.core.BottomPopupView
import com.lxj.xpopup.util.XPopupUtils
import com.test.common.R
import com.test.common.ui.dialog.goods.item.info.ItemInfo
import com.test.common.ui.dialog.goods.item.info.ItemInfoEntity
import com.test.common.ui.dialog.goods.item.info.ItemInfoTagIds
import com.test.common.ui.dialog.goods.item.paymode.ItemPayModeTime
import com.test.common.ui.dialog.goods.item.paymode.ItemPayModeTimeEntity
import com.test.common.ui.dialog.goods.item.paytitle.ItemPayModeTitle
import com.test.common.ui.dialog.goods.item.paytitle.ItemPayModeTitleEntity
import com.test.common.ui.dialog.goods.item.sizecount.ItemGoodsSizeCount
import com.test.common.ui.dialog.goods.item.sizecount.ItemGoodsSizeCountEntity
import com.test.common.ui.item.line.ItemGrayLine
import com.test.common.ui.item.line.ItemGrayLineEntity
import com.test.common.ui.item.line.ItemTransparentLine
import com.test.common.ui.item.line.ItemTransparentLineEntity

class GoodsDetailBottomPopupView(
    var mActivity: Activity,
) : BottomPopupView(mActivity) {

    override fun getImplLayoutId(): Int = R.layout.base_dialog_goods_detail

    var mAdapter = BaseBinderAdapter()

    override fun onCreate() {
        super.onCreate()
        mAdapter.apply {
            //线
            addItemBinder(ItemGrayLineEntity::class.java, ItemGrayLine())
            addItemBinder(ItemTransparentLineEntity::class.java, ItemTransparentLine())
            //布局
            addItemBinder(ItemPayModeTitleEntity::class.java, ItemPayModeTitle())
            addItemBinder(ItemPayModeTimeEntity::class.java, ItemPayModeTime(mActivity))
            addItemBinder(ItemInfoEntity::class.java, ItemInfo(mActivity))
            addItemBinder(ItemGoodsSizeCountEntity::class.java, ItemGoodsSizeCount(mActivity))

        }
        var rv_content = findViewById<RecyclerView>(R.id.rv_content)

        rv_content.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        var listData = arrayListOf(
            ItemInfoEntity(ItemInfoTagIds.OTHER, "单价：", "¥680.00"),
            ItemGrayLineEntity(),
            ItemInfoEntity(ItemInfoTagIds.ID_GOODS_TIME, "货期：", "2020-12-11", R.drawable.base_icon_date),

            ItemPayModeTitleEntity(),
            ItemTransparentLineEntity(7F),
            ItemPayModeTimeEntity("收款", "20%", 0, "2020-12-19"),
            ItemTransparentLineEntity(8F),
            ItemPayModeTimeEntity("中期款", "50%", 0, ""),
            ItemTransparentLineEntity(8F),
            ItemPayModeTimeEntity("尾款", "30%", 0, ""),
            ItemTransparentLineEntity(8F),


            ItemInfoEntity(ItemInfoTagIds.OTHER, "货号：", "123456778899"),
            ItemGrayLineEntity(),
            ItemInfoEntity(ItemInfoTagIds.OTHER, "颜色：", "黑色"),
            ItemGrayLineEntity(),

            ItemGoodsSizeCountEntity()
        )
        mAdapter.setList(listData)
    }
    /**
     * 弹窗最高高度
     */
    override fun getMaxHeight(): Int =
        (XPopupUtils.getScreenHeight(mActivity) * 0.7).toInt()

}