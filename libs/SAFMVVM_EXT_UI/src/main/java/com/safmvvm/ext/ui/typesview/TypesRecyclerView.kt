package com.safmvvm.ext.ui.typesview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.safmvvm.ext.ui.typesview.entity.BaseTypesNode
import com.safmvvm.ext.ui.typesview.test.FourEntity
import com.safmvvm.ext.ui.typesview.test.SecondEntity
import com.safmvvm.ext.ui.typesview.test.ThridEntity
import me.jessyan.autosize.utils.AutoSizeUtils

/**
 * 分类列表
 */
class TypesRecyclerView: LinearLayoutCompat {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        initView()
    }
    fun testData(): List<BaseTypesNode>{
        var firstDatas = arrayListOf<BaseTypesNode>()
        for (i in 0 until 10){
            var secondDatas = arrayListOf<BaseTypesNode>()
            for (i in 0 until 3){
                var thridDatas = arrayListOf<BaseTypesNode>()
                for (i in 0 until 4){
                    var fourDatas = arrayListOf<BaseTypesNode>()
                    for (i in 0 until 5){
                        fourDatas.add(FourEntity("four : $i", null))
                    }
                    thridDatas.add(ThridEntity("thrid : $i", fourDatas))
                }
                secondDatas.add(SecondEntity("second : $i", thridDatas))
            }
            firstDatas.add(SecondEntity("frist : $i", secondDatas))
        }


        return firstDatas
    }
    var rvOne: RecyclerView? = null
    var rvTwo: RecyclerView? = null
    var rvThree: RecyclerView? = null
    var rvFour: RecyclerView? = null

    private fun initView() {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT )
        orientation = HORIZONTAL


        var rvOne = createRecyclerView(testData())
        var rvTwo = createRecyclerView(null)
        var rvThree = createRecyclerView(null)
        var rvFour = createRecyclerView(null)

        addView(rvOne)
        addView(rvTwo)
        addView(rvThree)
        addView(rvFour)

        invalidate()
    }

    /**
     * 创建列表
     */
    fun createRecyclerView(
        datas: List<BaseTypesNode>?,
        itemClickBlock: (view: View, data: BaseTypesNode)->Unit={view: View, data: BaseTypesNode->}
    ): RecyclerView{
        var recyclerView = RecyclerView(context)
        recyclerView.layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0F)
        var itemLinerManager = LinearLayoutManager(context)
        var itemAdaper = TypesAdapter(context, itemClickBlock)
        datas?.run {
            itemAdaper.mDatas = datas
        }
        recyclerView.apply {
            layoutManager = itemLinerManager
            adapter = itemAdaper
        }
        return recyclerView
    }
}