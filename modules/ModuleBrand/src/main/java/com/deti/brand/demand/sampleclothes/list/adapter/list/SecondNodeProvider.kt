package com.deti.brand.demand.sampleclothes.list.adapter.list

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.deti.brand.R
import com.deti.brand.databinding.BrandItemSampleClothesListBtnBinding
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

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        var data = item as SecondNodeEntity
        var rvBtns = helper.getView<RecyclerView>(R.id.rv_btns)
        var tv_more_btn = helper.getView<TextView>(R.id.tv_more_btn)

        var mAdapter = SampleClothesBtnAdapter(mActivity)

        var btnDatas = controlBtns()

//        tv_more_btn.setOnClickListener {
//            //更多
//            if (btnDatas.size > 3) {
//
//                var listData = arrayListOf(
//                    BaseDialogSingleEntity(0, "我要修改33"),
//                    BaseDialogSingleEntity(4, "我要修改44"),
//                    BaseDialogSingleEntity(4, "我要修改55")
//                )
//                mActivity?.apply {
//                    listData.dialogBubbleSingle(
//                        this,
//                        tv_more_btn,
//                        DialogBubbleSinglePopupView.MODE_NONE,
//                        testSelected,
//                        true,
//                    ) { view: View, position: Int, entity: BaseDialogSingleEntity ->
//                        testSelected = position
//                        ToastUtil.showShortToast("选中了：${entity.text}")
//                    }.show()
//                }
//            }
//        }

        if (btnDatas.size > 3) {
            tv_more_btn.visibility = View.VISIBLE
        } else {
            tv_more_btn.visibility = View.GONE
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
     * 不同状态显示的按钮
     */
    fun controlBtns(): ArrayList<SampleClothesListAllBtnEntity>{
        var btns = arrayListOf<SampleClothesListAllBtnEntity>()
        when (mStateList) {
            StateListSimpleClothesEnum.STATE_ALL -> {
                //所有状态都要获取
            }
            StateListSimpleClothesEnum.STATE_DELIVER -> {
                //待发货 - 1、修改中；2、打版中；
                btns.apply {
                    //查看进度
                    add(generateBtn(StateBtnsSampleClothes.PROGRESS))
                    //齐色
                    add(generateBtn(StateBtnsSampleClothes.COLORS))
                }
            }
            StateListSimpleClothesEnum.STATE_RECEIVED -> {
                //待收货 - 1、寄送中
                btns.apply {
                    //查看物流
                    add(generateBtn(StateBtnsSampleClothes.LOGISTICS))
                    //确认评价
                    add(generateBtn(StateBtnsSampleClothes.EVALUATION))
                    //齐色
                    add(generateBtn(StateBtnsSampleClothes.COLORS))
                    //复版
                    add(generateBtn(StateBtnsSampleClothes.REPRINT))
                    //退回修改
                    add(generateBtn(StateBtnsSampleClothes.RETURN_UPDATA))
                }
            }
            StateListSimpleClothesEnum.STATE_EVALUATION -> {
                //待评价 - 1、待评价
                btns.apply {
                    //确认评价
                    add(generateBtn(StateBtnsSampleClothes.EVALUATION))
                    //齐色
                    add(generateBtn(StateBtnsSampleClothes.COLORS))
                    //复版
                    add(generateBtn(StateBtnsSampleClothes.REPRINT))
                }
            }
            StateListSimpleClothesEnum.STATE_PAYMENT -> {
                //待付款 - 1、待付款
                btns.apply {
                    //立即付款
                    add(generateBtn(StateBtnsSampleClothes.PAYMENT))
                    //费用详情
                    add(generateBtn(StateBtnsSampleClothes.FEE_DETAILS))
                }
            }
        }
        return btns
    }

    fun generateBtn(ss: StateBtnsSampleClothes): SampleClothesListAllBtnEntity{
        return SampleClothesListAllBtnEntity(ss.id, ss.text)
    }
}