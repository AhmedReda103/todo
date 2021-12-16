package com.example.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.database.model.Todo

class TodosRecyclerAdapter(var items: MutableList<Todo>?) :
    RecyclerView.Adapter<TodosRecyclerAdapter.ViewHolder>() {

    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title)
        val description = itemView.findViewById<TextView>(R.id.description)
        val markAsDone = itemView.findViewById<ImageView>(R.id.mark_as_done)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items!!.get(position)
        holder.title.setText(item.name)
        holder.description.setText(item.description)

    }

    fun changeData(newitems: MutableList<Todo>) {
        items = newitems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items?.size ?: 0
}