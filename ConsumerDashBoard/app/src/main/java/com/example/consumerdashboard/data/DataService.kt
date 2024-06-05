package com.example.consumerdashboard.data

import com.example.consumerdashboard.data.dataclass.DeleteTodos
import com.example.consumerdashboard.data.dataclass.SingleTodos
import com.example.consumerdashboard.data.dataclass.TodoRequest
import com.example.consumerdashboard.data.dataclass.TodoResponse
import com.example.consumerdashboard.data.dataclass.Todos
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface DataService {

    @GET("todos")
    suspend fun getTodos() : Response<TodoResponse>

    @GET("todos/{id}")
    suspend fun getTodosProfile(@Path("id") id : String) : Response<SingleTodos>

    @Headers("Content-Type: application/json")
    @POST("todos/add")
    suspend fun addTodos(@Body todoRequest: TodoRequest) : Response<Todos>

    @Headers("Content-Type: application/json")
    @PUT("todos/{id}")
    suspend fun updateTodo(@Path("id") id : String,@Body todoRequest: TodoRequest) : Response<Todos>

    @DELETE("todos/{id}")
    suspend fun deleteTodo(@Path("id") id : String) : Response<DeleteTodos>

}