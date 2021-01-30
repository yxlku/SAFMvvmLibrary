package com.deti.designer.style

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.deti.designer.R
import com.deti.designer.BR
import com.deti.designer.databinding.DesignerFragmentStyleListBinding
import com.deti.designer.style.adapter.StyleListAdapter
import com.deti.designer.style.entity.StyleListEntity
import com.deti.designer.style.entity.StyleVersionData
import com.safmvvm.mvvm.view.BaseFragment

/**
 * 款式列表
 */
class StyleListFragment: BaseFragment<DesignerFragmentStyleListBinding, StyleListViewModel>(
    R.layout.designer_fragment_style_list,
    BR.viewModel
) {

    override fun initData() {
        super.initData()
        var mAdapter = StyleListAdapter(activity as AppCompatActivity)
        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
//        mAdapter.setList(listData)

        mAdapter.setList(testData())
    }

    fun testData(): List<StyleListEntity>{
        var datas = arrayListOf<StyleListEntity>()
        for (i in 0 until 5){
            var verdata = arrayListOf<StyleVersionData>()
            for (j in 0 until 3){
                verdata.add(
                    StyleVersionData("版本号：$i|$j", "版本名字")
                )
            }

            datas.add(StyleListEntity("货号：$i", "公司：$i", verdata))

        }
        return datas
    }

}