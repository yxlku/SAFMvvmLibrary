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
import com.test.common.R
import com.test.common.ui.dialog.sizecount.adapter.SizeCountAdapter
import com.test.common.ui.dialog.sizecount.adapter.entity.FirstNodeEntity
import com.test.common.ui.dialog.sizecount.adapter.entity.SecondNodeEntity

class SizeCountPopupView(
    var mActivit: Activity,
    var mTitle: String = "",
    var mHeightMultiple: Float = 0.7F,
    var block: (nodes: List<BaseNode>)->Unit = {}
) : BottomPopupView(mActivit), View.OnClickListener {
    var mAdapter = SizeCountAdapter(R.layout.base_dialog_item_sizecount_first)

    override fun getImplLayoutId(): Int = R.layout.base_dialog_size_count

    override fun onCreate() {
        super.onCreate()
        var tv_title: TextView = findViewById(R.id.tv_title)
        var tv_sure: TextView = findViewById(R.id.tv_sure)
        var iv_close: ImageFilterView = findViewById(R.id.iv_close)
        var rv_content: RecyclerView = findViewById(R.id.rv_content)

        tv_title.text = mTitle
        iv_close.setOnClickListener(this)
        tv_sure.setOnClickListener(this)


        rv_content.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        //模拟数据
        mAdapter.setList(testData())

    }

    override fun onClick(v: View) {
        when (v.id) {
            //关闭
            R.id.iv_close -> dismiss()
            //确定
            R.id.tv_sure -> {
                block(mAdapter.data)
                dismiss()
            }
        }
    }

    fun testData(): List<FirstNodeEntity> {
        var firstNode = arrayListOf<FirstNodeEntity>()
        for (i in 0 until 3) {
            var secondNodes = arrayListOf<BaseNode>()
            for (j in 0 until 5) {
                var secondNodeEntity = SecondNodeEntity(
                    j,
                    "${j}xs",
                    0,
                    "屎黄$i"
                )
                secondNodes.add(secondNodeEntity)
            }
            firstNode.add(FirstNodeEntity(i, "屎黄$i", 0, secondNodes))
        }
        return firstNode
    }

    /**
     * 弹窗最高高度
     */
    override fun getMaxHeight(): Int =
        (XPopupUtils.getScreenHeight(mActivit) * mHeightMultiple).toInt()

    override fun getMinimumHeight(): Int =
        (XPopupUtils.getScreenHeight(mActivit) * mHeightMultiple).toInt()
}