package com.example.todo.database.dao

import androidx.room.*
import com.example.todo.database.model.Todo
import java.util.*

//acsses data and implement methods

@Dao
interface TodoDao {

    @Insert
    fun addTodo(todo: Todo)

    @Update
    fun updateToda(todo: Todo)

    @Delete
    fun deleteTodo(todo: Todo)

    @Query("select * from Todo")
    fun getAllTodos(): List<Todo>

    @Query("select *from Todo where date=:date")
    fun getTodosByDate(date: Date): List<Todo>


}