package com.test.common.ui.dialog.goods

import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseBinderAdapter
import com.lxj.xpopup.core.BottomPopupView
import com.test.common.R
import com.test.common.ui.dialog.goods.item.info.ItemInfo
import com.test.common.ui.dialog.goods.item.info.ItemInfoEntity
import com.test.common.ui.dialog.goods.item.info.ItemInfoTagIds
import com.test.common.ui.dialog.goods.item.paymode.ItemPayModeTime
import com.test.common.ui.dialog.goods.item.paymode.ItemPayModeTimeEntity
import com.test.common.ui.dialog.goods.item.paytitle.ItemPayModeTitle
import com.test.common.ui.dialog.goods.item.paytitle.ItemPayModeTitleEntity
import com.test.common.ui.line.ItemGrayLine
import com.test.common.ui.line.ItemGrayLineEntity
import com.test.common.ui.line.ItemTransparentLine
import com.test.common.ui.line.ItemTransparentLineEntity

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
            addItemBinder(ItemPayModeTimeEntity::class.java, ItemPayModeTime())
            addItemBinder(ItemInfoEntity::class.java, ItemInfo(mActivity))

        }
        var rv_content = findViewById<RecyclerView>(R.id.rv_content)

        rv_content.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        var listData = arrayListOf(
            ItemPayModeTitleEntity(),
            ItemTransparentLineEntity(context, 7F),
            ItemPayModeTimeEntity("收款", "20%", 0, "2020-12-19"),
            ItemTransparentLineEntity(context, 8F),
            ItemPayModeTimeEntity("中期款", "50%", 0, ""),
            ItemTransparentLineEntity(context, 8F),
            ItemPayModeTimeEntity("尾款", "30%", 0, ""),
            ItemTransparentLineEntity(context, 8F),

            ItemInfoEntity(ItemInfoTagIds.OTHER, "单价：", "¥680.00"),
            ItemGrayLineEntity(context),
            ItemInfoEntity(ItemInfoTagIds.ID_GOODS_TIME, "货期：", "2020-12-11", R.drawable.base_titlebar_close),
            ItemGrayLineEntity(context),
            ItemInfoEntity(ItemInfoTagIds.OTHER, "货号：", "123456778899"),
            ItemGrayLineEntity(context),
            ItemInfoEntity(ItemInfoTagIds.OTHER, "颜色：", "黑色"),
            ItemGrayLineEntity(context),
        )
        mAdapter.setList(listData)
    }


}