package com.example.diary.diaryfeature.presentation.notes.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.FtsOptions.Order
import com.example.diary.diaryfeature.domain.model.Note
import com.example.diary.diaryfeature.domain.use_case.DiaryUseCases
import com.example.diary.diaryfeature.domain.util.DiaryOrder
import com.example.diary.diaryfeature.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel
@Inject
constructor(private val diaryUseCases: DiaryUseCases) : ViewModel(){

    private val _state = mutableStateOf(DiaryState())
    var state : State<DiaryState> = _state

    private var recentlydeletednote : Note? = null
    private var getNotesJob : Job? = null

    init {
        getDiary(DiaryOrder.Date(OrderType.Descending))
    }

    fun onEvent(event : DiaryEvent){
        when(event){

            is DiaryEvent.Order ->{
                if (state.value.diaryOrder::class == event.diaryOrder::class &&
                    state.value.diaryOrder.orderType == event.diaryOrder.orderType){
                    return
                }
                getDiary(event.diaryOrder)
            }

            is DiaryEvent.DeleteNote ->{
                viewModelScope.launch {
                    diaryUseCases.deleteNote(event.note)
                    recentlydeletednote = event.note
                }
            }

            is DiaryEvent.RestoreNote ->{
                viewModelScope.launch {
                    diaryUseCases.addNote(recentlydeletednote ?: return@launch)
                    recentlydeletednote = null
                }
            }

            is DiaryEvent.ToggleOrderSection ->{
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getDiary(diaryOrder: DiaryOrder) {
        getNotesJob?.cancel()
        getNotesJob = diaryUseCases.getNotes(diaryOrder)
            .onEach { _diary ->
                _state.value = state.value.copy(
                    notes = _diary,
                    diaryOrder = diaryOrder
                )
            }.launchIn(viewModelScope)
    }
}