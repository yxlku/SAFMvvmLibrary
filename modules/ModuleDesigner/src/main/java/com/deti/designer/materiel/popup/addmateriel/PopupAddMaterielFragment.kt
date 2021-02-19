package com.deti.designer.materiel.popup.addmateriel

import android.graphics.Color
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.designer.R
import com.deti.designer.BR
import com.deti.designer.databinding.DesignerPopupMaterielAddBinding
import com.deti.designer.materiel.popup.addmateriel.item.btn.ItemBtn
import com.deti.designer.materiel.popup.addmateriel.item.btn.ItemBtnEntity
import com.deti.designer.materiel.popup.addmateriel.item.choose.ItemChoose
import com.deti.designer.materiel.popup.addmateriel.item.choose.ItemChooseEntity
import com.deti.designer.materiel.popup.addmateriel.item.input.ItemInput
import com.deti.designer.materiel.popup.addmateriel.item.input.ItemInputEntity
import com.deti.designer.materiel.popup.addmateriel.item.pic.ItemPic
import com.deti.designer.materiel.popup.addmateriel.item.pic.ItemPicEntity
import com.deti.designer.materiel.popup.addmateriel.item.radio.ItemRadioType
import com.deti.designer.materiel.popup.addmateriel.item.radio.ItemRadioTypeEntity
import com.deti.designer.materiel.popup.addmateriel.item.remark.ItemRemark
import com.deti.designer.materiel.popup.addmateriel.item.remark.ItemRemarkEntity
import com.safmvvm.mvvm.view.bottom.BaseBottomFragment
import com.test.common.ui.item.line.ItemGrayLine
import com.test.common.ui.item.line.ItemGrayLineEntity

/**
 * 添加物料
 */
class PopupAddMaterielFragment : BaseBottomFragment<DesignerPopupMaterielAddBinding, PopupAddMaterielViewModel>(
    R.layout.designer_popup_materiel_add,
    BR.viewModel
){

    var mAdapter = BaseBinderAdapter()
    var listData = arrayListOf<Any>()

    override fun initData() {
        super.initData()

        mAdapter.apply {
            addItemBinder(ItemGrayLineEntity::class.java, ItemGrayLine())

            //选择item
            addItemBinder(ItemRadioTypeEntity::class.java, ItemRadioType(mViewModel))
            //选择item
            addItemBinder(ItemChooseEntity::class.java, ItemChoose(mViewModel))
            //输入Item
            addItemBinder(ItemInputEntity::class.java, ItemInput(mViewModel))

            //备注
            addItemBinder(ItemRemarkEntity::class.java, ItemRemark(mViewModel))

            //上传图片
            addItemBinder(ItemPicEntity::class.java, ItemPic(mViewModel))

            //底部按钮
            addItemBinder(ItemBtnEntity::class.java, ItemBtn(activity as AppCompatActivity, mViewModel))
        }

        mBinding.rvContent.apply {
            layoutManager = GridLayoutManager(context, 2).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
                    override fun getSpanSize(position: Int): Int {
                        return if(position in 5..12){
                            1
                        }else{
                            2
                        }
                    }

                }
            }
            adapter = mAdapter
        }

        listData.apply {
            add(ItemRadioTypeEntity())

            add(ItemChooseEntity("0", "供应商", "请选择供应商"))
            add(ItemChooseEntity("0", "品名", "请选择品名"))
            add(ItemChooseEntity("0", "编号", "请选择编号", "", false))

            add(ItemGrayLineEntity(10.0F, Color.parseColor("#F8F8F8")))
            add(ItemInputEntity("0", "成分", "请输入成分")) //4
            add(ItemInputEntity("0", "产地", "请输入产地"))
            add(ItemChooseEntity("0", "颜色", "请选择颜色"))
            add(ItemChooseEntity("0", "色值", "请输入色值"))
            add(ItemInputEntity("0", "损耗", "请输入损耗", inputType = InputType.TYPE_CLASS_NUMBER))
            add(ItemInputEntity("0", "宽幅/CM", "请输入产地"))
            add(ItemInputEntity("0", "缩率", "请输入缩率", "", false))
            add(ItemInputEntity("0", "单价/元", "请输入单价", "", false)) //11

            add(ItemGrayLineEntity(10.0F, Color.parseColor("#F8F8F8")))
            add(ItemChooseEntity("0", "版本编号", "请选择供应商"))
            add(ItemChooseEntity("0", "提供方", "请选择品名", "", false))

            //备注
            add(ItemRemarkEntity())
            //上传图片
            add(ItemPicEntity())
            //完成
            add(ItemBtnEntity())

        }

        mAdapter.setList(listData)

    }

}