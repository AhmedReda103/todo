package com.example.todo.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.todo.database.dao.TodoDao
import com.example.todo.database.model.Todo


@Database(entities = [Todo::class], version = 2, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MyDataBase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {
        val DataBase_Name = "todo-DataBase"
        private var myDataBase: MyDataBase? = null
        fun getInstance(context: Context): MyDataBase {

            //single object from database (singleton0
            if (myDataBase == null)
            //build object from database
            {
                myDataBase = Room.databaseBuilder(context, MyDataBase::class.java, DataBase_Name)
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }

            return myDataBase!!;
        }

    }
}