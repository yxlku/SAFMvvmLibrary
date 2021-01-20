package com.deti.brand.demand.sampleclothes.list.adapter.list

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.deti.brand.R
import com.deti.brand.demand.sampleclothes.list.StateListSimpleClothesEnum
import com.deti.brand.demand.sampleclothes.list.adapter.btn.SampleClothesBtnAdapter
import com.deti.brand.demand.sampleclothes.list.adapter.btn.StateBtnsSampleClothes
import com.deti.brand.demand.sampleclothes.list.entity.SampleClothesListAllBtnEntity
import com.deti.brand.demand.sampleclothes.list.entity.SecondNodeEntity
import com.safmvvm.ui.toast.ToastUtil
import com.test.common.ui.popup.base.BaseDialogSingleEntity
import com.test.common.ui.popup.dialogBubbleSingle
import com.test.common.ui.popup.single.DialogBubbleSinglePopupView

class SecondNodeProvider(
    var mActivity: Activity?,
    /** 列表状态*/
    var mStateList: Int = StateListSimpleClothesEnum.STATE_ALL,
) : BaseNodeProvider() {

    override val itemViewType: Int = 2

    override val layoutId: Int = R.layout.brand_item_simple_clothes_list_second

    var testSelected = 2
    /** 按钮颜色 - 黄色背景*/
    var btnBgYellow = R.drawable.base_btn_yellow_bg
    /** 按钮颜色 - 灰色线框*/
    var btnBgGray = R.drawable.base_btn_gray_bg

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        var data = item as SecondNodeEntity
        var rvBtns = helper.getView<RecyclerView>(R.id.rv_btns)
        var tv_more_btn = helper.getView<TextView>(R.id.tv_more_btn)
        var group_cost = helper.getView<Group>(R.id.group_cost)

        controlViewIsShow(data, group_cost)

        var mAdapter = SampleClothesBtnAdapter(mActivity)
        var btnDatas = controlBtns(data)
        var moreBtnDatas = controlMoreBtns(data, tv_more_btn)
        tv_more_btn.setOnClickListener{
            mActivity?.apply {
                moreBtnDatas.dialogBubbleSingle(
                    this,
                    tv_more_btn,
                    DialogBubbleSinglePopupView.MODE_NONE,
                    testSelected,
                    true,
                ) { view: View, position: Int, entity: BaseDialogSingleEntity ->
                    testSelected = position
                    ToastUtil.showShortToast("选中了：${entity.text}")
                }.show()
            }
        }
        rvBtns.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            adapter = mAdapter
        }

        mAdapter.setList(btnDatas)
    }

    /**
     * 控制控件显示隐藏
     */
    fun controlViewIsShow(data: SecondNodeEntity, group_cost: Group){
        when (data.state) {
            1 -> {
                //寄送中
                group_cost.visibility = View.VISIBLE
            }
            else -> {
                group_cost.visibility = View.GONE
            }
        }

    }
    /**
     * 不同状态显示的按钮
     */
    fun controlBtns(data: SecondNodeEntity): ArrayList<SampleClothesListAllBtnEntity>{
        var btns = arrayListOf<SampleClothesListAllBtnEntity>()
        when (data.state) {
            0 -> {
                //待发货 - 1、修改中；2、打版中；
                btns.apply {
                    //查看进度
                    add(generateBtn(StateBtnsSampleClothes.PROGRESS, btnBgYellow))
                    //齐色
                    add(generateBtn(StateBtnsSampleClothes.COLORS))
                }
            }
            1-> {
                //待收货 - 1、寄送中
                btns.apply {
                    //查看物流
                    add(generateBtn(StateBtnsSampleClothes.LOGISTICS, btnBgYellow))
                    //确认评价
                    add(generateBtn(StateBtnsSampleClothes.EVALUATION))
                }
            }
            2 -> {
                //待评价 - 1、待评价
                btns.apply {
                    //确认评价
                    add(generateBtn(StateBtnsSampleClothes.EVALUATION, btnBgYellow))
                    //齐色
                    add(generateBtn(StateBtnsSampleClothes.COLORS))
                    //复版
                    add(generateBtn(StateBtnsSampleClothes.REPRINT))
                }
            }
            3 -> {
                //待付款 - 1、待付款
                btns.apply {
                    //立即付款
                    add(generateBtn(StateBtnsSampleClothes.PAYMENT, btnBgYellow))
                    //费用详情
                    add(generateBtn(StateBtnsSampleClothes.FEE_DETAILS))
                }
            }
        }
        return btns
    }

    /**
     * 更多按钮控制
     */
    fun controlMoreBtns(data: SecondNodeEntity, tv_more_btn: TextView): ArrayList<BaseDialogSingleEntity>{
        var moreBtns = arrayListOf<BaseDialogSingleEntity>()
        //默认更多按钮隐藏
        when (data.state) {
            1 -> {
                //待收货 - 1、寄送中
                tv_more_btn.visibility = View.VISIBLE
                moreBtns.apply {
                    //齐色
                    add(generateBtnMore(StateBtnsSampleClothes.COLORS))
                    //复版
                    add(generateBtnMore(StateBtnsSampleClothes.REPRINT))
                    //退回修改
                    add(generateBtnMore(StateBtnsSampleClothes.RETURN_UPDATA))
                }
            }
            3 -> {
                //待付款 - 1、待付款
                moreBtns.apply {
                    //齐色
                    add(generateBtnMore(StateBtnsSampleClothes.COLORS))
                    //复版
                    add(generateBtnMore(StateBtnsSampleClothes.REPRINT))
                }
            }
            else->{
                tv_more_btn.visibility = View.GONE
            }
        }
        return moreBtns
    }

    /**
     * 生成item列表
     */
    fun generateBtn(ss: StateBtnsSampleClothes, @DrawableRes bg: Int = btnBgGray): SampleClothesListAllBtnEntity{
        return SampleClothesListAllBtnEntity(ss.id, ss.text, bg)
    }

    /**
     * 生成更多弹窗中的按钮
     */
    fun generateBtnMore(ss: StateBtnsSampleClothes): BaseDialogSingleEntity{
        return BaseDialogSingleEntity(ss.id, ss.text)
    }

}