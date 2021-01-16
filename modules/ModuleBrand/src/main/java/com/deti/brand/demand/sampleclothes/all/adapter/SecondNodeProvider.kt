package com.deti.brand.demand.sampleclothes.all.adapter

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.deti.brand.R
import com.deti.brand.demand.price.all.entity.PriceListAllBtnEntity
import com.deti.brand.demand.sampleclothes.all.entity.SampleClothesListAllBtnEntity
import com.deti.brand.demand.sampleclothes.all.entity.SecondNodeEntity
import com.safmvvm.ui.toast.ToastUtil
import com.test.common.ui.dialog.morebtns.createDialogSelectedSingleMoreTip

class SecondNodeProvider(
    var mActivity: Activity?
) : BaseNodeProvider(){
    override val itemViewType: Int = 2

    override val layoutId: Int = R.layout.brand_item_simple_clothes_list_second

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        var data = item as SecondNodeEntity
        var rvBtns = helper.getView<RecyclerView>(R.id.rv_btns)
        var tv_more_btn = helper.getView<TextView>(R.id.tv_more_btn)

        var mAdapter = SampleClothesBtnAdapter(mActivity)

        var btnDatas = initBtn(data)

        tv_more_btn.setOnClickListener {
            //更多
            if(btnDatas.size > 3){

                var moreBtnsDatas = btnDatas.subList(3, btnDatas.size)
                var moreListString = arrayListOf<String>()
                moreBtnsDatas.forEach {
                    moreListString.add(it.text)
                }
                moreListString.createDialogSelectedSingleMoreTip(
                    context,
                    it,
                ){position: Int, text: String->
                    ToastUtil.showShortToast("选中了：$text")
                }.show()
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