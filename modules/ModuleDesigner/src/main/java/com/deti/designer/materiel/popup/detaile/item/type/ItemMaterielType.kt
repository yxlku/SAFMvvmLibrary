package com.deti.designer.materiel.popup.detaile.item.type

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.designer.databinding.DesignerItemMaterielTypeDataBinding
import com.deti.designer.materiel.popup.detaile.MaterielDeatilTypeData
import com.deti.designer.materiel.popup.detaile.MaterielDeatilTypeEntity
import com.deti.designer.materiel.popup.detaile.item.choose.ItemChoose
import com.deti.designer.materiel.popup.detaile.item.choose.ItemChooseEntity
import com.lxj.xpopup.core.CenterPopupView
import com.test.common.ui.item.line.ItemGrayLine
import com.test.common.ui.item.line.ItemGrayLineEntity
import com.test.common.ui.popup.comfirm.dialogComfirmAndCancelInput

/**
 * 物料详情 - 类型 - 主料、辅料
 */
class ItemMaterielType(
    var mActivity: Activity
): QuickDataBindingItemBinder<ItemMaterielTypeEntity, DesignerItemMaterielTypeDataBinding>() {

    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): DesignerItemMaterielTypeDataBinding = DesignerItemMaterielTypeDataBinding.inflate(layoutInflater, parent, false)


    override fun convert(
        holder: BinderDataBindingHolder<DesignerItemMaterielTypeDataBinding>,
        data: ItemMaterielTypeEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = data

            var tabAdapter = TabAdapter()
            rvTab.apply {
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
                adapter = tabAdapter
            }
            tabAdapter.setList(data.typeList)

            //信息适配器
            var infoAdapter = BaseBinderAdapter()
            infoAdapter.apply {
                addItemBinder(ItemChooseEntity::class.java, ItemChoose())
                addItemBinder(ItemGrayLineEntity::class.java, ItemGrayLine())
            }
            rvContent.apply {
                layoutManager = GridLayoutManager(context, 2).apply {
                    spanSizeLookup= object : GridLayoutManager.SpanSizeLookup(){
                        override fun getSpanSize(position: Int): Int {
                            if(position <= 3 || position == 16){
                                return 2
                            }else{
                                return 1
                            }
                        }
                    }
                }
                adapter = infoAdapter
            }
            infoAdapter.setOnItemClickListener { adapter, view, position ->
                //弹窗
                var itemChooseEntity = adapter.getItem(position) as ItemChooseEntity
                dialogComfirmAndCancelInput(
                    mActivity,
                    "修改信息",
                    itemChooseEntity.title,
                    mRightBtnColor = Color.parseColor("#F3B11C"),
                    mLeftClickBlock = { view: View, pop: CenterPopupView, inputText: String ->
                        pop.dismiss()
                    },
                    mRightClickBlock = { view: View, pop: CenterPopupView, inputText: String ->
                        //确定
                        //改Item的UI信息
                        itemChooseEntity.content = inputText
                        adapter.notifyDataSetChanged()
                        //改版数据信息
                        var tabPos = tabAdapter.isSelectedPosition
                        var tabData = data.typeList[tabPos].materielTypeData
                        tabData::class.java.declaredFields.forEach {
                            if (it.name == itemChooseEntity.id) {
                                var type = it.genericType.toString()
                                if("class java.lang.String" == type){
                                    var name = it.name.substring(0, 1).toUpperCase() + it.name.substring(1);
                                    var method = tabData::class.java.getMethod("set${name}", String::class.java)
                                    method.invoke(tabData, inputText)
                                }
                            }
                        }
                        pop.dismiss()
                    }
                ).show()
            }
            switchInfoPage(tabAdapter, infoAdapter, 0)

            tabAdapter.setOnItemClickListener { adapter, view, position ->
                switchInfoPage(tabAdapter, infoAdapter, position)
            }

            tvDel.setOnClickListener {
                //tab删除
                var isDel = !tabAdapter.isShowDel
                tabAdapter.isShowDel = isDel
                if (isDel) {
                    tvDel.text = "完成"
                }else{
                    tvDel.text = "删除"
                }
                tabAdapter.notifyDataSetChanged()
                switchInfoPage(tabAdapter, infoAdapter, tabAdapter.isSelectedPosition)
            }
            executePendingBindings()
        }
    }
    var currTypeInfo: MaterielDeatilTypeEntity? = null
    fun switchInfoPage(tabAdapter: TabAdapter, infoAdapter: BaseBinderAdapter, position: Int){
        //刷新列表
        tabAdapter.isSelectedPosition = position
        tabAdapter.notifyDataSetChanged()

        //更新信息数据
        if(tabAdapter.data.size > 0) {
            var info = tabAdapter.data[position] as MaterielDeatilTypeEntity
            if (info.materielTypeData != null) {
                currTypeInfo = info
                infoAdapter.setList(typeInfo(info.materielTypeData))
            }
        }else{
            infoAdapter.setList(arrayListOf())
        }
    }


    fun typeInfo(info: MaterielDeatilTypeData): ArrayList<Any>{
        var infos = arrayListOf<Any>()
        infos.apply {
            add(ItemChooseEntity("supplierName", "供应商", info.supplierName))
            add(ItemChooseEntity("productName", "品名", info.productName))
            add(ItemChooseEntity("0", "编号", "123"))
            add(ItemGrayLineEntity(, 8F))
            add(ItemChooseEntity("0", "产地", "123"))
            add(ItemChooseEntity("0", "幅宽", "123", "cm"))
            add(ItemChooseEntity("0", "成分", "123"))
            add(ItemChooseEntity("0", "克重", "123"))
            add(ItemChooseEntity("0", "颜色", "123"))
            add(ItemChooseEntity("0", "色号", "123"))
            add(ItemChooseEntity("0", "用量", "123"))
            add(ItemChooseEntity("0", "单位", "123", "米"))
            add(ItemChooseEntity("0", "损耗", "123", "%"))
            add(ItemChooseEntity("0", "缩率", "123", "%"))
            add(ItemChooseEntity("0", "总用量", "123"))
            add(ItemChooseEntity("0", "单价", "123", "元"))
            add(ItemChooseEntity("0", "总金额", "123", "元"))
        }
        return infos
    }
}