package com.example.todo.database.dao

import androidx.room.*
import com.example.todo.database.model.Todo

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

}