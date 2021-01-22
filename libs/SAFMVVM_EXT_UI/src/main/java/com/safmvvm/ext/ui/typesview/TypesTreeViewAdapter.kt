package com.safmvvm.ext.ui.typesview

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.safmvvm.ext.ui.R

/**
 * 列表统一适配器
 */
class TypesTreeViewAdapter(
    var context: Context,
    /** 级别*/
    var levelNum: Int,
    /** 总级别*/
    var totalLevelCount: Int,
    var selectPosition: Int = -1,
    var childer: List<TypesViewDataBean> = arrayListOf()
): RecyclerView.Adapter<TypesViewViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    fun setList(childer: List<TypesViewDataBean>){
        this.childer = childer
        notifyDataSetChanged()
    }

    fun setPosition(position: Int){
        selectPosition = position
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypesViewViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.extui_item_types, parent, false)
        return TypesViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: TypesViewViewHolder, position: Int) {
        var data = childer[position]
        holder.cb_item.text = data.text

        if (holder.adapterPosition == selectPosition) {
            //选中状态
            holder.ll_layout.setBackgroundColor(Color.parseColor("#66E5E6E8"))
            if (totalLevelCount-1 != levelNum) {
                holder.iv_arrow.visibility = View.VISIBLE
            }else{
                //最后一列不显示箭头
                holder.iv_arrow.visibility = View.INVISIBLE
            }
        } else {
            holder.ll_layout.setBackgroundColor(Color.parseColor("#FFFFFF"))
            //未选中没有状态
            holder.iv_arrow.visibility = View.INVISIBLE
        }

        holder.itemView.setOnClickListener{
            onItemClickListener?.onItemClick(levelNum, holder.adapterPosition, it, data)
        }
    }

    override fun getItemCount(): Int {
        return childer.size
    }

}

interface OnItemClickListener{

    fun onItemClick(levelNum: Int, position: Int, itemView: View, data: TypesViewDataBean?)
}

class TypesViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    var cb_item: TextView
    var iv_arrow: ImageView
    var ll_layout: LinearLayoutCompat

    init {
        cb_item = itemView.findViewById(R.id.cb_item)
        iv_arrow = itemView.findViewById(R.id.iv_arrow)
        ll_layout = itemView.findViewById(R.id.ll_layout)
    }
}