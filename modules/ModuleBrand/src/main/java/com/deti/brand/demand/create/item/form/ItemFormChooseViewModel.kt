package com.deti.brand.demand.create.item.form

import android.app.Activity
import android.view.View
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.brand.demand.create.item.service.ItemServiceEntity
import com.test.common.ui.dialog.color.createDialogColors
import com.test.common.ui.dialog.single.BaseSingleChoiceEntity
import com.test.common.ui.dialog.single.createDialogSelectedSingle
import com.test.common.ui.dialog.sizecount.adapter.entity.FirstNodeEntity
import com.test.common.ui.dialog.sizecount.adapter.entity.SecondNodeEntity
import com.test.common.ui.dialog.sizecount.createDialogSizeCount
import com.test.common.ui.dialog.time.createDialogDate
import com.test.common.ui.dialog.types.createDialogTypes
import java.lang.StringBuilder

class ItemFormChooseViewModel(
    var mActivity: Activity?,
    var mAdapter: BaseBinderAdapter,
) {
    fun clickItem(view: View, entity: ItemFormChooseEntity){
        when (entity.title) {
            "尺码类型" -> clickChooseSizeTypeDialog(view, entity)
            "设置交期" -> clickChooseDateDialog(view, entity)
            "款式分类" -> clickChooseTypesDialog(view, entity)
            "颜色选择" -> clickChooseColorsDialog(view, entity)
            "尺码数量" -> clickChooseSizeCountDialog(view, entity)
            else -> {
            }
        }
    }

    /**
     * 选择尺码类型
     */
    fun clickChooseSizeTypeDialog(view: View, entity: ItemFormChooseEntity){
        mActivity?.let {
            arrayListOf(
                BaseSingleChoiceEntity("数字码"),
                BaseSingleChoiceEntity("字母码")
            ).createDialogSelectedSingle(it, "请选择尺码类型", callback = {
                entity.contentText = it.text
                mAdapter.notifyDataSetChanged()
            }).show()
        }
    }

    /**
     * 选择时间
     */
    fun clickChooseDateDialog(view: View, entity: ItemFormChooseEntity){
        mActivity?.let {
            createDialogDate(it,"请选择时间"){millisecond: Long, time: String ->
                entity.contentText = time
                mAdapter.notifyDataSetChanged()
            }.show()
        }
    }
    /**
     * 选择款式分类
     */
    fun clickChooseTypesDialog(view: View, entity: ItemFormChooseEntity){
        mActivity?.let {
            createDialogTypes(it,"选择款式分类").show()
        }
    }
    /**
     * 选择颜色
     */
    fun clickChooseColorsDialog(view: View, entity: ItemFormChooseEntity){
        mActivity?.let {
            createDialogColors(it,"选择颜色").show()
        }
    }
    /**
     * 选择尺码数量
     */
    fun clickChooseSizeCountDialog(view: View, entity: ItemFormChooseEntity){
        mActivity?.let {
            createDialogSizeCount(it, "选择尺码和设置数量"){
                var sb = StringBuilder()
                it.forEach {
                    it.childNode?.forEach {
                        var secondEntity = it as SecondNodeEntity
                        if(secondEntity.count > 0) {
                            sb.append("【")
                                .append(secondEntity.color)
                                .append(": ")
                                .append(secondEntity.size)
                                .append(": ")
                                .append(secondEntity.count)
                                .append("】 ")
                        }
                    }

                }
                entity.contentText = sb.toString()
                mAdapter.notifyDataSetChanged()
            }.show()
        }
    }
}