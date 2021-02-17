package com.deti.brand.demand.price.list.adapter

import android.app.Activity
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.brand.R
import com.deti.brand.databinding.BrandItemPriceListAllBinding
import com.deti.brand.demand.detail.PriceDetailActivity
import com.deti.brand.demand.price.PriceDemandViewModel
import com.deti.brand.demand.price.list.PriceListAllViewModel
import com.deti.brand.demand.price.list.entity.PriceListAllEntity
import com.deti.brand.demand.progress.generate.SampleClothesProgressActivity
import com.deti.brand.demand.progress.logistics.LogisticsActivity
import com.deti.brand.demand.update.UpdateDemandActivity
import com.lxj.xpopup.core.CenterPopupView
import com.safmvvm.ext.ui.counttime.CountDownAndUpView
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.LogUtil
import com.test.common.ui.adapter.CommonListBtnsAdapter
import com.test.common.ui.adapter.CommonListBtnsEntity
import com.test.common.ui.item.listinfo.ItemListInfo
import com.test.common.ui.item.listinfo.ItemListInfoEntity
import com.test.common.ui.popup.comfirm.dialogComfirmAndCancelRemark
import com.test.common.ui.popup.comfirm.dialogComfirmAndCancelRemarkSingle
import com.test.common.ui.popup.comfirm.single.SingleEntity

/**
 * 报价列表
 */
