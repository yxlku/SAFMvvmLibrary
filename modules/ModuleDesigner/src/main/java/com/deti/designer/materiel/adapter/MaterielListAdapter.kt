package com.deti.designer.materiel.adapter

import android.app.Activity
import android.graphics.Color
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cn.iwgang.countdownview.CountdownView
import cn.iwgang.countdownview.DynamicConfig
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.deti.designer.R
import com.deti.designer.databinding.DesignerItemMaterielListBinding
import com.deti.designer.materiel.MaterielListViewModel
import com.deti.designer.materiel.entity.MaterielListEntity
import com.deti.designer.materiel.popup.addmateriel.PopupAddMaterielFragment
import com.deti.designer.materiel.popup.push.PushMaterielFragment
import com.deti.designer.materiel.popup.revoke.DialogRevokeFragment
import com.lxj.xpopup.core.CenterPopupView
import com.safmvvm.ui.dialog.DialogUtil
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.LogUtil
import com.test.common.adapter.CommonListBtnsAdapter
import com.test.common.adapter.CommonListBtnsEntity
import com.test.common.ui.popup.base.BaseDialogSingleEntity
import com.test.common.ui.popup.comfirm.dialogComfirmAndCancel
import com.test.common.ui.popup.comfirm.dialogComfirmAndCancelInput
import com.test.common.ui.popup.dialogBubbleSingle
import com.test.common.ui.popup.single.DialogBubbleSinglePopupView
import kotlin.collections.ArrayList

