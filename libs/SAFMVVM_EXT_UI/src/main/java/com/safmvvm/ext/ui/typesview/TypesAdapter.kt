package com.safmvvm.ext.ui.typesview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.safmvvm.ext.ui.R
import com.safmvvm.ext.ui.typesview.entity.BaseTypesNode

class TypesAdapter(
    var mContext: Context,
    var itemClickBlock: (view: View, data: BaseTypesNode)->Unit={view: View, data: BaseTypesNode->}
) : RecyclerView.Adapter<TypesViewHolder>() {
    var mDatas: List<BaseTypesNode> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypesViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.extui_item_types, parent, false)
        return TypesViewHolder(view)
    }

    override fun onBindViewHolder(holder: TypesViewHolder, position: Int) {
        holder.tv_text?.text = mDatas[position].textContent
        holder.tv_text?.setOnClickListener {
            itemClickBlock(it, mDatas[position])
        }
    }

    override fun getItemCount(): Int = mDatas.size

}

class TypesViewHolder(
    var itemView: View,
) : RecyclerView.ViewHolder(itemView) {
    var tv_text: TextView? = null
    init {
        tv_text = itemView.findViewById(R.id.tv_text)
    }

}