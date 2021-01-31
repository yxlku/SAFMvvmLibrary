package com.deti.designer.materiel.popup.detaile

import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.deti.designer.R
import com.deti.designer.BR
import com.deti.designer.databinding.DesignerActivityMaterielDetailBinding
import com.deti.designer.materiel.popup.detaile.item.remark.ItemRemark
import com.deti.designer.materiel.popup.detaile.item.remark.ItemRemarksEntity
import com.deti.designer.materiel.popup.detaile.item.type.ItemCraftEntity
import com.deti.designer.materiel.popup.detaile.item.type.ItemCraftType
import com.deti.designer.materiel.popup.detaile.item.type.ItemMaterielType
import com.deti.designer.materiel.popup.detaile.item.type.ItemMaterielTypeEntity
import com.safmvvm.mvvm.view.BaseActivity
import com.safmvvm.ui.theme.StatusBarUtil

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

        StatusBarUtil.init(this, true)


        mAdapter.apply {
            addItemBinder(ItemRemarksEntity::class.java, ItemRemark())
            addItemBinder(ItemMaterielTypeEntity::class.java, ItemMaterielType())
            addItemBinder(ItemCraftEntity::class.java, ItemCraftType())
        }

        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        var listData = arrayListOf(
            //物料类型
            ItemMaterielTypeEntity(testData()),
            //工艺
            ItemCraftEntity(testCratfData()),
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

    fun testData(): ArrayList<MaterielDeatilTypeEntity>{
        var typesData = arrayListOf<MaterielDeatilTypeEntity>()
        for (i in 0 until 3){
            var infoData = MaterielDeatilTypeData("供应商$i", "品名$i")
            var entity = MaterielDeatilTypeEntity("主料:$i", infoData)
            typesData.add(entity)
        }
        return typesData
    }

    fun testCratfData(): ArrayList<CraftDeatileTypeEntity>{
        var typesData = arrayListOf<CraftDeatileTypeEntity>()
        for (i in 0 until 3){
            var infoData = CraftDeatilTypeData("提供方$i")
            var entity = CraftDeatileTypeEntity("工艺:$i", infoData)
            typesData.add(entity)
        }
        return typesData
    }
}