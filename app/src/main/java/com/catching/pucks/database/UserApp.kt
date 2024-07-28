package com.catching.pucks.database

import android.app.Application
import com.catching.pucks.database.DataBase.AppDatabase

class App : Application() {
    val database by lazy { AppDatabase.createDataBase(this) }
}