package com.safmvvm.db

import android.util.ArrayMap

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.safmvvm.app.BaseApp
import com.safmvvm.app.globalconfig.GlobalConfig

/**
 * 便捷创建 room 实例的工具，通常来说只需要实例化一次即可，数据库也应该只有一个
 */
object RoomUtil {
    private val mDBEntityMap = ArrayMap<String, RoomDatabase>()

    @Synchronized
    fun <T : RoomDatabase> getDB(
        cls: Class<T>, dbName: String = GlobalConfig.DB.gDBName,
        callback: RoomDatabase.Callback? = null,
    ): T {
        val name = cls.name
        var database: RoomDatabase? = mDBEntityMap[name]
        if (database == null) {
            val builder = Room.databaseBuilder(
                BaseApp.getInstance(), cls,
                dbName
            )
            GlobalConfig.DB.gMigrationManager?.getMigrations()?.forEach {
                builder.addMigrations(it)
            }
            if (callback != null) {
                builder.addCallback(callback)
            }
            database = builder.build()
            mDBEntityMap[name] = database
        }
        @Suppress("UNCHECKED_CAST")
        return database as T
    }

}
