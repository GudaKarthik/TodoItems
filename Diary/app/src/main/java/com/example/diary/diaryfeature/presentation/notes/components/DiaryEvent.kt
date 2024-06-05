package com.example.diary.diaryfeature.presentation.notes.components

import com.example.diary.diaryfeature.domain.model.Note
import com.example.diary.diaryfeature.domain.util.DiaryOrder

sealed class DiaryEvent {
    data class Order(val diaryOrder: DiaryOrder) : DiaryEvent()
    data class DeleteNote(val note : Note) : DiaryEvent()
    object RestoreNote : DiaryEvent()
    object ToggleOrderSection : DiaryEvent()
}