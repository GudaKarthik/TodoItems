package com.example.consumerdashboard.data.dataclass

import com.google.gson.annotations.SerializedName

data class TodoResponse(@SerializedName("todos") var todos : ArrayList<Todos> = arrayListOf(),
                        @SerializedName("total") var total : Int? = null,
                        @SerializedName("skip") var skip  : Int? = null,
                        @SerializedName("limit") var limit : Int? = null)

data class Todos (@SerializedName("id") var id : Int? = null,
    @SerializedName("todo") var todo : String? = null,
    @SerializedName("completed") var completed : Boolean? = null,
    @SerializedName("userId") var userId : Int? = null)


data class SingleTodos (@SerializedName("id") var id : Int? = null,
                  @SerializedName("todo") var todo : String? = null,
                  @SerializedName("completed") var completed : Boolean? = null,
                  @SerializedName("userId") var userId : Int? = null)

data class DeleteTodos(@SerializedName("id") var id : Int? = null,
                       @SerializedName("todo") var todo : String? = null,
                       @SerializedName("completed") var completed : Boolean? = null,
                       @SerializedName("userId") var userId : Int? = null,
                       @SerializedName("isDeleted") var isDeleted : Boolean? = null,
                       @SerializedName("deletedOn") var deletedon : String? = null)

data class TodoRequest(
    var todo: String,
    var completed: Boolean?,
    var userId : String
)