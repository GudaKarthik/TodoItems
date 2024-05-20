package com.example.daggerhilt

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileViewActivity : AppCompatActivity() {

  //  lateinit var preRegProfileVM: PreRegProfileVM
    val preRegProfileVM : PreRegProfileVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_view)
   //     preRegProfileVM = ViewModelProvider(this).get(PreRegProfileVM::class.java)
        preRegProfileVM
    }
}