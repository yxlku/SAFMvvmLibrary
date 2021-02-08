package com.test.common.ui.popup.multiple

import android.app.Activity
import android.view.View
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.core.BottomPopupView
import com.lxj.xpopup.util.XPopupUtils
import com.safmvvm.ui.titlebar.OnTitleBarListener
import com.safmvvm.ui.titlebar.TitleBar
import com.safmvvm.utils.ResUtil
import com.test.common.R
import com.test.common.ui.popup.multiple.adapter.MultipleChoiceAdapter

class MultipleStringPoputView(
    var mActivit: Activity,
    var mTitle: String = "",
    var mData: List<BaseMultipleChoiceEntity> = arrayListOf(),
    var mHeightMultiple: Float = 0.7F,
    /** 是否显示标题栏tip*/
    var isShowTip: Boolean = false,
    /** 标题栏tip点击事件*/
    var tipBlock: (basePopupView: BasePopupView, view: View) -> Unit = {basePopupView: BasePopupView, view: View -> },
    /** item选中的时间*/
    var itemCallback: ((buttonView: CompoundButton?, isChecked: Boolean, entity: BaseMultipleChoiceEntity) -> Unit?)? = null,
    /** 确定按钮*/
    var sureBlock: (basePopupView: BasePopupView, selectedData: ArrayList<BaseMultipleChoiceEntity>, unSelectedData: ArrayList<BaseMultipleChoiceEntity>, adapter: MultipleChoiceAdapter) -> Unit = {basePopupView: BasePopupView, selectedData: ArrayList<BaseMultipleChoiceEntity>, unSelectedData: ArrayList<BaseMultipleChoiceEntity>, adapter: MultipleChoiceAdapter ->}
): BottomPopupView(mActivit), View.OnClickListener {

    var mAdapter = MultipleChoiceAdapter(itemCallback)

    override fun getImplLayoutId(): Int = R.layout.base_dialog_list

    override fun onCreate() {
        super.onCreate()
        var tv_sure: TextView = findViewById(R.id.tv_sure)
        tv_sure.setOnClickListener(this)

        var tb_title: TitleBar = findViewById(R.id.tb_title)
        tb_title.title = mTitle
        if (isShowTip) {
            tb_title.rightIcon = ResUtil.getDrawable(R.drawable.base_dialog_tip)
        }
        tb_title.setOnTitleBarListener(object : OnTitleBarListener{
            override fun onLeftClick(v: View?) {
                dismiss()
            }

            override fun onTitleClick(v: View?) {

            }

            override fun onRightClick(v: View?) {
                v?.apply {
                    tipBlock(this@MultipleStringPoputView, tb_title.rightView)
                }
            }
        })

        var rv_content: RecyclerView = findViewById(R.id.rv_content)
        rv_content.apply {
            layoutManager = LinearLayoutManager(mActivit)
            adapter = mAdapter
        }
        mAdapter.setList(mData)
    }


    /**
     * 弹窗最高高度
     */
    override fun getMaxHeight(): Int =
        (XPopupUtils.getScreenHeight(mActivit) * mHeightMultiple).toInt()

    override fun onClick(v: View) {
        when (v.id) {
            //底部确定
            R.id.tv_sure -> {
                var data = mAdapter.data
                var selectedData = ArrayList<BaseMultipleChoiceEntity>()
                var unSelectedData = ArrayList<BaseMultipleChoiceEntity>()
                data.forEach {
                    if (it.isSelected) {
                        selectedData.add(it)
                    }else{
                        unSelectedData.add(it)
                    }
                }
                //确定回调
                sureBlock(this, selectedData, unSelectedData, mAdapter)
            }
        }
    }

}