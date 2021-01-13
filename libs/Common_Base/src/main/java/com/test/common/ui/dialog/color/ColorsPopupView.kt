package com.test.common.ui.dialog.color

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.lxj.xpopup.core.BottomPopupView
import com.lxj.xpopup.util.XPopupUtils
import com.safmvvm.bus.putValue
import com.test.common.R
import com.test.common.ui.dialog.color.adapter.ColorsAdapter
import com.test.common.ui.dialog.color.adapter.ColorsSelectedAdapter
import com.test.common.ui.dialog.color.adapter.ColorsSubAdapter

class ColorsPopupView(
    var mActivit: Activity,
    var mTitle: String = "",
    var mHeightMultiple: Float = 0.7F,
) : BottomPopupView(mActivit), View.OnClickListener {
    var rv_selected: RecyclerView? = null
    var rv_left: RecyclerView? = null
    var rv_right: RecyclerView? = null

    var adapterLeft: ColorsAdapter = ColorsAdapter()
    var adapterRight: ColorsSubAdapter = ColorsSubAdapter()
    var adapterSelected: ColorsSelectedAdapter = ColorsSelectedAdapter()

    var selectedList = arrayListOf<ColorsSubEntity>()

    override fun getImplLayoutId(): Int = R.layout.base_dialog_colors

    /**
     * 弹窗最高高度
     */
    override fun getMaxHeight(): Int =
        (XPopupUtils.getScreenHeight(mActivit) * mHeightMultiple).toInt()

    fun testData(): List<ColorsEntity> {
        var colors = arrayListOf<ColorsEntity>()
        for (i in 0 until 10) {
            var colorsSubEntity = arrayListOf<ColorsSubEntity>()
            for (j in 0 until 20) {
                var subColorsEntity = ColorsSubEntity(
                    0,
                    "色$i$j",
                    false,
                )
                colorsSubEntity.add(subColorsEntity)
            }
            var colorsEntity = ColorsEntity(
                0,
                "颜色系$i",
                false,
                colorsSubEntity
            )
            colors.add(colorsEntity)
        }
        return colors
    }

    override fun onCreate() {
        super.onCreate()
        var tv_title: TextView = findViewById(R.id.tv_title)
        var tv_sure: TextView = findViewById(R.id.tv_sure)
        rv_selected = findViewById(R.id.rv_selected)
        rv_left = findViewById(R.id.rv_left)
        rv_right = findViewById(R.id.rv_right)

        tv_title.text = mTitle
        tv_sure.setOnClickListener(this)

        rv_left?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterLeft
        }
        rv_right?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterRight
        }
        rv_selected?.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            adapter = adapterSelected
        }
        adapterLeft.setList(testData())
        adapterLeft.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                var mAdapter = adapter as ColorsAdapter
                mAdapter.mViewModel.selectedPosition.putValue(position)
                mAdapter.notifyDataSetChanged()

                var firstData = mAdapter.data[position]
                var secondData = firstData.colorsSubEntitys
                adapterRight.setList(secondData)
            }
        })
        adapterRight.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                var mAdapter = adapter as ColorsSubAdapter
                var data = mAdapter.data[position]
                //刷新列表
                data.isCheck = !data.isCheck
                mAdapter.notifyDataSetChanged()

                //刷新选中区域列表
                if (!selectedList.contains(data) && data.isCheck) {
                    selectedList.add(data)
                }else{
                    selectedList.remove(data)
                }
                adapterSelected.setList(selectedList)
            }
        })
        adapterSelected.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                var mAdapter = adapter as ColorsSelectedAdapter
                mAdapter.data.removeAt(position)
                mAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onClick(v: View) {
        when (v.id) {
            //关闭
//            R.id.iv_close -> dismiss()
        }
    }


}