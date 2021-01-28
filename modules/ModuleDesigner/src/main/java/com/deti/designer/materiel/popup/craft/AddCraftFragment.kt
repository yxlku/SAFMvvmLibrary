package com.deti.designer.materiel.popup.craft

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.designer.BR
import com.deti.designer.R
import com.deti.designer.databinding.DesignerPopupAddCraftBinding
import com.deti.designer.materiel.popup.craft.item.choose.ItemChooseCraft
import com.deti.designer.materiel.popup.craft.item.choose.ItemChooseCraftEntity
import com.deti.designer.materiel.popup.craft.item.del.ItemDelCraft
import com.deti.designer.materiel.popup.craft.item.del.ItemDelCraftEntity
import com.deti.designer.materiel.popup.craft.item.input.ItemInputCraft
import com.deti.designer.materiel.popup.craft.item.input.ItemInputCraftEntity
import com.deti.designer.materiel.popup.craft.item.title.ItemTitleCraft
import com.deti.designer.materiel.popup.craft.item.title.ItemTitleCraftEntity
import com.safmvvm.mvvm.view.bottom.BaseBottomFragment
import com.safmvvm.ui.titlebar.OnTitleBarListener

/**
 * 添加工艺
 */
class AddCraftFragment : BaseBottomFragment<DesignerPopupAddCraftBinding, AddCraftViewModel>(
    R.layout.designer_popup_add_craft,
    BR.viewModel
) {


    var listData = arrayListOf<AddCraftItemEntity>(
        AddCraftItemEntity()
    )
    override fun initData() {
        super.initData()
        var mAdapter = AddCraftAdapter(mViewModel)
        var footView = LayoutInflater.from(context).inflate(R.layout.designer_footer_add_craft, null, false)

        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        mAdapter.setList(listData)
        mAdapter.addFooterView(footView)

        footView.setOnClickListener {

        }

        mBinding.tbTitle.setOnTitleBarListener(object : OnTitleBarListener{
            override fun onLeftClick(v: View?) = dismiss()
            override fun onTitleClick(v: View?) {}
            override fun onRightClick(v: View?) {
                mAdapter.addData(AddCraftItemEntity())
            }
        })

    }

}