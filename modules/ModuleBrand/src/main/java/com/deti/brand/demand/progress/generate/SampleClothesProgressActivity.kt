package com.deti.brand.demand.progress.generate

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import com.deti.brand.BR
import com.deti.brand.R
import com.deti.brand.databinding.BrandActivitySampleClothesProgressBinding
import com.deti.brand.demand.progress.generate.entity.InfoListDataBean
import com.deti.brand.demand.progress.generate.entity.SapmleClothesLogisticsEntity
import com.luck.picture.lib.camera.listener.ClickListener
import com.lxj.xpopup.core.CenterPopupView
import com.safmvvm.ext.ui.progressview.PorgressStepView
import com.safmvvm.mvvm.view.BaseActivity
import com.safmvvm.ui.toast.ToastUtil
import com.safmvvm.utils.TextViewUtil
import com.safmvvm.utils.Utils.callPhone
import com.safmvvm.utils.Utils.textToClipboard
import com.test.common.ui.popup.comfirm.dialogComfirmAndCancel


/**
 * 样衣进度
 */
class SampleClothesProgressActivity: BaseActivity<BrandActivitySampleClothesProgressBinding, SampleClothesProgressViewModel>(
    R.layout.brand_activity_sample_clothes_progress,
    BR.viewModel
) {

    companion object{
        fun startAction(activity: Activity?, demandId: String){
            activity?.apply {
                var intent = Intent(this, SampleClothesProgressActivity::class.java)
                intent.putExtra("demandId", demandId)
                startActivity(intent)
            }
        }
    }

    val mStepsList = arrayListOf(
        "样衣发货",
        "样衣运输",
        "样衣签收"
    )

    /** 订单Id*/

    override fun initData() {
        super.initData()

        mBinding.stepView.setSteps(mStepsList)
        mBinding.tvLogisticsCopy.setOnClickListener {
            //快递单号复制到剪切板中
            mViewModel.mExPressCode.get()?.textToClipboard(this)
            ToastUtil.showShortToast("快递单号已复制到剪切板")
        }
        //刷新状态布局
        mViewModel.PROGRESS_UPDATE_UI_STATE.observe(this){
            it?.apply {
                //状态
                mBinding.stepView.go(it, true)
            }
        }
        mViewModel.PROGRESS_UPDATE_UI_LOGISTICS.observe(this){
            it?.apply {
                mBinding.psvLogisticsProgress.apply {
                    setDatas(it)
                    setBindViewListener(object : PorgressStepView.BindViewListener{
                        override fun onBindView(itemMsg: TextView?, itemDate: TextView?, data: Any?) {
                            var entity = data as InfoListDataBean
                            itemDate?.text = entity.time
                            itemMsg?.apply {
                                text = TextViewUtil.formatPhoneNumber(
                                    this,
                                    entity.context,
                                    Color.parseColor("#3f8de2")
                                ) { textView, phoneNum ->
                                    dialogComfirmAndCancel(
                                        this@SampleClothesProgressActivity,
                                        mContent = "是否拨打：$phoneNum",
                                        mLeftClickBlock = {view: View, pop: CenterPopupView ->
                                          pop.dismiss()
                                        },
                                        mRightClickBlock = {view: View, pop: CenterPopupView ->
                                            //打电话
                                            startActivity(phoneNum.callPhone())
                                            pop.dismiss()
                                        }
                                    ).show()
                                }
                            }

                        }
                    })
                }
            }
        }


    }

}