package com.safmvvm.ext.ui.typesview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.safmvvm.bus.putValue
import com.safmvvm.ext.ui.typesview.adapter.first.TypeFirstAdapter
import com.safmvvm.ext.ui.typesview.adapter.four.TypeFourAdapter
import com.safmvvm.ext.ui.typesview.adapter.two.TypeThreeAdapter
import com.safmvvm.ext.ui.typesview.adapter.two.TypeTwoAdapter
import com.safmvvm.ext.ui.typesview.entity.*

/**
 * 分类列表
 */
class TypesRecyclerView : LinearLayoutCompat {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
        attrs,
        defStyleAttr) {
        initView()
    }

    fun testData(): List<TypesViewEntity> {
        var typesViewEntitys = arrayListOf<TypesViewEntity>()
        for (i in 0 until 5) {
            var twoEntitys = arrayListOf<TypesViewTwoEntity>()
            for (j in 0 until 4) {
                var threeEntitys = arrayListOf<TypesViewThreeEntity>()
                for (k in 0 until 3) {
                    var fourEntitys = arrayListOf<TypesViewFourEntity>()
                    for (l in 0 until 5) {
                        var fourEntity = TypesViewFourEntity(
                            l,
                            "four$i$j$k$l",
                            null
                        )
                        fourEntitys.add(fourEntity)
                    }
                    var threeEntity = TypesViewThreeEntity(
                        k,
                        "three$i$j$k",
                        fourEntitys
                    )
                    threeEntitys.add(threeEntity)
                }
                var twoEntity = TypesViewTwoEntity(
                    j,
                    "two$i$j",
                    threeEntitys
                )
                twoEntitys.add(twoEntity)
            }
            var firstEntity = TypesViewEntity(
                i,
                "first$i",
                twoEntitys
            )
            typesViewEntitys.add(firstEntity)
        }
        return typesViewEntitys
    }

    var rvOne: RecyclerView? = null
    var rvTwo: RecyclerView? = null
    var rvThree: RecyclerView? = null
    var rvFour: RecyclerView? = null

    var oneAdapter = TypeFirstAdapter()
    var twoAdapter = TypeTwoAdapter()
    var threeAdapter = TypeThreeAdapter()
    var fourAdapter = TypeFourAdapter()

    private fun initView() {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        orientation = HORIZONTAL


        var rvOne = createRecyclerView()
        var rvTwo = createRecyclerView()
        var rvThree = createRecyclerView()
        var rvFour = createRecyclerView()

        addView(rvOne)
        addView(rvTwo)
        addView(rvThree)
        addView(rvFour)

        invalidate()

        rvOne.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = oneAdapter
        }
        rvTwo.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = twoAdapter
        }
        rvThree.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = threeAdapter
        }
        rvFour.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = fourAdapter
        }

        oneAdapter.setList(testData())

        oneAdapter.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                var mAdapter = adapter as TypeFirstAdapter
                mAdapter.mViewModel.selectedPosition.putValue(position)
                mAdapter.notifyDataSetChanged()

                var firstData = mAdapter.data[position]
                var secondData = firstData.childEntity
                twoAdapter.setList(secondData)
            }
        })
        twoAdapter.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                var mAdapter = adapter as TypeTwoAdapter
                mAdapter.mViewModel.selectedPosition.putValue(position)
                mAdapter.notifyDataSetChanged()

                var firstData = mAdapter.data[position]
                var secondData = firstData.childEntity
                threeAdapter.setList(secondData)
            }
        })
        threeAdapter.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                var mAdapter = adapter as TypeThreeAdapter
                mAdapter.mViewModel.selectedPosition.putValue(position)
                mAdapter.notifyDataSetChanged()

                var firstData = mAdapter.data[position]
                var secondData = firstData.childEntity
                fourAdapter.setList(secondData)
            }
        })
        fourAdapter.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                var mAdapter = adapter as TypeFourAdapter
                mAdapter.mViewModel.selectedPosition.putValue(position)
                mAdapter.notifyDataSetChanged()
            }
        })
    }

    /**
     * 创建列表
     */
    fun createRecyclerView(): RecyclerView {
        var recyclerView = RecyclerView(context)
        recyclerView.layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0F)
        return recyclerView
    }
}