package com.deti.designer.style.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.designer.R
import com.deti.designer.databinding.DesignerItemStyleListBinding
import com.deti.designer.style.entity.StyleListEntity
import com.test.common.adapter.CommonListBtnsAdapter
import com.test.common.adapter.CommonListBtnsEntity

class StyleListAdapter: BaseQuickAdapter<StyleListEntity, BaseDataBindingHolder<DesignerItemStyleListBinding>>(
    R.layout.designer_item_style_list
) {
    override fun convert(
        holder: BaseDataBindingHolder<DesignerItemStyleListBinding>,
        item: StyleListEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            //版单列表
            var versionListAdapter = StyleListVersionAdapter()
            rvVersionList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = versionListAdapter
            }
            versionListAdapter.setList(item.styleVersionDataList)

            //按钮列表
            var btnsAdapter = CommonListBtnsAdapter()
            rvBtns.apply {
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
                adapter = btnsAdapter
            }
            btnsAdapter.setList(controlBtns())
            executePendingBindings()
        }
    }

    companion object{
        /** 按钮：添加版单*/
        const val TYPE_BTN_VERSION_ADD = "type_btn_version_add"
        /** 按钮：编辑款式*/
        const val TYPE_BTN_STYLE_EDIT = "type_btn_style_edit"
    }

    fun controlBtns(): List<CommonListBtnsEntity>{
        var datas = arrayListOf<CommonListBtnsEntity>()
        datas.add(CommonListBtnsEntity(TYPE_BTN_STYLE_EDIT, "编辑款式", R.drawable.base_btn_yellow_bg))
        datas.add(CommonListBtnsEntity(TYPE_BTN_VERSION_ADD, "添加版单"))
        return datas
    }
}