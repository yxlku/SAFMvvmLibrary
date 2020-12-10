package com.safmvvm.db

import androidx.collection.ArraySet
import androidx.room.migration.Migration

/**
 * 版本升级管理
 */
object MigrationManager {
    private val migrations: ArraySet<Migration> = ArraySet()

    fun addMigration(migration: Migration): MigrationManager{
        migration?.let {
            migrations.add(migration)
        }
        return this
    }

    fun getMigrations(): ArraySet<Migration>{
        return migrations
    }
}