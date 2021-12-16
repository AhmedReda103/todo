package com.example.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.database.MyDataBase

class TodoListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    lateinit var recyclerView: RecyclerView
    var adapter: TodosRecyclerAdapter = TodosRecyclerAdapter(null)

    override fun onResume() {
        super.onResume()
        getTodoListFromDB()
    }

    private fun getTodoListFromDB() {

        val todoList =
            MyDataBase.getInstance(requireContext().applicationContext).todoDao().getAllTodos()
        adapter.changeData(todoList.toMutableList())

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = requireView().findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter

    }
}