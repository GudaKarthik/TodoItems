package com.example.daggerhilt

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreRegistrationViewModel
@Inject
constructor(var preRegistrationRepository: PreRegistrationRepository) : ViewModel(){

    var scope : CoroutineScope = CoroutineScope(Dispatchers.Main)
    var plist = MutableLiveData<PreRegistraionResponse>()
    var slist : LiveData<PreRegistraionResponse> = plist
    var presp = MutableLiveData<PreregProfileResponse>()

    fun getConsumer(id : String){
        scope.launch {
            plist.value = preRegistrationRepository.getConsumers(id)
        }
    }

    fun getProfile(id : String){
        scope.launch {
            presp.value = preRegistrationRepository.getProfile(id)
            Log.d("HILTT","The profile is ${presp.value}")

        }
    }
}