package com.example.daggerhilt

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class ProfileViewActivity : AppCompatActivity() {

    @Inject
    lateinit var status: Status
  //  lateinit var preRegProfileVM: PreRegProfileVM
    val preRegProfileVM : PreRegProfileVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_view)
   //     preRegProfileVM = ViewModelProvider(this).get(PreRegProfileVM::class.java)
        Toast.makeText(this,status.getStatus(),Toast.LENGTH_SHORT).show()
        preRegProfileVM

    }
}

interface Deliver{
    fun getDelivery() : String
}

class Order
@Inject
constructor() : Deliver{
    override fun getDelivery() : String {
        return "Your Order has been delivered"
    }
}

class Status
@Inject
constructor(var deliver: Deliver){

    fun getStatus() : String{
        return deliver.getDelivery()
    }
}

@Module
@InstallIn(SingletonComponent::class)
class OrderModule{

    @Singleton
    @Provides
    fun getOrder() : Deliver = Order()
}