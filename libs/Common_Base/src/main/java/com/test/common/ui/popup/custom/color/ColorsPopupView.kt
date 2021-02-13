package com.test.common.ui.popup.custom.color

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.core.BottomPopupView
import com.lxj.xpopup.util.XPopupUtils
import com.safmvvm.ui.titlebar.OnTitleBarListener
import com.safmvvm.ui.titlebar.TitleBar
import com.test.common.R
import com.test.common.ui.popup.custom.color.adapter.ColorsLeftAdapter
import com.test.common.ui.popup.custom.color.adapter.ColorsRightAdapter
import com.test.common.ui.popup.custom.color.adapter.ColorsSelectedAdapter

/**
 * 颜色选择弹窗
 */
class ColorsPopupView(
    var mActivit: Activity,
    var mTitle: String = "",
    var datas: DemandColorListEntity,
    /** 选中的颜色*/
    var selectColors: List<DemandColorDataBean> = arrayListOf(),
    var mHeightMultiple: Float = 0.8F,
    /** 点击标题确定返回的结果*/
    var resultBlock: (selectDatas: ArrayList<DemandColorDataBean>, selectDatasText: ArrayList<String>, basePopupView: BasePopupView) -> Unit = {selectDatas: ArrayList<DemandColorDataBean>, selectDatasText: ArrayList<String>, basePopupView: BasePopupView->}
) : BottomPopupView(mActivit) {
    var rv_selected: RecyclerView? = null
    var rv_left: RecyclerView? = null
    var rv_right: RecyclerView? = null

    var adapterLeft: ColorsLeftAdapter = ColorsLeftAdapter()
    var adapterRight: ColorsRightAdapter = ColorsRightAdapter()
    var adapterSelected: ColorsSelectedAdapter = ColorsSelectedAdapter()


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
                var textList = ArrayList<String>()
                adapterSelected.data.forEach {
                    textList.add(it.name)
                }
                resultBlock(adapterSelected.data as ArrayList<DemandColorDataBean>, textList,this@ColorsPopupView)
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
            layoutManager = GridLayoutManager(context, 3)
            adapter = adapterSelected
        }
        //选中数据初始化
        adapterSelected.setList(arrayListOf())
        //左侧数据初始化
        adapterLeft.setList(datas.pageData)

        //默认选中的数据
        //1、选中列表
        adapterSelected.setList(selectColors)
        //2、默认打卡左边第一条数据
        if(adapterLeft.data.size > 0) {
            showRightRvInfo(adapterLeft, 0)
        }

        adapterLeft.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                showRightRvInfo(adapter, position)
            }
        })
        adapterRight.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                var mAdapter = adapter as ColorsRightAdapter
                var data = mAdapter.data[position]
                //刷新列表
                data.mIsCheck = !data.mIsCheck
                mAdapter.notifyDataSetChanged()
                if (data.mIsCheck) {
                    adapterSelected.addData(data)
                }else{
                    var iterator = adapterSelected.data.iterator()
                    while (iterator.hasNext()){
                        var itD = iterator.next()
                        if (itD.name == data.name) {
                            //存在就删除选中数据
                            iterator.remove()
                        }
                    }
                }
                adapterSelected.notifyDataSetChanged()
            }
        })
        //点击选中列表只代表删除对应子项
        adapterSelected.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                var mAdapter = adapter as ColorsSelectedAdapter
                var itemData = adapterSelected.data[position]
                adapterRight.data.forEach { rightData ->
                    //选中刷新子列表
                    if (itemData.name == rightData.name) {
                        rightData.mIsCheck = false
                        adapterRight.notifyDataSetChanged()
                    }
                }
                var iterator = adapterSelected.data.iterator()
                while (iterator.hasNext()){
                    var itD = iterator.next()
                    if (itD.name == itemData.name) {
                        //存在就删除选中数据
                        iterator.remove()
                    }
                }
                mAdapter.notifyDataSetChanged()
            }
        })
    }

    /**
     * 通过点击左侧列表 - 显示右侧数据
     * 触发场景：
     * 1、初始化显示第一条
     * 2、点击左侧列表时调用
     */
    private fun showRightRvInfo(
        adapter: BaseQuickAdapter<*, *>,
        position: Int,
    ) {
        var mAdapter = adapter as ColorsLeftAdapter
        mAdapter.selectedPosition = position
        mAdapter.notifyDataSetChanged()

        var firstData = mAdapter.data[position]
        var secondData = firstData.children
        //展开子列表时整理一遍是否选中
        for (i in secondData?.indices!!) {
            var secItem = secondData[i]
            if (adapterSelected.data.size <= 0) {
                secItem.mIsCheck = false
            } else {
                for (j in adapterSelected.data.indices) {
                    var sItem = adapterSelected.data[j]
                    if (secItem.name == sItem.name) {
                        secItem.mIsCheck = true
                        break
                    } else {
                        secItem.mIsCheck = false
                    }
                }
            }
        }
        adapterRight.setList(secondData)
    }


}