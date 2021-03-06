package com.example.todo.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//represent todo item

@Entity
data class Todo(
    @ColumnInfo
    @PrimaryKey  //by default make
    val id: Int = 0,
    @ColumnInfo
    val name: String? = null,
    @ColumnInfo
    val description: String? = null,
    @ColumnInfo
    val date: Date? = null,
    @ColumnInfo
    val isDone: Boolean? = false
)


