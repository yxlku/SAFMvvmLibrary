package com.deti.brand.demand.create.item.demandtype

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickDataBindingItemBinder
import com.deti.brand.databinding.BrandItemDemandTypeChooseBinding
import com.deti.brand.demand.create.CreateDemandViewModel
import com.deti.brand.demand.create.item.IItemIsShow
import com.lxj.xpopup.core.BasePopupView
import com.test.common.ui.popup.base.BaseSingleChoiceEntity
import com.test.common.ui.popup.custom.tip.createDialogTitleTipBottom
import com.test.common.ui.popup.dialogBottomSingle
import com.test.common.ui.popup.multiple.BaseMultipleChoiceEntity
import com.test.common.ui.popup.multiple.adapter.MultipleChoiceAdapter
import com.test.common.ui.popup.multiple.createDialogSelectedMultiple
import java.util.ArrayList

/**
 * 服务类型选择
 */
class ItemDeamndTypeChoose(
    var activity: Activity?,
    var mViewModel: CreateDemandViewModel
): QuickDataBindingItemBinder<ItemDeamandTypeChooseEntity, BrandItemDemandTypeChooseBinding>() {
    override fun onCreateDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BrandItemDemandTypeChooseBinding  = BrandItemDemandTypeChooseBinding.inflate(layoutInflater, parent, false)


    /** item - 图片*/
    val ITEM_TYPE_PICTURE = "item_type_picture"
    /** item - 面料信息*/
    val ITEM_TYPE_FABRIC = "item_type_fabric"
    /** item - 样衣*/
    val ITEM_TYPE_SAMPLE = "item_type_sample"
    /** item - 设计稿*/
    val ITEM_TYPE_LAYOUT = "item_type_layout"
    /** item - 制版文件*/
    val ITEM_TYPE_PRODUCTION_STANDARD= "item_type_production_standard"
    /** item显示类型*/
    var mItemTypeChooseDatas = arrayListOf(
        BaseMultipleChoiceEntity(ITEM_TYPE_PICTURE,"图片", true),
        BaseMultipleChoiceEntity(ITEM_TYPE_FABRIC, "面料信息", false),
        BaseMultipleChoiceEntity(ITEM_TYPE_SAMPLE, "样衣", false),
        BaseMultipleChoiceEntity(ITEM_TYPE_LAYOUT, "设计稿", false),
        BaseMultipleChoiceEntity(ITEM_TYPE_PRODUCTION_STANDARD, "制版文件", false),
    )

    override fun convert(
        holder: BinderDataBindingHolder<BrandItemDemandTypeChooseBinding>,
        data: ItemDeamandTypeChooseEntity,
    ) {
        holder.dataBinding?.apply {
            entity = data
            viewModel = mViewModel
            holder.itemView.setOnClickListener {
                //选择类型
                showChooseType(data)
            }
            executePendingBindings()
        }
    }

    /**
     * 选择类型 - 显示
     */
    fun showChooseType(entity: ItemDeamandTypeChooseEntity){
        activity?.apply {
            var mPopupChooseType = mItemTypeChooseDatas.createDialogSelectedMultiple(
                this, "请选择服务类型",
                isShowTip = true,
                tipBlock = { basePopupView: BasePopupView, view: View ->
                    "选择您目前有的信息给我们，根据已有信息提供报价。".createDialogTitleTipBottom(this, view).show()
                },
                sureBlock = { basePopupView: BasePopupView, selectedData: ArrayList<BaseMultipleChoiceEntity>, unSelectedData: ArrayList<BaseMultipleChoiceEntity>, adapter: MultipleChoiceAdapter ->
                    //选中后
                    //1、清空所有类型布局
                    chooseTypesClear()
                    //2、显示选中类型布局
                    chooseTypesShow(selectedData)
                    //3、赋值到实体中
                    entity.mChooseTypes = selectedData
                    //4、显示选中类型的文字
                    var showTextSb = StringBuilder()
                    selectedData.forEach {
                        showTextSb.append(it.text).append(" / ")
                    }
                    //类型布局显示的文字
                    entity.showText.set(showTextSb.toString())
                    //5、关闭弹窗
                    basePopupView.dismiss()
                }
            )
            mPopupChooseType.show()
        }
    }

    /**
     * 显示选中布局
     */
    fun chooseTypesShow(selectedDatas: ArrayList<BaseMultipleChoiceEntity>){
        selectedDatas.forEach {
            when (it.id) {
                ITEM_TYPE_PICTURE, ITEM_TYPE_LAYOUT -> addOrRemove(mViewModel.itemEntityPic, true)//图片、设计稿
                ITEM_TYPE_FABRIC -> addOrRemove(mViewModel.itemEntityFabric, true)               //面料信息
                ITEM_TYPE_SAMPLE -> addOrRemove(mViewModel.itemEntitySamplelothes, true)         //样衣
                ITEM_TYPE_PRODUCTION_STANDARD -> addOrRemove(mViewModel.itemEntityPlate, true)   //制版文件
            }
        }
    }

    /**
     * 清空所有类型样式
     */
    fun chooseTypesClear(){
        addOrRemove(mViewModel.itemEntityPic, false)           //图片
        addOrRemove(mViewModel.itemEntityFabric, false)        //面料信息
        addOrRemove(mViewModel.itemEntitySamplelothes, false)  //样衣
        addOrRemove(mViewModel.itemEntityPlate, false)         //制版文件
    }
    /**
     * 添加或删除item
     */
    fun addOrRemove(item: IItemIsShow, isShow: Boolean){
        item.isShow = isShow
        adapter.notifyDataSetChanged()
    }
}