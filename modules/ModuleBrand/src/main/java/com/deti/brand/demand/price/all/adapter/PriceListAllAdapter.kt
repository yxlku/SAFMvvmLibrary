package com.deti.brand.demand.price.all.adapter

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.brand.R
import com.deti.brand.databinding.BrandItemPriceListAllBinding
import com.deti.brand.demand.price.all.entity.PriceListAllEntity
import com.deti.brand.demand.progress.generate.SampleClothesProgressActivity
import com.deti.brand.demand.progress.logistics.LogisticsActivity
import com.safmvvm.ui.toast.ToastUtil
import com.test.common.ui.adapter.CommonListBtnsAdapter
import com.test.common.ui.adapter.CommonListBtnsEntity

class PriceListAllAdapter(
    var mActivity: Activity?
): BaseQuickAdapter<PriceListAllEntity, BaseDataBindingHolder<BrandItemPriceListAllBinding>>(
    R.layout.brand_item_price_list_all
) {

    companion object{

        /***  -- TODO 最后改为和接口类型一样的值 */
        /**********************报价状态***********************/
        /** 待得体报价*/
        const val STATE_OFFER_WAIT_DETI = "state_offer_wait_deti"
        /** 待得体报价 - 倒计时*/
        const val STATE_OFFER_WAIT_DETI_TIME = "state_offer_wait_deti_time"
        /** 待得体报价中*/
        const val STATE_OFFER_ING_DETI = "state_offer_ing_deti"
        /** 待报价 - 二次报价*/
        const val STATE_OFFER_WAIT = "state_offer_wait"
        /** 待确认*/
        const val STATE_CONFIRMED_WAIT = "state_confirmed_wait"
        /** 已确认报价*/
        const val STATE_CONFIRMED = "state_confirmed"

        /** 需求取消*/
        const val STATE_DEMAND_CANCEL = "state_demand_cancel"
        /** 已拒绝报价*/
        const val STATE_OFFER_REFUSE = "state_offer_refuse"

        /**********************按钮状态***********************/
        /** 查看物流*/
        const val BTN_LOGISTICS = "btn_Logistics"
        /** 我要修改*/
        const val BTN_MODIFY = "btn_modify"
        /** 查看进度*/
        const val BTN_SCHEDULE = "btn_schedule"

        /** 确认报价*/
        const val BTN_OFFER_CONFIRM = "btn_Offer_confirm"
        /** 拒绝报价*/
        const val BTN_OFFER_REFUSE = "btn_Offer_Refuse"
        /** 查看报价*/
        const val BTN_OFFER_LOOK = "btn_Offer_look"

    }

    override fun convert(
        holder: BaseDataBindingHolder<BrandItemPriceListAllBinding>,
        item: PriceListAllEntity,
    ) {
        var binding = holder.dataBinding
        binding?.apply {
            entity = item
            executePendingBindings()



            //按钮适配器，通过不同状态显示按钮
            var btnAdapter = CommonListBtnsAdapter()
            rvBtns.apply {
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
                adapter = btnAdapter
            }
            btnAdapter.setOnItemClickListener { adapter, view, position ->
                //按钮点击事件
                var data = adapter.data[position] as CommonListBtnsEntity
                controlBtnsClick(data.id)
            }
            btnAdapter.setList(controlBtns(item.state))
        }
    }

    /** 控制按item底部按钮*/
    fun controlBtns(state: String): ArrayList<CommonListBtnsEntity> {
        var btns = arrayListOf<CommonListBtnsEntity>()
        when (state) {
            STATE_OFFER_WAIT_DETI -> {
                //待得体报价
                btns.add(CommonListBtnsEntity(BTN_LOGISTICS, "查看物流", R.drawable.base_btn_yellow_bg))
                btns.add(CommonListBtnsEntity(BTN_MODIFY, "我要修改"))
            }
            STATE_OFFER_WAIT_DETI_TIME -> {
                //待得体报价剩余时间
                btns.add(CommonListBtnsEntity(BTN_SCHEDULE, "查看进度"  ))
                btns.add(CommonListBtnsEntity(BTN_MODIFY, "我要修改"))
            }
            STATE_OFFER_ING_DETI -> {
                //得体报价中
                btns.add(CommonListBtnsEntity(BTN_SCHEDULE, "查看进度", R.drawable.base_btn_yellow_bg))
                btns.add(CommonListBtnsEntity(BTN_LOGISTICS, "查看物流"))
            }
            STATE_CONFIRMED_WAIT -> {
                //待确认
                btns.add(CommonListBtnsEntity(BTN_OFFER_CONFIRM, "确认报价", R.drawable.base_btn_yellow_bg))
                btns.add(CommonListBtnsEntity(BTN_OFFER_REFUSE, "拒绝报价"))
            }
            STATE_CONFIRMED, STATE_DEMAND_CANCEL, STATE_OFFER_REFUSE, STATE_OFFER_WAIT  ->{
                //已确认、已取消、已拒绝、待报价（二次报价）
                btns.add(CommonListBtnsEntity(BTN_OFFER_LOOK, "查看报价"))
            }
        }
        return btns
    }

    /**
     * 控制按钮点击事件
     * @param btnState 按钮状态
     */
    fun controlBtnsClick(btnState: String){
        when (btnState) {
            BTN_LOGISTICS -> {
                //查看物流
                LogisticsActivity.startAction(mActivity)
            }
            BTN_MODIFY -> {
                //我要修改
                ToastUtil.showShortToast("我要修改")
            }
            BTN_SCHEDULE -> {
                //查看进度
                SampleClothesProgressActivity.startAction(mActivity)
            }
            BTN_OFFER_CONFIRM -> {
                //确认报价
            }
            BTN_OFFER_REFUSE -> {
                //拒绝报价
            }
            BTN_OFFER_LOOK -> {
                //查看报价
            }
        }
    }
}