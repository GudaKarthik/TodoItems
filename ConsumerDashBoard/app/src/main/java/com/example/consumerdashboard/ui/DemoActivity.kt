package com.example.consumerdashboard.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumerdashboard.R
import com.example.consumerdashboard.adapter.ClickListener
import com.example.consumerdashboard.adapter.TodoAdapter
import com.example.consumerdashboard.data.dataclass.Todos
import com.example.consumerdashboard.data.network.NetworkResult
import com.example.consumerdashboard.databinding.ActivityDemoBinding
import com.example.consumerdashboard.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoActivity : AppCompatActivity(), ClickListener {

    private val todoViewModel : TodoViewModel by viewModels()
    var TAG : String = "STATE"
    lateinit var binding: ActivityDemoBinding
    lateinit var alertV : AlertDialog
    var todolist = ArrayList<Todos>()
    var dataTodos : Todos? = null
    var dataposition : Int? = null
    var updateTodos : Todos? = null
    var newtodo : String? = null
    lateinit var edtUptext : EditText

    lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
   //     setContentView(R.layout.activity_demo)
        title = "Todo List"

        binding = DataBindingUtil.setContentView(this, R.layout.activity_demo)

        binding.todoRecyclerview.layoutManager = LinearLayoutManager(this)

        binding.floatingAddbtn.setOnClickListener {
            showAlert()
        }

        /**
         * Todos List
         */

        todoViewModel.vh.observe(this, Observer {
            when(it){

                is NetworkResult.Loading ->{
                    binding.progressbar.visibility = View.VISIBLE
                    Log.d(TAG,"It is in loading state")
                }

                is NetworkResult.Success ->{
                    binding.progressbar.visibility = View.GONE
                    Log.d(TAG,"The response is ${it.data}")
                    todoAdapter = TodoAdapter(this,it.data.todos,this)
                    binding.todoRecyclerview.adapter = todoAdapter
                    todoAdapter.notifyDataSetChanged()
                }

                is NetworkResult.Failure -> {
                    binding.progressbar.visibility = View.GONE
                    Log.d(TAG,"The faiure is ${it.notLoading}")
                }

                else -> {
                    Log.d(TAG,"Please Try again")
                }
            }
        })

        /**
         * Adding TodoItem
         */
        todoViewModel.todos.observe(this, Observer {
            when(it) {

                is NetworkResult.Loading ->{
                    Log.d(TAG,"The state is ${it.isLoading}")
                }

                is NetworkResult.Success ->{
                    todoAdapter.addItem(it.data)
                    todoAdapter.notifyDataSetChanged()
                    Log.d(TAG,"The response is ${it.data}")

                }

                is NetworkResult.Failure -> {
                    Log.d(TAG,"The failure is ${it.notLoading}")
                }

                else -> {
                    Log.d(TAG,"The error is unknown")
                }
            }
        })

        /**
         * Updating TodoItem
         */
        todoViewModel.updateTodos.observe(this, Observer {
            when(it) {

                is NetworkResult.Loading ->{
                    Log.d(TAG,"Update is ${it.isLoading}")
                }

                is NetworkResult.Success -> {
                    updateTodos!!.todo = edtUptext.text.toString()
                    todoAdapter.notifyDataSetChanged()
                    Log.d(TAG,"The data is ${it.data} $dataposition")
                }

                is NetworkResult.Failure -> {
                    Log.d(TAG,"Reason for failure is ${it.notLoading}")
                }
            }
        })

        /**
         * Deleting TodoItem
         */
        todoViewModel.deleteTodos.observe(this, Observer {
            when(it){

                is NetworkResult.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }

                is NetworkResult.Success -> {
                    binding.progressbar.visibility = View.GONE
                    todoAdapter.deleteItem(dataTodos!!)
                    Log.d(TAG,"The response is ${it.data}")
                }

                is NetworkResult.Failure ->{
                    binding.progressbar.visibility = View.GONE
                    Log.d(TAG,"The failure is ${it.notLoading}")
                }
            }
        })

    }

    override fun onClick(data: Todos) {
        var intent = Intent(this,TodoViewActivity::class.java)
        intent.putExtra("id",data.id.toString())
        startActivity(intent)
    }

    // Update Item
    override fun updateItem(data: Todos,position : Int) {
        updateAlert(data)
    //    dataposition = position
        updateTodos = data
    }

    // Delete Item
    override fun deleteItem(data: Todos) {
        deleteAlert(data)
        dataTodos = data
    }

    // Adding Todos item
    @SuppressLint("MissingInflatedId")
    private fun showAlert(){
        alertV = AlertDialog.Builder(this).create()
        alertV.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        var layoutInflater : LayoutInflater = this.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var alertView = layoutInflater.inflate(R.layout.add_item,null)
        var edtTodoItem : EditText = alertView.findViewById(R.id.edtTodoItem)
        var btnConfirm : Button = alertView.findViewById(R.id.btnConfirm)

        btnConfirm.setOnClickListener {
            if (edtTodoItem.text.toString().isNullOrEmpty()){
                Toast.makeText(this,"Please enter text",Toast.LENGTH_SHORT).show()
            }else{
                todoViewModel.addTodo(edtTodoItem.text.toString(),true,"3")
                alertV.cancel()

            }

        }
        alertV.setView(alertView)
        alertV.setCancelable(true)
        alertV.show()

    }

    // Update Alert
    @SuppressLint("MissingInflatedId")
    private fun updateAlert(data: Todos) {
        alertV = AlertDialog.Builder(this).create()
        alertV.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        var layoutinflater : LayoutInflater = this.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var alertview = layoutinflater.inflate(R.layout.update_item,null)
        edtUptext = alertview.findViewById(R.id.edtUpdateTodoItem)
        var btnupdate : Button = alertview.findViewById(R.id.btnUpdate)

        edtUptext.setText(data.todo.toString())
        newtodo = edtUptext.text.toString()
        btnupdate.setOnClickListener {
            if (edtUptext.text.toString().isNullOrEmpty()){
                Toast.makeText(this,"Please enter text",Toast.LENGTH_SHORT).show()
            }else{
                todoViewModel.updatetodoItem(data.id.toString(),edtUptext.text.toString(),true,"35")
                alertV.cancel()
            }

        }

        alertV.setView(alertview)
        alertV.setCancelable(true)
        alertV.show()

    }

    // Delete Alert
    private fun deleteAlert(data: Todos) {
        var alertDialog = AlertDialog.Builder(this)
            .setTitle("Alert")
            .setMessage("Are you sure, want to delete item")
            .setPositiveButton("Ok"){ dialog, _ ->
                todoViewModel.deleteTodoItem(data.id.toString())
            }
            .setNegativeButton("Cancel"){ dialog,_->
                dialog.cancel()
            }
            .create()
        alertDialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.nav_bar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.save ->{
                var intent = Intent(this,SavedTodoActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}