class PriceListAllAdapter(
    var mActivity: Activity?,
    var mViewModel: PriceListAllViewModel,
): BaseQuickAdapter<PriceListAllEntity, BaseDataBindingHolder<BrandItemPriceListAllBinding>>(
    R.layout.brand_item_price_list_all
) {

    companion object{
        /**********************报价状态***********************/
        /** //1待报价 2待报价(报价中) - 此类型不用 3待确认 4待报价(无样衣时显示) 5已关闭 10已确认 12待报价(二次报价)*/
        /** 待报价 - 需要判断是否已签收，然后显示对应按钮*/
        const val STATE_OFFER_WAIT = 1
        /** 待报价 - 无样衣*/
        const val STATE_OFFER_WAIT_NO_SAMPLE = 4
        /** 待报价 - 二次报价*/
        const val STATE_OFFER_WAIT_SECOND = 12
        /** 待确认*/
        const val STATE_CONFIRMED_WAIT = 3
        /** 已确认报价*/
        const val STATE_CONFIRMED = 10
        /** 需求已关闭*/
        const val STATE_DEMAND_CLOSE = 5

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
            controlBtnsClick(data.id, item)
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


    override fun onViewAttachedToWindow(holder: BaseDataBindingHolder<BrandItemPriceListAllBinding>) {
        super.onViewAttachedToWindow(holder)
        holder.dataBinding?.apply {
            var data = data[holder.adapterPosition]
            if (data.status == STATE_OFFER_WAIT) {
                //一次报价 - 有样衣 - 使用样衣签收的字段
                controlTime(cvTime, data.receiveTimestamp)
            }else if (data.status == STATE_OFFER_WAIT_NO_SAMPLE) {
                //一次报价 - 无样衣 - 使用下单时间的字段
                controlTime(cvTime, data.orderTimestamp)
            }else{
                //其他
            }
        }
    }

    override fun onViewDetachedFromWindow(holder: BaseDataBindingHolder<BrandItemPriceListAllBinding>) {
        super.onViewDetachedFromWindow(holder)
        //停止计时
        holder.dataBinding?.apply {
            cvTime.stop()
        }
    }

    /**
     * 控制倒计时 、 或正计时
     */
    fun controlTime(cvTime: CountDownAndUpView, time: Long){
        var tm = (time - (System.currentTimeMillis() / 1000)) * 1000
        LogUtil.d("剩余时间：$tm 、 初始时间：${time}、 当前时间： ${(System.currentTimeMillis() / 1000)}")
        cvTime.start(tm)
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
            add(ItemListInfoEntity("4", "预算：", item.price+"（元）", contentColor = Color.parseColor("#333333")))

            if (item.status == STATE_CONFIRMED) {
                //已确认报价
                add(ItemListInfoEntity("5", "报价：", item.quotePrice+"（元）", contentColor = Color.parseColor("#333333")))
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
            //报价气泡
            rlQuotePrice.visibility = View.GONE
            //待报价时间提示 -- TODO 就算隐藏也要赋值时间字段，和倒计时字段
            gpOrderTime.visibility = View.GONE
            cvTime.visibility = View.GONE //倒计时文字，默认隐藏 TODO 还需要设置个默认值
            tvCountdown.text = "" //倒计时提示文字（还剩、签收后倒计时）
            cvTime.setOnCountdownEndListener {
                //倒计时结束
                if ((item.status == STATE_OFFER_WAIT && item.receiveFlag == "30")
                    || item.status == STATE_OFFER_WAIT_NO_SAMPLE) {
                    tvCountdown.text = "超时："
                }
            }
            //各种状态下的item对应UI的控制
            when (item.status) {
                STATE_OFFER_WAIT -> {
                    //待报价 -- 需要判断是否已签收，然后显示对应按钮
                    gpOrderTime.visibility = View.VISIBLE //显示时间空间
                    if (item.receiveFlag == "30") {
                        //已签收 -- 从签收时间开始倒计时
                        tvCountdown.text = "还剩："
                        cvTime.visibility = View.VISIBLE
                        tvOrderTime.text = item.receiveTime //签收后 - 显示签收时间
                        controlTime(cvTime, item.receiveTimestamp) //开始倒计时
                    }else{
                        //未签收 -- 提示签收后倒计时文字
                        tvCountdown.text = item.prompt
                        tvOrderTime.text = item.orderTime //未签收 显示下单时间
                    }
                }
                STATE_OFFER_WAIT_NO_SAMPLE -> {
                    //待得体报价 - 无样衣
                    gpOrderTime.visibility = View.VISIBLE
                    tvCountdown.text = "还剩："
                    cvTime.visibility = View.VISIBLE
                    tvOrderTime.text = item.orderTime //无样衣 显示下单时间
                    controlTime(cvTime, item.orderTimestamp) //开始倒计时
                }
                STATE_CONFIRMED_WAIT -> {
                    //待确认
                    //1、显示红色气泡提示：报价
                    rlQuotePrice.visibility = View.VISIBLE
                }
                STATE_OFFER_WAIT_SECOND,//待报价 - 二次报价
                STATE_CONFIRMED,    //已确认报价
                STATE_DEMAND_CLOSE //需求取消
                -> {

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
    fun controlBtnsClick(btnState: String, item: PriceListAllEntity){
        when (btnState) {
            BTN_LOGISTICS -> {
                //查看物流
                SampleClothesProgressActivity.startAction(mActivity, item.indentId)
            }
            BTN_MODIFY -> {
                //我要修改
                if (item.indentId.isNotEmpty()) {
                    UpdateDemandActivity.startAction(mActivity, item.indentId)
                }else{
                    ToastUtil.showShortToast("订单异常，请刷新列表")
                }
            }
            BTN_SCHEDULE -> {
                //查看进度
                LogisticsActivity.startAction(mActivity)
            }
            BTN_OFFER_CONFIRM -> {
                //确认报价
                mViewModel.requestAcceptQuote(item.quoteId)
            }
            BTN_OFFER_REFUSE -> {
                //拒绝报价
                showDialogRefuse(item)
            }
            BTN_OFFER_LOOK -> {
                //查看报价
                clickToDetaile(item)
            }
        }
    }
    /**
     * 跳转到详情页面
     *  TODO 还没有传入id，目前查询id都是写死的
     */
    private fun clickToDetaile(item: PriceListAllEntity) {
//        PriceDetailActivity.startAction(mActivity, item.indentId, item.quoteId)

        PriceDetailActivity.startAction(mActivity)
    }
    /**
     * 拒绝报价
     */
    private fun showDialogRefuse(item: PriceListAllEntity) {
        mActivity?.apply {
            if (!item.isSecond) {
                //一次拒绝报价
                arrayListOf(
                    SingleEntity("0", "拒绝本次报价，并将信息反馈给得体， 待得体再次报价"),
                    SingleEntity("1", "不需要再次报价，关闭需求"),
                ).dialogComfirmAndCancelRemarkSingle(
                    this,
                    mTitle = "拒绝报价",
                    mTipOne = "选择想要执行的操作：",
                    mTipTwo = "拒绝报价原因",
                    mRemarkHint = "拒绝本次报价的原因都可以写在这里哦， 还可以提出可接受的价格。",
                    mLeftBtnColor = Color.parseColor("#333333"),
                    mRightBtnColor = Color.parseColor("#F3B11C"),
                    mLeftClickBlock = { view: View, pop: CenterPopupView, inputText: String, singleEntity: SingleEntity ->
                        pop.dismiss()
                    },
                    mRightClickBlock = { view: View, pop: CenterPopupView, inputText: String, singleEntity: SingleEntity ->
                        var isClose = singleEntity.id == "1" //==1 关闭报价
                        if (inputText.isNotEmpty()) {
                            mViewModel.requestRefuseQuote(
                                item.quoteId,
                                inputText,
                                isClose
                            )
                            pop.dismiss()
                        } else {
                            ToastUtil.showShortToast("请输入拒绝报价原因")
                        }
                    },
                ).show()
            }else{
                //二次拒绝报价
                dialogComfirmAndCancelRemark(
                    this,
                    mTitle = "拒绝报价",
                    mTipOne = "温馨提示：二次拒绝报价，需求将自动关闭",
                    mTipTwo = "拒绝报价原因（选填）",
                    mLeftBtnColor = Color.parseColor("#333333"),
                    mRightBtnColor = Color.parseColor("#F3B11C"),
                    mLeftClickBlock = { view: View, pop: CenterPopupView, inputText: String ->
                        pop.dismiss()
                    },
                    mRightClickBlock = { view: View, pop: CenterPopupView, inputText: String ->
                        mViewModel.requestRefuseQuote(
                            item.quoteId,
                            inputText,
                            true
                        )
                        pop.dismiss()
                    },
                ).show()

            }
        }
    }
}