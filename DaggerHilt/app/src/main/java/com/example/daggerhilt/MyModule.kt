package com.example.daggerhilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MyModule {

    @Singleton
    @Provides
    fun getService() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.22/cgdapps/cgd_api/v5/PNG/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun setService(retrofit: Retrofit) : ApiService{
        return retrofit.create(ApiService::class.java)
    }
}