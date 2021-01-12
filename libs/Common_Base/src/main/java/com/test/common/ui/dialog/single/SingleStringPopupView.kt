package com.test.common.ui.dialog.single

import android.app.Activity
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.lxj.xpopup.core.BottomPopupView
import com.lxj.xpopup.util.XPopupUtils
import com.safmvvm.app.BaseApp
import com.safmvvm.bus.putValue
import com.test.common.R
import com.test.common.ui.dialog.single.adpater.SingleChoiceAdapter
import com.test.common.ui.dialog.tip.createDialogTip
import me.jessyan.autosize.utils.AutoSizeUtils

class SingleStringPopupView(
    var mActivit: Activity,
    var mTitle: String = "",
    var mData: List<BaseSingleChoiceEntity> = arrayListOf(),
    var mHeightMultiple: Float = 0.7F,
    /** 选中后是否关闭弹窗*/
    var selectedIsDismiss: Boolean = true,
    var callback: (entity: BaseSingleChoiceEntity) -> Unit
) : BottomPopupView(mActivit), View.OnClickListener {

    var mAdapter = SingleChoiceAdapter()

    override fun getImplLayoutId(): Int = R.layout.base_dialog_list

    override fun onCreate() {
        super.onCreate()

        var tv_title: TextView = findViewById(R.id.tv_title)
        tv_title.text = mTitle
        var iv_close: ImageFilterView = findViewById(R.id.iv_close)
        var tv_close: TextView = findViewById(R.id.tv_close)
        var iv_tip: ImageFilterView = findViewById(R.id.iv_tip)
        iv_close.setOnClickListener(this)
        tv_close.setOnClickListener(this)
        iv_tip.setOnClickListener(this)


        var rv_content: RecyclerView = findViewById(R.id.rv_content)
        rv_content.apply {
            layoutManager = LinearLayoutManager(mActivit)
            adapter = mAdapter
        }
        mAdapter.setList(mData)
        mAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                var mAdapter = adapter as SingleChoiceAdapter
                mAdapter.mViewModel.selectedPosition.putValue(position)
                mAdapter.notifyDataSetChanged()
                callback(mAdapter.getItem(position))
                if (selectedIsDismiss) {
                    dismiss()
                }
            }
        })
    }

    /**
     * 弹窗最高高度
     */
    override fun getMaxHeight(): Int =
        (XPopupUtils.getScreenHeight(mActivit) * mHeightMultiple).toInt()

    override fun onClick(v: View) {
        when (v.id) {
            //关闭
            R.id.tv_close, R.id.iv_close -> dismiss()
            //右上角tip按钮
            R.id.iv_tip -> {
                "选择您目前有的信息给我们，根据已有信息提供报价。".createDialogTip(mActivit, v, block = {
                    it.offsetY(AutoSizeUtils.mm2px(mActivit, -15F))
                    it.offsetX(AutoSizeUtils.mm2px(mActivit, 8F))
                }).show()
            }
        }
    }


}