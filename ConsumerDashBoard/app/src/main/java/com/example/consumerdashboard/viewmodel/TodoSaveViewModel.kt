package com.example.consumerdashboard.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consumerdashboard.data.TodoItems
import com.example.consumerdashboard.data.dataclass.SingleTodos
import com.example.consumerdashboard.data.network.NetworkResultTwo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class TodoSaveViewModel
@Inject
constructor(var realm : Realm): ViewModel() {

    var TAG : String = "STATE"

  //  lateinit var realm: Realm
    private val _todoitems = MutableLiveData<ArrayList<TodoItems>>()
    val todoitems : LiveData<ArrayList<TodoItems>> = _todoitems
    var exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d(TAG,"The exception is ${throwable.message.toString()}")
    }


    /**
     *  Saved List
     */

    fun getTodoList(){
        viewModelScope.launch {
         //   realm = Realm.getDefaultInstance()
            withContext(Dispatchers.Main + exceptionHandler){
                var saveddata = realm.where(TodoItems::class.java).findAll()
                Log.d(TAG,"The saved data is $saveddata")
                _todoitems.value = ArrayList(saveddata)
            }
        }
    }

    /**
     * Update Item
     * */
    fun updatetodoItem(id : Int ,todo : String,completed : Boolean,userId : String){
        viewModelScope.launch{
                var todoItems = TodoItems()
                try {
                    realm.executeTransaction {
                        todoItems!!.todoname = todo
                        todoItems!!.id = id
                        todoItems!!.completed = completed
                        todoItems!!.userId = userId

                        realm.copyToRealmOrUpdate(todoItems)

                        Log.d(TAG, "Data has been updated $todoItems")
                    }
                }catch (e : Exception){
                    e.printStackTrace()
                    Log.d(TAG,"The saved error is ${e.message}")
                }
        }
    }

    /**
     * Deleting TodoItem
     */
    fun deleteTodoItem(data : TodoItems) {
        viewModelScope.launch {
            withContext(Dispatchers.Main + exceptionHandler){
                var todoitem = realm.where(TodoItems::class.java).equalTo("id",data.id).findFirst()
                try {
                    realm.executeTransaction {
                        todoitem!!.deleteFromRealm()
                    }
                }catch (e : Exception){
                    e.printStackTrace()
                    Log.d(TAG,"Delete Error is ${e.message}")
                }
            }
        }
    }

}