package com.test.common.ui.popup.custom.sizecount.adapter

import android.view.KeyEvent
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.safmvvm.bus.LiveDataBus
import com.safmvvm.ui.toast.ToastUtil
import com.test.common.R
import com.test.common.ui.popup.custom.sizecount.adapter.entity.SecondNodeEntity

class SecondNodeProvider(
    var countUpdateBlock: () -> Unit = {}
): BaseNodeProvider(){
    override val itemViewType: Int = 2

    override val layoutId: Int = R.layout.base_dialog_item_sizecount_second

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        var data = item as SecondNodeEntity

        var tv_size = helper.getView<AppCompatTextView>(R.id.tv_size)
        var iv_subtract = helper.getView<ImageFilterView>(R.id.iv_subtract)
        var et_count = helper.getView<AppCompatEditText>(R.id.et_count)
        var iv_add = helper.getView<ImageFilterView>(R.id.iv_add)

        et_count.setText(data.count.toString())
        tv_size.text = data.size

        iv_subtract.setOnClickListener {
            if(data.count > 0) {
                data.count = data.count - 1
                et_count.setText((data.count).toString())
//                LiveDataBus.send(data.color, "")
                countUpdateBlock()
            }
        }
        iv_add.setOnClickListener {
            data.count = data.count + 1
            et_count.setText((data.count).toString())
//            LiveDataBus.send(data.color, "")
            countUpdateBlock()
        }

        et_count.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                var contentText: String = et_count.text.toString().trim()
                try {
                    data.count = contentText.toInt()
                    LiveDataBus.send(data.color, "")
                } catch (e: Exception) {
                    e.printStackTrace()
                    ToastUtil.showShortToast("只能输入数字")
                }
                return false
            }

        })
    }


}