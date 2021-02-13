package com.test.common.ui.popup.custom.sizecount

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.core.BottomPopupView
import com.lxj.xpopup.util.KeyboardUtils
import com.lxj.xpopup.util.XPopupUtils
import com.safmvvm.ui.titlebar.OnTitleBarListener
import com.safmvvm.ui.titlebar.TitleBar
import com.safmvvm.ui.toast.ToastUtil
import com.test.common.R
import com.test.common.entity.CommonColorEntity
import com.test.common.entity.CommonSizeCountEntity
import com.test.common.ui.popup.custom.sizecount.adapter.SizeCountAdapter
import com.test.common.ui.popup.custom.sizecount.adapter.entity.FirstNodeEntity
import com.test.common.ui.popup.custom.sizecount.adapter.entity.SecondNodeEntity

class SizeCountPopupView(
    var mActivit: Activity,
    var mTitle: String = "",
    var datas: List<FirstNodeEntity> = arrayListOf(),
    /** 选中的数据*/
    var mColorSizeCountDatas: ArrayList<CommonColorEntity> = arrayListOf(),
    var mHeightMultiple: Float = 0.7F,
    var block: (adapter: SizeCountAdapter, resultData: ArrayList<CommonColorEntity>, resultText: String, popupView: BasePopupView) -> Unit = { adapter: SizeCountAdapter, resultData: ArrayList<CommonColorEntity>, resultText: String, popupView: BasePopupView -> },
) : BottomPopupView(mActivit) {
    var mAdapter = SizeCountAdapter(mActivit, R.layout.base_dialog_item_sizecount_first)

    override fun getImplLayoutId(): Int = R.layout.base_dialog_size_count

    override fun dismiss() {
        super.dismiss()
        //结果关闭弹窗后，键盘不关闭问题
        KeyboardUtils.hideSoftInput(this)
    }

    override fun onCreate() {
        super.onCreate()
        var tb_title: TitleBar = findViewById(R.id.tb_title)
        var rv_content: RecyclerView = findViewById(R.id.rv_content)

        //默认选中初始化
        datas.forEach {firstEntity ->
            //列表中的颜色
            var firstColorCode = firstEntity.colorCode
            mColorSizeCountDatas.forEach {selectColorEntity ->
                if (selectColorEntity.colorCode == firstColorCode) {
                    //如果默认数据和数据颜色相同，查找尺寸数据，修改count
                    selectColorEntity.sizeToCountList.forEach {selectSizeCountEntity ->
                        firstEntity.childNode.forEach {secondEntity ->
                            var secEntity = secondEntity as SecondNodeEntity
                            if (selectSizeCountEntity.size == secEntity.size && selectSizeCountEntity.count > 0) {
                                secEntity.count = selectSizeCountEntity.count
                            }
                        }
                    }
                }
            }
        }


        tb_title.title = mTitle
        tb_title.setOnTitleBarListener(object : OnTitleBarListener {
            override fun onLeftClick(v: View?) {
            }

            override fun onTitleClick(v: View?) {
            }

            override fun onRightClick(v: View?) {
                //尺码对应数量
                //结果文字
                var resultText = StringBuilder()
                //结果数据
                var resultData: ArrayList<CommonColorEntity> = arrayListOf()
                //处理数据
                datas.forEach {
                    var sizeCountList = arrayListOf<CommonSizeCountEntity>()
                    //1、点击的时候颜色数量不能为空
                    if (it is FirstNodeEntity && it.count <= 0) {
                        ToastUtil.showShortToast("${it.color} 未选择数量")
                        return
                    }
                    //2、所有颜色都不为空的时候，拼接所有已选择的数据，展示到布局上
                    it.childNode.forEach {
                        var secondEntity = it as SecondNodeEntity
                        if (secondEntity.count > 0) {
                            //设置后的数据
                            sizeCountList.add(
                                CommonSizeCountEntity(secondEntity.size, secondEntity.count)
                            )
                            //拼接显示内容
                            resultText.append("【")
                                .append(secondEntity.color)
                                .append(": ")
                                .append(secondEntity.size)
                                .append(": ")
                                .append(secondEntity.count)
                                .append("】 ")
                        }
                    }
                    //3、拼接结果数据
                    resultData.add(CommonColorEntity(
                        "", //这里不用传sizeId
                        it.color,
                        it.colorCode,
                        sizeCountList
                    ))
                }

                block(mAdapter, resultData, resultText.toString(), this@SizeCountPopupView)
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