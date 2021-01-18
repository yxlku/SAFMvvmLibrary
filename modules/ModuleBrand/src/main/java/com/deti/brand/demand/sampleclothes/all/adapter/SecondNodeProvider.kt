package com.deti.brand.demand.sampleclothes.all.adapter

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.deti.brand.R
import com.deti.brand.demand.price.all.entity.PriceListAllBtnEntity
import com.deti.brand.demand.progress.generate.SampleClothesProgressActivity
import com.deti.brand.demand.progress.logistics.LogisticsActivity
import com.deti.brand.demand.sampleclothes.all.entity.SampleClothesListAllBtnEntity
import com.deti.brand.demand.sampleclothes.all.entity.SecondNodeEntity
import com.lxj.xpopup.core.BasePopupView
import com.safmvvm.ui.toast.ToastUtil
import com.test.common.ui.dialog.morebtns.createDialogSelectedSingleMoreTip
import com.test.common.ui.popup.base.BaseDialogSingleEntity
import com.test.common.ui.popup.dialogBubbleSingle
import com.test.common.ui.popup.single.DialogBubbleSinglePopupView

class SecondNodeProvider(
    var mActivity: Activity?
) : BaseNodeProvider(){
    override val itemViewType: Int = 2

    override val layoutId: Int = R.layout.brand_item_simple_clothes_list_second
    var testSelected = 2
    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        var data = item as SecondNodeEntity
        var rvBtns = helper.getView<RecyclerView>(R.id.rv_btns)
        var tv_more_btn = helper.getView<TextView>(R.id.tv_more_btn)

        var mAdapter = SampleClothesBtnAdapter(mActivity)

        var btnDatas = initBtn(data)


        tv_more_btn.setOnClickListener {
            //更多
            if(btnDatas.size > 3){

//                var moreBtnsDatas = btnDatas.subList(3, btnDatas.size)
//                var moreListString = arrayListOf<String>()
//                moreBtnsDatas.forEach {
//                    moreListString.add(it.text)
//                }
//                moreListString.createDialogSelectedSingleMoreTip(
//                    context,
//                    it,
//                ){position: Int, text: String->
//                    ToastUtil.showShortToast("选中了：$text")
//                }.show()

                var listData = arrayListOf<BaseDialogSingleEntity>(
                    BaseDialogSingleEntity(0, "我要修改33"),
                    BaseDialogSingleEntity(4, "我要修改44"),
                    BaseDialogSingleEntity(4, "我要修改55")
                )
                mActivity?.apply {
                    listData.dialogBubbleSingle(
                        this,
                        tv_more_btn,
                        DialogBubbleSinglePopupView.MODE_NONE,
                        testSelected,
                        true
                    ){ view: View, position: Int, entity: BaseDialogSingleEntity->
                        testSelected = position
                        ToastUtil.showShortToast("选中了：${entity.text}")
                    }.show()
                }
            }
        }


        if(btnDatas.size > 3){
            tv_more_btn.visibility = View.VISIBLE
        }else{
            tv_more_btn.visibility = View.GONE
        }
        rvBtns.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            adapter = mAdapter
        }

        mAdapter.setList(btnDatas)
        mAdapter.setOnItemClickListener { adapter, view, position ->
            when (position) {
                0 -> {
                    mActivity?.apply {
                        var intent = Intent(this, SampleClothesProgressActivity::class.java)
                        startActivity(intent)
                    }
                }
                1 -> {
                    mActivity?.apply {
                        var intent = Intent(this, LogisticsActivity::class.java)
                        startActivity(intent)
                    }
                }
                else -> {
                }
            }
        }
    }

    private fun initBtn(item: SecondNodeEntity) : ArrayList<SampleClothesListAllBtnEntity>{
        var listBtn: ArrayList<SampleClothesListAllBtnEntity> = arrayListOf<SampleClothesListAllBtnEntity>()
        //查看物流、我要修改
        listBtn.add(
            SampleClothesListAllBtnEntity(
                0,
                "查看物流",
                0,
                R.drawable.base_btn_yellow_bg
            )
        )
        listBtn.add(
            SampleClothesListAllBtnEntity(
                0,
                "我要修改",
                0,
                R.drawable.base_btn_black_bg
            )
        )
        listBtn.add(
            SampleClothesListAllBtnEntity(
                0,
                "我要修改2",
                0,
                R.drawable.base_btn_black_bg
            )
        )
        listBtn.add(
            SampleClothesListAllBtnEntity(
                0,
                "我要修改3",
                0,
                R.drawable.base_btn_black_bg
            )
        )
        listBtn.add(
            SampleClothesListAllBtnEntity(
                0,
                "我要修改4",
                0,
                R.drawable.base_btn_black_bg
            )
        )
        return listBtn
    }

}