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
                pushFragment(TodoListFragment())
            } else if (item.itemId == R.id.navigation_settings) {
                pushFragment(SettingsFragment())
            }
            return@setOnItemSelectedListener true

        }

        bottomNavigationView.selectedItemId = R.id.navigation_list


        MyDataBase.getInstance(this)
    }

    private fun sowAddButtomSeet() {
        val addBottomSheet = AddTodoBottomSheet();
        addBottomSheet.show(supportFragmentManager, "")
    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()

    }
}