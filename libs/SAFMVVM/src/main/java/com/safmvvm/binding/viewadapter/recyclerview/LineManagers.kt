package com.safmvvm.binding.viewadapter.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.safmvvm.binding.command.BindingFunction
import com.safmvvm.binding.viewadapter.recyclerview.DividerLine

/**
 * RecyclerView 分割线
 */
object LineManagers {
    @JvmStatic
    fun both(): BindingFunction<RecyclerView, RecyclerView.ItemDecoration> {
        return BindingFunction {
            DividerLine(
                it.context,
                DividerLine.LINE_BOTH
            )
        }
    }

    @JvmStatic
    fun horizontal(): BindingFunction<RecyclerView, RecyclerView.ItemDecoration> {
        return BindingFunction {
            DividerLine(
                it.context,
                DividerLine.LINE_HORIZONTAL
            )
        }
    }

    @JvmStatic
    fun vertical(): BindingFunction<RecyclerView, RecyclerView.ItemDecoration> {
        return BindingFunction {
            DividerLine(
                it.context,
                DividerLine.LINE_VERTICAL
            )
        }
    }
}