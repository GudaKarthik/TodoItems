package com.example.daggerhilt

import com.google.gson.annotations.SerializedName

data class Numbers(var id : String,
    var num : String)


data class PreRegistraionResponse(@SerializedName("status") var status : Boolean?= null,
                                  @SerializedName("response") var response : PreRegistration?= PreRegistration(),
                                  @SerializedName("error") var error : String? = null)


data class PreRegistration(@SerializedName("count") var count : String? = null,
                           @SerializedName("next_page_status") var nextpagestatus : Int? = null,
                           @SerializedName("consumers") var consumers : ArrayList<PreRegistraionConsumers> = arrayListOf())


data class PreRegistraionConsumers(@SerializedName("id") var id : String? = null,
                                   @SerializedName("code") var code : String? = null,
                                   @SerializedName("name") var name : String? = null,
                                   @SerializedName("mobile") var mobile : String? = null,
                                   @SerializedName("state") var state : String? = null,
                                   @SerializedName("district") var district : String? = null,
                                   @SerializedName("location") var location : String? = null,
                                   @SerializedName("created_date") var createdDate : String? = null,
                                   @SerializedName("created_by") var createdBy : String? = null,
                                   @SerializedName("payment_status") var paymentStatus : String? = null)

sealed class NetworkResult<T> {
    data class Loading<T>(val isLoading: Boolean) : NetworkResult<T>()
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Failure<T>(val errorMessage: String) : NetworkResult<T>()
}