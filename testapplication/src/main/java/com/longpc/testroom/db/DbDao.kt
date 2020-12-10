package com.longpc.testroom.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DbDao {

    /**
     * 插入数据
     */
    @Insert
    suspend fun insertAll(tableEntity: TableEntity)


    @Query("SELECT * FROM table_te")
    fun getAll(): List<TableEntity>


}