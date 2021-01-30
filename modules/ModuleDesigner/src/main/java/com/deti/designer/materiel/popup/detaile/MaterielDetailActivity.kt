package com.deti.designer.materiel.popup.detaile

import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.designer.R
import com.deti.designer.BR
import com.deti.designer.databinding.DesignerActivityMaterielDetailBinding
import com.deti.designer.materiel.popup.detaile.item.ItemRemark
import com.deti.designer.materiel.popup.detaile.item.ItemRemarksEntity
import com.safmvvm.mvvm.view.BaseActivity

/**
 * 物料详情：本来是弹窗，改为页面了
 */
class MaterielDetailActivity: BaseActivity<DesignerActivityMaterielDetailBinding, MaterielDetailViewModel>(
    R.layout.designer_activity_materiel_detail,
    BR.viewModel
) {
    var mAdapter = BaseBinderAdapter()
    override fun initData() {
        super.initData()

        mAdapter.apply {
            addItemBinder(ItemRemarksEntity::class.java, ItemRemark())
        }

        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        var listData = arrayListOf(

            //备注
            ItemRemarksEntity()
        )
        mAdapter.setList(listData)

        mBinding.rgType.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                //主料辅料选择
            }
        })

    }

}