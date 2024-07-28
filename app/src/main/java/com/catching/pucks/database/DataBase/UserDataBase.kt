package com.catching.pucks.database.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// База данных
@Database(entities = [User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    //abstract fun userDao(): UserDao
    abstract val dao: UserDao

    companion object {
        fun createDataBase(context : Context) : AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database-name"
            ).fallbackToDestructiveMigration().build()
        }
    }
}