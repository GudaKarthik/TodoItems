package com.example.daggerhilt

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

    var TAG : String = "HILTT"
    lateinit var txtHilt : TextView

    @Inject
    lateinit var personName: PersonName

    lateinit var recyclerview : RecyclerView
    lateinit var numberAdapter: NumberAdapter
    val preRegistrationViewModel : PreRegistrationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        txtHilt = findViewById(R.id.txthilt)
        txtHilt.text = personName.MyName()

        recyclerview = findViewById(R.id.hiltrecylerview)

        preRegistrationViewModel.getConsumer("1")

        preRegistrationViewModel.plist.observe(this, Observer {
            Log.d(TAG,"The response is $it")
            numberAdapter = NumberAdapter(it.response!!.consumers)
            recyclerview.adapter = numberAdapter
            recyclerview.layoutManager = LinearLayoutManager(this)
            numberAdapter.notifyDataSetChanged()
        })


    }
}

interface Details{
    fun getName() : String
}

class Person
@Inject
constructor(var nameg: String) : Details{

    override fun getName(): String {
       return nameg
    }
}

class PersonName
@Inject
constructor(var person: Person){
    fun MyName() : String{
        return person.getName()
    }
}

@Module
@InstallIn(SingletonComponent::class)
class NameModule{

    @Singleton
    @Provides
    fun tetName() : String = "Dependency"

    @Singleton
    @Provides
    fun hetName(jname : String) : Details = Person(jname)
}





