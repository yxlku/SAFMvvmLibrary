package com.safmvvm.ext.ui.typesview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.safmvvm.utils.LogUtil

/**
 * 分类列表
 */
class TypesThreeView : LinearLayoutCompat {

    var rvs: ArrayList<RecyclerView> = arrayListOf()
    /** 存放返回结果*/
    var resultData: ArrayList<TypesViewDataBean?> = arrayListOf()

    /** 结果回调函数*/
    var onClickResultListener: OnClickResultListener? = null

    /** 默认选中的数据*/
    var mDefSelectedCodes: ArrayList<TypesViewDataBean?> = arrayListOf()

    /** 显示几列*/
    var mLevelCount: Int = 2

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
        attrs,
        defStyleAttr) {
        initView()
    }

    /**
     * 设置数据
     * @param datas 数据
     * @param levelCount 层级
     * @param defSelectedCodes 默认选中数据 - 按照1~levelCount的顺序存放选中数据
     */
    fun setTypesDatas(
        datas: TypesTreeViewEntity,
        levelCount: Int = 2,
        defSelectedCodes: ArrayList<TypesViewDataBean?> = arrayListOf()
    ){
        this.mDefSelectedCodes = defSelectedCodes
        this.mLevelCount = levelCount
        initRvCount(levelCount)
        invalidate()
        if(rvs.size > 0){
            var rvOne = rvs[0]
            var adapterOne = rvOne.adapter as TypesTreeViewAdapter
            adapterOne.onItemClickListener = itemClick(adapterOne)

            datas.childer?.apply {
                adapterOne.setList(this)
                //默认选中数据显示
                initDefaultCheckData(adapterOne)
            } ?: apply {
                //无数据
            }
        }
    }

    /**
     * 创建列表及适配器
     */
    private fun initRvCount(size: Int){
        for(i in 0 until size) {
            //当前列表不为空则创建当前列表
            var rv = createRecyclerView()
            addView(rv)
            //列表数量
            rvs.add(rv)
            //结果初始化
            resultData.add(null)
            //创建适配器
            rv.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = TypesTreeViewAdapter(context, i, mLevelCount)
            }
        }
    }

    private fun initView() {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        orientation = HORIZONTAL

    }

    /**
     * 默认选中状态显示
     * @param adapter 第一个rv的适配器，或下个rv的适配器
     */
    fun initDefaultCheckData(adapter: TypesTreeViewAdapter){
        if (mDefSelectedCodes.isNullOrEmpty() || mDefSelectedCodes.size < mLevelCount) {
            return
        }
        adapter.childer.forEachIndexed { index, typesViewDataBean ->
            //如果当前列表中数据 有选中的数据则显示选中状态
            if (typesViewDataBean.code == mDefSelectedCodes[adapter.levelNum]?.code) {
                updateNextAdapterData(adapter, index, typesViewDataBean){ nextAdapter ->
                    initDefaultCheckData(nextAdapter)
                }
            }
        }

    }

    /**
     * 统一rv点击事件 用于唤醒下个rv的显示和数据更新
     */
    fun itemClick(adapter: TypesTreeViewAdapter): OnItemClickListener {
        var onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(
                levelNum: Int,
                position: Int,
                itemView: View,
                data: TypesViewDataBean?,
            ) {
                data?.apply {
                    updateNextAdapterData(adapter, position, this)
                }

            }
        }
        adapter.onItemClickListener = onItemClickListener
        return onItemClickListener
    }

    /**
     * 更新下条数据
     */
    private fun updateNextAdapterData(
        adapter: TypesTreeViewAdapter,
        index: Int,
        typesViewDataBean: TypesViewDataBean,
        block: (adapter: TypesTreeViewAdapter)->Unit = {}
    ) {
        //1、选中
        adapter.setPosition(index)
        //2、设置选中结果
        resultData[adapter.levelNum] = typesViewDataBean
        //3、最后一条数据点击回调
        if (typesViewDataBean?.childer == null || adapter.levelNum == mLevelCount - 1) {
            onClickResultListener?.apply {
                this.onClickCompleteResult(resultData)
            }
        }
        typesViewDataBean?.childer?.apply {
            //4、更新下一级数据
            if (adapter.levelNum < mLevelCount - 1) {
                for (i in mLevelCount - 1 downTo adapter.levelNum + 1) {
                    var otherAdapter = rvs[i].adapter as TypesTreeViewAdapter
                    otherAdapter.setPosition(-1)
                    otherAdapter.setList(arrayListOf())
                    //清空下级的选中结果
                    resultData[i] = null
                }

                var nextAdapter = rvs[adapter.levelNum + 1].adapter as TypesTreeViewAdapter
                nextAdapter.onItemClickListener = itemClick(nextAdapter)
                nextAdapter.setList(this)
                block(nextAdapter)

            }
        }
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

/**
 * 结果回调函数
 */
interface OnClickResultListener{

    /**
     * 最后一条点击结果回调
     */
    fun onClickCompleteResult(resultData: ArrayList<TypesViewDataBean?>)
}