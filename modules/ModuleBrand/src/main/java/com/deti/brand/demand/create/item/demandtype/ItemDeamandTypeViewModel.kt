package com.deti.brand.demand.create.item.demandtype

import android.app.Activity
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.brand.demand.create.item.service.ItemServiceEntity
import com.lxj.xpopup.core.BasePopupView
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.LogUtil
import com.test.common.ui.dialog.multiple.BaseMultipleChoiceEntity
import com.test.common.ui.dialog.multiple.createDialogSelectedMultiple
import com.test.common.ui.dialog.single.BaseSingleChoiceEntity
import com.test.common.ui.dialog.single.createDialogSelectedSingle
import java.lang.StringBuilder

class ItemDeamandTypeViewModel(
    var mActivity: Activity?,
) {
    var mAdapter: BaseBinderAdapter? = null
    var popV: BasePopupView? = null
    var showTextSb: StringBuilder = StringBuilder("")
    fun clickChooseTypeDialog(view: View, entity: ItemDeamandTypeChooseEntity){
        mActivity?.let {
            if (popV == null) {
                popV = arrayListOf(
                    BaseMultipleChoiceEntity("图片", true),
                    BaseMultipleChoiceEntity("面料信息", false),
                    BaseMultipleChoiceEntity("样衣", false),
                    BaseMultipleChoiceEntity("设计稿", false),
                    BaseMultipleChoiceEntity("制版文件", false),
                    BaseMultipleChoiceEntity("企划", false),
                ).createDialogSelectedMultiple(it, "请选择服务类型", callback = { buttonView: CompoundButton?, isChecked: Boolean, checkEntity: BaseMultipleChoiceEntity ->
//                    if (checkEntity.text == "图片") {
//                        ToastUtil.showShortToast("图片为必选项")
//                        buttonView?.isChecked = true
//                        return@createDialogSelectedMultiple null
//                    }
                    if (checkEntity.isS) {
                        if (!entity.types.contains(checkEntity.text)) {
                            entity.types.add(checkEntity.text)
                        }
                    }else{
                        if (entity.types.contains(checkEntity.text)) {
                            entity.types.remove(checkEntity.text)
                        }
                    }
                    showTextSb = StringBuilder()
                    entity.types.forEach {
                        showTextSb.append(it).append(" / ")
                    }
                    LogUtil.d("sb：${entity.types.size}")
                    entity.showText = showTextSb.toString()
                    mAdapter?.notifyDataSetChanged()
                })
            }
            popV!!.show()
        }
    }
}