package com.longpc.testroom.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "table_te")
class TableEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    /** 名字 */
    @ColumnInfo(name = "ming_zi")
    var name: String,
    /** 年龄 */
    @ColumnInfo(name = "age")
    var age: Int,

    /** 性别*/
    @ColumnInfo(name = "sex")
    var sex: String?,

    @ColumnInfo(name = "other")
    var other: String?
)