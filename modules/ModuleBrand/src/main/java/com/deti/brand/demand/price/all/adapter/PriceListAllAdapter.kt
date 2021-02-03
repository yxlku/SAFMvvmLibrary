package com.deti.brand.demand.price.all.adapter

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.brand.R
import com.deti.brand.databinding.BrandItemPriceListAllBinding
import com.deti.brand.demand.price.all.entity.PriceListAllEntity
import com.deti.brand.demand.progress.generate.SampleClothesProgressActivity
import com.deti.brand.demand.progress.logistics.LogisticsActivity
import com.safmvvm.ui.toast.ToastUtil
import com.test.common.ui.adapter.CommonListBtnsAdapter
import com.test.common.ui.adapter.CommonListBtnsEntity
import com.test.common.ui.item.listinfo.ItemListInfo
import com.test.common.ui.item.listinfo.ItemListInfoEntity

class PriceListAllAdapter(
    var mActivity: Activity?
): BaseQuickAdapter<PriceListAllEntity, BaseDataBindingHolder<BrandItemPriceListAllBinding>>(
    R.layout.brand_item_price_list_all
) {

    companion object{

        /**********************报价状态***********************/
        /** //1待报价 2待报价(报价中) - 此类型不用 3待确认 4待报价(无样衣时显示) 5已关闭 10已确认 12待报价(二次报价)*/
        /** 待报价 - 需要判断是否已签收，然后显示对应按钮*/
        const val STATE_OFFER_WAIT = "1"
        /** 待报价 - 无样衣*/
        const val STATE_OFFER_WAIT_NO_SAMPLE = "4"
        /** 待报价 - 二次报价*/
        const val STATE_OFFER_WAIT_SECOND = "12"
        /** 待确认*/
        const val STATE_CONFIRMED_WAIT = "3"
        /** 已确认报价*/
        const val STATE_CONFIRMED = "10"
        /** 需求已关闭*/
        const val STATE_DEMAND_CLOSE = "5"

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
            //控制Item的UI差异
            controlUI(holder, item)
            //信息列表
            initInfoList(this, holder, item)
            //按钮适配器，通过不同状态显示按钮
            initBtns(this, holder, item)
            executePendingBindings()
        }
    }

    /**
     * 初始化按钮
     */
    private fun initBtns(
        binding: BrandItemPriceListAllBinding,
        holder: BaseDataBindingHolder<BrandItemPriceListAllBinding>,
        item: PriceListAllEntity,
    ){
        var btnAdapter = CommonListBtnsAdapter()
        binding.rvBtns.apply {
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
        btnAdapter.setList(controlBtns(item))
    }

    /**
     * 初始化 信息列表
     */
    private fun initInfoList(
        binding: BrandItemPriceListAllBinding,
        holder: BaseDataBindingHolder<BrandItemPriceListAllBinding>,
        item: PriceListAllEntity,
    ) {
        var infoAdapter = BaseBinderAdapter()
        infoAdapter.apply {
            addItemBinder(ItemListInfoEntity::class.java, ItemListInfo())
        }
        binding.rvInfo.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = infoAdapter
        }
        infoAdapter.setList(controlInfos(item))
    }

    /**
     * 控制信息数据
     */
    fun controlInfos(
        item: PriceListAllEntity
    ): ArrayList<Any>{
        var infos = arrayListOf<Any>()
        infos.apply {
            add(ItemListInfoEntity("0", "服务：", item.service))
            add(ItemListInfoEntity("1", "款式：", item.classifyName))
            add(ItemListInfoEntity("2", "颜色：", item.colorStr))
            add(ItemListInfoEntity("3", "货期：", item.deliveryTime))
            add(ItemListInfoEntity("4", "预算：", item.price))

            if (item.status == STATE_CONFIRMED) {
                //已确认报价
                add(ItemListInfoEntity("5", "报价：", item.quotePrice))
            }
        }
        return infos
    }

    /**
     * 控制不同状态下的UI差异
     */
    private fun controlUI(
        holder: BaseDataBindingHolder<BrandItemPriceListAllBinding>,
        item: PriceListAllEntity,
    ) {
        holder.dataBinding?.apply {
            rlQuotePrice.visibility = View.GONE
            when (item.status) {
                STATE_OFFER_WAIT -> {
                    //待报价 -- 需要判断是否已签收，然后显示对应按钮
                }
                STATE_OFFER_WAIT_NO_SAMPLE -> {
                    //待得体报价 - 无样衣
                }
                STATE_OFFER_WAIT_SECOND -> {
                    //待报价 - 二次报价
                }
                STATE_CONFIRMED_WAIT -> {
                    //待确认
                    //1、显示红色气泡提示：报价
                    rlQuotePrice.visibility = View.VISIBLE
                }
                STATE_CONFIRMED -> {
                    //已确认报价
                }
                STATE_DEMAND_CLOSE -> {
                    //需求取消
                }
            }
        }

    }
    /** 控制按item底部按钮*/
    fun controlBtns(entity: PriceListAllEntity): ArrayList<CommonListBtnsEntity> {
        var btns = arrayListOf<CommonListBtnsEntity>()
        when (entity.status) {
            STATE_OFFER_WAIT -> {
                //待得体报价 - 两种情况：1、样衣未签收 receiveFlag == 20，2、样衣已签收 receiveFlag == 30
                if (entity.receiveFlag == "20") {
                    //样衣未签收
                    btns.add(CommonListBtnsEntity(BTN_LOGISTICS, "查看物流", R.drawable.base_btn_yellow_bg))
                    btns.add(CommonListBtnsEntity(BTN_MODIFY, "我要修改"))
                } else if(entity.receiveFlag == "30"){
                    //样衣已签收
                    btns.add(CommonListBtnsEntity(BTN_SCHEDULE, "查看进度", R.drawable.base_btn_yellow_bg))
                    btns.add(CommonListBtnsEntity(BTN_LOGISTICS, "查看物流"))
                }
            }
            STATE_OFFER_WAIT_NO_SAMPLE -> {
                //待得体报价 - 无样衣
                btns.add(CommonListBtnsEntity(BTN_SCHEDULE, "查看进度"  ))
                btns.add(CommonListBtnsEntity(BTN_MODIFY, "我要修改"))
            }
            STATE_CONFIRMED_WAIT -> {
                //待确认
                btns.add(CommonListBtnsEntity(BTN_OFFER_CONFIRM, "确认报价", R.drawable.base_btn_yellow_bg))
                btns.add(CommonListBtnsEntity(BTN_OFFER_REFUSE, "拒绝报价"))
            }
            STATE_CONFIRMED, STATE_DEMAND_CLOSE, STATE_OFFER_WAIT_SECOND  ->{
                //已确认、已关闭、待报价（二次报价）
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