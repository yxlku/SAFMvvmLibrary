package com.test.common.ui.popup.single

import android.app.Activity
import android.view.View
import android.view.animation.Animation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lxj.xpopup.animator.PopupAnimator
import com.lxj.xpopup.core.AttachPopupView
import com.lxj.xpopup.core.BasePopupView
import com.safmvvm.ui.decoraction.CustomDecoration
import com.safmvvm.ui.decoraction.MarginDecoration
import com.test.common.R
import com.test.common.ui.popup.base.BaseDialogSingleEntity
import com.xujiaji.happybubble.BubbleLayout
import me.jessyan.autosize.utils.AutoSizeUtils

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
    /** 布局宽度*/
    var layoutWidth: Float = -1.0F,
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

    var bl_bg: BubbleLayout? = null
    override fun onCreate() {
        super.onCreate()
        //气泡背景
        bl_bg = findViewById(R.id.bl_bg)
        //列表
        var rv_content: RecyclerView = findViewById(R.id.rv_content)

        bl_bg?.apply {
            lookPosition = (atView.right - atView.left) / 2
            if (layoutWidth != -1.0F) {
                layoutParams = layoutParams.apply {
                    width = AutoSizeUtils.mm2px(context, layoutWidth)
                }
            }
        }

        rv_content.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(CustomDecoration(context, DividerItemDecoration.VERTICAL, false))
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

    override fun getPopupAnimator(): PopupAnimator {
        var an = super.getPopupAnimator()
        bl_bg = findViewById(R.id.bl_bg)
        popupContentView.post {
            if (isShowUpToTarget) {
                bl_bg?.look = BubbleLayout.Look.BOTTOM
            } else {
                bl_bg?.look = BubbleLayout.Look.TOP
            }
        }
        return an
    }


    override fun show(): BasePopupView {
        super.show()
        return this
    }
}