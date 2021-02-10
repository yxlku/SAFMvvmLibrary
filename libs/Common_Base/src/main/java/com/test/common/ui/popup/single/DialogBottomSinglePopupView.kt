package com.test.common.ui.popup.single

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.lxj.xpopup.core.BottomPopupView
import com.lxj.xpopup.util.XPopupUtils
import com.safmvvm.ui.titlebar.OnTitleBarListener
import com.safmvvm.ui.titlebar.TitleBar
import com.test.common.R
import com.test.common.ui.popup.base.BaseSingleChoiceEntity

/**
 * 通用底部单选弹窗
 */
class DialogBottomSinglePopupView(
    var mActivit: Activity,
    var mTitle: String = "",
    var mData: List<BaseSingleChoiceEntity> = arrayListOf(),
    /** 选中的实体类，传入后可以得到默认选中条目*/
    var selectedBaseSingleChoiceEntity: BaseSingleChoiceEntity? = BaseSingleChoiceEntity(),
    /** 优先级大于 传入实体类*/
    var selectedPosition: Int = -1,
    @LayoutRes var listLayoutId: Int = R.layout.base_popup_list,
    @LayoutRes var itemLayoutId: Int = R.layout.base_item_dialog_common_single,
    var mHeightMultiple: Float = 0.7F,
    /** 选中后是否关闭弹窗*/
    var selectedIsDismiss: Boolean = true,
    var callback: (entity: BaseSingleChoiceEntity, position: Int) -> Unit,
    /** 右侧按钮点击事件*/
    var rightClick: (view: View?) -> Unit = {}
) : BottomPopupView(mActivit), View.OnClickListener {

    var mAdapter = DialogBottomSingleAdapter(itemLayoutId, selectedPosition)

    override fun getImplLayoutId(): Int = listLayoutId

    override fun onCreate() {
        super.onCreate()

        var tb_title: TitleBar = findViewById(R.id.tb_title)
        tb_title.title = mTitle
        tb_title.setOnTitleBarListener(object : OnTitleBarListener{
            override fun onLeftClick(v: View?) {
                dismiss()
            }

            override fun onTitleClick(v: View?) {

            }

            override fun onRightClick(v: View?) {
                rightClick(v)
            }
        })

        var tv_close: TextView = findViewById(R.id.tv_close)
        tv_close.setOnClickListener(this)

        //默认选中的条目
        mAdapter.selectedPosition =
            if(selectedPosition == -1 && selectedBaseSingleChoiceEntity != null) {
            mData.indexOf(selectedBaseSingleChoiceEntity)
        }else{
            selectedPosition
        }


        var rv_content: RecyclerView = findViewById(R.id.rv_content)
        rv_content.apply {
            layoutManager = LinearLayoutManager(mActivit)
            adapter = mAdapter
        }
        mAdapter.setList(mData)


        mAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                var mAdapter = adapter as DialogBottomSingleAdapter
                mAdapter.selectedPosition = position
                callback(mAdapter.getItem(position), position)
                mAdapter.notifyDataSetChanged()
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
            R.id.tv_close -> dismiss()
        }
    }


}