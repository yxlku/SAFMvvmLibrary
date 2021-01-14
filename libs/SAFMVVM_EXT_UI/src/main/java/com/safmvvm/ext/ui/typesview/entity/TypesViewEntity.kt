package com.safmvvm.ext.ui.typesview.entity

abstract class TypesViewBaseEntity<T> {
    abstract var id: Int
    abstract var textContent: String
    abstract var childEntity: List<T>?
}

data class TypesViewEntity(
    override var id: Int,
    override var textContent: String,
    override var childEntity: List<TypesViewTwoEntity>?,
) : TypesViewBaseEntity<TypesViewTwoEntity>()

data class TypesViewTwoEntity(
    override var id: Int,
    override var textContent: String,
    override var childEntity: List<TypesViewThreeEntity>?,
) : TypesViewBaseEntity<TypesViewThreeEntity>()

data class TypesViewThreeEntity(
    override var id: Int,
    override var textContent: String,
    override var childEntity: List<TypesViewFourEntity>?,
) : TypesViewBaseEntity<TypesViewFourEntity>()

data class TypesViewFourEntity(
    override var id: Int,
    override var textContent: String,
    override var childEntity: List<Unit>?,
    ) : TypesViewBaseEntity<Unit>()