package com.longpc.testroom.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TableEntity ::class],
    version = 3,
    exportSchema = false
)
abstract class DataBase: RoomDatabase() {

    abstract fun tabEntityDao(): DbDao

}