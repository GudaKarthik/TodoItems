package com.example.daggerhilt

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("preDomesticConsumers?employee=1&emp_type=1&emp_role=1&emp_state=2&emp_ga=1&emp_district=1&records=&page=&keywords&state&ga&district&location&paid_status&next_page_status")
    suspend fun getConsumers(@Query("employee") employee : String) : Response<PreRegistraionResponse>

    @GET("preDomesticConsumer?employee=1")
    suspend fun getConsumerProfile(@Query("consumer_id") consumerid : String) : Response<PreregProfileResponse>
}