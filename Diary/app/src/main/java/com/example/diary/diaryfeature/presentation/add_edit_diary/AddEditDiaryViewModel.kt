package com.example.diary.diaryfeature.presentation.add_edit_diary

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diary.diaryfeature.domain.model.InvalidNoteException
import com.example.diary.diaryfeature.domain.model.Note
import com.example.diary.diaryfeature.domain.use_case.DiaryUseCases
import com.example.diary.diaryfeature.presentation.notes.components.AddEditDiaryEvent
import com.example.diary.diaryfeature.presentation.notes.components.NoteTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditDiaryViewModel
@Inject
constructor(val useCases: DiaryUseCases,savedstateHandle : SavedStateHandle) : ViewModel(){

    private var _noteTitle = mutableStateOf(NoteTextFieldState(hint = "Enter Title..."))
    var noteTitle : State<NoteTextFieldState> = _noteTitle

    private var _noteContent = mutableStateOf(NoteTextFieldState(hint = "Write Content..."))
    var noteContent : State<NoteTextFieldState> = _noteContent

    private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
     val noteColor : State<Int> = _noteColor

    private val _eventflow = MutableSharedFlow<UiEvent>()
    val eventflow = _eventflow.asSharedFlow()

    private var currentId : Int? = null

    init {
        savedstateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1){
                viewModelScope.launch {
                    useCases.getNote(noteId)?.also { note ->
                        currentId = noteId
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _noteColor.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event : AddEditDiaryEvent){
        when(event){
            is AddEditDiaryEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }

            is AddEditDiaryEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && _noteTitle.value.text.isBlank()
                )
            }

            is AddEditDiaryEvent.EnteredContent -> {
                _noteContent.value = _noteContent.value.copy(
                    text = event.content
                )
            }

            is AddEditDiaryEvent.ChangeContentFocus -> {
                _noteContent.value = _noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused && _noteContent.value.text.isBlank()
                )
            }

            is AddEditDiaryEvent.ChangeColor -> {
                _noteColor.value = event.color
            }

            is AddEditDiaryEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        useCases.addNote(
                            Note(
                                title = noteTitle.value.text,
                                content = noteContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = noteColor.value,
                                id = currentId
                            )
                        )
                        _eventflow.emit(UiEvent.SaveNote)
                    }catch (e : InvalidNoteException){
                        _eventflow.emit(UiEvent.ShowSnackBar(message = e.message ?: "Couldn't save note"))
                    }
                }
            }
        }
    }


    sealed class UiEvent{
        data class ShowSnackBar(var message : String) : UiEvent()
        object SaveNote : UiEvent()
    }

}