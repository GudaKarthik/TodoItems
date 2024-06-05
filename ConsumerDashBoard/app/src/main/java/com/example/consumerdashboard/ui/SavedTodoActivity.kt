package com.example.consumerdashboard.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.window.Dialog
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumerdashboard.R
import com.example.consumerdashboard.adapter.ClickSaved
import com.example.consumerdashboard.adapter.TodoSaveAdapter
import com.example.consumerdashboard.data.TodoItems
import com.example.consumerdashboard.data.dataclass.Todos
import com.example.consumerdashboard.databinding.ActivitySavedTodoBinding
import com.example.consumerdashboard.viewmodel.TodoSaveViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.realm.Realm
import javax.inject.Inject

@AndroidEntryPoint
class SavedTodoActivity : AppCompatActivity(),ClickSaved {

    var TAG : String = "STATE"
    lateinit var realm: Realm
    lateinit var todoSaveAdapter: TodoSaveAdapter
    var updateTodoitem : TodoItems? = null
    lateinit var alertV : AlertDialog
    lateinit var edtUptext : EditText

    val todoSaveViewModel : TodoSaveViewModel by viewModels()
    lateinit var binding: ActivitySavedTodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    //    setContentView(R.layout.activity_saved_todo)
        title = "Saved Todo Items"
        binding = DataBindingUtil.setContentView(this,R.layout.activity_saved_todo)

        todoSaveViewModel.getTodoList()
        todoSaveViewModel.todoitems.observe(this, Observer {
            binding.savedrecyclerview.layoutManager = LinearLayoutManager(this)
            todoSaveAdapter = TodoSaveAdapter(this@SavedTodoActivity,it,this)
            binding.savedrecyclerview.adapter = todoSaveAdapter
            todoSaveAdapter.notifyDataSetChanged()
        })


    }

    override fun updateItem(data: TodoItems) {
        updateAlert(data)
        updateTodoitem = data
    }

    override fun deleteItem(data: TodoItems) {
        deleteAlert(data)
    }

    private fun updateAlert(data: TodoItems) {
        alertV = AlertDialog.Builder(this).create()
        alertV.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        var layoutinflater : LayoutInflater = this.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var alertview = layoutinflater.inflate(R.layout.update_item,null)
        edtUptext = alertview.findViewById(R.id.edtUpdateTodoItem)
        var btnupdate : Button = alertview.findViewById(R.id.btnUpdate)

        edtUptext.setText(data.todoname)
    //    newtodo = edtUptext.text.toString()
        btnupdate.setOnClickListener {
            if (edtUptext.text.isNullOrEmpty()){
                Toast.makeText(this,"Enter Text",Toast.LENGTH_SHORT).show()
            }else{
                todoSaveViewModel.updatetodoItem(data.id,edtUptext.text.toString(),true,"35")
                todoSaveAdapter.notifyDataSetChanged()
                alertV.cancel()
            }


        }

        alertV.setView(alertview)
        alertV.setCancelable(true)
        alertV.show()
    }

    // Delete Alert
    private fun deleteAlert(data: TodoItems) {
        var alertDialog = AlertDialog.Builder(this)
            .setTitle("Alert")
            .setMessage("Are you sure, want to delete item")
            .setPositiveButton("Ok"){ dialog, _ ->
                todoSaveViewModel.deleteTodoItem(data)
                todoSaveAdapter.deleteItem(data)
                dialog.cancel()
            }
            .setNegativeButton("Cancel"){ dialog,_->
                dialog.cancel()
            }
            .create()
        alertDialog.show()
    }

}