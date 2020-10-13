package com.example.apirequest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apirequest.database.dao.UserEntityDao
import com.example.apirequest.database.entities.UserEntity

//Database tables, database version, no textfile
@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userEntityDao(): UserEntityDao
}