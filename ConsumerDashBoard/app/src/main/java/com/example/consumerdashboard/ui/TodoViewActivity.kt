package com.example.consumerdashboard.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.consumerdashboard.R
import com.example.consumerdashboard.data.dataclass.SingleTodos
import com.example.consumerdashboard.data.network.NetworkResult
import com.example.consumerdashboard.data.network.NetworkResultTwo
import com.example.consumerdashboard.databinding.ActivityTodoViewBinding
import com.example.consumerdashboard.viewmodel.TodoSingleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TodoViewActivity : AppCompatActivity() {

    val todoSingleViewModel : TodoSingleViewModel by viewModels()
    var scope : CoroutineScope = CoroutineScope(Dispatchers.Main)
    var TAG : String = "STATE"
    var id : String? = null
    lateinit var binding: ActivityTodoViewBinding
    var todo : SingleTodos? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
   //     setContentView(R.layout.activity_todo_view)
        title = "Todo Single View"

        binding = DataBindingUtil.setContentView(this,R.layout.activity_todo_view)

        id = intent.getStringExtra("id")

        scope.launch {
            todoSingleViewModel.getTodoView("$id")
        }

        /**
         * Single TodoResponse
         */

        todoSingleViewModel.todoSingleresponse.observe(this, Observer {
            when(it){
                is NetworkResultTwo.Loading ->{
                    binding.progressbar.visibility = View.VISIBLE
                    binding.cardview.visibility = View.GONE
                    binding.btnsave.visibility = View.GONE
                    Log.d(TAG,"It is in loading state ${it.isLoading}")
                }

                is NetworkResultTwo.Success -> {
                    binding.progressbar.visibility = View.GONE
                    binding.cardview.visibility = View.VISIBLE
                    binding.btnsave.visibility = View.VISIBLE
                    binding.txtTodo.text = it.data.todo
                    todo = it.data
                    Log.d(TAG,"Data is success ${it.data}")
                }

                is NetworkResultTwo.Failure -> {
                    binding.progressbar.visibility = View.GONE
                    binding.cardview.visibility = View.GONE
                    binding.btnsave.visibility = View.GONE
                    Log.d(TAG,"The reason for failure is ${it.notLoading}")
                }

                else -> {
                    Log.d(TAG,"Please try again")
                }
            }
        })

        // Saving TodoItem
        binding.btnsave.setOnClickListener {
            todoSingleViewModel.saveTodoItem(this,todo!!.todo!!,todo!!.completed!!,todo!!.userId!!.toString())
            Log.d(TAG,"Saving Todo")
            binding.btnsave.isEnabled = false
        }
    }
}