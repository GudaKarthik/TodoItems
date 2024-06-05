package com.example.consumerdashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.consumerdashboard.R
import com.example.consumerdashboard.data.TodoItems
import com.example.consumerdashboard.data.dataclass.Todos

class TodoSaveAdapter(var context : Context,var savedlist : ArrayList<TodoItems>,var clickSaved: ClickSaved) : RecyclerView.Adapter<TodoSaveAdapter.SaveViewHolder>() {


    fun deleteItem(item : TodoItems){
        savedlist.remove(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.data_item,parent,false)
        return SaveViewHolder(view)
    }

    override fun getItemCount(): Int {
        return savedlist.size
    }

    override fun onBindViewHolder(holder: SaveViewHolder, position: Int) {
        var data = savedlist[position]
        holder.txttodo.text = data.todoname

        holder.btnupdate.setOnClickListener {
            clickSaved.updateItem(data)
        }

        holder.btnDelete.setOnClickListener {
            clickSaved.deleteItem(data)
        }
    }

    class SaveViewHolder(view: View) : ViewHolder(view){
        var txttodo : TextView = view.findViewById(R.id.txtTitle)
        var btnDelete : ImageView = view.findViewById(R.id.btndelete)
        var btnupdate : ImageView = view.findViewById(R.id.edtbutton)
    }
}

interface ClickSaved{
    fun updateItem(data : TodoItems)

    fun deleteItem(data : TodoItems)
}