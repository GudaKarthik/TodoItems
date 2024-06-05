package com.example.consumerdashboard.data.network

import com.example.consumerdashboard.data.dataclass.TodoResponse

sealed class NetworkResult<T> {
    data class Loading<T>(val isLoading : Boolean) : NetworkResult<T>()
    data class Success<T>(val data : T) : NetworkResult<T>()
    data class Failure<T>(val notLoading : String) : NetworkResult<T>()
}

sealed class NetworkResultTwo<T> {
    data class Loading<T>(val isLoading : Boolean) : NetworkResultTwo<T>()
    data class Success<T>(val data : T) : NetworkResultTwo<T>()
    data class Failure<T>(val notLoading : String) : NetworkResultTwo<T>()
}

