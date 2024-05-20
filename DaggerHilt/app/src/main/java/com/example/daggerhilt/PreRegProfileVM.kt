package com.example.daggerhilt

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreRegProfileVM
    @Inject
    constructor(var preRegistrationRepository: PreRegistrationRepository): ViewModel(){

    var tgr = MutableLiveData<PreregProfileResponse>()
    var exceptionHandler = CoroutineExceptionHandler{_,throwable ->
        Log.d("HILTT","The error is ${throwable.message}")
    }
    var scope = CoroutineScope(Dispatchers.Main + exceptionHandler)

    init {
        getNum()
    }

    fun getNum(){
        scope.launch {
            var gt = preRegistrationRepository.getProfile("14315")

            Log.d("HILTT", "The number is 83 and ${gt.response.toString()}")
        }
    }

}