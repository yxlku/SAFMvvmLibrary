package com.deti.designer.version.datalist

import androidx.recyclerview.widget.LinearLayoutManager
import com.deti.designer.R
import com.deti.designer.BR
import com.deti.designer.databinding.DesignerItemVersionDataListBinding
import com.deti.designer.version.adapter.DemandOrderAdapter
import com.deti.designer.version.entity.DemandOrderEntity
import com.deti.designer.version.entity.GoodsDataEntity
import com.safmvvm.mvvm.view.BaseFragment
import com.safmvvm.mvvm.view.BaseLazyFragment

/**
 * 版单列表数据列表
 */
class VersionDataListFragment(
    /** 页面类型*/
    var mPageType: String = TYPE_ALL
): BaseLazyFragment<DesignerItemVersionDataListBinding, VersionDataListViewModel>(
    R.layout.designer_item_version_data_list,
    BR.viewModel
) {

    companion object{
        /** 全部列表*/
        const val TYPE_ALL = "type_all"
        /** 未添加*/
        const val TYPE_ADD_UN = "type_add_un"
        /** 已完成*/
        const val TYPE_COMPLETE = "type_completed"
        /** 未完成*/
        const val TYPE_COMPLETE_UN = "type_complete_un"
    }
    var listData = arrayListOf<DemandOrderEntity>()
    var mAdapter = DemandOrderAdapter()

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()

        mBinding.rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        listData.addAll(testData())
        mAdapter.setList(listData)

    }


    fun testData(): List<DemandOrderEntity>{
        var listData = arrayListOf<DemandOrderEntity>()
        for (i in 0 until 5){
            var goodsList = arrayListOf<GoodsDataEntity>()
            if (i % 2 == 1) {
                for (j in 0 until 3){
                    var goodsDataEntity = GoodsDataEntity("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2543020573,157321607&fm=26&gp=0.jpg", "name$i、$j")
                    goodsList.add(goodsDataEntity)
                }
            }else{
                for (j in 0 until 1){
                    var goodsDataEntity = GoodsDataEntity("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn.sinaimg.cn%2Fjiangsu%2Ftransform%2F20151203%2FPy4J-fxmifze7562559.jpg&refer=http%3A%2F%2Fn.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614509945&t=607a49f32a3ddde1bd8a8071d954369a", "name$i、$j")
                    goodsList.add(goodsDataEntity)
                }
            }
            var item = DemandOrderEntity(goodsList)
            listData.add(item)
        }
        return listData
    }

}