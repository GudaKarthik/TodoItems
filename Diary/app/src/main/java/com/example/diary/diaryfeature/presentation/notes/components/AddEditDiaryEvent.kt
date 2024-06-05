package com.example.diary.diaryfeature.presentation.notes.components

import androidx.compose.ui.focus.FocusState

sealed class AddEditDiaryEvent {
    data class EnteredTitle(val value : String) : AddEditDiaryEvent()
    data class ChangeTitleFocus(val focusState : FocusState) : AddEditDiaryEvent()
    data class EnteredContent(val content : String) : AddEditDiaryEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditDiaryEvent()
    data class ChangeColor(val color: Int): AddEditDiaryEvent()
    object SaveNote: AddEditDiaryEvent()
}