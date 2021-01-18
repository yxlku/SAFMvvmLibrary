package com.test.common.ui.popup.single

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.lxj.xpopup.core.AttachPopupView
import com.test.common.R
import com.test.common.ui.popup.base.BaseDialogSingleEntity
import com.xujiaji.happybubble.BubbleLayout

/**
 * 气泡弹窗view
 */
class DialogBubbleSinglePopupView(
    var mActivity: Activity,
    /** 挂在的View*/
    var atView: View,
    /** 单选模式*/
    var selectedMode: Int = MODE_IMG,
    /** 默认选中位置*/
    var selectedPosition: Int = -1,
    /** 单击是否关闭弹窗*/
    var clickDismiss: Boolean = true,
    /** 数据*/
    var datas: List<BaseDialogSingleEntity>,
    /** 点击事件回调*/
    var block: (view: View, position: Int, entity: BaseDialogSingleEntity)->Unit = {view: View, position: Int, entity: BaseDialogSingleEntity->}
): AttachPopupView(mActivity) {
    companion object{
        /** 不显示选中效果*/
        val MODE_NONE: Int = -1
        /** 选中显示图片模式*/
        val MODE_IMG: Int = 0
        /* 选中显示背景*/
        val MODE_BG: Int = 1
        /** 选中后显示所有显示状态*/
        val MODE_ALL: Int = 2

    }

    init{

        addInnerContent()
    }

    override fun getImplLayoutId(): Int = R.layout.base_dialog_single_bubble

    var mAdapter = DialogBubbleSingleAdapter(selectedPosition, selectedMode)


    override fun onCreate() {
        super.onCreate()
        //气泡背景
        var bl_bg: BubbleLayout = findViewById(R.id.bl_bg)
        //列表
        var rv_content: RecyclerView = findViewById(R.id.rv_content)

        bl_bg.look = if (isShowUpToTarget) {
            BubbleLayout.Look.BOTTOM
        }else{
            BubbleLayout.Look.TOP
        }

        bl_bg.lookPosition = (atView.right - atView.left) / 2

        rv_content.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        //单选点击事件
        mAdapter.setOnItemClickListener { adapter, view, position ->
            mAdapter.selectedPosition = position
            block(view, position, mAdapter.getItem(position))
            if (clickDismiss) {
                dismiss()
            }
            mAdapter.notifyDataSetChanged()
        }
        mAdapter.setList(datas)

    }
}