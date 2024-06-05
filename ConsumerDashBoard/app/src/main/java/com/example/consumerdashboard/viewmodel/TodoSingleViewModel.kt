package com.example.consumerdashboard.viewmodel

//import com.example.consumerdashboard.data.TodoItems
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consumerdashboard.data.TodoItems
import com.example.consumerdashboard.data.dataclass.SingleTodos
import com.example.consumerdashboard.data.network.NetworkResultTwo
import com.example.consumerdashboard.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.Realm
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TodoSingleViewModel
@Inject
constructor(var repository: TodoRepository,var realm: Realm) : ViewModel() {

    var TAG : String = "STATE"
    private val _todoSingleresponse = MutableLiveData<NetworkResultTwo<SingleTodos>>()
    val todoSingleresponse : LiveData<NetworkResultTwo<SingleTodos>> = _todoSingleresponse
    var exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d(TAG,"The exception is ${throwable.message.toString()}")
    }

    /**
     * Single TodoData
     */
    suspend fun getTodoView(id : String){
        viewModelScope.launch {
            repository.getTodoView(id).collect{
                _todoSingleresponse.postValue(it)
            }
        }
    }

    /**
     * Saving TodoItem
     */
    fun saveTodoItem(context: Context,todo : String,completed : Boolean,userId : String){
        viewModelScope.launch {
            withContext(Dispatchers.Main + exceptionHandler) {

        //    realm = Realm.getDefaultInstance()
                var nextId : Int
                realm.executeTransaction({
                    var ids = it.where(TodoItems::class.java).max("id")
                    nextId = ids?.toInt()?.plus(1) ?: 1
                    var todoitems = TodoItems(nextId,todo,completed,userId)
                    it.insert(todoitems)
                    Toast.makeText(context,"Successfully saved",Toast.LENGTH_SHORT).show()
                    Log.d(TAG,"Items has been inserted $todoitems")
                })
            }
        }
    }

}