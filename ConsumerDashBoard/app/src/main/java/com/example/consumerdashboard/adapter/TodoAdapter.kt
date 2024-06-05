package com.example.consumerdashboard.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.consumerdashboard.R
import com.example.consumerdashboard.R.id.edtbutton
import com.example.consumerdashboard.data.dataclass.Todos

class TodoAdapter(var context: Context,var todolist : ArrayList<Todos>,var onClick: ClickListener) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    fun addItem(item : Todos){
        todolist.add(item)
        notifyDataSetChanged()
    }

    fun deleteItem(item : Todos){
        todolist.remove(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TodoViewHolder {
        var view = LayoutInflater.from(p0.context).inflate(R.layout.data_item,p0,false)
        return TodoViewHolder(view)

    }
    override fun getItemCount(): Int {
        return todolist.size
    }

    @SuppressLint("MissingInflatedId")
    override fun onBindViewHolder(holder: TodoViewHolder, p1: Int) {
        var data = todolist[p1]
        holder.title.text = data.todo.toString()
        holder.itemView.setOnClickListener {
            onClick.onClick(data)
        }
        holder.edtimage.setOnClickListener {
            onClick.updateItem(data, p1)
        }

        holder.deleteimage.setOnClickListener {
            onClick.deleteItem(data)
        }
    }

    class TodoViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var title : TextView = view.findViewById(R.id.txtTitle)
        var edtimage : ImageView = view.findViewById(edtbutton)
        var deleteimage : ImageView = view.findViewById(R.id.btndelete)
    }
}

interface ClickListener{
    fun onClick(data : Todos)

    fun updateItem(data : Todos,position : Int)

    fun deleteItem(data : Todos)
}