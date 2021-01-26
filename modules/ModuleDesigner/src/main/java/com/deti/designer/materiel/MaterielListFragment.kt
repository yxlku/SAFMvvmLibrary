package com.deti.designer.materiel

import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import com.deti.designer.R
import com.deti.designer.BR
import com.deti.designer.databinding.DesignerFragmentMaterielListBinding
import com.deti.designer.materiel.adapter.MaterielListAdapter
import com.deti.designer.materiel.entity.MaterielListEntity
import com.safmvvm.ext.ui.progressview.item.LinearDividerItemDecoration
import com.safmvvm.mvvm.view.BaseFragment
import com.safmvvm.mvvm.view.BaseLazyFragment
import me.jessyan.autosize.utils.AutoSizeUtils

/**
 * 物料列表
 */
class MaterielListFragment: BaseLazyFragment<DesignerFragmentMaterielListBinding, MaterielListViewModel>(
    R.layout.designer_fragment_materiel_list,
    BR.viewModel
) {

    override fun initData() {
        super.initData()
        var mAdapter = MaterielListAdapter(activity)
        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context).apply {
                var lineD = LinearDividerItemDecoration.Builder()
                    .setDividerColor(Color.parseColor("#F5F5F5"))
                    .setDividerHeight(AutoSizeUtils.mm2px(context, 10F))
                    .build()
                addItemDecoration((lineD))
            }

            adapter = mAdapter
        }
        mAdapter.setList(testData())
    }

    fun testData(): ArrayList<MaterielListEntity>{
        var list = arrayListOf<MaterielListEntity>()
        for (i in 0 until 5){
            if(i % 2 == 1) {
                list.add(MaterielListEntity("X111111 $i", (System.currentTimeMillis() + 10000).toString(), "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1495097764,1513657123&fm=26&gp=0.jpg", i))
            }else{
                list.add(MaterielListEntity("X111111 $i", (System.currentTimeMillis() + 20000).toString(),  "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1495097764,1513657123&fm=26&gp=0.jpg", i))
            }
        }
        return list
    }
}