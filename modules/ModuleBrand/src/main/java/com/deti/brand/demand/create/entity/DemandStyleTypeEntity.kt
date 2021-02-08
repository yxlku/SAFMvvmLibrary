package com.deti.brand.demand.create.entity

import com.google.gson.annotations.SerializedName
import com.safmvvm.ext.ui.typesview.TypesTreeViewEntity
import com.safmvvm.ext.ui.typesview.TypesViewDataBean
import java.io.Serializable

/**
 * 创建需求：款式分类
 */
class DemandStyleTypeEntity(
    var tree: List<DemandStyleEntity>? = null,
): TypesTreeViewEntity(), Serializable{
    override var childer: List<TypesViewDataBean>?
        get() = tree
        set(value) {}
}

class DemandStyleEntity(
    var name: String = "",
    var children: List<DemandStyleEntity>? = null,
): TypesViewDataBean(), Serializable{
    override var childer: List<TypesViewDataBean>?
        get() = children
        set(value) {
            children
        }
    override var text: String
        get() = name
        set(value) {}

}