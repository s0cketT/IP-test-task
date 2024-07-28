package com.catching.pucks.database.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val time: String,
    val tags: String,
    val tags2: String,
    val tags3: String,
    var amount: Int
)



