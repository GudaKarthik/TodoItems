package com.example.consumerdashboard.repository

import com.example.consumerdashboard.data.DataService
import com.example.consumerdashboard.data.dataclass.TodoRequest
import com.example.consumerdashboard.data.network.NetworkResult
import com.example.consumerdashboard.data.network.NetworkResultTwo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType
import okhttp3.RequestBody
import javax.inject.Inject

class TodoRepository @Inject constructor(private val dataService: DataService) {

    /**
     * TodoData
     */
    suspend fun getTodoData()  = flow {
        emit(NetworkResult.Loading(true))
        val response = dataService.getTodos()
        emit(NetworkResult.Success(response.body()!!))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

    /**
    Single TodoData
     */
    suspend fun getTodoView(id : String) = flow {
        emit(NetworkResultTwo.Loading(true))
        var sresponse = dataService.getTodosProfile(id)
        emit(NetworkResultTwo.Success(sresponse.body()!!))
    }.catch {e ->
        emit(NetworkResultTwo.Failure(e.message ?: "Unknown Error"))
    }

    /**
     * Adding TodoItem
     */
    suspend fun addTodoItem(todo : String,completed : Boolean,userId : String) = flow {
        emit(NetworkResult.Loading(true))
        var todorequest = TodoRequest(todo = todo,
            completed = completed, userId = userId)
        val response = dataService.addTodos(todorequest)
        emit(NetworkResult.Success(response.body()!!))
    }.catch {e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown error"))
    }

    /**
     * Updating TodoItem
     */
    suspend fun updateTodoItem(id : String,todo : String,completed : Boolean,userId : String) = flow {
        emit(NetworkResult.Loading(true))
        var todorequest = TodoRequest(todo = todo,completed = completed,userId = userId)
        var response = dataService.updateTodo(id,todorequest)
        emit(NetworkResult.Success(response.body()!!))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown error"))
    }

    /**
     * Deleting TodoItem
     */
    suspend fun deleteTodoItem(id : String) = flow {
        emit(NetworkResult.Loading(true))
        var response = dataService.deleteTodo(id)
        emit(NetworkResult.Success(response.body()!!))
    }.catch { e->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }
}