class MaterielListAdapter(
    var mActivity: AppCompatActivity?,
    var mViewModel: MaterielListViewModel
) :
    BaseQuickAdapter<MaterielListEntity, BaseDataBindingHolder<DesignerItemMaterielListBinding>>(
        R.layout.designer_item_materiel_list
    ) {

    override fun onViewAttachedToWindow(holder: BaseDataBindingHolder<DesignerItemMaterielListBinding>) {
        super.onViewAttachedToWindow(holder)
        holder.dataBinding?.apply {
            var data = data[holder.adapterPosition]
            var tm = data.time.toLong() - System.currentTimeMillis()

            //控制背景颜色
            controlTimeBg(cvTime, tm)
            //刷新时间
            refreshTime(cvTime, tm)
        }
    }

    override fun onViewDetachedFromWindow(holder: BaseDataBindingHolder<DesignerItemMaterielListBinding>) {
        super.onViewDetachedFromWindow(holder)
        holder.dataBinding?.apply {
            cvTime.stop()
//            chronometer?.stop()
        }
    }
//    var chronometer: Chronometer? = null
    fun refreshTime(cvView: CountdownView, leftTime: Long) {
        if(leftTime > 0){
            cvView.start(leftTime)
        }else{
            var addTime: Long = -leftTime
            LogUtil.d("${addTime} - 时间 - ${leftTime}")
//            var runable = object : Runnable{
//                override fun run() {
//                    addTime += 1000
//                    cvView.updateShow(addTime)
//                    cvView.postDelayed(this, 1000)
//                    LogUtil.d("刷新：${addTime}")
//                }
//            }
//            cvView.post(runable)
            var chronometer = Chronometer(context).apply {
                base = addTime
            }
            chronometer.setOnChronometerTickListener {
                LogUtil.d("sssssssss: ${SystemClock.elapsedRealtime() - it.base}")
                cvView.updateShow(SystemClock.elapsedRealtime() - it.base)
            }
            chronometer.start()
        }
    }

    override fun convert(
        holder: BaseDataBindingHolder<DesignerItemMaterielListBinding>,
        item: MaterielListEntity,
    ) {
        var binding = holder.dataBinding
        var btnsAdapter = CommonListBtnsAdapter()
        binding?.apply {
            entity = item
            //1、按钮
            rvBtns.apply {
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
                adapter = btnsAdapter
            }
            btnsAdapter.setList(controlBtns(item))
            btnsAdapter.setOnItemClickListener(object : OnItemClickListener{
                override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                    var data = adapter.data[position] as CommonListBtnsEntity
                    when (data.id) {
                        BTN_MATERIEL_ADD -> {
                            //添加物料
                            mActivity?.apply {
                                PopupAddMaterielFragment().show(supportFragmentManager, "")
                            }
                        }
                        BTN_MATERIEL_PUSH -> {
                            //物料推送
                            clickMaterielPush()
                        }

                        BTN_MATERIEL_BACK -> {
                            mActivity?.apply {
                                dialogComfirmAndCancelInput(
                                    this,
                                    mTitle = "退回需求单",
                                    mTipOne = "",
                                    mTipTwo = "请填写退回此单的原因",
                                    mLeftText = "取消",
                                    mRightText = "确定退回",
                                    mLeftClickBlock = {view: View, pop: CenterPopupView, inputText: String ->
                                        pop.dismiss()
                                    },
                                    mRightClickBlock = {view: View, pop: CenterPopupView, inputText: String ->
                                        ToastUtil.showShortToast("退回原因:${inputText}")
                                    }

                                ).show()
                            }
                        }

                    }
                }
            })

            //更多按钮
            var btnList = controlMoreBtns(item, tvMoreBtn)
            tvMoreBtn.setOnClickListener{
                mActivity?.apply {
                    btnList.dialogBubbleSingle(
                        this,
                        tvMoreBtn,
                        DialogBubbleSinglePopupView.MODE_NONE,
                        -1,
                        true,
                    ) { view: View, position: Int, entity: BaseDialogSingleEntity ->
                        when (entity.id) {
                            BTN_MATERIEL_PUSH -> {
                                //物料推送
                                clickMaterielPush()
                            }
                            BTN_MATERIEL_REVOKE -> {
                                //撤回
                                clickRevoke()
                            }
                            else -> {
                            }
                        }
                    }.show()
                }
            }

            //倒计时
            cvTime.start(item.time.toLong() - System.currentTimeMillis())
            //倒计时结束后往上累加时间
            cvTime.setOnCountdownEndListener {
                controlTimeBg(it, 0)
                refreshTime(it, 0)
            }
            executePendingBindings()
        }
    }

    /**
     * 控制倒计时控件属性
     */
    fun controlTimeBg(ct: CountdownView, time: Long){
        var backgroundInfo = DynamicConfig.BackgroundInfo().apply {
            color = Color.parseColor("#000000")
        }
        if(time <= 0){
            backgroundInfo = DynamicConfig.BackgroundInfo().apply {
                color = Color.parseColor("#F74B60")
            }
        }
        var ds = DynamicConfig.Builder()
            .setBackgroundInfo(backgroundInfo)
            .build()
        ct.dynamicShow(ds)
    }
    companion object{
        /** 按钮：物料添加*/
        const val BTN_MATERIEL_ADD = "bt_materiel_add"
        /** 按钮：物料推送*/
        const val BTN_MATERIEL_PUSH = "bt_materiel_push"
        /** 按钮：退回*/
        const val BTN_MATERIEL_BACK = "bt_materiel_back"
        /** 按钮：撤回*/
        const val BTN_MATERIEL_REVOKE = "bt_materiel_revoke"
    }
    fun controlBtns(data: MaterielListEntity): ArrayList<CommonListBtnsEntity>{
        var list = arrayListOf<CommonListBtnsEntity>()
        when (data.state) {
            0 -> {
                list.apply {
                    add(CommonListBtnsEntity(BTN_MATERIEL_ADD, "添加物料", R.drawable.base_btn_yellow_bg))
                    add(CommonListBtnsEntity(BTN_MATERIEL_PUSH, "物料推送"))
                    add(CommonListBtnsEntity(BTN_MATERIEL_BACK, "退回该单"))
                }
            }
            1 -> {
                list.apply {
                    add(CommonListBtnsEntity("0", "添加完毕", R.drawable.base_btn_yellow_bg))
                    add(CommonListBtnsEntity("0", "选择物料"))
                    add(CommonListBtnsEntity("0", "继续添加"))
                }
            }
        }
        return list
    }

    fun controlMoreBtns(data: MaterielListEntity, moreTv: TextView): ArrayList<BaseDialogSingleEntity>{
        var btnsList = arrayListOf<BaseDialogSingleEntity>()
        when (data.state) {
            1 -> {
                moreTv.visibility = View.VISIBLE
                btnsList.apply {
                    add(BaseDialogSingleEntity(BTN_MATERIEL_PUSH, "物料推送"))
                    add(BaseDialogSingleEntity("0", "退回该单"))
                    add(BaseDialogSingleEntity(BTN_MATERIEL_REVOKE, "撤回"))
                }
            }
            else -> {
                moreTv.visibility = View.GONE
            }
        }
        return btnsList
    }

    /** 物料 推送*/
    fun clickMaterielPush(){
        mActivity?.apply {
            PushMaterielFragment().show(this.supportFragmentManager, "")
        }
    }

    /**
     * 添加确认
     */
    fun clickSure(){
        mActivity?.apply {
            dialogComfirmAndCancel(
                this,
                mTitle = "添加物料确认",
                mContent = "确定物料已经添加完毕了吗？",
                mLeftText = "取消",
                mRightText = "确认完成",
                mLeftClickBlock = {view: View, pop: CenterPopupView ->
                    pop.dismiss()
                },
                mRightClickBlock = {view: View, pop: CenterPopupView ->
                    ToastUtil.showShortToast("确定完成")
                }
            ).show()
        }
    }

    /**
     * 撤回
     */
    fun clickRevoke(){
        mActivity?.apply {
            DialogRevokeFragment().show(supportFragmentManager, "")
        }
    }

}