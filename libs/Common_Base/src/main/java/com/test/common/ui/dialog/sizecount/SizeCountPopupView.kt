package com.test.common.ui.dialog.sizecount

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.entity.node.BaseNode
import com.lxj.xpopup.core.BottomPopupView
import com.lxj.xpopup.util.XPopupUtils
import com.safmvvm.ui.titlebar.OnTitleBarListener
import com.safmvvm.ui.titlebar.TitleBar
import com.test.common.R
import com.test.common.ui.dialog.sizecount.adapter.SizeCountAdapter
import com.test.common.ui.dialog.sizecount.adapter.entity.FirstNodeEntity
import com.test.common.ui.dialog.sizecount.adapter.entity.SecondNodeEntity

class SizeCountPopupView(
    var mActivit: Activity,
    var mTitle: String = "",
    var datas: List<FirstNodeEntity> = arrayListOf(),
    var mHeightMultiple: Float = 0.7F,
    var block: (nodes: List<BaseNode>)->Unit = {}
) : BottomPopupView(mActivit) {
    var mAdapter = SizeCountAdapter(R.layout.base_dialog_item_sizecount_first)

    override fun getImplLayoutId(): Int = R.layout.base_dialog_size_count

    override fun onCreate() {
        super.onCreate()
        var tb_title: TitleBar = findViewById(R.id.tb_title)
        var rv_content: RecyclerView = findViewById(R.id.rv_content)

        tb_title.title = mTitle
        tb_title.setOnTitleBarListener(object : OnTitleBarListener{
            override fun onLeftClick(v: View?) {
            }

            override fun onTitleClick(v: View?) {
            }

            override fun onRightClick(v: View?) {
                block(mAdapter.data)
                dismiss()
            }

        })

        rv_content.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        mAdapter.setList(datas)

    }
    /**
     * 弹窗最高高度
     */
    override fun getMaxHeight(): Int =
        (XPopupUtils.getScreenHeight(mActivit) * mHeightMultiple).toInt()

    override fun getMinimumHeight(): Int =
        (XPopupUtils.getScreenHeight(mActivit) * mHeightMultiple).toInt()
}