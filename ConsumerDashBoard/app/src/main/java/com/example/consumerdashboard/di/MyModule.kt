package com.example.consumerdashboard.di

import com.example.consumerdashboard.data.Constants
import com.example.consumerdashboard.data.DataService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import io.realm.Realm

@Module
@InstallIn(SingletonComponent::class)
class MyModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit) : DataService{
        return retrofit.create(DataService::class.java)
    }

    @Singleton
    @Provides
    fun getRealm() : Realm{
        return Realm.getDefaultInstance()
    }

}