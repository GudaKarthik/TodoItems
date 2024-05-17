package com.example.daggerhilt

import retrofit2.Response
import javax.inject.Inject

class PreRegistrationRepository
@Inject
constructor(var apiService: ApiService){

   suspend fun getConsumers(id : String) : PreRegistraionResponse {
        var ret = apiService.getConsumers(id)
         return ret!!.body()!!
    }
}