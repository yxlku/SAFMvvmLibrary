package com.test.common.ui.popup.color

import android.app.Activity
import android.view.View
import androidx.collection.ArraySet
import androidx.collection.arraySetOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.lxj.xpopup.core.BottomPopupView
import com.lxj.xpopup.util.XPopupUtils
import com.safmvvm.ui.titlebar.OnTitleBarListener
import com.safmvvm.ui.titlebar.TitleBar
import com.test.common.R
import com.test.common.ui.popup.color.adapter.ColorsLeftAdapter
import com.test.common.ui.popup.color.adapter.ColorsSelectedAdapter
import com.test.common.ui.popup.color.adapter.ColorsRightAdapter

/**
 * 颜色选择弹窗
 */
class ColorsPopupView(
    var mActivit: Activity,
    var mTitle: String = "",
    var datas: DemandColorListEntity,
    var mHeightMultiple: Float = 0.8F,
    /** 点击标题确定返回的结果*/
    var resultBlock: (selectDatas: ArraySet<DemandColorDataBean>) -> Unit = {}
) : BottomPopupView(mActivit) {
    var rv_selected: RecyclerView? = null
    var rv_left: RecyclerView? = null
    var rv_right: RecyclerView? = null

    var adapterLeft: ColorsLeftAdapter = ColorsLeftAdapter()
    var adapterRight: ColorsRightAdapter = ColorsRightAdapter()
    var adapterSelected: ColorsSelectedAdapter = ColorsSelectedAdapter()

    /** 选择的列表数据*/
    var selectColorEntity = arraySetOf<DemandColorDataBean>()

    override fun getImplLayoutId(): Int = R.layout.base_dialog_colors

    /**
     * 弹窗最高高度
     */
    override fun getMaxHeight(): Int =
        (XPopupUtils.getScreenHeight(mActivit) * mHeightMultiple).toInt()

    override fun onCreate() {
        super.onCreate()
        var tb_title = findViewById<TitleBar>(R.id.tb_title)
        rv_selected = findViewById(R.id.rv_selected)
        rv_left = findViewById(R.id.rv_left)
        rv_right = findViewById(R.id.rv_right)

        tb_title.title = mTitle
        tb_title.setOnTitleBarListener(object : OnTitleBarListener{
            override fun onLeftClick(v: View?) {
                dismiss()
            }

            override fun onTitleClick(v: View?) {
            }

            override fun onRightClick(v: View?) {
                //确定按钮
                resultBlock(selectColorEntity)
                dismiss()
            }
        })


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
        adapterLeft.setList(datas.pageData)
        adapterLeft.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                var mAdapter = adapter as ColorsLeftAdapter
                mAdapter.selectedPosition = position
                mAdapter.notifyDataSetChanged()

                var firstData = mAdapter.data[position]
                var secondData = firstData.children
                adapterRight.setList(secondData)
            }
        })
        adapterRight.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                var mAdapter = adapter as ColorsRightAdapter
                var data = mAdapter.data[position]
                //刷新列表
                data.mIsCheck = !data.mIsCheck
                mAdapter.notifyDataSetChanged()

                //刷新选中区域列表
                if (!selectColorEntity.contains(data) && data.mIsCheck) {
                    selectColorEntity.add(data)
                }else{
                    selectColorEntity.remove(data)
                }
                adapterSelected.setList(selectColorEntity)
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


}