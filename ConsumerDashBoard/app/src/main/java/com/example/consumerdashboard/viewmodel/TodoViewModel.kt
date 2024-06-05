package com.example.consumerdashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consumerdashboard.data.dataclass.DeleteTodos
import com.example.consumerdashboard.data.dataclass.SingleTodos
import com.example.consumerdashboard.data.dataclass.TodoResponse
import com.example.consumerdashboard.data.dataclass.Todos
import com.example.consumerdashboard.data.network.NetworkResult
import com.example.consumerdashboard.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel
@Inject
constructor(var todoRepository: TodoRepository) : ViewModel(){

    private var gh = MutableLiveData<NetworkResult<TodoResponse>>()
    var vh : LiveData<NetworkResult<TodoResponse>> = gh

    private var _todos = MutableLiveData<NetworkResult<Todos>>()
    var todos : LiveData<NetworkResult<Todos>> = _todos

    private var _updateTodos = MutableLiveData<NetworkResult<Todos>>()
    var updateTodos : LiveData<NetworkResult<Todos>> = _updateTodos

    private var _deleteTodos = MutableLiveData<NetworkResult<DeleteTodos>>()
    var deleteTodos : LiveData<NetworkResult<DeleteTodos>> = _deleteTodos

    init {
        fetchTodoResponse()
    }

    /**
     * TodoData Response
     */
    private fun fetchTodoResponse() {
        viewModelScope.launch {
            todoRepository.getTodoData().collect{
                gh.postValue(it)

            }
        }
    }

    /**
     * Adding TodoItem
     */
    fun addTodo(todo : String,completed : Boolean,userId : String) {
        viewModelScope.launch {
            todoRepository.addTodoItem(todo,completed,userId).collect{
                _todos.postValue(it)
            }
        }
    }

    /**
     * Updating TodoItem
     */
    fun updatetodoItem(id : String,todo : String,completed : Boolean,userId : String) {
        viewModelScope.launch {
            todoRepository.updateTodoItem(id,todo,completed, userId).collect{
                _updateTodos.postValue(it)
            }
        }
    }

    /**
     * Deleting TodoItem
     */
    fun deleteTodoItem(id : String){
        viewModelScope.launch {
            todoRepository.deleteTodoItem(id).collect{
                _deleteTodos.postValue(it)
            }
        }
    }
}