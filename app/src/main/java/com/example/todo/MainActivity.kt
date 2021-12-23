package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.todo.database.MyDataBase
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var addButton: FloatingActionButton
    val todoListFragment = TodoListFragment()
    val settingsFragment = SettingsFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.navigationView)
        addButton = findViewById(R.id.add)
        addButton.setOnClickListener {
            // val intent =Intent(this , UpdateToDoActivity::class.java)
            // startActivity(intent)
            sowAddButtomSeet()

        }
        bottomNavigationView.setOnItemSelectedListener { item ->
            if (item.itemId == R.id.navigation_list) {
                pushFragment(todoListFragment)
            } else if (item.itemId == R.id.navigation_settings) {
                pushFragment(settingsFragment)
            }
            return@setOnItemSelectedListener true

        }

        bottomNavigationView.selectedItemId = R.id.navigation_list

        MyDataBase.getInstance(this)
    }

    private fun sowAddButtomSeet() {
        val addBottomSheet = AddTodoBottomSheet();
        addBottomSheet.show(supportFragmentManager, "")
        addBottomSheet.onTodoAddedLisner = object : AddTodoBottomSheet.onTodoAddedListner {
            override fun onTodoAdded() {
                //refreshtodo list from database inside listfragment
                //when added in seeting fragment to no get crach
                if (todoListFragment.isVisible)
                    todoListFragment.getTodoListFromDB()
            }
        }
    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()

    }
}